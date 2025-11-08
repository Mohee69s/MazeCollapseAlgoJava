import Maze.Tile;

import java.util.HashSet;

public class MazeStruct {
    public HashSet<Tile> maze;
    public int height;
    public int width;
    public MazeStruct(HashSet<Tile> maze, int height, int width ) {
        this.maze=maze;
        this.height=height;
        this.width=width;
    }


}
