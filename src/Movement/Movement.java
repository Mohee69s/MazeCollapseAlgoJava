package Movement;

import Maze.MazeStruct;
import Maze.Tile;
import java.awt.*;

public class Movement {
    public static boolean CanMoveUp(MazeStruct mazeStruct){
        Tile tile = mazeStruct.maze.get(new Point(mazeStruct.player.x,mazeStruct.player.y-1));
        if (tile==null  || tile.health==0){
            return false;
        }
        if(tile.locked){
            return mazeStruct.player.keys > 0;
        }
        else {
            return true;
        }
    }
    public static boolean CanMoveDown(MazeStruct mazeStruct ){
        Tile tile = mazeStruct.maze.get(new Point(mazeStruct.player.x,mazeStruct.player.y+1));
        if (tile==null || tile.health==0){
            return false;
        }
        if(tile.locked){
            return mazeStruct.player.keys < 0;
        }
        else {
            return true;
        }
    }
    public static boolean CanMoveRight(MazeStruct mazeStruct ){
        Tile tile = mazeStruct.maze.get(new Point(mazeStruct.player.x+1,mazeStruct.player.y));
        if (tile==null || tile.health==0){
            return false;
        }
        if(tile.locked){
            return mazeStruct.player.keys > 0;
        }
        else {
            return true;
        }
    }
    public static boolean CanMoveLeft(MazeStruct mazeStruct ){
        Tile tile = mazeStruct.maze.get(new Point(mazeStruct.player.x-1,mazeStruct.player.y));
        if (tile==null || tile.health==0){
            return false;
        }
        if(tile.locked){
            return mazeStruct.player.keys < 0;
        }
        else {
            return true;
        }
    }
    public static MazeStruct MoveUp(MazeStruct mazeStruct ){
        MazeStruct copiedMaze = new MazeStruct(mazeStruct);
        Tile next = copiedMaze.maze.get(new Point(copiedMaze.player.x,copiedMaze.player.y-1));
        Tile current = copiedMaze.maze.get(new Point(copiedMaze.player.x,copiedMaze.player.y));
        current.health--;
        if(next.locked && copiedMaze.player.keys>0){
            copiedMaze.player.keys--;
            next.unlock();
        }
        if(!next.locked){
            copiedMaze.player.y-=1;
        }
        if (next.hasKey){
            copiedMaze.player.keys++;
            next.hasKey=false;
        }
        return copiedMaze;
    }
    public static MazeStruct MoveDown(MazeStruct mazeStruct){
        MazeStruct copiedMaze = new MazeStruct(mazeStruct);
        Tile next = copiedMaze.maze.get(new Point(copiedMaze.player.x,copiedMaze.player.y+1));
        Tile current = copiedMaze.maze.get(new Point(copiedMaze.player.x,copiedMaze.player.y));
        current.health--;
        if(next.locked && copiedMaze.player.keys>0){
            copiedMaze.player.keys--;
            next.unlock();
        }
        if(!next.locked){
            copiedMaze.player.y+=1;
        }
        if (next.hasKey){
            copiedMaze.player.keys++;
            next.hasKey=false;
        }
        return copiedMaze;
    }
    public static MazeStruct MoveRight(MazeStruct mazeStruct ){
        MazeStruct copiedMaze = new MazeStruct(mazeStruct);
        Tile next = copiedMaze.maze.get(new Point(copiedMaze.player.x+1,copiedMaze.player.y));
        Tile current = copiedMaze.maze.get(new Point(copiedMaze.player.x,copiedMaze.player.y));
        current.health--;
        if(next.locked && copiedMaze.player.keys>0){
            copiedMaze.player.keys--;
            next.unlock();
        }
        if(!next.locked){
            copiedMaze.player.x+=1;
        }
        if (next.hasKey){
            copiedMaze.player.keys++;
            next.hasKey=false;
        }
        return copiedMaze;
    }
    public static MazeStruct MoveLeft(MazeStruct mazeStruct ){
        MazeStruct copiedMaze = new MazeStruct(mazeStruct);
        Tile next = copiedMaze.maze.get(new Point(copiedMaze.player.x-1,copiedMaze.player.y));
        Tile current = copiedMaze.maze.get(new Point(copiedMaze.player.x,copiedMaze.player.y));
        current.health--;
        if(next.locked && copiedMaze.player.keys>0) {
            copiedMaze.player.keys--;
            next.unlock();
        }
        if(!next.locked){
            copiedMaze.player.x-=1;
        }
        if (next.hasKey){
            copiedMaze.player.keys++;
            next.hasKey=false;
        }
        return copiedMaze;
    }
}


