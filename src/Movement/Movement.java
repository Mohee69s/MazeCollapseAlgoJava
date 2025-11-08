package Movement;

import Maze.Tile;
import Player.Player;
import java.util.HashSet;

public class Movement {
    public static void CanMoveUp(HashSet<Tile> maze,Player player){
        Tile current;
        for (Tile tile : maze){
            if (tile.x == player.x && tile.y == player.y){
                current = tile;
            }
        }

    }
}
