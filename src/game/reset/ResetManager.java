// declare package
package game.reset;

import java.util.ArrayList;

/**
 * A Reset Manager class that handles all entities that have to be reset
 * after player dies due to any causes
 *
 * Created by:
 * @author Koe Rui En
 */
public class ResetManager {

    /**
     * instance of ResetManager class
     */
    private static ResetManager instanceReset = null;

    // list of classes that need to be reset
    /**
     * a list of resettables
     */
    private ArrayList<Resettable> resettablesList;

    /**
     * A factory method that ensures only one instance of the ResetManager class can be created
     *
     * @return an instance of the ResetManager class
     */
    public static ResetManager getInstanceReset(){

        ResetManager reset = new ResetManager();

        if (instanceReset == null){

            instanceReset = reset;

        }

        return instanceReset;
    }

    /**
     * A private constructor to create a list containing resettables
     */
    private ResetManager() {

        this.resettablesList = new ArrayList<>();

    }

    /**
     * A method to add an actor item or a ground to the list of resettable
     *
     * @param resettable an actor or item that can be reset after player dies (trigger game reset) to be added
     */
    public void registerResettable(Resettable resettable) {

        resettablesList.add(resettable);

    }

    /**
     * A method to remove an actor, item or a ground from the list of resettable
     *
     * @param resettable an actor or item that can be reset after player dies (trigger game reset) to be removed
     */
    public void removeResettable(Resettable resettable) {

        resettablesList.remove(resettable);

    }


    // run reset manager
    /**
     * Run the reset manager when it is called.
     * It will trigger the reset method of all classes that implement resettable interface on the GameMap.
     */
    public void run() {

        for (Resettable resettable : resettablesList) {

            resettable.reset();

        }

    }






}
