package node.ProjectGrapeLooter;

/**
 * Created by Genoss on 12/16/2016:4:28 PM
 */

import api.AntiBan;
import api.Node;
import db.Shared;
import org.powerbot.script.rt4.Interactive;
import org.powerbot.script.rt4.ClientContext;

public class LootGrape extends Node<ClientContext> {

    final int[] grapeBound = {-16, 16, -100, -84, -16, 16};//{-16, 16, -104, -88, -12, 16}
    AntiBan ab;

    public LootGrape(ClientContext ctx) {
        super(ctx);
        ab = new AntiBan(ctx);
    }

    @Override
    public void executeBlock() {
        if (ctx.groundItems.select().id(Shared.store().grapes).nearest().poll().inViewport()) {
            Shared.store().status = "Looting grape...";
            ctx.groundItems.select().id(Shared.store().grapes).each(Interactive.doSetBounds(grapeBound)).nearest().poll().interact("Take");
        } else {
            Shared.store().status = "AntiBan...";
            if(ab.isReady())
                ab.executeBlock();
        }
    }

    @Override
    public boolean isReady() {
        return ctx.players.local().tile().floor()==2 && ctx.inventory.select().count() < 28;
    }
}
