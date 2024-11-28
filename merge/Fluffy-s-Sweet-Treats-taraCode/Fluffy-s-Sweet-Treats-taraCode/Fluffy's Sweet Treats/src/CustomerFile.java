//Authors: Jaden Anthony, Dana Archer, Tara-Lee Donald
//Last Modified: 25-11-2024

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CustomerFile{
    public ArrayList<Customer> customerList = new ArrayList<>();
    public CustomerFile custFile;

    public CustomerFile(){

        this.custFile = this; 
    }

    public ArrayList<Customer> load(){ 

        try (BufferedReader br = Files.newBufferedReader(Paths.get("C:/Users/Admin/2140/merge/Fluffy-s-Sweet-Treats-taraCode/Fluffy-s-Sweet-Treats-taraCode/Fluffy's Sweet Treats/src/Customers.txt"))){
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

            BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/Admin/2140/merge/Fluffy-s-Sweet-Treats-taraCode/Fluffy-s-Sweet-Treats-taraCode/Fluffy's Sweet Treats/src/Customers.txt", true));

            writer.write(cust.getFirstName() + ";" + cust.getLastName() + ";" + cust.getTelephone() + ";" + cust.getAddress() + ";" + cust.getContactMethod()); 

            writer.newLine();
            writer.flush();
            writer.close();
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving to customer file.");
        }
    }

    public int getLineNum(String file, Customer c){
        int lineNum = 1;
        try{
            File f = new File(file);
            Scanner scanner = new Scanner(f);
            String temp = c.getName() + ";" + c.getAddress()+ ";" + c.getTelephone();

            while(scanner.hasNext()){
                if(scanner.nextLine().startsWith(temp)){
                    break;

                }

                else{
                    lineNum++;
                }
            }

        }
        catch(FileNotFoundException e){

        }

        catch(IOException e){

        }

        return lineNum;
        
    }

    public void removeCustomer(String file, int dLine){
        String tempFile = "C:/Users/Admin/2140/merge/Fluffy-s-Sweet-Treats-taraCode/Fluffy-s-Sweet-Treats-taraCode/Fluffy's Sweet Treats/src/tempC.txt";
        File oldCfile = new File(file);
        File newCFile = new File(tempFile);

        int line = 0;
        String currentLine = null;

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true));
            PrintWriter pWriter = new PrintWriter(writer);
            
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);

            while((currentLine = bReader.readLine()) != null){
                line++;

                if(dLine != line){
                    pWriter.println(currentLine);
                }
            }
            pWriter.flush();
            pWriter.close();
            fReader.close();
            bReader.close();
            writer.close();

            oldCfile.delete();
            File dump = new File(file);
            newCFile.renameTo(dump);

        }

        catch(Exception e){

        }


        /*load();

        for(Customer i: customerList){
            if(i.getID() == id){
                customerList.remove(i);
                break;
            }
        }

        try{
            Files.deleteIfExists(Paths.get("C:/Users/Admin/2140/merge/Fluffy-s-Sweet-Treats-taraCode/Fluffy-s-Sweet-Treats-taraCode/Fluffy's Sweet Treats/src/Customers.txt"));

            File ofile = new File("C:/Users/Admin/2140/merge/Fluffy-s-Sweet-Treats-taraCode/Fluffy-s-Sweet-Treats-taraCode/Fluffy's Sweet Treats/src/Customers.txt");

            for(Customer p: customerList){
                addToFile(p);
            }

        }
    
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "File could not be accessed at this time.", "File Error", JOptionPane.ERROR_MESSAGE);
        }*/
    }



    public void updateCustomer(Customer c, String tele){
        System.out.println(tele);

        String customerToChange = c.getFirstName() + ";" + c.getLastName();
        System.out.println(customerToChange);
        ArrayList<String> temp = new ArrayList<String>();

            try{
                FileReader freader = new FileReader("C:/Users/Admin/2140/merge/Fluffy-s-Sweet-Treats-taraCode/Fluffy-s-Sweet-Treats-taraCode/Fluffy's Sweet Treats/src/Customers.txt");
                Scanner scanner = new Scanner(freader);
                String line;
                String [] lineArray;

                while(scanner.hasNext()){
                    line = scanner.nextLine();
                    if(line.length() == 0){
                        continue;
                    }

                    else{
                        lineArray = line.split(";");
                        System.out.println(line);
                        if(line.startsWith(customerToChange)){
                            System.out.println("Whyyy");
                            temp.add(
                                lineArray[0] + ";"+
                                lineArray[1] + ";" +
                                lineArray[2] + ";" +
                                 tele + ";" +
                                lineArray[4]
                                );                            
                        }
                        else{
                            temp.add(line);
                        }
                    }
                }
                freader.close();
                System.out.println(temp);
            }

            catch(FileNotFoundException e){

            }

            catch(IOException e){

            }

        try{
            PrintWriter pWriter = new PrintWriter("C:/Users/Admin/2140/merge/Fluffy-s-Sweet-Treats-taraCode/Fluffy-s-Sweet-Treats-taraCode/Fluffy's Sweet Treats/src/Customers.txt");
            for(String str: temp){
                pWriter.println(str);
            }

            pWriter.close();
        }

        catch(Exception e){

        }

    }



}