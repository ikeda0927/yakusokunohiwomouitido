package com.kohei.ikegon.yakusokunohiwomouitido;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/*
  Created by ikego on 2018/02/10.
 */

public class TitleView extends View {
    Bitmap bgBitmap;
    Rect srcRect;
    RectF rect;
    Paint paint = new Paint();

    private static Point real;
    private static int real_height;
    private static int real_width;
    private static float touch_rate = 0.8f;
    private static boolean isLoadFromTitleView = false;
    public TitleView(Context context,Point real) {
        super(context);
        real_height = real.y;
        real_width = real.x;
        bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.title);
        srcRect = new Rect(0,0, bgBitmap.getWidth(), bgBitmap.getHeight());
    }
    public static boolean getIsLoadFromTitleView(){
        return isLoadFromTitleView;
    }
    public static void setIsLoadFromTitleView(boolean bool){
        isLoadFromTitleView = bool;
    }
    public static int getReal_height(){
        return real_height;
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        rect = new RectF(0,0,canvas.getWidth(),canvas.getHeight());
        canvas.drawBitmap(bgBitmap,srcRect,rect,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
        }

        return super.onTouchEvent(event);
    }

}
