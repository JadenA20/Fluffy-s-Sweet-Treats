//Author: Tara-Lee Donald
//Last Modified: 26-11-2024

//Provides the original template of an order

public class Order{ 
    protected int id;
    protected Customer customer;
    protected String event;
    protected String flavour;
    protected String desc;
    protected float price;
    protected String deliveryLocation;
    protected String creationDate;
    protected String paymentStatus;
    

    //Constructor accepts the id assigned to the customer, the customer's name, the customer's phone number, the date on which the entry was entered, the due date of the order, the type of cake, the description of the order, the cost of the cake, payment Status of the customer, the delivery location and the status of the progress of the order.
    public Order(int id, Customer customer, String creationDate, String event, String flavour, String desc, float price, String deliveryLocation, String paymentStatus){
        this.id = id;
        this.customer = customer;
        this.event = event;
        this.flavour = flavour;
        this.desc = desc;
        this.price = price;
        this.deliveryLocation = deliveryLocation;
        this.creationDate = creationDate;
        this.paymentStatus = paymentStatus;

    }

    
    //Getters or Accessors for the private variables
    public int getID(){
        return id;
    }

    public Customer getCustomer(){
        return customer;
    }

    public String getEvent(){
        return event;
    }

    public String getDesc(){
        return desc;
    }

    public float getPrice(){
        return price;
    }

    public String getPaymentStatus(){
        return paymentStatus;
    }

    public String getDeliveryLocation(){
        return deliveryLocation;
    }

    public String getCreationDate(){
        return creationDate;
    }

    public String getFlavour(){
        return flavour;
    }

    
}
    
    
