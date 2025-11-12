package Maze;

import static java.util.Objects.hash;

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
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;
        Tile t = (Tile) o;
        return cost == t.cost &&
                health == t.health &&
                end == t.end &&
                hasKey == t.hasKey &&
                locked == t.locked;
    }

    @Override
    public int hashCode() {
        return hash(cost, health, end, hasKey, locked);
    }

}
