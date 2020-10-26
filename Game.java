/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * 10-26-20
 * Added look and eat into the process command. 
 * Coupling with the CommandWord class.
 * Added look(), eat() methods.
 * 
 * @author  Erick Rubio
 * @version 2020.10.26
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, bar, garden, gym, court, bathroom,
        store1, store2, store3,  
        office1, office2, office3, 
        resturant1, resturant2, resturant3;
      
        // create the rooms (16 for a 4x4 grid)
        outside = new Room("outside the main entrance of the mall");
        theater = new Room("in a movie theater");
        bar     = new Room("in the mall bar");
        gym     = new Room("in the gym");
        garden  = new Room("in the mall garden area");
        court   = new Room("in the mall food court");
        bathroom= new Room("in the unisex bathroom");
        
        store1  = new Room("in the mall clothing store");
        store2  = new Room("in a toy store");
        store3  = new Room("in candle store");

        office1 = new Room("in a computing lab");
        office2 = new Room("in the computing admin office");
        office3 = new Room("mall security office");
        
        resturant1 = new Room("in a Chinese food resturant");
        resturant1 = new Room("in a burger joint");
        resturant1 = new Room("in a fancy dining hall");     
        
        // initialise room exits
        //Grid:1,1-1,4
        outside.setExit("east", theater);
        outside.setExit("south", garden);
        
        garden.setExit("east", office3);
        garden.setExit("south",bar);
        garden.setExit("north",outside);
        
        bar.setExit("east", store1);
        bar.setExit("south",store3);
        bar.setExit("north",garden);
        
        store3.setExit("east", gym);
        bar.setExit("north",bar);        
        
        //Grid 2,1 - 2,4
        theater.setExit("west", outside);

        // pub.setExit("east", outside);

        // office1.setExit("north", outside);
        // office2.setExit("east", office);

        // office.setExit("west", lab);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     * 
     * 8.14 + 8.15
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                look();
                break;

            case EAT:
                eat();
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        //8.16 print commands using parser
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    /**
     * Look around a room
     * 8.14
       */
    private void look(){
        System.out.println(currentRoom.getLongDescription());
    }
    /**
     * Eat something
     * 8.15
       */
    private void eat(){
        System.out.println("You have eaten now and are not hungry any more");
    }    
}
