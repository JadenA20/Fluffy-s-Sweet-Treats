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
        this.lname = newLName;
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


}
