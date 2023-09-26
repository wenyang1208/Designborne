package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

public class Rune extends Item implements Consumable{
  private int value;

  public Rune(int value) {
    super("Runes", '$', true);
    this.value = value;
  }

  @Override
  public ActionList allowableActions(Actor owner) {
    return new ActionList( new ConsumeAction(this) );
  }
  @Override
  public String consume(Actor owner) {
    owner.addBalance(this.value);
    owner.removeItemFromInventory(this);
    return  owner + " consumes " + this;
  }

}
