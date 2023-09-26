package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

public class Bloodberry extends Item implements Consumable{

  public Bloodberry() {
    super("Bloodberry", '*', true);
  }

  @Override
  public ActionList allowableActions(Actor owner) {

    return new ActionList( new ConsumeAction(this) );

  }
  @Override
  public String consume(Actor owner) {
    owner.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,5);
    owner.removeItemFromInventory(this);
    return owner + " consumes " + this + ", and it increases the maximum health by " + 5 + " points.";
  }
}
