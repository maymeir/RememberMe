package com.example.rememberme;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private int numOfElements;
    private long score;
    private CardButton[] allButtons;
    private int[] allButtonsGraphicLocation; //random all the location
    private int[] allButtonsGraphics;
    private CardButton selectButton1;
    private CardButton selectButton2;
    private boolean isBusy = false;
    int category;
    private int level;
    private long numOfSeconds;
    private TextView name_tv;
    private EditText timer_et;
    private String userName;
    private int[] timerSeconds = {60, 100, 150};
    private Timer timer;
    int numCol;
    int numRow;
    long leftSeconds;
    ArrayList<Player> players=new ArrayList<Player>();
    Player player1;
    Player player2;
    Player player3;
    int flag=0;
    HighScoreEasy highScoreEasy;
    HighScoreMedium highScoreMedium;
    HighScoreHard highScoreHard;
    private SoundEffects sound;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.action_menu)
        {timer.cancel();
            Intent intent= new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            if (item.getItemId() == R.id.action_table) {
                timer.cancel();
                Intent intent1 = new Intent(GameActivity.this, RecordsTable.class);
                startActivity(intent1);
                finish();
            }

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        sound=new SoundEffects(GameActivity.this);


        highScoreEasy= HighScoreEasy.getInstanceEasy(GameActivity.this);
        highScoreMedium= HighScoreMedium.getInstanceMedium(GameActivity.this);
        highScoreHard= HighScoreHard.getInstanceHard(GameActivity.this);




        name_tv = (TextView) findViewById(R.id.userName);
        category = getIntent().getIntExtra("category", 0);
        timer_et = (EditText) findViewById(R.id.timer_et);
        level = getIntent().getIntExtra("level", 0);
        SharedPreferences sp=getSharedPreferences("INFO",MODE_PRIVATE);
        userName = sp.getString("name","");
        name_tv.setText(getString(R.string.name)+": "+userName);

        numOfSeconds = timerSeconds[level - 1];

        initForTimer(numOfSeconds + 1, timer_et);
        GridLayout theGridLayout = (GridLayout) findViewById(R.id.grid_layout_for_all);

        if (level == 1) {
            numCol = 3;
            numRow = 4;
            score=100;

        }
        if (level == 2) {
            numCol = 4;
            numRow = 5;
            score=300;
        }
        if (level == 3) {
            numCol = 5;
            numRow = 6;
            score= 500;
        }
        this.numOfElements = numCol * numRow;
        this.allButtons = new CardButton[numOfElements];
        this.allButtonsGraphics = new int[numOfElements / 2];
        if (numRow == 4) {
            if (category == 1) {
                putAllButtonsGraphicForEasyCar();
            }
            if (category == 2) {
                putAllButtonsGraphicForEasyAnimals();
            }
            if (category == 3) {
                putAllButtonsGraphicForEasyCartoons();
            }

        } else if (numRow == 5) {
            if (category == 1) {
                putAllButtonsGraphicForMediumCar();
            }
            if (category == 2) {
                putAllButtonsGraphicForMediumAnimals();
            }
            if (category == 3) {
                putAllButtonsGraphicForMediumCartoons();
            }

        } else {
            if (category == 1) {
                putAllButtonsGraphicForHardCar();
            }
            if (category == 2) {
                putAllButtonsGraphicForHardAnimals();
            }
            if (category == 3) {
                putAllButtonsGraphicForHardCartoons();
            }

        }
        this.allButtonsGraphicLocation = new int[numOfElements];
        shuffleButtonGraphics();
        initMemoryButtons(numRow, numCol, theGridLayout);


    }

    private void initForTimer(long numOfSeconds, EditText timerText) {
        timer = new Timer(numOfSeconds * 1000, 1000, timerText, this);
        timer.start();
    }


    public void initMemoryButtons(int numRow, int numCol, GridLayout theGridLayout) {
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                CardButton tempButton = new CardButton(GameActivity.this, row, col, allButtonsGraphics[allButtonsGraphicLocation[row * numCol + col]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                allButtons[row * numCol + col] = tempButton;
                theGridLayout.addView(tempButton);
            }
        }
    }

    protected void shuffleButtonGraphics() {
        Random rand = new Random();

        for (int i = 0; i < numOfElements; i++) {
            this.allButtonsGraphicLocation[i] = i % (numOfElements / 2);
        }
        for (int i = 0; i < numOfElements; i++) {//swap location
            int temp = this.allButtonsGraphicLocation[i];
            if (numOfElements == 12) {
                int swapIndex = rand.nextInt(12);
                allButtonsGraphicLocation[i] = allButtonsGraphicLocation[swapIndex];
                allButtonsGraphicLocation[swapIndex] = temp;
            } else if (numOfElements == 20) {
                int swapIndex = rand.nextInt(20);
                allButtonsGraphicLocation[i] = allButtonsGraphicLocation[swapIndex];
                allButtonsGraphicLocation[swapIndex] = temp;
            } else {
                int swapIndex = rand.nextInt(30);
                allButtonsGraphicLocation[i] = allButtonsGraphicLocation[swapIndex];
                allButtonsGraphicLocation[swapIndex] = temp;
            }

        }
    }

    public void onBackPressed() {
        timer.cancel();
        helperForMenu();
    }


    public void putAllButtonsGraphicForEasyCar() {
        this.allButtonsGraphics[0] = R.drawable.cars1;
        this.allButtonsGraphics[1] = R.drawable.cars2;
        this.allButtonsGraphics[2] = R.drawable.cars3;
        this.allButtonsGraphics[3] = R.drawable.cars4;
        this.allButtonsGraphics[4] = R.drawable.cars5;
        this.allButtonsGraphics[5] = R.drawable.cars6;

    }

    public void putAllButtonsGraphicForMediumCar() {
        this.allButtonsGraphics[0] = R.drawable.cars21;
        this.allButtonsGraphics[1] = R.drawable.cars22;
        this.allButtonsGraphics[2] = R.drawable.cars23;
        this.allButtonsGraphics[3] = R.drawable.cars24;
        this.allButtonsGraphics[4] = R.drawable.cars25;
        this.allButtonsGraphics[5] = R.drawable.cars26;
        this.allButtonsGraphics[6] = R.drawable.cars27;
        this.allButtonsGraphics[7] = R.drawable.cars28;
        this.allButtonsGraphics[8] = R.drawable.cars29;
        this.allButtonsGraphics[9] = R.drawable.cars210;


    }

    public void putAllButtonsGraphicForHardCar() {
        this.allButtonsGraphics[0] = R.drawable.cars31;
        this.allButtonsGraphics[1] = R.drawable.cars32;
        this.allButtonsGraphics[2] = R.drawable.cars33;
        this.allButtonsGraphics[3] = R.drawable.cars34;
        this.allButtonsGraphics[4] = R.drawable.cars35;
        this.allButtonsGraphics[5] = R.drawable.cars36;
        this.allButtonsGraphics[6] = R.drawable.cars37;
        this.allButtonsGraphics[7] = R.drawable.cars38;
        this.allButtonsGraphics[8] = R.drawable.cars39;
        this.allButtonsGraphics[9] = R.drawable.cars310;
        this.allButtonsGraphics[10] = R.drawable.cars311;
        this.allButtonsGraphics[11] = R.drawable.cars312;
        this.allButtonsGraphics[12] = R.drawable.cars313;
        this.allButtonsGraphics[13] = R.drawable.cars314;
        this.allButtonsGraphics[14] = R.drawable.cars315;

    }

    public void putAllButtonsGraphicForEasyAnimals() {
        this.allButtonsGraphics[0] = R.drawable.animals1;
        this.allButtonsGraphics[1] = R.drawable.animals2;
        this.allButtonsGraphics[2] = R.drawable.animals3;
        this.allButtonsGraphics[3] = R.drawable.animals4;
        this.allButtonsGraphics[4] = R.drawable.animals5;
        this.allButtonsGraphics[5] = R.drawable.animals6;

    }

    public void putAllButtonsGraphicForMediumAnimals() {
        this.allButtonsGraphics[0] = R.drawable.animals21;
        this.allButtonsGraphics[1] = R.drawable.animals22;
        this.allButtonsGraphics[2] = R.drawable.animals23;
        this.allButtonsGraphics[3] = R.drawable.animals24;
        this.allButtonsGraphics[4] = R.drawable.animals25;
        this.allButtonsGraphics[5] = R.drawable.animals26;
        this.allButtonsGraphics[6] = R.drawable.animals27;
        this.allButtonsGraphics[7] = R.drawable.animals28;
        this.allButtonsGraphics[8] = R.drawable.animals29;
        this.allButtonsGraphics[9] = R.drawable.animals210;

    }

    public void putAllButtonsGraphicForHardAnimals() {
        this.allButtonsGraphics[0] = R.drawable.animals31;
        this.allButtonsGraphics[1] = R.drawable.animals32;
        this.allButtonsGraphics[2] = R.drawable.animals33;
        this.allButtonsGraphics[3] = R.drawable.animals34;
        this.allButtonsGraphics[4] = R.drawable.animals35;
        this.allButtonsGraphics[5] = R.drawable.animals36;
        this.allButtonsGraphics[6] = R.drawable.animals37;
        this.allButtonsGraphics[7] = R.drawable.animals38;
        this.allButtonsGraphics[8] = R.drawable.animals39;
        this.allButtonsGraphics[9] = R.drawable.animals310;
        this.allButtonsGraphics[10] = R.drawable.animals311;
        this.allButtonsGraphics[11] = R.drawable.animals312;
        this.allButtonsGraphics[12] = R.drawable.animals313;
        this.allButtonsGraphics[13] = R.drawable.animals314;
        this.allButtonsGraphics[14] = R.drawable.animals315;

    }

    public void putAllButtonsGraphicForEasyCartoons() {
        this.allButtonsGraphics[0] = R.drawable.cartoons1;
        this.allButtonsGraphics[1] = R.drawable.cartoons2;
        this.allButtonsGraphics[2] = R.drawable.cartoons3;
        this.allButtonsGraphics[3] = R.drawable.cartoons4;
        this.allButtonsGraphics[4] = R.drawable.cartoons5;
        this.allButtonsGraphics[5] = R.drawable.cartoons6;

    }

    public void putAllButtonsGraphicForMediumCartoons() {
        this.allButtonsGraphics[0] = R.drawable.cartoons21;
        this.allButtonsGraphics[1] = R.drawable.cartoons22;
        this.allButtonsGraphics[2] = R.drawable.cartoons23;
        this.allButtonsGraphics[3] = R.drawable.cartoons24;
        this.allButtonsGraphics[4] = R.drawable.cartoons25;
        this.allButtonsGraphics[5] = R.drawable.cartoons26;
        this.allButtonsGraphics[6] = R.drawable.cartoons27;
        this.allButtonsGraphics[7] = R.drawable.cartoons28;
        this.allButtonsGraphics[8] = R.drawable.cartoons29;
        this.allButtonsGraphics[9] = R.drawable.cartoons210;


    }

    public void putAllButtonsGraphicForHardCartoons() {

        this.allButtonsGraphics[0] = R.drawable.cartoons31;
        this.allButtonsGraphics[1] = R.drawable.cartoons32;
        this.allButtonsGraphics[2] = R.drawable.cartoons33;
        this.allButtonsGraphics[3] = R.drawable.cartoons34;
        this.allButtonsGraphics[4] = R.drawable.cartoons35;
        this.allButtonsGraphics[5] = R.drawable.cartoons36;
        this.allButtonsGraphics[6] = R.drawable.cartoons37;
        this.allButtonsGraphics[7] = R.drawable.cartoons38;
        this.allButtonsGraphics[8] = R.drawable.cartoons39;
        this.allButtonsGraphics[9] = R.drawable.cartoons310;
        this.allButtonsGraphics[10] = R.drawable.cartoons311;
        this.allButtonsGraphics[11] = R.drawable.cartoons312;
        this.allButtonsGraphics[12] = R.drawable.cartoons313;
        this.allButtonsGraphics[13] = R.drawable.cartoons314;
        this.allButtonsGraphics[14] = R.drawable.cartoons315;

    }

    private boolean checkIfDone() {
        for (int i = 0; i < numOfElements; i++) {
            if (allButtons[i].isEnabled()) {
                return false;
            }
        }
        return true;
    }



    public void timeOutForGame() {
        sound.playFailSound();
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        View dialogView= getLayoutInflater().inflate(R.layout.dialog_game_over, null);

        EditText currentScore= dialogView.findViewById(R.id.current_score);
        long currentScoreString=leftSeconds+score;
        currentScore.setText(currentScoreString+"");


        ImageView imageView= dialogView.findViewById(R.id.animation_game_over);
        AnimationDrawable animationDrawable = (AnimationDrawable)imageView.getDrawable();

        animationDrawable.start();
        builder.setTitle("Game over!").setCancelable(true).setCancelable(true).setNegativeButton("Records Table", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(GameActivity.this, RecordsTable.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setView(dialogView).setPositiveButton("Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(GameActivity.this, ChooseLevel.class);
                startActivity(intent);
                finish();
            }
        }).show();




    }

    private void helperForMenu() {
        Intent intent = new Intent(GameActivity.this, ChooseLevel.class);
        intent.putExtra("name", userName);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        if (isBusy) {
            return;
        }
        CardButton button = (CardButton) view;
        if (button.isMatched) {
            return;
        }
        if (selectButton1 == null) {
            selectButton1 = button;
            selectButton1.flip();
            return;
        }
        if (selectButton1.getId() == button.getId()) {
            return;
        }
        if (selectButton1.getFrontImageID() == button.getFrontImageID()) {
            button.flip();
            button.setMatched(true);
            selectButton1.setMatched(true);

            selectButton1.setEnabled(false);
            button.setEnabled(false);
            selectButton1 = null;

            if (checkIfDone()) {

                flag=0;


                Player player = new Player(userName, score + leftSeconds);

                if (level == 1) {
                    players=highScoreEasy.getHighScoreEasy();
                    player1=players.get(0);
                    player2=players.get(1);
                    player3=players.get(2);
                    if (player.getScore()>player2.getScore()||player.getScore()>player3.getScore()){
                        if (player.getScore()>player1.getScore()){
                            highScoreEasy.addPlayerEasy(player);
                            sound.playWinSound();
                            //send to win first
                            firstInTable();
                        }
                        else {
                            highScoreEasy.addPlayerEasy(player);
                            //sent to win 2 or 3
                            sound.playWinSound();
                            getIntoTable();
                        }
                    }
                    else
                    {
                        //send to win but not high
                        sound.playWinSound();
                        justWin();
                    }

                }
                if (level == 2) {
                    players=highScoreMedium.getHighScoreMedium();
                    player1=players.get(0);
                    player2=players.get(1);
                    player3=players.get(2);

                    if (player.getScore()>player2.getScore()||player.getScore()>player3.getScore()){
                        if (player.getScore()>player1.getScore()){
                            highScoreMedium.addPlayerMedium(player);
                            //send to win first
                            sound.playWinSound();
                            firstInTable();
                        }
                        else {
                            highScoreMedium.addPlayerMedium(player);
                            //sent to win 2 or 3
                            sound.playWinSound();
                            getIntoTable();
                        }
                    }
                    else
                    {
                        //send to win but not high
                        sound.playWinSound();
                        justWin();
                    }




                }
                if (level == 3) {
                    players=highScoreHard.getHighScoreHard();
                    player1=players.get(0);
                    player2=players.get(1);
                    player3=players.get(2);
                    if (player.getScore()>player2.getScore()||player.getScore()>player3.getScore()){
                        if (player.getScore()>player1.getScore()){
                            highScoreHard.addPlayerHard(player);
                            //send to win first
                            sound.playWinSound();
                            firstInTable();
                        }
                        else {
                            highScoreHard.addPlayerHard(player);
                            //sent to win 2 or 3
                            sound.playWinSound();
                            getIntoTable();
                        }
                    }
                    else
                    {
                        //send to win but not high
                        sound.playWinSound();
                        justWin();
                    }


                }





            }

        } else {// are not the same
            selectButton2 = button;
            selectButton2.flip();
            isBusy = true;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectButton2.flip();
                    selectButton1.flip();
                    selectButton1 = null;
                    selectButton2 = null;
                    isBusy = false;
                }
            }, 500);
        }
    }

    public void justWin(){

        timer.cancel();

        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        View dialogView= getLayoutInflater().inflate(R.layout.dialog_win, null);

        EditText currentScore= dialogView.findViewById(R.id.current_score);
        long currentScoreString=leftSeconds+score;
        currentScore.setText(currentScoreString+"");

        ImageView imageView= dialogView.findViewById(R.id.welldone);
        AnimationDrawable animationDrawable = (AnimationDrawable)imageView.getDrawable();

        animationDrawable.start();
        builder.setTitle("Winning!").setCancelable(true).setNegativeButton("Records Table", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(GameActivity.this, RecordsTable.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setView(dialogView).setPositiveButton("Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(GameActivity.this, ChooseLevel.class);
                startActivity(intent);
                finish();
            }
        }).show();



    }

    public void firstInTable(){
        timer.cancel();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(GameActivity.this);
        View dialogView1= getLayoutInflater().inflate(R.layout.dialog_first_in_table, null);

        EditText currentScore1= dialogView1.findViewById(R.id.current_score);
        long currentScoreString1=leftSeconds+score;
        currentScore1.setText(currentScoreString1+"");

        ImageView imageView1= dialogView1.findViewById(R.id.animation_win);
        AnimationDrawable animationDrawable1 = (AnimationDrawable)imageView1.getDrawable();

        animationDrawable1.start();
        builder1.setTitle("Winning!").setCancelable(true).setNegativeButton("Records Table", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(GameActivity.this, RecordsTable.class);
                startActivity(intent);
                finish();
            }
        });

        builder1.setView(dialogView1).setPositiveButton("Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(GameActivity.this, ChooseLevel.class);
                startActivity(intent);
                finish();
            }
        }).show();


    }


    public void getIntoTable(){
        timer.cancel();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(GameActivity.this);
        View dialogView1= getLayoutInflater().inflate(R.layout.dialog_into_table, null);

        EditText currentScore1= dialogView1.findViewById(R.id.current_score);
        long currentScoreString1=leftSeconds+score;
        currentScore1.setText(currentScoreString1+"");

        ImageView imageView1= dialogView1.findViewById(R.id.getinto_anim);
        AnimationDrawable animationDrawable1 = (AnimationDrawable)imageView1.getDrawable();

        animationDrawable1.start();
        builder1.setTitle("Winning!").setCancelable(true).setNegativeButton("Records Table", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(GameActivity.this, RecordsTable.class);
                startActivity(intent);
                finish();
            }
        });

        builder1.setView(dialogView1).setPositiveButton("Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent= new Intent(GameActivity.this, ChooseLevel.class);
                startActivity(intent);
                finish();
            }
        }).show();


    }



    public void onCountDownTimerTickEvent(long millisUntilFinished)
    {
        // Calculate left seconds.
         leftSeconds = millisUntilFinished / 1000;


    }




}



