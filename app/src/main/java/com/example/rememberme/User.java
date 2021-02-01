package com.example.rememberme;

import java.io.IOException;
import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String fullName;
    private String email;
    private String password;

    public User(String username, String fullName, String email, String password) {

        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(){}

    public String getFullName() {
        return fullName;
    }
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private void writeObject(java.io.ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();

    }
    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException{
        in.defaultReadObject();
    }

}
