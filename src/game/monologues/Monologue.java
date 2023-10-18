package game.monologues;

/**
 * @author yangdan
 *
 * Class representing a monologue
 */
public class Monologue {

    /**
     * Bool value to check if the speaker can actually deliver this monologue
     */
    private boolean condition;

    /**
     * Content of the monologue
     */
    private String text;

    /**
     * Name of the speaker
     */
    private String speaker;

    /**
     * Constructor of the Monologue object
     */
    public Monologue(boolean condition, String text, String speaker){
        this.condition = condition;
        this.text = text;
        this.speaker = speaker;
    }

    /**
     * Getter for the attribute 'condition'
     * @return bool value to check if the speaker can actually deliver this monologue
     */
    public boolean getCondition(){
        return this.condition;
    }

    /**
     * When the monologue object is printed, it follows the following format
     * @return a string with two information, speaker name and the content of the monologue
     */
    @Override
    public String toString() {
        return speaker + ": " + text;
    }
}
