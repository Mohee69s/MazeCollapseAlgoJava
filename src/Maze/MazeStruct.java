package Maze;

import Player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import Movement.Movement;
import java.util.Objects;
import static java.util.Objects.hash;

public class MazeStruct {
    public HashMap<Point,Tile> maze;
    public int height;
    public int width;
    public Player player;
    public MazeStruct parent;
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

    public MazeStruct(MazeStruct original) {
        this.maze=original.maze;
        this.height=original.height;
        this.width=original.width;
        this.player=original.player;
        this.parent=original;
    }

    public MazeStruct() {

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
        return hash(maze, height, width, player.x, player.y);
    }

    public List<MazeStruct> generateNextStates() {
        List<MazeStruct> nextStates = new ArrayList<>();
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
            if (target == null || target.health == 0) continue;
            boolean canMove = false;
            if (!target.locked) {
                canMove = true;
            } else if (player.keys > 0) {
                canMove = true;
            }

            if (!canMove) continue;
            HashMap<Point, Tile> mazeCopy = new HashMap<>();
            for (Map.Entry<Point, Tile> entry : maze.entrySet()) {
                Tile t = entry.getValue();
                mazeCopy.put(entry.getKey(), new Tile(t.cost, t.health, t.end, t.hasKey, t.locked));
            }
            Player newPlayer = new Player(player.x, player.y);
            newPlayer.keys = player.keys;
            MazeStruct tempMaze = new MazeStruct(mazeCopy, height, width, newPlayer);
            MazeStruct nextState = null;
            if (dir[0] == 0 && dir[1] == -1) nextState = Movement.MoveUp(tempMaze);
            else if (dir[0] == 0 && dir[1] == 1) nextState = Movement.MoveDown(tempMaze);
            else if (dir[0] == -1 && dir[1] == 0) nextState = Movement.MoveLeft(tempMaze);
            else if (dir[0] == 1 && dir[1] == 0) nextState = Movement.MoveRight(tempMaze);

            if (nextState != null) nextStates.add(nextState);
        }

        return nextStates;
    }


}
