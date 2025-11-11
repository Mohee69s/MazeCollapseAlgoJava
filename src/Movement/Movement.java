package Movement;

import Maze.MazeStruct;
import Maze.Tile;
import Player.Player;

import java.awt.*;
import java.util.HashMap;

public class Movement {
    public static boolean CanMoveUp(HashMap<Point,Tile> maze,Player player ){
        Tile tile = maze.get(new Point(player.x,player.y+1));
        if (tile==null  || tile.health==0){
            return false;
        }
        if(tile.locked){
            return player.keys > 0;
        }
        else {
            return true;
        }
    }
    public static boolean CanMoveDown(HashMap<Point,Tile> maze,Player player ){
        Tile tile = maze.get(new Point(player.x,player.y-1));
        if (tile==null || tile.health==0){
            return false;
        }
        if(tile.locked){
            return player.keys < 0;
        }
        else {
            return true;
        }
    }
    public static boolean CanMoveRight(HashMap<Point,Tile> maze,Player player ){
        Tile tile = maze.get(new Point(player.x+1,player.y));
        if (tile==null || tile.health==0){
            return false;
        }
        if(tile.locked){
            return player.keys > 0;
        }
        else {
            return true;
        }
    }
    public static boolean CanMoveLeft(HashMap<Point,Tile> maze,Player player ){
        Tile tile = maze.get(new Point(player.x,player.y+1));
        if (tile==null || tile.health==0){
            return false;
        }
        if(tile.locked){
            return player.keys < 0;
        }
        else {
            return true;
        }
    }
    public static MazeStruct MoveUp(HashMap<Point,Tile> maze, Player player ){
        Tile next = maze.get(new Point(player.x,player.y+1));
        Tile current = maze.get(new Point(player.x,player.y));
        current.health--;
        if(next.locked && player.keys>0){
            player.keys--;
            next.unlock();
            player.y+=1;
        }
        if(!next.locked){
            player.y+=1;
        }
        if (next.hasKey){
            player.keys++;
        }
        return new MazeStruct(maze,player);
    }
    public static MazeStruct MoveDown(HashMap<Point,Tile> maze, Player player ){
        Tile next = maze.get(new Point(player.x,player.y-1));
        Tile current = maze.get(new Point(player.x,player.y));
        current.health++;
        if(next.locked && player.keys>0){
            player.keys--;
            next.unlock();
            player.y-=1;
        }
        if(!next.locked){
            player.y-=1;
        }
        if (next.hasKey){
            player.keys++;
        }
        return new MazeStruct(maze,player);
    }
    public static MazeStruct MoveRight(HashMap<Point,Tile> maze, Player player ){
        Tile next = maze.get(new Point(player.x+1,player.y));
        Tile current = maze.get(new Point(player.x,player.y));
        current.health++;
        if(next.locked && player.keys>0){
            player.keys--;
            next.unlock();
            player.x+=1;
        }
        if(!next.locked){
            player.x+=1;
        }
        if (next.hasKey){
            player.keys++;
        }
        return new MazeStruct(maze,player);
    }
    public static MazeStruct MoveLeft(HashMap<Point,Tile> maze, Player player ){
        Tile next = maze.get(new Point(player.x-1,player.y));
        Tile current = maze.get(new Point(player.x,player.y));
        current.health--;
        if(next.locked && player.keys>0) {
            player.keys--;
            next.unlock();
            player.x-=1;
        }
        if(!next.locked){
            player.x-=1;
        }
        if (next.hasKey){
            player.keys++;
        }
        return new MazeStruct(maze,player);
    }
}


