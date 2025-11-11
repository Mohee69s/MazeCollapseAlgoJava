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
                    sb.append("P ");
                } else if (tile == null || tile.health==0) {
                    sb.append("X ");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MazeStruct)) return false;
        MazeStruct other = (MazeStruct) o;
        return height == other.height &&
                width == other.width &&
                player.x == other.player.x &&
                player.y == other.player.y &&
                java.util.Objects.equals(maze, other.maze);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(maze, height, width, player.x, player.y);
    }

    public java.util.List<MazeStruct> generateNextStates() {
        java.util.List<MazeStruct> nextStates = new java.util.ArrayList<>();
        int[][] directions = {
                {0, -1},
                {0, 1},
                {-1, 0},
                {1, 0}
        };

        for (int[] dir : directions) {
            int newX = player.x + dir[0];
            int newY = player.y + dir[1];
            Point newPoint = new Point(newX, newY);
            Tile target = maze.get(newPoint);

            if (target != null && target.health > 0 && !target.locked) {

                HashMap<Point, Tile> mazeCopy = new HashMap<>();
                for (java.util.Map.Entry<Point, Tile> entry : maze.entrySet()) {
                    Tile t = entry.getValue();
                    mazeCopy.put(entry.getKey(), new Tile(t.cost, t.health, t.end, t.hasKey, t.locked));
                }

                Player newPlayer = new Player(newX, newY);
                newPlayer.keys = player.keys;


                Tile movedTile = mazeCopy.get(newPoint);
                if (movedTile.hasKey) {
                    movedTile.hasKey = false;
                    newPlayer.keys += 1;
                }

                movedTile.health = Math.max(0, movedTile.health - 1);

                MazeStruct nextState = new MazeStruct(mazeCopy, height, width, newPlayer);
                nextStates.add(nextState);
            }
        }
        return nextStates;
    }


}
