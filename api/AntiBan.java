package api;

import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

import java.awt.*;
import java.util.logging.Logger;

/**
 * Created by Zack on 12/15/2016.
 */
public class AntiBan extends Node<ClientContext> {

    int randInt = -1,min = 1,max = 24,frandInt = Random.nextInt(min,max);
    Logger log = Logger.getLogger("[AntiBan]");

    public AntiBan(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void executeBlock() {
        log.warning("Started");
        randInt = Random.nextInt(min,max);
        if(randInt > 6)
            randInt/=4;
        switch(randInt) {
            case 1:log.info("Random 1");
                   ctx.input.move(Random.nextInt(7,514),Random.nextInt(7,334));
                   break;
            case 2:log.info("Random 2");
                   ctx.input.move(0,Random.nextInt(0,503));
                   break;
            case 3:log.info("Random 3");
                   ctx.input.move(Random.nextInt(0,767),0);
                   break;
            case 4:log.info("Random 4");
                   ctx.input.move(767,Random.nextInt(0,503));
                   break;
            case 5:log.info("Random 5");
                   ctx.input.move(Random.nextInt(0,767),503);
                   break;
            default:log.info("Random 6");
                    ctx.input.move(Random.nextInt(7,514),Random.nextInt(7,334));
                    break;
        }
    }

    @Override
    public boolean isReady() {
        randInt = Random.nextInt(min,max);
        return randInt == frandInt;
    }
}
