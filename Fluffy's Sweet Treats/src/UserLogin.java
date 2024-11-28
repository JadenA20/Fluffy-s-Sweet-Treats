//Authors: Dana Archer, Jaden Anthony
//Last Modified: 14-11-2024

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


public class UserLogin extends JFrame {
    private JPanel userPanel, headerPanel;
    private JLabel userName, password, title, userlogin;
    private JTextField user, pass;
    private JButton login, exit, signup;

    public UserLogin thisLogin;

    public UserLogin (){
        // Current instance of UserLogin
        this.thisLogin = this;

        // Set Title
        setTitle("Fluffy's Sweet Treats Login Screen");

        // Font
        Font f = new Font("Comic Sans Ms", Font.BOLD, 30);
        Font h = new Font("Comic Sans Ms", Font.BOLD, 20);
        Font l = new Font("Comic Sans Ms", Font.BOLD, 25);


        // Instantiate Panels
        headerPanel = new JPanel();
        userPanel = new JPanel();
      
    
        // Instantiate Labels
        userName = new JLabel("Username:");
        password = new JLabel("Password:");
        title = new JLabel("Fluffy's Sweet Treats");
        userlogin = new JLabel("LOGIN");

        // Instantiate Text fields
        user = new JTextField(20);
        user.setBounds(20, 140, 200, 30);
        user.setBackground(new Color(244, 235, 220));

        pass = new JTextField(20);
        pass.setBounds(20, 210, 200, 30);
        pass.setBackground(new Color(244, 235, 220));

        // Instantiate Buttons
        login = new JButton("Login");
        login.setBounds(20, 260, 100, 50);
        login.setBackground(new Color(100,67, 59));
        login.setForeground(new Color(255, 255, 255));
        login.setSize(new Dimension(100, 35));
        login.setFont(h);

        exit = new JButton("Exit");
        exit.setBounds(20, 315, 100, 50);
        exit.setBackground(new Color(100,67, 59));
        exit.setForeground(new Color(255, 255, 255));
        exit.setSize(new Dimension(100, 35));
        exit.setFont(h);

        signup = new JButton("SignUp");
        signup.setBounds(170, 260, 100, 50);
        signup.setBackground(new Color(100,67, 59));
        signup.setForeground(new Color(255, 255, 255));
        signup.setSize(new Dimension(100, 35));
        signup.setFont(h);

      
       

        // Header Panel
        headerPanel.setBackground(new Color(255,255, 255, 180));
        headerPanel.setBounds(0, 0, 500, 60);
        title.setForeground(new Color(100, 67, 59));
        title.setFont(f);
        title.setBounds(250, 50, 600, 10);
        headerPanel.add(title);


        // Login Labels
        userlogin.setForeground(new Color(100, 67, 59));
        userlogin.setFont(l);
        userlogin.setBounds(100, 50, 300, 50);

        userName.setForeground(new Color(100, 67, 59));
        userName.setFont(h);
        userName.setBounds(20, 100, 300, 50);

        password.setForeground(new Color(100, 67, 59));
        password.setFont(h);
        password.setBounds(20, 170, 300, 50);

        //User Panel
        userPanel.setSize(200, 250);
        userPanel.setBackground(new Color(255, 255, 255, 180));
        userPanel.setBounds(100, 85, 300, 370);
        userPanel.setLayout(null);

        userPanel.add(userlogin);
        userPanel.add(userName);
        userPanel.add(user);
        userPanel.add(password);
        userPanel.add(pass);
        userPanel.add(login);
        userPanel.add(exit);
        userPanel.add(signup);
        

        
        //Background
        ImageIcon background_image = new ImageIcon("C:/Users/Admin/OneDrive/Documents/Fluffy's Sweet Treats/Fluffy's Sweet Treats/Images/background.png");
        Image img = background_image.getImage();
        Image temp_img = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        background_image = new ImageIcon(temp_img);
        JLabel backgrounds = new JLabel("", background_image, JLabel.CENTER);

        backgrounds.add(headerPanel);
        backgrounds.add(userPanel);
        backgrounds.setBounds(0, 0, 500, 500);
       

        // Listeners
        login.addActionListener(new ButtonListener());
        exit.addActionListener(new ButtonListener());
        signup.addActionListener(new ButtonListener());
       
        //Frame
        add(backgrounds);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 500));

    }

    public String getCurrentUser(){
        return ""; //gets current user
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == login){

                String detail1 = user.getText();
                String detail2 = pass.getText();

                if (("".equals(detail1)) || ("".equals(detail2))){
                    JOptionPane.showMessageDialog(login, "Missing details detected. Please try again.");
                }

                else{
                    setVisible(false);
                    verifyUser(detail1, detail2);
                 
                }
            
            }
            if(e.getSource() == signup){
                setVisible(false);
                UserSignUp userSignUp = new UserSignUp(UserLogin.this);
            }
            if (e.getSource() == exit){
                System.exit(0);
            }
        }

    }

    
    //Funtion to ensure login details match with saved user details
    public void verifyUser(String user, String pass){

        try (BufferedReader br = Files.newBufferedReader(Paths.get("C:\\Users\\IOLYN DONALD\\Documents\\Fluffy-s-Sweet-Treats\\Fluffy's Sweet Treats\\src\\Users.txt"))){
            String line;
                
            while ((line = br.readLine()) != null){
                   
                String[] breakdown = line.split(";");
                
                String savedUser = breakdown[2];
                String savedPass = breakdown[3];
            
                if ((savedUser.equals(user)) && (savedPass.equals(pass)) ){
                    if (user.equals("Fluffy")){
                        AdminUI admin = new AdminUI();
                    }
                    else{
                        HomeScreen home = new HomeScreen(user);
                    }
                    break;
                }
                
            }
                    
        }
        catch (FileNotFoundException e) { 
            JOptionPane.showMessageDialog(null, "File Not Found.");
            System.exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error accessing file. Try again later.");
            System.exit(0);
        }
          

    }

}
