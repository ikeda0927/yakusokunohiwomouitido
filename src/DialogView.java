package com.kohei.ikegon.yakusokunohiwomouitido;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getDiaog;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getW_unit;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setHouse;

/**
 * Created by ikego on 2018/04/02.
 */

public class DialogView extends View {
    private static LinearLayout linearLayout;
    private TextView textView1;
    private TextView textView3;
    private TextView textView5;
    private TextView textView7;
    private TextView textView9;
    private TextView textView_go;
    private static TextView textView;
    private TextView textView2;
    private TextView textView4;
    private TextView textView6;
    private TextView textView8;
    private TextView textView0;
    private TextView textView_back;

    //    private GridLayout gridLayout;
    private LinearLayout row1;
    private LinearLayout row2;
    private LinearLayout row3;
    private LinearLayout row4;

    private static String s = "";

    public DialogView(final Context context) {
        super(context);

        linearLayout = new LinearLayout(context);

        row1 = new LinearLayout(context);
        row2 = new LinearLayout(context);
        row3 = new LinearLayout(context);
        row4 = new LinearLayout(context);

        textView1 = new TextView(context);
        textView3 = new TextView(context);
        textView5 = new TextView(context);
        textView7 = new TextView(context);
        textView9 = new TextView(context);
        textView_go = new TextView(context);
        textView = new TextView(context);
        textView2 = new TextView(context);
        textView4 = new TextView(context);
        textView6 = new TextView(context);
        textView8 = new TextView(context);
        textView0 = new TextView(context);
        textView_back = new TextView(context);

        textView.setText(s);
        textView0.setText("0");
        textView1.setText("1");
        textView2.setText("2");
        textView3.setText("3");
        textView4.setText("4");
        textView5.setText("5");
        textView6.setText("6");
        textView7.setText("7");
        textView9.setText("9");
        textView8.setText("8");
        textView_go.setText("go");
        textView_back.setText("back");

        textView.setTextSize(40f);
        textView0.setTextSize(40f);
        textView1.setTextSize(40f);
        textView2.setTextSize(40f);
        textView3.setTextSize(40f);
        textView4.setTextSize(40f);
        textView5.setTextSize(40f);
        textView6.setTextSize(40f);
        textView7.setTextSize(40f);
        textView8.setTextSize(40f);
        textView9.setTextSize(40f);
        textView_go.setTextSize(40f);
        textView_back.setTextSize(35f);

        textView.setMinimumWidth((int)getW_unit()*2);
        textView0.setMinimumWidth((int)getW_unit()*2);
        textView1.setMinimumWidth((int)getW_unit()*2);
        textView2.setMinimumWidth((int)getW_unit()*2);
        textView3.setMinimumWidth((int)getW_unit()*2);
        textView4.setMinimumWidth((int)getW_unit()*2);
        textView5.setMinimumWidth((int)getW_unit()*2);
        textView6.setMinimumWidth((int)getW_unit()*2);
        textView7.setMinimumWidth((int)getW_unit()*2);
        textView8.setMinimumWidth((int)getW_unit()*2);
        textView9.setMinimumWidth((int)getW_unit()*2);
        textView_go.setMinimumWidth((int)getW_unit()*2);
        textView_back.setMinimumWidth((int)getW_unit()*2);

        textView.setGravity(Gravity.CENTER);
        textView0.setGravity(Gravity.CENTER);
        textView1.setGravity(Gravity.CENTER);
        textView2.setGravity(Gravity.CENTER);
        textView3.setGravity(Gravity.CENTER);
        textView4.setGravity(Gravity.CENTER);
        textView5.setGravity(Gravity.CENTER);
        textView6.setGravity(Gravity.CENTER);
        textView7.setGravity(Gravity.CENTER);
        textView8.setGravity(Gravity.CENTER);
        textView9.setGravity(Gravity.CENTER);
        textView_go.setGravity(Gravity.CENTER);
        textView_back.setGravity(Gravity.CENTER);

        textView.setPadding(10,5,10,10);
        textView0.setPadding(10,5,10,5);
        textView1.setPadding(10,5,10,5);
        textView2.setPadding(10,5,10,5);
        textView3.setPadding(10,5,10,5);
        textView4.setPadding(10,5,10,5);
        textView5.setPadding(10,5,10,5);
        textView6.setPadding(10,5,10,5);
        textView7.setPadding(10,5,10,5);
        textView8.setPadding(10,5,10,5);
        textView9.setPadding(10,5,10,5);
        textView_go.setPadding(10,5,10,5);
        textView_back.setPadding(10,5,10,5);

//        textView.setTextColor(Color.WHITE);
        textView0.setTextColor(Color.WHITE);
        textView1.setTextColor(Color.WHITE);
        textView3.setTextColor(Color.WHITE);
        textView5.setTextColor(Color.WHITE);
        textView7.setTextColor(Color.WHITE);
        textView9.setTextColor(Color.WHITE);

        textView0.setBackgroundColor(Color.BLACK);
        textView1.setBackgroundColor(Color.BLACK);
        textView3.setBackgroundColor(Color.BLACK);
        textView5.setBackgroundColor(Color.BLACK);
        textView7.setBackgroundColor(Color.BLACK);
        textView9.setBackgroundColor(Color.BLACK);

        textView0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.length()<4){
                    s += "0";
                    textView.setText(s);
                }
            }
        });
        textView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.length()<4){
                    s += "1";
                    textView.setText(s);
                }
            }
        });
        textView2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.length()<4){
                    s += "2";
                    textView.setText(s);
                }
            }
        });
        textView3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.length()<4){
                    s += "3";
                    textView.setText(s);
                }
            }
        });
        textView4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.length()<4){
                    s += "4";
                    textView.setText(s);
                }
            }
        });
        textView5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.length()<4){
                    s += "5";
                    textView.setText(s);
                }
            }
        });
        textView6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.length()<4){
                    s += "6";
                    textView.setText(s);
                }
            }
        });
        textView7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.length()<4){
                    s += "7";
                    textView.setText(s);
                }
            }
        });
        textView8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.length()<4){
                    s += "8";
                    textView.setText(s);
                }
            }
        });
        textView9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.length()<4){
                    s += "9";
                    textView.setText(s);
                }
            }
        });
        textView_go.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.equals("7532")){
                    //Log.d("lock","OK : "+s);
                    setHouse(true);
                    FunctionView.getmyHandler().post(new Runnable() {//母
                        @Override
                        public void run() {
                            FunctionView.getTextView().setText("鍵の開いた音がした。");
                            FunctionView.getTextView().setVisibility(VISIBLE);
                            StoryView.setFunctionViewContentChanged(true);
                        }
                    });
                    getDiaog().dismiss();

                }else{
                    Log.d("lock","NG : "+s);
                    s = "";
                    textView.setText(s);
                }
            }
        });
        textView_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(s.length() == 0){
                        getDiaog().dismiss();
                    }
                    s = s.substring(0,s.length()-1);
                    textView.setText(s);
                }catch (StringIndexOutOfBoundsException e){
                }
            }
        });


        linearLayout.setBackgroundColor(Color.WHITE);

        row1.setOrientation(LinearLayout.HORIZONTAL);
        row2.setOrientation(LinearLayout.HORIZONTAL);
        row3.setOrientation(LinearLayout.HORIZONTAL);
        row4.setOrientation(LinearLayout.HORIZONTAL);


        row1.addView(textView1);
        row1.addView(textView2);
        row1.addView(textView3);
        row2.addView(textView4);
        row2.addView(textView5);
        row2.addView(textView6);
        row3.addView(textView7);
        row3.addView(textView8);
        row3.addView(textView9);
        row4.addView(textView_back);
        row4.addView(textView0);
        row4.addView(textView_go);

        linearLayout.addView(textView);
        linearLayout.addView(row1);
        linearLayout.addView(row2);
        linearLayout.addView(row3);
        linearLayout.addView(row4);

        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.setPadding(5,5,5,5);
    }

    public static void clearS(){
        s = "";
        try{
            textView.setText(s);
        }catch (NullPointerException e){
        }
    }

    LinearLayout getLayout(){
        return linearLayout;
    }

}
