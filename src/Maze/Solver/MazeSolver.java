package Maze.Solver;

import Maze.MazeLoader;
import Maze.MazeStruct;

import java.io.IOException;
import java.util.*;

public class MazeSolver {
    public static void main(String[] args) throws IOException {
        MazeStruct startMaze = MazeLoader.loadFromFile("test.txt") ;

        Queue<MazeStruct> queue = new LinkedList<>();
        Set<MazeStruct> visited = new HashSet<>();
        Map<MazeStruct, MazeStruct> parent = new HashMap<>();

        queue.add(startMaze);
        visited.add(startMaze);
        parent.put(startMaze, null);

        MazeStruct goalState = null;

        while (!queue.isEmpty()) {
            MazeStruct current = queue.poll();
            if (Boolean.TRUE.equals(current.CheckWinCondition())) {
                goalState = current;
                break;
            }

            for (MazeStruct next : current.generateNextStates()) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    parent.put(next, current);
                    queue.add(next);
                }
            }
        }

        if (goalState == null) {
            System.out.println("No solution found.");
        } else {
            // Reconstruct path
            List<MazeStruct> path = new ArrayList<>();
            MazeStruct temp = goalState;
            while (temp != null) {
                path.add(temp);
                temp = parent.get(temp);
            }
            Collections.reverse(path);

            System.out.println("Solution found! Steps: " + path.size());
            for (MazeStruct step : path) {
                System.out.println(step);
                System.out.println("------------");
            }
        }
    }
}
