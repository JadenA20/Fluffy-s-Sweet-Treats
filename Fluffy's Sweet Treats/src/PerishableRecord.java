//Author: Jaden Anthony
//Last Modified: November 28th, 2024


public class PerishableRecord extends Inventory{

    protected String storage, shelfLife;

    public PerishableRecord(int id, int stockCount, int priorityStatus, String name, String description, String storage, String shelfLife){
        super(id, stockCount, priorityStatus, name, description);
        this.storage = storage;
        this.shelfLife = shelfLife;
    }

    //Gettors
    public String getStorage(){
        return this.storage;
    }
    public String getShelfLife(){
        return this.shelfLife;
    }

    //Settors
    public void setStorage(String newStore){
        this.storage = newStore;
    }

    public void setShelfLife(String newLifeShelf){
        this.shelfLife = newLifeShelf;
    }

}
