/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * 10-26-20
 * Added look and eat into the enumuration
 * 
 * @author  Erick Rubio
 * @version 2020.10.26
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    // 8.14 + 8.15
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), LOOK("look"),
    EAT("eat");
    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
