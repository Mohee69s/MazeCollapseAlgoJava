package Maze;

import Player.Player;

import java.awt.*;
import java.util.HashMap;

public class MazeStruct {
    public HashMap<Point,Tile> maze;
    public int height;
    public int width;
    public Player player;
    public MazeStruct(HashMap<Point,Tile> maze, int height, int width ,Player player) {
        this.maze=maze;
        this.height=height;
        this.width=width;
        this.player=player;
    }
    public MazeStruct(HashMap<Point,Tile> maze,Player player) {
        this.maze=maze;
        this.player=player;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point p = new Point(x, y);
                Tile tile = maze.get(p);

                if (player.x == x && player.y == y) {
                    sb.append("P "); // player
                } else if (tile == null || tile.health==0) {
                    sb.append("X "); // void / empty
                } else if (tile.end) {
                    sb.append("E ");
                } else if (tile.hasKey) {
                    sb.append("K ");
                } else if (tile.locked) {
                    sb.append("L ");
                } else {
                    sb.append("N ");
                }
            }
            sb.append("\n");
        }

        sb.append("\nPlayer keys: ").append(player.keys);
        return sb.toString();
    }

    public Boolean CheckWinCondition() {
        Boolean winCondition = null;
        Point p = new Point(player.x,player.y);
        Tile tile = maze.get(p);
        if (tile.end) {
            for(Tile t: maze.values()){
                if(t.end){
                    continue;
                } else if (t.health>0) {
                    winCondition = false;
                    return winCondition;
                }
            }
            winCondition = true;
            return winCondition;
        }
        return winCondition;
    }
}
