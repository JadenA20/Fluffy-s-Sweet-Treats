//Author: Tara-Lee Donald
//Last Modified: 06-11-2024

//Creates and updates values of an order

public class Order{
    private String date, type, desc, location, status, currentDate, paymentStatus, flavour;
    private Customer customer;
    private float price;
    private int id;

    //Constructor accepts the id assigned to the customer, the customer's name, the customer's phone number, the date on which the entry was entered, the due date of the order, the type of cake, the description of the order, the cost of the cake, payment Status of the customer, the delivery location and the status of the progress of the order.

    public Order(int id, Customer customer, String currentDate, String date,String type, String flavour, String desc, float price, String paymentStatus, String location, String status){
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.type = type;
        this.flavour = flavour;
        this.desc = desc;
        this.price = price;
        this.location = location;
        this.status = status;
        this.currentDate = currentDate;
        this.paymentStatus = paymentStatus;

    }

    //Getters or Accessors for the private variables

    public int getID(){
        return id;
    }

    public Customer getCustomer(){
        return customer;
    }

    public String getDueDate(){
        return date;
    }

    public String getType(){
        return type;
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

    public String getLocation(){
        return location;
    }

    public String getStatus(){
        return status;
    }

    public String getCurrentDate(){
        return currentDate;
    }

    public String getFlavour(){
        return flavour;
    }

    //Setters or Mutators for the private variables but current date and id

    public void setStatus(String newStatus){
        this.status = newStatus;
    }

    public void setFName(String newFName){
        this.customer.setFirstName(newFName);
    }

    public void setLName(String newLName){
        this.customer.setLastName(newLName);
    }

    public void setLocation(String newLocation){
        this.location = newLocation;
    }

    public void setType(String newType){
        this.type = newType;
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
