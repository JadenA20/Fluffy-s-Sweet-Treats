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
    private JButton orders, inventory, exit;

    public HomeScreen thisHome;

    public HomeScreen (String user){
        this.thisHome = this;
        //UserLogin loginScreen = user; 

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


        welcome =  new JLabel("Welcome, " + user + "!");
        welcome.setForeground(new Color(120, 67, 59));
        welcome.setFont(f);
        welcome.setBounds(80, 50, 300, 50);

        orders = new JButton("Orders");
        orders.setBackground(new Color(120, 67, 59));
        orders.setForeground(new Color(255, 255, 255));
        orders.setFont(h);
        orders.setBounds(30, 200, 100, 35);

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
        menuPanel.add(orders);
        menuPanel.add(inventory);
        menuPanel.add(exit);


        // Add Listeners
        orders.addActionListener(new ButtonListener());
        inventory.addActionListener(new ButtonListener());
        exit.addActionListener(new ButtonListener());

        // Background
        ImageIcon background_image = new ImageIcon("C:/Users/jaden/OneDrive/UWI/Java Programs/Fluffy's Sweet Treats/Images/background.png");
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
            if(e.getSource() == orders){
                // Code to implement
                setVisible(false);
                OrdersUI order = new OrdersUI(HomeScreen.this);
            }

            if(e.getSource() == inventory){
                // Code to implement
                // ViewInventory inventory = new ViewInventory(thisHome);
            }
        }
    }
    

}
