package com.kohei.ikegon.yakusokunohiwomouitido;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private final int FP = ViewGroup.LayoutParams.FILL_PARENT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = new FrameLayout(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        StoryView storyView = new StoryView(this);
        storyView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TopBar topBar = new TopBar(this);
        linearLayout.addView(topBar.getLinearLayout());
        linearLayout.addView(storyView);
        frameLayout.addView(linearLayout,new ViewGroup.LayoutParams(FP,FP));
        frameLayout.addView(new FunctionView(this).getFrameLayout(),new ViewGroup.LayoutParams(FP,FP));
        setContentView(frameLayout);
        //BGMの音量をボタンでコントロールできるようにする。
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

//        //ナビゲーションバーを隠す
//        View decor = this.getWindow().getDecorView();
//        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    @Override
    protected void onPause(){
        super.onPause();
        StoryView.stopMusic();
    }
    @Override
    protected void onStop(){
        super.onStop();
        StoryView.stopMusic();
    }
    @Override
    protected void onResume(){
        super.onResume();
        StoryView.playMusic();
//        finish();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        StoryView.stopMusic();
    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent keyEvent){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            //ダイアログを開き、終了するか否かを訊く
            //Log.d("KEY_EVENT","BACK");
            if(StoryView.getIsList()){
                FunctionView.getmyHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        FunctionView.setListView(false);
                    }
                });
            }
            return true;
        }
        return false;
    }
}

