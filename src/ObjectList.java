package com.kohei.ikegon.yakusokunohiwomouitido;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.getChapter;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.getCounter;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.getObjectChanger;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getChangeBG;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getH_unit;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getW_unit;

public class ObjectList extends View {
    List<DrawObject> drawObjectList = new ArrayList<>();
    private static int myBGNum = 100;

    public ObjectList(Context context){
        super(context);
    }
    public static int getMyBGNum(){
        return myBGNum;
    }
    public static void setMyBGNum(int num){
        myBGNum = num;
    }
    public void makeObjectList(){
        float w = getW_unit();
        float h = getH_unit();
        switch (getChangeBG()){
            case 1:
                drawObjectList.clear();
            {
            }
            break;
            case 2:
                drawObjectList.clear();
            {
            }
            break;
            case 3:
                drawObjectList.clear();
            {
                Bitmap obj = BitmapFactory.decodeResource(getResources(),R.drawable.bag_2);
                if(Integer.valueOf(getChapter()).equals(21)){
                    drawObjectList.add(new DrawObject(obj,new RectF(w*5,h*6,w*6,h*7)));
                }
            }
            break;
            case 4:
                drawObjectList.clear();
            {
            }
            break;
            case 5:
                drawObjectList.clear();
            {
                if(Integer.valueOf(getChapter()).equals(39)&&(Integer.valueOf(getCounter()).equals(2)
                        ||Integer.valueOf(getCounter()).equals(3)||Integer.valueOf(getCounter()).equals(4)
                        ||Integer.valueOf(getCounter()).equals(5))){
                    Bitmap flower = BitmapFactory.decodeResource(getResources(),R.drawable.flower);
                    drawObjectList.add(new DrawObject(flower,new RectF(w*3.5f,h*10,w*4.5f,h*11)));
                }
            }
            break;
            case 6:
                drawObjectList.clear();
                break;
            case 7:
                drawObjectList.clear();
            {
                Bitmap food = BitmapFactory.decodeResource(getResources(),R.drawable.food_2);
                drawObjectList.add(new DrawObject(food,new RectF(w*3,h*2,w*4,h*3)));
            }
            break;
            case 8:
                drawObjectList.clear();
            {
                Bitmap table = BitmapFactory.decodeResource(getResources(), R.drawable.table);
                Bitmap bed = BitmapFactory.decodeResource(getResources(),R.drawable.bed3);
                Bitmap wall = BitmapFactory.decodeResource(getResources(),R.drawable.wall3);
                Bitmap flower = BitmapFactory.decodeResource(getResources(),R.drawable.flower);
                drawObjectList.add(new DrawObject(table,new RectF(w*1,h*2,w*2,h*3)));
                drawObjectList.add(new DrawObject(table,new RectF(w*5,h*8,w*7,h*9)));
                drawObjectList.add(new DrawObject(bed,new RectF(w*6,h*2,w*7,h*4)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*1,h*5,w*2,h*7)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*3,h*5,w*4,h*7)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*4,h*5,w*5,h*7)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*5,h*5,w*6,h*7)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*6,h*5,w*7,h*7)));
                if(getChapter() >= 42){
                    drawObjectList.add(new DrawObject(flower,new RectF(w*1,h*2,w*2,h*3)));
                }
            }
            break;
            case 9:
                drawObjectList.clear();
            {
                Bitmap table = BitmapFactory.decodeResource(getResources(), R.drawable.table);
                Bitmap bed = BitmapFactory.decodeResource(getResources(),R.drawable.bed3);
                Bitmap wall = BitmapFactory.decodeResource(getResources(),R.drawable.wall3);
                drawObjectList.add(new DrawObject(table,new RectF(w*1,h*8,w*3,h*9)));
                drawObjectList.add(new DrawObject(table,new RectF(w*2,h*4,w*3,h*5)));
                drawObjectList.add(new DrawObject(bed,new RectF(w*1,h*2,w*2,h*4)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*1,h*5,w*2,h*7)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*2,h*5,w*3,h*7)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*3,h*5,w*4,h*7)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*4,h*5,w*5,h*7)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*6,h*5,w*7,h*7)));
                if(Integer.valueOf(getChapter()).equals(14)){
                    Bitmap memo = BitmapFactory.decodeResource(getResources(),R.drawable.memo_2);
                    drawObjectList.add(new DrawObject(memo,new RectF(w*2,h*4,w*3,h*5)));
                }
            }
            break;
            case 10:
                drawObjectList.clear();
                break;
            case 11:
                drawObjectList.clear();
            {
                Bitmap table = BitmapFactory.decodeResource(getResources(), R.drawable.table);
                drawObjectList.add(new DrawObject(table,new RectF(w*2,h*3,w*3,h*4)));
                drawObjectList.add(new DrawObject(table,new RectF(w*2,h*5,w*3,h*6)));
                drawObjectList.add(new DrawObject(table,new RectF(w*2,h*7,w*3,h*8)));
                drawObjectList.add(new DrawObject(table,new RectF(w*1,h*8,w*3,h*9)));
                drawObjectList.add(new DrawObject(table,new RectF(w*2,h*8,w*3,h*11)));
                drawObjectList.add(new DrawObject(table,new RectF(w*5,h*3,w*6,h*4)));
                drawObjectList.add(new DrawObject(table,new RectF(w*5,h*5,w*6,h*6)));
                drawObjectList.add(new DrawObject(table,new RectF(w*5,h*7,w*6,h*8)));
                if(getObjectChanger()){
                    Bitmap window1 = BitmapFactory.decodeResource(getResources(), R.drawable.window3);
                    Bitmap window2 = BitmapFactory.decodeResource(getResources(), R.drawable.sii_with_bender);
                    drawObjectList.add(new DrawObject(window1,new RectF(w*1,h*0,w*2,h*2)));
                    drawObjectList.add(new DrawObject(window2,new RectF(w*2,h*0,w*4,h*2)));
                    drawObjectList.add(new DrawObject(window1,new RectF(w*4,h*0,w*7,h*2)));
                }else{
                    Bitmap window1 = BitmapFactory.decodeResource(getResources(), R.drawable.window3);
                    drawObjectList.add(new DrawObject(window1,new RectF(w*1,h*0,w*7,h*2)));
                }
            }
            break;
            case 12:
                drawObjectList.clear();
                if(getObjectChanger()){
                    Bitmap flute = BitmapFactory.decodeResource(getResources(), R.drawable.flute_2);
                    drawObjectList.add(new DrawObject(flute,new RectF(w*5,h*4,w*6,h*5)));
                }
                break;
            case 13:
                drawObjectList.clear();
                break;
            case 14:
                drawObjectList.clear();
                break;
            case 15:
                drawObjectList.clear();
            {
                Bitmap table = BitmapFactory.decodeResource(getResources(), R.drawable.table);
                Bitmap brown = BitmapFactory.decodeResource(getResources(),R.drawable.brown_pixel);
                Bitmap diary = BitmapFactory.decodeResource(getResources(),R.drawable.diary_2);
                Bitmap net = BitmapFactory.decodeResource(getResources(),R.drawable.net_2);
                Bitmap wall = BitmapFactory.decodeResource(getResources(),R.drawable.wall3);
                drawObjectList.add(new DrawObject(table,new RectF(w*1,h*3,w*2,h*5)));
                drawObjectList.add(new DrawObject(table,new RectF(w*3,h*4,w*5,h*6)));
                drawObjectList.add(new DrawObject(diary,new RectF(w*1,h*3,w*2,h*4)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*1,h*1,w*2,h*3)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*2,h*1,w*3,h*3)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*3,h*1,w*4,h*3)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*4,h*1,w*5,h*3)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*5,h*1,w*6,h*3)));
                drawObjectList.add(new DrawObject(wall,new RectF(w*6,h*1,w*7,h*3)));
                drawObjectList.add(new DrawObject(brown,new RectF(w*1,h*0,w*7,h*1)));
                drawObjectList.add(new DrawObject(brown,new RectF(w*1,h*9,w*3,h*10)));
                drawObjectList.add(new DrawObject(brown,new RectF(w*5,h*9,w*7,h*10)));
                drawObjectList.add(new DrawObject(brown,new RectF(w*1,h*10,w*7,h*11)));
                drawObjectList.add(new DrawObject(brown,new RectF(w*3,h*11,w*5,h*12)));
                if(Integer.valueOf(getChapter()).equals(32)||Integer.valueOf(getChapter()).equals(33)){
                    drawObjectList.add(new DrawObject(net,new RectF(w*1,h*6,w*2,h*7)));
                }
            }
            break;
            case 16:
                drawObjectList.clear();
                switch (StoryView.getFlashNum()){
                    case 0:
                        Bitmap table = BitmapFactory.decodeResource(getResources(), R.drawable.table);
                        Bitmap bed = BitmapFactory.decodeResource(getResources(),R.drawable.bed3);
                        Bitmap wall = BitmapFactory.decodeResource(getResources(),R.drawable.wall3);
                        drawObjectList.add(new DrawObject(table,new RectF(w*1,h*8,w*3,h*9)));
                        drawObjectList.add(new DrawObject(table,new RectF(w*2,h*4,w*3,h*5)));
                        drawObjectList.add(new DrawObject(bed,new RectF(w*1,h*2,w*2,h*4)));
                        drawObjectList.add(new DrawObject(wall,new RectF(w*1,h*5,w*2,h*7)));
                        drawObjectList.add(new DrawObject(wall,new RectF(w*2,h*5,w*3,h*7)));
                        drawObjectList.add(new DrawObject(wall,new RectF(w*3,h*5,w*4,h*7)));
                        drawObjectList.add(new DrawObject(wall,new RectF(w*4,h*5,w*5,h*7)));
                        drawObjectList.add(new DrawObject(wall,new RectF(w*6,h*5,w*7,h*7)));
                        break;
                }
                Bitmap gray = BitmapFactory.decodeResource(getResources(),R.drawable.gray3);
                drawObjectList.add(new DrawObject(gray,new RectF(w*0,h*0,w*8,h*2)));
                Bitmap grayInverse = BitmapFactory.decodeResource(getResources(),R.drawable.gray4);
                drawObjectList.add(new DrawObject(grayInverse,new RectF(w*0,h*6,w*8,h*12)));
                break;

        }
    }
    public List<DrawObject> getObjectList(){
        return drawObjectList;
    }
}
