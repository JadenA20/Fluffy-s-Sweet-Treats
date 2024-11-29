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
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewCurrent extends JFrame{

    private JPanel mainPanel, optionPanel, displayPanel, sortPanel;
    private JButton create, edit, delete, changeStatus, exit, sortDueDate, sortPaymentState, sortCreationDate;
    private JLabel title, backgrounds;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Current> orderList;
    private int id = 0;
    private ViewCurrent thisViewCurrent;
    private User userAccount;
    private Admin adminAccount;

    public ViewCurrent(HomeScreen home, User userAcc, Admin adminAcc) {
        

        this.thisViewCurrent = this;
        this.userAccount = userAcc;
        this.adminAccount = adminAcc;
        HomeScreen homescreen = home;
        //user = user.getFName();


        // Set Title
        setTitle("Current Orders");

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

        sortPanel = new JPanel();
        sortPanel.setLayout(new FlowLayout());

        displayPanel = new JPanel();
        

        //Labels
        title =  new JLabel("Current Orders");
        title.setForeground(new Color(120, 67, 59));
        title.setFont(f);
        title.setBounds(0, 50, 300, 50);


        //Buttons
        create = new JButton("Create");
        create.setBackground(new Color(120, 67, 59));
        create.setForeground(new Color(255, 255, 255));
        create.setFont(h);
        create.setBounds(30, 200, 50, 35);

        sortCreationDate = new JButton("Sort By Creation Date");
        sortCreationDate.setBackground(new Color(120, 67, 59));
        sortCreationDate.setForeground(new Color(255, 255, 255));
        sortCreationDate.setFont(h);
        sortCreationDate.setBounds(30, 200, 75, 35);

        sortDueDate = new JButton("Sort by Due Date");
        sortDueDate.setBackground(new Color(120, 67, 59));
        sortDueDate.setForeground(new Color(255, 255, 255));
        sortDueDate.setFont(h);
        sortDueDate.setBounds(30, 200, 75, 35);

        sortPaymentState = new JButton("Sort By Payment Status");
        sortPaymentState.setBackground(new Color(120, 67, 59));
        sortPaymentState.setForeground(new Color(255, 255, 255));
        sortPaymentState.setFont(h);
        sortPaymentState.setBounds(30, 200, 75, 35);


        edit = new JButton("Edit");
        edit.setBackground(new Color(120, 67, 59));
        edit.setForeground(new Color(255, 255, 255));
        edit.setFont(h);
        edit.setBounds(70, 200, 50, 35);

        delete = new JButton("Delete");
        delete.setBackground(new Color(120, 67, 59));
        delete.setForeground(new Color(255, 255, 255));
        delete.setFont(h);
        delete.setBounds(110, 200, 50, 35);

        changeStatus = new JButton("Change Status");
        changeStatus.setBackground(new Color(120, 67, 59));
        changeStatus.setForeground(new Color(255, 255, 255));
        changeStatus.setFont(h);
        changeStatus.setBounds(150, 200, 50, 35);


        exit = new JButton("Exit");
        exit.setBackground(new Color(120, 67, 59));
        exit.setForeground(new Color(255, 255, 255));
        exit.setFont(h);
        exit.setBounds(190, 200, 50, 35);


        //Adding to MainPanel
        mainPanel.add(title);
        mainPanel.add(displayPanel);
        mainPanel.add(optionPanel);
        mainPanel.add(sortPanel);
        optionPanel.add(create);
        optionPanel.add(edit);
        optionPanel.add(delete);
        optionPanel.add(changeStatus);
        optionPanel.add(exit);
        sortPanel.add(sortDueDate);
        sortPanel.add(sortCreationDate);
        sortPanel.add(sortPaymentState);
       
        

        //Add Listeners
        create.addActionListener(new ButtonListener());
        sortCreationDate.addActionListener(new ButtonListener());
        sortDueDate.addActionListener(new ButtonListener());
        sortPaymentState.addActionListener(new ButtonListener());
        edit.addActionListener(new ButtonListener());
        delete.addActionListener(new ButtonListener());
        changeStatus.addActionListener(new ButtonListener());
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
            "Delivery Location",
            "Payment Status",
            "Due Date"
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
    private ArrayList<Current> loadOrders() { 

        ArrayList<Current> orderList = new ArrayList<Current>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get("CurrentOrders.txt"))){
            String line;
                
            while ((line = br.readLine()) != null){
                String[] details = line.split(";");
                ++id;
                String custName = details[0];
                String sepName[] = custName.split(" ");
                String fname = sepName[0];
                String lname = sepName[1];
                String creationDate = details[1];
                String event = details[2];
                String flavour = details[3];
                String desc = details[4];
                String price = String.valueOf(details[5]);
                String paymentStatus = details[6];
                String deliveryLocation = details[7];
                String dueDate = details[8];
                Customer cust = new Customer(id, fname, lname, null, null, null);
            
                Current c = new Current(id, cust, creationDate, event, flavour, desc, Float.valueOf(price), deliveryLocation, paymentStatus, dueDate);
                orderList.add(c);

                }    
                  
            }
            catch (FileNotFoundException e) { 
                JOptionPane.showMessageDialog(null, "File Not Found.");
                System.exit(0);
            }
            catch (IOException e) { 
                JOptionPane.showMessageDialog(null, "Error in accessing the file. Please try again later.");
                //System.exit(0);
            }
            /*catch (Exception e) { 
                JOptionPane.showConfirmDialog(null, "Error Occurred.");
                System.exit(0);
            } */

        return orderList;
    } 



    private void addToTable(Current c) //Adds order to table
    {
        
        int id = c.getID();
        Customer customer = c.getCustomer();
        String customerName = customer.getName();
        String creationDate = c.getCreationDate();
        String event = c.getEvent();
        String flavour = c.getFlavour();
        String desc = c.getDesc();
        String price = String.valueOf(c.getPrice());
        String paymentStatus = c.getPaymentStatus();
        String deliveryLocation = c.getDeliveryLocation();
        String dueDate = c.getDueDate();

        
        String[] order = {String.valueOf(id), customerName, creationDate, event, flavour, desc, price, deliveryLocation, paymentStatus, dueDate};
        model.addRow(order);        

    }

    //Displays CurrentOrder.txt via table 
    private void displayTable(ArrayList<Current> orderList) 
    {
       int n = 0;
       if (orderList.size()>0)
       {
        for (Current c: orderList)
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
            if(e.getSource() == create){
                // Code to implement
                model.setRowCount(0);
                CreateOrders creOrd = new CreateOrders();
                
                displayTable(orderList);
                
            }

            if(e.getSource() == edit){
                OrderFile ordFile = new OrderFile();
                EditOrders ediOrd = new EditOrders(thisViewCurrent, userAccount, adminAccount, ordFile);
                
            }
            if(e.getSource() == delete){
                
                DeleteOrders delOrd = new DeleteOrders(thisViewCurrent, userAccount, adminAccount);
                
            }
            if(e.getSource() == changeStatus){
                
                ChangeStatus changeStat = new ChangeStatus(thisViewCurrent, userAccount, adminAccount);
            }

            if(e.getSource() == sortCreationDate){
                model.setRowCount(0);
                Collections.sort(orderList, new Comparator<Current>(){

                    public int compare(Current o1, Current o2) {
                        return o1.getCreationDate().compareTo(o2.getCreationDate());
                    }
                });
               
                displayTable(orderList);
            }

            if(e.getSource() == sortDueDate){
                model.setRowCount(0);
                Collections.sort(orderList, new Comparator<Current>(){

                    public int compare(Current o1, Current o2) {
                        return o1.getDueDate().compareTo(o2.getDueDate());
                    }
                });
               
                displayTable(orderList);
            }

            if(e.getSource() == sortPaymentState){
                model.setRowCount(0);
                Collections.sort(orderList, new Comparator<Current>(){

                    public int compare(Current o1, Current o2) {
                        return o1.getPaymentStatus().compareTo(o2.getPaymentStatus());
                    }
                });
               
                displayTable(orderList);
            }

            
        }
    }
}