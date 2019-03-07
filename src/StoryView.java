package com.kohei.ikegon.yakusokunohiwomouitido;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kohei.ikegon.yakusokunohiwomouitido.Character.BACK;
import static com.kohei.ikegon.yakusokunohiwomouitido.Character.FRONT;
import static com.kohei.ikegon.yakusokunohiwomouitido.Character.getSwitcherNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.Character.setSwitcherNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.Character.switcherNumStandard;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.getChapter;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.getCounter;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.getFluteJudge;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.getFood;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.getObjectChanger;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.setCharacterStoryJudge;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.setCharacterStoryNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.setFluteJudge;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.setFood;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.setIsStoryChangeable;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.setObjectChanger;
import static com.kohei.ikegon.yakusokunohiwomouitido.OverObjectList.setSunsetJudge;


/**
 * Created by ikego on 2018/03/31.
 */

public class StoryView extends SurfaceView implements SurfaceHolder.Callback {
    private Context context;
    private static final int DRAW_INTERVAL = 40;
    //canvasの幅と高さを代入する
    private static int height;
    private static int width;
    private static Bitmap bg1;
    private static Bitmap menuBitmap;
    private static Bitmap MC;
    Intent intent;
    private static GameMap gameMap;
    private static Character mainCharacter;
    private Bitmap sub1CharacterBmp;
    private static Character sub1Character;//しーちゃん
    private Bitmap sub2CharacterBmp;
    private static Character sub2Character;//自分の母
    private Bitmap sub3CharacterBmp;
    private static Character sub3Character;//友人A
    private Bitmap sub4CharacterBmp;
    private static Character sub4Character;//しー母
    private Bitmap sub5CharacterBmp;
    private static Character sub5Character;//自宅横
    private Bitmap sub6CharacterBmp;
    private static Character sub6Character;//しー宅横
    private Bitmap sub7CharacterBmp;
    private static Character sub7Character;//カフェ横
    private Bitmap sub8CharacterBmp;
    private static Character sub8Character;//カフェ店員
    private Bitmap sub9CharacterBmp;
    private static Character sub9Character;//カフェ常連
    private Bitmap sub10CharacterBmp;
    private static Character sub10Character;//カフェ客
    private Bitmap sub11CharacterBmp;
    private static Character sub11Character;//公衆電話横
    private Bitmap sub12CharacterBmp;
    private static Character sub12Character;//池入口
    private Bitmap sub13CharacterBmp;
    private static Character sub13Character;//池ベンチ1
    private Bitmap sub14CharacterBmp;
    private static Character sub14Character;//池ベンチ2
    private Bitmap sub15CharacterBmp;
    private static Character sub15Character;//釣り老人
    private Bitmap sub16CharacterBmp;
    private static Character sub16Character;//猟友会1
    private Bitmap sub17CharacterBmp;
    private static Character sub17Character;//猟友会2
    private Bitmap sub18CharacterBmp;
    private static Character sub18Character;//猟友会3,
    private Bitmap sub19CharacterBmp;
    private static Character sub19Character;//kappa
    private Bitmap sub20CharacterBmp;
    private static Character sub20Character;//イノシシ1 (親)
    private Bitmap sub21CharacterBmp;
    private static Character sub21Character;//イノシシ2 (子)
    private Bitmap sub22CharacterBmp;
    private static Character sub22Character;//回想したときの主人公
    private Bitmap sub23CharacterBmp;
    private static Character sub23Character;//回想したときのしーちゃん
    private Bitmap sub24CharacterBmp;
    private static Character sub24Character;//小さい頃の友人Ａ

    private static boolean CharacterDirectionChanged = false;

    //主人公のアニメーション用
//    private static Character animationMainCharacter;
    private static boolean mcJudge = true;
    private boolean mcJudgeJudge = false;

    private static Boolean isLoad = false;
    private static Boolean isList = false;
    private static boolean isAnimation;

    //touch　された座標
    private float touchedX = -1f;
    private float touchedY = -1f;

    //移動中か否か
    private static boolean isStop = true;

    //touch時の移動アニメーション用
    private static boolean isTouch;

    private static boolean isFunctionViewContentChanged = false;

    //リストを表示するか否か(デフォルトではfalse)
    private static boolean judgeList = false;
    //障害物用
    private static boolean isInTheObject = false;
    //小屋
    private static boolean house = false;
    private static boolean housePass = false;

    private static float save_rate = 0.92f;

    private static float L_rate = 0.07f;
    private static float R_rate = 0.93f;

    private static float x;
    private static float y;
    private static float[] pre_x = {-200,-200};
    private static float[] pre_y = {-200,-200};
    private static float[] load_position = {-200,-200};
    private static boolean judgeKirby = false;
    //背景の番号
    private static int ChangeBG = 8;
    //画面をいくつかの升に分けたときのやつ
    private static float W_unit;
    private static float H_unit;

    //いろいろなものを表示させるよう
    private static ObjectList objectList;
    //主人公の上にものを表示させるよう
    private static OverObjectList overObjectList;

    //右・左・上・下 (石像)
    private boolean right = false;
    private boolean left = false;
    private boolean above = false;
    private boolean below = false;

    //回想用
    private boolean flashJudge = false;
    private static int flashNum = 0;//回想区別用

    //bgm用
    private static Music music;
    private static boolean musicInitialized = false;

    //ダイアログ用
    private static MyDialog dialog;
    private final Handler handler = new Handler();
    //謎の光
    private LightEffect light;
    private Bitmap light1;
    private Bitmap light2;

    //セーブデータを取り出す
    private static float[] save_data = new float[17];

    public StoryView(Context context) {
        super(context);
        this.context = context;
        bg1 = BitmapFactory.decodeResource(getResources(), R.drawable.bg15);
        menuBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.menu2);
        MC = BitmapFactory.decodeResource(getResources(),R.drawable.mc1_2);
        sub1CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc1);
        sub2CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc2_2);
        sub3CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc3_2);
        sub4CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc4_2);
        sub5CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc5_2);
        sub6CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc6_2);
        sub7CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc7_2);
        sub8CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc8_2);
        sub9CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc9_2);
        sub10CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc10_2);
        sub11CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc11_2);
        sub12CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc12_2);
        sub13CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc13_2);
        sub14CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc14_2);
        sub15CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc15_2);
        sub16CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc16_2);
        sub17CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc17_2);
        sub18CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc18_2);
        sub19CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc19);
        sub20CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc20);
        sub21CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc21);
        sub22CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc22_2);
        sub23CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc23_2);
        sub24CharacterBmp = BitmapFactory.decodeResource(getResources(),R.drawable.sc24_2);
        light1 = BitmapFactory.decodeResource(getResources(),R.drawable.light6);
        light2 = BitmapFactory.decodeResource(getResources(),R.drawable.light2);
        x = -100;
        y = -100;
        dialog = new MyDialog(context);
        objectList = new ObjectList(context);
        overObjectList = new OverObjectList(context);

        music = new Music(context);

        getHolder().addCallback(this);
    }

    public static void setIsLoad(boolean bool){
        isLoad = bool;
    }
    public static boolean getIsLoad(){
        return isLoad;
    }
    public static void setIsAnimation(boolean setIsAnimation){
        isAnimation = setIsAnimation;
    }
    public static boolean getIsAnimation(){
        return isAnimation;
    }
    public static boolean getIsTouch(){
        return isTouch;
    }
    public static void setIsTouch(boolean setIsTouch){
        isTouch = setIsTouch;
    }
    public static void setIsStop(boolean setIsStop){
        isStop = setIsStop;
    }
    public static boolean getIsStop(){
        return isStop;
    }
    public static void setIsInTheObject(boolean setIsInTheObject){
        isInTheObject = setIsInTheObject;
    }
    public static boolean getIsInTheObject(){
        return isInTheObject;
    }
    public static float getSave_rate(){
        return save_rate;
    }
    public static int getChangeBG(){
        return ChangeBG;
    }
    public static void playMusic(){
        music.play();
    }
    public static void stopMusic(){
        music.stop();
    }
    public static void setMusicID(int musicID){
        music.setMusicID(musicID);
    }
    public static int getMusicID(){
        return music.getMusicID();
    }
    public static void setIsStoryMusic(boolean bool){
        music.setIsStoryMusic(bool);
    }
    public static boolean getIsStoryMusic(){
        return music.getIsStoryMusic();
    }
    public static void setMusicInitialized(boolean bool){
        musicInitialized = bool;
    }
    public static void setChangeBG(int setChangeBG){
        ChangeBG = setChangeBG;
    }
    public static int getFlashNum(){
        return flashNum;
    }
    public static void setFlashNum(int num){
        flashNum = num;
    }
    public static void setW_unit(float setW_unit){
        W_unit = setW_unit;
    }
    public static float getW_unit(){
        return W_unit;
    }
    public static void setH_unit(float setH_unit){
        H_unit = setH_unit;
    }
    public static float getH_unit(){
        return H_unit;
    }
    public static Dialog getDiaog(){
        return dialog;
    }
    public static void setMainCharacterPrePoint(Point point){
        mainCharacter.setPrePoint(point);
    }
    public static void setMainCharacterDirectionByCharacter(float subX,float subY){
        mainCharacter.setMainCharacterDirectionByCharacter(subX,subY);
    }
    public static void setMainCharacterDirection(int direction){
        mainCharacter.setMainCharacterDirection(direction);
    }
    public static void setMainCharacterIsPassive(boolean bool){
        mainCharacter.setIsPassive(bool);
    }
    public static void setMainCharacterPointList(List<Point> pointList){
        mainCharacter.setPointList(pointList);
    }
    public static boolean getIsPassive(){
        return mainCharacter.getIsPassive();
    }//for debug
    public static void setMainCharacterBgNum(int bgNum){
        mainCharacter.setBgNum(bgNum);
    }
    public static void setMainCharacterBgNum(int bgNum,float x,float y){
        mainCharacter.setBgNum(bgNum);
        mainCharacter.setDstRectByCoordinate(x,y);
    }
    public static void setMainCharacterIsShow(boolean bool){
        mainCharacter.setShowCharacter(bool);
    }
    public static void setMainCharacterIsHide(boolean bool){
        mainCharacter.setIsHide(bool);
    }
    public static void searchMainCharacterPass(int destX, int destY, List<CoordinateData> coordinateDataList){
        mainCharacter.searchPath((int)mainCharacter.getNowX(),(int)mainCharacter.getNowY(),destX,destY,coordinateDataList);
    }
    public static void setSub1CharacterPointList(List<Point> pointList){
        sub1Character.setPointList(pointList);
    }
    public static void setSub1CharacterBgNum(int bgNum,float x,float y){
        sub1Character.setBgNum(bgNum);
        //sub1Character.setDstRect(new RectF(x*getW_unit(),y*getH_unit(),x*getW_unit()+getW_unit(),y*getH_unit()+getH_unit()));
        sub1Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub1CharacterIsShow(boolean bool){
        sub1Character.setShowCharacter(bool);
    }
    public static void setSub1CharacterDirection(int direction){
        sub1Character.setActiveCharacterDirection(direction);
    }
    public static void setSub1CharacterDirectionForMainCharacter(){
        sub1Character.setDirectionForMainCharacter();
    }
    public static int getSub1CharacterDirectionNum(){
        return sub1Character.getDirectionNum();
    }
    public static void setSub2CharacterPointList(List<Point> pointList){
        sub2Character.setPointList(pointList);
    }
    public static void setSub2CharacterIsShow(boolean bool){
        sub2Character.setShowCharacter(bool);
    }
    public static void setSub2CharacterDirectionForMainCharacter(){
        sub2Character.setDirectionForMainCharacter();
    }
    public static void setSub3CharacterPointList(List<Point> pointList){
        sub3Character.setPointList(pointList);
    }
    public static void setSub3CharacterIsShow(boolean bool){
        sub3Character.setShowCharacter(bool);
    }
    public static void setSub3CharacterDirectionForMainCharacter(){
        sub3Character.setDirectionForMainCharacter();
    }
    public static void setSub3CharacterDirection(int direction){
        sub3Character.setDirection(direction);
    }
    public static void setSub3CharacterBgNum(int bgNum,float x,float y){
        sub3Character.setBgNum(bgNum);
        sub3Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub4CharacterPointList(List<Point> pointList){
        sub4Character.setPointList(pointList);
    }
    public static void setSub4CharacterIsShow(boolean bool){
        sub4Character.setShowCharacter(bool);
    }
    public static void setSub4CharacterDirection(int direction){
        sub4Character.setActiveCharacterDirection(direction);
    }
    public static void setSub4CharacterDirectionForMainCharacter(){
        sub4Character.setDirectionForMainCharacter();
    }
    public static void setSub5CharacterIsShow(boolean bool){
        sub5Character.setShowCharacter(bool);
    }
    public static void setSub5CharacterDirectionForMainCharacter(){
        sub5Character.setDirectionForMainCharacter();
    }
    public static void setSub6CharacterIsShow(boolean bool){
        sub6Character.setShowCharacter(bool);
    }
    public static void setSub6CharacterDirectionForMainCharacter(){
        sub6Character.setDirectionForMainCharacter();
    }
    public static int getSub6CharacterDirectionNum(){
        return sub6Character.getDirectionNum();
    }
    public static void setSub7CharacterIsShow(boolean bool){
        sub7Character.setShowCharacter(bool);
    }
    public static void setSub7CharacterDirection(int direction){
        sub7Character.setDirection(direction);
    }
    public static void setSub7CharacterBgNum(int bgNum,float x,float y){
        sub7Character.setBgNum(bgNum);
        sub7Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub7CharacterDirectionForMainCharacter(){
        sub7Character.setDirectionForMainCharacter();
    }
    public static void setSub8CharacterIsShow(boolean bool){
        sub8Character.setShowCharacter(bool);
    }
    public static void setSub8CharacterDirectionForMainCharacter(){
        sub8Character.setDirectionForMainCharacter();
    }
    public static void setSub9CharacterIsShow(boolean bool){
        sub9Character.setShowCharacter(bool);
    }
    public static void setSub9CharacterDirectionForMainCharacter(){
        sub9Character.setDirectionForMainCharacter();
    }
    public static void setSub10CharacterIsShow(boolean bool){
        sub10Character.setShowCharacter(bool);
    }
    public static void setSub10CharacterDirectionForMainCharacter(){
        sub10Character.setDirectionForMainCharacter();
    }
    public static void setSub11CharacterIsShow(boolean bool){
        sub11Character.setShowCharacter(bool);
    }
    public static void setSub11CharacterDstRectByCoordinate(float x,float y){
        sub11Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub11CharacterDirectionForMainCharacter(){
        sub11Character.setDirectionForMainCharacter();
    }
    public static void setSub12CharacterIsShow(boolean bool){
        sub12Character.setShowCharacter(bool);
    }
    public static void setSub12CharacterDirectionForMainCharacter(){
        sub12Character.setDirectionForMainCharacter();
    }
    public static void setSub12CharacterDirection(int direction){
        sub12Character.setDirection(direction);
    }
    public static int getSub12CharacterBgNum(){
        return sub12Character.getBgNum();
    }
    public static void setSub12CharacterBgNum(int bgNum,float x,float y){
        sub12Character.setBgNum(bgNum);
        sub12Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub13CharacterPointList(List<Point> pointList){
        sub13Character.setPointList(pointList);
    }
    public static void setSub13CharacterIsShow(boolean bool){
        sub13Character.setShowCharacter(bool);
    }
    public static void setSub13CharacterBgNum(int bgNum,float x,float y){
        sub13Character.setBgNum(bgNum);
        sub13Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub13CharacterDirectionForMainCharacter(){
        sub13Character.setDirectionForMainCharacter();
    }
    public static void setSub13CharacterDirection(int direction){
        sub13Character.setDirection(direction);
    }
    public static int getSub13CharacterBgNum(){
        return sub13Character.getBgNum();
    }
    public static void setSub14CharacterPointList(List<Point> pointList){
        sub14Character.setPointList(pointList);
    }
    public static void setSub14CharacterIsShow(boolean bool){
        sub14Character.setShowCharacter(bool);
    }
    public static void setSub14CharacterBgNum(int bgNum){
        sub14Character.setBgNum(bgNum);
    }
    public static void setSub14CharacterBgNum(int bgNum,float x,float y){
        sub14Character.setBgNum(bgNum);
        //sub14Character.setDstRect(new RectF(x*getW_unit(),y*getH_unit(),x*getW_unit()+getW_unit(),y*getH_unit()+getH_unit()));
        sub14Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub14CharacterDirectionForMainCharacter(){
        sub14Character.setDirectionForMainCharacter();
    }
    public static void setSub14CharacterDirection(int direction){
        sub14Character.setDirection(direction);
    }
    public static int getSub14CharacterBgNum(){
        return sub14Character.getBgNum();
    }
    public static void setSub15CharacterPointList(List<Point> pointList){
        sub15Character.setPointList(pointList);
    }
    public static void setSub15CharacterIsShow(boolean bool){
        sub15Character.setShowCharacter(bool);
    }
    public static void setSub15CharacterBgNum(int bgNum){
        sub15Character.setBgNum(bgNum);
    }
    public static void setSub15CharacterBgNum(int bgNum,float x,float y){
        sub15Character.setBgNum(bgNum);
        sub15Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub15CharacterDirectionForMainCharacter(){
        sub15Character.setDirectionForMainCharacter();
    }
    public static void setSub15CharacterDirection(int direction){
        sub15Character.setDirection(direction);
    }
    public static int getSub15CharacterDirectionNum(){
        return sub15Character.getDirectionNum();
    }
    public static void setSub16CharacterPointList(List<Point> pointList){
        sub16Character.setPointList(pointList);
    }
    public static void setSub16CharacterIsShow(boolean bool){
        sub16Character.setShowCharacter(bool);
    }
    public static void setSub16CharacterBgNum(int bgNum){
        sub16Character.setBgNum(bgNum);
    }
    public static void setSub16CharacterBgNum(int bgNum,float x,float y){
        sub16Character.setBgNum(bgNum);
        sub16Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub16CharacterDirectionForMainCharacter(){
        sub16Character.setDirectionForMainCharacter();
    }
    public static void setSub17CharacterPointList(List<Point> pointList){
        sub17Character.setPointList(pointList);
    }
    public static void setSub17CharacterIsShow(boolean bool){
        sub17Character.setShowCharacter(bool);
    }
    public static void setSub17CharacterBgNum(int bgNum){
        sub17Character.setBgNum(bgNum);
    }
    public static void setSub17CharacterBgNum(int bgNum,float x,float y){
        sub17Character.setBgNum(bgNum);
        sub17Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub17CharacterDirectionForMainCharacter(){
        sub17Character.setDirectionForMainCharacter();
    }
    public static void setSub18CharacterPointList(List<Point> pointList){
        sub18Character.setPointList(pointList);
    }
    public static void setSub18CharacterIsShow(boolean bool){
        sub18Character.setShowCharacter(bool);
    }
    public static void setSub18CharacterBgNum(int bgNum){
        sub18Character.setBgNum(bgNum);
    }
    public static void setSub18CharacterBgNum(int bgNum,float x,float y){
        sub18Character.setBgNum(bgNum);
        sub18Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub18CharacterDirectionForMainCharacter(){
        sub18Character.setDirectionForMainCharacter();
    }
    public static void setSub19CharacterBgNum(int bgNum,float x ,float y){
        sub19Character.setBgNum(bgNum);
        sub19Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub19CharacterPointList(List<Point> pointList){
        sub19Character.setPointList(pointList);
    }
    public static void setSub19CharacterIsShow(boolean bool){
        sub19Character.setShowCharacter(bool);
    }
    public static void setSub19CharacterDirection(int direction){
        sub19Character.setDirection(direction);
    }
    public static void setSub19CharacterDirectionForMainCharacter(){
        sub19Character.setDirectionForMainCharacter();
    }
    public static void setSub20CharacterPointList(List<Point> pointList){
        sub20Character.setPointList(pointList);
    }
    public static void setSub20CharacterPrePoint(Point point){
        sub20Character.setPrePoint(point);
    }
    public static void setSub20CharacterIsHide(boolean bool){
        sub20Character.setIsHide(bool);
    }
    public static void setSub20CharacterIsShow(boolean bool){
        sub20Character.setShowCharacter(bool);
    }
    public static void setSub20CharacterBgNum(int bgNum){
        sub20Character.setBgNum(bgNum);
    }
    public static void setSub20CharacterBgNum(int bgNum,float x,float y){
        sub20Character.setBgNum(bgNum);
        sub20Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub20CharacterDirectionForMainCharacter(){
        sub20Character.setDirectionForMainCharacter();
    }
    public static void setSub20CharacterDirection(int direction){
        sub20Character.setActiveCharacterDirection(direction);
    }
    public static void setSub21CharacterPointList(List<Point> pointList){
        sub21Character.setPointList(pointList);
    }
    public static void setSub21CharacterIsShow(boolean bool){
        sub21Character.setShowCharacter(bool);
    }
    public static void setSub21CharacterBgNum(int bgNum){
        sub21Character.setBgNum(bgNum);
    }
    public static void setSub21CharacterBgNum(int bgNum,float x,float y){
        sub21Character.setBgNum(bgNum);
        sub21Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub21CharacterDirection(int direction){
        sub21Character.setDirection(direction);
    }
    public static void setSub21CharacterDirectionForMainCharacter(){
        sub21Character.setDirectionForMainCharacter();
    }
    public static void setSub22CharacterPointList(List<Point> pointList){
        sub22Character.setPointList(pointList);
    }
    public static void setSub22CharacterIsShow(boolean bool){
        sub22Character.setShowCharacter(bool);
    }
    public static void setSub22CharacterBgNum(int bgNum){
        sub22Character.setBgNum(bgNum);
    }
    public static void setSub22CharacterBgNum(int bgNum,float x,float y){
        sub22Character.setBgNum(bgNum);
        sub22Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub22CharacterDirection(int direction){
        sub22Character.setDirection(direction);
    }
    public static void setSub22CharacterDirectionForMainCharacter(){
        sub22Character.setDirectionForMainCharacter();
    }
    public static int getSub22CharacterDirectionNum(){
        return  sub22Character.getDirectionNum();
    }
    public static void setSub23CharacterPointList(List<Point> pointList){
        sub23Character.setPointList(pointList);
    }
    public static void setSub23CharacterIsShow(boolean bool){
        sub23Character.setShowCharacter(bool);
    }
    public static boolean getSub23CharacterIsShow(){
        return sub23Character.getIsShow();
    }
    public static void setSub23CharacterBgNum(int bgNum){
        sub23Character.setBgNum(bgNum);
    }
    public static void setSub23CharacterBgNum(int bgNum,float x,float y){
        sub23Character.setBgNum(bgNum);
        sub23Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub23CharacterDirection(int direction){
        sub23Character.setDirection(direction);
    }
    public static void setSub23CharacterDirectionForMainCharacter(){
        sub23Character.setDirectionForMainCharacter();
    }
    public static void setSub24CharacterPointList(List<Point> pointList){
        sub24Character.setPointList(pointList);
    }
    public static void setSub24CharacterIsShow(boolean bool){
        sub24Character.setShowCharacter(bool);
    }
    public static void setSub24CharacterBgNum(int bgNum){
        sub24Character.setBgNum(bgNum);
    }
    public static void setSub24CharacterBgNum(int bgNum,float x,float y){
        sub24Character.setBgNum(bgNum);
        sub24Character.setDstRectByCoordinate(x,y);
    }
    public static void setSub24CharacterDirection(int direction){
        sub24Character.setDirection(direction);
    }
    public static void setSub24CharacterDirectionForMainCharacter(){
        sub24Character.setDirectionForMainCharacter();
    }
    public static void setHouse(boolean bool){
        house = bool;
    }
    public static boolean getHouse(){
        return house;
    }
    public static boolean getHousePass(){
        return housePass;
    }
    public static void setHousePass(boolean bool){
        housePass = bool;
    }
    public static void setFunctionViewContentChanged(boolean bool){
        isFunctionViewContentChanged = bool;
    }
    public static void setSaveData(float[] data){
        save_data = data;
    }
    public static boolean getIsList(){
        return isList;
    }
    public static void setObjectList(){
        objectList.makeObjectList();
        ObjectList.setMyBGNum(getChangeBG());
//            flashJudge = true;
        setObjectChanger(false);
    }
    public static void setOverObjectList(){
        overObjectList.makeObjectList();
    }

    private DrawThread drawThread;

    private class DrawThread extends Thread{
        private final AtomicBoolean isFinished = new AtomicBoolean();

        public void finish(){ isFinished.set(true);}

        @Override
        public void run(){
            SurfaceHolder holder = getHolder();
            while(!isFinished.get()){
                if(holder.isCreating()){
                    continue;
                }
                Canvas canvas = holder.lockCanvas();
                if(canvas == null){
                    continue;
                }
                drawStory(canvas);
                holder.unlockCanvasAndPost(canvas);

                synchronized(this){
                    try{
                        wait(DRAW_INTERVAL);
                    }catch (InterruptedException e){
                    }
                }
            }
        }
    }

    public void startDrawThread(){
        stopDrawThread();

        drawThread = new DrawThread();
        drawThread.start();
    }
    public boolean stopDrawThread(){
        if(drawThread == null){
            return false;
        }
        drawThread.finish();
        drawThread = null;
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        startDrawThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        stopDrawThread();
    }

    public void drawStory(Canvas canvas) {
//        canvas.drawColor(Color.LTGRAY);
//        width = canvas.getWidth();
//        height = canvas.getHeight();
//        setW_unit(width / 8);
//        setH_unit(height / 13);
//        if (gameMap == null) {
//            //ナビゲーションバーを常に表示
//            gameMap = new GameMap(bg1, menuBitmap,width, height);
//            //ナビゲーションバー非表示
//            //gameMap = new GameMap(bgBitmap,saveBitmap,real_width,real_height);
//        }
//
//        if(mainCharacter == null){
//            mainCharacter = new Character(canvas,MC);
//        }
////        if(animationMainCharacter == null){
////            animationMainCharacter = new Character(canvas,sub1CharacterBmp,8,0);
////            animationMainCharacter.setShowCharacter(true);
////        }
//        if(sub1Character == null){
////            sub1Character = new CharacterAnimationThird(canvas,sub1CharacterBmp);
//            sub1Character = new Character(canvas,sub1CharacterBmp,9,1);
//        }
//        if(sub2Character == null){
////            sub2Character = new CharacterAnimationThird(canvas,sub2CharacterBmp);
//            sub2Character = new Character(canvas,sub2CharacterBmp,8,2,5f,7f,Character.FRONT);
//        }
//        if(sub3Character == null){
////            sub3Character = new CharacterAnimationThird(canvas,sub3CharacterBmp);
//            sub3Character = new Character(canvas,sub3CharacterBmp,7,3);
//        }
//        if(sub4Character == null){
////            sub4Character = new CharacterAnimationThird(canvas,sub4CharacterBmp);
//            sub4Character = new Character(canvas,sub4CharacterBmp,9,4,2f,7f,Character.FRONT);
//        }
//        if(sub5Character == null){
////            sub4Character = new CharacterAnimationThird(canvas,sub4CharacterBmp);
//            sub5Character = new Character(canvas,sub5CharacterBmp,2,5,7f,3f,Character.FRONT);
//        }
//        if(sub6Character == null){
////            sub4Character = new CharacterAnimationThird(canvas,sub4CharacterBmp);
//            sub6Character = new Character(canvas,sub6CharacterBmp,5,6,7f,9f,Character.FRONT);
//        }
//        if(sub7Character == null){
////            sub4Character = new CharacterAnimationThird(canvas,sub4CharacterBmp);
//            sub7Character = new Character(canvas,sub7CharacterBmp,1,7,1f,7f,Character.FRONT);
//        }
//        if(sub8Character == null){
//            sub8Character = new Character(canvas,sub8CharacterBmp,11,8,1f,9f,Character.RIGHT);
//        }
//        if(sub9Character == null){
////            sub3Character = new CharacterAnimationThird(canvas,sub3CharacterBmp);
//            sub9Character = new Character(canvas,sub9CharacterBmp,11,9,1f,7f,Character.RIGHT);
//        }
//        if(sub10Character == null){
////            sub4Character = new CharacterAnimationThird(canvas,sub4CharacterBmp);
//            sub10Character = new Character(canvas,sub10CharacterBmp,11,10,6f,7f,Character.LEFT);
//        }
//        if(sub11Character == null){
////            sub4Character = new CharacterAnimationThird(canvas,sub4CharacterBmp);
//            sub11Character = new Character(canvas,sub11CharacterBmp,6,11,3f,2f,Character.FRONT);
//        }
//        if(sub12Character == null){
////            sub4Character = new CharacterAnimationThird(canvas,sub4CharacterBmp);
//            sub12Character = new Character(canvas,sub12CharacterBmp,4,12,7f,2f,Character.FRONT);
//        }
//        if(sub13Character == null){
////            sub4Character = new CharacterAnimationThird(canvas,sub4CharacterBmp);
//            sub13Character = new Character(canvas,sub13CharacterBmp,2,13,4f,0f,Character.RIGHT);
//        }
//        if(sub14Character == null){
////            sub4Character = new CharacterAnimationThird(canvas,sub4CharacterBmp);
//            sub14Character = new Character(canvas,sub14CharacterBmp,2,14,5f,0f,Character.LEFT);
//        }
//        if(sub15Character == null){
////            sub4Character = new CharacterAnimationThird(canvas,sub4CharacterBmp);
//            sub15Character = new Character(canvas,sub15CharacterBmp,7,15,5f,9f,Character.FRONT);
//        }
//        if(sub16Character == null){
////            sub4Character = new CharacterAnimationThird(canvas,sub4CharacterBmp);
//            sub16Character = new Character(canvas,sub16CharacterBmp,4,16);
//        }
//        if(sub17Character == null){
////            sub4Character = new CharacterAnimationThird(canvas,sub4CharacterBmp);
//            sub17Character = new Character(canvas,sub17CharacterBmp,4,17);
//        }
//        if(sub18Character == null){
//            sub18Character = new Character(canvas,sub18CharacterBmp,4,18);
//        }
//
//        if(sub19Character == null){
//            sub19Character = new Character(canvas,sub19CharacterBmp,4,19,2f,2f,Character.FRONT);
//        }
//        if(sub20Character == null){
//            sub20Character = new Character(canvas,sub20CharacterBmp,3,20);
//        }
//        if(sub21Character == null){
//            sub21Character = new Character(canvas,sub21CharacterBmp,3,21);
//        }
//        if(sub22Character == null){
//            sub22Character = new Character(canvas,sub22CharacterBmp,16,22,3f,3f,Character.RIGHT);
//        }
//        if(sub23Character == null){
//            sub23Character = new Character(canvas,sub23CharacterBmp,16,23,4f,3f,Character.LEFT);
//        }
//        if(sub24Character == null){
//            sub24Character = new Character(canvas,sub24CharacterBmp,7,24,4f,3f,Character.LEFT);
//        }
        if(gameMap == null){
            init(canvas);
        }
        gameMap.draw(canvas);

        if(!music.isPlaying() && musicInitialized){
            //Log.d("Music","Music stopped but I noticed.");
            setMusicInitialized(false);
            stopMusic();
            playMusic();
        }

        if(!Integer.valueOf(getChangeBG()).equals(Integer.valueOf(ObjectList.getMyBGNum())) || getObjectChanger()){
            setObjectList();
            setOverObjectList();
        }
        for(DrawObject drawObject : objectList.getObjectList()){
            drawObject.draw(canvas);
        }

//        if(isList){
//            if(isAnimation){
//            }else {
//            }
//        }
        //ストーリーに入るときに主人公を表示するか否か
        if(Integer.valueOf(FunctionView.getChapter()).equals(1) && Integer.valueOf(getChangeBG()).equals(7)){
            mainCharacter.setIsHide(true);
        }
        if (isLoad){
            initLoad(canvas);
        }else{
            sub1Character.drawCharacter();
            sub2Character.drawCharacter();
            sub3Character.drawCharacter();
            sub4Character.drawCharacter();
            sub5Character.drawCharacter();
            sub6Character.drawCharacter();
            sub7Character.drawCharacter();
            sub8Character.drawCharacter();
            sub9Character.drawCharacter();
            sub10Character.drawCharacter();
            sub11Character.drawCharacter();
            sub12Character.drawCharacter();
            sub13Character.drawCharacter();
            sub14Character.drawCharacter();
            sub15Character.drawCharacter();
            sub16Character.drawCharacter();
            sub17Character.drawCharacter();
            sub18Character.drawCharacter();
            sub19Character.drawCharacter();
            sub20Character.drawCharacter();
            sub21Character.drawCharacter();
            sub22Character.drawCharacter();
            sub23Character.drawCharacter();
            sub24Character.drawCharacter();
            try{
                mainCharacter.drawCharacter();
            }catch (NullPointerException e){
                //Log.d("mainCharacter",e.toString()+"");
                mainCharacter.setPrePoint(new Point(-1,-2));
            }
        }
        for(DrawObject drawObject : overObjectList.getObjectList()){
            drawObject.draw(canvas);
        }
        //謎の光の表示
        if(light==null){
            light = new LightEffect(canvas,light1,light2);
        }
        light.draw();
        if(switcherNumStandard*2<getSwitcherNum()){
            setSwitcherNum(0);
        }else{
            setSwitcherNum(getSwitcherNum()+1);
        }

        if(isStop && mcJudge){
            int MCX;
            int MCY;
            try{
                MCX = (int)mainCharacter.getPrePoint().getX();
                MCY = (int)mainCharacter.getPrePoint().getY();
            }catch (NullPointerException e){
                MCX = 0;
                MCY = 0;
            }
            switch (ChangeBG) {
                case 1:
                    if(getChapter() == 6 && getIsStop() && (getW_unit()*4< touchedX && touchedX < getW_unit()*5) && (getH_unit()*4< touchedY && touchedY < getH_unit()*5)){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 12 && getIsStop() && (getW_unit()*4< touchedX && touchedX < getW_unit()*5) && (getH_unit()*4< touchedY && touchedY < getH_unit()*5)){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }
                    if (7 == MCX && (4 == MCY || MCY ==5)) {
                        if(Integer.valueOf(getChapter()).equals(7)||(Integer.valueOf(getChapter()).equals(13)&&Integer.valueOf(getCounter()).equals(14))){
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("カフェに置いてきた荷物を取ってこないと。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                }
                            });
                            isFunctionViewContentChanged = true;
                        }else if(Integer.valueOf(getChapter()).equals(6)||(Integer.valueOf(getChapter()).equals(12))){
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("しーちゃんと話さないと。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                }
                            });
                            isFunctionViewContentChanged = true;
                        }else{
                            ChangeBG = 5;
                            setMainCharacterIsHide(true);
                            mainCharacter.setPrePoint(new Point(1,4));
                            mainCharacter.drawCharacter();
                        }
                        mcJudge = false;
                    }else if( 5 == MCX && 9 == MCY){
                        ChangeBG = 11;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(3,10));
                        mainCharacter.drawCharacter();
                        mcJudge = false;
                    }else if(Integer.valueOf(getChapter()).equals(37) && getCounter() < 22){
                        mcJudge = false;
                    }else if(getIsStop() && (getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*7< touchedY && touchedY < getH_unit()*8)){
                        setMainCharacterDirectionByCharacter(1f,7f);
                        setSub7CharacterDirectionForMainCharacter();
                        if(Integer.valueOf(getChapter()).equals(40)){
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(4071);//talk_40_7_1()
                                }
                            });
                        }else if(Integer.valueOf(getChapter()).equals(17) && getHousePass()){
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(1771);
                                }
                            });
                        }else if(Integer.valueOf(getChapter()).equals(38)||Integer.valueOf(getChapter()).equals(39)){

                        }else{
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(370);
                                }
                            });
                        }
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }
                    break;
                case 2:
                    if ( 0 == MCX && (MCY == 9 || MCY == 10)) {
                        ChangeBG = 14;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(6,9));
                        mcJudge = false;
                    } else if ( 7 == MCX && ( MCY == 8 || MCY == 9 )) {
                        if(Integer.valueOf(getChapter()).equals(28)){
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("りょうと会っていたら間に合わなくなってしまうかもしれない。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                }
                            });
                            isFunctionViewContentChanged = true;
                        }else if(Integer.valueOf(getChapter()).equals(39) &&Integer.valueOf(getCounter()).equals(0)){
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("しーちゃんの家はこっちではないかな。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                }
                            });
                            isFunctionViewContentChanged = true;
                        }else{

                            ChangeBG = 7;
                            setMainCharacterIsHide(true);

                            mainCharacter.setPrePoint(new Point(1,7));
                            mainCharacter.drawCharacter();
                        }
                        mcJudge = false;
                    } else if ( ( MCX == 4 || MCX == 5) && 0 == MCY ) {
                        ChangeBG = 5;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(3,10));
                        mainCharacter.setPrePoint(new Point(3,10));
                        mainCharacter.drawCharacter();
                        mcJudge = false;
                    } else if ( MCX == 2 && MCY == 5 ){
                        ChangeBG = 8;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(3,10));
                        mainCharacter.setPrePoint(new Point(3,10));
                        mainCharacter.drawCharacter();
                        mcJudge = false;
                    }else if(Integer.valueOf(getChapter()).equals(37) && getCounter() < 22){
                        mcJudge = false;
                    }else if(getIsStop() && (getW_unit()*7< touchedX && touchedX < getW_unit()*8) && (getH_unit()*3< touchedY && touchedY < getH_unit()*4)){
                        setMainCharacterDirectionByCharacter(7f,3f);
                        setSub5CharacterDirectionForMainCharacter();
                        if(Integer.valueOf(getChapter()).equals(17) && getHousePass()){
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(1751);//シアン
                                }
                            });
                        }else if(Integer.valueOf(getChapter()).equals(40)){
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(4051);//シアン
                                }
                            });
                        }else if(Integer.valueOf(getChapter()).equals(38)||Integer.valueOf(getChapter()).equals(39)){

                        }else{
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(50);//シアン
                                }
                            });
                        }
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }else if(getIsStop() && Integer.valueOf(getChapter()).equals(1) && ((getW_unit()*4< touchedX && touchedX < getW_unit()*5)||(getW_unit()*5< touchedX && touchedX < getW_unit()*6)) && (getH_unit()*0< touchedY && touchedY < getH_unit()*1)){
                        setMainCharacterDirectionByCharacter(4f,0f);
                        setMainCharacterDirectionByCharacter(5f,0f);
                        FunctionView.getmyHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                setCharacterStoryJudge(true);
                                setCharacterStoryNum(131);
                            }
                        });
                        isFunctionViewContentChanged = true;
                        mcJudge = false;
                    }
                    break;
                case 3:
                    if (0 == MCX && (MCY == 9 || MCY == 10 || MCY == 11)) {
                        ChangeBG = 6;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(6,3));
                        mainCharacter.drawCharacter();
                        mcJudge = false;
                    }else if( (MCX == 4 || MCX == 5 || MCX == 6 ) && MCY == 0){
                        if(Integer.valueOf(getChapter()).equals(16)){
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("こっちはまずい。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                }
                            });
                            isFunctionViewContentChanged = true;
                        }else if(Integer.valueOf(getChapter()).equals(21) && Integer.valueOf(getCounter()).equals(1)){
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("まだ何かありそう。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                }
                            });
                        }else{
                            ChangeBG = 13;
                            setMainCharacterIsHide(true);
                            mainCharacter.setPrePoint(new Point(3,10));
                            mainCharacter.drawCharacter();
                        }
                        mcJudge = false;
                    }else if(getChapter() == 16 && getIsStop() && getCounter() == 1 && (getW_unit()*3< touchedX && touchedX < getW_unit()*4) && (getH_unit()*6< touchedY && touchedY < getH_unit()*7)){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 16 && getIsStop() && getCounter() == 5){
                        if(getChangeBG() == 6){
                            setIsStoryChangeable(true);
                        }else if(getIsStop() && ((getW_unit()*4< touchedX && touchedX < getW_unit()*5) && (getH_unit()*5< touchedY && touchedY < getH_unit()*6))){
                            setMainCharacterDirectionByCharacter(4f,5f);
                            setSub20CharacterDirectionForMainCharacter();
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(16211);//親イノシシ
                                    FunctionView.characterStoryEvent();
                                }
                            });
                            isFunctionViewContentChanged = true;
                        }else if(getIsStop() && ((getW_unit()*3< touchedX && touchedX < getW_unit()*4) && (getH_unit()*6< touchedY && touchedY < getH_unit()*7))){
                            setMainCharacterDirectionByCharacter(3f,6f);
                            setSub21CharacterDirectionForMainCharacter();
                        }
                        mcJudge = false;
                    }else if(getChapter() == 21 && getIsStop() && getCounter() < 3 && (getW_unit()*5< touchedX && touchedX < getW_unit()*6) && (getH_unit()*6< touchedY && touchedY < getH_unit()*7)){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 28 && getIsStop() && (getW_unit()*5< touchedX && touchedX < getW_unit()*6) && (getH_unit()*7< touchedY && touchedY < getH_unit()*8)){
                        searchMainCharacterPass(4,7,GameMap.getMapData(getChangeBG()).getCoordinateDataList());
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 43 && getIsStop() && (getW_unit()*5< touchedX && touchedX < getW_unit()*6) && (getH_unit()*7< touchedY && touchedY < getH_unit()*8)){
                        setIsStoryChangeable(true);
                        searchMainCharacterPass(4,7,GameMap.getMapData(3).getCoordinateDataList());
                        mcJudge = false;
                    }
                    break;
                case 4:
                    if(getIsStop() &&(getChapter() < 31)&& (getW_unit()*7< touchedX && touchedX < getW_unit()*8) && (getH_unit()*2< touchedY && touchedY < getH_unit()*3) && Integer.valueOf(getSub12CharacterBgNum()).equals(4)){
                        setMainCharacterDirectionByCharacter(7f,2f);
                        setSub12CharacterDirectionForMainCharacter();
                        if(Integer.valueOf(getChapter()).equals(17) && getHousePass()){
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(17121);
                                }
                            });
                        }else{
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(120);
                                }
                            });
                        }
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }else if(getIsStop() &&(getChapter() < 31)&& (getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*7< touchedY && touchedY < getH_unit()*8) && Integer.valueOf(getSub13CharacterBgNum()).equals(4)){
                        setMainCharacterDirectionByCharacter(1f,7f);
                        setSub13CharacterDirectionForMainCharacter();
                        FunctionView.getmyHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                setCharacterStoryJudge(true);
                                setCharacterStoryNum(3130);
                            }
                        });
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }else if(getIsStop() && (getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*8< touchedY && touchedY < getH_unit()*9) && Integer.valueOf(getSub14CharacterBgNum()).equals(4)){
                        setMainCharacterDirectionByCharacter(1f,8f);
                        setSub14CharacterDirectionForMainCharacter();
                        FunctionView.getmyHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                setCharacterStoryJudge(true);
                                setCharacterStoryNum(3130);
                            }
                        });
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }else if(getChapter() == 37 && getCounter()>17 && getIsStop() && (getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*6< touchedY && touchedY < getH_unit()*7)){
                        setMainCharacterDirectionByCharacter(1f,6f);
                        setSub24CharacterDirectionForMainCharacter();
                        FunctionView.getmyHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                setCharacterStoryJudge(true);
                                setCharacterStoryNum(37241);
                            }
                        });
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }else if(getChapter() == 37 && 22 > getCounter() && getCounter() > 17 && getIsStop() && getSub23CharacterIsShow() && (getW_unit()*6< touchedX && touchedX < getW_unit()*7) && (getH_unit()*2< touchedY && touchedY < getH_unit()*3)){
                        setMainCharacterDirectionByCharacter(6f,2f);
                        setSub23CharacterDirectionForMainCharacter();
                        FunctionView.getmyHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                setCharacterStoryJudge(true);
                                setCharacterStoryNum(37231);
                            }
                        });
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }else if(getChapter() == 37 && getCounter()>17 && getIsStop() && (getW_unit()*4< touchedX && touchedX < getW_unit()*5) && (getH_unit()*5< touchedY && touchedY < getH_unit()*6)){
                        setMainCharacterDirectionByCharacter(4f,5f);
                        setSub22CharacterDirectionForMainCharacter();
                        FunctionView.getmyHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                setCharacterStoryJudge(true);
                                setCharacterStoryNum(37221);
                            }
                        });
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }
                    if ( 7 == MCX && (MCY == 4 || MCY == 5 ) ) {
                        if(Integer.valueOf(getChapter()).equals(37) && Integer.valueOf(getCounter()).equals(21)){
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                FunctionView.getTextView().setText("もう少しこの辺りを探そう。");
                                FunctionView.getTextView().setVisibility(VISIBLE);
                                }
                            });
                            isFunctionViewContentChanged = true;
                        }else{
                            ChangeBG = 14;
                            setMainCharacterIsHide(true);
                            mainCharacter.setPrePoint(new Point(1,5));
                        }
                        mcJudge = false;
                    }else if((MCX == 6 || MCX == 7) && MCY == 0){//池の奧
                        if(Integer.valueOf(getChapter()).equals(37) && Integer.valueOf(getCounter()).equals(21)){
                        }else{
                            ChangeBG = 12;
                            setMainCharacterIsHide(true);
                            mainCharacter.setPrePoint(new Point(5,10));
                            mainCharacter.drawCharacter();
                        }
                        mcJudge = false;
                    }else if(MCX == 5 && MCY == 10){//小屋に移動する
                        if(house){
                            ChangeBG = 15;
                            setMainCharacterIsHide(true);
                            mainCharacter.setPrePoint(new Point(3,8));
                            mainCharacter.drawCharacter();
                        }else{
                            mainCharacter.setMainCharacterDirection(Character.BACK);
//                            mainCharacter.setPrePoint(new Point(5,10,Character.BACK));
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    if(Integer.valueOf(getChapter()).equals(17)){
                                        setCharacterStoryJudge(true);
                                        setCharacterStoryNum(1701);
                                        StoryView.setHousePass(true);//小屋の暗証番号について聞き始める
                                    }else{
                                        FunctionView.getTextView().setText("鍵がかかっている。");
                                        FunctionView.getTextView().setVisibility(VISIBLE);
                                    }
                                    isFunctionViewContentChanged = true;
                                }
                            });
                            //暗証番号入力画面の表示
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.show();
                                }
                            });
                            DialogView.clearS();
                        }
                        mcJudge = false;
                    }else if(getChapter() == 32 && getIsStop() && (getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*5< touchedY && touchedY < getH_unit()*6)){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 34 && getIsStop() && (((getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*5< touchedY && touchedY < getH_unit()*6))||((getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*2< touchedY && touchedY < getH_unit()*3)))){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 35 && getIsStop() && getFood() && (((getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*5< touchedY && touchedY < getH_unit()*6))||((getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*2< touchedY && touchedY < getH_unit()*3)))){
                        setIsStoryChangeable(true);
                        setFood(false);
                        mcJudge = false;
                    }else if(getChapter() == 36 && getIsStop() && MCX == 3 && MCY == 6){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 37 && (getCounter() == 18 || !getSub23CharacterIsShow()) && getIsStop() && (getW_unit()*4< touchedX && touchedX < getW_unit()*5) && (getH_unit()*5< touchedY && touchedY < getH_unit()*6)){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 40 && getIsStop() && getFluteJudge() && (((getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*5< touchedY && touchedY < getH_unit()*6))||((getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*2< touchedY && touchedY < getH_unit()*3)))){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 44 && getIsStop() && MCX == 1 && (MCY == 7 || MCY == 8) /*( (getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*6< touchedY && touchedY < getH_unit()*7) || (getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*7< touchedY && touchedY < getH_unit()*8) )*/ ){
                        if(MCY == 8){
                            searchMainCharacterPass(1,7,GameMap.getMapData(getChangeBG()).getCoordinateDataList());
                        }
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }
                    break;
                case 5:
                    if(getIsStop() && (getW_unit()*7< touchedX && touchedX < getW_unit()*8) && (getH_unit()*9< touchedY && touchedY < getH_unit()*10)){
                        setMainCharacterDirectionByCharacter(7f,9f);
                        setSub6CharacterDirectionForMainCharacter();
                        if(Integer.valueOf(getChapter()).equals(40)){
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(4061);//talk_40_6_1()
                                }
                            });
                        }else if(Integer.valueOf(getChapter()).equals(37)){
                            setSub13CharacterDirectionForMainCharacter();
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("くさの：良いお天気ね。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                }
                            });
                        }else if(Integer.valueOf(getChapter()).equals(38)||Integer.valueOf(getChapter()).equals(39)){

                        }else{
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(360);
                                }
                            });
                        }
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }else if(Integer.valueOf(getChapter()).equals(37) && getIsStop() && (getW_unit()*7< touchedX && touchedX < getW_unit()*8) && (getH_unit()*8< touchedY && touchedY < getH_unit()*9)){
                        setSub14CharacterDirectionForMainCharacter();
                        FunctionView.getmyHandler().post(new Runnable() {//母
                            @Override
                            public void run() {
                                FunctionView.getTextView().setText("くさの：良いお天気ね。");
                                FunctionView.getTextView().setVisibility(VISIBLE);
                            }
                        });
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }
                    if ( 0 == MCX && ( MCY == 4 || MCY == 5 ) ) {
                        ChangeBG = 1;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(6,4));
                        mainCharacter.setPrePoint(new Point(6,4));
                        mainCharacter.drawCharacter();
                        mcJudge = false;
                    } else if ( 7 == MCX && ( MCY == 4 || MCY == 5 ) ) {
                        ChangeBG = 6;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(1,4));
                        mainCharacter.setPrePoint(new Point(1,4));
                        mainCharacter.drawCharacter();
                        mcJudge = false;
                    } else if ( ( MCX == 4 || MCX == 5 ) && 11 == MCY ) {
                        ChangeBG = 2;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(5,1));
                        mainCharacter.drawCharacter();
                        mcJudge = false;
                    } else if ( MCX == 3 && 3 == MCY ) {
                        ChangeBG = 9;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(3,10));
                        mainCharacter.setPrePoint(new Point(3,10));
                        mainCharacter.drawCharacter();
                        mcJudge = false;
                    }
                    break;
                case 6:
                    if(getIsStop() && (getW_unit()*3< touchedX && touchedX < getW_unit()*4) && (getH_unit()*2< touchedY && touchedY < getH_unit()*3)){
                        setMainCharacterDirectionByCharacter(3f,2f);
                        setSub11CharacterDirectionForMainCharacter();
                        if(Integer.valueOf(getChapter()).equals(40)){
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(40111);//talk_40_11_1()
                                }
                            });
                        }else if(Integer.valueOf(getChapter()).equals(38)||Integer.valueOf(getChapter()).equals(39)){

                        }else if(Integer.valueOf(getChapter()).equals(17) && !getHousePass()){
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(17111);//talk_17_11_1()
                                }
                            });
                        }else{
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(3110);
                                }
                            });
                        }
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }else if(getIsStop() && (getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*8< touchedY && touchedY < getH_unit()*9)){
                        mainCharacter.setPrePoint(new Point(1,8));
                    }
                    if ( 0 == MCX && ( MCY == 4 || MCY == 5 ) ) {
                        ChangeBG = 5;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(6,4));
                        mainCharacter.drawCharacter();
                        mcJudge = false;
                    } else if ( 7 == MCX && (MCY == 2 || MCY == 3 || MCY == 4) ) {
                        if((getChapter() == 16 && getCounter() != 0) || getChapter() == 17){
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("まだイノシシが居るかもしれない。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                    isFunctionViewContentChanged = true;
                                }
                            });
                        }else{
                            ChangeBG = 3;
                            setMainCharacterIsHide(true);
                            mainCharacter.setPrePoint(new Point(1,10));
                            mainCharacter.setPrePoint(new Point(1,10));
                            mainCharacter.drawCharacter();
                        }
                        mcJudge = false;
                    } else if ( ( MCX == 5 || MCX == 6 )&& 11 == MCY ) {
                        if(Integer.valueOf(getChapter()).equals(28)){
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("りょうと会っていたら間に合わなくなってしまうかもしれない。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                }
                            });
                            isFunctionViewContentChanged = true;
                        }else{
                            ChangeBG = 7;
                            setMainCharacterIsHide(true);
                            mainCharacter.setPrePoint(new Point(5,1));
                            mainCharacter.drawCharacter();
                        }
                        mcJudge = false;
                    }else if(getChapter() == 10 && getIsStop() && MCX == 1 && MCY == 8){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }
                    break;
                case 7:
                    if ( 0 == MCX && ( MCY == 8 || MCY == 9 ) ) {
                        ChangeBG = 2;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(6,7));
                        mainCharacter.drawCharacter();
                        mcJudge = false;
                    } else if ( ( MCX == 5 || MCX == 6 ) && 0 == MCY ) {
                        ChangeBG = 6;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(5,10));
                        mainCharacter.drawCharacter();
                        mcJudge = false;
                    }else if(getChapter() == 20 && getIsStop() && MCX == 5 && MCY == 8){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 31 && getIsStop() && (getW_unit()*5< touchedX && touchedX < getW_unit()*6) && (getH_unit()*8< touchedY && touchedY < getH_unit()*9)){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 37 && getIsStop() && (((getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*6< touchedY && touchedY < getH_unit()*7 && (MCX == 0 || MCX == 1) && (MCY == 6 || MCY == 7)))||((getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*5< touchedY && touchedY < getH_unit()*6) && (MCX == 0)&& (MCY == 5))||((getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*6< touchedY && touchedY < getH_unit()*7) && (MCX == 2 || MCX == 3) && (MCY == 6 || MCY == 7)))){
                        setIsStoryChangeable(true);
                        searchMainCharacterPass(0,6,GameMap.getMapData(getChangeBG()).getCoordinateDataList());
                        mcJudge = false;
                    }else if(Integer.valueOf(objectList.getObjectList().size()).equals(1)&& getIsStop() && (MCX == 3 && MCY == 2)){//(getW_unit()*3< touchedX && touchedX < getW_unit()*4) && (getH_unit()*2< touchedY && touchedY < getH_unit()*3)
                        if(FunctionView.getFoodEnable()){
                            objectList.getObjectList().remove(0);
                            setFood(true);
                            FunctionView.setFoodEnable(false);
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("きのみを手に入れた。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                }
                            });
                        }else{
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("おいしそうな木の実がおちている。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                }
                            });
                        }
                        isFunctionViewContentChanged = true;
                        mcJudge = false;
                    }
                    break;
                case 8:
                    if( ( MCX == 3 || MCX == 4 ) && 11 == MCY ){
                        if(Integer.valueOf(getChapter()).equals(26) && Integer.valueOf(getCounter()).equals(3)){
                            //nop
                        }else if(Integer.valueOf(getChapter()).equals(30)){
                            //nop
                        }else{
                            ChangeBG = 2;
                            setMainCharacterIsHide(true);
                            mainCharacter.setPrePoint(new Point(2,6));
                            mainCharacter.drawCharacter();
                        }
                        mcJudge = false;
                    }else if(getChapter() == 15 && getIsStop() && MCY < 5){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 22 && getIsStop() && MCY < 5){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 26 && getIsStop() && MCX == 6 && MCY == 3 && LightEffect.getShowTheLight()){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 26 && getIsStop() && MCY < 5 && !LightEffect.getShowTheLight()){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 30 && MCY > 5){
                        setIsStoryChangeable(true);
//                        mainCharacter.setPrePoint(new Point(4,8));
                        mcJudge = false;
                    }else if(getChapter() == 41 && ((MCX == 6 && MCY == 2)||(MCX == 6 && MCY == 3)) ){
                        setIsStoryChangeable(true);
                        mainCharacter.setPrePoint(new Point(6,2,Character.FRONT));
                        mcJudge = false;
                    }else if(getIsStop() && (getW_unit()*5< touchedX && touchedX < getW_unit()*6) && (getH_unit()*7< touchedY && touchedY < getH_unit()*8)){
                        setSub2CharacterDirectionForMainCharacter();
                        CharacterDirectionChanged = true;
                        mainCharacter.setMainCharacterDirectionByCharacter(5f,7f);
                        if(Integer.valueOf(getChapter()).equals(1)){
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(21);
                                }
                            });
                        }else if(Integer.valueOf(getChapter()).equals(3)){
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(321);
                                }
                            });
                        }else{
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(20);
                                }
                            });
                        }
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }
                    break;
                case 9:
                    if(getIsStop() && !(getChapter() == 3) && (getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*7< touchedY && touchedY < getH_unit()*8)){
                        setMainCharacterDirectionByCharacter(2f,7f);
                        setSub4CharacterDirectionForMainCharacter();
                        if(Integer.valueOf(getChapter()).equals(5)){
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(541);
                                }
                            });
                        }else if(Integer.valueOf(getChapter()).equals(9)){
                            //nop
                        }else if(Integer.valueOf(getChapter()).equals(25)){
                            //nop
                        }else{
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(540);
                                }
                            });
                        }
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }
                    if( ( MCX == 3 || MCX == 4 )&& 11 == MCY ){
                        if((Integer.valueOf(getChapter()).equals(4))){
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("しーちゃんの部屋に何かあるかもしれない。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                }
                            });
                            isFunctionViewContentChanged = true;
                        }else{
                            ChangeBG = 5;
                            setMainCharacterIsHide(true);
                            mainCharacter.setPrePoint(new Point(3,4));
                            mainCharacter.drawCharacter();
                        }
                        mcJudge = false;
                    }else if(getChapter() == 3 && getIsStop() && (getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*7< touchedY && touchedY < getH_unit()*8)){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 4 && getIsStop() && (getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*4< touchedY && touchedY < getH_unit()*5)){
                        setIsStoryChangeable(true);
//                        mcJudge = false;
                    }else if(getChapter() == 9 && getIsStop() && (getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*7< touchedY && touchedY < getH_unit()*8)){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 14 && getIsStop() && (getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*4< touchedY && touchedY < getH_unit()*5)){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 25 && getIsStop() && (getW_unit()*2< touchedX && touchedX < getW_unit()*3) && (getH_unit()*7< touchedY && touchedY < getH_unit()*8)){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }
                    break;
                case 11:
                    //いすに座ったときの挙動
                    if(getIsStop() && MCX == 1 && MCY ==3){
                        mainCharacter.setMainCharacterDirection(Character.RIGHT);
                    }else if(getIsStop() && MCX == 1 && MCY == 5){
                        mainCharacter.setMainCharacterDirection(Character.RIGHT);
                    }else if(getIsStop() && MCX == 6 && MCY == 3){
                        mainCharacter.setMainCharacterDirection(Character.LEFT);
                    }else if(getIsStop() && MCX == 6 && MCY == 5){
                        mainCharacter.setMainCharacterDirection(Character.LEFT);
                    }else if(getIsStop() && (getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*9< touchedY && touchedY < getH_unit()*10)){
                        setMainCharacterDirectionByCharacter(1f,9f);
                        setSub8CharacterDirectionForMainCharacter();
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }else if(getIsStop() && (getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*7< touchedY && touchedY < getH_unit()*8)){
                        setMainCharacterDirectionByCharacter(1f,7f);
                        setSub9CharacterDirectionForMainCharacter();
                        if(Integer.valueOf(getChapter()).equals(40)){
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(4091);
                                }
                            });
                        }else{
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(390);
                                }
                            });
                        }
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }else if(getIsStop() && (getW_unit()*6< touchedX && touchedX < getW_unit()*7) && (getH_unit()*7< touchedY && touchedY < getH_unit()*8)){
                        setMainCharacterDirectionByCharacter(6f,7f);
                        if(Integer.valueOf(getChapter()).equals(37)){
                            setSub7CharacterDirectionForMainCharacter();
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("おざわ：ここのコーヒーは香りが良いね。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                }
                            });
                        }else{
                            setSub10CharacterDirectionForMainCharacter();
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(3100);
                                }
                            });
                        }
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }
                    if( ( MCX == 3 || MCX == 4 ) && 11 == MCY ){
                        ChangeBG = 1;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(5,10));
                        mainCharacter.setPrePoint(new Point(5,10));
                        mcJudge = false;
                    }else if(getChapter() == 7 && getIsStop() && (getW_unit()*6< touchedX && touchedX < getW_unit()*7) && (getH_unit()*5< touchedY && touchedY < getH_unit()*6)){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 11 && getIsStop() && getCounter() == 1 && MCX == 6 && MCY == 5){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 13 && getIsStop() && getCounter() == 14 && MCX == 6 && MCY == 5){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }
                    break;
                case 12:
                    if(getIsStop() && (getW_unit()*5< touchedX && touchedX < getW_unit()*6) && (getH_unit()*4< touchedY && touchedY < getH_unit()*5)){
                        setMainCharacterDirectionByCharacter(5f,4f);
                        if(getChapter() == 40 && !getFluteJudge()){
                            //右・左・上・下
                            if(getIsStop() && MCX == 6 && MCY ==4){
                                right = true;
                            }
                            if(getIsStop() && (MCX == 4 && MCY ==4) && right){
                                left = true;
                                right = false;
                            }else if(getIsStop() && left && (MCX == 5 && MCY ==3)){
                                above = true;
                            }else if(getIsStop() && left && above && (MCX == 5 && MCY == 5)){
                                below = true;
                            }else{
                                left = false;
                                above = false;
                                below = false;
                            }
                            if(left && above && below){
                                setFluteJudge(true);
                                setObjectChanger(true);
                                objectList.makeObjectList();
                                FunctionView.getmyHandler().post(new Runnable() {//母
                                    @Override
                                    public void run() {
                                        FunctionView.getTextView().setText("笛を手に入れた。");
                                        FunctionView.getTextView().setVisibility(VISIBLE);
                                        isFunctionViewContentChanged = true;
                                    }
                                });
                            }
                        }else if(Integer.valueOf(objectList.getObjectList().size()).equals(1)){
                            objectList.getObjectList().remove(0);
                        }else{
                            FunctionView.getmyHandler().post(new Runnable() {//母
                                @Override
                                public void run() {
                                    FunctionView.getTextView().setText("石像がある。");
                                    FunctionView.getTextView().setVisibility(VISIBLE);
                                    isFunctionViewContentChanged = true;
                                }
                            });
                        }
                        mcJudge = false;//石像
                    }
                    if( ( MCX == 6 || MCX == 7 ) && 11 == MCY ){
                        ChangeBG = 4;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(6,1));
                        mcJudge = false;
                    }else if(getChapter() == 38 && getIsStop() && getCounter() != 0 && getFood() && (getW_unit()*5< touchedX && touchedX < getW_unit()*6) && (getH_unit()*4< touchedY && touchedY < getH_unit()*5)){
                        if((MCX == 5 && MCY ==3)||(MCX == 4 && MCY ==4)||(MCX == 6 && MCY ==4)){
                            searchMainCharacterPass(5,5,GameMap.getMapData(getChangeBG()).getCoordinateDataList());
                        }
                        setMainCharacterDirectionByCharacter(5f,4f);
                        setIsStoryChangeable(true);
                        setFood(false);
                        mcJudge = false;
                    }else if(getChapter() == 38 && getIsStop() && getCounter() == 0 &&(getW_unit()*5< touchedX && touchedX < getW_unit()*6) && (getH_unit()*4< touchedY && touchedY < getH_unit()*5)){
                        setIsStoryChangeable(true);
                        mcJudge =false;
                    }else if(getChapter() == 40 && getIsStop() &&(getW_unit()*5< touchedX && touchedX < getW_unit()*6) && (getH_unit()*4< touchedY && touchedY < getH_unit()*5)){
                        mcJudge =false;
                    }
                    break;
                case 13:
                    if( ( MCX == 2 || MCX == 3 || MCX == 4 ) && 11 == MCY ){
                        ChangeBG = 3;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(5,1));
                        mainCharacter.setPrePoint(new Point(5,1));
                        mcJudge = false;
                    }else if(getChapter() == 21 && getIsStop() && getCounter() > 3){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 29 && getIsStop() && MCY < 4){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }
                    break;
                case 14:
                    if(getIsStop() &&(getChapter() >= 31)&& (getW_unit()*6< touchedX && touchedX < getW_unit()*7) && (getH_unit()*2< touchedY && touchedY < getH_unit()*3) && Integer.valueOf(getSub12CharacterBgNum()).equals(14)){
                        if(Integer.valueOf(getChapter()).equals(38)||Integer.valueOf(getChapter()).equals(39)){
                        }else{
                            setMainCharacterDirectionByCharacter(6f,2f);
                            setSub12CharacterDirectionForMainCharacter();
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(120);
                                }
                            });
                        }
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }else if(getIsStop() &&(getChapter() >= 31)&& (getW_unit()*2< touchedX && touchedX < getW_unit()*4) && (getH_unit()*10< touchedY && touchedY < getH_unit()*11) && Integer.valueOf(getSub13CharacterBgNum()).equals(14)){
                        if(Integer.valueOf(getChapter()).equals(38)||Integer.valueOf(getChapter()).equals(39)){
                        }else{
                            setMainCharacterDirectionByCharacter(2f,10f);
                            setMainCharacterDirectionByCharacter(3f,10f);
                            setSub13CharacterDirectionForMainCharacter();
                            setSub14CharacterDirectionForMainCharacter();
                            FunctionView.getmyHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    setCharacterStoryJudge(true);
                                    setCharacterStoryNum(3130);
                                }
                            });
                        }
                        isFunctionViewContentChanged = true;
                        CharacterDirectionChanged = true;
                        mcJudge = false;
                    }
                    if(MCX == 0 &&(MCY == 4 || MCY == 5)){
                        ChangeBG = 4;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(6,5));
                        mcJudge = false;
                    }else if(MCX == 7 && (MCY == 9 || MCY == 10)){
                        ChangeBG = 2;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(1,9));
                        mcJudge = false;
                    }
                    break;
                case 15:
                    if((MCX == 3 || MCX == 4) && MCY == 9){
                        ChangeBG = 4;
                        setMainCharacterIsHide(true);
                        mainCharacter.setPrePoint(new Point(5,11,FRONT));
                        mcJudge = false;
                    }else if(getChapter() == 17 && getIsStop() && (getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*3< touchedY && touchedY < getH_unit()*4)){
                        setIsStoryChangeable(true);
                        LightEffect.setLightable(false);
                        LightEffect.turnOffTheLight();
                        mcJudge = false;
                    }else if(getChapter() == 23 && getIsStop() && (getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*3< touchedY && touchedY < getH_unit()*4) && (FunctionView.getCounter() == 3 || FunctionView.getCounter() == 0) && !LightEffect.getLightable()){
                        setIsStoryChangeable(true);
                        mcJudge = false;
                    }else if(getChapter() == 23 && getIsStop() && (getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*3< touchedY && touchedY < getH_unit()*4) && LightEffect.getLightable()){
                        setIsStoryChangeable(true);
//                        LightEffect.setLightable(false);
//                        LightEffect.turnOffTheLight();
                        mcJudgeJudge = false;
                    }else if(getChapter() == 33 && getIsStop() && (getW_unit()*1< touchedX && touchedX < getW_unit()*2) && (getH_unit()*6< touchedY && touchedY < getH_unit()*7) && (FunctionView.getCounter() == 3 || FunctionView.getCounter() == 0)){
                        setIsStoryChangeable(true);//上の座標を釣りをしているおじさんの網
                        if(Integer.valueOf(objectList.getObjectList().size()).equals(8)){
                            objectList.getObjectList().remove(7);
                        }
                        mcJudge = false;
                    }else if(getChapter() == 37 && getSub23CharacterIsShow() && getCounter() == 21 ){
                        setSub23CharacterIsShow(false);
                        setSunsetJudge(true);
                        mcJudge = false;
                    }
                    break;
                case 16:
                    break;

            }
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        setIsStop(false);
        //Log.d("Toched bg",String.valueOf(getChangeBG()));
        if(isFunctionViewContentChanged){
            FunctionView.getmyHandler().post(new Runnable() {
                @Override
                public void run() {
                    FunctionView.getTextView().setText("");
                    FunctionView.getTextView().setVisibility(INVISIBLE);
                    isFunctionViewContentChanged = false;
                }
            });
        }
        if(CharacterDirectionChanged){
            switch (getChangeBG()){
                case 1:
                    sub7Character.setDirection(Character.FRONT);
                    break;
                case 2:
                    sub5Character.setDirection(Character.FRONT);
                    break;
                case 4:
                    sub12Character.setDirection(Character.FRONT);
                    sub13Character.setDirection(Character.RIGHT);
                    sub14Character.setDirection(Character.RIGHT);
                    break;
                case 5:
                    sub6Character.setDirection(Character.FRONT);
                    break;
                case 6:
                    sub11Character.setDirection(Character.FRONT);
                    break;
                case 8:
                    sub2Character.setActiveCharacterDirection(Character.FRONT);
                    break;
                case 9:
                    sub4Character.setDirection(FRONT);
                    break;
                case 11:
                    sub8Character.setDirection(Character.RIGHT);
                    sub9Character.setDirection(Character.RIGHT);
                    sub10Character.setDirection(Character.LEFT);
                    break;
                case 14:
                    sub12Character.setDirection(FRONT);
                    sub13Character.setDirection(BACK);
                    sub14Character.setDirection(BACK);
                    break;
            }
            CharacterDirectionChanged = false;
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //ロードでは無いのでfalse
                isLoad = false;
                isInTheObject = false;
                touchedX = event.getX();
                touchedY = event.getY();
                //下のメニューバーを押したときにキャラクターを移動させるかどうか
                if(H_unit*12 < event.getY()){
                    isList = true;
                    if(W_unit*4<event.getX() && (getCounter() == 0
                            || (Integer.valueOf(getChapter()).equals(20)&& getCounter() == 1)
                            || (Integer.valueOf(getChapter()).equals(21)&& getCounter() == 1)
                            || (Integer.valueOf(getChapter()).equals(37)&& getCounter() == 18)
                            || (Integer.valueOf(getChapter()).equals(38)&& getCounter() == 12))){
                        FunctionView.getmyHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                ListBody.setIsSaveToList(true);
                                FunctionView.setListView(true);
                            }
                        });
                    }else if(W_unit*4>event.getX() && getCounter() == 0 || (Integer.valueOf(getChapter()).equals(21)&& getCounter() == 1)){
                        FunctionView.getmyHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                FunctionView.setConvLog();
                            }
                        });
                    }
                }else if(isLoad){
                    //ロードするとメインキャラクターのprePointがセットされていない
                    mainCharacter.setPrePoint(new Point(save_data[8],save_data[9]));
                    isList =false;
                    int x = (int)(event.getX()/getW_unit());
                    int y = (int)(event.getY()/getH_unit());
                    //Log.d("path","PrePointX:"+mainCharacter.getPrePoint().getX()+" PrePointY:"+mainCharacter.getPrePoint().getY()+" x:"+String.valueOf(x)+" y:"+String.valueOf(y));
                    mainCharacter.searchPath((int)mainCharacter.getPrePoint().getX(),(int)mainCharacter.getPrePoint().getY(),x,y,GameMap.getMapData(getChangeBG()).getCoordinateDataList());
                }else if(!Integer.valueOf(getChangeBG()).equals(16)){
                    isList =false;
                    int x = (int)(event.getX()/getW_unit());
                    int y = (int)(event.getY()/getH_unit());
                    //Log.d("path","PrePointX:"+mainCharacter.getPrePoint().getX()+" PrePointY:"+mainCharacter.getPrePoint().getY()+" x:"+String.valueOf(x)+" y:"+String.valueOf(y));
                    mainCharacter.searchPath((int)mainCharacter.getPrePoint().getX(),(int)mainCharacter.getPrePoint().getY(),x,y,GameMap.getMapData(getChangeBG()).getCoordinateDataList());
                }
                mcJudge = true;
        }
        return false;
    }
    boolean init(Canvas canvas){
        canvas.drawColor(Color.LTGRAY);
        width = canvas.getWidth();
        height = canvas.getHeight();
        setW_unit(width / 8);
        setH_unit(height / 13);
        gameMap = new GameMap(bg1, menuBitmap,width, height);
        mainCharacter = new Character(canvas,MC);
        sub1Character = new Character(canvas,sub1CharacterBmp,9,1);
        sub2Character = new Character(canvas,sub2CharacterBmp,8,2,5f,7f,Character.FRONT);
        sub3Character = new Character(canvas,sub3CharacterBmp,7,3);
        sub4Character = new Character(canvas,sub4CharacterBmp,9,4,2f,7f,Character.FRONT);
        sub5Character = new Character(canvas,sub5CharacterBmp,2,5,7f,3f,Character.FRONT);
        sub6Character = new Character(canvas,sub6CharacterBmp,5,6,7f,9f,Character.FRONT);
        sub7Character = new Character(canvas,sub7CharacterBmp,1,7,1f,7f,Character.FRONT);
        sub8Character = new Character(canvas,sub8CharacterBmp,11,8,1f,9f,Character.RIGHT);
        sub9Character = new Character(canvas,sub9CharacterBmp,11,9,1f,7f,Character.RIGHT);
        sub10Character = new Character(canvas,sub10CharacterBmp,11,10,6f,7f,Character.LEFT);
        sub11Character = new Character(canvas,sub11CharacterBmp,6,11,3f,2f,Character.FRONT);
        sub12Character = new Character(canvas,sub12CharacterBmp,4,12,7f,2f,Character.FRONT);
        sub13Character = new Character(canvas,sub13CharacterBmp,2,13,4f,0f,Character.RIGHT);
        sub14Character = new Character(canvas,sub14CharacterBmp,2,14,5f,0f,Character.LEFT);
        sub15Character = new Character(canvas,sub15CharacterBmp,7,15,5f,9f,Character.FRONT);
        sub16Character = new Character(canvas,sub16CharacterBmp,4,16);
        sub17Character = new Character(canvas,sub17CharacterBmp,4,17);
        sub18Character = new Character(canvas,sub18CharacterBmp,4,18);
        sub19Character = new Character(canvas,sub19CharacterBmp,4,19,2f,2f,Character.FRONT);
        sub20Character = new Character(canvas,sub20CharacterBmp,3,20);
        sub21Character = new Character(canvas,sub21CharacterBmp,3,21);
        sub22Character = new Character(canvas,sub22CharacterBmp,16,22,3f,3f,Character.RIGHT);
        sub23Character = new Character(canvas,sub23CharacterBmp,16,23,4f,3f,Character.LEFT);
        sub24Character = new Character(canvas,sub24CharacterBmp,7,24,4f,3f,Character.LEFT);
        light = new LightEffect(canvas,light1,light2);
        if(!isLoad){
            FunctionView.setTouch_judgeF(true);
        }
        return true;
    }
    //ロードしたときの初期化
    boolean initLoad(Canvas canvas){
        init(canvas);
        FunctionView.setChapter((int)save_data[0]);
        FunctionView.setIsStory((save_data[1]==1)?true:false);
        FunctionView.setIsStoryChangeable((save_data[2]==1)?true:false);
        FunctionView.setCounter((int)save_data[3]);
        FunctionView.setFoodEnable((save_data[4]==1)?true:false);
        FunctionView.setFluteJudge((save_data[5]==1)?true:false);
        Character.setNowX(save_data[6]);
        Character.setNowY(save_data[7]);
        LightEffect.setLightX(save_data[8]);
        LightEffect.setLightY(save_data[9]);
        LightEffect.setLightBGNum((int)save_data[10]);
        LightEffect.setLightable((save_data[11]==1)?true:false);
        LightEffect.setShowTheLight((save_data[12]==1)?true:false);
        ObjectList.setMyBGNum((int)save_data[13]);
        StoryView.setIsAnimation((save_data[14]==1)?true:false);
        StoryView.setHouse((save_data[15]==1)?true:false);
        StoryView.setChangeBG((int)save_data[16]);
        mainCharacter.setPrePoint(new Point(save_data[6],save_data[7]));
        setObjectList();
        setOverObjectList();

        FunctionView.getmyHandler().post(new Runnable() {
            @Override
            public void run() {
                FunctionView.setCharacter(getChapter());
                FunctionView.setListView(false);
            }
        });
        FunctionView.setTouch_judgeF(true);
        setIsLoad(false);
        return true;
    }
}
