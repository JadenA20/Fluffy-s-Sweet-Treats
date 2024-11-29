//Authors: Jaden Anthony
//Last Modified: November 27th, 2024

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class UserSignUp extends JFrame {
    private JPanel mainPanel;
    private JLabel userSignUp, first, last, userName, password;
    private JTextField fname, lname, user, pass;
    private JButton submit, exit;

    public UserSignUp thisUserSignUp;

    public UserSignUp (UserLogin login){

        this.thisUserSignUp = this;
        UserLogin loginScreen = login; 
     

        // Set Title
        setTitle("User Sign Up Screen");
        

        //Font
        Font f = new Font("Comic Sans Ms", Font.BOLD, 30);
        Font h = new Font("Comic Sans Ms", Font.BOLD, 15);
         

        //Instantiate Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255,255, 180));
        mainPanel.setBounds(100, 35, 300, 600);
        mainPanel.setLayout(null);


        //Instantiate Text fields
        fname = new JTextField(20);
        fname.setBounds(20, 140, 200, 30);
        fname.setBackground(new Color(244, 235, 220));

        lname = new JTextField(20);
        lname.setBounds(20, 220, 200, 30);
        lname.setBackground(new Color(244, 235, 220));

        user = new JTextField(20);
        user.setBounds(20, 300, 200, 30);
        user.setBackground(new Color(244, 235, 220));

        pass = new JTextField(20);
        pass.setBounds(20, 380, 200, 30);
        pass.setBackground(new Color(244, 235, 220));


        //Instantiate Labels
        first = new JLabel("First Name:");
        last = new JLabel("Last Name:");
        userName = new JLabel("Username: ");
        password = new JLabel("Password: ");
        userSignUp = new JLabel("SIGN UP");


        // Instantiate Buttons
        submit = new JButton("Submit");
        submit.setBounds(20, 440, 100, 50);
        submit.setBackground(new Color(100,67, 59));
        submit.setForeground(new Color(255, 255, 255));
        submit.setSize(new Dimension(100, 35));
        submit.setFont(h);

        exit = new JButton("Exit");
        exit.setBounds(170, 440, 100, 50);
        exit.setBackground(new Color(100,67, 59));
        exit.setForeground(new Color(255, 255, 255));
        exit.setSize(new Dimension(100, 35));
        exit.setFont(h);


        //SignUp Labels
        userSignUp.setForeground(new Color(100, 67, 59));
        userSignUp.setFont(f);
        userSignUp.setBounds(100, 50, 300, 50);

        first.setForeground(new Color(100, 67, 59));
        first.setFont(h);
        first.setBounds(20, 100, 300, 50);

        last.setForeground(new Color(100, 67, 59));
        last.setFont(h);
        last.setBounds(20, 180, 300, 50);

        userName.setForeground(new Color(100, 67, 59));
        userName.setFont(h);
        userName.setBounds(20, 260, 300, 50);

        password.setForeground(new Color(100, 67, 59));
        password.setFont(h);
        password.setBounds(20, 340, 300, 50);


        mainPanel.add(userSignUp);
        mainPanel.add(first);
        mainPanel.add(fname);
        mainPanel.add(last);
        mainPanel.add(lname);
        mainPanel.add(userName);
        mainPanel.add(user);
        mainPanel.add(password);
        mainPanel.add(pass);
        mainPanel.add(submit);
        mainPanel.add(exit);


        //Background
        ImageIcon background_image = new ImageIcon("C:/Users/Admin/OneDrive/Documents/Fluffy's Sweet Treats/Fluffy's Sweet Treats/Images/background.png");
        Image img = background_image.getImage();
        Image temp_img = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        background_image = new ImageIcon(temp_img);
        JLabel backgrounds = new JLabel("", background_image, JLabel.CENTER);
 
        backgrounds.add(mainPanel);
        backgrounds.setBounds(0, 0, 500, 500);

 
        //Listeners
        submit.addActionListener(new ButtonListener());
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
            if(e.getSource() == submit){
                
                String detail1 = fname.getText();
                String detail2 = lname.getText();
                String detail3 = user.getText();
                String detail4 = pass.getText();

                if (("".equals(detail1)) || ("".equals(detail2)) || ("".equals(detail3)) || ("".equals(detail4))){
                    JOptionPane.showMessageDialog(submit, "Please enter data in all fields.");
                }
                else if (detail4.length() < 5){
                    JOptionPane.showMessageDialog(submit, "Passwords must be at least 5 characters in length.");
                }
                else{

                    User u = new User(detail1, detail2, detail3, detail4, null);

                    boolean fNameCheck = u.validNameCheck(detail1);
                    boolean lNameCheck = u.validNameCheck(detail2);

                    if (fNameCheck == false || lNameCheck == false){
                        JOptionPane.showMessageDialog(submit, "Invalid first/last name.");
                    }
                    else{

                        u.saveUserDetails(detail1, detail2, detail3, detail4);

                        JOptionPane.showMessageDialog(submit, "User Created Successfully.");
                        setVisible(false);
                        UserLogin login = new UserLogin();
                    }

                }

            }
            if (e.getSource() == exit){
                setVisible(false);
                UserLogin login = new UserLogin();
            }
        }

    }
        
}
           
