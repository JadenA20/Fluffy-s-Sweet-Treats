//Authors: Dana Archer
//Last Modified: 26-11-24


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeScreen extends JFrame{
    private JPanel menuPanel;
    private JLabel welcome, backgrounds;
    private JButton curOrders, comOrders, customers, inventory, exit;

    public HomeScreen thisHome;
    public User userAccount;
    public Admin adminAccount;

    public HomeScreen (User userAcc, Admin adminAcc){
        this.thisHome = this;
        this.userAccount = userAcc;
        this.adminAccount = adminAcc;
        
        // Set Title
        setTitle("Fluffy's Sweet Treats Home Screen");

        // Font
        Font f = new Font("Comic Sans Ms", Font.BOLD, 30);
        Font h = new Font("Comic Sans Ms", Font.BOLD, 15);


        // Menu Panel
        menuPanel = new JPanel();
        menuPanel.setBackground(new Color(255, 255,255, 180));
        menuPanel.setBounds(100, 35, 400, 300);
        menuPanel.setLayout(null);


        if (userAcc == null){

            welcome =  new JLabel("Welcome, " + adminAcc.getFName() + "!");
            welcome.setForeground(new Color(120, 67, 59));
            welcome.setFont(f);
            welcome.setBounds(80, 50, 300, 50);

        }
        else {

            welcome =  new JLabel("Welcome, " + userAcc.getFName() + "!");
            welcome.setForeground(new Color(120, 67, 59));
            welcome.setFont(f);
            welcome.setBounds(80, 50, 300, 50);
        }
       

        curOrders = new JButton("Current Orders");
        curOrders.setBackground(new Color(120, 67, 59));
        curOrders.setForeground(new Color(255, 255, 255));
        curOrders.setFont(h);
        curOrders.setBounds(30, 150, 155, 35);

        comOrders = new JButton("Completed Orders");
        comOrders.setBackground(new Color(120, 67, 59));
        comOrders.setForeground(new Color(255, 255, 255));
        comOrders.setFont(h);
        comOrders.setBounds(200, 150, 155, 35);

        customers = new JButton("Customers");
        customers.setBackground(new Color(120, 67, 59));
        customers.setForeground(new Color(255, 255, 255));
        customers.setFont(h);
        customers.setBounds(30, 200, 100, 35);

        inventory = new JButton("Inventory");
        inventory.setBackground(new Color(120, 67, 59));
        inventory.setForeground(new Color(255, 255, 255));
        inventory.setFont(h);
        inventory.setBounds(150, 200, 100, 35);


        exit = new JButton("Exit");
        exit.setBackground(new Color(120, 67, 59));
        exit.setForeground(new Color(255, 255, 255));
        exit.setFont(h);
        exit.setBounds(270, 200, 100, 35);


        menuPanel.add(welcome);
        menuPanel.add(curOrders);
        menuPanel.add(comOrders);
        menuPanel.add(customers);
        menuPanel.add(inventory);
        menuPanel.add(exit);


        // Add Listeners
        curOrders.addActionListener(new ButtonListener());
        comOrders.addActionListener(new ButtonListener());
        customers.addActionListener(new ButtonListener());
        inventory.addActionListener(new ButtonListener());
        exit.addActionListener(new ButtonListener());

        // Background
        ImageIcon background_image = new ImageIcon("../background.png");
        Image img = background_image.getImage();
        Image temp_img = img.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        background_image =  new ImageIcon(temp_img);
        backgrounds = new JLabel("", background_image, JLabel.CENTER);

        backgrounds.add(menuPanel);
        backgrounds.setBounds(0,0,600,400);

        // Frame
        add(backgrounds);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 400));
        

    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == exit){
                setVisible(false);
                UserLogin login = new UserLogin();   
            }
            if(e.getSource() == curOrders){
                // Code to implement
                setVisible(false);
                ViewCurrent viewCur = new ViewCurrent(HomeScreen.this, userAccount, adminAccount);
            }
            if(e.getSource() == comOrders){
                //Code to implement
                setVisible(false);
                ViewComplete viewCom = new ViewComplete(HomeScreen.this, userAccount, adminAccount);
            }
            if(e.getSource() == customers){
                // Code to implement
                setVisible(false);
                ViewCustomers viewCust = new ViewCustomers(HomeScreen.this, userAccount, adminAccount);
            }

            if(e.getSource() == inventory){
                // Code to implement
                setVisible(false);
                ViewInventory inventory = new ViewInventory(HomeScreen.this, userAccount, adminAccount);
            }
        }
    }
    

}
