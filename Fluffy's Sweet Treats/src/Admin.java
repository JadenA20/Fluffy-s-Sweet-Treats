//Author: Jaden Anthony
//Last Modified: 14-11-2024

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Admin extends User {

    private int passKey;

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

    public void verifyUser(int key){
    
        File users = new File("Users.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader("Users.txt"));
            String line = br.readLine();

            while (line != null){
                line = br.readLine();
                String[] breakdown = line.split(";");
                String strKey = String.valueOf(key);
                String name = breakdown[2];

                if ((breakdown[5].equals(strKey))){
                    HomeScreen home = new HomeScreen(name);
                } 
                else {
                    JOptionPane.showConfirmDialog(null, "Incorrect key.");
                }
            }
            br.close();

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

}