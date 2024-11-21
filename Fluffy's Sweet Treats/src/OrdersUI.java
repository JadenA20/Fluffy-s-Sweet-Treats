//Author: Jaden Anthony
//Last Modified: 15-11-2024

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

public class OrdersUI extends JFrame {

    private JPanel menuPanel;
    private JLabel order, backgrounds;
    private JButton customers, orders, exit;

    public OrdersUI thisOrdersUI;

    public OrdersUI(HomeScreen home) {

        this.thisOrdersUI = this;
        HomeScreen homeScreen = home; 
        
        // Set Title
        setTitle("Orders Home Screen");

        
        // Font
        Font f = new Font("Comic Sans Ms", Font.BOLD, 30);
        Font h = new Font("Comic Sans Ms", Font.BOLD, 20);
        Font l = new Font("Comic Sans Ms", Font.BOLD, 25);

        // Menu Panel
        menuPanel = new JPanel();
        menuPanel.setBackground(new Color(255, 255,255, 180));
        menuPanel.setBounds(100, 35, 400, 300);
        menuPanel.setLayout(null);
        
        order =  new JLabel("Orders");
        order.setForeground(new Color(120, 67, 59));
        order.setFont(f);
        order.setBounds(140, 50, 300, 50);

        customers = new JButton("Customers");
        customers.setBackground(new Color(120, 67, 59));
        customers.setForeground(new Color(255, 255, 255));
        customers.setFont(h);
        customers.setBounds(30, 200, 100, 35);

        orders = new JButton("Orders");
        orders.setBackground(new Color(120, 67, 59));
        orders.setForeground(new Color(255, 255, 255));
        orders.setFont(h);
        orders.setBounds(150, 200, 100, 35);


        exit = new JButton("Exit");
        exit.setBackground(new Color(120, 67, 59));
        exit.setForeground(new Color(255, 255, 255));
        exit.setFont(h);
        exit.setBounds(270, 200, 100, 35);


        menuPanel.add(order);
        menuPanel.add(customers);
        menuPanel.add(orders);
        menuPanel.add(exit);

        // Add Listeners
        customers.addActionListener(new ButtonListener());
        orders.addActionListener(new ButtonListener());
        exit.addActionListener(new ButtonListener());       


         // Background
         ImageIcon background_image = new ImageIcon("C:/Users/jaden/OneDrive/UWI/Java Programs/Fluffy's Sweet Treats/Images/logo.jpg");
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
                HomeScreen home = new HomeScreen(null);
                
            }
            if(e.getSource() == customers){
                // Code to implement
                //setVisible(false);
                //Customer cust = new Customer(OrdersUI.this);
                
            }

            if(e.getSource() == orders){
                setVisible(false);
                OrderArchive orderArc = new OrderArchive(OrdersUI.this);
                
            }
        }
    }



}
