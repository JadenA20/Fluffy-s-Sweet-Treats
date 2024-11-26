//Authors: Khanez Wallace and Jada-Kay Williams
//Last Modified: 25/11/24

//Creates a new perishable item of inventory


public class PerishableRecord extends Inventory {
    private String storage, shelfLife;

    // Constructor accepting additional attributes for PerishableRecord
    public PerishableRecord(int stockCount, int priorityStatus, String name, String description, String storage, String shelfLife) {
        super(stockCount, priorityStatus, name, description); 
        this.storage = storage;
        this.shelfLife = shelfLife;   
    }

    // Getters for the private attributes
    public String getStorage() {
        return storage;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    // Setters for the private attributes
    public void setStorage(String newStorage) {
        this.storage = newStorage;
    }

    public void setShelfLife(String newShelfLife) {
        this.shelfLife = newShelfLife;
    }
}


