package Algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import Maze.MazeStruct;

public class MazeSolver {
    public PriorityQueue<MazeStruct> openSet;
    public HashSet<MazeStruct> visited = new HashSet<>();
    private Comparator<MazeStruct> ucsComparator = new Comparator<MazeStruct>() {
        @Override
        public int compare(MazeStruct m1, MazeStruct m2) {
            return Integer.compare(m1.player.currentCost, m2.player.currentCost);
        }
    };
    public MazeStruct uniformCostSearch(MazeStruct startState) {
        openSet = new PriorityQueue<>(ucsComparator);
        visited = new HashSet<>();
        startState.player.currentCost = 0;
        startState.parent = null;
        openSet.add(startState);
        
        while (!openSet.isEmpty()) {
            MazeStruct current = openSet.poll();
            if (current.CheckWinCondition() != null && current.CheckWinCondition() == true) {
                return current;
            }

            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);
            HashSet<MazeStruct> nextStates = current.generateNextStates();
            for (MazeStruct nextState : nextStates) {
                nextState.parent = current;
                if (!visited.contains(nextState)) {
                    openSet.add(nextState);
                }
            }
        }
        return null;
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
    public PriorityQueue<MazeStruct> initMazeSolver(MazeStruct mazeStruct) {
        MazeStruct solution = uniformCostSearch(mazeStruct);
        openSet = new PriorityQueue<>(ucsComparator);
        if (solution != null) {
            openSet.add(solution);
        }
        return openSet;
    }
}
