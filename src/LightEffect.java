package com.kohei.ikegon.yakusokunohiwomouitido;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getChangeBG;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getW_unit;

public class LightEffect {
    private Canvas canvas;
    private static float lightX;
    private static float lightY;
    private static int lightBGNum = 0;
    private Bitmap light1;
    private Bitmap light2;
    private Rect srcRect;
    private static RectF dstRect;
    private int baseAlpha = 0;
    private int alpha = 0;
    private static boolean lightable = false;
    private static boolean showTheLight = false;
    private Paint basePaint;
    private Paint alphaPaint;
    private boolean convert = true;
    public LightEffect(Canvas canvas,Bitmap bmp1,Bitmap bmp2){
        super();
        this.canvas = canvas;
        light1 = bmp1;
        light2 = bmp2;
        srcRect = new Rect(0,0,bmp1.getWidth(),bmp1.getHeight());
        basePaint = new Paint();
        alphaPaint = new Paint();
    }

    public static void setLightable(boolean bool){
        lightable = bool;
    }
    public static boolean getLightable(){
        return lightable;
    }
    public static boolean getShowTheLight(){
        return showTheLight;
    }
    public static void turnOffTheLight(){
        showTheLight = false;
    }
    public static void setShowTheLight(boolean bool){
        showTheLight = bool;
    }
    public static void setLightX(float f){
        lightX = f;
        dstRect = new RectF(lightX, lightY, lightX +getW_unit()/2, lightY +getW_unit()/2);
    }
    public static void setLightY(float f){
        lightY = f;
        dstRect = new RectF(lightX, lightY, lightX +getW_unit()/2, lightY +getW_unit()/2);
    }
    public static float getLightX(){
        return lightX;
    }
    public static float getLightY(){
        return lightY;
    }

    public static void setLightBGNum(int bgNum){
        lightBGNum = bgNum;
    }
    public static int getLightBGNum(){
        return lightBGNum;
    }

    void draw() {
        if(lightable){
            if(Integer.valueOf(lightBGNum).equals(Integer.valueOf(getChangeBG()))){
                showTheLight = true;
            }else{
                showTheLight = false;
            }
            if(showTheLight){
                basePaint.setAlpha(baseAlpha);
                canvas.drawBitmap(light1, srcRect, dstRect, basePaint);
                alphaPaint.setAlpha(alpha);
                canvas.drawBitmap(light2, srcRect, dstRect, alphaPaint);
                if(convert){
                    alpha+=15;
                    if(alpha>240){
                        convert = false;
                    }
                }else{
                    alpha-=15;
                    if(alpha<15){
                        convert = true;
                    }
                }
                if(baseAlpha<255){
                    baseAlpha += 15;
                }
            }
        }
    }
}
