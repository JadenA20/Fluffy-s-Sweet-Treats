//Authors: Jaden Anthony
//Last Modified: 15-11-2024

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

public class OrderArchive extends JFrame{

    private JPanel mainPanel, optionPanel, displayPanel;
    private JButton create, edit, delete, export, exit;
    private JLabel title, backgrounds;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Order> orderList;

    public OrderArchive thisOrderQueue;

    public OrderArchive(OrdersUI ordersUI) {
      
        this.thisOrderQueue = this;
        OrdersUI ordersui = ordersUI; 


        // Set Title
        setTitle("Order Archive");

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
        title =  new JLabel("Orders");
        title.setForeground(new Color(120, 67, 59));
        title.setFont(f);
        title.setBounds(50, 50, 300, 50);


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

        export = new JButton("Export");
        export.setBackground(new Color(120, 67, 59));
        export.setForeground(new Color(255, 255, 255));
        export.setFont(h);
        export.setBounds(150, 200, 100, 35);

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
        optionPanel.add(export);
        optionPanel.add(exit);
        

        //Add Listeners
        create.addActionListener(new ButtonListener());
        edit.addActionListener(new ButtonListener());
        delete.addActionListener(new ButtonListener());
        export.addActionListener(new ButtonListener());
        exit.addActionListener(new ButtonListener());       


        //Background
        ImageIcon background_image = new ImageIcon("C:/Users/jaden/OneDrive/UWI/Java Programs/Fluffy's Sweet Treats/Images/background.png");
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


        //Loading Orders into ArrayList
        orderList = loadOrders();

        //Table Headings
        String[] columnNames=  {"ID",
            "Name",
            "Phone #",
            "Date Created",
            "Due Date",
            "Type",
            "Flavour",
            "Description",
            "Price",
            "Payment Status",
            "Location",
            "Order Status"
        };

        //Table Details
        model = new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        displayTable(orderList);

        table.setPreferredScrollableViewportSize(new Dimension(900, orderList.size()*15 +50));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        displayPanel.add(scrollPane);

        add(mainPanel);
        pack();
        setVisible(true);
        setPreferredSize(new Dimension(800, 600));
        setContentPane(mainPanel);
    }



    //Loads Orders from Orders.txt
    private ArrayList<Order> loadOrders() { 

        ArrayList<Order> orderList = new ArrayList<Order>();
    
        File orders = new File("Orders.txt");
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get("Orders.txt"))){
            String line;
                
            while ((line = br.readLine()) != null){
                String[] details = line.split(";");

                int id = Integer.parseInt(details[0]);
                String customerName = details[1];
                String tele_num = details[2];
                String currentDate = details[3];
                String date = details[4];
                String type = details[5];
                String flavour = details[6];
                String desc = details[7];
                String price = details[8];
                String paymentStatus = details[9];
                String location = details[10];
                String status = details[11];

                Order o = new Order(id, customerName, tele_num, currentDate, date, type, flavour, desc, price, paymentStatus, location, status);
                orderList.add(o);

                }    
                  
            }
            catch (FileNotFoundException e) { 
                JOptionPane.showConfirmDialog(null, "File Not Found.");
                System.exit(0);
            }
            catch (IOException e) { 
                JOptionPane.showConfirmDialog(null, "I/O Error Occurred.");
                System.exit(0);
            }
            /*catch (Exception e) { 
                JOptionPane.showConfirmDialog(null, "Error Occurred.");
                System.exit(0);
            } */

        return orderList;
    } 



    private void addToTable(Order o) //Adds order to table
    {
        int id = o.getID();
        String customerName = o.getCustomerName();
        String tele_num = o.getPhone();
        String currentDate = o.getCurrentDate();
        String date = o.getDueDate();
        String type = o.getType();
        String flavour = o.getFlavour();
        String desc = o.getDesc();
        String price = o.getPrice();
        String paymentStatus = o.getPaymentStatus();
        String location = o.getLocation();
        String status = o.getStatus();
        
        String[] order = {String.valueOf(id), customerName, tele_num, currentDate, date, type, flavour, desc, price, paymentStatus, location, status};
        model.addRow(order);        

    }

    //Displays Order.txt via table 
    private void displayTable(ArrayList<Order> orderList) 
    {
       int n = 0;
       if (orderList.size()>0)
       {
        for (Order o: orderList)
        {
         addToTable(orderList.get(n));
         n += 1;
        }
       }
    }


    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == exit){
                setVisible(false);
                HomeScreen home = new HomeScreen(null);
                
            }
            if(e.getSource() == create){
                // Code to implement
                setVisible(false);  
                CreateOrders creOrd = new CreateOrders();
                
            }

            if(e.getSource() == edit){
               // setVisible(false);
               //EditOrders ediOrd = new EditOrders();
                
            }
            if(e.getSource() == delete){
               // setVisible(false);
               //DeleteOrders delOrd = new DeleteOrders();
                
            }
            if(e.getSource() == export){
               // setVisible(false);
               //Export class needed? D:
                
            }
        }
    }
}
