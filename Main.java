import java.util.Scanner; //import for user input

public class Main {
    public static void main(String[] args) {

        //initializing game world
        Room startRoom = createGameWorld();
        Player player = new Player(startRoom);
        Scanner scanner = new Scanner(System.in);

        //to display game title
        System.out.println(" ");
        System.out.println("--------ESCAPE OF KINGDOM VELMORIA--------");
        System.out.println("");

        //creating Lunara the NPC and adding her to the startign room
        NPC lunara=new NPC("Lunara", "A nervous servant girl holding a frying pan");
        startRoom.addNPC(lunara);
        lunara.talk(); //lunara speaks(the conversation between her and the player)
        printHelp(); //showing player all their different commands

        //prints the information on the start room
        System.out.println();
        System.out.println(startRoom.getFullInfo());
        System.out.println(" ");

        //main game loop /runs while the player is alive in the game
        while(player.getIsAlive()){
            System.out.print("\n> ");
            String input = scanner.nextLine().toLowerCase();//lowercases input

            //all user commands and what happens if the user types it
            if (input.startsWith("go ")) {
                //moves player to a certain direction, to another room
                player.move(input.substring(3));
            }
            else if (input.startsWith("take ")) {
                //player tries to take an item
                player.takeItem(input.substring(5));
            }
            else if (input.equals("inventory")) {
                //show sthe player's inventory, what items they have so far
                player.showInventory();
            }
            else if (input.equals("help")) {
                //prints out help menu/commands
                printHelp();
            }
            else if (input.equals("quit")) {
                //ends the game
                System.out.println("Goodbye!");
                break;
            }
            else if (input.equals("clown")) {
                //player does the conversation/challenge with the clown
                player.talkToClown();
            }
            else if (input.equals("open")) {
                //player tries to open the chest
                player.openChest();
            }
            else if (input.equals("solve")) {
                //player tries to solve the world scramble to get diamond
                player.solveScramble();
            }
            else if(input.equals("witch")){
                //player talks to the witch
                player.talkToWitch();
            }
            else {
                //player types anythign else weird, it says its an invalid method
                System.out.println("Invalid command. Type 'help' for options.");
            }
        }


    }

    //method made to create allt he rooms and create the game envirment
    public static Room createGameWorld(){
        //creating all 9 rooms and making their description, plus puzzles if they have any(NEED TO WORK ON PUZZLES LATER)
        Room witchRoom = new Room("The Chamber of the Witch Nyxeria",false,false,true,false,false,false,false,false);
        witchRoom.setRoomInfo("Welcome to the hidden chamber of the Witch Nyxeira. Nyxeira hates this place and is willing to help you escape. You must have the wand, spellbook and purple diamond to escape. ");

        Room queenRoom = new Room("THE QUEEN'S ROOM",true,false,false,false,false,false,false,false);
        queenRoom.setRoomInfo("Welcome to the room of the Evil,DANGEROUS,POWER,ANGRY QUEEN OF VELMORIA: QUEEN VESPARA");

        Room clownRoom = new Room("CIRCUS OF GRINSORROW THE CLOWN",false,true,false,false,true,false,false,false);
        clownRoom.setRoomInfo("Welcome to Abandoned Circus of GrinSorrow the Clown. \n Grinsorrow is hungry for souls and will challenge you to a riddle for the spellbook.\n Howveer Grinsorrow has the spellbook, whcih is what you need to escape\n you must talk to Grinsorrow to retrieve the spellbook\n write 'clown' to talk to Grinsorrow\n if not go to a different room");
        clownRoom.addPuzzle("She rules the halls with silent dread,Speak her name, and you may be dead.A name that whispers fear and night,Six letters long, a shadowed fright.Who is she?","vespara");

        Room wandRoom = new Room("The Treasure Room",false,false,false,true,false,false,false,false);
        wandRoom.setRoomInfo("This is the room full of the Kingdom's stolen glory. A massive chest sits in the center of the room.\n" + "The chest is locked with a magical seal that requires a special blue key to open.\n" + "You must find the blue key first before you can open this chest and get the wand!\n" + "Use the 'open' command when you have the blue key.");

        Room hintForClownRoom = new Room("The Royal Bathroom",false,false,false,false,false,false,false,false);
        hintForClownRoom.setRoomInfo("Here is the Royal Bathroom of the Kingdom. Fillled with 100 restrooms and toilet. Only used by one person: Vespara\n YOU SEE SOEMTHING WRITTEN N THE MIRROR\n \n At one point, you will meet Grinsorrow the clown. Let me give you a hint to the riddle he will ask you\n    SHE IS THE QUEEN. SHE RULES THE KINGDOM. WHATS HER NAME \n   \n now that yu have the hint, run keep going ");

        Room pdRoom = new Room("The room of diamonds", false,false,false,false,false,true,false,false);
        pdRoom.setRoomInfo("Vespara the queen is obsessed with diamonds. To get the purple diamond, you must solve the word scramble.");

        Room hint4PD = new Room("Closet of Queen Vespara",false,false,false,false,false,false,false,false);
        hint4PD.setRoomInfo("You walk into the room and see stacks an stacks of dresses on top of each othe. All the dresses are fancy , dark and glowy. \n You look down and see a note on the ground. You pick up the note a read it\n   \n  To get the purple diamond, you must solve the unscramble the word. \nHint: Whats the name of the clown.\n \nYou see this hint, and decide to keep going a there is nothing in the room to take");

        Room startRoom = new Room("Start Room", false, false, false, false, false, false, false,false);
        startRoom.setRoomInfo(" Welcome to the Start Room! The Beginning of the Dangerous Journey that you will hopefully but probably not survive and come out alive out of. \n Nothing much here really, except for a cute servant holding a frying pan that you can talk to. JUST DON'T GET HER MAD!! \n GO RUN AND ESCAPE NOW, OFC AFTER YOU TALK WITH HER :) ");

        Room hint4Wand = new Room("The Kitchen of Pain",false,false,false,false,false,false,true,true);
        hint4Wand.setRoomInfo("Welcome to the Kitchen of Pain...\n" + "You find two distinct keys on a ring:\n" + "- A SHINY BLUE key that glows faintly\n" + "- A DULL PURPLE key with strange markings\n" + "A note says: 'The blue key opens the treasure'");

        //connecting the rooms together
        witchRoom.setExits(null,wandRoom,queenRoom,null);
        queenRoom.setExits(null,hintForClownRoom,clownRoom,witchRoom);
        clownRoom.setExits(null,pdRoom,null,queenRoom);
        wandRoom.setExits(witchRoom,hint4PD,hintForClownRoom,null);
        hintForClownRoom.setExits(queenRoom,startRoom,pdRoom,wandRoom);
        pdRoom.setExits(clownRoom,hint4Wand,null,hintForClownRoom);
        hint4PD.setExits(wandRoom,null,startRoom,null);
        startRoom.setExits(hintForClownRoom,null,hint4Wand,hint4PD);
        hint4Wand.setExits(pdRoom,null,null,startRoom);

        return startRoom;//returning starting room
    }

    //printing out the commands player uses to play the game
    public static void printHelp() {
        System.out.println("Commands: ");
        System.out.println("go [direction] - Move north/south/east/west");
        System.out.println("take [item] - Pick up an item (wand/spellbook/diamond/keys)");
        System.out.println("open - Attempt to open the wand chest (requires blue key)");
        System.out.println("talk - Speak to a character");
        System.out.println("clown - Interact with Grinsorrow the Clown");
        System.out.println("solve - Attempt to solve the word scramble");
        System.out.println("witch - Talk to Witch Nyxeira to escape (when you have all items)");
        System.out.println("inventory - View your items");
        System.out.println("help - Show this help");
        System.out.println("quit - Exit the game");

    }
}
