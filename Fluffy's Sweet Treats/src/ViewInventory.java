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
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ViewInventory extends JFrame {

    
    private JPanel mainPanel, optionPanel, displayPanel;
    private JButton create, edit, delete, exit;
    private JLabel title, backgrounds;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Inventory> inventoryList;

    public ViewInventory thisViewInventory;

    public ViewInventory(HomeScreen home, User user) {

        this.thisViewInventory = this;
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

        //Adding to MainPanel
        mainPanel.add(title);
        mainPanel.add(displayPanel);
        mainPanel.add(optionPanel);
        optionPanel.add(create);
        optionPanel.add(edit);
        optionPanel.add(delete);
        optionPanel.add(exit);

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

        //Loading Inventory items into an ArrayList
        inventoryList = loadInventory();

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

    //Loads Inventory Items
    private ArrayList<Inventory> loadInventory(){

        ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();

        File items = new File("InventoryItems.txt");
        
        try(BufferedReader bre = Files.newBufferedReader(Paths.get("InventoryItems.txt"))){
            String line;

            while((line = bre.readLine()) !=null){
                String []itemDetails = line.split(";");

                int id = Integer.parseInt(itemDetails[0]);
                String itName = itemDetails[1];
                String itDesc = itemDetails[2];
                int priorityStat = Integer.parseInt(itemDetails[3]);
                int itstockCount = Integer.parseInt(itemDetails[4]);
                String itStorage = itemDetails[5];
                String itShelf = itemDetails[6];
                
                Inventory i = new Inventory(id, itstockCount,priorityStat,itName,itDesc,itStorage,itShelf);
                inventoryList.add(i);


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

    //Adds inventory item to table
    private void addToTable(Inventory i) {
        int id = i.getID();
        String itName = i.getName();
        String itDesc = i.getDescription();
        int priorityStat = i.getPriorityStatus();
        int itstockCount = i.getStockCount(); 
        String itStorage = i.getStorage();
        String itShelf = i.getShelfLife();

        String[] item = {String.valueOf(id),itName,itDesc,String.valueOf(priorityStat),String.valueOf(itstockCount),itStorage,itShelf};
        model.addRow(item);
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

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == exit){
                setVisible(false);
                HomeScreen home = new HomeScreen(null);
            }
            /* 
            if(e.getSource() == create){
                //setVisible(false);

            }
            if(e.getSource() ==edit){
                //setVisible(false);
               //EditOrders ediOrd = new EditOrders();
            }
            if(e.getSource()==delete){
                // setVisible(false);
               //DeleteOrders delOrd = new DeleteOrders();
            }
           
       */ }
    }





}
