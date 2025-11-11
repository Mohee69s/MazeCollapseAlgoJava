package Maze;

public class Tile {
    public int cost;
    public int health;
    public boolean end;
    public boolean hasKey;
    public boolean locked;
    public Tile( int cost, int health, boolean end,boolean hasKey, boolean locked ) {
        this.cost = cost;
        this.health = health;
        this.end = end;
        this.hasKey = hasKey;
        this.locked = locked;
    }
    public void unlock(){
        this.locked = false;
        return;
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Tile)) return false;
//        Tile p = (Tile) o;
//        return x == p.x && y == p.y;
//
//    @Override
//    public int hashCode() {
//        return 31 * x + y;
//    }
}
