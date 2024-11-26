//Author: Tara-Lee Donald
//Last Modified: 25-11-2024

//Creates and updates values of an current order

public class Current extends Order {

    protected String status;

    public Current(int id, Customer customer, String creationDate, String event, String flavour, String desc, float price, String deliveryLocation, String paymentStatus, String dueDate, String status) {
        super(id, customer, creationDate, event, flavour, desc, price, deliveryLocation, paymentStatus, dueDate);
    
        this.status = status;
       
    }

    public String getStatus(){
        return ("Ongoing");
        
    }

    //Setters or Mutators for the private variables except current date and id
    public void setStatus(String newStatus){
        status = newStatus;
    }

    public void setFName(String newFName){
       this.customer.setFirstName(newFName);
    }

    public void setLName(String newLName){
        this.customer.setLastName(newLName);
    }

    public void setLocation(String newLocation){
       this.deliveryLocation = newLocation;
    }

    public void setEvent(String newEvent){
         this.event = newEvent;
    }

    public void setPhone(String newPhone){
        this.customer.setTelephone(newPhone);
    }

    public void setDesc(String newDesc){
        this.desc = newDesc;
    }

    public void setPrice(float newPrice){
        this.price = newPrice;
    }

    public void setDueDate(String newDueDate){
        this.date = newDueDate;
    }

    public void setPaymentStatus(String newPaymentStatus){
        this.paymentStatus = newPaymentStatus;
    }

    public void setFlavour(String newFlavour){
        this.flavour = newFlavour;
    }
}


    

