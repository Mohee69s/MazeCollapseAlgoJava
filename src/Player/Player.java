package Player;

import Maze.Tile;

public class Player {
    public int x;
    public int y;
    public int keys;
    public Player(int x,int y) {
        this.x = x;
        this.y = y;
    }
    public Player(){}
    public void AquireKey(Tile tile){
        this.keys+=1;
        tile.hasKey=false;
    }
    public void OpenLock(Tile tile){
        this.keys-=1;
        tile.unlock();
    }
}
