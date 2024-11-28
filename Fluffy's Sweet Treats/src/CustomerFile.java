//Authors: Jaden Anthony, Dana Archer, Tara-Lee Donald
//Last Modified: 25-11-2024

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CustomerFile{
    public ArrayList<Customer> customerList = new ArrayList<>();
    public CustomerFile custFile;

    public CustomerFile(){

        this.custFile = this; 
    }

    public ArrayList<Customer> load(){ 

        try (BufferedReader br = Files.newBufferedReader(Paths.get("C:\\Users\\IOLYN DONALD\\Documents\\Fluffy-s-Sweet-Treats\\Fluffy's Sweet Treats\\src\\Customers.txt"))){
            String line;
                
            while ((line = br.readLine()) != null){
                String[] details = line.split(";");
                
                String fName = details[0];
                String lName = details[1];
                String address = details[2];
                String tele_num = details[3];
                String conMethod = details[4];
            
                
                Customer c = new Customer(0,fName, lName, address, tele_num, conMethod);  
                customerList.add(c);

            }   
   
        }

        catch (FileNotFoundException e) { 
            JOptionPane.showConfirmDialog(null, "File Not Found.");
            System.exit(0);
        }
        catch (IOException e) { 
            JOptionPane.showConfirmDialog(null, e.getMessage());
            System.exit(0);
        }

        return customerList;   
    }
    //This function adds the entries to the CurrentOrders.txt file
    public void addToFile(Customer cust){

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\IOLYN DONALD\\Documents\\Fluffy-s-Sweet-Treats\\Fluffy's Sweet Treats\\src\\Customers.txt", true));

            writer.write(cust.getFirstName() + ";" + cust.getLastName() + ";" + cust.getTelephone() + ";" + cust.getAddress() + ";" + cust.getContactMethod()); 

            writer.newLine();
            writer.flush();
            writer.close();
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving to customer file.");
        }
    }

    public void deleteFromCurrentFile(int id){

        load();

        for(Customer i: customerList){
            if(i.getID() == id){
                customerList.remove(i);
                break;
            }
        }

        try{
            Files.deleteIfExists(Paths.get("Customers.txt"));

            File ofile = new File("Customers.txt");

            for(Customer p: customerList){
                addToFile(p);
            }

        }
    
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "File could not be accessed at this time.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }



}