package com.kohei.ikegon.yakusokunohiwomouitido;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getChangeBG;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getH_unit;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getW_unit;

public class OverObjectList extends ObjectList {
    private static boolean sunsetJudge = false;
    public OverObjectList(Context context){
        super(context);
    }
    public static void setSunsetJudge(boolean bool){
        sunsetJudge = bool;
    }
    @Override
    public void makeObjectList(){
        float w = getW_unit();
        float h = getH_unit();
        switch (getChangeBG()){
            case 1:
                drawObjectList.clear();
            {
                Bitmap sunset = BitmapFactory.decodeResource(getResources(),R.drawable.sunset2);
                if(sunsetJudge){
                    drawObjectList.add(new DrawObject(sunset,new RectF(w*0,h*0,w*8,h*12)));
                }
            }
            break;
            case 2:
                drawObjectList.clear();
            {
                Bitmap sunset = BitmapFactory.decodeResource(getResources(),R.drawable.sunset2);
                if(sunsetJudge){
                    drawObjectList.add(new DrawObject(sunset,new RectF(w*0,h*0,w*8,h*12)));
                }
            }
            break;
            case 3:
                drawObjectList.clear();
            {
                Bitmap sunset = BitmapFactory.decodeResource(getResources(),R.drawable.sunset2);
                if(sunsetJudge){
                    drawObjectList.add(new DrawObject(sunset,new RectF(w*0,h*0,w*8,h*12)));
                }
            }
            break;
            case 4:
                drawObjectList.clear();
            {
                Bitmap sunset = BitmapFactory.decodeResource(getResources(),R.drawable.sunset2);
                if(sunsetJudge){
                    drawObjectList.add(new DrawObject(sunset,new RectF(w*0,h*0,w*8,h*12)));
                }
            }
            break;
            case 5:
                drawObjectList.clear();
            {
                Bitmap sunset = BitmapFactory.decodeResource(getResources(),R.drawable.sunset2);
                if(sunsetJudge){
                    drawObjectList.add(new DrawObject(sunset,new RectF(w*0,h*0,w*8,h*12)));
                }
            }
            break;
            case 6:
                drawObjectList.clear();
                Bitmap public_phone = BitmapFactory.decodeResource(getResources(),R.drawable.public_phone);
                drawObjectList.add(new DrawObject(public_phone,new RectF(w*1,h*8,w*2,h*9)));
            {
                Bitmap sunset = BitmapFactory.decodeResource(getResources(),R.drawable.sunset2);
                if(sunsetJudge){
                    drawObjectList.add(new DrawObject(sunset,new RectF(w*0,h*0,w*8,h*12)));
                }
            }
            break;
            case 7:
                drawObjectList.clear();
            {
                Bitmap sunset = BitmapFactory.decodeResource(getResources(),R.drawable.sunset2);
                if(sunsetJudge){
                    drawObjectList.add(new DrawObject(sunset,new RectF(w*0,h*0,w*8,h*12)));
                }
            }
            break;
            case 8:
                drawObjectList.clear();
                break;
            case 9:
                drawObjectList.clear();
                break;
            case 10:
                drawObjectList.clear();
                break;
            case 11:
                drawObjectList.clear();
            {
                Bitmap sunset = BitmapFactory.decodeResource(getResources(),R.drawable.sunset2);
                if(sunsetJudge){
                    drawObjectList.add(new DrawObject(sunset,new RectF(w*0,h*0,w*8,h*12)));
                }
            }
            break;
            case 12:
                drawObjectList.clear();
            {
                Bitmap sunset = BitmapFactory.decodeResource(getResources(),R.drawable.sunset2);
                if(sunsetJudge){
                    drawObjectList.add(new DrawObject(sunset,new RectF(w*0,h*0,w*8,h*12)));
                }
            }
            break;
            case 13:
                drawObjectList.clear();
            {
                Bitmap sunset = BitmapFactory.decodeResource(getResources(),R.drawable.sunset2);
                if(sunsetJudge){
                    drawObjectList.add(new DrawObject(sunset,new RectF(w*0,h*0,w*8,h*12)));
                }
            }
            break;
            case 14:
                drawObjectList.clear();
            {
                Bitmap sunset = BitmapFactory.decodeResource(getResources(),R.drawable.sunset2);
                if(sunsetJudge){
                    drawObjectList.add(new DrawObject(sunset,new RectF(w*0,h*0,w*8,h*12)));
                }
            }
            break;
            case 15:
                drawObjectList.clear();
                break;
            case 16:
                drawObjectList.clear();
                break;

        }
    }
}
