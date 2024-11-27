/Authors: Jaden Anthony, Dana Archer, Tara-Lee Donald
//Last Modified: 25-11-2024

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class OrderFile{

    public OrderFile ordFile;

    public OrderFile(){

        this.ordFile = this; 
    }


    //Function to append orders to the CurrentOrders.txt file
    public void addToCurrentFile(Current currentOrd){

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("CurrentOrders.txt", true));

            writer.write(currentOrd.getID() + ";" + currentOrd.getCustomer().getName() + ";" + currentOrd.getCreationDate() + ";" + currentOrd.getEvent() + ";" + currentOrd.getFlavour() + ";" + currentOrd.getDesc() + ";" + currentOrd.getPrice() + ";" + currentOrd.getDeliveryLocation() + ";" + currentOrd.getPaymentStatus() +  ";" + currentOrd.getDueDate());

            writer.newLine();

            writer.close();
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving to current order file.");
        }
    }



    //Function to edit orders within the CurrentOrders.txt file
    /*public void editCurrentFile(Current currentOrd){

        try{
        
        } 
        catch (IOException e){
            
        }
    }*/




    //Function to delete orders within the CurrentOrders.txt file
    /*public void deleteFromCurrentFile(Current currentOrd){

        try{
        
        } 
        catch (IOException e){
            
        }
    }*/



    //Function to append orders to the CompleteOrders.txt file
      /*public void addToCompleteFile(Complete completeOrd){

        try{
        
        } 
        catch (IOException e){
            
        }
    }*/



    //Should I even mention exporting atp


}



