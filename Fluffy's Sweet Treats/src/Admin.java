//Author: Jaden Anthony
//Last Modified: 28-11-2024

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Admin extends User {

    private int passKey;
    private ArrayList<Admin> adminList;

    public Admin(String fname, String lname, String userName, String password, String role, int passKey) {
        super(fname, lname, userName, password, role);

        this.passKey = passKey;
    }

    public int getPassKey(){
        return passKey;
    }

    public void setPassKey(int newPassKey){
        this.passKey = newPassKey;
    }

    public int createKey(int x){
        this.passKey = 3579;
        JOptionPane.showMessageDialog(null, "Your admin key is " +passKey+ ", you will need it for login.");
        return passKey;
    }

    public void verifyUser(String user, String pass, int key){

         ArrayList<Admin> adminList = new ArrayList<Admin>();
    
        File users = new File("Users.txt");

        try (BufferedReader br = Files.newBufferedReader(Paths.get("./Users.txt"))){
            String line;
            String check = "";

            while  ((line = br.readLine()) != null) {
                
                String[] details = line.split(";");

                if (details[details.length - 2].equals("Admin")) {

                    String fName = details[0];
                    String lName = details[1];
                    String username = details[2];
                    String passWord = details[3];
                    String userRole = details[4];
                    String savedKey = details[5];
    
                    Admin a = new Admin(fName, lName, username, passWord, userRole, Integer.parseInt(savedKey));
                    adminList.add(a);

                }

            }
            

            for (Admin a : adminList) {
        
                String savedUser = a.getUserName();
                String savedPass = a.getPassword();
                int savedKey = a.getPassKey();
                
                   
                if ((savedUser.equals(user)) && (savedPass.equals(pass)) && savedKey == key) {
                    check = "Success";
                    Admin adminAcc = a;
                    HomeScreen home = new HomeScreen(null, adminAcc);
                    break;
                }


            }
            if (check.equals("")){
                JOptionPane.showMessageDialog(null, "Incorrect password or admin key. Please try again.");
                UserLogin newUserLogin = new UserLogin();
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
        catch (Exception e) { 
            JOptionPane.showConfirmDialog(null, "Error Occurred.");
            System.exit(0);
        } 
          

    }

}