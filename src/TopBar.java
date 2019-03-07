package com.kohei.ikegon.yakusokunohiwomouitido;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TopBar extends View {

    private static LinearLayout linearLayout;
    private static TextView textView;
    private static android.os.Handler handler;
    private static TextView date;
    private Context context;

    public TopBar(final Context context) {
        super(context);
        this.context = context;
        linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(Color.BLACK);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        linearLayout.setGravity(Gravity.CENTER);
        textView =  new TextView(context);
        date = new TextView(context);
        date.setText(R.string.day_7);
        textView.setBackgroundColor(Color.BLACK);
        textView.setTextColor(Color.WHITE);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,1.0f));
        date.setBackgroundColor(Color.BLACK);
        date.setTextColor(Color.WHITE);
        textView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        linearLayout.addView(textView);
        linearLayout.addView(date);
        handler = new Handler();
    }

    public static void setDateText(int id) {
        date.setText(id);
    }
    public static void setText(String string){
        textView.setText(string);
    }

    public static Handler getTopBarHandler(){
        return handler;
    }
    public static float getTopBarHeight(){
        return linearLayout.getHeight();
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }
}
