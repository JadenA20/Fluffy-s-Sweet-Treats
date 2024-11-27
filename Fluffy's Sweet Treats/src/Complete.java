//Author: Tara-Lee Donald
//Last Modified: 26-11-2024

//Template of a completed order for archive purposes

public class Complete extends Order {

    protected String dateCompleted;
    protected String dueDate;

    public Complete(int id, Customer customer, String creationDate, String event, String flavour, String desc, float price, String deliveryLocation, String paymentStatus, String dueDate, String dateCompleted) {
        super(id, customer, creationDate, event, flavour, desc, price, deliveryLocation, paymentStatus);
    
        this.dateCompleted = dateCompleted;
        this.dueDate = dueDate;
       
    }

    public String getDateCompleted(){
        return dateCompleted;
    }

    public String getDueDate(){
        return dueDate;
    }

}
