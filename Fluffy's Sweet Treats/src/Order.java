//Author: Tara-Lee Donald
//Last Modified: November 26th, 2024

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
    

    /* Constructor accepts the ID of the order, customer (name, telephone, address, contact method),
    the date on which the entry was created, the event, the flavour, the description of the order,
    the cost of the cake, payment status of the customer, the delivery location */
     
    public Order(int id, Customer customer, String creationDate,  String event, String flavour, String desc, float price, String deliveryLocation, String paymentStatus){
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
    
