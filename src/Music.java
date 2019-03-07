package com.kohei.ikegon.yakusokunohiwomouitido;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;
    private Context context;
    private int musicID = 0;
    private static int resId = 0;
    private boolean isStoryMusic = false;
    private boolean threadJudge = true;
    private static boolean confBGM = true;//TitleViewから中の値をもってくる
    public Music(Context context){
        this.context = context;
        confBGM = TitleActivity.getConfBGM();
    }
    void mediaPlayerLooper(){
        if(mediaPlayer1 != null){
            mediaPlayer1.stop();
            mediaPlayer1.release();
            mediaPlayer2.release();
            mediaPlayer1 = null;
            mediaPlayer2 = null;
        }
        mediaPlayer1 = MediaPlayer.create(context,resId);
        mediaPlayer1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                try{
                    mediaPlayer1.start();
                }catch (NullPointerException e){
                    //Log.d("Music","mediaplayer.start");
                }
            }
        });
        createNextMediaPlayer();
    }
    private void createNextMediaPlayer(){
        try{
            mediaPlayer2 = MediaPlayer.create(context,resId);
            mediaPlayer1.setNextMediaPlayer(mediaPlayer2);
        }catch (IllegalStateException e) {
            mediaPlayer1.release();
            mediaPlayer2.release();
            mediaPlayer1 = null;
            mediaPlayer2 = null;
        }catch (NullPointerException e){
        }
        try{
            mediaPlayer1.setOnCompletionListener(onCompletionListener);
        }catch (NullPointerException e){
            //Log.d("Music","Cached NullPointerException on createNextMediaPlayer");
            mediaPlayer1 = null;
            mediaPlayer2 = null;
            mediaPlayerLooper();
        }
    }
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mp.release();
            mediaPlayer1 = mediaPlayer2;

            createNextMediaPlayer();
        }
    };
    public void play(){
        if(confBGM && mediaPlayer1 == null){
            switch (musicID){
                case 0:
                    try{
                        resId = R.raw.mm1;//base
                        mediaPlayerLooper();
                    }catch (NullPointerException e){
                        //Log.e("Music","mediaPayer");
                    }
                    break;
                case 1:
                    try{
                        resId = R.raw.mihon8;//池の奧
                        mediaPlayerLooper();
                    }catch (NullPointerException e){
                        //Log.e("Music","mediaPayer");
                    }
                    break;
                case 2:
                    try{
                        resId = R.raw.main;//河童がでてるとき
                        mediaPlayerLooper();
                    }catch (NullPointerException e){
                        //Log.e("Music","mediaPayer");
                    }
                    break;
                case 3:
                    try{
                        resId = R.raw.code2c;//いのししと話し合い
                        mediaPlayerLooper();
                    }catch (NullPointerException e){
                        //Log.e("Music","mediaPayer");
                    }
                    break;
                case 4:
                    try{
                        resId = R.raw.code3c;//子供とはなす
                        mediaPlayerLooper();
                    }catch (NullPointerException e){
                        //Log.e("Music","mediaPayer");
                    }
                    break;
                case 5:
                    try{
                        resId = R.raw.general2;//ending1
                        mediaPlayerLooper();
                    }catch (NullPointerException e){
                        //Log.e("Music","mediaPayer");
                    }
                    break;
                case 6:
                    try{
                        resId = R.raw.canoncode;//ending2
                        mediaPlayerLooper();
                    }catch (NullPointerException e){
                        //Log.e("Music","mediaPayer");
                    }
                    break;
            }
            try{
                mediaPlayer1.setVolume(100,100);
            }catch (NullPointerException e){
                //Log.e("Music","mediaPayer");
            }
            StoryView.setMusicInitialized(true);
        }
    }
    public void stop(){
        threadJudge = false;
        try{
            mediaPlayer1.stop();
            mediaPlayer1.release();
            mediaPlayer2.release();
            mediaPlayer1 = null;
            mediaPlayer2 = null;

        }catch (NullPointerException e){
            //Log.e("Music",String.valueOf(e));
        }catch (IllegalStateException e){
            mediaPlayer1.release();
            mediaPlayer2.release();
            mediaPlayer1 = null;
            mediaPlayer2 = null;
        }
    }
    public boolean isPlaying(){
        boolean bool = true;
        if(mediaPlayer1 != null){
            try{
                bool = mediaPlayer1.isPlaying();
            }catch (IllegalStateException e){
            }
        }else{
            bool = true;
        }
        return bool;
    }
    public void setMusicID(int musicID){
        this.musicID = musicID;
    }
    public int getMusicID(){
        return musicID;
    }
    public void setIsStoryMusic(boolean bool){
        isStoryMusic = bool;
    }
    public boolean getIsStoryMusic(){
        return isStoryMusic;
    }
}
