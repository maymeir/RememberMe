package com.example.rememberme;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class SoundEffects {
    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX=2;
    private static SoundPool sounds;
    private static int winSound;
    private static int failSound;

    public SoundEffects(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            sounds = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes).setMaxStreams(SOUND_POOL_MAX)
                    .build();

        }else {
            sounds=new SoundPool(SOUND_POOL_MAX,AudioManager.STREAM_MUSIC,0);

        }

        sounds=new SoundPool(2,AudioManager.STREAM_MUSIC,0);
        winSound=sounds.load(context,R.raw.win,1);
        failSound=sounds.load(context,R.raw.fail,1);
    }
    public void playWinSound(){

        sounds.play(winSound,1.0f,1.0f,1,0,1.0f);

    }
    public void playFailSound(){
        sounds.play(failSound,1.0f,1.0f,1,0,1.0f);
    }
}

