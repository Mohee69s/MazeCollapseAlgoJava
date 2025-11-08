package Maze;

public class Tile {
    public int x;
    public int y;
    public int cost;
    public int health;
    public boolean end;
    public Tile(int x, int y, int cost, int health, boolean end) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.health = health;
        this.end = end;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;
        Tile p = (Tile) o;
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
