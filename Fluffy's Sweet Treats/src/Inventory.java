//Authors: Khanez Wallace and Jada-Kay Williams
//Last Modified: 24/11/24

//Creates and updates each record of inventory

public class Inventory {
    private int stockCount, priorityStatus;
    private static int id =0;
    private String name, description;

    //Constructor accepts the id, description, name, stock amount and priority status of assigned to each inventory item
     
    public Inventory(int id, int stockCount, int priorityStatus, String name, String description){
        id = id++;
        this.stockCount = stockCount;
        this.priorityStatus = priorityStatus;
        this.name = name;
        this.description = description;
    }

    //Getters for the pivate attributes

    public static int getID(){
        return id;
    }

    public int getStockCount(){
        return stockCount;
    }

    public int getPriorityStatus(){
        return priorityStatus;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    
    

    


}
