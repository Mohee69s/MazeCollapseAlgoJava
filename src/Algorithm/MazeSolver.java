package Algorithm;

import java.util.HashSet;
import java.util.PriorityQueue;
import Maze.MazeStruct;

public class MazeSolver {
    public PriorityQueue<MazeStruct> openSet;
    public HashSet<MazeStruct> visited = new HashSet<>();
    public PriorityQueue<MazeStruct> initMazeSolver(MazeStruct mazeStruct) {

        openSet = new PriorityQueue<>();
        if (mazeStruct.CheckWinCondition() != null && mazeStruct.CheckWinCondition() == true) {
            openSet.add(mazeStruct);
            return openSet;
        }
        if (visited.contains(mazeStruct)) {
            return null;
        }
        else {
        openSet.add(mazeStruct);
        visited.add(mazeStruct);
        }
        HashSet<MazeStruct> nextStates = new HashSet<>();
        nextStates = mazeStruct.generateNextStates();
        for (MazeStruct maze : nextStates) {
            initMazeSolver(maze);
        }
        return openSet;
    }

    
}
