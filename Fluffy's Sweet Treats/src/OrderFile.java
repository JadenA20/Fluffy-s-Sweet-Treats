//Authors: Jaden Anthony, Dana Archer, Tara-Lee Donald
//Last Modified: 25-11-2024

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OrderFile{
    ArrayList<Current> orderList = new ArrayList<>();
    public OrderFile ordFile;

    public OrderFile(){

        this.ordFile = this; 
    }


    //Function to append orders to the CurrentOrders.txt file
    public void addToCurrentFile(Current currentOrd){

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\IOLYN DONALD\\Documents\\Fluffy-s-Sweet-Treats\\Fluffy's Sweet Treats\\src\\CurrentOrders.txt", true));

            writer.write(currentOrd.getCustomer().getName() + ";" + currentOrd.getCreationDate() + ";" + currentOrd.getEvent() + ";" + currentOrd.getFlavour() + ";" + currentOrd.getDesc() + ";" + currentOrd.getPrice() + ";" + currentOrd.getDeliveryLocation() + ";" + currentOrd.getPaymentStatus() + ";" + currentOrd.getDueDate());

            writer.newLine();
            writer.flush();
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

    public ArrayList<Current> load(){
    
        try (BufferedReader br = Files.newBufferedReader(Paths.get("C:\\Users\\IOLYN DONALD\\Documents\\Fluffy-s-Sweet-Treats\\Fluffy's Sweet Treats\\src\\CurrentOrders.txt"))){
            String line;
                
            while ((line = br.readLine()) != null){
                String[] details = line.split(";");

                String custName = details[0];
                String sepName[] = custName.split(" ");
                String fname = sepName[0];
                String lname = sepName[1];
                String creationDate = details[1];
                String event = details[2];
                String flavour = details[3];
                String desc = details[4];
                String price = String.valueOf(details[5]);
                String deliveryLocation = details[6];
                String paymentStatus = details[7];
                String dueDate = details[8];
                Customer cust = new Customer(0,fname, lname, null, null, null);
            
                Current c = new Current(0,cust, creationDate, event, flavour, desc, Float.valueOf(price), deliveryLocation, paymentStatus, dueDate);
                orderList.add(c);
            }

        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "File could not be accessed at this time.", "File Error", JOptionPane.ERROR_MESSAGE);
        }

        return orderList;
    }



    //Function to delete orders within the CurrentOrders.txt file
    public void deleteFromCurrentFile(int id){
        CustomerFile customer = new CustomerFile();

        customer.deleteFromCurrentFile(id);
        
        load();

        for(Current i: orderList){
            if(i.getID() == id){
                orderList.remove(i);
                break;
            }
        }

        try{
            Files.deleteIfExists(Paths.get("CurrentOrders.txt"));

            File ofile = new File("CurrentOrders.txt");

            for(Current p: orderList){
                addToCurrentFile(p);
            }

        }

        
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "File could not be accessed at this time.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    //Function to append orders to the CompleteOrders.txt file
      /*public void addToCompleteFile(Complete completeOrd){

        try{
        
        } 
        catch (IOException e){
            
        }
    }*/



    //Should I even mention exporting atp


}



