package com.example.rememberme;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.GridLayoutAnimationController;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class CardButton extends AppCompatButton {

    protected int row;
    protected int col;
    protected int frontImageID;



    protected boolean isFlipped = false;
    protected boolean isMatched = false;

    protected Drawable front;
    protected Drawable back;

    public CardButton(Context context, int row, int col, int frontImageID ){
        super(context);

        this.row = row;
        this.col = col;
        this.frontImageID = frontImageID;


        front = context.getResources().getDrawable(frontImageID);
        back = context.getResources().getDrawable(R.drawable.back);

        setBackground(back);
        GridLayout.LayoutParams tempParams = new GridLayout.LayoutParams(GridLayout.spec(row),GridLayout.spec(col));

        tempParams.width = (int) getResources().getDisplayMetrics().density * 65;
        tempParams.height = (int) getResources().getDisplayMetrics().density * 65;

        setLayoutParams(tempParams);



    }
    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public int getFrontImageID() {
        return frontImageID;
    }

    public void flip(){
        if(isMatched){
            return;
        }

        if(isFlipped){

           setBackground(back);
            isFlipped = false;
        }else{

            setBackground(front);
            isFlipped = true;
        }
    }



}
