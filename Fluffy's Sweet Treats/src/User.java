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
    private ArrayList<User> usersList;

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



    public void checkAccountType(String user, String pass){

        File users = new File("./Users.txt");
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get("./Users.txt"))){
            String line;
            String check = "";

            while ((line = br.readLine()) != null){

                String[] details = line.split(";");
                if (details[2].equals(user) && (details[details.length - 1].equals("Generic"))){
                    check = "UserExist";
                    verifyUser(user, pass);
                    break;

                } else if (details[2].equals(user) && (details[details.length - 2].equals("Admin"))){
                    check = "UserExist";
                    String fName = details[0];
                    String lName = details[1];
                    String username = details[2];
                    String passWord = details[3];
                    String userRole = details[4];

                    Admin a = new Admin(fName, lName, username, passWord, userRole, 0);
                    ConfirmAdmin conAdmin = new ConfirmAdmin(a);
                    break;

                }

            }

            if (check.equals("")){
                JOptionPane.showMessageDialog(null, "No account found for: " + user);
                UserLogin newUserLogin = new UserLogin();
            }

        }
        catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "File Not Found.");
            System.exit(0);
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "File Not Found.");
            System.exit(0);
        }
        catch (Exception e) { 
            JOptionPane.showMessageDialog(null, "Error Occurred.");
            System.exit(0);
        } 

    }
    
    
    //Funtion to ensure login details match with saved user details
    public void verifyUser(String user, String pass){

        ArrayList<User> usersList = new ArrayList<User>();
        String check = "";
    
        File users = new File("./Users.txt");
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get("./Users.txt"))){
            String line;
                
            while ((line = br.readLine()) != null){

                String[] details = line.split(";");

               
                String fName = details[0];
                String lName = details[1];
                String username = details[2];
                String passWord = details[3];
                String userRole = details[4];

                User u = new User(fName, lName, username, passWord, userRole);
                usersList.add(u);
                
                
            }    
                
            for (User u : usersList) {
                    
                String savedUser = u.getUserName();
                String savedPass = u.getPassword();
                
                   
                if ((savedUser.equals(user)) && (savedPass.equals(pass))) {
                    check = "Success";
                    User userAcc = u;
                    HomeScreen home = new HomeScreen(userAcc, null);
                    break;
                }
                
                   
            } 
            
            if (check.equals("")){
                JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.");
                UserLogin newUserLogin = new UserLogin();
            }
         
                    
        }
        catch (FileNotFoundException e) { 
            JOptionPane.showMessageDialog(null, "File Not Found.");
            System.exit(0);
        }

        catch (IOException e) { 
            JOptionPane.showMessageDialog(null, "I/O Error Occurred.");
            System.exit(0);
        }

        catch (Exception e) { 
            JOptionPane.showMessageDialog(null, "Error Occurred.");
            System.exit(0);
        } 
          

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
            File users = new File("./Users.txt");
            
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("./Users.txt", true));
                bw.write(details);
                bw.newLine();
                bw.close();
            } 
            catch (Exception e) { 
                JOptionPane.showMessageDialog(null, "Error saving user details to file.");
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
                JOptionPane.showMessageDialog(null, "Error saving user details to file.");
            }

        }

    }


    public boolean validNameCheck(String word){
       char wordSep[] = word.toCharArray();

        for (char c : wordSep){
            if (Character.isDigit(c)){
                return false;          
            }   
        }
        return true;

        
       
    }
}


