/*
This class extends from the item class. It was made for only the items needed to give to the witch.
AKA the spellbook, wand and purple diamond.
 */
public class NeededItem extends Item{
    private boolean isGivenToWitch;

    public NeededItem(String name,String description){
        super(name,description);
        isGivenToWitch=false;
    }

    //methods are made so that if the player wants to win the game, they can only win if these items are given to the witch
    public boolean isGivenToWitch(){
        return isGivenToWitch;
    }

    public void setGivenToWitch(boolean given) {
        isGivenToWitch = given;
    }
}
