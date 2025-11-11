import Maze.MazeLoader;
import Maze.MazeStruct;

import java.io.IOException;

public class Main  {
    public static void main(String[] args) throws IOException {
        MazeStruct maze = MazeLoader.loadFromFile("/home/mohee/IdeaProjects/MazeCollapseAlgoJava/src/test.txt");
        System.out.println(maze);


    }
}