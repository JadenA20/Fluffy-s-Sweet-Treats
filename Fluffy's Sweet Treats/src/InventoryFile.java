
//Author: Khanez Wallace
//Last Modified: 27/11/24
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;



public class InventoryFile{
    ArrayList<Inventory>inventoryList = new ArrayList<>();
    ArrayList<PerishableRecord>periRecordList = new ArrayList<>();

    public InventoryFile inventFile;

    public InventoryFile(){
        this.inventFile = this;

    }

    //Appending inventory items to the InventoryItems.txt
    public void addToInventoryFile(Inventory inventItem, PerishableRecord periRecord){
        try{
            if (inventItem != null){
                BufferedWriter writer = new BufferedWriter(new FileWriter("InventoryItems.txt",true));

                writer.write(inventItem.getName() + ";" + inventItem.getDescription() + ";"  + inventItem.getPriorityStatus() + ";" + inventItem.getStockCount() + ";" + "N/A" + ";" + "N/A");
    
                writer.newLine();
                writer.flush();
                writer.close();

            } 
            
            else if (periRecord != null){
            BufferedWriter writer = new BufferedWriter(new FileWriter("InventoryItems.txt",true));

            writer.write(periRecord.getName() + ";" + periRecord.getDescription() + ";"  + periRecord.getPriorityStatus() + ";" + periRecord.getStockCount() + ";" + periRecord.getStorage() + ";" + periRecord.getShelfLife());

            writer.newLine();
            writer.flush();
            writer.close();
            }
        }

        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error saving to inventory file.");
        }

    }

    public ArrayList<Inventory> load(){
        try(BufferedReader br = Files.newBufferedReader(Paths.get("InventoryItems.txt"))){
            String line;
            while ((line = br.readLine()) != null){
                String []itemDetails = line.split(";");
                String itName = itemDetails[1];
                String itDesc = itemDetails[2];
                String priorityStat = String.valueOf(itemDetails[3]);
                String itstockCount = String.valueOf(itemDetails[4]);
                //String itStorage = itemDetails[5];
                //String itShelf = itemDetails[6];
                
                Inventory i = new Inventory(0,Integer.parseInt(itstockCount),Integer.parseInt(priorityStat),itName,itDesc);
                inventoryList.add(i);
            }

        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "File could not be accessed at this time.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
        return inventoryList;

    }
    
    public ArrayList<PerishableRecord>load2(){
        try (BufferedReader br = Files.newBufferedReader(Paths.get("InventoryItems.txt"))){
        String line;
        int ID = 1;

        while ((line = br.readLine()) != null){

            String []itemDetails = line.split(";");
            String itName = itemDetails[1];
            String itDesc = itemDetails[2];
            String priorityStat = String.valueOf(itemDetails[3]);
            String itstockCount = String.valueOf(itemDetails[4]);
            String itStorage = itemDetails[5];
            String itShelf = itemDetails[6];
            
            PerishableRecord pr = new PerishableRecord(ID,Integer.parseInt(itstockCount),Integer.parseInt(priorityStat),itName,itDesc, itStorage, itShelf);
            periRecordList.add(pr);
            ID++;
        }
    }

    
    catch(IOException e){
        JOptionPane.showMessageDialog(null, "File could not be accessed at this time.", "File Error", JOptionPane.ERROR_MESSAGE);
    }
    return periRecordList;


    }
     
    //Allow for the deletion of inventory itens in the InventoryItems.txt file

 public void deleteFromInventorytFile(int id){
        
        for(Inventory v: inventoryList){
            if(v.getID() == id){
                inventoryList.remove(v);
                break;
            }
        }

        try{
            Files.deleteIfExists(Paths.get("InventoryItems.txt"));

            File ifile = new File("InventoryItems.txt");

            for(Inventory t: inventoryList){
                addToInventoryFile(t, null);
            }

        }

        
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "File could not be accessed at this time.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Allow for the editing of inventory items
    public void updateInventory(Inventory perm, String newstockCount){
        String invent_change = perm.getName() + ";" + perm.getDescription() + ";" + String.valueOf(perm.getPriorityStatus()) + perm.getStorage() + ";" + perm.getShelfLife();
        System.out.println(invent_change);

        ArrayList<String> invtemp = new ArrayList<String>();

        try{
            FileReader fReader = new FileReader("InventoryItems.txt");
            Scanner scanner = new Scanner(fReader);
            String line;
            String [] lineArray;

            while(scanner.hasNext()){
                line = scanner.nextLine();
                if(line.length() == 0){
                    continue;

                }
                else{
                    lineArray = line.split(";");
                    if(line.startsWith(invent_change)){
                        invtemp.add(
                            lineArray[0] + ";" +
                            lineArray[1] + ";" +
                            newstockCount + ";" +
                            lineArray[3]  + ";" +
                            lineArray[4]  + ";" +
                            lineArray[5]  
                        );
                    

                        
                    }
                    else{
                        invtemp.add(line);
                    }


                    }
                }
                fReader.close();
              
              
                System.out.println(invtemp);
            }
            catch(FileNotFoundException e){

            }
    
            catch(IOException e){
    
            }
    
            try{
                PrintWriter pWriter = new PrintWriter("InventoryItems.txt");
                for(String str: invtemp){
                    pWriter.println(str);
                }
    
                pWriter.close();
    
    
            }
    
            catch(Exception e){
    
            }



            
        }
    }

        
    
