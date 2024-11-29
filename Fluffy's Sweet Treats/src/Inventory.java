//Authors: Khanez Wallace and Jada-Kay Williams
//Last Modified: 24/11/24

//Creates and updates each record of inventory

public class Inventory {
    protected int id, stockCount, priorityStatus;
    protected String name, description;

    // Constructor accepts the description, name, stock amount, and priority status assigned to each inventory item
    public Inventory(int id, int stockCount, int priorityStatus, String name, String description) {
        this.id = id;
        this.stockCount = stockCount;
        this.priorityStatus = priorityStatus;
        this.name = name;
        this.description = description;
       
    }

    // Getters for the private attributes
    public int getID() {
        return this.id;
    }

    public int getStockCount() {
        return this.stockCount;
    }

    public int getPriorityStatus() {
        return this.priorityStatus;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
   
    
    // Setters for the private attributes

    public void setStockCount(int newStockCount) {
        this.stockCount = newStockCount;
    }

    public void setPriorityStatus(int newPriority) {
        this.priorityStatus = newPriority;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setDescription(String newDescrip) {
        this.description = newDescrip;
    }



}