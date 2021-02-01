package com.example.rememberme;

import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.TextView;

public class Timer extends CountDownTimer  {

    TextView timerText;
    GameActivity theActivity;


    public Timer(long numOfSeconds, long interval, EditText text, GameActivity theActivity) {
        super(numOfSeconds, interval);
        this.timerText = text;
        this.theActivity = theActivity;
    }

    public void onTick(long millisUntilFinished) {
        timerText.setText(millisUntilFinished / 1000+"");
        this.theActivity.onCountDownTimerTickEvent(millisUntilFinished);

    }

    public void onFinish() {
        timerText.setText( 0+"" );
        theActivity.timeOutForGame();

    }



}
