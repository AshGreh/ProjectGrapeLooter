package node.ProjectGrapeLooter;

/**
 * Created by Genoss on 12/16/2016:4:42 PM
 */
import api.Node;
import db.Shared;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Interactive;

public class getOutsideOfGuild extends Node<ClientContext>{

    public getOutsideOfGuild(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void executeBlock() {
        boolean inMotion = ctx.players.local().inMotion();
        int floor = ctx.players.local().tile().floor();
        if(ctx.players.local().tile().equals(Shared.store().outside)) {
            Shared.store().out = true;
        } else if(!inMotion) {
            if(floor > 0)
                Shared.store().status = "Climbing down";
            switch(floor) {
                case 2:ctx.objects.select().id(Shared.store().stair1).poll().interact("Climb-down","Staircase");break;
                case 1:ctx.objects.select().id(Shared.store().stair2).poll().interact("Climb-down","Staircase");break;
                case 0:ctx.objects.select().id(Shared.store().door).each(Interactive.doSetBounds(Shared.store().doorBounds)).nearest().poll().interact("Open");
                    Shared.store().status = "Opening door";
                    break;
            }
        }
    }

    @Override
    public boolean isReady() {
        return ctx.inventory.select().count() == 28 && !Shared.store().out;
    }
}
