package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * @author yangdan
 */
public interface Sellable {


    int getSellingPrice();


    String sell(Actor actor, Actor trader);

}
