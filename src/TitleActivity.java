package com.kohei.ikegon.yakusokunohiwomouitido;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.lang.reflect.Method;

/**
 * Created by ikego on 2018/02/10.
 */

public class TitleActivity extends AppCompatActivity {

    private static Point real;
    private FrameLayout frameLayout;
    private TextView textView1;
    private TextView textView2;
    private TextView configText;
    private TextView volumeText;
    private TextView credit;
    private TextView creditTitle;
    private TextView creditClose;
    private TextView creditValue;
    private ScrollView scrollView;
    private LinearLayout layoutForCredit;
    private static boolean isList = false;
    private static boolean cancel = false;
    private static boolean confBGM = true;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        real = getRealSize();
        frameLayout = new FrameLayout(this);
        TitleView titleView = new TitleView(this,real);
        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.MATCH_PARENT);
        params1.gravity = Gravity.CENTER;
        params2.gravity = Gravity.CENTER;

        frameLayout.addView(titleView,params1);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.BOTTOM);
        linearLayout.setPadding(0,0,0,(int)(TitleView.getReal_height()/20));
        final LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        linearLayout2.setPadding(0,(int)(TitleView.getReal_height()/1.6),0,0);
        linearLayout2.setGravity(Gravity.RIGHT);
        final LinearLayout confLayout = new LinearLayout(this);
        final LinearLayout confSubLayout = new LinearLayout(this);
        confLayout.setOrientation(LinearLayout.VERTICAL);
        confLayout.setGravity(Gravity.CENTER);
        confLayout.setBackgroundColor(Color.BLACK);
        confLayout.setAlpha(0.8f);
        confSubLayout.setOrientation(LinearLayout.HORIZONTAL);
        confSubLayout.setGravity(Gravity.CENTER);
        confLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        confSubLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView1 = new TextView(this);
        textView2 = new TextView(this);
        configText = new TextView(this);
        volumeText = new TextView(this);
        textView1.setPadding(0,0,0,(int)(TitleView.getReal_height()/80));
        textView2.setPadding(0,(int)(TitleView.getReal_height()/80),0,(int)(TitleView.getReal_height()/80));
        textView1.setTextSize(TitleView.getReal_height()/60);
        textView2.setTextSize(TitleView.getReal_height()/60);
        configText.setTextSize(TitleView.getReal_height()/90);
        volumeText.setTextSize(TitleView.getReal_height()/90);
        textView1.setText("はじめから");
        textView1.setTextColor(Color.WHITE);
        textView2.setText("つづきから");
        textView2.setTextColor(Color.WHITE);
        configText.setText("設定");
        configText.setTextColor(Color.WHITE);
        volumeText.setText("音有り");
        volumeText.setTextColor(Color.WHITE);
        volumeText.setPadding(0,0,(int)(TitleView.getReal_height()/20),0);
        configText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        volumeText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(textView1);
        linearLayout.addView(textView2);
        linearLayout2.addView(configText);

        credit = new TextView(this);
        creditTitle = new TextView(this);
        creditClose = new TextView(this);
        creditValue = new TextView(this);
        scrollView = new ScrollView(this);
        layoutForCredit = new LinearLayout(this);
        layoutForCredit.setOrientation(LinearLayout.VERTICAL);
        layoutForCredit.setGravity(Gravity.CENTER);
        layoutForCredit.setBackgroundColor(Color.BLACK);
        layoutForCredit.setAlpha(0.8f);
        credit.setText("クレジット");
        credit.setPadding((int)(TitleView.getReal_height()/20),0,0,0);
        credit.setTextColor(Color.WHITE);
        creditTitle.setText("クレジット");
        creditClose.setText("もどる");
        creditValue.setText(R.string.credit);
        credit.setTextSize(TitleView.getReal_height()/90);
        creditTitle.setTextSize(TitleView.getReal_height()/60);
        creditClose.setTextSize(TitleView.getReal_height()/75);
        creditValue.setTextSize(TitleView.getReal_height()/85);
        creditTitle.setTextColor(Color.WHITE);
        creditClose.setTextColor(Color.CYAN);
        creditValue.setTextColor(Color.WHITE);
        scrollView.addView(creditValue);
        scrollView.setLayoutParams(new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        layoutForCredit.addView(creditTitle);
        layoutForCredit.addView(creditClose);
        layoutForCredit.addView(scrollView);
        layoutForCredit.setVisibility(View.INVISIBLE);
        confSubLayout.addView(volumeText);
        confSubLayout.addView(credit);
        confLayout.addView(confSubLayout);
        confLayout.setVisibility(View.INVISIBLE);
        frameLayout.addView(linearLayout2);
        frameLayout.addView(linearLayout,params2);
        frameLayout.addView(layoutForCredit);
        frameLayout.addView(confLayout);

        textView1.setOnTouchListener(new TextView.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        cancel = true;
                        return false;
                    case MotionEvent.ACTION_CANCEL:
                        cancel = true;
                        return true;
                    case MotionEvent.ACTION_UP:
                        if(!cancel){
                            textView1.setBackgroundColor(Color.GRAY);
                            TitleView.setIsLoadFromTitleView(false);
                            Intent intent = new Intent(textView1.getContext(),MainActivity.class);
                            textView1.getContext().startActivity(intent);
                        }else{
                            cancel = false;
                        }
                        break;
                }
                return true;
            }
        });
        textView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        cancel = true;
                        return false;
                    case MotionEvent.ACTION_CANCEL:
                        cancel = true;
                        break;
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        if(!cancel){
                            isList = true;
                            textView2.setBackgroundColor(Color.GRAY);
                            TitleView.setIsLoadFromTitleView(true);
                            Intent intent = new Intent(textView2.getContext(),MainActivity.class);
                            textView2.getContext().startActivity(intent);
                        }else{
                            cancel = false;
                        }
                        break;
                }
                return true;
            }
        });
        configText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                confLayout.setVisibility(View.VISIBLE);
                return true;
            }
        });
        linearLayout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        volumeText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        cancel = true;
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        cancel = true;
                        break;
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        if(!cancel){
                            if(!confBGM){
                                //音楽を鳴らし続ける
                                volumeText.setText("音有り");
                                confBGM = true;
                            }else{
                                //音楽を止める。
                                volumeText.setText("音無し");
                                confBGM = false;
                            }
                        }else{
                            cancel = false;
                        }
                        break;
                }
                return true;
            }
        });
        credit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                confLayout.setVisibility(View.INVISIBLE);
                layoutForCredit.setVisibility(View.VISIBLE);
                return true;
            }
        });
        creditClose.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                layoutForCredit.setVisibility(View.INVISIBLE);
                return true;
            }
        });
        confLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                confLayout.setVisibility(View.INVISIBLE);
                return true;
            }
        });
        layoutForCredit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        setContentView(frameLayout);
//        //ナビゲーションバーを隠す
//        View decor = this.getWindow().getDecorView();
//        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    public static void setIsList(boolean bool){
        isList = bool;
    }
    public static boolean getIsList(){
        return isList;
    }
    public static boolean getConfBGM(){
        return confBGM;
    }
    public static void setConfBGM(boolean bool){
        confBGM = bool;
    }
    @Override
    protected void onStop(){
        super.onStop();
        //Log.d("TAG","########### Title onStop #############");
        finish();
    }
    @Override
    protected void onPause(){
        super.onPause();
        //Log.d("TAG","########### Title onPause #############");
        finish();
    }

    private Point getRealSize() {

        Display display = getWindowManager().getDefaultDisplay();
        Point real = new Point(0, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // Android 4.2以上
            display.getRealSize(real);
            return real;

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            // Android 3.2以上
            try {
                Method getRawWidth = Display.class.getMethod("getRawWidth");
                Method getRawHeight = Display.class.getMethod("getRawHeight");
                int width = (Integer) getRawWidth.invoke(display);
                int height = (Integer) getRawHeight.invoke(display);
                real.set(width, height);
                return real;

            } catch (Exception e) {
                //Log.e("EXCEPTION","######### " +e+" #############");
            }
        }

        return real;
    }
}
