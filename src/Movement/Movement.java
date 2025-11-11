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
    public static MazeStruct MoveUp(MazeStruct mazeStruct ){
        Tile next = mazeStruct.maze.get(new Point(mazeStruct.player.x,mazeStruct.player.y-1));
        Tile current = mazeStruct.maze.get(new Point(mazeStruct.player.x,mazeStruct.player.y));
        current.health--;
        if(next.locked && mazeStruct.player.keys>0){
            mazeStruct.player.keys--;
            next.unlock();
        }
        if(!next.locked){
            mazeStruct.player.y-=1;
        }
        if (next.hasKey){
            mazeStruct.player.keys++;
            next.hasKey=false;
        }
        return new MazeStruct(mazeStruct.maze,mazeStruct.height, mazeStruct.width, mazeStruct.player);
    }
    public static MazeStruct MoveDown(MazeStruct mazeStruct){
        Tile next = mazeStruct.maze.get(new Point(mazeStruct.player.x,mazeStruct.player.y+1));
        Tile current = mazeStruct.maze.get(new Point(mazeStruct.player.x,mazeStruct.player.y));
        current.health--;
        if(next.locked && mazeStruct.player.keys>0){
            mazeStruct.player.keys--;
            next.unlock();
        }
        if(!next.locked){
            mazeStruct.player.y+=1;
        }
        if (next.hasKey){
            mazeStruct.player.keys++;
            next.hasKey=false;
        }
        return new MazeStruct(mazeStruct.maze,mazeStruct.height,mazeStruct.width,mazeStruct.player);
    }
    public static MazeStruct MoveRight(MazeStruct mazeStruct ){
        Tile next = mazeStruct.maze.get(new Point(mazeStruct.player.x+1,mazeStruct.player.y));
        Tile current = mazeStruct.maze.get(new Point(mazeStruct.player.x,mazeStruct.player.y));
        current.health--;
        if(next.locked && mazeStruct.player.keys>0){
            mazeStruct.player.keys--;
            next.unlock();
        }
        if(!next.locked){
            mazeStruct.player.x+=1;
        }
        if (next.hasKey){
            mazeStruct.player.keys++;
            next.hasKey=false;
        }
        return new MazeStruct(mazeStruct.maze,mazeStruct.height,mazeStruct.width,mazeStruct.player);
    }
    public static MazeStruct MoveLeft(MazeStruct mazeStruct ){
        Tile next = mazeStruct.maze.get(new Point(mazeStruct.player.x-1,mazeStruct.player.y));
        Tile current = mazeStruct.maze.get(new Point(mazeStruct.player.x,mazeStruct.player.y));
        current.health--;
        if(next.locked && mazeStruct.player.keys>0) {
            mazeStruct.player.keys--;
            next.unlock();
        }
        if(!next.locked){
            mazeStruct.player.x-=1;
        }
        if (next.hasKey){
            mazeStruct.player.keys++;
            next.hasKey=false;
        }
        return new MazeStruct(mazeStruct.maze,mazeStruct.height,mazeStruct.width,mazeStruct.player);
    }
}


