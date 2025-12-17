package Algorithm;

import Maze.MazeStruct;
import Maze.Tile;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
public class AStarSolver {
    private int generatedStates;
    private int visitedStates;

    public MazeStruct aStarSearch(MazeStruct startState) {
        HashMap<MazeStruct, Integer> gScore = new HashMap<>();
        HashSet<MazeStruct> closed = new HashSet<>();

        generatedStates = 0;
        visitedStates = 0;

        Comparator<MazeStruct> comparator = (a, b) -> {
            int fa = fScore(a, gScore);
            int fb = fScore(b, gScore);
            return Integer.compare(fa, fb);
        };

        PriorityQueue<MazeStruct> open = new PriorityQueue<>(comparator);
        startState.parent = null;
        startState.player.currentCost = 0;
        gScore.put(startState, 0);
        open.add(startState);

        while (!open.isEmpty()) {
            MazeStruct current = open.poll();
            if (current.CheckWinCondition() != null && current.CheckWinCondition() == true) {
                return current;
            }
            if (closed.contains(current)) {
                continue;
            }
            closed.add(current);
            visitedStates++;

            for (MazeStruct neighbor : current.generateNextStates()) {
                generatedStates++;
                if (closed.contains(neighbor)) {
                    continue;
                }

                int tentativeG = neighbor.player.currentCost;
                Integer knownG = gScore.get(neighbor);

                if (knownG == null || tentativeG < knownG) {
                    neighbor.parent = current;
                    gScore.put(neighbor, tentativeG);
                    open.add(neighbor);
                }
            }
        }

        return null;
    }

    private int fScore(MazeStruct state, HashMap<MazeStruct, Integer> gScore) {
        int g = gScore.getOrDefault(state, Integer.MAX_VALUE / 2);
        int h = heuristic(state);
        return g + h;
    }
    private int heuristic(MazeStruct state) {
        int px = state.player.x;
        int py = state.player.y;
        int best = Integer.MAX_VALUE;

        for (Map.Entry<Point, Tile> entry : state.maze.entrySet()) {
            Tile t = entry.getValue();
            if (t != null && t.end) {
                Point p = entry.getKey();
                int manhattan = Math.abs(px - p.x) + Math.abs(py - p.y);
                if (manhattan < best) {
                    best = manhattan;
                }
            }
        }

        return best == Integer.MAX_VALUE ? 0 : best;
    }

    public List<MazeStruct> reconstructPath(MazeStruct goalState) {
        List<MazeStruct> path = new ArrayList<>();
        MazeStruct current = goalState;

        while (current != null) {
            path.add(0, current);
            current = current.parent;
        }

        return path;
    }

    public int getGeneratedStates() {
        return generatedStates;
    }

    public int getVisitedStates() {
        return visitedStates;
    }
}

