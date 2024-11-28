//Authors: Jaden Anthony
//Last Modified: 24-11-2024

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

public class ViewComplete extends JFrame{

    private JPanel mainPanel, optionPanel, displayPanel;
    private JButton export, exit;
    private JLabel title, backgrounds;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Complete> orderList;

    public ViewComplete thisViewComplete;
    public User userAccount;
    public Admin adminAccount;

    public ViewComplete(HomeScreen home, User userAcc, Admin adminAcc) {
        

        this.thisViewComplete = this;
        this.userAccount = userAcc;
        this.adminAccount = adminAcc;

        HomeScreen homescreen = home;
       


        // Set Title
        setTitle("Completed Orders");

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
        title =  new JLabel("Completed Orders");
        title.setForeground(new Color(120, 67, 59));
        title.setFont(f);
        title.setBounds(0, 50, 300, 50);


        //Buttons
        
        export = new JButton("Export");
        export.setBackground(new Color(120, 67, 59));
        export.setForeground(new Color(255, 255, 255));
        export.setFont(h);
        export.setBounds(70, 200, 100, 35);

        exit = new JButton("Exit");
        exit.setBackground(new Color(120, 67, 59));
        exit.setForeground(new Color(255, 255, 255));
        exit.setFont(h);
        exit.setBounds(120, 200, 100, 35);


        //Adding to MainPanel
        mainPanel.add(title);
        mainPanel.add(displayPanel);
        mainPanel.add(optionPanel);
        optionPanel.add(export);
        optionPanel.add(exit);
        

        //Add Listeners
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
            "Date Created",
            "Event",
            "Flavour",
            "Description",
            "Price",
            "Payment Status",
            "Delivery Location",
            "Due Date",
            "Date Completed"
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



    //Loads Orders from CurrentOrders.txt
    private ArrayList<Complete> loadOrders() { 

        ArrayList<Complete> orderList = new ArrayList<Complete>();
    
        File orders = new File("CompletedOrders.txt");
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get("CompletedOrders.txt"))){
            String line;
                
            while ((line = br.readLine()) != null){
                String[] details = line.split(";");

                int id = Integer.parseInt(details[0]);
                String custName = details[1];
                String sepName[] = custName.split(" ");
                String fname = sepName[0];
                String lname = sepName[1];
                String creationDate = details[2];
                String event = details[3];
                String flavour = details[4];
                String desc = details[5];
                String price = String.valueOf(details[6]);
                String deliveryLocation = details[7];
                String paymentStatus = details[8];
                String dueDate = details[9];
                String dateCompleted = details[10];
                Customer cust = new Customer(id, fname, lname, null, null, null);
            
                Complete c = new Complete(id, cust, creationDate, event, flavour, desc, Float.valueOf(price), deliveryLocation, paymentStatus, dueDate, dateCompleted);
                orderList.add(c);

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
            /*catch (Exception e) { 
                JOptionPane.showConfirmDialog(null, "Error Occurred.");
                System.exit(0);
            } */

        return orderList;
    } 



    private void addToTable(Complete c) //Adds order to table
    {
        
        int id = c.getID();
        Customer customer = c.getCustomer();
        String customerName = customer.getName();
        String creationDate = c.getCreationDate();
        String dueDate = c.getDueDate();
        String event = c.getEvent();
        String flavour = c.getFlavour();
        String desc = c.getDesc();
        String price = String.valueOf(c.getPrice());
        String paymentStatus = c.getPaymentStatus();
        String deliveryLocation = c.getDeliveryLocation();
        String dateCompleted = c.getDateCompleted();

        
        String[] order = {String.valueOf(id), customerName, creationDate, event, flavour, desc, price, deliveryLocation, paymentStatus, dueDate, dateCompleted};
        model.addRow(order);        

    }

    //Displays CurrentOrder.txt via table 
    private void displayTable(ArrayList<Complete> orderList) 
    {
       int n = 0;
       if (orderList.size()>0)
       {
        for (Complete c: orderList)
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
                HomeScreen home = new HomeScreen(userAccount, adminAccount);
                
            }

            if(e.getSource() == export){
               // setVisible(false);
               //Export class needed? D:
                
            }
        }
    }
}
