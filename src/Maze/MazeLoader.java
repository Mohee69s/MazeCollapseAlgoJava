package Maze;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import Player.Player;

public class MazeLoader {

    public static MazeStruct loadFromFile(String path) throws IOException {
        HashMap<Point, Tile> maze = new HashMap<>();
        Player player = new Player();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int y = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                for (int x = 0; x < parts.length; x++) {
                    String token = parts[x].trim();
                    if (token.equals("X") || token.equals(".")) continue; // skip null tile

                    String[] fields = token.split(":");
                    if (fields.length < 3) continue; // malformed line

                    String type = fields[0];
                    int cost = Integer.parseInt(fields[1]);
                    int health = Integer.parseInt(fields[2]);

                    boolean end = type.equals("E");
                    boolean hasKey = type.equals("K");
                    boolean locked = type.equals("L");

                    Tile tile = new Tile(cost, health, end, hasKey, locked);
                    maze.put(new Point(x, y), tile);
                }
                y++;
            }

            int width = maze.keySet().stream().mapToInt(p -> p.x).max().orElse(0) + 1;
            return new MazeStruct(maze, y, width, player);
        }
    }
}
