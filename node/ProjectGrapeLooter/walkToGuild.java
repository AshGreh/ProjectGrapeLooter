package node.ProjectGrapeLooter;

import api.Node;
import db.Shared;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Interactive;
import org.powerbot.script.rt4.TilePath;

/**
 * Created by Genoss on 12/16/2016:9:23 PM
 */
public class walkToGuild extends Node<ClientContext> {

    private Tile inside = new Tile(3143,3444,0);

    private int banker[] = {2897,2898};
    private int doorBounds[] = {20, 116, -228, 0, 100, 120};

    private TilePath pathToBank = ctx.movement.newTilePath(Shared.store().PATH).reverse();

    public walkToGuild(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void executeBlock() {
        if(Shared.store().lock) {
            Shared.store().bankCount+=1;
            Shared.store().lock = false;
        }
        if(ctx.players.local().tile().distanceTo(Shared.store().outside) > 3) {
            Shared.store().status = "Walking to guild";
            pathToBank.traverse();
        } else if(ctx.players.local().tile().equals(inside)) {
            Shared.store().out = false;
        } else if(!ctx.objects.select().id(banker).poll().inViewport() && Shared.store().out) {
            Shared.store().status = "Opening door";
            ctx.objects.select().id(Shared.store().door).each(Interactive.doSetBounds(doorBounds)).nearest().poll().interact("Open");
        }
    }

    @Override
    public boolean isReady() {
        return ctx.inventory.select().count() == 0 && Shared.store().out;
    }
}