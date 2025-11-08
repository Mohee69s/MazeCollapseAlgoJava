package Movement;

import Maze.Tile;
import Player.Player;
import java.util.HashSet;

public class Movement {
    public static boolean CanMoveUp(HashSet<Tile> maze,Player player){
        for (Tile tile : maze){
            if (tile.y == player.y+1){
                return true;
            }
        }
        return false;
    }
    public static boolean CanMoveDown(HashSet<Tile> maze,Player player){
        for (Tile tile : maze){
            if (tile.y == player.y-1){
                return true;
            }
        }
        return false;
    }
    public static boolean CanMoveLeft(HashSet<Tile> maze,Player player){
        for (Tile tile : maze){
            if (tile.x == player.x-1){
                return true;
            }
        }
        return false;
    }
    public static boolean CanMoveRight(HashSet<Tile> maze,Player player){
        for (Tile tile : maze){
            if (tile.x == player.x+1){
                return true;
            }
        }
        return false;
    }
    public static void MoveUp(HashSet<Tile> maze,Player player){
        for (Tile tile : maze){

        }
    }
}
