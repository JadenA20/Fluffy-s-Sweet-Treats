//Author: Jaden Anthony
//Last Modified: 14-11-2024

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class User {

    private String fname, lname, userName, password, role;
    private ArrayList<String> usersList;

    public User(String fname, String lname, String userName, String password, String role){
        this.fname = fname;
        this.lname = lname;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    //Getter functions
    public String getFName(){
        return fname;
    }

    public String getLName(){
        return lname;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }


    //Setter functions
    public void setFName(String newFName){
        this.fname = newFName;
    }

    public void setLName(String newLName){
        this.fname = newLName;
    }

    public void setUserName(String newUserName){
        this.userName = newUserName;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }
    public void setRole(String newRole){
        this.role = newRole;
    }


    //Funtion to ensure login details match with saved user details
    public void verifyUser(String user, String pass){

        ArrayList<String> usersList = new ArrayList<String>();
    
        File users = new File("Users.txt");
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get("users.txt"))){
            String line;
                
            while ((line = br.readLine()) != null){
                usersList.add(line);
                }    
                
                for (String u : usersList) {
                   
                    String[] breakdown = u.split(";");
                    
                    String savedUser = breakdown[2];
                    String savedPass = breakdown[3];
                
                    if ((savedUser.equals(user)) && (savedPass.equals(pass)) && user.equals("Fluffy")){
                        AdminUI admin = new AdminUI();
                    }
                    else if ((savedUser.equals(user)) && (savedPass.equals(pass))) {
                        HomeScreen home = new HomeScreen(user);
                    }
                    /*else {
                        JOptionPane.showConfirmDialog(null, "Login Error.");
                        System.exit(0);
                    }*/
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
          

    }


    //Function to save user details to text file after sign up
    public void saveUserDetails(String fname, String lname, String userName, String password){
        if ((fname.equals("Marcelle")) && (lname.equals("Reid"))){

            setFName(fname);
            setLName(lname);
            setUserName(userName);
            setPassword(password);
            setRole("Admin");

            Admin a = new Admin(fname, lname, userName, password, role, 0);
            int key = a.createKey(0);

            String details = fname + ";" + lname + ";" + userName + ";" + password + ";" + role + ";" + key;
            File users = new File("Users.txt");
            
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("Users.txt", true));
                bw.write(details);
                bw.newLine();
                bw.close();

            } 
            catch (Exception e) { 
                JOptionPane.showConfirmDialog(null, "Error saving user details to file.");
            }

        }

        else 
        {
            setFName(fname);
            setLName(lname);
            setUserName(userName);
            setPassword(password);
            setRole("Generic");

            String details = fname + ";" + lname + ";" + userName + ";" + password + ";" + role;
            File users = new File("Users.txt");
            
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("Users.txt", true));
                bw.write(details);
                bw.newLine();
                bw.close();
            } 
            catch (Exception e) { 
                JOptionPane.showConfirmDialog(null, "Error saving user details to file.");
            }

        }

    }

}
