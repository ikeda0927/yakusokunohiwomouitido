package com.kohei.ikegon.yakusokunohiwomouitido;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.kohei.ikegon.yakusokunohiwomouitido.Character.BACK;
import static com.kohei.ikegon.yakusokunohiwomouitido.Character.FRONT;
import static com.kohei.ikegon.yakusokunohiwomouitido.Character.LDB;
import static com.kohei.ikegon.yakusokunohiwomouitido.Character.LEFT;
import static com.kohei.ikegon.yakusokunohiwomouitido.Character.RDB;
import static com.kohei.ikegon.yakusokunohiwomouitido.Character.RIGHT;
import static com.kohei.ikegon.yakusokunohiwomouitido.OverObjectList.setSunsetJudge;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getChangeBG;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getH_unit;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getHouse;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getIsStop;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getSub15CharacterDirectionNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getSub1CharacterDirectionNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getSub22CharacterDirectionNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getSub23CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getSub6CharacterDirectionNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getW_unit;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.playMusic;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.searchMainCharacterPass;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setChangeBG;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setIsAnimation;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setIsStoryMusic;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setMainCharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setMainCharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setMainCharacterDirectionByCharacter;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setMainCharacterIsHide;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setMainCharacterIsPassive;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setMainCharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setMainCharacterPointList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setMainCharacterPrePoint;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setMusicID;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setObjectList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setOverObjectList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub10CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub11CharacterDstRectByCoordinate;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub11CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub12CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub12CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub12CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub13CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub13CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub13CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub14CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub14CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub14CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub15CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub15CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub15CharacterDirectionForMainCharacter;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub15CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub15CharacterPointList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub16CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub16CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub16CharacterPointList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub17CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub17CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub17CharacterPointList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub18CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub18CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub18CharacterPointList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub19CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub19CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub19CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub1CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub1CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub1CharacterDirectionForMainCharacter;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub1CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub1CharacterPointList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub20CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub20CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub20CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub20CharacterPointList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub21CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub21CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub21CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub21CharacterPointList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub22CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub22CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub22CharacterDirectionForMainCharacter;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub22CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub22CharacterPointList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub23CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub23CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub23CharacterDirectionForMainCharacter;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub23CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub23CharacterPointList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub24CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub24CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub24CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub24CharacterPointList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub2CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub2CharacterPointList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub3CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub3CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub3CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub3CharacterPointList;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub4CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub4CharacterDirectionForMainCharacter;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub4CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub5CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub6CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub7CharacterBgNum;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub7CharacterDirection;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub7CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub8CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setSub9CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.stopMusic;

//import static com.example.ikego.ikedanorpg1_6.StoryView.setAnimationMainCharacterBgNum;
//import static com.example.ikego.ikedanorpg1_6.StoryView.setAnimationMainCharacterIsShow;
//import static com.example.ikego.ikedanorpg1_6.StoryView.setAnimationMainCharacterPointList;

/**
 * Created by ikego on 2018/03/31.
 */

public class FunctionView extends View {
    private static Context context;
    private static LinearLayout linearLayout;
    private static TextView textView;
    private static TextView textView2;
    private static boolean touch_judgeF = false;
    private static boolean touch_judgeS;
    private static int chapter;
    private static boolean isStory = true;
    private static boolean isViewAnimation;
    //ストーリーの変化の判断(StoryViewとかで判断する時用)
    private static boolean isStoryChangeable = false;
    //章内の進行度をカウントする
    private static int counter;
    //characterStory用のカウンタ
    private static int characterStoryCounter = 0;
    //章内のストーリーの終わりを感知
    private static boolean isChapterPartFinish;
    private static boolean english_card_flag;
    private static boolean insurance_flag;
    //キャラクターとの会話イベント
    private static boolean characterStory = false;
    private static int characterStoryNum = 0;
    //章の終わりを感知
    private static boolean isChapterFinish;
    //河童に渡す食べものよう
    private static boolean food = false;
    private static boolean foodEnable = false;
    //笛
    private static boolean flute = false;

    private static Handler handler;

    private static final int milsWeight = 55;

    //回想用
    private static boolean isFlash = false;

    //endingか否か
    private static boolean isEnding = false;

    private static boolean objectChanger = false;

    //会話用のログ
    private static boolean convLog = false;
    private static TextView textConv;
    private TextView textView3;
    private TextView textView4;
    private ScrollView scrollView;
    private static LinearLayout linearLayout4;

    //fadeout fadeinのアニメーション用
    private FrameLayout frameLayout;
    private final int FP = ViewGroup.LayoutParams.FILL_PARENT;
    private LinearLayout linearLayout2;
    private static boolean linearLayout2Judge = true;

    private static LinearLayout linearLayout3;
    private ListBody listBody;

    private List<Point> pointList = new ArrayList<>();
    private List<Point> sub1PointList = new ArrayList<>();
    private List<Point> sub2PointList = new ArrayList<>();
    private List<Point> sub3PointList = new ArrayList<>();
    private List<Point> sub4PointList = new ArrayList<>();
    private List<Point> sub13PointList = new ArrayList<>();
    private List<Point> sub14PointList = new ArrayList<>();
    private List<Point> sub15PointList = new ArrayList<>();
    private List<Point> sub16PointList = new ArrayList<>();
    private List<Point> sub17PointList = new ArrayList<>();
    private List<Point> sub18PointList = new ArrayList<>();
    private List<Point> sub19PointList = new ArrayList<>();
    private List<Point> sub20PointList = new ArrayList<>();
    private List<Point> sub21PointList = new ArrayList<>();
    private List<Point> sub22PointList = new ArrayList<>();
    private List<Point> sub23PointList = new ArrayList<>();
    private List<Point> sub24PointList = new ArrayList<>();

    public FunctionView(final Context context){
        super(context);
        this.context = context;

        handler = new Handler();

        frameLayout = new FrameLayout(context);
        linearLayout2 = new LinearLayout(context);
        linearLayout2.setBackgroundResource(R.drawable.black_bg);
        linearLayout3 = new LinearLayout(context);


        linearLayout = new LinearLayout(context);
        textView = new TextView(context);
        textView.setBackgroundResource(R.drawable.text_bg);
        textView.setVisibility(INVISIBLE);
        textView.setTextColor(Color.WHITE);

        linearLayout.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(touch_judgeF){
                    if(TopBar.getTopBarHeight() < motionEvent.getY()){
                        switch (chapter){
                            case 1:
                                if(Integer.valueOf(getChangeBG()).equals(7)){
                                    isStory = true;
                                }
                                break;
                            case 3:
                                if(Integer.valueOf(getChangeBG()).equals(9) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 4:
                                if(Integer.valueOf(getChangeBG()).equals(9) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 5:
                                if(Integer.valueOf(getChangeBG()).equals(11) && !LightEffect.getLightable()){
                                    isStory = true;
                                }else if(getW_unit()*5<motionEvent.getX()&&motionEvent.getX()<getW_unit()*6&&getH_unit()*5<motionEvent.getY()&&motionEvent.getY()<getH_unit()*6 && Integer.valueOf(counter).equals(2) && LightEffect.getShowTheLight()){
                                    counter = 3;
                                    LightEffect.setLightable(false);
                                    LightEffect.turnOffTheLight();
                                    scene6_1();
                                }
                                break;
                            case 6:
                                if(Integer.valueOf(getChangeBG()).equals(1) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 7:
                                if(Integer.valueOf(getChangeBG()).equals(11) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 8:
                                if(Integer.valueOf(getChangeBG()).equals(8)){
                                    isStory = true;
                                }
                                break;
                            case 9:
                                if(Integer.valueOf(getChangeBG()).equals(9) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 10:
                                if(Integer.valueOf(getChangeBG()).equals(6) && isStoryChangeable && !LightEffect.getLightable()){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }else if(Integer.valueOf(getChangeBG()).equals(6) && getW_unit()*1<motionEvent.getX() && motionEvent.getX()<getW_unit()*2 && getH_unit()*8<motionEvent.getY() && motionEvent.getY()<getH_unit()*9 && LightEffect.getShowTheLight()){
                                    counter = 5;
                                    LightEffect.setLightable(false);
                                    LightEffect.turnOffTheLight();
                                    scene9();
                                }
                                break;
                            case 11:
                                if(Integer.valueOf(getChangeBG()).equals(11) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                    counter = 2;
                                }else if(Integer.valueOf(getChangeBG()).equals(11)){
                                    isStory = true;
                                }
                                break;
                            case 12:
                                if(Integer.valueOf(getChangeBG()).equals(11) && getW_unit()*5<motionEvent.getX() && motionEvent.getX()<getW_unit()*6 && getH_unit()*5<motionEvent.getY() && motionEvent.getY()<getH_unit()*6 && LightEffect.getShowTheLight()){
                                    isStory = true;
                                    LightEffect.setLightable(false);
                                    LightEffect.turnOffTheLight();
                                }else if(Integer.valueOf(getChangeBG()).equals(1)&& isStoryChangeable){
                                    isStoryChangeable = false;
                                    counter = 3;
                                    scene11();
                                }
                                break;
                            case 13:
                                if(Integer.valueOf(getChangeBG()).equals(1) && isStoryChangeable && Integer.valueOf(getCounter()).equals(0)){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 14:
                                if(Integer.valueOf(getChangeBG()).equals(9) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 15:
                                if(Integer.valueOf(getChangeBG()).equals(8) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 16:
                                if(Integer.valueOf(getChangeBG()).equals(3) /*|| ((Integer.valueOf(getChangeBG()).equals(6)) && getCounter() > 4)*/){
                                    isStory = true;
                                }else if(getCounter() > 4){
                                    setIsStoryChangeable(true);
                                }
                                break;
                            case 17:
                                if(Integer.valueOf(getChangeBG()).equals(15)){
                                    isStory = true;
                                }
                                break;
                            case 18:
                                if(Integer.valueOf(getChangeBG()).equals(16)){
                                    isStory = true;
                                }
                                break;
                            case 19:
                                if(Integer.valueOf(getChangeBG()).equals(15)){
                                    isStory = true;
                                }
                                break;
                            case 20:
                                if(Integer.valueOf(getChangeBG()).equals(7)){
                                    isStory = true;
                                }
                                break;
                            case 21:
                                if(Integer.valueOf(getChangeBG()).equals(3) && !isStoryChangeable){
                                    isStory = true;
                                }else if(Integer.valueOf(getChangeBG()).equals(3) && isStoryChangeable){
                                    isStory = true;
                                    counter = 2;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 22:
                                if(Integer.valueOf(getChangeBG()).equals(8) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                    touch_judgeS = true;
                                }
                                break;
                            case 23:
                                if(Integer.valueOf(getChangeBG()).equals(15) && isStoryChangeable && !LightEffect.getLightable()){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }else if(Integer.valueOf(getChangeBG()).equals(15) && isStoryChangeable && LightEffect.getLightable()){
                                    counter = 4;
                                    LightEffect.setLightable(false);
                                    LightEffect.turnOffTheLight();
                                    isStoryChangeable = false;
                                    isStory = true;
                                }
                                break;
                            case 24:
                                if(Integer.valueOf(getChangeBG()).equals(16)){
                                    isStory = true;
                                    touch_judgeS = true;
                                }
                                break;
                            case 25:
                                if(Integer.valueOf(getChangeBG()).equals(9) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 26:
                                if(Integer.valueOf(getChangeBG()).equals(8) && isStoryChangeable && !LightEffect.getShowTheLight()){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }else if(Integer.valueOf(getChangeBG()).equals(8) && isStoryChangeable && LightEffect.getShowTheLight()){
                                    isStory = true;
                                    counter = 4;
                                    LightEffect.setLightable(false);
                                    LightEffect.turnOffTheLight();
                                    isStoryChangeable = false;
                                }
                                break;
                            case 27:
                                if(Integer.valueOf(getChangeBG()).equals(8)){
                                    isStory = true;
                                }
                                break;
                            case 28:
                                if(Integer.valueOf(getChangeBG()).equals(3) && isStoryChangeable){
                                    searchMainCharacterPass(4,7,GameMap.getMapData(getChangeBG()).getCoordinateDataList());
                                    isStory = true;
                                    isStoryChangeable = false;
                                    touch_judgeS = true;
                                }
                                break;
                            case 29:
                                if(Integer.valueOf(getChangeBG()).equals(13)){
                                    isStory = true;
                                }
                                break;
                            case 30:
                                if(Integer.valueOf(getChangeBG()).equals(8) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                    counter = 2;
                                }else if(Integer.valueOf(getChangeBG()).equals(8)){
                                    isStory = true;
                                }
                                break;
                            case 31:
                                if(Integer.valueOf(getChangeBG()).equals(7) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 32:
                                if(Integer.valueOf(getChangeBG()).equals(4) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 33:
                                if(Integer.valueOf(getChangeBG()).equals(15) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 34:
                                if(Integer.valueOf(getChangeBG()).equals(4) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 35:
                                if(Integer.valueOf(getChangeBG()).equals(4) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 36:
                                if(Integer.valueOf(getChangeBG()).equals(4) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 37:
                                if(Integer.valueOf(getChangeBG()).equals(7) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }else if(Integer.valueOf(getChangeBG()).equals(4) && counter == 18 && isStoryChangeable){
                                    isStory = true;
                                    counter = 19;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 38:
                                if(Integer.valueOf(getChangeBG()).equals(12) && counter == 0 && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }else if(Integer.valueOf(getChangeBG()).equals(12) && isStoryChangeable){
                                    isStory = true;
                                }
                                break;
                            case 39:
                                if(Integer.valueOf(getChangeBG()).equals(5)){
                                    isStory = true;
                                    isStoryChangeable = true;
                                }else if(Integer.valueOf(getChangeBG()).equals(4) && isStoryChangeable){
                                    isStoryChangeable = false;
                                    isStory = true;
                                    counter = 9;
                                }
                                break;
                            case 40:
                                if(Integer.valueOf(getChangeBG()).equals(4) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 41:
                                if(Integer.valueOf(getChangeBG()).equals(8) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 42:
                                if(Integer.valueOf(getChangeBG()).equals(8) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 43:
                                if(Integer.valueOf(getChangeBG()).equals(3) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                            case 44:
                                if(Integer.valueOf(getChangeBG()).equals(4) && isStoryChangeable){
                                    isStory = true;
                                    isStoryChangeable = false;
                                }
                                break;
                        }
                        if(isViewAnimation){
                            touch_judgeS = true;
                        }else{
                            switch (motionEvent.getAction()){
                                case MotionEvent.ACTION_DOWN:
                                    if(isStory){

                                        if(characterStory){
                                            storyEvent(motionEvent);
                                            characterStoryEvent();
                                        }else{
                                            storyEvent(motionEvent);
                                        }
                                    }else if(characterStory){
                                        characterStoryEvent();
                                    }else{
                                        touchEvent(motionEvent);
                                    }break;
                            }
                        }
                    }else{
                        return false;
                    }
                }else{
                    return true;
                }
                return touch_judgeS;
            }
        });
        linearLayout.addView(textView);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        textView2 = new TextView(context);
        textView2.setText(R.string.day_7);
        textView2.setTextColor(Color.WHITE);
        linearLayout2.addView(textView2);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        linearLayout2.setGravity(Gravity.CENTER);
        linearLayout2.setVisibility(INVISIBLE);
        linearLayout3.addView(new ListBody(context).layoutView());
        if(TitleView.getIsLoadFromTitleView()){
            linearLayout3.setVisibility(VISIBLE);
            textView2.setText("");
        }else{
            linearLayout3.setVisibility(INVISIBLE);
            if(linearLayout2Judge){
                //最初の一回のみ表示させる
                linearLayout2.setVisibility(VISIBLE);
            }
        }
        linearLayout4 = new LinearLayout(context);
        textConv = new TextView(context);
        textView4 = new TextView(context);
        textView3 = new TextView(context);
        scrollView = new ScrollView(context);

        textConv.setText("log");
        textConv.setTextColor(Color.WHITE);
        textConv.setTextSize(textView.getTextSize()/2);
        textConv.setPadding(0,(int)(textView.getTextSize()/1.8f),0,0);
        textView4.setText("会話ログ\n");
        textView4.setTextColor(Color.WHITE);
        textView4.setTextSize(textView.getTextSize()/1.6f);
        textView3.setBackgroundColor(Color.BLACK);
        textView3.setText("もどる");
        textView3.setTextColor(Color.CYAN);
        textView3.setTextSize(textView.getTextSize()/2);
        linearLayout4.setBackgroundColor(Color.BLACK);
        linearLayout4.setAlpha(0.8f);
        linearLayout4.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(textConv);
        scrollView.setLayoutParams(new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout4.addView(textView4);
        linearLayout4.addView(textView3);
        linearLayout4.addView(scrollView);
        linearLayout4.setVisibility(INVISIBLE);
        textView3.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linearLayout4.setVisibility(INVISIBLE);
                return false;
            }
        });
        linearLayout4.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        frameLayout.addView(linearLayout,new ViewGroup.LayoutParams(FP,FP));
        frameLayout.addView(linearLayout2, new ViewGroup.LayoutParams(FP,FP));
        frameLayout.addView(linearLayout3,new ViewGroup.LayoutParams(FP,FP));
        frameLayout.addView(linearLayout4,new ViewGroup.LayoutParams(FP,FP));
    }

    public static void setConvLog(){
        convLog = true;
        setTextConv();
        linearLayout4.setVisibility(VISIBLE);
    }
    public static void setListView(boolean bool){
        if(bool){

            linearLayout3.setVisibility(VISIBLE);
            linearLayout3.setClickable(true);
        }else{
            linearLayout3.setVisibility(INVISIBLE);
            linearLayout3.setClickable(false);
        }
    }

    public static int getChapter(){
        return chapter;
    }
    public static void setChapter(int ch){
        chapter = ch;
    }
    public static boolean getIsStory(){
        return isStory;
    }
    public static void setIsStory(boolean bool){
        isStory = bool;
    }
    public static TextView getTextView2(){
        return textView2;
    }
    public static void setIsStoryChangeable(boolean judge){
        isStoryChangeable = judge;
    }
    public static boolean getIsStoryChangeable(){
        return isStoryChangeable;
    }
    public static void setCharacterStoryNum(int num){
        characterStoryNum = num;
    }
    public static void setCharacterStoryJudge(boolean bool){
        characterStory = bool;
    }
    public static TextView getTextView(){
        return textView;
    }
    public static Handler getmyHandler(){
        return handler;
    }
    public static boolean getIsFlash(){
        return isFlash;
    }
    public static int getCounter(){
        return counter;
    }
    public static void setCounter(int co){
        counter = co;
    }
    public static boolean getObjectChanger(){
        return objectChanger;
    }
    public static void setObjectChanger(boolean bool){
        objectChanger = bool;
    }
    public static boolean getFood(){
        return food;
    }
    public static void setFood(boolean bool){
        food = bool;
    }
    public static boolean getFoodEnable(){
        return foodEnable;
    }
    public static void setFoodEnable(boolean bool){
        foodEnable = bool;
    }
    public static void setFluteJudge(boolean bool){
        flute = bool;
    }
    public static boolean getFluteJudge(){
        return flute;
    }
    public static void setTouch_judgeF(boolean bool){
        touch_judgeF = bool;
    }

    public static void setEnding(){
        isEnding = true;
    }
    public static boolean getEnding(){
        return isEnding;
    }

    void fadeOut(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);
        linearLayout2.startAnimation(alphaAnimation);
    }
    void fadeIn(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);
        linearLayout2.startAnimation(alphaAnimation);
    }
    static void setStoryMusic(int mId){
        setIsStoryMusic(true);
        stopMusic();
        setMusicID(mId);
        playMusic();
    }
    void resetMusic(int mId){
        setIsStoryMusic(false);
        stopMusic();
        setMusicID(mId);
        playMusic();
    }

    boolean touchEvent(MotionEvent motionEvent){
        return touch_judgeS;
    }

    void storyEvent(MotionEvent motionEvent){
        switch(chapter){
            case 0:
                scene1();
                break;
            case 1:
                scene2();
                break;
            case 2:
                scene3();
                break;
            case 3:
                scene4();
                break;
            case 4:
                scene5();
                break;
            case 5:
                scene6_1();
                break;
            case 6:
                scene6_2();
                break;
            case 7:
                scene6_3();
                break;
            case 8:
                scene7();
                break;
            case 9:
                scene8();
                break;
            case 10:
                scene9();
                break;
            case 11:
                scene10();
                break;
            case 12:
                scene11();
                break;
            case 13:
                scene12();
                break;
            case 14:
                scene14();
                break;
            case 15:
                scene15();
                break;
            case 16:
                scene16();
                break;
            case 17:
                scene17();
                break;
            case 18:
                scene18();
                break;
            case 19:
                scene19();
                break;
            case 20:
                scene20();
                break;
            case 21:
                scene21();
                break;
            case 22:
                scene22();
                break;
            case 23:
                scene23();
                break;
            case 24:
                scene24();
                break;
            case 25:
                scene25();
                break;
            case 26:
                scene26();
                break;
            case 27:
                scene27();
                break;
            case 28:
                scene28();
                break;
            case 29:
                scene29();
                break;
            case 30:
                scene30();
                break;
            case 31:
                scene31();
                break;
            case 32:
                scene32();
                break;
            case 33:
                scene33();
                break;
            case 34:
                scene34();
                break;
            case 35:
                scene35();
                break;
            case 36:
                scene36();
                break;
            case 37:
                scene37();
                break;
            case 38:
                scene38();
                break;
            case 39:
                scene39();
                break;
            case 40:
                scene40();
                break;
            case 41:
                scene41();
                break;
            case 42:
                scene42();
                break;
            case 43:
                scene43();
                break;
            case 44:
                scene44();
                break;
        }
        if(isChapterPartFinish){
            counter = 0;
            chapter += 1;
            isStory = false;
            textView.setVisibility(INVISIBLE);
            isChapterPartFinish = false;
            touch_judgeS = false;
            setMainCharacterIsPassive(false);
            setIsAnimation(false);
        }else if(isChapterFinish){
            isChapterFinish = false;
            counter = 0;
            chapter+=1;
            isStory = true;
            textView.setVisibility(INVISIBLE);
            touch_judgeS = true;
            setMainCharacterIsPassive(false);
            setIsAnimation(true);
        }
    }
    void scene1(){
        //起きて目が覚めたとき
        switch (counter){
            case 0:
                //アニメーションの開始
                textView2.setText(R.string.day_7);
                setIsAnimation(true);
                setMainCharacterIsPassive(true);
                setMainCharacterBgNum(8);
                setMainCharacterIsShow(true);
                setMainCharacterIsHide(true);
                pointList.add(new Point(6f,2.5f));
                setMainCharacterPointList(pointList);
                setCharacter(1);//主人公以外の表示設定？
                fadeOut();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setText("");
                        textView2.setVisibility(VISIBLE);
                    }
                },1000);
                //linearLayout2を一度だけ表示させるため
                linearLayout2Judge = false;
                textView.setText(R.string.ring_1_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                postDelayed(6);
                textView.setText(R.string.MC_1_1);
                textView.setVisibility(VISIBLE);
                pointList.clear();
                pointList.add(new Point(6,3, FRONT));
                pointList.add(new Point(6,3, FRONT));
                pointList.add(new Point(6,3,Character.LEFT));
                pointList.add(new Point(5,3,Character.LEFT));
                pointList.add(new Point(5,3,Character.BACK));
                pointList.add(new Point(5,3,Character.BACK));
                setMainCharacterPointList(pointList);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.SC_1_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                postDelayed(21);
                textView.setText(R.string.MC_1_2);
                textView.setVisibility(VISIBLE);
                setMainCharacterIsShow(true);
                pointList.clear();
                pointList.add(new Point(5,3,Character.LEFT));
                pointList.add(new Point(4.5f,3f,Character.LEFT));
                pointList.add(new Point(4,3,Character.LEFT));
                pointList.add(new Point(3.5f,3f,Character.LEFT));
                pointList.add(new Point(3,3,Character.LEFT));
                pointList.add(new Point(2.5f,3.5f,Character.LDF));
                pointList.add(new Point(2,4,Character.LDF));
                pointList.add(new Point(2f,4.5f, FRONT));
                pointList.add(new Point(2,5, FRONT));
                pointList.add(new Point(2f,5.5f, FRONT));
                pointList.add(new Point(2,6, FRONT));
                pointList.add(new Point(2f,6.5f, FRONT));
                pointList.add(new Point(2,7, FRONT));
                pointList.add(new Point(2f,7.5f, FRONT));
                pointList.add(new Point(2,8, FRONT));
                pointList.add(new Point(2f,8.5f, FRONT));
                pointList.add(new Point(2,9, FRONT));
                pointList.add(new Point(2.5f,9.5f,Character.RDF));
                pointList.add(new Point(3,10,Character.RDF));
                pointList.add(new Point(3f,10.5f, FRONT));
                pointList.add(new Point(3,11, FRONT));
                setMainCharacterPointList(pointList);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.T_1_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView2.setText("");
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setChangeBG(2);
                        setMainCharacterBgNum(2);
                        pointList.add(new Point(2,6));
                        setMainCharacterPointList(pointList);
                        setMainCharacterPrePoint(new Point(2,6));
                        textView.setVisibility(INVISIBLE);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation = false;
                                textView.setText(R.string.SC_1_2);
                                textView.setVisibility(VISIBLE);
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.MC_1_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.SC_1_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.MC_1_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                textView.setText(R.string.MC_1_5);
                textView.setVisibility(VISIBLE);
                setSub20CharacterIsShow(true);
                break;
            case 10:
                isChapterPartFinish = true;
                setMainCharacterIsHide(false);
                break;
        }
        counter++;
    }
    void scene2(){
        //公民館に行くとき
        switch (counter){
            case 0:
                postDelayed(15);
                setMainCharacterIsPassive(true);
                setMainCharacterBgNum(7);
                setSub13CharacterBgNum(4,1f,7f);
                setSub14CharacterBgNum(4,1f,8f);
                setSub13CharacterDirection(Character.RIGHT);
                setSub14CharacterDirection(Character.RIGHT);
                setIsAnimation(true);
                textView.setVisibility(VISIBLE);
                //移動アニメーションを有効にしたいときはfalseにする
                touch_judgeS = true;
//                //fadeIn中のタッチの無効化
//                isViewAnimation = true;
//                //fadeOut();
//                fadeIn();
//                //黒い画面がfadeInした数秒後に自動でfadeOutするためのHandler
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        textView.setDateText(R.string.SC_1_3);
////                        textView.setVisibility(VISIBLE);
//                        fadeOut();
//                        touch_judgeS = true;
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                //タッチの無効化を解除
//                                isViewAnimation = false;
//                                textView.setDateText(R.string.SC_1_3);
//                                textView.setVisibility(VISIBLE);
//                            }
//                        },1000);
//                    }
//                },1500);
                //表示の許可
//                setAnimationMainCharacterIsShow(true);
                setMainCharacterIsShow(true);
                pointList.clear();
                pointList.add(new Point(0,8,Character.RIGHT));
                pointList.add(new Point(1,8,Character.RIGHT));
                pointList.add(new Point(2,8,Character.RIGHT));
                pointList.add(new Point(3,8,Character.RIGHT));
                pointList.add(new Point(4,8,Character.RIGHT));
                pointList.add(new Point(5,8,Character.RIGHT));
                pointList.add(new Point(5,8,Character.LEFT));
                setMainCharacterPointList(pointList);
                setSub3CharacterIsShow(true);
                sub3PointList.clear();
                sub3PointList.add(new Point(-1,-1));
                sub3PointList.add(new Point(-1,-1));
                sub3PointList.add(new Point(-1,-1));
                sub3PointList.add(new Point(-1,-1));
                sub3PointList.add(new Point(-1,-1));
                sub3PointList.add(new Point(-1,-1));
                sub3PointList.add(new Point(-1,-1));
                sub3PointList.add(new Point(-1,-1));
                sub3PointList.add(new Point(-1,-1));
                sub3PointList.add(new Point(-1,-1));
                sub3PointList.add(new Point(1,5, FRONT));
                sub3PointList.add(new Point(1,6, FRONT));
                sub3PointList.add(new Point(1,7,Character.FRONT));
                sub3PointList.add(new Point(1,8,Character.FRONT));
                sub3PointList.add(new Point(2,8,Character.RIGHT));
                sub3PointList.add(new Point(3,8,Character.RIGHT));
                sub3PointList.add(new Point(4,8,Character.RIGHT));
                setSub3CharacterPointList(sub3PointList);
                textView.setText(R.string.SC_2_1);
                textView.setVisibility(VISIBLE);
                break;
            case 1:
                textView.setText(R.string.MC_2_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.SC_2_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_2_2);
                textView.setVisibility(VISIBLE);
                pointList.add(new Point(5,8,Character.BACK));
                setMainCharacterPointList(pointList);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.SC_2_3);
                textView.setVisibility(VISIBLE);
                sub3PointList.add(new Point(4,8,Character.BACK));
                setSub3CharacterPointList(sub3PointList);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.T_2_1);
                textView.setVisibility(VISIBLE);
                pointList.add(new Point(5,8,Character.LEFT));
                setMainCharacterPointList(pointList);
                sub3PointList.add(new Point(4,8,Character.RIGHT));
                setSub3CharacterPointList(sub3PointList);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.MC_2_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.SC_2_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.MC_2_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                textView.setText(R.string.SC_2_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                postDelayed(7);
                textView.setText(R.string.MC_2_5);
                textView.setVisibility(VISIBLE);
                pointList.clear();
                pointList.add(new Point(5,8, FRONT));
                pointList.add(new Point(5f,8.5f, FRONT));
                pointList.add(new Point(5,9, FRONT));
                pointList.add(new Point(5f,9.5f, FRONT));
                pointList.add(new Point(5,10, FRONT));
                pointList.add(new Point(5f,10.5f, FRONT));
                pointList.add(new Point(5,14, FRONT));
                sub3PointList.clear();
                sub3PointList.add(new Point(4,8, FRONT));
                sub3PointList.add(new Point(4f,8.5f, FRONT));
                sub3PointList.add(new Point(4,9, FRONT));
                sub3PointList.add(new Point(4f,9.5f, FRONT));
                sub3PointList.add(new Point(4,10, FRONT));
                sub3PointList.add(new Point(4f,10.5f, FRONT));
                sub3PointList.add(new Point(4,14, FRONT));
                setMainCharacterPointList(pointList);
                setSub3CharacterPointList(sub3PointList);
                touch_judgeS = true;
                break;
            case 11:
                textView.setVisibility(INVISIBLE);
                isViewAnimation = true;
                textView2.setText("");
                fadeIn();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //sub3characterの非表示
                        setSub3CharacterIsShow(false);
                        textView2.setText(R.string.T_2_2);
                        textView2.setVisibility(VISIBLE);
                        isViewAnimation = false;
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 12:
                setChangeBG(8);
                textView2.setText(R.string.day_8);
                touch_judgeS = true;
                break;
            case 13:
                isChapterFinish = true;
                setMainCharacterIsHide(false);
                break;
        }
        counter++;
    }
    void scene3(){
        switch (counter){
            case 0:
                isViewAnimation = true;
                setIsAnimation(true);
                setMainCharacterIsHide(false);
                setMainCharacterPrePoint(new Point(6f,2.5f));
                setMainCharacterDirection(FRONT);
                isViewAnimation = true;
                //日付の設定
                TopBar.setDateText(R.string.day_8);
                fadeOut();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setVisibility(INVISIBLE);
                        textView2.setText("");
                        setSub2CharacterIsShow(true);
                        postDelayed(20);
                        //移動の許可
                        sub2PointList.clear();
                        sub2PointList.add(new Point(5,7, FRONT));
                        sub2PointList.add(new Point(5,7,Character.LEFT));
                        sub2PointList.add(new Point(4.5f,7f,Character.LEFT));
                        sub2PointList.add(new Point(3.5f,7f,Character.LEFT));
                        sub2PointList.add(new Point(3,7,Character.LEFT));
                        sub2PointList.add(new Point(2.5f,7f,Character.LEFT));
                        sub2PointList.add(new Point(2,7,Character.LEFT));
                        sub2PointList.add(new Point(2f,6.5f,Character.BACK));
                        sub2PointList.add(new Point(2,6,Character.BACK));
                        sub2PointList.add(new Point(2f,5.5f,Character.BACK));
                        sub2PointList.add(new Point(2,5,Character.BACK));
                        sub2PointList.add(new Point(2f,4.5f,Character.BACK));
                        sub2PointList.add(new Point(2,4,Character.BACK));
                        sub2PointList.add(new Point(2f,3.5f,Character.BACK));
                        sub2PointList.add(new Point(2,3,Character.BACK));
                        sub2PointList.add(new Point(2.5f,3f,Character.RIGHT));
                        sub2PointList.add(new Point(3,3,Character.RIGHT));
                        sub2PointList.add(new Point(3.5f,3f,Character.RIGHT));
                        sub2PointList.add(new Point(4,3,Character.RIGHT));
                        setSub2CharacterPointList(sub2PointList);
                        isViewAnimation = false;
                    }
                },1000);
                break;
            case 1:
                textView.setText(R.string.SC_3_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_3_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.SC_3_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_3_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.SC_3_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.SC_3_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.MC_3_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.SC_3_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                postDelayed(18);
                textView.setText(R.string.MC_3_4);
                textView.setVisibility(VISIBLE);
                sub2PointList.clear();
                sub2PointList.add(new Point(3.5f,3f,Character.LEFT));
                sub2PointList.add(new Point(3,3,Character.LEFT));
                sub2PointList.add(new Point(2.5f,3f,Character.LEFT));
                sub2PointList.add(new Point(2,3, FRONT));
                sub2PointList.add(new Point(2f,3.5f, FRONT));
                sub2PointList.add(new Point(2,4, FRONT));
                sub2PointList.add(new Point(2f,4.5f, FRONT));
                sub2PointList.add(new Point(2,5, FRONT));
                sub2PointList.add(new Point(2f,5.5f, FRONT));
                sub2PointList.add(new Point(2,6, FRONT));
                sub2PointList.add(new Point(2f,6.5f, FRONT));
                sub2PointList.add(new Point(2,7,Character.RIGHT));
                sub2PointList.add(new Point(2.5f,7f,Character.RIGHT));
                sub2PointList.add(new Point(3,7,Character.RIGHT));
                sub2PointList.add(new Point(3.5f,7f,Character.RIGHT));
                sub2PointList.add(new Point(4.5f,7f,Character.RIGHT));
                sub2PointList.add(new Point(5,7,Character.RIGHT));
                sub2PointList.add(new Point(5,7, FRONT));
                touch_judgeS = true;
                break;
            case 10:
                isChapterPartFinish = true;
                break;
        }
        counter++;
    }
    void scene4(){
        switch (counter){
            case 0:
                setMainCharacterDirectionByCharacter(2f,7f);
                setSub4CharacterDirection(Character.RIGHT);
                textView.setText(R.string.SC_4_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.T_4_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_4_1);
                textView.setVisibility(VISIBLE);
//                isViewAnimation = true;
//                fadeIn();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        StoryView.bg_changer = 8;
//                        textView.setVisibility(INVISIBLE);
//                        fadeOut();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                textView2.setVisibility(INVISIBLE);
//                                isViewAnimation = false;
//                            }
//                        },1000);
//                    }
//                },1500);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.SC_4_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_4_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                setSub4CharacterDirection(FRONT);
                isViewAnimation = true;
                textView.setVisibility(INVISIBLE);
                textView2.setText(R.string.T_4_2);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isViewAnimation = false;
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 6:
                isViewAnimation = true;
                fadeOut();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setText("");
                        isViewAnimation = false;
                        isChapterPartFinish = true;
                    }
                },1000);
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene5(){
        switch (counter){
            case 0:
                setMainCharacterDirectionByCharacter(2f,4f);
                textView.setText(R.string.MC_5_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1://flash
                textView2.setText("");
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setChangeBG(16);
                        setMainCharacterIsHide(true);
                        setSub22CharacterIsShow(true);
                        setSub23CharacterIsShow(true);
                        //日付の設定
                        TopBar.setDateText(R.string.day_);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation = false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.T_5_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.SC_5_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_5_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.T_5_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView2.setText("");
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setChangeBG(9);
                        setMainCharacterIsHide(false);
                        setSub22CharacterIsShow(false);
                        setSub23CharacterIsShow(false);
                        //日付の設定
                        TopBar.setDateText(R.string.day_8);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation = false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.MC_5_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene6_1(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_6_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                LightEffect.setLightable(true);
                LightEffect.setLightX(getW_unit()*5);
                LightEffect.setLightY(getH_unit()*5);
                LightEffect.setLightBGNum(getChangeBG());
                break;
            case 1://光にふれるまで先に進まないようにする
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
            case 2:
                touch_judgeS = false;
                counter = 1;
                break;
            case 3:
                textView.setText(R.string.MC_6_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4://三日前に戻る
                textView2.setText(R.string.day_);
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        objectChanger = true;
                        //日付の設定
                        TopBar.setDateText(R.string.day_);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation = false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.MC_6_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.MC_6_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                isChapterPartFinish = true;
                touch_judgeS = true;
                setSub1CharacterBgNum(1,4f,4f);
                setSub1CharacterIsShow(true);
                setSub1CharacterDirection(Character.BACK);
                break;
        }
        counter++;
    }
    void scene6_2(){
        touch_judgeS = true;
        if(getIsStop()){
            switch (counter){
                case 0:
                    setMainCharacterDirectionByCharacter(4f,4f);
                    setSub1CharacterDirectionForMainCharacter();
                    textView.setText(R.string.SC_6_1);
                    textView.setVisibility(VISIBLE);
                    touch_judgeS = true;
                    break;
                case 1:
                    textView.setText(R.string.MC_6_5);
                    textView.setVisibility(VISIBLE);
                    touch_judgeS = true;
                    break;
                case 2:
                    textView.setText(R.string.SC_6_2);
                    textView.setVisibility(VISIBLE);
                    touch_judgeS = true;
                    break;
                case 3:
                    textView.setText(R.string.MC_6_6);
                    textView.setVisibility(VISIBLE);
                    touch_judgeS = true;
                    break;
                case 4:
                    textView.setText(R.string.SC_6_3);
                    textView.setVisibility(VISIBLE);
                    touch_judgeS = true;
                    break;
                case 5:
                    textView.setText(R.string.MC_6_7);
                    textView.setVisibility(VISIBLE);
                    touch_judgeS = true;
                    break;
                case 6:
                    textView.setText(R.string.SC_6_4);
                    textView.setVisibility(VISIBLE);
                    touch_judgeS = true;
                    break;
                case 7:
                    textView.setText(R.string.MC_6_8);
                    textView.setVisibility(VISIBLE);
                    //日付の設定
                    TopBar.setDateText(R.string.day_5_);
                    touch_judgeS = true;
                    break;
                case 8:
                    textView.setText(R.string.SC_6_5);
                    textView.setVisibility(VISIBLE);
                    touch_judgeS = true;
                    break;
                case 9:
                    textView.setText(R.string.MC_6_9);
                    textView.setVisibility(VISIBLE);
                    touch_judgeS = true;
                    break;
                case 10:
                    postDelayed(10);
                    textView.setText(R.string.SC_6_6);
                    textView.setVisibility(VISIBLE);
                    touch_judgeS = true;
                    if(Character.RIGHT == getSub1CharacterDirectionNum()){
                        sub1PointList.clear();
                        sub1PointList.add(new Point(4.5f,4.5f, FRONT));
                        sub1PointList.add(new Point(4.5f,5f, FRONT));
                        sub1PointList.add(new Point(4.5f,5f,Character.RIGHT));
                        sub1PointList.add(new Point(5,5,Character.RIGHT));
                        sub1PointList.add(new Point(5.5f,5f,Character.RIGHT));
                        sub1PointList.add(new Point(6,5,Character.RIGHT));
                        sub1PointList.add(new Point(6.5f,5f,Character.RIGHT));
                        sub1PointList.add(new Point(7,5,Character.RIGHT));
                        sub1PointList.add(new Point(7.5f,5f,Character.RIGHT));
                        sub1PointList.add(new Point(8,5,Character.RIGHT));
                    }else{
                        sub1PointList.clear();
                        sub1PointList.add(new Point(4.5f,4f,Character.RIGHT));
                        sub1PointList.add(new Point(5,4,Character.RIGHT));
                        sub1PointList.add(new Point(5.5f,4f,Character.RIGHT));
                        sub1PointList.add(new Point(6,4,Character.RIGHT));
                        sub1PointList.add(new Point(6.5f,4f,Character.RIGHT));
                        sub1PointList.add(new Point(7,4,Character.RIGHT));
                        sub1PointList.add(new Point(7.5f,4f,Character.RIGHT));
                        sub1PointList.add(new Point(8,4,Character.RIGHT));
                    }
                    setSub1CharacterPointList(sub1PointList);
                    break;
                case 11:
                    textView.setText(R.string.MC_6_10);
                    textView.setVisibility(VISIBLE);
                    touch_judgeS = true;
                    break;
                case 12:
                    isChapterPartFinish = true;
                    touch_judgeS = true;
                    break;
            }
        }
        counter++;
    }
    void scene6_3(){
        switch (counter){
            case 0:
                setSub1CharacterIsShow(false);
                textView.setText(R.string.MC_6_11);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView2.setText(R.string.day_8);
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setVisibility(INVISIBLE);
                        //日付の設定
                        TopBar.setDateText(R.string.day_8);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation =false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_6_12);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView2.setText("");
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isViewAnimation = false;
                    }
                },1000);
                textView2.setText(R.string.T_6_1_1);
                textView2.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView2.setText(R.string.T_6_1_2);
                textView2.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView2.setText(R.string.T_6_2_1);
                textView2.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView2.setText(R.string.T_6_2_2);
                textView2.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView2.setText(R.string.T_6_3);
                textView2.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                fadeOut();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isViewAnimation = false;
                        textView2.setText("");
                        textView2.setVisibility(INVISIBLE);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 9:
                textView.setText(R.string.MC_6_13);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                textView2.setText(R.string.day_9);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setChangeBG(8);
                        pointList.add(new Point(6f,2.5f, FRONT));
                        setMainCharacterPrePoint(new Point(6f,2.5f, FRONT));
                        setMainCharacterPointList(pointList);
                        textView.setText("");
                        textView.setVisibility(INVISIBLE);
                        //日付の設定
                        TopBar.setDateText(R.string.day_9);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation = false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 11:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene7(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_7_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene8(){
        switch (counter){
            case 0:
                setMainCharacterDirectionByCharacter(2f,7f);
                setSub4CharacterDirectionForMainCharacter();
                textView.setText(R.string.SC_8_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.MC_8_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_8_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.SC_8_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_8_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                setSub4CharacterDirection(FRONT);
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene9(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_9_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                touch_judgeS = true;
                LightEffect.setLightable(true);
                LightEffect.setLightX(getW_unit()*1.25f);
                LightEffect.setLightY(getH_unit()*8.5f);
                LightEffect.setLightBGNum(getChangeBG());
                break;
            case 2:
                textView.setText(R.string.MC_9_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_9_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4://光に触れるため
                textView.setVisibility(INVISIBLE);
                counter = 3;
                touch_judgeS = true;
                break;
            case 5:
                textView2.setText(R.string.day_);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //日付の設定
                        TopBar.setDateText(R.string.day_);
                        setSunsetJudge(true);
                        setOverObjectList();
                        fadeOut();
                        textView.setVisibility(INVISIBLE);
                        setSub11CharacterIsShow(false);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation =false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.MC_9_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.MC_9_5);
                textView.setVisibility(VISIBLE);
                //日付の設定
                TopBar.setDateText(R.string.day_6_);
                touch_judgeS = true;
                break;
            case 8:
                postDelayed(9);
                textView.setText(R.string.MC_9_6);
                setSub1CharacterIsShow(true);
                setSub1CharacterBgNum(6,7f,4f);
                setSub1CharacterDirection(Character.LEFT);
                sub1PointList.add(new Point(7f,4f,Character.LEFT));
                sub1PointList.add(new Point(6.5f,4f,Character.LEFT));
                sub1PointList.add(new Point(6f,4f,Character.LEFT));
                sub1PointList.add(new Point(5.5f,4f,Character.LEFT));
                sub1PointList.add(new Point(5f,4f,Character.LEFT));
                sub1PointList.add(new Point(4.5f,4f,Character.LEFT));
                sub1PointList.add(new Point(4f,4f,Character.LEFT));
                sub1PointList.add(new Point(4f,4f,Character.BACK));
                sub1PointList.add(new Point(4f,4f,Character.RIGHT));
                setSub1CharacterPointList(sub1PointList);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                setMainCharacterDirection(Character.BACK);
                textView.setText(R.string.MC_9_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                postDelayed(10);
                textView.setText(R.string.MC_9_8);
                sub1PointList.add(new Point(4f,4f,Character.BACK));
                sub1PointList.add(new Point(4f,4f,Character.LEFT));
                sub1PointList.add(new Point(3.5f,4f,Character.LEFT));
                sub1PointList.add(new Point(3f,4f,Character.LEFT));
                sub1PointList.add(new Point(2.5f,4f,Character.LEFT));
                sub1PointList.add(new Point(2f,4f,Character.LEFT));
                sub1PointList.add(new Point(1.5f,4f,Character.LEFT));
                sub1PointList.add(new Point(1f,4f,Character.LEFT));
                sub1PointList.add(new Point(0.5f,4f,Character.LEFT));
                sub1PointList.add(new Point(0f,4f,Character.LEFT));
                setSub1CharacterPointList(sub1PointList);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 11:
                textView.setText(R.string.MC_9_9);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 12:
                textView.setText(R.string.MC_9_10);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 13:
                textView.setText(R.string.MC_9_11);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 14:
                textView2.setText(R.string.day_9);
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setSub1CharacterIsShow(false);
                        textView.setVisibility(INVISIBLE);
                        //date configure
                        TopBar.setDateText(R.string.day_9);
                        setSunsetJudge(false);
                        setOverObjectList();
                        fadeOut();
                        setSub11CharacterIsShow(true);
                        setSub11CharacterDstRectByCoordinate(3f,2f);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation =false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 15:
                textView.setText(R.string.MC_9_12);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 16:
                textView.setText(R.string.MC_9_13);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 17:
                textView.setText(R.string.MC_9_14);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 18:
                textView.setText(R.string.MC_9_15);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 19:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene10(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_10_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                if(isStoryChangeable){
                    isStoryChangeable = false;
                    touch_judgeS = true;
                }else{
                    counter = 0;
                    touch_judgeS = false;
                    textView.setVisibility(INVISIBLE);
                }
                break;
            case 2:
                textView.setText(R.string.MC_10_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                LightEffect.setLightable(true);
                LightEffect.setLightX(getW_unit()*5);
                LightEffect.setLightY(getH_unit()*5);
                LightEffect.setLightBGNum(getChangeBG());
                break;
            case 3:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene11(){
        switch (counter){
            case 0:
                textView2.setText(R.string.day_5);
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        objectChanger = true;
                        setSub1CharacterBgNum(1,4f,4f);
                        setSub1CharacterIsShow(true);
                        setSub1CharacterDirection(Character.BACK);
                        //date configure
                        TopBar.setDateText(R.string.day_5);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation =false;
                                textView.setText(R.string.MC_11_1);
                                textView.setVisibility(VISIBLE);
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.MC_11_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setVisibility(INVISIBLE);
                counter = 1;
                touch_judgeS = false;
                break;
            case 3:
                setMainCharacterDirectionByCharacter(4f,4f);
                textView.setText(R.string.MC_11_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                setSub1CharacterDirectionForMainCharacter();
                textView.setText(R.string.SC_11_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                isChapterFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene12(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_12_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.SC_12_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_12_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_12_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.SC_12_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.MC_12_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.MC_12_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.SC_12_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.MC_12_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                textView.setText(R.string.SC_12_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                textView.setText(R.string.MC_14_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 11:
                textView.setText(R.string.SC_14_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 12:
                postDelayed(10);
                if(Character.RIGHT == getSub1CharacterDirectionNum()){
                    sub1PointList.clear();
                    sub1PointList.add(new Point(4.5f,4.5f, FRONT));
                    sub1PointList.add(new Point(4.5f,5f, FRONT));
                    sub1PointList.add(new Point(4.5f,5f,Character.RIGHT));
                    sub1PointList.add(new Point(5,5,Character.RIGHT));
                    sub1PointList.add(new Point(5.5f,5f,Character.RIGHT));
                    sub1PointList.add(new Point(6,5,Character.RIGHT));
                    sub1PointList.add(new Point(6.5f,5f,Character.RIGHT));
                    sub1PointList.add(new Point(7,5,Character.RIGHT));
                    sub1PointList.add(new Point(7.5f,5f,Character.RIGHT));
                    sub1PointList.add(new Point(8,5,Character.RIGHT));
                }else{
                    sub1PointList.clear();
                    sub1PointList.add(new Point(4.5f,4f,Character.RIGHT));
                    sub1PointList.add(new Point(5,4,Character.RIGHT));
                    sub1PointList.add(new Point(5.5f,4f,Character.RIGHT));
                    sub1PointList.add(new Point(6,4,Character.RIGHT));
                    sub1PointList.add(new Point(6.5f,4f,Character.RIGHT));
                    sub1PointList.add(new Point(7,4,Character.RIGHT));
                    sub1PointList.add(new Point(7.5f,4f,Character.RIGHT));
                    sub1PointList.add(new Point(8,4,Character.RIGHT));
                }
                setSub1CharacterPointList(sub1PointList);
                textView.setText(R.string.T_14_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 13:
                textView.setText(R.string.T_14_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 14:
                if(isStoryChangeable){
                    isStoryChangeable = false;
                    touch_judgeS = true;
                    textView2.setText(R.string.day_9);
                    textView2.setVisibility(VISIBLE);
                    fadeIn();
                    isViewAnimation = true;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setSub1CharacterIsShow(false);
                            textView.setVisibility(INVISIBLE);
                            //date configure
                            TopBar.setDateText(R.string.day_9);
                            fadeOut();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    isViewAnimation =false;
                                }
                            },1000);
                        }
                    },1000);
                }else{
                    textView.setVisibility(INVISIBLE);
                    counter = 13;
                    touch_judgeS = false;
                }
                break;
            case 15:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    //13は飛ばす
    void scene14(){
        switch (counter){
            case 0:
                setMainCharacterDirectionByCharacter(2f,4f);
                textView.setText(R.string.MC_14_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.MC_14_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene15(){
        switch (counter){
            case 0:
                textView2.setText(R.string.day_10);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setMainCharacterPrePoint(new Point(6,2, FRONT));
                        //date configure
                        TopBar.setDateText(R.string.day_10);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation =false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.MC_15_1);
                textView.setVisibility(VISIBLE);
                setMainCharacterDirection(FRONT);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_15_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_15_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene16(){
        switch (counter){
            case 0:
                postDelayed(10);
                textView.setText(R.string.MC_16_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                setSub21CharacterBgNum(3,6f,3f);
                setSub21CharacterIsShow(true);
                sub21PointList.clear();
                sub21PointList.add(new Point(6f,3f,Character.LEFT));
                sub21PointList.add(new Point(6f,3f,Character.LEFT));
                sub21PointList.add(new Point(6f,3f,Character.LEFT));
                sub21PointList.add(new Point(6f,3f,Character.LEFT));
                sub21PointList.add(new Point(5f,3f,Character.LEFT));
                sub21PointList.add(new Point(4f,3f,Character.LEFT));
                sub21PointList.add(new Point(3f,3f, FRONT));
                sub21PointList.add(new Point(3f,4f, FRONT));
                sub21PointList.add(new Point(3f,5f, FRONT));
                sub21PointList.add(new Point(3f,6f, FRONT));
                setSub21CharacterPointList(sub21PointList);
                break;
            case 1:
                if(isStoryChangeable){
                    touch_judgeS = true;
                    isStoryChangeable = false;
                }else{
                    touch_judgeS = false;
                    counter = 0;
                    textView.setVisibility(INVISIBLE);
                }
                break;
            case 2:
                setMainCharacterDirectionByCharacter(3f,6f);
                textView.setText(R.string.MC_16_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_16_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                setSub20CharacterBgNum(3,6f,3f);
                setSub20CharacterIsShow(true);
                break;
            case 4:
                postDelayed(5);
                textView.setText(R.string.MC_16_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = false;
                sub20PointList.clear();
                sub20PointList.add(new Point(6f,3f,Character.LEFT));
                sub20PointList.add(new Point(5f,3f,Character.LEFT));
                sub20PointList.add(new Point(4f,3f,Character.LEFT));
                sub20PointList.add(new Point(4f,4f, FRONT));
                sub20PointList.add(new Point(4f,5f, FRONT));
                setSub20CharacterPointList(sub20PointList);
                break;
            case 5:
                if(isStoryChangeable){
                    isStoryChangeable = false;
                    touch_judgeS = true;
                }else{
                    counter = 4;
                    touch_judgeS = false;
                    textView.setVisibility(INVISIBLE);
                }
                break;
            case 6:
                textView.setText(R.string.MC_16_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene17(){
        switch (counter){
            case 0:
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                textView.setText(R.string.MC_17_1);
                textView.setVisibility(VISIBLE);
                isStoryChangeable = false;
                touch_judgeS = true;
                break;
            case 1:
                if(isStoryChangeable){
                    isStoryChangeable = false;
                    touch_judgeS = true;
                }else{
                    touch_judgeS = false;
                    textView.setVisibility(INVISIBLE);
                    counter = 0;
                }
                break;
            case 2:
                setMainCharacterDirectionByCharacter(1f,3f);
                textView.setText(R.string.MC_17_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.T_17_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_17_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.T_17_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.MC_17_4);
                textView.setVisibility(VISIBLE);
                LightEffect.setLightable(true);
                LightEffect.setLightX(getW_unit()*1);
                LightEffect.setLightY(getH_unit()*3);
                LightEffect.setLightBGNum(getChangeBG());
                touch_judgeS = true;
                break;
            case 7:
                if(isStoryChangeable){
                    isStoryChangeable = false;
                    touch_judgeS = true;
                }else{
                    textView.setVisibility(INVISIBLE);
                    counter = 6;
                    touch_judgeS = false;
                }
                break;
            case 8:
                textView2.setText(R.string.day_7_before);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setChangeBG(16);
                        setMainCharacterIsHide(true);
                        setSub22CharacterIsShow(true);
                        setSub23CharacterIsShow(true);
                        textView.setVisibility(INVISIBLE);
                        //date configure
                        TopBar.setDateText(R.string.day_7_before);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation = false;
                                isChapterPartFinish = true;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene18(){
        switch (counter){
            case 0:
                textView.setText(R.string.SC_18_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.MC_18_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.SC_18_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_18_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.SC_18_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.MC_18_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.SC_18_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.MC_18_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.SC_18_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                textView.setText(R.string.MC_18_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                textView.setText(R.string.SC_18_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 11:
                textView.setText(R.string.MC_18_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 12:
                textView.setText(R.string.SC_18_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 13:
                textView.setText(R.string.MC_18_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 14:
                textView.setText(R.string.MC_18_8);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 15:
                textView.setText(R.string.SC_18_8);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 16:
                textView.setText(R.string.MC_18_9);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 17:
                textView.setText(R.string.SC_18_9);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 18:
                textView.setText(R.string.MC_18_10);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 19:
                textView.setText(R.string.MC_18_11);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 20:
                textView.setText(R.string.SC_18_10);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 21:
                textView.setText(R.string.MC_18_12);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 22:
                textView2.setText(R.string.day_10);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setChangeBG(15);
                        setMainCharacterIsHide(false);
                        setSub22CharacterIsShow(false);
                        setSub23CharacterIsShow(false);
                        textView.setVisibility(INVISIBLE);
                        //date configure
                        TopBar.setDateText(R.string.day_10);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation = false;
                                isChapterPartFinish = true;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene19(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_19_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.MC_19_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_19_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_19_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_19_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.MC_19_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.MC_19_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7://公民館に行く
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene20(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_20_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                LightEffect.setLightable(true);
                LightEffect.setLightX(getW_unit()*5);
                LightEffect.setLightY(getH_unit()*8);
                LightEffect.setLightBGNum(getChangeBG());
                break;
            case 1:
                if(isStoryChangeable){
                    isStoryChangeable = false;
                    LightEffect.setLightable(false);
                    LightEffect.turnOffTheLight();
                    pointList.clear();
                    setMainCharacterBgNum(7,5f,8f);
                    setMainCharacterIsPassive(true);
                    setMainCharacterIsShow(true);
                    setMainCharacterIsHide(true);
                    isViewAnimation = true;
                    touch_judgeS = true;
                    textView2.setText(R.string.day_7);
                    textView2.setVisibility(VISIBLE);
                    fadeIn();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setSub1CharacterIsShow(false);
                            textView.setVisibility(INVISIBLE);
                            setSub3CharacterIsShow(true);
                            setSub3CharacterBgNum(7,4f,8f);
                            //date configure
                            TopBar.setDateText(R.string.day_7);
                            fadeOut();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    isViewAnimation =false;
                                }
                            },1000);
                        }
                    },1000);
                }else{
                    counter = 0;
                    touch_judgeS = false;
                    textView.setVisibility(INVISIBLE);
                }
                break;
            case 2:
                textView.setText(R.string.SC_20_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                setMainCharacterDirectionByCharacter(4f,8f);
                setMainCharacterDirection(LEFT);
                textView.setText(R.string.MC_20_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_20_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                postDelayed(16);
                pointList.add(new Point(5f,7.5f,Character.BACK));
                pointList.add(new Point(5f,7f,Character.BACK));
                pointList.add(new Point(5f,6.5f,Character.BACK));
                pointList.add(new Point(5f,6f,Character.BACK));
                pointList.add(new Point(5f,5.5f,Character.BACK));
                pointList.add(new Point(5f,5f,Character.BACK));
                pointList.add(new Point(5f,4.5f,Character.BACK));
                pointList.add(new Point(5f,4f,Character.BACK));
                pointList.add(new Point(5f,3.5f,Character.BACK));
                pointList.add(new Point(5f,3f,Character.BACK));
                pointList.add(new Point(5f,2.5f,Character.BACK));
                pointList.add(new Point(5f,2f,Character.BACK));
                pointList.add(new Point(5f,1.5f,Character.BACK));
                pointList.add(new Point(5f,1f,Character.BACK));
                pointList.add(new Point(5f,0.5f,Character.BACK));
                pointList.add(new Point(5f,0f,Character.BACK));
                setMainCharacterPointList(pointList);
                setSub3CharacterDirection(RIGHT);
                textView.setText(R.string.SC_20_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.SC_20_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView2.setText("");
                fadeIn();
                isViewAnimation = true;
                touch_judgeS = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    setChangeBG(6);
                    setMainCharacterIsHide(false);
                    setSub3CharacterIsShow(false);
                    setMainCharacterIsPassive(false);
                    fadeOut();
                     new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            isViewAnimation = false;
                            }
                        },1000);
                    }
                },1000);
                break;
            case 8:
                touch_judgeS = true;
                isChapterPartFinish = true;
                break;
        }
        counter++;
    }
    void scene21(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_21_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setVisibility(INVISIBLE);
                counter = 0;
                touch_judgeS = false;
                break;
            case 2:
                textView.setText(R.string.SC_21_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_21_2);
                textView.setVisibility(VISIBLE);
                setSub1CharacterBgNum(13,5,2);
                setSub1CharacterIsShow(true);
                setSub1CharacterDirection(FRONT);
                setSub20CharacterBgNum(13,5,3);
                setSub20CharacterIsShow(true);
                setSub20CharacterDirection(Character.BACK);
                touch_judgeS = true;
                break;
            case 4:
                if(isStoryChangeable){
                    touch_judgeS = true;
                    isStoryChangeable = false;
                }else{
                    counter = 3;
                    touch_judgeS = false;
                    textView.setVisibility(INVISIBLE);
                }
                break;
            case 5:
                if(Character.getNowX() > 6){
                    setMainCharacterDirection(LDB);
                }else if(Character.getNowX() < 4){
                    setMainCharacterDirection(RDB);
                }else{
                    setMainCharacterDirection(BACK);
                }
                textView.setText(R.string.SC_21_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.MC_21_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.SC_21_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                postDelayed(16);
                textView.setText(R.string.MC_21_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                sub20PointList.add(new Point(5f,2.5f));
                sub20PointList.add(new Point(5f,2f));
                setSub20CharacterPointList(sub20PointList);
                sub1PointList.add(new Point(5f,1.5f));
                sub1PointList.add(new Point(-1,-1));
                setSub1CharacterPointList(sub1PointList);
                sub20PointList.add(new Point(5f,2f));
                sub20PointList.add(new Point(5f,2.5f));
                sub20PointList.add(new Point(5f,3f));
                setSub20CharacterPointList(sub20PointList);
                sub20PointList.add(new Point(5f,3f,Character.RIGHT));
                sub20PointList.add(new Point(5f,3.5f, FRONT));
                sub20PointList.add(new Point(5f,4f, FRONT));
                sub20PointList.add(new Point(5f,4f, FRONT));
                sub20PointList.add(new Point(5f,4f,Character.RIGHT));
                sub20PointList.add(new Point(5.5f,4f,Character.RIGHT));
                sub20PointList.add(new Point(6f,4f,Character.RIGHT));
                sub20PointList.add(new Point(6.5f,4f,Character.RIGHT));
                sub20PointList.add(new Point(7f,4f,Character.RIGHT));
                sub20PointList.add(new Point(7.5f,4f,Character.RIGHT));
                sub20PointList.add(new Point(8f,4f,Character.RIGHT));
                break;
            case 9:
                isViewAnimation = true;
                touch_judgeS = true;
                textView2.setText(R.string.day_10);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setSub1CharacterIsShow(false);
                        textView.setVisibility(INVISIBLE);
                        //date configure
                        TopBar.setDateText(R.string.day_10);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation =false;
                            }
                        },1000);
                    }
                },1000);
                break;
            case 10:
                setSub20CharacterPointList(sub20PointList);
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene22(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_22_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.MC_22_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_22_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_22_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_22_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView2.setText(R.string.day_11);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pointList.add(new Point(6f,2.5f));
                        setMainCharacterPointList(pointList);
                        setMainCharacterDirection(FRONT);
                        textView.setText("");
                        textView.setVisibility(INVISIBLE);
                        //date configure
                        TopBar.setDateText(R.string.day_11);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation = false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.MC_22_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene23(){
        switch (counter){
            case 0:
                textView.setVisibility(INVISIBLE);
                setMainCharacterDirection(LEFT);
                isViewAnimation = true;
                textView2.setText("");
                fadeIn();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setText(R.string.T_23_1);
                        textView2.setVisibility(VISIBLE);
                        isViewAnimation = false;
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 1:
                textView2.setText(R.string.T_23_2);
                textView2.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                isViewAnimation = true;
                fadeOut();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setText("");
                        textView2.setVisibility(INVISIBLE);
                        isViewAnimation = false;
                        LightEffect.setLightable(true);
                        LightEffect.setLightX(getW_unit()*1);
                        LightEffect.setLightY(getH_unit()*3);
                        LightEffect.setLightBGNum(getChangeBG());
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 3:
                counter = 2;
                touch_judgeS = false;
                break;
            case 4:
                setMainCharacterDirection(LEFT);
                isViewAnimation = true;
                textView2.setText(R.string.day_7_before);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                //date configure
                TopBar.setDateText(R.string.day_7_before);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setChangeBG(16);
                        setMainCharacterIsHide(true);
                        setSub22CharacterIsShow(true);
                        setSub23CharacterIsShow(true);
                        textView.setVisibility(INVISIBLE);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation = false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.SC_23_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.MC_23_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.SC_23_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene24(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_24_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.SC_24_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_24_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.SC_24_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_24_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setVisibility(INVISIBLE);
                isViewAnimation = true;
                textView2.setText(R.string.day_11);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setChangeBG(15);
                        setMainCharacterIsHide(false);
                        setSub22CharacterIsShow(false);
                        setSub23CharacterIsShow(false);
                        pointList.add(new Point(2f,3f,LEFT));
                        setMainCharacterPointList(pointList);
                        textView.setVisibility(INVISIBLE);
                        //date configure
                        TopBar.setDateText(R.string.day_11);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation = false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.MC_24_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene25(){
        switch (counter){
            case 0:
                setSub4CharacterDirectionForMainCharacter();
                setMainCharacterDirectionByCharacter(2f,7f);
                textView.setText(R.string.SC_25_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.MC_25_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.SC_25_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_25_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_25_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.MC_25_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.SC_25_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.MC_25_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.SC_25_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                textView.setText(R.string.MC_25_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                textView.setText(R.string.SC_25_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 11:
                textView.setText(R.string.MC_25_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 12:
                setSub4CharacterDirection(FRONT);
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene26(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_26_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                LightEffect.setLightable(true);
                LightEffect.setLightX(getW_unit()*6);
                LightEffect.setLightY(getH_unit()*3);
                LightEffect.setLightBGNum(getChangeBG());
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_26_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = false;
                break;
            case 3:
                counter = 2;
                touch_judgeS = false;
                break;
            case 4:
                isViewAnimation = true;
                textView2.setText(R.string.day_7);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setMainCharacterPrePoint(new Point(6f,2.5f));
                        pointList.add(new Point(6f,2.5f));
                        setMainCharacterDirection(FRONT);
                        setMainCharacterPointList(pointList);
                        //test 12/21
                        setMainCharacterIsShow(true);
                        setMainCharacterIsHide(false);
                        setMainCharacterBgNum(8,6f,2.5f);
                        setMainCharacterDirection(FRONT);
                        setMainCharacterIsPassive(true);
                        //test
                        //date configure
                        TopBar.setDateText(R.string.day_7);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation = false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 5:
                isChapterFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene27(){
        switch (counter){
            case 0:
//                setMainCharacterBgNum(8,6f,2.5f);
//                setMainCharacterDirection(FRONT);
//                setMainCharacterIsShow(true);
//                setMainCharacterIsHide(false);
//                setMainCharacterIsPassive(true);
                textView.setText(R.string.ring_27_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.SC_27_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_27_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;

                setSub13CharacterBgNum(2,3f,1f);
                setSub14CharacterBgNum(2,4f,1f);
                setSub13CharacterDirection(Character.RIGHT);
                setSub14CharacterDirection(Character.BACK);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                break;
            case 3:
                textView.setText(R.string.SC_27_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_27_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                isChapterPartFinish = true;
                setSub1CharacterBgNum(3,5f,7f);
                setSub1CharacterDirection(Character.RIGHT);
                setSub1CharacterIsShow(true);
                touch_judgeS = true;
                break;
        }

        counter++;
    }
    void scene28(){
        switch (counter){
            case 0:
                setMainCharacterBgNum(3);
                setMainCharacterDirectionByCharacter(5f,7f);
                textView.setText(R.string.MC_28_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                setSub1CharacterDirectionForMainCharacter();
                setMainCharacterDirectionByCharacter(5f,7f);
                setSub21CharacterIsShow(true);
                setSub21CharacterBgNum(3,4.5f,7.25f);
                textView.setText(R.string.SC_28_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                setMainCharacterDirectionByCharacter(5f,7f);
                textView.setText(R.string.SC_28_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                setMainCharacterDirectionByCharacter(5f,7f);
                textView.setText(R.string.MC_28_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                postDelayed(7);
                setSub20CharacterIsShow(true);
                setSub20CharacterBgNum(3,0f,10f);
                sub20PointList.clear();
                sub20PointList.add(new Point(0.5f,10f, RIGHT));
                sub20PointList.add(new Point(1f,10f, RIGHT));
                sub20PointList.add(new Point(1.5f,10f, RIGHT));
                sub20PointList.add(new Point(2f,9.5f, BACK));
                sub20PointList.add(new Point(2f,9f, BACK));
                sub20PointList.add(new Point(2f,8.5f, BACK));
                sub20PointList.add(new Point(2f,8f, BACK));
                setSub20CharacterPointList(sub20PointList);
                setMainCharacterIsPassive(true);
                setMainCharacterIsHide(true);
                pointList.clear();
                pointList.add(new Point(4f,7f));
                setMainCharacterPointList(pointList);
                textView.setText(R.string.SC_28_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                postDelayed(15);
                textView.setText(R.string.MC_28_3);
                textView.setVisibility(VISIBLE);
                sub1PointList.clear();
                sub1PointList.add(new Point(5f,6f,Character.BACK));
                sub1PointList.add(new Point(5f,5.5f,Character.BACK));
                sub1PointList.add(new Point(5f,5f,Character.BACK));
                sub1PointList.add(new Point(5f,4.5f,Character.BACK));
                sub1PointList.add(new Point(5f,4f,Character.BACK));
                sub1PointList.add(new Point(5f,3.5f,Character.BACK));
                sub1PointList.add(new Point(5f,3f,Character.BACK));
                sub1PointList.add(new Point(5.5f,2.5f,Character.BACK));
                sub1PointList.add(new Point(6f,2f,Character.BACK));
                sub1PointList.add(new Point(6f,1.5f,Character.BACK));
                sub1PointList.add(new Point(6f,1f,Character.BACK));
                sub1PointList.add(new Point(6f,0.5f,Character.BACK));
                sub1PointList.add(new Point(6f,0f,Character.BACK));
                sub1PointList.add(new Point(6f,-1f,Character.BACK));
                pointList.clear();
                pointList.add(new Point(4f,6f,Character.BACK));
                pointList.add(new Point(4f,5.5f,Character.BACK));
                pointList.add(new Point(4f,5f,Character.BACK));
                pointList.add(new Point(4f,4.5f,Character.BACK));
                pointList.add(new Point(4f,4f,Character.BACK));
                pointList.add(new Point(4f,3.5f,Character.BACK));
                pointList.add(new Point(4f,3f,Character.BACK));
                pointList.add(new Point(4.5f,2.5f,Character.BACK));
                pointList.add(new Point(5f,2f,Character.BACK));
                pointList.add(new Point(5f,1.5f,Character.BACK));
                pointList.add(new Point(5f,1f,Character.BACK));
                pointList.add(new Point(5f,0.5f,Character.BACK));
                pointList.add(new Point(5f,-1f,Character.BACK));
                setSub1CharacterPointList(sub1PointList);
                setMainCharacterPointList(pointList);
//                setMainCharacterIsHide(true);
                touch_judgeS = true;
                break;
            case 6:
                textView2.setText("");
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setVisibility(INVISIBLE);
                        setChangeBG(13);
                        setMainCharacterBgNum(13,3,11.5f);
                        setSub1CharacterBgNum(13,4,11.5f);
                        setMainCharacterIsShow(false);
                        setSub1CharacterIsShow(false);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation =false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                isChapterFinish = true;
                break;
        }
        counter++;
    }
    void scene29(){
        switch (counter){
            case 0:
                postDelayed(20);
                pointList.clear();
                pointList.add(new Point(3f,11.5f,Character.BACK));
                pointList.add(new Point(3f,11f,Character.BACK));
                pointList.add(new Point(3f,10.5f,Character.BACK));
                pointList.add(new Point(3f,10f,Character.BACK));
                pointList.add(new Point(3f,9.5f,Character.BACK));
                pointList.add(new Point(3f,9f,Character.BACK));
                pointList.add(new Point(3f,8.5f,Character.BACK));
                pointList.add(new Point(3f,8f,Character.BACK));
                pointList.add(new Point(3f,7.5f,Character.BACK));
                pointList.add(new Point(3f,7f,Character.BACK));
                pointList.add(new Point(3f,6.5f,Character.BACK));
                pointList.add(new Point(3f,6f,Character.BACK));
                pointList.add(new Point(3.5f,5.5f,Character.BACK));
                pointList.add(new Point(4f,5f,Character.BACK));
                pointList.add(new Point(4f,4.5f,Character.BACK));
                pointList.add(new Point(4f,4f,Character.BACK));
                pointList.add(new Point(4f,3.5f,Character.BACK));
                pointList.add(new Point(4f,3f,Character.BACK));
                pointList.add(new Point(4f,2.5f,Character.BACK));
                pointList.add(new Point(4f,2f,Character.BACK));
//                pointList.add(new Point(4f,1.5f,Character.BACK));
//                pointList.add(new Point(4f,1f,Character.BACK));
//                pointList.add(new Point(4f,0.5f,Character.BACK));
//                pointList.add(new Point(4f,0f,Character.BACK));
                sub1PointList.clear();
                sub1PointList.add(new Point(4f,11.5f,Character.BACK));
                sub1PointList.add(new Point(4f,11f,Character.BACK));
                sub1PointList.add(new Point(4f,10.5f,Character.BACK));
                sub1PointList.add(new Point(4f,10f,Character.BACK));
                sub1PointList.add(new Point(4f,9.5f,Character.BACK));
                sub1PointList.add(new Point(4f,9f,Character.BACK));
                sub1PointList.add(new Point(4f,8.5f,Character.BACK));
                sub1PointList.add(new Point(4f,8f,Character.BACK));
                sub1PointList.add(new Point(4f,7.5f,Character.BACK));
                sub1PointList.add(new Point(4f,7f,Character.BACK));
                sub1PointList.add(new Point(4f,6.5f,Character.BACK));
                sub1PointList.add(new Point(4f,6f,Character.BACK));
                sub1PointList.add(new Point(4.5f,5.5f,Character.BACK));
                sub1PointList.add(new Point(5f,5f,Character.BACK));
                sub1PointList.add(new Point(5f,4.5f,Character.BACK));
                sub1PointList.add(new Point(5f,4f,Character.BACK));
                sub1PointList.add(new Point(5f,3.5f,Character.BACK));
                sub1PointList.add(new Point(5f,3f,Character.BACK));
                sub1PointList.add(new Point(5f,2.5f,Character.BACK));
                sub1PointList.add(new Point(5f,2f,Character.BACK));
                setMainCharacterIsPassive(true);
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(true);
                setMainCharacterPointList(pointList);
                setSub1CharacterPointList(sub1PointList);
                break;
            case 1:
                postDelayed(16);
                textView.setText(R.string.SC_29_1);
                textView.setVisibility(VISIBLE);
                setSub20CharacterBgNum(13,3f,11);
                sub20PointList.clear();
                sub20PointList.add(new Point(4f,10.5f, BACK));
                sub20PointList.add(new Point(4f,10f, BACK));
                sub20PointList.add(new Point(4f,9.5f, BACK));
                sub20PointList.add(new Point(4f,9f, BACK));
                sub20PointList.add(new Point(4f,8.5f, BACK));
                sub20PointList.add(new Point(4f,8f, BACK));
                sub20PointList.add(new Point(4f,7.5f, BACK));
                sub20PointList.add(new Point(4f,7f, BACK));
                sub20PointList.add(new Point(4f,6.5f, BACK));
                sub20PointList.add(new Point(4f,6f, BACK));
                sub20PointList.add(new Point(4f,5.5f, BACK));
                sub20PointList.add(new Point(4f,5f, BACK));
                sub20PointList.add(new Point(4f,4.5f, BACK));
                sub20PointList.add(new Point(4f,4f, BACK));
                sub20PointList.add(new Point(4f,3.5f, BACK));
                sub20PointList.add(new Point(4f,3f, BACK));
                setSub20CharacterPointList(sub20PointList);
                touch_judgeS = true;
                break;
            case 2:
                postDelayed(5);
                textView.setText(R.string.MC_29_1);
                textView.setVisibility(VISIBLE);
                sub20PointList.clear();
                sub20PointList.add(new Point(4f,2.5f));
                sub20PointList.add(new Point(4f,2f));
                setSub20CharacterPointList(sub20PointList);
                pointList.clear();
                pointList.add(new Point(4f,1.5f));
                pointList.add(new Point(-1,-1));
                setMainCharacterPointList(pointList);
                sub20PointList.clear();
                sub20PointList.add(new Point(4f,2f));
                sub20PointList.add(new Point(4f,2.5f));
                sub20PointList.add(new Point(4f,3f));
                setSub20CharacterPointList(sub20PointList);
                touch_judgeS = true;
                break;
            case 3:
                textView2.setText(R.string.day_10);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setChangeBG(8);
                        pointList.add(new Point(6,2));
                        setMainCharacterPointList(pointList);
                        setMainCharacterPrePoint(new Point(6,2));
                        setMainCharacterIsPassive(false);
                        setMainCharacterIsHide(false);
                        textView.setVisibility(INVISIBLE);
                        //date configure
                        TopBar.setDateText(R.string.day_10);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation =false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 4:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene30(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_30_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                counter = 0;
                touch_judgeS = false;
                break;
            case 2:
                setSub1CharacterIsShow(false);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                textView.setText(R.string.SC_30_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_30_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.SC_30_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.MC_30_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.SC_30_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.MC_30_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.MC_30_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                textView.setText(R.string.MC_30_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                textView.setText(R.string.SC_30_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 11:
                textView.setText(R.string.MC_30_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 12:
                textView.setText(R.string.MC_30_8);
                textView.setVisibility(VISIBLE);
                setSub12CharacterBgNum(14,6f,2f);
                setSub12CharacterDirection(Character.FRONT);
                setSub13CharacterBgNum(14,2f,10f);
                setSub14CharacterBgNum(14,3f,10f);
                setSub13CharacterDirection(Character.BACK);
                setSub14CharacterDirection(Character.BACK);
                touch_judgeS = true;
                break;
            case 13:
                setSub15CharacterBgNum(7,5f,8f);
                setSub15CharacterIsShow(true);
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene31(){
        switch (counter){
            case 0:
                setSub15CharacterDirectionForMainCharacter();
                setMainCharacterDirectionByCharacter(5f,8f);
                textView.setText(R.string.SC_31_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                setMainCharacterDirection(LEFT);
                textView.setText(R.string.MC_31_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                postDelayed(15);
                setMainCharacterDirectionByCharacter(5f,8f);
                textView.setText(R.string.SC_31_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                if(getSub15CharacterDirectionNum() == Character.LEFT){
                    sub15PointList.clear();
                    sub15PointList.add(new Point(6f,8.5f, FRONT));
                    sub15PointList.add(new Point(6f,9f, FRONT));
                    sub15PointList.add(new Point(5.5f,9f,Character.LEFT));
                    sub15PointList.add(new Point(5f,9f));
                    sub15PointList.add(new Point(4.5f,9f));
                    sub15PointList.add(new Point(4f,9f));
                    sub15PointList.add(new Point(3.5f,9f));
                    sub15PointList.add(new Point(3f,9f));
                    sub15PointList.add(new Point(2.5f,9f));
                    sub15PointList.add(new Point(2f,9f));
                    sub15PointList.add(new Point(1.5f,9f));
                    sub15PointList.add(new Point(1f,9f));
                    sub15PointList.add(new Point(0.5f,9f));
                    sub15PointList.add(new Point(0f,9f));
                    sub15PointList.add(new Point(-1f,9f));
                }else{
                    sub15PointList.clear();
                    sub15PointList.add(new Point(5.5f,8f,Character.LEFT));
                    sub15PointList.add(new Point(5f,8f));
                    sub15PointList.add(new Point(4.5f,8f));
                    sub15PointList.add(new Point(4f,8f));
                    sub15PointList.add(new Point(3.5f,8f));
                    sub15PointList.add(new Point(3f,8f));
                    sub15PointList.add(new Point(2.5f,8f));
                    sub15PointList.add(new Point(2f,8f));
                    sub15PointList.add(new Point(1.5f,8f));
                    sub15PointList.add(new Point(1f,8f));
                    sub15PointList.add(new Point(0.5f,8f));
                    sub15PointList.add(new Point(0f,8f));
                    sub15PointList.add(new Point(-1f,8f));
                }
//                setSub15CharacterPointList(sub15PointList);
                break;
            case 3:
                isViewAnimation = true;
                setSub15CharacterPointList(sub15PointList);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setSub15CharacterBgNum(4,2f,5f);
                        setSub15CharacterDirection(Character.BACK);
                        isViewAnimation = false;
                    }
                },1200);
                touch_judgeS = true;
                break;
            case 4:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene32(){
        switch (counter){
            case 0:
                setSub15CharacterDirectionForMainCharacter();
                textView.setText(R.string.SC_32_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.MC_32_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                setSub15CharacterDirection(Character.BACK);
                textView.setText(R.string.SC_32_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                setSub15CharacterDirectionForMainCharacter();
                textView.setText(R.string.SC_32_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                setSub15CharacterDirection(Character.BACK);
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene33(){
        switch (counter){
            case 0:
                setMainCharacterDirectionByCharacter(1f,6f);
                textView.setText(R.string.MC_33_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.SC_33_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                isChapterPartFinish = true;
                setSub15CharacterIsShow(false);
                setSub19CharacterBgNum(4,2f,2f);
                setSub19CharacterDirection(FRONT);
                setSub19CharacterIsShow(true);
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene34(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_34_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.SC_34_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_34_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.SC_34_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_34_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.SC_34_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.MC_34_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.SC_34_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.MC_34_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                textView.setText(R.string.SC_34_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                textView.setText(R.string.MC_34_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 11:
                textView.setText(R.string.SC_34_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 12:
                textView.setText(R.string.MC_34_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 13:
                textView.setText(R.string.SC_34_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 14:
                textView.setText(R.string.MC_34_8);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 15:
                textView.setText(R.string.SC_34_8);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 16:
                textView.setText(R.string.MC_34_9);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 17:
                textView.setText(R.string.SC_34_9);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 18:
                textView.setText(R.string.MC_34_10);
                textView.setVisibility(VISIBLE);
                foodEnable = true;//木の実を入手できるようにする。
                touch_judgeS = true;
                break;
            case 19:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene35(){
        switch (counter){
            case 0:
                setMainCharacterDirection(Character.BACK);
                textView.setText(R.string.MC_35_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.SC_35_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_35_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.SC_35_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_35_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.SC_35_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.MC_35_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.SC_35_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.MC_35_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                textView.setText(R.string.MC_35_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                textView.setText(R.string.SC_35_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 11:
                textView.setText(R.string.MC_35_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 12:
                textView.setText(R.string.SC_35_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 13:
                isChapterPartFinish = true;
                LightEffect.setLightable(true);
                LightEffect.setLightX(getW_unit()*3);
                LightEffect.setLightY(getH_unit()*6);
                LightEffect.setLightBGNum(getChangeBG());
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene36(){
        switch (counter){
            case 0:
                textView2.setText(R.string.day_m_);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setSub5CharacterIsShow(false);
                        setSub6CharacterIsShow(false);
                        setSub10CharacterIsShow(false);
                        setSub19CharacterIsShow(false);
                        setSub12CharacterIsShow(false);
                        setSub13CharacterIsShow(true);
                        setSub14CharacterIsShow(true);
                        setSub7CharacterBgNum(11,6f,7f);
                        setSub7CharacterDirection(LEFT);
                        setSub13CharacterBgNum(5,7f,8f);
                        setSub14CharacterBgNum(5,7f,9f);
                        setSub13CharacterDirection(Character.LEFT);
                        setSub14CharacterDirection(Character.LEFT);
                        setSub22CharacterBgNum(7,1f,6f);
                        setSub22CharacterDirection(Character.RIGHT);
                        setSub23CharacterBgNum(7,1f,5f);
                        setSub23CharacterDirection(FRONT);
                        setSub24CharacterBgNum(7,2f,6f);
                        setSub24CharacterDirection(Character.LEFT);
                        setSub22CharacterIsShow(true);
                        setSub23CharacterIsShow(true);
                        setSub24CharacterIsShow(true);
                        LightEffect.setLightable(false);
                        LightEffect.turnOffTheLight();
                        textView.setVisibility(INVISIBLE);
                        //date configure
                        TopBar.setDateText(R.string.day_m_);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation =false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.MC_36_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_36_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene37(){
        switch (counter){
            case 0:
                setMainCharacterDirection(Character.RIGHT);
                textView.setText(R.string.MC_37_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                setSub24CharacterDirection(Character.LEFT);
                setSub23CharacterDirection(Character.LEFT);
                setSub22CharacterDirection(Character.LEFT);
                textView.setText(R.string.SC_37_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                setMainCharacterDirection(Character.RIGHT);
                textView.setText(R.string.SC_37_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_37_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.SC_37_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.SC_37_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.SC_37_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.SC_37_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.MC_37_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                textView.setText(R.string.MC_37_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                textView.setText(R.string.SC_37_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 11:
                textView.setText(R.string.SC_37_8);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 12:
                textView.setText(R.string.SC_37_9);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 13:
                textView.setText(R.string.SC_37_10);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 14:
                textView.setText(R.string.SC_37_11);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 15:
                textView.setText(R.string.SC_37_12);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 16:
                postDelayed(14);
                textView.setText(R.string.SC_37_13);
                textView.setVisibility(VISIBLE);
                isViewAnimation = true;
                sub22PointList.clear();
                sub22PointList.add(new Point(1f,6f, FRONT));
                sub22PointList.add(new Point(1f,6.5f, FRONT));
                sub22PointList.add(new Point(1f,7f, FRONT));
                sub22PointList.add(new Point(1f,7.5f, FRONT));
                sub22PointList.add(new Point(1f,8f, FRONT));
                sub22PointList.add(new Point(1f,8.5f, FRONT));
                sub22PointList.add(new Point(1f,9f, FRONT));
                sub22PointList.add(new Point(1f,9f,Character.LEFT));
                sub22PointList.add(new Point(0.5f,9f,Character.LEFT));
                sub22PointList.add(new Point(0f,9f,Character.LEFT));
                sub22PointList.add(new Point(-1f,-1f, FRONT));

                sub24PointList.clear();
                sub24PointList.add(new Point(2f,6f, FRONT));
                sub24PointList.add(new Point(2f,6.5f, FRONT));
                sub24PointList.add(new Point(2f,7f, FRONT));
                sub24PointList.add(new Point(2f,7.5f, FRONT));
                sub24PointList.add(new Point(2f,8f, FRONT));
                sub24PointList.add(new Point(2f,8f,Character.LEFT));
                sub24PointList.add(new Point(1.5f,8f,Character.LEFT));
                sub24PointList.add(new Point(1f,8f,Character.LEFT));
                sub24PointList.add(new Point(0.5f,8f,Character.LEFT));
                sub24PointList.add(new Point(0f,8f,Character.LEFT));
                sub24PointList.add(new Point(-1f,-1f, FRONT));

                sub23PointList.clear();
                sub23PointList.add(new Point(1f,5f, FRONT));
                sub23PointList.add(new Point(1f,5f, FRONT));
                sub23PointList.add(new Point(1f,5f, FRONT));
                sub23PointList.add(new Point(1f,5.5f, FRONT));
                sub23PointList.add(new Point(1f,6f, FRONT));
                sub23PointList.add(new Point(1f,6.5f, FRONT));
                sub23PointList.add(new Point(1f,7f, FRONT));
                sub23PointList.add(new Point(1f,7.5f, FRONT));
                sub23PointList.add(new Point(1f,8f, FRONT));
                sub23PointList.add(new Point(1f,8f,Character.LEFT));
                sub23PointList.add(new Point(0.5f,8f,Character.LEFT));
                sub23PointList.add(new Point(0f,8f,Character.LEFT));
                sub23PointList.add(new Point(-1f,-1f, FRONT));

                setSub22CharacterPointList(sub22PointList);
                setSub24CharacterPointList(sub24PointList);
                setSub23CharacterPointList(sub23PointList);
                touch_judgeS = true;
                break;
            case 17:
                setSub22CharacterBgNum(4,4f,5f);
                setSub22CharacterDirection(FRONT);
                setSub23CharacterBgNum(4,6f,2f);
                setSub23CharacterDirection(Character.BACK);
                setSub24CharacterBgNum(4,1f,6f);
                setSub24CharacterDirection(Character.LEFT);
                break;
            case 18://小屋に入るまで
                counter = 17;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
            case 19:
                setSub22CharacterDirectionForMainCharacter();
                textView.setText(R.string.SC_37_14);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 20:
                textView.setText(R.string.SC_37_15);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 21:
                if(isStoryChangeable){
                    touch_judgeS = true;
                    isStoryChangeable = false;
                }else{
                    counter = 20;
                    textView.setVisibility(INVISIBLE);
                    touch_judgeS = false;
                }
                break;
            case 22:
                setSub22CharacterDirectionForMainCharacter();
                textView.setText(R.string.SC_37_16);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 23:
                setSub23CharacterDirectionForMainCharacter();
                setMainCharacterDirectionByCharacter(4f,5f);
                textView.setText(R.string.SC_37_17);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 24:
                postDelayed(12);
                textView.setText(R.string.SC_37_18);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                if(getSub22CharacterDirectionNum() == Character.LEFT){
                    sub24PointList.clear();
                    sub24PointList.add(new Point(1f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(1.5f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(2f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(2.5f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(3f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(3.5f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(4f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(4f,6f,Character.BACK));
                    sub24PointList.add(new Point(4f,6f,Character.BACK));
                    sub24PointList.add(new Point(4f,5.5f,Character.BACK));
                    sub24PointList.add(new Point(4f,5f,Character.BACK));
                    sub24PointList.add(new Point(4f,5f,Character.LEFT));
                    sub22PointList.clear();
                    sub22PointList.add(new Point(4f,5f,Character.LEFT));
                    sub22PointList.add(new Point(4f,5f,Character.LEFT));
                    sub22PointList.add(new Point(4f,5f,Character.LEFT));
                    sub22PointList.add(new Point(4f,5f,Character.LEFT));
                    sub22PointList.add(new Point(4f,5f,Character.LEFT));
                    sub22PointList.add(new Point(4f,5f,Character.LEFT));
                    sub22PointList.add(new Point(4f,5f,Character.LEFT));
                    sub22PointList.add(new Point(4f,5f,Character.BACK));
                    sub22PointList.add(new Point(4f,5f,Character.BACK));
                    sub22PointList.add(new Point(4f,4.5f,Character.BACK));
                    sub22PointList.add(new Point(4f,4f,Character.BACK));
                    sub22PointList.add(new Point(4f,4f,Character.LEFT));

                }else if(getSub22CharacterDirectionNum() == Character.BACK){
                    sub24PointList.clear();
                    sub24PointList.add(new Point(1f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(1.5f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(2f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(2.5f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(3f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(3f,6f,Character.BACK));
                    sub24PointList.add(new Point(3f,5.5f,Character.BACK));
                    sub24PointList.add(new Point(3f,5f,Character.BACK));
                }else if(getSub22CharacterDirectionNum() == Character.RIGHT){
                    sub24PointList.clear();
                    sub24PointList.add(new Point(1f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(1.5f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(2f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(2.5f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(3f,6f,Character.RIGHT));
                    sub24PointList.add(new Point(3f,6f,Character.BACK));
                    sub24PointList.add(new Point(3f,5.5f,Character.BACK));
                    sub24PointList.add(new Point(3f,5f,Character.BACK));
                    sub24PointList.add(new Point(3f,4.5f,Character.BACK));
                    sub24PointList.add(new Point(3f,4f,Character.BACK));
                    sub24PointList.add(new Point(3f,4f,Character.RIGHT));
                    sub24PointList.add(new Point(3.5f,4f,Character.RIGHT));
                    sub24PointList.add(new Point(4f,4f,Character.RIGHT));
                }else if(getSub22CharacterDirectionNum() == FRONT){
                    sub24PointList.clear();
                    sub24PointList.add(new Point(1f,6f,Character.BACK));
                    sub24PointList.add(new Point(1f,5.5f,Character.BACK));
                    sub24PointList.add(new Point(1f,5f,Character.BACK));
                    sub24PointList.add(new Point(1f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(1.5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(2f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(2.5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(3f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(3f,5f, FRONT));
                }
                setSub22CharacterPointList(sub22PointList);
                setSub24CharacterPointList(sub24PointList);
                break;
            case 25:
                textView.setText(R.string.MC_37_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 26:
                textView.setText(R.string.SC_37_19);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 27:
                textView.setText(R.string.SC_37_20);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 28:
                textView.setText(R.string.SC_37_21);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 29:
                postDelayed(12);
                textView.setVisibility(INVISIBLE);
                if(getSub22CharacterDirectionNum() == Character.LEFT){
                    sub24PointList.clear();
                    sub24PointList.add(new Point(4f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(4.5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(5.5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(6f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(6.5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(7f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(7.5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(8f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(8.5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(-1f,-1f, FRONT));
                    sub22PointList.clear();
                    sub22PointList.add(new Point(4f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(4.5f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(5f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(5.5f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(6f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(6.5f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(7f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(7.5f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(8f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(8.5f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(-1f,-1f, FRONT));
                }else if(getSub22CharacterDirectionNum() == Character.BACK || getSub22CharacterDirectionNum() == FRONT){
                    sub24PointList.clear();
                    sub24PointList.add(new Point(3f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(3.5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(4f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(4.5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(5.5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(6f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(6.5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(7f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(7.5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(8f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(8.5f,5f,Character.RIGHT));
                    sub24PointList.add(new Point(-1f,-1f, FRONT));
                    sub22PointList.clear();
                    sub22PointList.add(new Point(4f,5f,Character.RIGHT));
                    sub22PointList.add(new Point(4.5f,5f,Character.RIGHT));
                    sub22PointList.add(new Point(5f,5f,Character.RIGHT));
                    sub22PointList.add(new Point(5.5f,5f,Character.RIGHT));
                    sub22PointList.add(new Point(6f,5f,Character.RIGHT));
                    sub22PointList.add(new Point(6.5f,5f,Character.RIGHT));
                    sub22PointList.add(new Point(7f,5f,Character.RIGHT));
                    sub22PointList.add(new Point(7.5f,5f,Character.RIGHT));
                    sub22PointList.add(new Point(8f,5f,Character.RIGHT));
                    sub22PointList.add(new Point(8.5f,5f,Character.RIGHT));
                    sub22PointList.add(new Point(-1f,-1f, FRONT));
                }else if(getSub22CharacterDirectionNum() == Character.RIGHT){
                    sub24PointList.clear();
                    sub24PointList.add(new Point(4f,4f,Character.RIGHT));
                    sub24PointList.add(new Point(4.5f,4f,Character.RIGHT));
                    sub24PointList.add(new Point(5f,4f,Character.RIGHT));
                    sub24PointList.add(new Point(5.5f,4f,Character.RIGHT));
                    sub24PointList.add(new Point(6f,4f,Character.RIGHT));
                    sub24PointList.add(new Point(6.5f,4f,Character.RIGHT));
                    sub24PointList.add(new Point(7f,4f,Character.RIGHT));
                    sub24PointList.add(new Point(7.5f,4f,Character.RIGHT));
                    sub24PointList.add(new Point(8f,4f,Character.RIGHT));
                    sub24PointList.add(new Point(8.5f,4f,Character.RIGHT));
                    sub24PointList.add(new Point(-1f,-1f, FRONT));
                    sub22PointList.clear();
                    sub22PointList.add(new Point(4f,5f,Character.BACK));
                    sub22PointList.add(new Point(4f,4.5f,Character.BACK));
                    sub22PointList.add(new Point(4f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(4.5f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(5f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(5.5f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(6f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(6.5f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(7f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(7.5f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(8f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(8.5f,4f,Character.RIGHT));
                    sub22PointList.add(new Point(-1f,-1f, FRONT));
                }
                setSub22CharacterPointList(sub22PointList);
                setSub24CharacterPointList(sub24PointList);
                break;
            case 30:
                textView.setText(R.string.MC_37_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 31:
                setMainCharacterIsShow(true);//省略できるものは省略して
                setSub1CharacterIsShow(false);
                setSub2CharacterIsShow(false);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(false);
                setSub5CharacterIsShow(false);
                setSub6CharacterIsShow(false);
                setSub7CharacterIsShow(false);
                setSub8CharacterIsShow(false);
                setSub9CharacterIsShow(false);
                setSub10CharacterIsShow(false);
                setSub11CharacterIsShow(false);
                setSub12CharacterIsShow(false);
                setSub13CharacterIsShow(false);
                setSub14CharacterIsShow(false);
                setSub15CharacterIsShow(false);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(false);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene38(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_38_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.SC_38_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_38_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.SC_38_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.T_38_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.SC_38_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.SC_38_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.MC_38_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.SC_38_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                textView.setText(R.string.MC_38_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                textView.setText(R.string.SC_38_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 11:
                textView.setText(R.string.MC_38_5);
                textView.setVisibility(VISIBLE);
                setFoodEnable(true);
                touch_judgeS = true;
                break;
            case 12:
                if(isStoryChangeable){
                    isStoryChangeable = false;
                    touch_judgeS = true;
                }else{
                    textView.setVisibility(INVISIBLE);
                    counter = 11;
                    touch_judgeS = false;
                }
                break;
            case 13:
                textView.setText(R.string.SC_38_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 14:
                textView.setText(R.string.MC_38_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 15:
                textView.setText(R.string.SC_38_8);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                setSub23CharacterBgNum(12,5f,3f);
                setSub23CharacterDirection(FRONT);
                setSub23CharacterIsShow(true);
                break;
            case 16:
                textView.setText(R.string.SC_38_9);
                textView.setVisibility(VISIBLE);
                setSub23CharacterDirection(RIGHT);
                touch_judgeS = true;
                break;
            case 17:
                textView.setText(R.string.MC_38_7);
                textView.setVisibility(VISIBLE);
                setSub23CharacterDirection(LEFT);
                touch_judgeS = true;
                break;
            case 18:
                textView.setText(R.string.SC_38_10);
                textView.setVisibility(VISIBLE);
                setSub23CharacterDirection(FRONT);
                touch_judgeS = true;
                break;
            case 19:
                postDelayed(9);
                textView.setText(R.string.MC_38_8);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                sub23PointList.add(new Point(5f,3f,Character.LEFT));
                sub23PointList.add(new Point(4.5f,3f,Character.LEFT));
                sub23PointList.add(new Point(4f,3f,Character.LEFT));
                sub23PointList.add(new Point(4f,3f, FRONT));
                sub23PointList.add(new Point(4f,3.5f, FRONT));
                sub23PointList.add(new Point(4f,4f, FRONT));
                sub23PointList.add(new Point(4f,4.5f, FRONT));
                sub23PointList.add(new Point(4f,5f, FRONT));
                sub23PointList.add(new Point(4f,5f,Character.RIGHT));
                break;
            case 20:
                textView.setText(R.string.SC_38_11);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                setMainCharacterDirection(LEFT);
                setSub23CharacterPointList(sub23PointList);
                break;
            case 21:
                textView.setText(R.string.SC_38_12);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                sub23PointList.add(new Point(4f,5f,Character.RIGHT));
                sub23PointList.add(new Point(4f,5f,Character.RIGHT));
                sub23PointList.add(new Point(4.5f,5f,Character.RIGHT));
                sub23PointList.add(new Point(4.5f,5f,Character.RIGHT));
                sub23PointList.add(new Point(-1f,-1f));
                break;
            case 22:
                textView.setText(R.string.MC_38_9);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                setSub23CharacterPointList(sub23PointList);
                break;
            case 23:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene39(){
        switch (counter){
            case 0:
                postDelayed(5);
                setSub23CharacterBgNum(5,3.5f,10f);
                setSub23CharacterIsShow(true);
                setMainCharacterDirection(RIGHT);
                sub23PointList.add(new Point(3.5f,10f,Character.RIGHT));
                sub23PointList.add(new Point(4f,10f,Character.RIGHT));
                sub23PointList.add(new Point(4f,10f,Character.RIGHT));
                sub23PointList.add(new Point(4f,10f,Character.BACK));
                sub23PointList.add(new Point(4f,10f,Character.LEFT));
                setSub23CharacterPointList(sub23PointList);
                textView.setText(R.string.SC_39_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.MC_39_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.SC_39_2);
                textView.setVisibility(VISIBLE);
                setObjectList();
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_39_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.SC_39_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.MC_39_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.SC_39_4);
                textView.setVisibility(VISIBLE);
                setObjectList();
                touch_judgeS = true;
                break;
            case 7:
                postDelayed(17);
                textView.setVisibility(INVISIBLE);
                sub23PointList.add(new Point(4f,10f,Character.BACK));
                sub23PointList.add(new Point(4f,9.5f,Character.BACK));
                sub23PointList.add(new Point(4f,9f,Character.BACK));
                sub23PointList.add(new Point(4f,8.5f,Character.BACK));
                sub23PointList.add(new Point(4f,8f,Character.BACK));
                sub23PointList.add(new Point(4f,7.5f,Character.BACK));
                sub23PointList.add(new Point(4f,7f,Character.BACK));
                sub23PointList.add(new Point(3.5f,7f,Character.LEFT));
                sub23PointList.add(new Point(3f,7f,Character.LEFT));
                sub23PointList.add(new Point(3f,6.5f,Character.BACK));
                sub23PointList.add(new Point(3f,6f,Character.BACK));
                sub23PointList.add(new Point(3f,5.5f,Character.BACK));
                sub23PointList.add(new Point(3f,5f,Character.BACK));
                sub23PointList.add(new Point(3f,4.5f,Character.BACK));
                sub23PointList.add(new Point(3f,4f,Character.BACK));
                sub23PointList.add(new Point(3f,3.5f,Character.BACK));
                sub23PointList.add(new Point(3f,3f,Character.BACK));
                sub23PointList.add(new Point(-1f,-1f,Character.BACK));
                setSub23CharacterPointList(sub23PointList);
                setSub19CharacterIsShow(true);
                touch_judgeS = true;
                break;
            case 8:
                counter = 7;
                touch_judgeS = false;
                break;
            case 9:
                textView.setText(R.string.SC_39_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                textView.setText(R.string.MC_39_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 11:
                textView.setText(R.string.SC_39_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 12:
                textView.setText(R.string.MC_39_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 13:
                textView.setText(R.string.SC_39_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 14:
                textView.setText(R.string.MC_39_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 15:
                textView.setText(R.string.SC_39_8_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 16:
                textView.setText(R.string.SC_39_8_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 17:
                textView.setText(R.string.SC_39_8_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 18:
                isViewAnimation = true;
                textView2.setText(R.string.day_10);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setSub12CharacterIsShow(false);
                        setSub13CharacterIsShow(true);
                        setSub14CharacterIsShow(true);
                        setSub16CharacterBgNum(4,-1f,-1f);
                        setSub17CharacterBgNum(4,-1f,-1f);
                        setSub18CharacterBgNum(4,-1f,-1f);
                        setSub16CharacterIsShow(true);
                        setSub17CharacterIsShow(true);
                        setSub18CharacterIsShow(true);
                        setMainCharacterPrePoint(new Point(2f,5f));
                        setMainCharacterDirection(BACK);
                        textView.setText("");
                        textView.setVisibility(INVISIBLE);
                        //date configure
                        TopBar.setDateText(R.string.day_10);
                        setSunsetJudge(false);
                        setOverObjectList();
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation = false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                break;
            case 19:
                postDelayed(8);
                textView.setText(R.string.SC_39_9);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                sub16PointList.add(new Point(7f,4f,Character.LEFT));
                sub16PointList.add(new Point(6.5f,4f,Character.LEFT));
                sub16PointList.add(new Point(6f,4f,Character.LEFT));
                sub16PointList.add(new Point(5.5f,4f,Character.LEFT));
                sub16PointList.add(new Point(5f,4f,Character.LEFT));
                sub16PointList.add(new Point(4.5f,4f,Character.LEFT));
                sub16PointList.add(new Point(4f,4f,Character.LEFT));
                sub17PointList.add(new Point(7f,5f,Character.LEFT));
                sub17PointList.add(new Point(6.5f,5f,Character.LEFT));
                sub17PointList.add(new Point(6f,5f,Character.LEFT));
                sub17PointList.add(new Point(5.5f,5f,Character.LEFT));
                sub17PointList.add(new Point(5f,5f,Character.LEFT));
                sub17PointList.add(new Point(4.5f,5f,Character.LEFT));
                sub17PointList.add(new Point(4f,5f,Character.LEFT));
                sub18PointList.add(new Point(-1f,-1f));
                sub18PointList.add(new Point(-1f,-1f));
                sub18PointList.add(new Point(7f,5f,Character.LEFT));
                sub18PointList.add(new Point(6.5f,5f,Character.LEFT));
                sub18PointList.add(new Point(6f,5f,Character.LEFT));
                sub18PointList.add(new Point(5.5f,5f,Character.LEFT));
                sub18PointList.add(new Point(5f,5f,Character.LEFT));
                sub18PointList.add(new Point(5f,5.5f, FRONT));
                sub18PointList.add(new Point(5f,6f, FRONT));
                sub18PointList.add(new Point(4.5f,6f,Character.LEFT));
                sub18PointList.add(new Point(4f,6f,Character.LEFT));
                break;
            case 20:
                textView.setText(R.string.MC_39_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                setSub16CharacterPointList(sub16PointList);
                setSub17CharacterPointList(sub17PointList);
                setSub18CharacterPointList(sub18PointList);
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isViewAnimation = false;
                    }
                },1200);
                break;
            case 21:
                textView.setText(R.string.SC_39_10);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 22:
                setMainCharacterDirection(RIGHT);
                textView.setText(R.string.MC_39_8);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 23:
                textView.setText(R.string.SC_39_11);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 24:
                textView.setText(R.string.MC_39_9);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 25:
                textView.setText(R.string.SC_39_12);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;

                break;
            case 26:
                postDelayed(8);
                textView.setText(R.string.MC_39_10);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                isViewAnimation = true;
                sub16PointList.clear();
                sub16PointList.add(new Point(4f,4f,Character.RIGHT));
                sub16PointList.add(new Point(4f,4f,Character.RIGHT));
                sub16PointList.add(new Point(4f,4f,Character.RIGHT));
                sub16PointList.add(new Point(4.5f,4f,Character.RIGHT));
                sub16PointList.add(new Point(5f,4f,Character.RIGHT));
                sub16PointList.add(new Point(5.5f,4f,Character.RIGHT));
                sub16PointList.add(new Point(6f,4f,Character.RIGHT));
                sub16PointList.add(new Point(6.5f,4f,Character.RIGHT));
                sub16PointList.add(new Point(7f,4f,Character.RIGHT));
                sub16PointList.add(new Point(-1f,-1f));
                sub17PointList.clear();
                sub17PointList.add(new Point(4f,5f,Character.RIGHT));
                sub17PointList.add(new Point(4f,5f,Character.RIGHT));
                sub17PointList.add(new Point(4f,5f,Character.RIGHT));
                sub17PointList.add(new Point(4f,5f,Character.RIGHT));
                sub17PointList.add(new Point(4f,5f,Character.RIGHT));
                sub17PointList.add(new Point(4.5f,5f,Character.RIGHT));
                sub17PointList.add(new Point(5f,5f,Character.RIGHT));
                sub17PointList.add(new Point(5.5f,5f,Character.RIGHT));
                sub17PointList.add(new Point(6f,5f,Character.RIGHT));
                sub17PointList.add(new Point(6.5f,5f,Character.RIGHT));
                sub17PointList.add(new Point(7f,5f,Character.RIGHT));
                sub17PointList.add(new Point(-1f,-1f));
                sub18PointList.clear();
                sub18PointList.add(new Point(4f,6f,Character.RIGHT));
                sub18PointList.add(new Point(4.5f,6f,Character.RIGHT));
                sub18PointList.add(new Point(5f,6f,Character.BACK));
                sub18PointList.add(new Point(5f,5.5f,Character.BACK));
                sub18PointList.add(new Point(5f,5f,Character.RIGHT));
                sub18PointList.add(new Point(5.5f,5f,Character.RIGHT));
                sub18PointList.add(new Point(6f,5f,Character.RIGHT));
                sub18PointList.add(new Point(6.5f,5f,Character.RIGHT));
                sub18PointList.add(new Point(7f,5f,Character.RIGHT));
                sub18PointList.add(new Point(-1f,-1f));
                setSub18CharacterPointList(sub18PointList);
                setSub16CharacterPointList(sub16PointList);
                setSub17CharacterPointList(sub17PointList);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isViewAnimation = false;
                    }
                },1200);
                break;
            case 27:
                setMainCharacterDirection(BACK);
                textView.setText(R.string.MC_39_11);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 28:
                textView.setText(R.string.SC_39_13);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 29:
                textView.setText(R.string.MC_39_12);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 30:
                textView.setText(R.string.SC_39_14);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 31:
                textView.setText(R.string.MC_39_13);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 32:
                textView.setText(R.string.SC_39_15);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 33:
                textView.setText(R.string.T_39_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 34:
                textView.setText(R.string.SC_39_16);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 35:
                textView.setText(R.string.MC_39_14);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 36:
                textView.setText(R.string.SC_39_17);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 37:
                textView.setText(R.string.MC_39_15);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 38:
                textView.setText(R.string.SC_39_18);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 39:
                textView.setText(R.string.MC_39_16);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 40:
                textView.setText(R.string.SC_39_19);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 41:
                textView.setText(R.string.MC_39_17);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 42:
                textView.setText(R.string.SC_39_20);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 43:
                textView.setText(R.string.SC_39_21);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 44:
                textView.setText(R.string.MC_39_18);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 45:
                textView.setText(R.string.SC_39_22);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 46:
                textView.setText(R.string.MC_39_19);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 47:
                textView.setText(R.string.SC_39_23);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 48:
                textView.setText(R.string.MC_39_20);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 49:
                textView.setText(R.string.SC_39_24);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 50:
                textView.setText(R.string.MC_39_21);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 51:
                setCharacter(40);
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene40(){
        switch (counter){
            case 0:
                setMainCharacterDirection(BACK);
                textView.setText(R.string.SC_40_1_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.SC_40_1_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                postDelayed(9);
                setSub20CharacterBgNum(4,-1,-1);
                setSub20CharacterIsShow(true);
                sub20PointList.add(new Point(7f,5f,Character.LEFT));
                sub20PointList.add(new Point(6.5f,5f,Character.LEFT));
                sub20PointList.add(new Point(6f,5f,Character.LEFT));
                sub20PointList.add(new Point(5.5f,5f,Character.LEFT));
                sub20PointList.add(new Point(5f,5f,Character.LEFT));
                sub20PointList.add(new Point(4.5f,5f,Character.LEFT));
                sub20PointList.add(new Point(4f,5f,Character.LEFT));
                sub20PointList.add(new Point(3.5f,5f,Character.LEFT));
                sub20PointList.add(new Point(3f,5f,Character.LEFT));
                setSub20CharacterPointList(sub20PointList);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.SC_40_1_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                setMainCharacterDirection(RIGHT);
                textView.setText(R.string.SC_40_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.SC_40_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                setMainCharacterDirection(BACK);
                textView.setText(R.string.MC_40_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.MC_40_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.SC_40_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                textView.setText(R.string.MC_40_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                textView.setText(R.string.SC_40_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 11:
                textView.setText(R.string.MC_40_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 12:
                textView.setText(R.string.SC_40_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 13:
                textView.setText(R.string.MC_40_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 14:
                textView.setText(R.string.SC_40_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 15:
                textView.setText(R.string.MC_40_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 16:
                textView.setText(R.string.SC_40_8);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 17:
                textView.setText(R.string.MC_40_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 18:
                textView.setText(R.string.SC_40_9);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 19:
                postDelayed(11);
                textView.setText(R.string.MC_40_8);
                textView.setVisibility(VISIBLE);
                sub20PointList.add(new Point(3f,5f,Character.RIGHT));
                sub20PointList.add(new Point(3.5f,5f,Character.RIGHT));
                sub20PointList.add(new Point(4f,5f,Character.RIGHT));
                sub20PointList.add(new Point(4.5f,5f,Character.RIGHT));
                sub20PointList.add(new Point(5f,5f,Character.RIGHT));
                sub20PointList.add(new Point(5.5f,5f,Character.RIGHT));
                sub20PointList.add(new Point(6f,5f,Character.RIGHT));
                sub20PointList.add(new Point(6.5f,5f,Character.RIGHT));
                sub20PointList.add(new Point(7f,5f,Character.RIGHT));
                sub20PointList.add(new Point(7.5f,5f,Character.RIGHT));
                sub20PointList.add(new Point(8f,5f,Character.RIGHT));
                setSub20CharacterPointList(sub20PointList);
                touch_judgeS = true;
                break;
            case 20:
                textView.setText(R.string.SC_40_10);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 21:
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene41(){
        switch (counter){
            case 0:
                textView.setText(R.string.MC_41_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.MC_41_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_41_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView2.setText(R.string.day_);
                textView2.setVisibility(VISIBLE);
                fadeIn();
                isViewAnimation = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setVisibility(INVISIBLE);
                        pointList.clear();
                        setMainCharacterIsPassive(true);
                        setMainCharacterBgNum(8,6f,2.5f);
                        setMainCharacterIsShow(true);
                        setMainCharacterIsHide(true);
                        setMainCharacterPrePoint(new Point(6f,2.5f, FRONT));
                        //date configure
                        TopBar.setDateText(R.string.day_);
                        fadeOut();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isViewAnimation = false;
                            }
                        },1000);
                    }
                },1000);
                touch_judgeS = true;
                isChapterFinish = true;
                break;
        }
        counter++;
    }
    void scene42(){
        switch (counter){
            case 0:
                textView.setText(R.string.ring_1_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                postDelayed(6);
                textView.setText(R.string.SC_42_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
//                isViewAnimation = true;
                pointList.clear();
                pointList.add(new Point(6f,3f, FRONT));
                pointList.add(new Point(5.5f,3f,Character.LEFT));
                pointList.add(new Point(5f,3f,Character.LEFT));
                pointList.add(new Point(4.5f,3f,Character.LEFT));
                pointList.add(new Point(4f,3f,Character.LEFT));
                pointList.add(new Point(4f,3f, FRONT));
                setMainCharacterPointList(pointList);
                break;
            case 2:
                postDelayed(6);
                textView.setText(R.string.MC_42_1);
                textView.setVisibility(VISIBLE);
                //date configure
                TopBar.setDateText(R.string.day_7_);
                touch_judgeS = true;
                pointList.clear();
                pointList.add(new Point(4f,3f,Character.RIGHT));
                pointList.add(new Point(4.5f,3f,Character.RIGHT));
                pointList.add(new Point(5f,3f,Character.RIGHT));
                pointList.add(new Point(5.5f,3f,Character.RIGHT));
                pointList.add(new Point(6f,3f,Character.RIGHT));
                pointList.add(new Point(6f,3f, FRONT));
                setMainCharacterPointList(pointList);
                break;
            case 3:
                postDelayed(6);
                textView.setText(R.string.MC_42_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                pointList.clear();
                pointList.add(new Point(6f,3f, FRONT));
                pointList.add(new Point(5.5f,3f,Character.LEFT));
                pointList.add(new Point(5f,3f,Character.LEFT));
                pointList.add(new Point(4.5f,3f,Character.LEFT));
                pointList.add(new Point(4f,3f,Character.LEFT));
                pointList.add(new Point(4f,3f, FRONT));
                setMainCharacterPointList(pointList);
                break;
            case 4:
                postDelayed(6);
                textView.setText(R.string.SC_42_2);
                textView.setVisibility(VISIBLE);
                //date configure
                TopBar.setDateText(R.string.day_7);
                touch_judgeS = true;
                pointList.clear();
                pointList.add(new Point(4f,3f,Character.RIGHT));
                pointList.add(new Point(4.5f,3f,Character.RIGHT));
                pointList.add(new Point(5f,3f,Character.RIGHT));
                pointList.add(new Point(5.5f,3f,Character.RIGHT));
                pointList.add(new Point(6f,3f,Character.RIGHT));
                pointList.add(new Point(6f,3f, FRONT));
                setMainCharacterPointList(pointList);
                break;
            case 5:
                postDelayed(6);
                textView.setText(R.string.MC_42_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                pointList.clear();
                pointList.add(new Point(6f,3f, FRONT));
                pointList.add(new Point(5.5f,3f,Character.LEFT));
                pointList.add(new Point(5f,3f,Character.LEFT));
                pointList.add(new Point(4.5f,3f,Character.LEFT));
                pointList.add(new Point(4f,3f,Character.LEFT));
                pointList.add(new Point(4f,3f, FRONT));
                setMainCharacterPointList(pointList);
                break;
            case 6:
                textView.setText(R.string.SC_42_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.MC_42_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.MC_42_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                postDelayed(6);
                textView.setText(R.string.SC_42_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                pointList.clear();
                pointList.add(new Point(4f,3f,Character.RIGHT));
                pointList.add(new Point(4.5f,3f,Character.RIGHT));
                pointList.add(new Point(5f,3f,Character.RIGHT));
                pointList.add(new Point(5.5f,3f,Character.RIGHT));
                pointList.add(new Point(6f,3f,Character.RIGHT));
                pointList.add(new Point(6f,3f, FRONT));
                setMainCharacterPointList(pointList);
                break;
            case 10:
                textView.setText(R.string.MC_42_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 11:
                textView.setText(R.string.MC_42_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 12:
                textView.setText(R.string.SC_42_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 13:
                postDelayed(6);
                textView.setText(R.string.MC_42_8);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                pointList.clear();
                pointList.add(new Point(6f,3f, FRONT));
                pointList.add(new Point(5.5f,3f,Character.LEFT));
                pointList.add(new Point(5f,3f,Character.LEFT));
                pointList.add(new Point(4.5f,3f,Character.LEFT));
                pointList.add(new Point(4f,3f,Character.LEFT));
                pointList.add(new Point(4f,3f, FRONT));
                setMainCharacterPointList(pointList);
                setMainCharacterPrePoint(new Point(4f,3f));
                break;
            case 14:
                textView.setText(R.string.SC_42_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 15:
                textView.setText(R.string.MC_42_9);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 16:
                textView.setText(R.string.SC_42_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 17:
                setMainCharacterIsHide(false);
                setSub1CharacterBgNum(3,5f,7f);
                setSub1CharacterDirection(Character.RIGHT);
                setSub1CharacterIsShow(true);
                setSub21CharacterBgNum(3,5f,7.5f);
                setSub21CharacterDirection(Character.RIGHT);
                setSub21CharacterIsShow(false);
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene43(){
        switch (counter){
            case 0:
                setStoryMusic(5);
                setEnding();
                setMainCharacterDirectionByCharacter(5f,7f);
                textView.setText(R.string.MC_43_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 1:
                setSub1CharacterDirection(Character.LEFT);
                setSub21CharacterDirection(FRONT);
                setSub21CharacterIsShow(true);
                textView.setText(R.string.SC_43_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                setMainCharacterDirection(Character.RIGHT);
                textView.setText(R.string.SC_43_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.MC_43_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                postDelayed(16);
                textView.setText(R.string.SC_43_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
//                isViewAnimation = true;
                sub21PointList.clear();
                sub21PointList.add(new Point(5f,8.5f, FRONT));
                sub21PointList.add(new Point(5f,9f, FRONT));
                sub21PointList.add(new Point(5f,9.5f, FRONT));
                sub21PointList.add(new Point(5f,10f, FRONT));
                sub21PointList.add(new Point(5f,10f,Character.LEFT));
                sub21PointList.add(new Point(4.5f,10f,Character.LEFT));
                sub21PointList.add(new Point(4f,10f,Character.LEFT));
                sub21PointList.add(new Point(3.5f,10f,Character.LEFT));
                sub21PointList.add(new Point(3f,10f,Character.LEFT));
                sub21PointList.add(new Point(2.5f,10f,Character.LEFT));
                sub21PointList.add(new Point(2f,10f,Character.LEFT));
                sub21PointList.add(new Point(1.5f,10f,Character.LEFT));
                sub21PointList.add(new Point(1f,10f,Character.LEFT));
                sub21PointList.add(new Point(0.5f,10f,Character.LEFT));
                sub21PointList.add(new Point(0f,10f,Character.LEFT));
                sub21PointList.add(new Point(-1f,-1f, FRONT));
                setSub21CharacterPointList(sub21PointList);
                break;
            case 5:
                textView.setText(R.string.SC_43_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView.setText(R.string.SC_43_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 7:
                textView.setText(R.string.MC_43_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 8:
                textView.setText(R.string.SC_43_6);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 9:
                textView.setText(R.string.MC_43_4);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 10:
                textView.setText(R.string.SC_43_7);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 11:
                postDelayed(3);
                textView.setText(R.string.MC_43_5);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                sub1PointList.clear();
                sub1PointList.add(new Point(5f,7f));
                sub1PointList.add(new Point(4.5f,7f));
                sub1PointList.add(new Point(-1f,-1f));
                setSub1CharacterPointList(sub1PointList);
                break;
            case 12:
                setSub1CharacterBgNum(4,-1,-1);
                setSub19CharacterIsShow(false);
                isChapterPartFinish = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }
    void scene44(){
        switch (counter){
            case 0://1718
                postDelayed(3);
                setMainCharacterDirection(Character.RIGHT);
                sub1PointList.clear();
                sub1PointList.add(new Point(1f,7.5f, FRONT));
                sub1PointList.add(new Point(1f,8f, FRONT));
                sub1PointList.add(new Point(1f,8f,Character.RIGHT));
                setSub1CharacterPointList(sub1PointList);
                touch_judgeS = true;
                break;
            case 1:
                textView.setText(R.string.MC_44_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 2:
                textView.setText(R.string.MC_44_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 3:
                textView.setText(R.string.SC_44_1);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 4:
                textView.setText(R.string.MC_44_3);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 5:
                textView.setText(R.string.SC_44_2);
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                break;
            case 6:
                textView2.setText("おわり");
                textView2.setVisibility(VISIBLE);
                fadeIn();
                setSub19CharacterIsShow(true);
                isViewAnimation = true;
                touch_judgeS = true;
                break;
        }
        counter++;
    }

    void postDelayed(long mils){
        mils = mils*milsWeight;
        isViewAnimation = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isViewAnimation = false;
            }
        },mils);
    }

    static void characterStoryEvent(){
        switch (characterStoryNum){
            case 20:
                talk_0_2_0();
                break;
            case 21:
                talk_0_2_1();
                break;
            case 50:
                talk_0_5_0();
                break;
            case 321:
                talk_3_2_1();
                break;
            case 120:
                talk_0_12_0();
                break;
            case 131:
                talk_0_13_1();
                break;
            case 360:
                talk_3_6_0();
                break;
            case 370:
                talk_3_7_0();
                break;
            case 390:
                talk_3_9_0();
                break;
            case 540:
                talk_5_4_0();
                break;
            case 541:
                talk_5_4_1();
                break;
            case 3100:
                talk_3_10_0();
                break;
            case 3110:
                talk_3_11_0();
                break;
            case 3130:
                talk_3_13_0();
                break;
            case 16211:
                talk_16_21_1();
                break;
            case 1701:
                talk_17_0_1();
                break;
            case 1751:
                talk_17_5_1();
                break;
            case 1771:
                talk_17_7_1();
                break;
            case 17111:
                talk_17_11_1();
                break;
            case 17121:
                talk_17_12_1();
                break;
            case 34191:
                talk_34_19_1();
                break;
            case 37221:
                talk_37_22_1();
                break;
            case 37231:
                talk_37_23_1();
                break;
            case 37241:
                talk_37_24_1();
                break;
            case 4051:
                talk_40_5_1();
                break;
            case 4071:
                talk_40_7_1();
                break;
            case 4061:
                talk_40_6_1();
                break;
            case 4091:
                talk_40_9_1();
                break;
            case 40111:
                talk_40_11_1();
                break;
        }
    }

    public static void talk_0_2_0(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("母：おはよう。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_0_2_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("母：あら、どこかへ出かけてくるの？。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_0_5_0(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("はなだ：よう、元気？");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_0_12_0(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("いけがみ：やぁ。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_0_13_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("こちらに気付いていないようだ。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_3_2_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("母：「しぃ」ちゃんを捜してきて。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_3_6_0(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("なみき：ここは自然がいっぱいでいいところよね。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_3_7_0(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("おざわ：そこのカフェからとても良い香りが漂ってくるよ。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_3_9_0(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("常連：ここのコーヒーはとってもおいしいんだ。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_3_10_0(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("客：何か用かしら。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_3_11_0(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("やまもと：あーあ、ひますぎちゃうわ。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_3_13_0(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("くさの：良いお天気ね。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_5_4_0(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("しーちゃんのお母さん：ゆうくん、おはよう。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_5_4_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("しーちゃんのお母さん：はぁ…　　");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_16_21_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("親イノシシ：フガァ　　");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_17_0_1(){
        switch (characterStoryCounter){
            case 0:
                if(getHouse()){
                    textView.setText("");
                    characterStoryCounter = 0;
                    characterStory = false;
                    textView.setVisibility(INVISIBLE);
                    touch_judgeS = false;
                    break;
                }
                textView.setText("鍵がかかっている。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                if(getHouse()){
                    textView.setText("");
                    characterStoryCounter = 0;
                    characterStory = false;
                    textView.setVisibility(INVISIBLE);
                    touch_judgeS = false;
                    break;
                }
                textView.setText("ゆう：暗証番号……");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 2:
                if(getHouse()){
                    textView.setText("");
                    characterStoryCounter = 0;
                    characterStory = false;
                    textView.setVisibility(INVISIBLE);
                    touch_judgeS = false;
                    break;
                }
                textView.setText("ゆう：誰か暗証番号を知っている人はいないだろうか。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 3:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_17_5_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("はなだ：池の小屋の暗証番号？");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("はなだ：何だったかなぁ……");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 2:
                textView.setText("はなだ：確か\"5\"が含まれていたような……");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 3:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }//小屋ヒント1
    public static void talk_17_7_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("おざわ：池の小屋の暗証番号について？");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("おざわ：最初の数字は\"7\"だよ。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 2:
                textView.setText("おざわ：あとは覚えていないな……");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 3:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_17_11_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("やまもと：この前池へお散歩に行ってきたんだけど近くにあった小屋は何かしら？");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("やまもと：気になったけど鍵がかかっていて入れなかったわ");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 2:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_17_12_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("いけがみ：そこの小屋の暗証番号？");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("いけがみ：何だったかなぁ……");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 2:
                textView.setText("いけがみ：確か最後の二桁は\"32\"だったような…");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 3:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_34_19_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("河童：……　　");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("(河童はじっとこっちをみている。)");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 2:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_37_22_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("ゆう(小さい頃)：みつかった？");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                if(getSub23CharacterIsShow()){
                    touch_judgeS = false;
                }else{
                    touch_judgeS = true;
                }
                break;
        }
    }
    public static void talk_37_23_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("しぃちゃん(小さい頃)：こっちにもみちがあるのね。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_37_24_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("りょう(小さい頃)：どこいっちゃたんだ？");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }
    public static void talk_40_7_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("おざわ：古い笛について？");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("おざわ：そういえばおじいちゃんが石像を叩くとか言っていたような……");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 2:
                textView.setText("おざわ：それに、叩く順番と方向も合っていないとだめって言ってたよ。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 3:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }//笛ヒント1
    public static void talk_40_6_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("なみき：古い笛について？");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("なみき：最初は東側から叩くってのは聞いたことがあるよ。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 2:
                if(Integer.valueOf(getSub6CharacterDirectionNum()).equals(FRONT)){
                    textView.setText("なみき：ちなみに今君は私の南側に居るよ。");
                }else if(Integer.valueOf(getSub6CharacterDirectionNum()).equals(Character.LEFT)){
                    textView.setText("なみき：ちなみに今君は私の西側に居るよ。");
                }else if(Integer.valueOf(getSub6CharacterDirectionNum()).equals(Character.BACK)){
                    textView.setText("なみき：ちなみに今君は私の北側に居るよ。");
                }
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 3:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }//笛ヒント2
    public static void talk_40_9_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("常連さん：古い笛？");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("常連さん：んん……確か2番目は西側からで、");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 2:
                textView.setText("常連さん：4番目は南側から叩くんだったと思うぞ。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 3:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }//笛ヒント3
    public static void talk_40_11_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("やまもと：古い笛かぁ……");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("やまもと：三回目に叩くのは北側からだって聞いたことあるけど……");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 2:
                textView.setText("やまもと：ごめんね、他は知らないや。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 3:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }//笛ヒント4
    public static void talk_40_5_1(){
        switch (characterStoryCounter){
            case 0:
                textView.setText("はなだ：古い笛？");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 1:
                textView.setText("はなだ：わからないなぁ。");
                textView.setVisibility(VISIBLE);
                touch_judgeS = true;
                characterStoryCounter++;
                break;
            case 2:
                textView.setText("");
                characterStoryCounter = 0;
                characterStory = false;
                textView.setVisibility(INVISIBLE);
                touch_judgeS = false;
                break;
        }
    }


    public LinearLayout getLinearLayout(){
        return linearLayout;
    }
    public FrameLayout getFrameLayout(){
        return frameLayout;
    }

    public static void setCharacter(int num){
//        Boolean bools[] = new Boolean[24];
//        Boolean boos[] = {false,true,true,true,true,true,true,true,true,true,true,true,true,false,false,false,false,true,true,true,true,true};
//        bools = boos;
        switch (num){
            case 1:
                //日付の設定
                TopBar.setDateText(R.string.day_7);
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(false);
                setSub2CharacterIsShow(true);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(true);
                setSub5CharacterIsShow(true);
                setSub6CharacterIsShow(true);
                setSub7CharacterIsShow(true);
                setSub8CharacterIsShow(true);
                setSub9CharacterIsShow(true);
                setSub10CharacterIsShow(true);
                setSub11CharacterIsShow(true);
                setSub12CharacterIsShow(true);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                setSub15CharacterIsShow(false);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(false);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);
                break;
            case 5://counterが2以外なら3と同じ
                if(Integer.valueOf(counter).equals(2)){
//                    LightEffect.setLightable(true);
//                    LightEffect.setLightX(getW_unit()*5);
//                    LightEffect.setLightY(getH_unit()*5);
//                    LightEffect.setLightBGNum(11);
                    touch_judgeS = false;
                }
            case 10://counterが4以外なら3と同じ
                if(Integer.valueOf(counter).equals(4)){
//                    LightEffect.setLightable(true);
//                    LightEffect.setLightX(getW_unit()*1);
//                    LightEffect.setLightY(getH_unit()*8);
//                    LightEffect.setLightBGNum(6);
                    touch_judgeS = true;
                }
            case 4://3と同じ
            case 7://3と同じ
            case 8://3と同じ
            case 9://3と同じ
            case 13://3と同じ
            case 14://3と同じ
            case 15://3と同じ
            case 17://3と同じ
            case 18://3と同じ
            case 19://3と同じ
            case 20://3と同じ
            case 22://3と同じ
            case 23://3と同じ
            case 24://3と同じ
            case 25://3と同じ
            case 26://3と同じ
            case 27://3と同じ
            case 29://3と同じ
            case 30://3と同じ
            case 3:
                //日付の設定
                TopBar.setDateText(R.string.day_8);
                if(num == 7 || num == 8){
                    TopBar.setDateText(R.string.day_5_);
                }else if(num == 9 || num == 10 || num == 14 || num == 15){
                    TopBar.setDateText(R.string.day_9);
                }else if(num == 13){
                    TopBar.setDateText(R.string.day_5);
                }else if(num == 17 || num == 18 || num == 19 || num == 20 || num == 22 || num == 30){
                    TopBar.setDateText(R.string.day_10);
                }else if(num == 23 || num == 24 || num == 25 || num == 26){
                    TopBar.setDateText(R.string.day_11);
                }else if(num == 27 || num == 29){
                    TopBar.setDateText(R.string.day_7);
                }
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(false);
                setSub2CharacterIsShow(true);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(true);
                setSub5CharacterIsShow(true);
                setSub6CharacterIsShow(true);
                setSub7CharacterIsShow(true);
                setSub8CharacterIsShow(true);
                setSub9CharacterIsShow(true);
                setSub10CharacterIsShow(true);
                setSub11CharacterIsShow(true);
                setSub12CharacterIsShow(true);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                setSub15CharacterIsShow(false);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(false);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);

                setMainCharacterIsHide(false);
                setSub13CharacterBgNum(4,1f,7f);
                setSub14CharacterBgNum(4,1f,8f);
                setSub13CharacterDirection(Character.RIGHT);
                setSub14CharacterDirection(Character.RIGHT);
                break;
            case 6:
                //日付の設定
                TopBar.setDateText(R.string.day_);
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(true);
                setSub2CharacterIsShow(true);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(true);
                setSub5CharacterIsShow(true);
                setSub6CharacterIsShow(true);
                setSub7CharacterIsShow(true);
                setSub8CharacterIsShow(true);
                setSub9CharacterIsShow(true);
                setSub10CharacterIsShow(true);
                setSub11CharacterIsShow(true);
                setSub12CharacterIsShow(true);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                setSub15CharacterIsShow(false);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(false);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);

                setMainCharacterIsHide(false);
                setSub1CharacterBgNum(1,4f,4f);
                setSub1CharacterDirection(Character.BACK);
                setSub13CharacterBgNum(4,1f,7f);
                setSub14CharacterBgNum(4,1f,8f);
                setSub13CharacterDirection(Character.RIGHT);
                setSub14CharacterDirection(Character.RIGHT);
                break;
            case 11:
                //日付の設定
                TopBar.setDateText(R.string.day_9);
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(true);
                setSub2CharacterIsShow(true);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(true);
                setSub5CharacterIsShow(true);
                setSub6CharacterIsShow(true);
                setSub7CharacterIsShow(true);
                setSub8CharacterIsShow(true);
                setSub9CharacterIsShow(true);
                setSub10CharacterIsShow(true);
                setSub11CharacterIsShow(true);
                setSub12CharacterIsShow(true);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                setSub15CharacterIsShow(false);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(false);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);

                setMainCharacterIsHide(false);
                setSub13CharacterBgNum(4,1f,7f);
                setSub14CharacterBgNum(4,1f,8f);
                setSub13CharacterDirection(Character.RIGHT);
                setSub14CharacterDirection(Character.RIGHT);
                break;
            case 12:
                //日付の設定
                TopBar.setDateText(R.string.day_9);
                switch (counter){
                    case 0:
                        setMainCharacterIsShow(true);
                        setSub1CharacterIsShow(false);
                        setSub2CharacterIsShow(true);
                        setSub3CharacterIsShow(false);
                        setSub4CharacterIsShow(true);
                        setSub5CharacterIsShow(true);
                        setSub6CharacterIsShow(true);
                        setSub7CharacterIsShow(true);
                        setSub8CharacterIsShow(true);
                        setSub9CharacterIsShow(true);
                        setSub10CharacterIsShow(true);
                        setSub11CharacterIsShow(true);
                        setSub12CharacterIsShow(true);
                        setSub13CharacterIsShow(true);
                        setSub14CharacterIsShow(true);
                        setSub15CharacterIsShow(false);
                        setSub16CharacterIsShow(false);
                        setSub17CharacterIsShow(false);
                        setSub18CharacterIsShow(false);
                        setSub19CharacterIsShow(false);
                        setSub20CharacterIsShow(false);
                        setSub21CharacterIsShow(false);
                        setSub22CharacterIsShow(false);
                        setSub23CharacterIsShow(false);
                        setSub24CharacterIsShow(false);

                        setMainCharacterIsHide(false);
                        setSub13CharacterBgNum(4,1f,7f);
                        setSub14CharacterBgNum(4,1f,8f);
                        setSub13CharacterDirection(Character.RIGHT);
                        setSub14CharacterDirection(Character.RIGHT);

//                        LightEffect.setLightable(true);
//                        LightEffect.setLightX(getW_unit()*5);
//                        LightEffect.setLightY(getH_unit()*5);
//                        LightEffect.setLightBGNum(11);
                        break;
                    case 2:
                        setMainCharacterIsShow(true);
                        setSub1CharacterIsShow(true);
                        setSub2CharacterIsShow(true);
                        setSub3CharacterIsShow(false);
                        setSub4CharacterIsShow(true);
                        setSub5CharacterIsShow(true);
                        setSub6CharacterIsShow(true);
                        setSub7CharacterIsShow(true);
                        setSub8CharacterIsShow(true);
                        setSub9CharacterIsShow(true);
                        setSub10CharacterIsShow(true);
                        setSub11CharacterIsShow(true);
                        setSub12CharacterIsShow(true);
                        setSub13CharacterIsShow(true);
                        setSub14CharacterIsShow(true);
                        setSub15CharacterIsShow(false);
                        setSub16CharacterIsShow(false);
                        setSub17CharacterIsShow(false);
                        setSub18CharacterIsShow(false);
                        setSub19CharacterIsShow(false);
                        setSub20CharacterIsShow(false);
                        setSub21CharacterIsShow(false);
                        setSub22CharacterIsShow(false);
                        setSub23CharacterIsShow(false);
                        setSub24CharacterIsShow(false);

                        setMainCharacterIsHide(false);
                        setSub1CharacterBgNum(1,4f,4f);
                        setSub1CharacterDirection(Character.BACK);
                        setSub13CharacterBgNum(4,1f,7f);
                        setSub14CharacterBgNum(4,1f,8f);
                        setSub13CharacterDirection(Character.RIGHT);
                        setSub14CharacterDirection(Character.RIGHT);
                        break;

                }
                break;
            case 16:
                //日付の設定
                TopBar.setDateText(R.string.day_10);
                if(Integer.valueOf(counter).equals(1)){
                    setMainCharacterIsShow(true);
                    setSub1CharacterIsShow(false);
                    setSub2CharacterIsShow(true);
                    setSub3CharacterIsShow(false);
                    setSub4CharacterIsShow(true);
                    setSub5CharacterIsShow(true);
                    setSub6CharacterIsShow(true);
                    setSub7CharacterIsShow(true);
                    setSub8CharacterIsShow(true);
                    setSub9CharacterIsShow(true);
                    setSub10CharacterIsShow(true);
                    setSub11CharacterIsShow(true);
                    setSub12CharacterIsShow(true);
                    setSub13CharacterIsShow(true);
                    setSub14CharacterIsShow(true);
                    setSub15CharacterIsShow(false);
                    setSub16CharacterIsShow(false);
                    setSub17CharacterIsShow(false);
                    setSub18CharacterIsShow(false);
                    setSub19CharacterIsShow(false);
                    setSub20CharacterIsShow(false);
                    setSub21CharacterIsShow(true);
                    setSub22CharacterIsShow(false);
                    setSub23CharacterIsShow(false);
                    setSub24CharacterIsShow(false);

                    setMainCharacterIsHide(false);
                    setSub13CharacterBgNum(4,1f,7f);
                    setSub14CharacterBgNum(4,1f,8f);
                    setSub13CharacterDirection(Character.RIGHT);
                    setSub14CharacterDirection(Character.RIGHT);
                    setSub21CharacterBgNum(3,3f,6f);
                    setSub21CharacterDirection(FRONT);
                }else if(Integer.valueOf(counter).equals(5)){
                    setMainCharacterIsShow(true);
                    setSub1CharacterIsShow(false);
                    setSub2CharacterIsShow(true);
                    setSub3CharacterIsShow(false);
                    setSub4CharacterIsShow(true);
                    setSub5CharacterIsShow(true);
                    setSub6CharacterIsShow(true);
                    setSub7CharacterIsShow(true);
                    setSub8CharacterIsShow(true);
                    setSub9CharacterIsShow(true);
                    setSub10CharacterIsShow(true);
                    setSub11CharacterIsShow(true);
                    setSub12CharacterIsShow(true);
                    setSub13CharacterIsShow(true);
                    setSub14CharacterIsShow(true);
                    setSub15CharacterIsShow(false);
                    setSub16CharacterIsShow(false);
                    setSub17CharacterIsShow(false);
                    setSub18CharacterIsShow(false);
                    setSub19CharacterIsShow(false);
                    setSub20CharacterIsShow(true);
                    setSub21CharacterIsShow(true);
                    setSub22CharacterIsShow(false);
                    setSub23CharacterIsShow(false);
                    setSub24CharacterIsShow(false);

                    setMainCharacterIsHide(false);
                    setSub13CharacterBgNum(4,1f,7f);
                    setSub14CharacterBgNum(4,1f,8f);
                    setSub13CharacterDirection(Character.RIGHT);
                    setSub14CharacterDirection(Character.RIGHT);
                    setSub20CharacterBgNum(3,4f,5f);
                    setSub20CharacterDirection(FRONT);
                    setSub21CharacterBgNum(3,3f,6f);
                    setSub21CharacterDirection(FRONT);
                } else{
                    setMainCharacterIsShow(true);
                    setSub1CharacterIsShow(false);
                    setSub2CharacterIsShow(true);
                    setSub3CharacterIsShow(false);
                    setSub4CharacterIsShow(true);
                    setSub5CharacterIsShow(true);
                    setSub6CharacterIsShow(true);
                    setSub7CharacterIsShow(true);
                    setSub8CharacterIsShow(true);
                    setSub9CharacterIsShow(true);
                    setSub10CharacterIsShow(true);
                    setSub11CharacterIsShow(true);
                    setSub12CharacterIsShow(true);
                    setSub13CharacterIsShow(true);
                    setSub14CharacterIsShow(true);
                    setSub15CharacterIsShow(false);
                    setSub16CharacterIsShow(false);
                    setSub17CharacterIsShow(false);
                    setSub18CharacterIsShow(false);
                    setSub19CharacterIsShow(false);
                    setSub20CharacterIsShow(false);
                    setSub21CharacterIsShow(false);
                    setSub22CharacterIsShow(false);
                    setSub23CharacterIsShow(false);
                    setSub24CharacterIsShow(false);

                    setMainCharacterIsHide(false);
                    setSub13CharacterBgNum(4,1f,7f);
                    setSub14CharacterBgNum(4,1f,8f);
                    setSub13CharacterDirection(Character.RIGHT);
                    setSub14CharacterDirection(Character.RIGHT);
                }
                break;
            case 21:
                //日付の設定
                TopBar.setDateText(R.string.day_7);
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(true);
                setSub2CharacterIsShow(true);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(true);
                setSub5CharacterIsShow(true);
                setSub6CharacterIsShow(true);
                setSub7CharacterIsShow(true);
                setSub8CharacterIsShow(true);
                setSub9CharacterIsShow(true);
                setSub10CharacterIsShow(true);
                setSub11CharacterIsShow(true);
                setSub12CharacterIsShow(true);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                setSub15CharacterIsShow(false);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(false);
                setSub20CharacterIsShow(true);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);

                setMainCharacterIsHide(false);
                setSub1CharacterBgNum(13,5,2);
                setSub1CharacterDirection(FRONT);
                setSub13CharacterBgNum(4,1f,7f);
                setSub14CharacterBgNum(4,1f,8f);
                setSub13CharacterDirection(Character.RIGHT);
                setSub14CharacterDirection(Character.RIGHT);
                setSub20CharacterBgNum(13,5,3);
                setSub20CharacterDirection(Character.BACK);
                break;
            case 28:
                //日付の設定
                TopBar.setDateText(R.string.day_7);
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(true);
                setSub2CharacterIsShow(true);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(true);
                setSub5CharacterIsShow(true);
                setSub6CharacterIsShow(true);
                setSub7CharacterIsShow(true);
                setSub8CharacterIsShow(true);
                setSub9CharacterIsShow(true);
                setSub10CharacterIsShow(true);
                setSub11CharacterIsShow(true);
                setSub12CharacterIsShow(true);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                setSub15CharacterIsShow(false);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(false);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);

                setMainCharacterIsHide(false);
                setSub1CharacterBgNum(3,5f,7f);
                setSub1CharacterDirection(Character.RIGHT);
                setSub12CharacterBgNum(14,6f,2f);
                setSub12CharacterDirection(Character.FRONT);
                setSub19CharacterBgNum(4,2f,2f);
                setSub19CharacterDirection(FRONT);
                setSub13CharacterBgNum(2,3f,1f);
                setSub14CharacterBgNum(2,4f,1f);
                setSub13CharacterDirection(Character.RIGHT);
                setSub14CharacterDirection(Character.BACK);
                break;
            case 31:
                //日付の設定
                TopBar.setDateText(R.string.day_10);
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(false);
                setSub2CharacterIsShow(true);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(true);
                setSub5CharacterIsShow(true);
                setSub6CharacterIsShow(true);
                setSub7CharacterIsShow(true);
                setSub8CharacterIsShow(true);
                setSub9CharacterIsShow(true);
                setSub10CharacterIsShow(true);
                setSub11CharacterIsShow(true);
                setSub12CharacterIsShow(true);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                setSub15CharacterIsShow(true);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(false);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);

                setMainCharacterIsHide(false);
                setSub12CharacterBgNum(14,6f,2f);
                setSub12CharacterDirection(Character.FRONT);
                setSub13CharacterBgNum(14,2f,10f);
                setSub14CharacterBgNum(14,3f,10f);
                setSub13CharacterDirection(Character.BACK);
                setSub14CharacterDirection(Character.BACK);
                setSub15CharacterBgNum(7,5f,8f);
                setSub15CharacterDirection(FRONT);
                break;
            case 33://32と同じ
            case 32:
                //日付の設定
                TopBar.setDateText(R.string.day_10);
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(false);
                setSub2CharacterIsShow(true);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(true);
                setSub5CharacterIsShow(true);
                setSub6CharacterIsShow(true);
                setSub7CharacterIsShow(true);
                setSub8CharacterIsShow(true);
                setSub9CharacterIsShow(true);
                setSub10CharacterIsShow(true);
                setSub11CharacterIsShow(true);
                setSub12CharacterIsShow(true);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                setSub15CharacterIsShow(true);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(false);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);

                setMainCharacterIsHide(false);
                setSub12CharacterBgNum(14,6f,2f);
                setSub12CharacterDirection(Character.FRONT);
                setSub13CharacterBgNum(14,2f,10f);
                setSub14CharacterBgNum(14,3f,10f);
                setSub13CharacterDirection(Character.BACK);
                setSub14CharacterDirection(Character.BACK);
                setSub15CharacterBgNum(4,2f,5f);
                setSub15CharacterDirection(Character.BACK);
                break;
            case 35://34と同じ
            case 36://34と同じ
            case 34:
                //日付の設定
                TopBar.setDateText(R.string.day_10);
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(false);
                setSub2CharacterIsShow(true);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(true);
                setSub5CharacterIsShow(true);
                setSub6CharacterIsShow(true);
                setSub7CharacterIsShow(true);
                setSub8CharacterIsShow(true);
                setSub9CharacterIsShow(true);
                setSub10CharacterIsShow(true);
                setSub11CharacterIsShow(true);
                setSub12CharacterIsShow(true);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                setSub15CharacterIsShow(false);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(true);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);

                setMainCharacterIsHide(false);
                setSub12CharacterBgNum(14,6f,2f);
                setSub12CharacterDirection(Character.FRONT);
                setSub13CharacterBgNum(14,2f,10f);
                setSub14CharacterBgNum(14,3f,10f);
                setSub13CharacterDirection(Character.BACK);
                setSub14CharacterDirection(Character.BACK);
                setSub19CharacterBgNum(4,2f,2f);
                setSub19CharacterDirection(FRONT);
                break;
            case 37:
                //日付の設定
                TopBar.setDateText(R.string.day_m_);
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(false);
                setSub2CharacterIsShow(true);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(true);
                setSub5CharacterIsShow(false);
                setSub6CharacterIsShow(false);
                setSub7CharacterIsShow(true);
                setSub8CharacterIsShow(true);
                setSub9CharacterIsShow(true);
                setSub10CharacterIsShow(false);
                setSub11CharacterIsShow(true);
                setSub12CharacterIsShow(false);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                setSub15CharacterIsShow(false);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(false);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(true);
                setSub23CharacterIsShow(true);
                setSub24CharacterIsShow(true);

                setMainCharacterIsHide(false);
                setSub7CharacterBgNum(11,6f,7f);
                setSub7CharacterDirection(LEFT);
                setSub13CharacterBgNum(5,7f,8f);
                setSub14CharacterBgNum(5,7f,9f);
                setSub13CharacterDirection(Character.LEFT);
                setSub14CharacterDirection(Character.LEFT);
                if(getCounter() >17){
                    setSub22CharacterBgNum(4,4f,5f);
                    setSub22CharacterDirection(FRONT);
                    setSub23CharacterBgNum(4,6f,2f);
                    setSub23CharacterDirection(Character.BACK);
                    setSub24CharacterBgNum(4,1f,6f);
                    setSub24CharacterDirection(Character.LEFT);
                }else{
                    setSub22CharacterBgNum(7,1f,6f);
                    setSub22CharacterDirection(Character.RIGHT);
                    setSub23CharacterBgNum(7,1f,5f);
                    setSub23CharacterDirection(FRONT);
                    setSub24CharacterBgNum(7,2f,6f);
                    setSub24CharacterDirection(Character.LEFT);
                }
                break;
            case 39://38と同じ
            case 38:
                //日付の設定
                TopBar.setDateText(R.string.day_m_);
                setSunsetJudge(true);
                setOverObjectList();
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(false);
                setSub2CharacterIsShow(false);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(false);
                setSub5CharacterIsShow(false);
                setSub6CharacterIsShow(false);
                setSub7CharacterIsShow(false);
                setSub8CharacterIsShow(false);
                setSub9CharacterIsShow(false);
                setSub10CharacterIsShow(false);
                setSub11CharacterIsShow(false);
                setSub12CharacterIsShow(false);
                setSub13CharacterIsShow(false);
                setSub14CharacterIsShow(false);
                setSub15CharacterIsShow(false);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(false);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);

                setMainCharacterIsHide(false);
                setSub12CharacterBgNum(14,6f,2f);
                setSub12CharacterDirection(Character.FRONT);
                setSub13CharacterBgNum(14,2f,10f);
                setSub14CharacterBgNum(14,3f,10f);
                setSub13CharacterDirection(Character.BACK);
                setSub14CharacterDirection(Character.BACK);
                setSub19CharacterBgNum(4,2f,2f);
                setSub19CharacterDirection(FRONT);
                break;
            case 42:
            case 41:
            case 40:
                if(num == 40 || num == 41){
                    TopBar.setDateText(R.string.day_10);
                }else if(num == 42){
                    TopBar.setDateText(R.string.day_);
                }
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(false);
                setSub2CharacterIsShow(true);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(true);
                setSub5CharacterIsShow(true);
                setSub6CharacterIsShow(true);
                setSub7CharacterIsShow(true);
                setSub8CharacterIsShow(true);
                setSub9CharacterIsShow(true);
                setSub10CharacterIsShow(true);
                setSub11CharacterIsShow(true);
                setSub12CharacterIsShow(true);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                setSub15CharacterIsShow(false);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(true);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);

                setMainCharacterIsHide(false);
                setSub7CharacterBgNum(1,1f,7f);
                setSub12CharacterBgNum(14,6f,2f);
                setSub12CharacterDirection(Character.FRONT);
                setSub13CharacterBgNum(14,2f,10f);
                setSub14CharacterBgNum(14,3f,10f);
                setSub13CharacterDirection(Character.BACK);
                setSub14CharacterDirection(Character.BACK);
                setSub19CharacterBgNum(4,2f,2f);
                setSub19CharacterDirection(FRONT);
                break;
            case 43:
                //日付の設定
                TopBar.setDateText(R.string.day_7);
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(true);
                setSub2CharacterIsShow(true);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(true);
                setSub5CharacterIsShow(true);
                setSub6CharacterIsShow(true);
                setSub7CharacterIsShow(true);
                setSub8CharacterIsShow(true);
                setSub9CharacterIsShow(true);
                setSub10CharacterIsShow(true);
                setSub11CharacterIsShow(true);
                setSub12CharacterIsShow(true);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                setSub15CharacterIsShow(false);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(false);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);

                setMainCharacterIsHide(false);
                setSub7CharacterBgNum(1,1f,7f);
                setSub12CharacterBgNum(14,6f,2f);
                setSub12CharacterDirection(Character.FRONT);
                setSub13CharacterBgNum(14,2f,10f);
                setSub14CharacterBgNum(14,3f,10f);
                setSub13CharacterDirection(Character.BACK);
                setSub14CharacterDirection(Character.BACK);

                setSub1CharacterBgNum(3,5f,7f);
                setSub1CharacterDirection(Character.RIGHT);
                setSub21CharacterBgNum(3,5f,7.5f);
                setSub21CharacterDirection(Character.RIGHT);
                break;
            case 44:
                //日付の設定
                TopBar.setDateText(R.string.day_7);
                //bgm
                setStoryMusic(5);
                setEnding();
                setMainCharacterIsShow(true);
                setSub1CharacterIsShow(true);
                setSub2CharacterIsShow(true);
                setSub3CharacterIsShow(false);
                setSub4CharacterIsShow(true);
                setSub5CharacterIsShow(true);
                setSub6CharacterIsShow(true);
                setSub7CharacterIsShow(true);
                setSub8CharacterIsShow(true);
                setSub9CharacterIsShow(true);
                setSub10CharacterIsShow(true);
                setSub11CharacterIsShow(true);
                setSub12CharacterIsShow(true);
                setSub13CharacterIsShow(true);
                setSub14CharacterIsShow(true);
                setSub15CharacterIsShow(false);
                setSub16CharacterIsShow(false);
                setSub17CharacterIsShow(false);
                setSub18CharacterIsShow(false);
                setSub19CharacterIsShow(false);
                setSub20CharacterIsShow(false);
                setSub21CharacterIsShow(false);
                setSub22CharacterIsShow(false);
                setSub23CharacterIsShow(false);
                setSub24CharacterIsShow(false);

                setMainCharacterIsHide(false);
                setSub1CharacterBgNum(4,-1,-1);
                setSub7CharacterBgNum(1,1f,7f);
                setSub12CharacterBgNum(14,6f,2f);
                setSub12CharacterDirection(Character.FRONT);
                setSub13CharacterBgNum(14,2f,10f);
                setSub14CharacterBgNum(14,3f,10f);
                setSub13CharacterDirection(Character.BACK);
                setSub14CharacterDirection(Character.BACK);
                setSub19CharacterBgNum(4,2f,2f);
                setSub19CharacterDirection(FRONT);

                break;
        }

    }
    public static void setTextConv(){
        int num = getChapter();
        if(Integer.valueOf(getChapter()).equals(21) && Integer.valueOf(getCounter()).equals(1)){
            num = 211;
        }
        switch(num){//-1した値(前の章の会話内容を得るため)
            case 1:
                textConv.setText(context.getString(R.string.ring_1_1)+"\n\n"+context.getString(R.string.MC_1_1)
                        +"\n\n"+context.getString(R.string.SC_1_1)+"\n\n"+context.getString(R.string.MC_1_2)+"\n\n"
                        +context.getString(R.string.T_1_1)+"\n\n"+context.getString(R.string.SC_1_2)+"\n\n"
                        +context.getString(R.string.MC_1_3)+"\n\n"+context.getString(R.string.MC_1_4)+"\n\n"
                        +context.getString(R.string.MC_1_5)+"\n\n");
                break;
            case 2:
                textConv.setText(context.getString(R.string.SC_2_1)
                        +"\n\n"
                        +context.getString(R.string.MC_2_1)
                        +"\n\n"
                        +context.getString(R.string.SC_2_2)
                        +"\n\n"
                        +context.getString(R.string.MC_2_2)
                        +"\n\n"
                        +context.getString(R.string.SC_2_3)
                        +"\n\n"
                        +context.getString(R.string.T_2_1)
                        +"\n\n"
                        +context.getString(R.string.MC_2_3)
                        +"\n\n"
                        +context.getString(R.string.SC_2_4)
                        +"\n\n"
                        +context.getString(R.string.MC_2_4)
                        +"\n\n"
                        +context.getString(R.string.SC_2_5)
                        +"\n\n"
                        +context.getString(R.string.MC_2_5)
                        +"\n\n"
                        +context.getString(R.string.T_2_2)
                        +"\n\n");
                break;
            case 3:
                textConv.setText(context.getString(R.string.SC_3_1)
                        +"\n\n"
                        +context.getString(R.string.MC_3_1)
                        +"\n\n"
                        +context.getString(R.string.SC_3_2)
                        +"\n\n"
                        +context.getString(R.string.MC_3_2)
                        +"\n\n"
                        +context.getString(R.string.SC_3_3)
                        +"\n\n"
                        +context.getString(R.string.SC_3_4)
                        +"\n\n"
                        +context.getString(R.string.MC_3_3)
                        +"\n\n"
                        +context.getString(R.string.SC_3_5)
                        +"\n\n"
                        +context.getString(R.string.MC_3_4)
                        +"\n\n");
                break;
            case 4:
                textConv.setText(context.getString(R.string.SC_4_1)
                        +"\n\n"
                        +context.getString(R.string.T_4_1)
                        +"\n\n"
                        +context.getString(R.string.MC_4_1)
                        +"\n\n"
                        +context.getString(R.string.SC_4_2)
                        +"\n\n"
                        +context.getString(R.string.MC_4_2)
                        +"\n\n"
                        +context.getString(R.string.T_4_2)
                        +"\n\n");
                break;
            case 5:
                textConv.setText(context.getString(R.string.MC_5_1)
                        +"\n\n"
                        +context.getString(R.string.T_5_1)
                        +"\n\n"
                        +context.getString(R.string.SC_5_1)
                        +"\n\n"
                        +context.getString(R.string.MC_5_2)
                        +"\n\n"
                        +context.getString(R.string.T_5_2)
                        +"\n\n"
                        +context.getString(R.string.MC_5_3)
                        +"\n\n");
                break;
            case 6:
                textConv.setText(context.getString(R.string.MC_6_1)
                        +"\n\n"
                        +context.getString(R.string.MC_6_2)
                        +"\n\n"
                        +context.getString(R.string.MC_6_3)
                        +"\n\n"
                        +context.getString(R.string.MC_6_4)
                        +"\n\n");
                break;
            case 7:
                textConv.setText(context.getString(R.string.SC_6_1)
                        +"\n\n"
                        +context.getString(R.string.MC_6_5)
                        +"\n\n"
                        +context.getString(R.string.SC_6_2)
                        +"\n\n"
                        +context.getString(R.string.MC_6_6)
                        +"\n\n"
                        +context.getString(R.string.SC_6_3)
                        +"\n\n"
                        +context.getString(R.string.MC_6_7)
                        +"\n\n"
                        +context.getString(R.string.SC_6_4)
                        +"\n\n"
                        +context.getString(R.string.MC_6_8)
                        +"\n\n"
                        +context.getString(R.string.SC_6_5)
                        +"\n\n"
                        +context.getString(R.string.MC_6_9)
                        +"\n\n"
                        +context.getString(R.string.SC_6_6)
                        +"\n\n"
                        +context.getString(R.string.MC_6_10)
                        +"\n\n");
                break;
            case 8:
                textConv.setText(context.getString(R.string.MC_6_11)
                        +"\n\n"
                        +context.getString(R.string.MC_6_12)
                        +"\n\n"
                        +context.getString(R.string.T_6_1_1)
                        +"\n\n"
                        +context.getString(R.string.T_6_1_2)
                        +"\n\n"
                        +context.getString(R.string.T_6_2_1)
                        +"\n\n"
                        +context.getString(R.string.T_6_2_2)
                        +"\n\n"
                        +context.getString(R.string.T_6_3)
                        +"\n\n"
                        +context.getString(R.string.MC_6_13)
                        +"\n\n");
                break;
            case 9:
                textConv.setText(context.getString(R.string.MC_7_1)
                        +"\n\n");
                break;
            case 10:
                textConv.setText(context.getString(R.string.SC_8_1)
                        +"\n\n"
                        +context.getString(R.string.MC_8_1)
                        +"\n\n"
                        +context.getString(R.string.MC_8_2)
                        +"\n\n"
                        +context.getString(R.string.SC_8_2)
                        +"\n\n"
                        +context.getString(R.string.MC_8_3)
                        +"\n\n");
                break;
            case 11:
                textConv.setText(context.getString(R.string.MC_9_1)
                        +"\n\n"
                        +context.getString(R.string.MC_9_2)
                        +"\n\n"
                        +context.getString(R.string.MC_9_3)
                        +"\n\n"
                        +context.getString(R.string.MC_9_4)
                        +"\n\n"
                        +context.getString(R.string.MC_9_5)
                        +"\n\n"
                        +context.getString(R.string.MC_9_6)
                        +"\n\n"
                        +context.getString(R.string.MC_9_7)
                        +"\n\n"
                        +context.getString(R.string.MC_9_8)
                        +"\n\n"
                        +context.getString(R.string.MC_9_9)
                        +"\n\n"
                        +context.getString(R.string.MC_9_10)
                        +"\n\n"
                        +context.getString(R.string.MC_9_11)
                        +"\n\n"
                        +context.getString(R.string.MC_9_12)
                        +"\n\n"
                        +context.getString(R.string.MC_9_13)
                        +"\n\n"
                        +context.getString(R.string.MC_9_14)
                        +"\n\n"
                        +context.getString(R.string.MC_9_15)
                        +"\n\n");
                break;
            case 12:
                textConv.setText(context.getString(R.string.MC_10_1)
                        +"\n\n"
                        +context.getString(R.string.MC_10_2)
                        +"\n\n");
                break;
            case 13:
                textConv.setText(context.getString(R.string.MC_11_1)
                        +"\n\n"
                        +context.getString(R.string.MC_11_2)
                        +"\n\n"
                        +context.getString(R.string.MC_11_3)
                        +"\n\n"
                        +context.getString(R.string.SC_11_1)
                        +"\n\n");
                break;
            case 14:
                textConv.setText(context.getString(R.string.MC_12_1)
                        +"\n\n"
                        +context.getString(R.string.SC_12_1)
                        +"\n\n"
                        +context.getString(R.string.MC_12_2)
                        +"\n\n"
                        +context.getString(R.string.MC_12_3)
                        +"\n\n"
                        +context.getString(R.string.SC_12_2)
                        +"\n\n"
                        +context.getString(R.string.MC_12_4)
                        +"\n\n"
                        +context.getString(R.string.MC_12_5)
                        +"\n\n"
                        +context.getString(R.string.SC_12_3)
                        +"\n\n"
                        +context.getString(R.string.MC_12_6)
                        +"\n\n"
                        +context.getString(R.string.SC_12_4)
                        +"\n\n");
                break ;
            case 15:
                textConv.setText((context.getText(R.string.MC_14_1)+"\n\n"
                        +context.getString(R.string.SC_14_1)+"\n\n"
                        +context.getString(R.string.T_14_1)+"\n\n"
                        +context.getString(R.string.T_14_2)+"\n\n"
                        +context.getString(R.string.MC_14_2)+"\n\n"+context.getString(R.string.MC_14_3)+"\n\n"));
                break;
            case 16:
                textConv.setText(context.getString(R.string.MC_15_1)
                        +"\n\n"
                        +context.getString(R.string.MC_15_2)
                        +"\n\n"
                        +context.getString(R.string.MC_15_3)
                        +"\n\n");
                break;
            case 17:
                textConv.setText(context.getString(R.string.MC_16_1)
                        +"\n\n"
                        +context.getString(R.string.MC_16_2)
                        +"\n\n"
                        +context.getString(R.string.MC_16_3)
                        +"\n\n"
                        +context.getString(R.string.MC_16_4)
                        +"\n\n"
                        +context.getString(R.string.MC_16_5)
                        +"\n\n");
                break;
            case 18:
                textConv.setText(context.getString(R.string.MC_17_1)
                        +"\n\n"
                        +context.getString(R.string.MC_17_2)
                        +"\n\n"
                        +context.getString(R.string.T_17_1)
                        +"\n\n"
                        +context.getString(R.string.MC_17_3)
                        +"\n\n"
                        +context.getString(R.string.T_17_2)
                        +"\n\n"
                        +context.getString(R.string.MC_17_4)
                        +"\n\n");
                break;
            case 19:
                textConv.setText(context.getString(R.string.SC_18_1)
                        +"\n\n"
                        +context.getString(R.string.MC_18_1)
                        +"\n\n"
                        +context.getString(R.string.SC_18_2)
                        +"\n\n"
                        +context.getString(R.string.MC_18_2)
                        +"\n\n"
                        +context.getString(R.string.SC_18_3)
                        +"\n\n"
                        +context.getString(R.string.MC_18_3)
                        +"\n\n"
                        +context.getString(R.string.SC_18_4)
                        +"\n\n"
                        +context.getString(R.string.MC_18_4)
                        +"\n\n"
                        +context.getString(R.string.SC_18_5)
                        +"\n\n"
                        +context.getString(R.string.MC_18_5)
                        +"\n\n"
                        +context.getString(R.string.SC_18_6)
                        +"\n\n"
                        +context.getString(R.string.MC_18_6)
                        +"\n\n"
                        +context.getString(R.string.SC_18_7)
                        +"\n\n"
                        +context.getString(R.string.MC_18_7)
                        +"\n\n"
                        +context.getString(R.string.MC_18_8)
                        +"\n\n"
                        +context.getString(R.string.SC_18_8)
                        +"\n\n"
                        +context.getString(R.string.MC_18_9)
                        +"\n\n"
                        +context.getString(R.string.SC_18_9)
                        +"\n\n"
                        +context.getString(R.string.MC_18_10)
                        +"\n\n"
                        +context.getString(R.string.MC_18_11)
                        +"\n\n"
                        +context.getString(R.string.SC_18_10)
                        +"\n\n"
                        +context.getString(R.string.MC_18_12)
                        +"\n\n");
                break;
            case 20:
                textConv.setText(context.getString(R.string.MC_19_1)
                        +"\n\n"
                        +context.getString(R.string.MC_19_2)
                        +"\n\n"
                        +context.getString(R.string.MC_19_3)
                        +"\n\n"
                        +context.getString(R.string.MC_19_4)
                        +"\n\n"
                        +context.getString(R.string.MC_19_5)
                        +"\n\n"
                        +context.getString(R.string.MC_19_6)
                        +"\n\n"
                        +context.getString(R.string.MC_19_7)
                        +"\n\n");
                break;
            case 211:
                textConv.setText(context.getString(R.string.MC_20_1)
                        +"\n\n"
                        +context.getString(R.string.SC_20_1)
                        +"\n\n"
                        +context.getString(R.string.MC_20_2)
                        +"\n\n"
                        +context.getString(R.string.MC_20_3)
                        +"\n\n"
                        +context.getString(R.string.SC_20_2)
                        +"\n\n"
                        +context.getString(R.string.SC_20_3)
                        +"\n\n"
                        +context.getString(R.string.MC_21_1)
                        +"\n\n");
                break;
            case 21:
                textConv.setText(context.getString(R.string.MC_20_1)
                        +"\n\n"
                        +context.getString(R.string.SC_20_1)
                        +"\n\n"
                        +context.getString(R.string.MC_20_2)
                        +"\n\n"
                        +context.getString(R.string.MC_20_3)
                        +"\n\n"
                        +context.getString(R.string.SC_20_2)
                        +"\n\n"
                        +context.getString(R.string.SC_20_3)
                        +"\n\n");
                break;
            case 22:
                textConv.setText(context.getString(R.string.MC_21_1)
                        +"\n\n"
                        +context.getString(R.string.SC_21_1)
                        +"\n\n"
                        +context.getString(R.string.MC_21_2)
                        +"\n\n"
                        +context.getString(R.string.SC_21_2)
                        +"\n\n"
                        +context.getString(R.string.MC_21_3)
                        +"\n\n"
                        +context.getString(R.string.SC_21_3)
                        +"\n\n"
                        +context.getString(R.string.MC_21_4)
                        +"\n\n");
                break;
            case 23:
                textConv.setText(context.getString(R.string.MC_22_1)
                        +"\n\n"
                        +context.getString(R.string.MC_22_2)
                        +"\n\n"
                        +context.getString(R.string.MC_22_3)
                        +"\n\n"
                        +context.getString(R.string.MC_22_4)
                        +"\n\n"
                        +context.getString(R.string.MC_22_5)
                        +"\n\n"
                        +context.getString(R.string.MC_22_6)
                        +"\n\n");
                break;
            case 24:
                textConv.setText(context.getString(R.string.T_23_1)
                        +"\n\n"
                        +context.getString(R.string.T_23_2)
                        +"\n\n"
                        +context.getString(R.string.SC_23_1)
                        +"\n\n"
                        +context.getString(R.string.MC_23_1)
                        +"\n\n"
                        +context.getString(R.string.SC_23_2)
                        +"\n\n");
                break;
            case 25:
                textConv.setText(context.getString(R.string.MC_24_1)
                        +"\n\n"
                        +context.getString(R.string.SC_24_1)
                        +"\n\n"
                        +context.getString(R.string.MC_24_2)
                        +"\n\n"
                        +context.getString(R.string.SC_24_2)
                        +"\n\n"
                        +context.getString(R.string.MC_24_3)
                        +"\n\n"
                        +context.getString(R.string.MC_24_4)
                        +"\n\n");
                break;
            case 26:
                textConv.setText(context.getString(R.string.SC_25_1)
                        +"\n\n"
                        +context.getString(R.string.MC_25_1)
                        +"\n\n"
                        +context.getString(R.string.SC_25_2)
                        +"\n\n"
                        +context.getString(R.string.MC_25_2)
                        +"\n\n"
                        +context.getString(R.string.MC_25_3)
                        +"\n\n"
                        +context.getString(R.string.MC_25_4)
                        +"\n\n"
                        +context.getString(R.string.SC_25_3)
                        +"\n\n"
                        +context.getString(R.string.MC_25_5)
                        +"\n\n"
                        +context.getString(R.string.SC_25_4)
                        +"\n\n"
                        +context.getString(R.string.MC_25_6)
                        +"\n\n"
                        +context.getString(R.string.SC_25_5)
                        +"\n\n"
                        +context.getString(R.string.MC_25_7)
                        +"\n\n");
                break;
            case 27:
                textConv.setText(context.getString(R.string.MC_26_1)
                        +"\n\n"
                        +context.getString(R.string.MC_26_2)
                        +"\n\n");
                break;
            case 28:
                textConv.setText(context.getString(R.string.ring_27_1)
                        +"\n\n"
                        +context.getString(R.string.SC_27_1)
                        +"\n\n"
                        +context.getString(R.string.MC_27_1)
                        +"\n\n"
                        +context.getString(R.string.SC_27_2)
                        +"\n\n"
                        +context.getString(R.string.MC_27_2)
                        +"\n\n");
                break;
            case 29:
                textConv.setText(context.getString(R.string.MC_28_1)
                        +"\n\n"
                        +context.getString(R.string.SC_28_1)
                        +"\n\n"
                        +context.getString(R.string.SC_28_2)
                        +"\n\n"
                        +context.getString(R.string.MC_28_2)
                        +"\n\n"
                        +context.getString(R.string.SC_28_3)
                        +"\n\n"
                        +context.getString(R.string.MC_28_3)
                        +"\n\n");
                break;
            case 30:
                textConv.setText(context.getString(R.string.SC_29_1)
                        +"\n\n"
                        +context.getString(R.string.MC_29_1)
                        +"\n\n");
                break;
            case 31:
                textConv.setText(context.getString(R.string.MC_30_1)
                        +"\n\n"
                        +context.getString(R.string.SC_30_1)
                        +"\n\n"
                        +context.getString(R.string.MC_30_2)
                        +"\n\n"
                        +context.getString(R.string.SC_30_2)
                        +"\n\n"
                        +context.getString(R.string.MC_30_3)
                        +"\n\n"
                        +context.getString(R.string.SC_30_3)
                        +"\n\n"
                        +context.getString(R.string.MC_30_4)
                        +"\n\n"
                        +context.getString(R.string.MC_30_5)
                        +"\n\n"
                        +context.getString(R.string.MC_30_6)
                        +"\n\n"
                        +context.getString(R.string.SC_30_4)
                        +"\n\n"
                        +context.getString(R.string.MC_30_7)
                        +"\n\n"
                        +context.getString(R.string.MC_30_8)
                        +"\n\n");
                break;
            case 32:
                textConv.setText(context.getString(R.string.SC_31_1)
                        +"\n\n"
                        +context.getString(R.string.MC_31_1)
                        +"\n\n"
                        +context.getString(R.string.SC_31_2)
                        +"\n\n");
                break;
            case 33:
                textConv.setText(context.getString(R.string.SC_32_1)
                        +"\n\n"
                        +context.getString(R.string.MC_32_1)
                        +"\n\n"
                        +context.getString(R.string.SC_32_2)
                        +"\n\n"
                        +context.getString(R.string.SC_32_3)
                        +"\n\n");
                break;
            case 34:
                textConv.setText(context.getString(R.string.MC_33_1)
                        +"\n\n"
                        +context.getString(R.string.SC_33_1)
                        +"\n\n");
                break;
            case 35:
                textConv.setText(context.getString(R.string.MC_34_1)
                        +"\n\n"
                        +context.getString(R.string.SC_34_1)
                        +"\n\n"
                        +context.getString(R.string.MC_34_2)
                        +"\n\n"
                        +context.getString(R.string.SC_34_2)
                        +"\n\n"
                        +context.getString(R.string.MC_34_3)
                        +"\n\n"
                        +context.getString(R.string.SC_34_3)
                        +"\n\n"
                        +context.getString(R.string.MC_34_4)
                        +"\n\n"
                        +context.getString(R.string.SC_34_4)
                        +"\n\n"
                        +context.getString(R.string.MC_34_5)
                        +"\n\n"
                        +context.getString(R.string.SC_34_5)
                        +"\n\n"
                        +context.getString(R.string.MC_34_6)
                        +"\n\n"
                        +context.getString(R.string.SC_34_6)
                        +"\n\n"
                        +context.getString(R.string.MC_34_7)
                        +"\n\n"
                        +context.getString(R.string.SC_34_7)
                        +"\n\n"
                        +context.getString(R.string.MC_34_8)
                        +"\n\n"
                        +context.getString(R.string.SC_34_8)
                        +"\n\n"
                        +context.getString(R.string.MC_34_9)
                        +"\n\n"
                        +context.getString(R.string.SC_34_9)
                        +"\n\n"
                        +context.getString(R.string.MC_34_10)
                        +"\n\n" );
                break;
            case 36:
                textConv.setText(context.getString(R.string.MC_35_1)
                        +"\n\n"
                        +context.getString(R.string.SC_35_1)
                        +"\n\n"
                        +context.getString(R.string.MC_35_2)
                        +"\n\n"
                        +context.getString(R.string.SC_35_2)
                        +"\n\n"
                        +context.getString(R.string.MC_35_3)
                        +"\n\n"
                        +context.getString(R.string.SC_35_3)
                        +"\n\n"
                        +context.getString(R.string.MC_35_4)
                        +"\n\n"
                        +context.getString(R.string.SC_35_4)
                        +"\n\n"
                        +context.getString(R.string.MC_35_5)
                        +"\n\n"
                        +context.getString(R.string.MC_35_6)
                        +"\n\n"
                        +context.getString(R.string.SC_35_5)
                        +"\n\n"
                        +context.getString(R.string.MC_35_7)
                        +"\n\n"
                        +context.getString(R.string.SC_35_6)
                        +"\n\n");
                break;
            case 37:
                textConv.setText(context.getString(R.string.MC_36_1)
                        +"\n\n"
                        +context.getString(R.string.MC_36_2)
                        +"\n\n");
                break;
            case 38:
                textConv.setText(context.getString(R.string.MC_37_1)
                        +"\n\n"
                        +context.getString(R.string.SC_37_1)
                        +"\n\n"
                        +context.getString(R.string.SC_37_2)
                        +"\n\n"
                        +context.getString(R.string.MC_37_2)
                        +"\n\n"
                        +context.getString(R.string.SC_37_3)
                        +"\n\n"
                        +context.getString(R.string.SC_37_4)
                        +"\n\n"
                        +context.getString(R.string.SC_37_5)
                        +"\n\n"
                        +context.getString(R.string.SC_37_6)
                        +"\n\n"
                        +context.getString(R.string.MC_37_3)
                        +"\n\n"
                        +context.getString(R.string.MC_37_4)
                        +"\n\n"
                        +context.getString(R.string.SC_37_7)
                        +"\n\n"
                        +context.getString(R.string.SC_37_8)
                        +"\n\n"
                        +context.getString(R.string.SC_37_9)
                        +"\n\n"
                        +context.getString(R.string.SC_37_10)
                        +"\n\n"
                        +context.getString(R.string.SC_37_11)
                        +"\n\n"
                        +context.getString(R.string.SC_37_12)
                        +"\n\n"
                        +context.getString(R.string.SC_37_13)
                        +"\n\n"
                        +context.getString(R.string.SC_37_14)
                        +"\n\n"
                        +context.getString(R.string.SC_37_15)
                        +"\n\n"
                        +context.getString(R.string.SC_37_16)
                        +"\n\n"
                        +context.getString(R.string.SC_37_17)
                        +"\n\n"
                        +context.getString(R.string.SC_37_18)
                        +"\n\n"
                        +context.getString(R.string.SC_37_19)
                        +"\n\n"
                        +context.getString(R.string.SC_37_20)
                        +"\n\n"
                        +context.getString(R.string.MC_37_5)
                        +"\n\n"
                        +context.getString(R.string.SC_37_21)
                        +"\n\n"
                        +context.getString(R.string.MC_37_6)
                        +"\n\n" );
                break;
            case 39:
                textConv.setText(context.getString(R.string.MC_38_1)
                        +"\n\n"
                        +context.getString(R.string.SC_38_1)
                        +"\n\n"
                        +context.getString(R.string.MC_38_2)
                        +"\n\n"
                        +context.getString(R.string.SC_38_2)
                        +"\n\n"
                        +context.getString(R.string.T_38_1)
                        +"\n\n"
                        +context.getString(R.string.SC_38_3)
                        +"\n\n"
                        +context.getString(R.string.SC_38_4)
                        +"\n\n"
                        +context.getString(R.string.MC_38_3)
                        +"\n\n"
                        +context.getString(R.string.SC_38_5)
                        +"\n\n"
                        +context.getString(R.string.MC_38_4)
                        +"\n\n"
                        +context.getString(R.string.SC_38_6)
                        +"\n\n"
                        +context.getString(R.string.MC_38_5)
                        +"\n\n"
                        +context.getString(R.string.SC_38_7)
                        +"\n\n"
                        +context.getString(R.string.MC_38_6)
                        +"\n\n"
                        +context.getString(R.string.SC_38_8)
                        +"\n\n"
                        +context.getString(R.string.SC_38_9)
                        +"\n\n"
                        +context.getString(R.string.MC_38_7)
                        +"\n\n"
                        +context.getString(R.string.SC_38_10)
                        +"\n\n"
                        +context.getString(R.string.MC_38_8)
                        +"\n\n"
                        +context.getString(R.string.SC_38_11)
                        +"\n\n"
                        +context.getString(R.string.SC_38_12)
                        +"\n\n"
                        +context.getString(R.string.MC_38_9)
                        +"\n\n" );
                break;
            case 40:
                textConv.setText(context.getString(R.string.SC_39_1)
                        +"\n\n"
                        +context.getString(R.string.MC_39_1)
                        +"\n\n"
                        +context.getString(R.string.SC_39_2)
                        +"\n\n"
                        +context.getString(R.string.MC_39_2)
                        +"\n\n"
                        +context.getString(R.string.SC_39_3)
                        +"\n\n"
                        +context.getString(R.string.MC_39_3)
                        +"\n\n"
                        +context.getString(R.string.SC_39_4)
                        +"\n\n"
                        +context.getString(R.string.SC_39_5)
                        +"\n\n"
                        +context.getString(R.string.MC_39_4)
                        +"\n\n"
                        +context.getString(R.string.SC_39_6)
                        +"\n\n"
                        +context.getString(R.string.MC_39_5)
                        +"\n\n"
                        +context.getString(R.string.SC_39_7)
                        +"\n\n"
                        +context.getString(R.string.MC_39_6)
                        +"\n\n"
                        +context.getString(R.string.SC_39_8_1)
                        +"\n\n"
                        +context.getString(R.string.SC_39_8_2)
                        +"\n\n"
                        +context.getString(R.string.SC_39_8_3)
                        +"\n\n"
                        +context.getString(R.string.SC_39_9)
                        +"\n\n"
                        +context.getString(R.string.MC_39_7)
                        +"\n\n"
                        +context.getString(R.string.SC_39_10)
                        +"\n\n"
                        +context.getString(R.string.MC_39_8)
                        +"\n\n"
                        +context.getString(R.string.SC_39_11)
                        +"\n\n"
                        +context.getString(R.string.MC_39_9)
                        +"\n\n"
                        +context.getString(R.string.SC_39_12)
                        +"\n\n"
                        +context.getString(R.string.MC_39_10)
                        +"\n\n"
                        +context.getString(R.string.MC_39_11)
                        +"\n\n"
                        +context.getString(R.string.SC_39_13)
                        +"\n\n"
                        +context.getString(R.string.MC_39_12)
                        +"\n\n"
                        +context.getString(R.string.SC_39_14)
                        +"\n\n"
                        +context.getString(R.string.MC_39_13)
                        +"\n\n"
                        +context.getString(R.string.SC_39_15)
                        +"\n\n"
                        +context.getString(R.string.T_39_1)
                        +"\n\n"
                        +context.getString(R.string.SC_39_16)
                        +"\n\n"
                        +context.getString(R.string.MC_39_14)
                        +"\n\n"
                        +context.getString(R.string.SC_39_17)
                        +"\n\n"
                        +context.getString(R.string.MC_39_15)
                        +"\n\n"
                        +context.getString(R.string.SC_39_18)
                        +"\n\n"
                        +context.getString(R.string.MC_39_16)
                        +"\n\n"
                        +context.getString(R.string.SC_39_19)
                        +"\n\n"
                        +context.getString(R.string.MC_39_17)
                        +"\n\n"
                        +context.getString(R.string.SC_39_20)
                        +"\n\n"
                        +context.getString(R.string.SC_39_21)
                        +"\n\n"
                        +context.getString(R.string.MC_39_18)
                        +"\n\n"
                        +context.getString(R.string.SC_39_22)
                        +"\n\n"
                        +context.getString(R.string.MC_39_19)
                        +"\n\n"
                        +context.getString(R.string.SC_39_23)
                        +"\n\n"
                        +context.getString(R.string.MC_39_20)
                        +"\n\n"
                        +context.getString(R.string.SC_39_24)
                        +"\n\n"
                        +context.getString(R.string.MC_39_21)
                        +"\n\n" );
                break;
            case 41:
                textConv.setText(context.getString(R.string.SC_40_1_1)
                        +"\n\n"
                        +context.getString(R.string.SC_40_1_2)
                        +"\n\n"
                        +context.getString(R.string.SC_40_1_3)
                        +"\n\n"
                        +context.getString(R.string.SC_40_2)
                        +"\n\n"
                        +context.getString(R.string.SC_40_3)
                        +"\n\n"
                        +context.getString(R.string.MC_40_1)
                        +"\n\n"
                        +context.getString(R.string.MC_40_2)
                        +"\n\n"
                        +context.getString(R.string.SC_40_4)
                        +"\n\n"
                        +context.getString(R.string.MC_40_3)
                        +"\n\n"
                        +context.getString(R.string.SC_40_5)
                        +"\n\n"
                        +context.getString(R.string.MC_40_4)
                        +"\n\n"
                        +context.getString(R.string.SC_40_6)
                        +"\n\n"
                        +context.getString(R.string.MC_40_5)
                        +"\n\n"
                        +context.getString(R.string.SC_40_7)
                        +"\n\n"
                        +context.getString(R.string.MC_40_6)
                        +"\n\n"
                        +context.getString(R.string.SC_40_8)
                        +"\n\n"
                        +context.getString(R.string.MC_40_7)
                        +"\n\n"
                        +context.getString(R.string.SC_40_9)
                        +"\n\n"
                        +context.getString(R.string.MC_40_8)
                        +"\n\n"
                        +context.getString(R.string.SC_40_10)
                        +"\n\n" );
                break;
            case 42:
                textConv.setText(context.getString(R.string.MC_41_1)
                        +"\n\n"
                        +context.getString(R.string.MC_41_2)
                        +"\n\n"
                        +context.getString(R.string.MC_41_3)
                        +"\n\n"
                        +context.getString(R.string.MC_41_4)
                        +"\n\n");
                break;
            case 43:
                textConv.setText(context.getString(R.string.SC_42_1)
                        +"\n\n"
                        +context.getString(R.string.MC_42_1)
                        +"\n\n"
                        +context.getString(R.string.MC_42_2)
                        +"\n\n"
                        +context.getString(R.string.SC_42_2)
                        +"\n\n"
                        +context.getString(R.string.MC_42_3)
                        +"\n\n"
                        +context.getString(R.string.SC_42_3)
                        +"\n\n"
                        +context.getString(R.string.MC_42_4)
                        +"\n\n"
                        +context.getString(R.string.MC_42_5)
                        +"\n\n"
                        +context.getString(R.string.SC_42_4)
                        +"\n\n"
                        +context.getString(R.string.MC_42_6)
                        +"\n\n"
                        +context.getString(R.string.MC_42_7)
                        +"\n\n"
                        +context.getString(R.string.SC_42_5)
                        +"\n\n"
                        +context.getString(R.string.MC_42_8)
                        +"\n\n"
                        +context.getString(R.string.SC_42_6)
                        +"\n\n"
                        +context.getString(R.string.MC_42_9)
                        +"\n\n"
                        +context.getString(R.string.SC_42_7)
                        +"\n\n" );
                break;
            case 44:
                textConv.setText(context.getString(R.string.MC_43_1)
                        +"\n\n"
                        +context.getString(R.string.SC_43_1)
                        +"\n\n"
                        +context.getString(R.string.SC_43_2)
                        +"\n\n"
                        +context.getString(R.string.MC_43_2)
                        +"\n\n"
                        +context.getString(R.string.SC_43_3)
                        +"\n\n"
                        +context.getString(R.string.SC_43_4)
                        +"\n\n"
                        +context.getString(R.string.SC_43_5)
                        +"\n\n"
                        +context.getString(R.string.MC_43_3)
                        +"\n\n"
                        +context.getString(R.string.SC_43_6)
                        +"\n\n"
                        +context.getString(R.string.MC_43_4)
                        +"\n\n"
                        +context.getString(R.string.SC_43_7)
                        +"\n\n"
                        +context.getString(R.string.MC_43_5)
                        +"\n\n");
                break;

        }
    }

//    @Override
//    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//    }

//    @Override
//    public void afterTextChanged(Editable editable) {
//        edited = editable.toString();
////        switch (StoryView.bg_changer){
////            case 0:
////                if (nakayama.equals(edited)){
////                    editText.setVisibility(INVISIBLE);
////                    editText.setDateText("");
////                    editText.setHint("");
//////                    textView.setDateText(R.string.first_5);
////                    textView.setVisibility(VISIBLE);
////                    isStory = true;
////                }
////                break;
////            case 7:
////                if (nihonsei.equals(edited)){
////                    editText.setVisibility(INVISIBLE);
////                    editText.setDateText("");
//////                    textView.setDateText(R.string.pra_banana_1);
////                    textView.setVisibility(VISIBLE);
////                    isStory = false;
////                    touch_judgeS = false;
////                }
////                break;
////        }
//        try{
//            switch (Integer.valueOf(edited)){
//                case 5:
//                    editText.setVisibility(INVISIBLE);
//                    editText.setDateText("");
////                    chapter = 5;
//                    ListBody.setIsSaveToList(true);
//                    FunctionView.setListView(true);
//                    break;
//                case 10:
//                    editText.setVisibility(INVISIBLE);
//                    editText.setDateText("");
////                    chapter = 10;
//                    ListBody.setIsSaveToList(false);
//                    FunctionView.setListView(true);
//                    break;
//                case 15:
//                    editText.setVisibility(INVISIBLE);
//                    editText.setDateText("");
//                    chapter = 15;
//                    break;
//                case 20:
//                    editText.setVisibility(INVISIBLE);
//                    editText.setDateText("");
//                    chapter = 20;
//                    break;
//                case 25:
//                    editText.setVisibility(INVISIBLE);
//                    editText.setDateText("");
//                    chapter = 25;
//                    break;
//                case 30:
//                    editText.setVisibility(INVISIBLE);
//                    editText.setDateText("");
//                    chapter = 30;
//                    break;
//                case 35:
//                    editText.setVisibility(INVISIBLE);
//                    editText.setDateText("");
//                    chapter = 35;
//                    break;
//                case 40:
//                    editText.setVisibility(INVISIBLE);
//                    editText.setDateText("");
//                    chapter = 40;
//                    break;
//            }
//        }catch (Exception e){
//
//        }
//        isViewAnimation = false;
//    }
}
