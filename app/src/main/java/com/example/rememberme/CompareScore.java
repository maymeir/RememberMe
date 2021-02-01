package com.example.rememberme;

import java.util.Comparator;

public class CompareScore implements Comparator<Player> {
    public int compare(Player player1,Player player2) {

        long sc1 = player1.getScore();
        long sc2 = player2.getScore();

        if (sc1 > sc2){
            return -1;
        }else if (sc1 < sc2){
            return +1;
        }else{
            return 0;
        }
    }

}
