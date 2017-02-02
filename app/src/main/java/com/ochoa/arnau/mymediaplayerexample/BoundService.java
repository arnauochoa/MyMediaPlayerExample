package com.ochoa.arnau.mymediaplayerexample;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

public class BoundService extends Service {

    private final IBinder binder = new MyBinder();

    MediaPlayer mediaPlayer;

    public class MyBinder extends Binder{
        BoundService getService() {
            return BoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        mediaPlayer = MediaPlayer.create(this, R.raw.layla);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });
        return binder;
    }

    public void play(){
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    public void pause(){
        mediaPlayer.pause();
    }

    public void stop(){
        mediaPlayer.stop();
    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
