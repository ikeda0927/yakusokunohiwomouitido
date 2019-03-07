package com.kohei.ikegon.yakusokunohiwomouitido;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class DrawObject {
    private Bitmap bmp;
    private RectF dstRect;
    private Paint paint = new Paint();
    public boolean isGot = false;

    public DrawObject(Bitmap bmp,RectF dstRect){
        this.bmp = bmp;
        this.dstRect = dstRect;
    }

    public void setIsGot(boolean bool){
        isGot = bool;
    }
    public void draw(Canvas canvas){
        if(!isGot){
            canvas.drawBitmap(bmp,new Rect(0,0,bmp.getWidth(),bmp.getHeight()),dstRect,paint);
        }
    }
}
