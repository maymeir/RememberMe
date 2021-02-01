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

public class HighScoreHard {

    private Context context;

    private ArrayList<Player> players=new ArrayList<>();
    public static HighScoreHard instanceHard;




    private HighScoreHard(Context context) {
        this.context = context;

        try {
            FileInputStream fis = context.openFileInput("HighScoreHard");
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

    public static HighScoreHard getInstanceHard(Context context) {
        if(instanceHard==null)
            instanceHard = new HighScoreHard(context);

        return instanceHard;
    }



    public void addPlayerHard(Player player) {

        players.add(player);
        savePlayersHard();
    }


    private void savePlayersHard() {

        try {
            FileOutputStream fos = context.openFileOutput("HighScoreHard",Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(players);

            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Player> getHighScoreHard() {
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
