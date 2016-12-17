package db;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Npc;

/**
 * Created by Zack on 12/14/2016.
 */
public class Shared {

    protected static Shared instance = null;

    public String status = "Status...";
    public Boolean out= false;
    public Boolean inventFull = false;
    public GameObject gobject = null;
    public Npc gnpc = null;
    public int[] doorBounds = {20, 116, -228, 0, 100, 120};
    public int stair1 = 2610,stair2 = 2609,door = 24958;
    public int grapes = 1987;
    public Tile outside = new Tile(3143,3443,0);
    public int banker[] = {2897,2898};;
    public int bankCount = 0;
    public Boolean lock = false;

    public static final Tile[] PATH = {
            new Tile(3143,3443,0),
            new Tile(3153,3447,0),
            new Tile(3160,3450,0),
            new Tile(3166,3454,0),
            new Tile(3173,3454,0),
            new Tile(3180,3451,0),
            new Tile(3183,3447,0),
            new Tile(3185,3444,0)
    };

    public static Shared store() {
        if(instance==null) {
            instance = new Shared();
        }
        return instance;
    }
}
