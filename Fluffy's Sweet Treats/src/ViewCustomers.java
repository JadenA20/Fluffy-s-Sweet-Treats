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

public class ViewCustomers extends JFrame {

 private JPanel mainPanel, optionPanel, displayPanel;
    private JButton sortFName, sortLName, exit;
    private JLabel title, backgrounds;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Customer> customerList;

    public ViewCustomers thisViewCustomers;

    public ViewCustomers(HomeScreen home, User user) {

        this.thisViewCustomers = this;
        HomeScreen homescreen = home;
        User useracc = user;

        // Set Title
        setTitle("Customers");

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
        title =  new JLabel("Customers");
        title.setForeground(new Color(120, 67, 59));
        title.setFont(f);
        title.setBounds(-30, 50, 300, 50);


        //Buttons
        sortFName = new JButton("Sort by First Name");
        sortFName.setBackground(new Color(120, 67, 59));
        sortFName.setForeground(new Color(255, 255, 255));
        sortFName.setFont(h);
        sortFName.setBounds(70, 200, 100, 35);

        sortLName = new JButton("Sort by Last Name");
        sortLName.setBackground(new Color(120, 67, 59));
        sortLName.setForeground(new Color(255, 255, 255));
        sortLName.setFont(h);
        sortLName.setBounds(110, 200, 100, 35);

        exit = new JButton("Exit");
        exit.setBackground(new Color(120, 67, 59));
        exit.setForeground(new Color(255, 255, 255));
        exit.setFont(h);
        exit.setBounds(150, 200, 100, 35);



        //Adding to MainPanel
        mainPanel.add(title);
        mainPanel.add(displayPanel);
        mainPanel.add(optionPanel);
        optionPanel.add(sortFName);
        optionPanel.add(sortLName);
        optionPanel.add(exit);
        

        //Add Listeners
        sortFName.addActionListener(new ButtonListener());
        sortLName.addActionListener(new ButtonListener());
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


        //Loading Customers into ArrayList
        customerList = loadOrders();

        //Table Headings
        String[] columnNames=  {"First Name",
            "Last Name",
            "Address",
            "Telephone #",
            "Contact Method",

        };

        //Table Details
        model = new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        displayTable(customerList);

        table.setPreferredScrollableViewportSize(new Dimension(900, customerList.size()*15 +50));
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
    private ArrayList<Customer> loadOrders() { 

        ArrayList<Customer> customerList = new ArrayList<Customer>();
    
        File customers = new File("Customers.txt");
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get("Customers.txt"))){
            String line;
                
            while ((line = br.readLine()) != null){
                String[] details = line.split(";");

                String fName = details[0];
                String lName = details[1];
                String address = details[2];
                String tele_num = details[3];
                String conMethod = details[4];
            
                
                Customer c = new Customer(fName, lName, address, tele_num, conMethod);  
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
            /*catch (Exception e) { 
                JOptionPane.showConfirmDialog(null, "Error Occurred.");
                System.exit(0);
            } */

        return customerList;
    } 



    private void addToTable(Customer c) //Adds customer to table
    {
        
        String fname = c.getFirstName();
        String lname = c.getLastName();
        String address = c.getAddress();
        String tele_num = c.getTelephone();
        String conMethod = c.getContactMethod();
        
        String[] customer = {fname, lname, address, tele_num, conMethod};
        model.addRow(customer);        

    }

    //Displays Customers.txt via table 
    private void displayTable(ArrayList<Customer> customerList) 
    {
       int n = 0;
       if (customerList.size()>0)
       {
        for (Customer c: customerList)
        {
         addToTable(customerList.get(n));
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
            if(e.getSource() == sortFName){
               
                
            }

            if(e.getSource() == sortLName){
               
               
                
            }
           
        }
    }


}
