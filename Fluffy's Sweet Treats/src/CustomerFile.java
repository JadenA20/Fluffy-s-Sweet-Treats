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

    private void load() { 

        File customers = new File("Customers.txt");
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get("Customers.txt"))){
            String line;
                
            while ((line = br.readLine()) != null){
                String[] details = line.split(";");

                int id = Integer.parseInt(details[0]);
                String fName = details[1];
                String lName = details[2];
                String address = details[3];
                String tele_num = details[4];
                String conMethod = details[5];
            
                
                Customer c = new Customer(id,fName, lName, address, tele_num, conMethod);  
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
    }
    //This function adds the entries to the CurrentOrders.txt file
    public void addToFile(Customer cust){

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("Customers.txt", true));

            writer.write(cust.getID() + ";" +cust.getFirstName() + ";" + cust.getLastName() + ";" + cust.getTelephone() + ";" + cust.getAddress() + ";" + cust.getContactMethod()); 

            writer.newLine();

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