package Maze;

import Player.Player;

import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;

import Movement.Movement;


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
        if (o == null || getClass() != o.getClass()) return false;

        return deepEquals(this, o, new IdentityHashMap<>());
    }
    private static boolean deepEquals(Object a, Object b, IdentityHashMap<Object, Boolean> visited) {
        if (a == b) return true;
        if (a == null || b == null) return false;

        // Prevent cycles
        if (visited.containsKey(a) && visited.containsKey(b)) {
            return true;
        }
        visited.put(a, true);
        visited.put(b, true);

        Class<?> clsA = a.getClass();
        Class<?> clsB = b.getClass();
        if (!clsA.equals(clsB)) return false;

        // ---- Primitive wrappers / Strings / enums ----
        if (clsA.isPrimitive()
                || a instanceof String
                || a instanceof Number
                || a instanceof Boolean
                || a instanceof Character
                || a instanceof Enum<?>) {
            return a.equals(b);
        }

        // ---- Arrays ----
        if (clsA.isArray()) {
            int lenA = Array.getLength(a);
            int lenB = Array.getLength(b);
            if (lenA != lenB) return false;
            for (int i = 0; i < lenA; i++) {
                if (!deepEquals(Array.get(a, i), Array.get(b, i), visited)) {
                    return false;
                }
            }
            return true;
        }

        // ---- Iterable (List, Set, etc.) ----
        if (a instanceof Iterable<?> itA && b instanceof Iterable<?> itB) {
            var iterA = itA.iterator();
            var iterB = itB.iterator();
            while (iterA.hasNext() && iterB.hasNext()) {
                if (!deepEquals(iterA.next(), iterB.next(), visited)) {
                    return false;
                }
            }
            return !iterA.hasNext() && !iterB.hasNext();
        }

        // ---- Map ----
        if (a instanceof Map<?, ?> mapA && b instanceof Map<?, ?> mapB) {
            if (mapA.size() != mapB.size()) return false;

            for (var entryA : mapA.entrySet()) {
                Object keyA = entryA.getKey();
                Object valA = entryA.getValue();

                // Find matching entry in B
                boolean found = false;
                for (var entryB : mapB.entrySet()) {
                    if (deepEquals(keyA, entryB.getKey(), visited)) {
                        if (!deepEquals(valA, entryB.getValue(), visited)) {
                            return false;
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) return false;
            }
            return true;
        }

        // ---- Custom objects (deep compare all fields) ----
        for (Field f : clsA.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                Object valA = f.get(a);
                Object valB = f.get(b);
                if (!deepEquals(valA, valB, visited)) {
                    return false;
                }
            } catch (Exception ignored) {}
        }
        return true;
    }


    @Override
    public int hashCode() {
        return deepHash(this, new IdentityHashMap<>());
    }


    public HashSet<MazeStruct> generateNextStates() {
        HashSet<MazeStruct> nextStates = new HashSet<>();
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
    private static int deepHash(Object obj, Map<Object, Boolean> visited) {
        if (obj == null) return 0;

        // Avoid infinite recursion (cycles in parent/child graphs)
        if (visited.containsKey(obj)) return 0;
        visited.put(obj, true);

        Class<?> cls = obj.getClass();

        // ---- Primitive wrappers, String, enums ----
        if (cls.isPrimitive()
                || obj instanceof String
                || obj instanceof Number
                || obj instanceof Boolean
                || obj instanceof Character
                || obj instanceof Enum<?>) {
            return obj.hashCode();
        }

        // ---- Arrays ----
        if (cls.isArray()) {
            int len = Array.getLength(obj);
            int h = 1;
            for (int i = 0; i < len; i++)
                h = 31 * h + deepHash(Array.get(obj, i), visited);
            return h;
        }

        // ---- Collection ----
        if (obj instanceof Iterable<?> it) {
            int h = 1;
            for (Object o : it)
                h = 31 * h + deepHash(o, visited);
            return h;
        }

        // ---- Map ----
        if (obj instanceof Map<?, ?> map) {
            int h = 1;
            for (var entry : map.entrySet()) {
                h = 31 * h + deepHash(entry.getKey(), visited);
                h = 31 * h + deepHash(entry.getValue(), visited);
            }
            return h;
        }

        // ---- Custom object: hash all fields ----
        int h = 1;
        for (Field f : cls.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                h = 31 * h + deepHash(f.get(obj), visited);
            } catch (Exception ignored) {}
        }
        return h;
    }


}
