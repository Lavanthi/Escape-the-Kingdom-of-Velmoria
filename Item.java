/*
This class represents all item, mainly basic items
*/
public class Item {
    private String name;
    private String description;

    //item constructor which gets the itemName and description
    public Item(String a, String d){
        name=a;
        description= d;
    }

    //gettter methods
    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }
}
