package node.ProjectGrapeLooter;

import api.Node;
import db.Shared;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

/**
 * Created by Genoss on 12/16/2016:8:12 PM
 */
public class walkToBank extends Node<ClientContext>{

    final Tile bankTile= new Tile(3185,3444,0);

    private TilePath pathToBank = ctx.movement.newTilePath(Shared.store().PATH);

    public walkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void executeBlock() {
        if(ctx.players.local().tile().distanceTo(bankTile) > 10) {
            Shared.store().status = "Moving to bank";
            pathToBank.traverse();
        } else if(!ctx.npcs.select().nearest().id(Shared.store().banker).poll().inViewport()) {
            Shared.store().status = "Turning to banker";
            ctx.camera.turnTo(ctx.npcs.select().nearest().id(Shared.store().banker).poll());
        } else if(ctx.bank.opened()) {
            Shared.store().status = "Depositing inventory";
            ctx.bank.depositInventory();
        } else {
            Shared.store().status = "Opening bank";
            ctx.bank.open();
            Shared.store().lock = true;
        }
    }

    @Override
    public boolean isReady() {
        return ctx.inventory.count() == 28 && Shared.store().out;
    }
}
