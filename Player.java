import java.util.ArrayList;
import java.util.Scanner;
/*
This is for the user and their inventory. They so special I made a whole class for them.
 */
public class Player{
    private Room currentRoom;
    private ArrayList <Item> inventory;
    private boolean hasALLItems;
    private boolean isAlive;

    public Player(Room startRoom){
        currentRoom=startRoom;
        inventory = new ArrayList<>();
        hasALLItems=false;
        isAlive=true;
    }

    //getter methods
    public Room getCurrentRoom(){ return currentRoom;}
    public boolean getHasALLItems(){return hasALLItems;}
    public boolean getIsAlive(){return isAlive;}

    public void move(String direction){
        Room nextRoom = currentRoom.getExit(direction);
        if(!isAlive){
            System.out.println("WHY U TRYNA MOVE? U DEAD");
        }
        if(nextRoom!=null){
            currentRoom=nextRoom;
            if(!(currentRoom.hasQueen())){
                System.out.println(currentRoom.getFullInfo());
            } else if(currentRoom.hasQueen()){
                System.out.println("YOU HAVE WALKED INTO THE ROOM OF VESARA the EVIL QUEEN. \n Queen Vespera(says while smiling evily): WHO ARE YOU? YOU DON'T BELONG IN THIS KINGDOM. DO YOU? YOU KNOW WHAT HAPPENS TO PEOPLE WHO DON'T TO THE KINGDOM OF VELMORE?");
                System.out.println("Vespara he Queen approaches and you feel a sharp pain in your stomach. You look down and see the knife and look back and see the Queen smiling.");
                System.out.println("You DIE!!");
                isAlive=false;
                died();
            }
        }
        else{
            System.out.println("You can't go that way, stupid.");
        }
    }

    //takes item from currentRoom
    public void takeItem(String itemName) {
        if (!isValidItem(itemName)) {
            System.out.println("You think I'm stupid. This item doesn't even exist in the game");
            return;
        }

        // Special case for the wand
        if (itemName.equals("wand")) {
            System.out.println("You can't take the wand directly! You need to find the blue key and use the 'open' command.");
            return;
        }

        if (currentRoom.takeItem(itemName)) {
            Item item = new Item(itemName, " ");
            inventory.add(item);
            System.out.println("You took the " + itemName);
            showInventory(); // Show updated inventory
        }
    }

    private boolean isValidItem(String itemName) {
        return (itemName.equals("wand") ||
                itemName.equals("purple diamond") ||
                itemName.equals("spellbook") ||
                itemName.equals("blue key") ||
                itemName.equals("red key") ||
                itemName.equals("purple key"));
    }

    //checks if player has all the required items
    private void checkInventory(){
      boolean hasWand=false;
      boolean hasBook=false;
      boolean hasDiamond=false;

      for(Item item: inventory){
          if(item.getName().equals("wand")){hasWand=true;}
          if(item.getName().equals("spellbook")){hasBook=true;}
          if(item.getName().equals("purple diamond")){hasDiamond=true;}
      }

      hasALLItems=hasWand&&hasBook&&hasDiamond;
    }

    //shows the inventory to user, so user cna see what items they have and can relaize what items they need to get
    public void showInventory(){
        if(inventory.isEmpty()){
            System.out.println("YOUR INVENTORY IS EMPTY> GO OPEN YOU EYE> LOOK AND GRAB THE ITEMS. DO YOU WANNA DIE OR SOMETHING??");
        }
        else{
            System.out.println("Looks like you have: ");
            for (Item item:inventory){
                System.out.println("-"+item.getName());
            }
        }
    }

    public void talkToNPC(){
        currentRoom.talkToNPC();
    }

    public void talkToClown(){
        if(currentRoom.hasClown()){
            Scanner scanner = new Scanner(System.in);

            System.out.println("Grinsorrow: Well well well... who do we have here.");
            System.out.println("Grinsorrow: WHats your name, little one:  ");
            String name= scanner.nextLine();
            System.out.println();
            System.out.println("Grinsorrow: Ah " + name +". So thats your name. " + name + " Well, I know what you're looking for. ");
            System.out.println("Grinsorrow: You want the spellbook, don't you? So, you can escape from Queen Vespara.");
            System.out.println("Grinsorrow: Hahha, well Im not gonna give it to you so easily ,"+ name);
            System.out.println("Grinsorrow: ANSWER MY RIDDLE FIRST");
            System.out.println("");
            System.out.println("\"She rules the halls with silent dread,");
            System.out.println("Speak her name, and you may be dead.");
            System.out.println("A name that whispers fear and night,");
            System.out.println("Six letters long, a shadowed fright.");
            System.out.println("Who is she?\"");

            String answer=scanner.nextLine();
            System.out.println("");

            if(currentRoom.solveClownRiddle(answer)){
                //number battle
                int x =(int)(Math.random()*6)+1;
                String cN=x+" ";
                String clownNum= cN.substring(0,1);
                System.out.println("Grinsorrow: Now pick a number from 1 and 6, Don't try to be sneaky and put a number out of the range or a decimal. I knwo how you humans are.\n Now Choose you number: ");

                String playerNumber = scanner.nextLine();

                //this is to check if th player is tryna be sneaky and cheat
                if(!playerNumber.equals("1") && !playerNumber.equals("2") &&
                        !playerNumber.equals("3") && !playerNumber.equals("4") &&
                        !playerNumber.equals("5") && !playerNumber.equals("6")){
                    System.out.println("oh "+ name+" i warned you and you still tried to eb sneaky. I warned you though. YOU DIE");
                    isAlive=false;
                    died();
                    return;
                }


                //comparing usernum with clown num
                if(playerNumber.equals(clownNum)){
                    System.out.println("Grinsorrow: HAHAHHA! Oh how unfortunate. Looks like I also choose the number " + clownNum + " too!");
                    System.out.println("Grinsorrow: Now you DIE!");
                    isAlive = false;
                    died();
                }
                else{
                    System.out.println("Grinsorrow: Oh god no! I chose " + clownNum + ". I lost! No! Well you got lucky "+ name);
                    System.out.println("Grinsorrow Fine, take the spellbook. Lets see if you last any longer");
                    System.out.println("");
                    System.out.println("Write the words 'take spellbook' to get take the spellbook(this way u can give the witch the spellbook)");



                }
            }
            else{
                    System.out.println("Grinsorrow: Oh how unfortunate! Looks like you chose the wrong answer. HAHAH Now you DIE!");
                    isAlive = false;
                    died();
            }
        }
    }

    public void talkToWitch(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Witch Nyxeira: Ah, a visitor! What is your name: ");
        String name= scanner.nextLine();

        System.out.println("Witch Nyxeira: " + name + "... interesting. My name is Nyxeria. I came to this kingdom in hope of a few books to make a new spell\n Instead I got betrayed and trapped in this cell.\n She put on a curse on me so I can never escape.");
        System.out.println("I am sure you don't know how you got here "+ name +". Do you want to escape out of this kingdom(yes/no): ");

        String answer=scanner.nextLine();

        if(answer.equals("yes")||answer.equals("ya")||answer.equals("of couse")||answer.equals("ofc")||answer.equals("yep")||answer.equals("yeah")){

            //check inventory
            ArrayList<NeededItem> neededItems = new ArrayList<>();
            boolean hasAllItems=true;

            for(Item item: inventory){
                if(item.getName().equals("wand")){
                    neededItems.add(new NeededItem("wand", "a powerful magical wand to help the with cast the spell"));
                }
                else if(item.getName().equals("spellbook")){
                    neededItems.add(new NeededItem("spellbook", "a filled the secret spell to help visitors escape"));
                }
                else if(item.getName().equals("purple diamond")){
                    neededItems.add(new NeededItem("purple diamond", "a rare diamond to help open the spellbook"));
                }
            }

            if(neededItems.size()<3){
                System.out.println("Witch Nyxeira: You don't have all the required items! You need:");
                System.out.println("- The wand");
                System.out.println("- The spellbook");
                System.out.println("- The purple diamond");
                System.out.println("I'm sorry but I oly do the spell with all three items. Come back when you have them all!");
                return;
            }

            boolean allGiven=true;
            for(NeededItem item:neededItems){
                item.setGivenToWitch(true);
                if(!item.isGivenToWitch()){
                    allGiven=false;
                }
            }
            if (allGiven) {
                System.out.println("Witch Nyxeira: Excellent! You have brought me all the required items!");
                System.out.println("The witch begins chanting... the room fills with purple smoke...");
                System.out.println("You feel yourself being pulled through space and time...");
                System.out.println("\n\nCONGRATULATIONS! YOU HAVE ESCAPED THE KINGDOM OF VELMORIA!");
                System.out.println("----------------YOU WIN!----------------");
                isAlive = false; // Ends the game
            } else {
                System.out.println("Witch Nyxeira: Something went wrong with the ritual...");
            }
        } else {
            System.out.println("Witch Nyxeira: Very well, stay here and perish then! If you get killed by Grinsorrow or Vespara , don't say that I didn't warn you and didn't give you a chance");
        }
    }

    //chest
    public void openChest() {
        if (currentRoom.getName().equals("The Treasure Room")) {
            if (!currentRoom.hasWand()) {
                System.out.println("The chest is already empty!");
                return;
            }

            boolean hasBlueKey = false;
            for (Item item : inventory) {
                if (item.getName().equals("blue key")) {
                    hasBlueKey = true;
                    break;
                }
            }

            if (hasBlueKey) {
                if (currentRoom.openWandChest("blue")) {
                    // Create the wand item and add to inventory
                    Item wand = new Item("wand", "A powerful magical wand");
                    inventory.add(wand);
                    System.out.println("You opened the chest and found the wand!");
                    System.out.println("The wand has been added to your inventory!");
                }
            } else {
                System.out.println("You need a blue key to open this chest!");
                System.out.println("Find the key in the Kitchen of Pain first!");
            }
        } else {
            System.out.println("There's no chest to open here.");
        }
    }

    public void solveScramble() {
        if (currentRoom.getName().equals("The room of diamonds")) {
            Scanner scanner = new Scanner(System.in);

            // Only proceed if the room still has the diamond
            if (!currentRoom.hasPurpleDiamond()) {
                System.out.println("You've already taken the purple diamond from this room!");
                return;
            }

            // Sort the word (existing code)
            String str = "grinsorrow";
            String[] letters = new String[str.length()];
            for (int i = 0; i < str.length(); i++) {
                letters[i] = str.substring(i, i + 1);
            }
            for (int i = 1; i < letters.length; i++) {
                String key = letters[i];
                int j = i - 1;
                while (j >= 0 && letters[j].compareTo(key) > 0) {
                    letters[j + 1] = letters[j];
                    j--;
                }
                letters[j + 1] = key;
            }
            String sorted = "";
            for (int i = 0; i < letters.length; i++) {
                sorted += letters[i];
            }

            System.out.println("A voice echoes: 'To claim the diamond, solve the riddle!'");
            System.out.println("The scrambled word is: " + sorted);
            System.out.print("Enter the unscrambled word: ");

            String answer = scanner.nextLine();

            if (currentRoom.solveWordScramble(answer)) {
                if (currentRoom.giveDiamond(answer)) {
                    // Create and add the diamond item
                    Item diamond = new Item("purple diamond", "A rare magical diamond");
                    inventory.add(diamond);
                    System.out.println("CORRECT! The purple diamond appears in your hands!");
                }
            } else {
                System.out.println("Nothing happens... maybe try again?");
            }
        } else {
            System.out.println("There's nothing to solve here.");
        }
    }


    public void died(){
        System.out.println();
        System.out.println("YOU HAVE DIED. UNFORTUNATLY, YOU WERE NOT ABLE TO ABLE TO MAKE IT ALIVE");
        System.out.println();
        System.out.println("----------------GAME OVER----------------");

    }
}
