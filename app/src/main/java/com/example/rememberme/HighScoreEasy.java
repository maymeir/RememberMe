package com.example.rememberme;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class HighScoreEasy {


private Context context;

private ArrayList<Player> players=new ArrayList<>();
public static HighScoreEasy instanceEasy;
final String FileName="HighScoreEasy";



 private HighScoreEasy(Context context) {
        this.context = context;

        try {
            FileInputStream fis = context.openFileInput(FileName);
            ObjectInputStream ois  = new ObjectInputStream(fis);
            players = (ArrayList<Player>)ois.readObject();

            ois.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static HighScoreEasy getInstanceEasy(Context context) {
        if(instanceEasy==null)
            instanceEasy = new HighScoreEasy(context);

        return instanceEasy;
    }



    public void addPlayerEasy(Player player) {

        players.add(player);
        savePlayersEasy();
    }


    private void savePlayersEasy() {

        try {
            FileOutputStream fos = context.openFileOutput(FileName,Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(players);

            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Player> getHighScoreEasy() {
     if(players.size()<3){
         Player player=new Player("",0);
         players.add(player);
         players.add(player);
         players.add(player);
     }
        sort();
        return players;
    }

    private void sort() {
        CompareScore comparator = new CompareScore();
        Collections.sort(players, comparator);
    }

}
