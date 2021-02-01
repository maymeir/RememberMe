package com.example.rememberme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecordsTable extends AppCompatActivity {

    HighScoreEasy highScoreEasy;
    HighScoreMedium highScoreMedium;
    HighScoreHard highScoreHard;
    ArrayList<Player> playersEasy;
    ArrayList<Player> playersMedium;
    ArrayList<Player> playersHard;
    private final int limit= 3;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.records_table);

        Button button= findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(RecordsTable.this, ChooseLevel.class);
                startActivity(intent);
                finish();
            }
        });


        highScoreEasy= HighScoreEasy.getInstanceEasy(this);
        highScoreMedium= HighScoreMedium.getInstanceMedium(this);
        highScoreHard= HighScoreHard.getInstanceHard(this);


        RecyclerView ViewEasy= findViewById(R.id.easy_rv);
        RecyclerView   ViewMedium= findViewById(R.id.medium_rv);
        RecyclerView  ViewHard= findViewById(R.id.hard_rv);

        ViewEasy.setHasFixedSize(true);
        ViewMedium.setHasFixedSize(true);
        ViewHard.setHasFixedSize(true);

        ViewEasy.setLayoutManager(new LinearLayoutManager(this));

        HighScoreEasy EasyManager = HighScoreEasy.getInstanceEasy(this);
        playersEasy=EasyManager.getHighScoreEasy();

        PlayerAdapter EasyAdapter = new PlayerAdapter(playersEasy);

        ViewEasy.setAdapter(EasyAdapter);



        ViewMedium.setLayoutManager(new LinearLayoutManager(this));

        HighScoreMedium MediumManager = HighScoreMedium.getInstanceMedium(this);

         playersMedium=MediumManager.getHighScoreMedium();
        PlayerAdapter MediumAdapter = new PlayerAdapter(playersMedium);

        ViewMedium.setAdapter(MediumAdapter);




        ViewHard.setLayoutManager(new LinearLayoutManager(this));

        HighScoreHard HardManager = HighScoreHard.getInstanceHard(this);
        playersHard=HardManager.getHighScoreHard();

        PlayerAdapter HardAdapter = new PlayerAdapter(playersHard);

        ViewHard.setAdapter(HardAdapter);

    }




}
