package game.monologues;

/**
 * A Monologue Class representing a monologue text delivered by speaker
 *
 * Created by:
 * @author Yang Dan
 *
 */
public class Monologue {

    /**
     * Boolean value to check if the speaker can actually deliver this monologue
     */
    private boolean condition;

    /**
     * Content of the monologue
     */
    private String text;

    /**
     * Name of the speaker
     */
    private Speaker speaker;

    /**
     * Constructor of the Monologue class
     *
     * @param condition a boolean value to check if the speaker can actually deliver this monologue
     * @param text content of the monologue
     * @param speaker name of the speaker
     *
     */
    public Monologue(boolean condition, String text, Speaker speaker){
        this.condition = condition;
        this.text = text;
        this.speaker = speaker;
    }

    /**
     * Getter for the attribute 'condition'
     *
     * @return bool value to check if the speaker can actually deliver this monologue
     */
    public boolean getCondition(){
        return this.condition;
    }

    /**
     * Override toString() method from Object class to print the monologue text
     *
     * When the monologue object is printed, it follows the following format
     *
     * @return a string with two information, speaker name and the content of the monologue
     */
    @Override
    public String toString() {
        return speaker + ": " + text;
    }
}
