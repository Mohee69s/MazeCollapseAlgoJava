package Algorithm;

import Maze.MazeLoader;
import Maze.MazeStruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.Stack;

public class MazeSolver {
    HashSet<MazeStruct> visited = new HashSet<>();
    int vis = 0;
    static int gen1 = 0;

    public static Stack<MazeStruct> generatedStates(MazeStruct mazeStruct){
        HashSet<MazeStruct> generatedEachStep;
        Stack<MazeStruct> generated = new Stack<>();
        generatedEachStep = mazeStruct.generateNextStates();
        for (MazeStruct m : generatedEachStep){
            generated.push(m);
            gen1+=1;
        }
        return generated;
    }


    public HashSet<MazeStruct> DFSLogic(Stack<MazeStruct> generatedMazes){
        MazeStruct m;
        while(!generatedMazes.isEmpty()){
            m=generatedMazes.pop();
            if(visited.contains(m)) {
                continue;
            }else {
                vis += 1;
                visited.add(m);
                System.out.println(m);

                if (m.CheckWinCondition() != null && m.CheckWinCondition()) {
                    System.out.println("Generated: " + gen1);
                    System.out.println("Visited: " + vis);
                    return visited;
                }
                DFSLogic(generatedStates (m));
            }
    }
        return null;

//
//        MazeStruct m;
//        while (!generatedMazes.isEmpty()){
//            m=generatedMazes.pop();
//            if ()
//        }
//
    }
    public void InitDFSInShaaAllah(MazeStruct mazeStruct){
        Stack<MazeStruct> stack = new Stack<>();
        HashSet<MazeStruct>  gen= mazeStruct.generateNextStates();
        for (MazeStruct m : gen){
            stack.push(m);
        }
        DFSLogic(stack);
}
}