package Algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import Maze.MazeStruct;

public class GraphSearchAlgorithms {
    public MazeStruct breadthFirstSearch(MazeStruct startState) {
        Queue<MazeStruct> queue = new LinkedList<>();
        HashSet<MazeStruct> visited = new HashSet<>();
        startState.parent = null;
        queue.add(startState);
        visited.add(startState);
        while (!queue.isEmpty()) {
            MazeStruct current = queue.poll();
            if (current.CheckWinCondition() != null && current.CheckWinCondition() == true) {
                return current;
            }
            HashSet<MazeStruct> nextStates = current.generateNextStates();
            for (MazeStruct nextState : nextStates) {
                if (!visited.contains(nextState)) {
                    nextState.parent = current;
                    visited.add(nextState);
                    queue.add(nextState);
                }
            }
        }
        
        return null;
    }
    public MazeStruct depthFirstSearch(MazeStruct startState) {
        Stack<MazeStruct> stack = new Stack<>();
        HashSet<MazeStruct> visited = new HashSet<>();
        startState.parent = null;
        stack.push(startState);
        visited.add(startState);
        
        while (!stack.isEmpty()) {
            MazeStruct current = stack.pop();
            
            if (current.CheckWinCondition() != null && current.CheckWinCondition() == true) {
                return current;
            }
            
            HashSet<MazeStruct> nextStates = current.generateNextStates();
            for (MazeStruct nextState : nextStates) {
                if (!visited.contains(nextState)) {
                    nextState.parent = current;
                    visited.add(nextState);
                    stack.push(nextState);
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
}

