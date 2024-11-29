//Author: Jaden Anthony
//Last Modified: November 28th, 2024

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ConfirmAdmin extends JFrame{

    private JPanel mainPanel;
    private JLabel adminKey, title;
    private JTextField key;
    private JButton enter, exit;

    public ConfirmAdmin thisAdminUI;
    public Admin adminAccount;

    public ConfirmAdmin(Admin admin){

        this.adminAccount = admin;

        // Set Title
        setTitle("Admin Key Confirmation Screen");

        // Font
        Font f = new Font("Comic Sans Ms", Font.BOLD, 30);
        Font h = new Font("Comic Sans Ms", Font.BOLD, 20);
        

        // Instantiate Panel
        mainPanel = new JPanel();
        mainPanel.setSize(200, 250);
        mainPanel.setBackground(new Color(255, 255, 255, 180));
        mainPanel.setBounds(100, 85, 300, 370);
        mainPanel.setLayout(null);
      
    
        // Instantiate Labels
        adminKey = new JLabel("Admin Key:");
        title = new JLabel("Confirm Your Key");

        // Instantiate Text fields
        key = new JTextField(20);
        key.setBounds(20, 210, 200, 30);
        key.setBackground(new Color(244, 235, 220));


        // Instantiate Buttons
        enter = new JButton("Enter");
        enter.setBounds(20, 260, 100, 50);
        enter.setBackground(new Color(100,67, 59));
        enter.setForeground(new Color(255, 255, 255));
        enter.setSize(new Dimension(100, 35));
        enter.setFont(h);

        exit = new JButton("Exit");
        exit.setBounds(140, 260, 100, 50);
        exit.setBackground(new Color(100,67, 59));
        exit.setForeground(new Color(255, 255, 255));
        exit.setSize(new Dimension(100, 35));
        exit.setFont(h);


        //Labels
        title.setForeground(new Color(100, 67, 59));
        title.setFont(f);
        title.setBounds(20, 80, 300, 50);

        adminKey.setForeground(new Color(100, 67, 59));
        adminKey.setFont(h);
        adminKey.setBounds(20, 160, 300, 50);


        //Adding components to Main Panel
        mainPanel.add(title);
        mainPanel.add(adminKey);
        mainPanel.add(key);
        mainPanel.add(enter);
        mainPanel.add(exit);

             //Background
             ImageIcon background_image = new ImageIcon("C:/Users/Admin/OneDrive/Documents/Fluffy's Sweet Treats/Fluffy's Sweet Treats/Images/background.png");
             Image img = background_image.getImage();
             Image temp_img = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
             background_image = new ImageIcon(temp_img);
             JLabel backgrounds = new JLabel("", background_image, JLabel.CENTER);
     
             backgrounds.add(mainPanel);
             backgrounds.setBounds(0, 0, 500, 500);
            
     
             // Listeners
             enter.addActionListener(new ButtonListener());
             exit.addActionListener(new ButtonListener());
            
            
             //Frame
             add(backgrounds);
             pack();
             setVisible(true);
             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             setSize(new Dimension(500, 500));

    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == enter){

                String detail1 = key.getText();

                if (("".equals(detail1))){
                    JOptionPane.showMessageDialog(enter, "Please enter the admin key.");
                }

                else{
                    setVisible(false);
                    int intKey = Integer.parseInt(detail1);
                    Admin a = new Admin(adminAccount.getFName(), adminAccount.getLName(), adminAccount.getUserName(), adminAccount.getPassword(), adminAccount.getRole(), intKey);
                    a.verifyUser(adminAccount.getUserName(), adminAccount.getPassword(), intKey);
                 
                }
            
            }
            if (e.getSource() == exit){
                setVisible(false);
                System.exit(0);
            }
            
        }
    }

}
