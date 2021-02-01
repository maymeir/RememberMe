package com.example.rememberme;

import android.content.Context;
import android.provider.ContactsContract;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserManager {

    public static UserManager instance;
    public Context context;
    private static ArrayList<User> users=new ArrayList<>();
    private UserManager(Context context){
      this.context=context;

        try {
            FileInputStream fis=context.openFileInput("users");
            ObjectInputStream ois=new ObjectInputStream(fis);
            users=(ArrayList<User>)ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static UserManager getInstance(Context context){

        if (instance==null)
            instance=new UserManager(context);
        return instance;

    }

public ArrayList<User> getUsers(){
        return users;

    }
    private void saveUser(){
        try {
            FileOutputStream fos = context.openFileOutput("users", context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void addUser(User user){

        users.add(user);
        saveUser();


    }
}
