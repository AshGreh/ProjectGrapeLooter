package node.ProjectGrapeLooter;

/**
 * Created by Genoss on 12/16/2016:4:42 PM
 */
import api.Node;
import db.Shared;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class getInsideOfGuild extends Node<ClientContext>{

    private int stair0 = 2608;
    private Tile outside = new Tile(3143,3443,0);

    public getInsideOfGuild(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void executeBlock() {
        boolean inMotion = ctx.players.local().inMotion();
        int floor = ctx.players.local().tile().floor();
        if(ctx.players.local().tile().equals(outside)) {
            Shared.store().out = true;
        } else if(!inMotion) {
            Shared.store().status = "Climbing staircase";
            switch(floor) {
                case 2:ctx.objects.select().id(Shared.store().stair1).poll().interact("Climb-up","Staircase");break;
                case 1:ctx.objects.select().id(Shared.store().stair2).poll().interact("Climb-up","Staircase");break;
                case 0:ctx.objects.select().id(stair0).poll().interact("Climb-up","Staircase");break;
            }
        }
    }

    @Override
    public boolean isReady() {
        return ctx.inventory.select().count() == 0 && !Shared.store().out && ctx.players.local().tile().floor()!=2;
    }
}
