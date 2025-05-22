/*
The class is made to represent a room in the kingdom.
Each room contains character,items or puzzles. They also have exits to other rooms
 */
public class Room {

    //name and description of room
    private String name;
    private String info;

    //boolean to check for character presense
    private boolean hasQueen;
    private boolean hasClown;
    private boolean hasWitch;

    //boolean for items
    private boolean hasWand;
    private boolean hasSpellBook;
    private boolean hasPurpleDiamond;
    private boolean hasBlueKey;
    private boolean hasPurpleKey;

    //room connections
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    private NPC npc;//for lunara

    //puzzle stuff
    private boolean hasPuzzle;
    private String puzzle;
    private String solution;

    //constructor
    public Room(String n, boolean Queen,boolean Clown, boolean witch, boolean wand, boolean book, boolean diamond,boolean blueKey,boolean purpleKey){
        name=n;
        hasQueen=Queen;
        hasClown=Clown;
        hasWitch=witch;

        hasWand=wand;
        hasSpellBook=book;
        hasPurpleDiamond=diamond;

        hasBlueKey=blueKey;
        hasPurpleKey=purpleKey;
    }

    // Getters
    public boolean hasQueen() { return hasQueen; }
    public boolean hasClown() { return hasClown; }
    public boolean hasWitch() { return hasWitch; }
    public boolean hasWand() { return hasWand; }
    public boolean hasSpellBook() { return hasSpellBook; }
    public boolean hasPurpleDiamond() { return hasPurpleDiamond; }
    public boolean isHasBlueKey() { return hasBlueKey; }
    public boolean isHasPurpleKey() { return hasPurpleKey; }
    public boolean hasPuzzle() { return hasPuzzle; }
    public String getName() { return name; }
    public String getInfo() { return info; }
    public String getPuzzle() { return puzzle; }

    //sets the description of room
    public void setRoomInfo(String description) {
        this.info = description;
    }

    //removes clown from the room
    public void removeClown(){
        hasClown = false;
    }

    //adds NPC to the room
    public void addNPC(NPC npc){
        this.npc=npc;
    }
    //allows user to talk to NPC
    public void talkToNPC(){
        if(npc!=null){
            npc.talk();
        }
        else{
            System.out.println("There is no one in the room to talk to. Unless, you liek tlaking to yourslef or soemthing");
        }
    }

    //sets the exit paths to other rooms
    public void setExits(Room north, Room south, Room east, Room west){
        this.north=north;
        this.south=south;
        this.east=east;
        this.west=west;
    }

    //gets the exit of that room
    public Room getExit(String direction){
        if (direction.equals("north")) {return north;}
        if (direction.equals("south")) {return south;}
        if (direction.equals("east")){ return east;}
        if (direction.equals("west")) {return west;}
        else{
            System.out.println("Thats not a direction, learn to spell stupid");
        }
        return null;
    }

    //prints a list of all the avalible exits for the room
    public String getAvalibleExits(){
        String exits = "Exits: ";
        if(north!=null){ exits+= "\n -north";}
        if(south!=null) {exits+= "\n -south";}
        if(east!=null) {exits+= "\n -east";}
        if(west!=null) {exits+= "\n -west";}
        return exits;
    }

    //allows user to take an item if its present
    public boolean takeItem(String itemName) {
        if (itemName.equals("wand")) {
            if (hasWand) {
                hasWand = false;
                return true;
            } else {
                System.out.println("The wand isn't in this room. Check elsewhere!");
                return false;
            }
        }
        if (itemName.equals("spellbook")) {
            if (hasSpellBook) {
                hasSpellBook = false;
                return true;
            } else {
                System.out.println("The spellbook isn't in this room.");
                return false;
            }
        }
        if (itemName.equals("purple diamond")) {
            if (hasPurpleDiamond) {
                hasPurpleDiamond = false;
                return true;
            } else {
                System.out.println("The purple diamond isn't here.");
                return false;
            }
        }
        if (itemName.equals("blue key")) {
            if (hasBlueKey) {
                hasBlueKey = false;
                return true;
            } else {
                System.out.println("The blue key is not in this room.");
                return false;
            }
        }
        if (itemName.equals("purple key")) {
            if (hasPurpleKey) {
                hasPurpleKey = false;
                return true;
            } else {
                System.out.println("The purple key is not here.");
                return false;
            }
        }
        System.out.println("This item doesn't exist in the game.");
        return false;
    }

    //adds puzzle to room
    public void addPuzzle(String puzzle,String solution){
        hasPuzzle=true;
        this.puzzle=puzzle;
        this.solution=solution;
    }

    //checks if user's answer is right
    public boolean solvePuzzle(String answer){
        return hasPuzzle && answer.equals(solution);
    }

    //handles clown riddle puzzles
    public boolean solveClownRiddle(String answer){
        if(!hasClown){
            return false;
        }

        if(answer.toLowerCase().contains("vespara")|| answer.toLowerCase().contains("queen vespara")){
            System.out.println("Grinsorrow: Correct! Why didn't you get lucky, you little human. But I won't make this easy for you.");
            return true;
        }
        return false;
    }

    //checks if user
    public boolean numberBattle(int playerNum,int clownNum){
        return playerNum==clownNum;
    }

    public boolean openWandChest(String keyColor) {
        if (!hasWand) return false; // No wand left in chest

        if (keyColor.equals("blue")) {
            hasWand = false;
            return true;
        } else {
            System.out.println("The chest won't open with the " + keyColor + " key!");
            System.out.println("You need the blue key to open this chest!");
            return false;
        }
    }

    public boolean solveWordScramble(String answer){
        return answer.equals("grinsorrow");
    }

    public boolean giveDiamond(String answer) {
        if (answer.equalsIgnoreCase("grinsorrow") && hasPurpleDiamond) {
            hasPurpleDiamond = false;
            return true;
        }
        return false;
    }

    public String getFullInfo(){
        System.out.println("|------"+ name+"------|");
        System.out.println();

        String i= info;

        if (npc != null) {
            i += npc.getName() + " is here!\n";
        }

        if(hasWand||hasSpellBook||hasPurpleDiamond||hasBlueKey||hasPurpleKey){
            System.out.println("");
            i+= "\nItems here: \n";
            if (hasWand) {i += "- Wand\n";}
            if (hasSpellBook){ i += "- Spellbook\n";}
            if (hasPurpleDiamond) {i += "- Purple Diamond\n";}
            if (hasBlueKey) {i += "-Blue Key\n";}
            if (hasPurpleKey) {i += "-Purple Key\n";}
            i += "\n";
        }

        if (hasQueen) i += " \nQueen Vespera is here! You Dead bud. You Dead!\n";
        if (hasClown) i += " \nGrinsorrow the Clown is here! Pray you make it out Alive!\n";
        if (hasWitch) i += " \nWitch Nyxeira is here! THIS IS YOUR CHANCE! ESCAPE!\n";


        if(hasPurpleDiamond){
            System.out.println("\"Write out 'solve' to solve the riddle, only this way will you get something important");
        }


        if(!hasClown||!hasQueen)
        i+= "\n" + getAvalibleExits();
        return i;
    }


}
