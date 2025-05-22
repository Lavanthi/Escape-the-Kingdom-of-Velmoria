import java.util.Scanner;//importing for user input
/*
This class was made for Lunara. Lunara is an NPC servant.
She provides the important game instructions and talk with the player.
 */
public class NPC {

    //instance variables for Lunara
    private String name;
    private String description;
    private boolean hasAskedName;
    private String playerName;

    //constructor
    public NPC(String name,String description){
        this.name=name;
        this.description=description;
        hasAskedName=false;
        playerName="";
    }

    //the method for Lunara to talk to the user
    public void talk(){
        Scanner scanner = new Scanner(System.in);//creates a scanner

        //if the player has not said their name yet
        if(!hasAskedName){
            System.out.println("Cute Servant: Hey, who are you? What are-.Anyways, my name is Lunara.\n I was forced to work here as a servant. Wait, whats your name:  ");

            //user's responce to what their name is
            playerName=scanner.nextLine();
            hasAskedName=true;

            //lunara warns about the kingdom and asks if user wanna escape
            System.out.println("Cute Servant: Ok "+ playerName + ". Listen to me. You are in the Kingdom of Velmoria. One of the most dangerous kingdoms ever in the universe.\n I am telling you right now, YOU will die if you stay here any longer. You must run and escape. \nThere is evil queen who runs this kingdom. If she finds you, she will kill you. Wanna know how to get out of here: (yes/no)");
            String reply=scanner.nextLine();
            reply.toLowerCase();

            //plaer agrees to listen
            if(reply.equals("yes")||reply.equals("ya")||reply.equals("y")){
                System.out.println("Cute Servant: Ok great! Here is what you need to know: ");
                System.out.println();
            //player refuses, but she will sya anyway
            } else if (reply.equals("no")||reply.equals("nope")||reply.equals("n")||reply.equals("nah")) {
                System.out.println("Cute Servant: What are you? Stupid? dumb? Your gonna die if you stay here. You need to get out. Whatever. I will tell you anyway, listen up.");
            //if they don't say yes or no, Lunara says the instructions anyway
            }else{
                System.out.println("Ok, I don't understand what your saying but whatever. Listen up. I will tell you how to escape");
            }

            //Lunara explaisn the rules of the game and how to win the game
            System.out.println();
            System.out.println("To escape this kingdom, you need three specific items: spellbook, purple diamond, and the wand. \nTo find these items, you must go through the different rooms and find them. Some rooms will have them while others will have hints. \nWhatever you do, just don't go into the room of the Evil Queen. If you go there, you die. Once you get all three items, find Witch Nyxeira.\n Give her these items and she will cast a spell which will take you to where you belong. Now, why you standing like a lazy potato? GO RUN, FIND THE ITEMS!");
            System.out.println("If you ever forget how to move throughout the game, type the words help, and the directions will reapper.");
            System.out.println("Remember. Find the items, avoid the queen, find the witch. GO GO. LET'S SEE IF YOU CAN MAKE IT OUT ALIVE!");
            System.out.println();
        } else {
            //if plaer has already said their name
            System.out.println("Cute Servant: " + playerName + ", why are you still talking to me? GO FIND THE ITEMS BEFORE THE QUEEN FINDS YOU!");
            System.out.println();
        }
    }

    //getters
    public String getName(){return name;}
    public String getDescription(){return description;}
}
