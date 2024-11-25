//Author: Tara-Lee Donald
//Last Modified: November 24, 2024

//Constructor accepts customer's name, address, telephone number and contact method.
public class Customer {
    private String firstName, lastName, address, telephone, contactMethod;

    public Customer(String firstName, String lastName, String address, String telephone, String contactMethod){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.contactMethod = contactMethod;
    }

    //gettors for customer
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getAddress(){
        return address;
    }

    public String getTelephone(){
        return telephone;
    }

    public String getContactMethod(){
        return contactMethod;
    }

    //Mutators for customers
    public void setFirstName(String newFName){
        this.firstName = newFName;
    }

    public void setLastName(String newLName){
        this.lastName = newLName;
    }

    public void setAddress(String newAddr){
        this.address = newAddr;
    }

    public void setTelephone(String newTelephone){
        this.telephone = newTelephone;
    }

    public void setContactMethod(String newMethod){
        this.contactMethod = newMethod;
    }

}
