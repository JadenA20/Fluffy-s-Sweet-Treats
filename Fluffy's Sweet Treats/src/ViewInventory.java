//Author: Khanez Wallace
//Last Modified: 26/11/2024

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ViewInventory extends JFrame {

    
    private JPanel mainPanel, optionPanel, displayPanel, sortPanel;;
    private JButton create, edit, delete, exit, sortPriority, sortQuan;
    private JLabel title, backgrounds;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Inventory> inventoryList;
    private ArrayList<PerishableRecord> periRecordList;
    private int id;

    private ViewInventory thisViewInventory;
    private User userAccount;
    private Admin adminAccount;

    public ViewInventory(HomeScreen home,  User userAcc, Admin adminAcc) {

        this.thisViewInventory = this;
        this.userAccount = userAcc;
        this.adminAccount = adminAcc;
        HomeScreen homescreen = home;

        //Set Title
        setTitle("Inventory");

        //Fonts
        Font f = new Font("Comic Sans Ms", Font.BOLD, 30);
        Font h = new Font("Comic Sans Ms", Font.BOLD, 20);

        //Panels
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255,255, 180));
        mainPanel.setBounds(100, 35, 600, 600);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout());

        displayPanel = new JPanel();

        sortPanel = new JPanel();
        sortPanel.setLayout(new FlowLayout());

        //Labels
        title =  new JLabel("Inventory");
        title.setForeground(new Color(120, 67, 59));
        title.setFont(f);
        title.setBounds(0, 50, 300, 50);

        //Buttons
        create = new JButton("Create");
        create.setBackground(new Color(120, 67, 59));
        create.setForeground(new Color(255, 255, 255));
        create.setFont(h);
        create.setBounds(30, 200, 100, 35);

        edit = new JButton("Edit");
        edit.setBackground(new Color(120, 67, 59));
        edit.setForeground(new Color(255, 255, 255));
        edit.setFont(h);
        edit.setBounds(70, 200, 100, 35);

        delete = new JButton("Delete");
        delete.setBackground(new Color(120, 67, 59));
        delete.setForeground(new Color(255, 255, 255));
        delete.setFont(h);
        delete.setBounds(110, 200, 100, 35);

        exit = new JButton("Exit");
        exit.setBackground(new Color(120, 67, 59));
        exit.setForeground(new Color(255, 255, 255));
        exit.setFont(h);
        exit.setBounds(190, 200, 100, 35);

        sortQuan = new JButton("Sort By Stock Count");
        sortQuan.setBackground(new Color(120, 67, 59));
        sortQuan.setForeground(new Color(255, 255, 255));
        sortQuan.setFont(h);
        sortQuan.setBounds(30, 200, 75, 35);

        sortPriority = new JButton("Sort by Priority");
        sortPriority.setBackground(new Color(120, 67, 59));
        sortPriority.setForeground(new Color(255, 255, 255));
        sortPriority.setFont(h);
        sortPriority.setBounds(30, 200, 75, 35);

        //Adding to MainPanel
        mainPanel.add(title);
        mainPanel.add(displayPanel);
        mainPanel.add(sortPanel);
        mainPanel.add(optionPanel);
        optionPanel.add(create);
        optionPanel.add(edit);
        optionPanel.add(delete);
        optionPanel.add(exit);
        sortPanel.add(sortQuan);
        sortPanel.add(sortPriority);

        //Add Listeners
        create.addActionListener(new ButtonListener());
        edit.addActionListener(new ButtonListener());
        delete.addActionListener(new ButtonListener());
        exit.addActionListener(new ButtonListener()); 

        //Background
        ImageIcon background_image = new ImageIcon("background.png");
        Image img = background_image.getImage();
        Image temp_img = img.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        background_image =  new ImageIcon(temp_img);
        backgrounds = new JLabel("", background_image, JLabel.CENTER);

        backgrounds.add(mainPanel);
        backgrounds.setBounds(0,0,600,400);

        //Frame
        add(backgrounds);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 400));

        //Loading Inventory items into various ArrayList
        inventoryList = loadInventory();
        periRecordList = loadPerishableInventory();

        //Table Headings
        String[] columnnNames = {"ID", 
        "Name",
        "Item Description",
        "Priority Status",
        "Stock Count",
        "Area of Storage",
        "Shelf Life"
       };

       //Table Details
       model = new DefaultTableModel(columnnNames,0);
       table = new JTable(model);
       displayTable(inventoryList);
       displayPeriTable(periRecordList);

       table.setPreferredScrollableViewportSize(new Dimension(900, inventoryList.size()*15 +50));
       table.setFillsViewportHeight(true);
       scrollPane = new JScrollPane(table);
       displayPanel.add(scrollPane);

       add(mainPanel);
       pack();
       setVisible(true);
       setPreferredSize(new Dimension(800, 600));
       setContentPane(mainPanel);
    }  

    //Loads Regular Inventory Items
    private ArrayList<Inventory> loadInventory(){

        ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();

        File items = new File("InventoryItems.txt");
        
        try(BufferedReader bre = Files.newBufferedReader(Paths.get("InventoryItems.txt"))){
            String line;

            while((line = bre.readLine()) !=null){
                String []itemDetails = line.split(";");

                if (itemDetails[itemDetails.length - 1].equals("N/A") == true){

                ++id;
                String itName = itemDetails[0];
                String itDesc = itemDetails[1];
                int priorityStat = Integer.parseInt(itemDetails[2]);
                int itstockCount = Integer.parseInt(itemDetails[3]);
                
                Inventory i = new Inventory(id, itstockCount,priorityStat,itName,itDesc);
                inventoryList.add(i);
                }

            }
        }
        catch (FileNotFoundException e){
            JOptionPane.showConfirmDialog(null, "File Not Found.");
            System.exit(0); 
        }
        catch(IOException e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
                System.exit(0);
            }

        return inventoryList;

    }



    //Loads Perishable Inventory Items
    private ArrayList<PerishableRecord> loadPerishableInventory(){

        ArrayList<PerishableRecord> periRecordList = new ArrayList<PerishableRecord>();

        File prItems = new File("InventoryItems.txt");
        
        try(BufferedReader bre = Files.newBufferedReader(Paths.get("InventoryItems.txt"))){
            String line;

            while((line = bre.readLine()) !=null){
                String []itemDetails = line.split(";");

                if (itemDetails[itemDetails.length - 1].equals("N/A") == false){

                    ++id;
                    String itName = itemDetails[0];
                    String itDesc = itemDetails[1];
                    int priorityStat = Integer.parseInt(itemDetails[2]);
                    int itstockCount = Integer.parseInt(itemDetails[3]);
                    String itStorage = itemDetails[4];
                    String itShelf = itemDetails[5];
                    
                    PerishableRecord pr = new PerishableRecord(id, itstockCount,priorityStat,itName,itDesc, itStorage, itShelf);
                    periRecordList.add(pr);


                }

            }
        }
        catch (FileNotFoundException e){
            JOptionPane.showConfirmDialog(null, "File Not Found.");
            System.exit(0); 
        }
        catch(IOException e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
                System.exit(0);
            }

        return periRecordList;

    }



    //Adds inventory item to table
    private void addToTable(Inventory i) {
        int id = i.getID();
        String itName = i.getName();
        String itDesc = i.getDescription();
        int priorityStat = i.getPriorityStatus();
        int itstockCount = i.getStockCount(); 
       

        String[] item = {String.valueOf(id),itName,itDesc,String.valueOf(priorityStat),String.valueOf(itstockCount), "N/A","N/A"};
        model.addRow(item);
    }



    //Adds perishable inventory item to table
    private void addPeriToTable(PerishableRecord pr) {
        int id = pr.getID();
        String itName = pr.getName();
        String itDesc = pr.getDescription();
        int priorityStat = pr.getPriorityStatus();
        int itstockCount = pr.getStockCount(); 
        String itStorage = pr.getStorage();
        String itShelf = pr.getShelfLife();
    
        String[] prItem = {String.valueOf(id),itName,itDesc,String.valueOf(priorityStat),String.valueOf(itstockCount), itStorage, itShelf};
        model.addRow(prItem);
    }


    

    //Displays Inventoryitems.txt via the table
    private void displayTable(ArrayList<Inventory>inventoryList){
        
        int t =0;
        if(inventoryList.size()>0)
        {
            for(Inventory i: inventoryList){
                addToTable(inventoryList.get(t));
                t +=1;
            }
        }
    }

    //Displays Inventoryitems.txt via the table
    private void displayPeriTable(ArrayList<PerishableRecord>periRecordList){
        
        int t =0;
        if(periRecordList.size()>0)
        {
            for(PerishableRecord pr: periRecordList){
                addPeriToTable(periRecordList.get(t));
                t +=1;
            }
        }
    }



    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == exit){
                setVisible(false);
                HomeScreen home = new HomeScreen(userAccount, adminAccount);
            }
             
            if(e.getSource() == create){
                model.setRowCount(0);
                setVisible(false);
                CreateInventoryRecord createInv = new CreateInventoryRecord(thisViewInventory, userAccount, adminAccount);
                displayTable(inventoryList);
                displayPeriTable(periRecordList);
            }
           
            /* 
            if(e.getSource() == edit){
                //setVisible(false);
               //EditOrders ediOrd = new EditOrders();
            }
            if(e.getSource()== delete){
               //setVisible(false);
               //DeleteInventoryRecord delnvRec = new DeleteInventoryRecord(thisViewInventory, userAccount, adminAccount);
            }*/
           
        }
    }





}