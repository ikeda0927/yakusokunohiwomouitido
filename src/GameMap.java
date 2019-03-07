package com.kohei.ikegon.yakusokunohiwomouitido;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.getChapter;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.getCounter;
import static com.kohei.ikegon.yakusokunohiwomouitido.FunctionView.getEnding;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getChangeBG;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getH_unit;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getIsStoryMusic;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getMusicID;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getSub23CharacterIsShow;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.playMusic;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setIsStoryMusic;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setMainCharacterIsHide;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setMusicID;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.stopMusic;

/**
 * Created by ikego on 2018/03/31.
 */

public class GameMap {
    //画像
    private static Bitmap bgbmp;
    private static Bitmap save_bmp;
    //画面の幅と高さ
    private static int width;
    private static int height;
    private static float bgW_unit;
    private static float bgH_unit;
    //BGのサイズ設定
    private static float left;
    private static float right;
    private static float top;
    private static float bottom;
    //bgのrect
    private static RectF rect;
    private static Rect srcRect;
    //場面ごとのrect
    private static Rect scene_A;
    private static Rect scene_B;
    private static Rect scene_C;
    private static Rect scene_D;
    private static Rect scene_E;
    private static Rect scene_F;
    private static Rect scene_G;
    private static Rect scene_H;
    private static Rect scene_I;
    private static Rect scene_J;
    private static Rect scene_K;
    private static Rect scene_L;

    //savebmpのサイズ設定
    private static float savebmp_left;
    private static float savebmp_right;
    private static float savebmp_top;
    private static float savebmp_bottom;
    private final RectF save_rect;
    private Rect src_save_rect;
    private static int pre_changeBG = 0;

    private final Paint paint = new Paint();

    //MapData用の障害物データ
    private final int[][] obstacle1 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{1,0},{1,1},{1,7},{1,12},{2,0},{2,1},{2,12},{3,0},{3,1},{3,12},{4,0},{4,1},{4,3},{4,7},{4,8},{4,9},{4,12},{5,0},{5,1},{5,7},{5,8},{5,12},{6,0},{6,1},{6,7},{6,8},{6,9},{6,12},{7,0},{7,1},{7,7},{7,8},{7,9},{7,12}};//カフェ
    private final int[][] obstacle1_37_22 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{1,0},{1,1},{1,12},{2,0},{2,1},{2,12},{3,0},{3,1},{3,12},{4,0},{4,1},{4,3},{4,7},{4,8},{4,9},{4,12},{5,0},{5,1},{5,7},{5,8},{5,12},{6,0},{6,1},{6,7},{6,8},{6,9},{6,12},{7,0},{7,1},{7,7},{7,8},{7,9},{7,12}};//カフェ
    private final int[][] obstacle1_38_39 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{1,0},{1,1},{1,12},{2,0},{2,1},{2,12},{3,0},{3,1},{3,12},{4,0},{4,1},{4,3},{4,7},{4,8},{4,9},{4,12},{5,0},{5,1},{5,7},{5,8},{5,9},{5,12},{6,0},{6,1},{6,7},{6,8},{6,9},{6,12},{7,0},{7,1},{7,7},{7,8},{7,9},{7,12}};//カフェ
    private final int[][] obstacle1_6_12 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{1,0},{1,1},{1,7},{1,12},{2,0},{2,1},{2,12},{3,0},{3,1},{3,12},{4,0},{4,1},{4,3},{4,4},{4,7},{4,8},{4,9},{4,12},{5,0},{5,1},{5,7},{5,8},{5,12},{6,0},{6,1},{6,7},{6,8},{6,9},{6,12},{7,0},{7,1},{7,7},{7,8},{7,9},{7,12}};//カフェ(chapter6)
    private final int[][] obstacle2 = {{0,2},{0,3},{0,4},{0,5},{1,1},{1,2},{1,3},{1,4},{1,5},{2,1},{2,2},{2,3},{2,4},{3,2},{3,3},{3,4},{3,5},{7,3}};//自宅
    private final int[][] obstacle2_1 = {{0,2},{0,3},{0,4},{0,5},{1,1},{1,2},{1,3},{1,4},{1,5},{2,1},{2,2},{2,3},{2,4},{3,2},{3,3},{3,4},{3,5},{4,0},{5,0},{7,3}};//自宅 chapter 1
    private final int[][] obstacle2_28 = {{0,2},{0,3},{0,4},{0,5},{1,1},{1,2},{1,3},{1,4},{1,5},{2,1},{2,2},{2,3},{2,4},{3,1},{3,2},{3,3},{3,4},{3,5},{4,1},{7,3}};//自宅 chapter 28
    private final int[][] obstacle2_37_22_38_39 = {{0,2},{0,3},{0,4},{0,5},{1,1},{1,2},{1,3},{1,4},{1,5},{2,1},{2,2},{2,3},{2,4},{2,5},{3,2},{3,3},{3,4},{3,5}};//自宅
    private final int[][] obstacle3 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,12},{1,0},{1,1},{1,2},{1,3},{1,4},{1,12},{2,0},{2,12},{3,0},{3,11},{3,12},{4,10},{4,11},{4,12},{5,10},{5,11},{5,12},{6,4},{6,5},{6,6},{6,7},{6,8},{6,9},{6,10},{6,11},{6,12},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//もり
    private final int[][] obstacle3_16_1 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,12},{1,0},{1,1},{1,2},{1,3},{1,4},{1,12},{2,0},{2,12},{3,0},{3,6},{3,11},{3,12},{4,10},{4,11},{4,12},{5,10},{5,11},{5,12},{6,4},{6,5},{6,6},{6,7},{6,8},{6,9},{6,10},{6,11},{6,12},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//もり
    private final int[][] obstacle3_16_5 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,12},{1,0},{1,1},{1,2},{1,3},{1,4},{1,12},{2,0},{2,12},{3,0},{3,6},{3,11},{3,12},{4,5},{4,10},{4,11},{4,12},{5,10},{5,11},{5,12},{6,4},{6,5},{6,6},{6,7},{6,8},{6,9},{6,10},{6,11},{6,12},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//もり
    private final int[][] obstacle3_28 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,12},{1,0},{1,1},{1,2},{1,3},{1,4},{1,12},{2,0},{2,12},{3,0},{3,11},{3,12},{4,10},{4,11},{4,12},{5,7},{5,10},{5,11},{5,12},{6,4},{6,5},{6,6},{6,7},{6,8},{6,9},{6,10},{6,11},{6,12},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//もり
    private final int[][] obstacle3_43 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,12},{1,0},{1,1},{1,2},{1,3},{1,4},{1,12},{2,0},{2,12},{3,0},{3,11},{3,12},{4,10},{4,11},{4,12},{5,7},{5,10},{5,11},{5,12},{6,4},{6,5},{6,6},{6,7},{6,8},{6,9},{6,10},{6,11},{6,12},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//もり
    private final int[][] obstacle4 = {{0,0},{0,1},{0,2},{0,3},{0,4},{1,0},{1,1},{1,2},{1,3},{1,4},{1,7},{1,8},{2,0},{2,1},{2,2},{2,3},{2,4},{3,0},{3,1},{3,2},{3,3},{4,0},{4,1},{4,2},{4,3},{5,0},{5,1},{5,2},{5,9},{6,9},{6,10},{7,2}};//池
    private final int[][] obstacle4_1 = {{0,0},{0,1},{0,2},{0,3},{0,4},{1,0},{1,1},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},{2,3},{2,4},{3,0},{3,1},{3,2},{3,3},{4,0},{4,1},{4,2},{4,3},{5,0},{5,1},{5,2},{5,9},{6,9},{6,10},{7,2}};//池
    private final int[][] obstacle4_31_ = {{0,0},{0,1},{0,2},{0,3},{0,4},{1,0},{1,1},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},{2,3},{2,4},{3,0},{3,1},{3,2},{3,3},{4,0},{4,1},{4,2},{4,3},{5,0},{5,1},{5,2},{5,9},{6,9},{6,10}};//池
    private final int[][] obstacle4_32 = {{0,0},{0,1},{0,2},{0,3},{0,4},{1,0},{1,1},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},{2,3},{2,4},{2,5},{3,0},{3,1},{3,2},{3,3},{4,0},{4,1},{4,2},{4,3},{5,0},{5,1},{5,2},{5,9},{6,9},{6,10}};//池
    private final int[][] obstacle4_32_33 = {{0,0},{0,1},{0,2},{0,3},{0,4},{1,0},{1,1},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},{2,3},{2,4},{3,0},{3,1},{3,2},{3,3},{3,5},{4,0},{4,1},{4,2},{4,3},{5,0},{5,1},{5,2},{5,9},{6,9},{6,10}};//池
    private final int[][] obstacle4_37_44_1 = {{0,0},{0,1},{0,2},{0,3},{0,4},{1,0},{1,1},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},{2,3},{2,4},{3,0},{3,1},{3,2},{3,3},{4,0},{4,1},{4,2},{4,3},{5,0},{5,1},{5,2},{5,9},{6,9},{6,10}};//池
    private final int[][] obstacle4_37_2 = {{0,0},{0,1},{0,2},{0,3},{0,4},{1,0},{1,1},{1,2},{1,3},{1,4},{1,6},{2,0},{2,1},{2,2},{2,3},{2,4},{3,0},{3,1},{3,2},{3,3},{4,0},{4,1},{4,2},{4,3},{4,5},{5,0},{5,1},{5,2},{5,9},{6,2},{6,9},{6,10}};//池
    private final int[][] obstacle4_37_3 = {{0,0},{0,1},{0,2},{0,3},{0,4},{1,0},{1,1},{1,2},{1,3},{1,4},{1,6},{2,0},{2,1},{2,2},{2,3},{2,4},{3,0},{3,1},{3,2},{3,3},{4,0},{4,1},{4,2},{4,3},{4,5},{5,0},{5,1},{5,2},{5,9},{6,9},{6,10}};//池
    private final int[][] obstacle5 = {{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{2,1},{2,2},{2,3},{2,12},{3,0},{3,1},{3,2},{3,12},{4,0},{4,1},{4,2},{4,3},{4,12},{5,1},{5,2},{5,3},{5,12},{6,12},{7,9},{7,12}};//しー家まえ
    private final int[][] obstacle5_37_22 = {{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{2,1},{2,2},{2,3},{2,12},{3,0},{3,1},{3,2},{3,3},{3,12},{4,0},{4,1},{4,2},{4,3},{4,12},{5,1},{5,2},{5,3},{5,12},{6,12},{7,8},{7,9},{7,12}};//しー家まえ
    private final int[][] obstacle5_38_39 = {{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{2,1},{2,2},{2,3},{2,12},{3,0},{3,1},{3,2},{3,3},{3,12},{4,0},{4,1},{4,2},{4,3},{4,12},{5,1},{5,2},{5,3},{5,12},{6,12},{7,12}};//しー家まえ
    private final int[][] obstacle6 = {{0,12},{1,8},{1,12},{2,12},{3,2},{3,12},{4,12},{5,12},{6,12},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//公衆電話
    private final int[][] obstacle6_38_39 = {{0,12},{1,8},{1,12},{2,12},{3,12},{4,12},{5,12},{6,12},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//公衆電話
    private final int[][] obstacle7 = {{0,3},{0,4},{0,12},{1,1},{1,3},{1,4},{1,12},{2,1},{2,3},{2,4},{2,5},{2,12},{3,1},{3,3},{3,12},{4,2},{4,3},{4,4},{4,5},{4,6},{4,12},{5,12},{6,12},{7,0},{7,1},{7,2},{7,3},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//公民館
    private final int[][] obstacle7_31 = {{0,3},{0,4},{0,12},{1,1},{1,3},{1,4},{1,12},{2,1},{2,3},{2,4},{2,5},{2,12},{3,1},{3,3},{3,12},{4,2},{4,3},{4,4},{4,5},{4,6},{4,12},{5,8},{5,12},{6,12},{7,0},{7,1},{7,2},{7,3},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//公民館
    private final int[][] obstacle7_37 = {{0,3},{0,4},{0,12},{1,1},{1,3},{1,4},{1,5},{1,6},{1,12},{2,1},{2,3},{2,4},{2,5},{2,6},{2,12},{3,1},{3,3},{3,12},{4,2},{4,3},{4,4},{4,5},{4,6},{4,12},{5,12},{6,12},{7,0},{7,1},{7,2},{7,3},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//公民館
    private final int[][] obstacle8 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{1,0},{1,1},{1,2},{1,5},{1,6},{1,11},{1,12},{2,0},{2,1},{2,11},{2,12},{3,0},{3,1},{3,5},{3,6},{3,12},{4,0},{4,1},{4,5},{4,6},{4,12},{5,0},{5,1},{5,5},{5,6},{5,7},{5,8},{5,11},{6,0},{6,1},{6,5},{6,6},{6,8},{6,11},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//自宅
    private final int[][] obstacle8_8 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{1,0},{1,1},{1,2},{1,5},{1,6},{1,11},{1,12},{2,0},{2,1},{2,11},{2,12},{3,0},{3,1},{3,5},{3,6},{3,11},{3,12},{4,0},{4,1},{4,5},{4,6},{4,9},{4,10},{4,11},{4,12},{5,0},{5,1},{5,5},{5,6},{5,7},{5,8},{5,11},{6,0},{6,1},{6,5},{6,6},{6,8},{6,11},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//自宅
    private final int[][] obstacle8_30 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{1,0},{1,1},{1,2},{1,5},{1,6},{1,9},{1,10},{1,11},{1,12},{2,0},{2,1},{2,9},{2,10},{2,11},{2,12},{3,0},{3,1},{3,5},{3,6},{3,9},{3,10},{3,11},{3,12},{4,0},{4,1},{4,5},{4,6},{4,9},{4,10},{4,11},{4,12},{5,0},{5,1},{5,5},{5,6},{5,7},{5,8},{5,9},{5,10},{5,11},{6,0},{6,1},{6,5},{6,6},{6,8},{6,9},{6,10},{6,11},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//自宅
    private final int[][] obstacle9 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{1,0},{1,1},{1,5},{1,6},{1,8},{1,11},{1,12},{2,0},{2,1},{2,4},{2,5},{2,6},{2,7},{2,8},{2,11},{2,12},{3,0},{3,1},{3,4},{3,5},{3,6},{3,12},{4,0},{4,1},{4,5},{4,6},{4,12},{5,0},{5,1},{5,11},{5,12},{6,0},{6,1},{6,5},{6,6},{6,11},{6,12},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//しー家
    private final int[][] obstacle10 = {{0,0},{0,1},{0,2},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{1,0},{1,1},{2,0},{2,1},{3,0},{3,1},{4,0},{4,1},{5,0},{5,1},{6,0},{6,1},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11}};//しー部屋
    private final int[][] obstacle11 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{1,0},{1,1},{1,7},{1,8},{1,11},{1,12},{2,0},{2,1},{2,3},{2,5},{2,7},{2,8},{2,9},{2,10},{2,11},{2,12},{3,0},{3,1},{3,12},{4,0},{4,1},{4,12},{5,0},{5,1},{5,3},{5,5},{5,7},{5,11},{5,12},{6,0},{6,1},{6,7},{6,11},{6,12},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//カフェ
    private final int[][] obstacle12 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{1,0},{1,1},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7},{1,8},{1,9},{1,10},{1,11},{1,12},{2,0},{2,1},{2,2},{2,3},{2,4},{2,5},{2,6},{2,7},{2,8},{2,9},{2,10},{2,11},{2,12},{3,0},{3,1},{3,8},{3,9},{3,10},{3,11},{3,12},{4,0},{4,1},{4,12},{5,0},{5,1},{5,4},{5,12},{6,0},{6,1},{6,6},{6,7},{6,12},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,12}};//池の奧
    private final int[][] obstacle13 = {{0,0},{0,1},{0,2},{0,3},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{1,0},{1,1},{1,2},{1,11},{1,12},{2,0},{2,1},{2,12},{3,0},{3,1},{3,12},{4,0},{4,1},{4,12},{5,0},{5,1},{5,11},{5,12},{6,0},{6,1},{6,2},{6,8},{6,9},{6,10},{6,11},{6,12},{7,0},{7,1},{7,2},{7,3},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//森の奧
    private final int[][] obstacle13_21_4 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{1,0},{1,1},{1,2},{1,3},{1,4},{1,11},{1,12},{2,0},{2,1},{2,2},{2,3},{2,4},{2,12},{3,0},{3,1},{3,2},{3,3},{3,4},{3,12},{4,0},{4,1},{4,2},{4,3},{4,4},{4,12},{5,0},{5,1},{5,2},{5,3},{5,4},{5,11},{5,12},{6,0},{6,1},{6,2},{6,3},{6,4},{6,8},{6,9},{6,10},{6,11},{6,12},{7,0},{7,1},{7,2},{7,3},{7,4},{7,7},{7,8},{7,9},{7,10},{7,11},{7,12}};//森の奧
    private final int[][] obstacle14 = {{0,0},{0,1},{0,9},{0,10},{0,11},{0,12},{1,0},{1,11},{1,12},{2,0},{2,11},{2,12},{3,0},{3,12},{4,0},{4,12},{5,0},{5,12},{6,0},{6,12},{7,0},{7,1},{7,2},{7,3},{7,12}};//空き地
    private final int[][] obstacle14_31_ = {{0,0},{0,1},{0,9},{0,10},{0,11},{0,12},{1,0},{1,11},{1,12},{2,0},{2,10},{2,11},{2,12},{3,0},{3,10},{3,12},{4,0},{4,12},{5,0},{5,12},{6,0},{6,2},{6,12},{7,0},{7,1},{7,2},{7,3},{7,12}};//空き地
    private final int[][] obstacle15 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{1,0},{1,1},{1,2},{1,3},{1,4},{1,9},{1,10},{1,11},{2,0},{2,1},{2,2},{2,9},{2,10},{2,11},{3,0},{3,1},{3,2},{3,4},{3,5},{3,10},{3,11},{4,0},{4,1},{4,2},{4,4},{4,5},{4,10},{4,11},{5,0},{5,1},{5,2},{5,9},{5,10},{5,11},{6,0},{6,1},{6,2},{6,9},{6,10},{6,11},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11}};//池の近くにある小屋
    private final int[][] obstacle15_19 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{1,0},{1,1},{1,2},{1,3},{1,4},{1,9},{1,10},{1,11},{2,0},{2,1},{2,2},{2,9},{2,10},{2,11},{3,0},{3,1},{3,2},{3,4},{3,5},{3,9},{3,10},{3,11},{4,0},{4,1},{4,2},{4,4},{4,5},{4,9},{4,10},{4,11},{5,0},{5,1},{5,2},{5,9},{5,10},{5,11},{6,0},{6,1},{6,2},{6,9},{6,10},{6,11},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11}};//池の近くにある小屋
    private final int[][] obstacle15_32_33 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{1,0},{1,1},{1,2},{1,3},{1,4},{1,6},{1,9},{1,10},{1,11},{2,0},{2,1},{2,2},{2,9},{2,10},{2,11},{3,0},{3,1},{3,2},{3,4},{3,5},{3,10},{3,11},{4,0},{4,1},{4,2},{4,4},{4,5},{4,10},{4,11},{5,0},{5,1},{5,2},{5,9},{5,10},{5,11},{6,0},{6,1},{6,2},{6,9},{6,10},{6,11},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11}};//池の近くにある小屋
    private final int[][] obstacle15_23_3 = {{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{1,0},{1,1},{1,2},{1,3},{1,4},{1,6},{1,9},{1,10},{1,11},{2,0},{2,1},{2,2},{2,9},{2,10},{2,11},{3,0},{3,1},{3,2},{3,4},{3,5},{3,9},{3,10},{3,11},{4,0},{4,1},{4,2},{4,4},{4,5},{4,9},{4,10},{4,11},{5,0},{5,1},{5,2},{5,9},{5,10},{5,11},{6,0},{6,1},{6,2},{6,9},{6,10},{6,11},{7,0},{7,1},{7,2},{7,3},{7,4},{7,5},{7,6},{7,7},{7,8},{7,9},{7,10},{7,11}};//池の近くにある小屋
    private final int[][] obstacle_kaisou ={{0,0}};
    //MapData
    private static MapData mapData_jitaku_;
    private static MapData mapData_jitaku_1;
    private static MapData mapData_jitaku_28;
    private static MapData mapData_jitaku_37_22_38_39;
    private static MapData mapData_cafe;
    private static MapData mapData_cafe_6_12;
    private static MapData mapData_cafe_37_22;
    private static MapData mapData_cafe_38_39;
    private static MapData mapData_forest;
    private static MapData mapData_forest_16_1;
    private static MapData mapData_forest_16_5;
    private static MapData mapData_forest_28;
    private static MapData mapData_forest_43;
    private static MapData mapData_ike;
    private static MapData mapData_ike_31_;
    private static MapData mapData_ike_32_33;
    private static MapData mapData_ike_32;
    private static MapData mapData_ike_37_44_1;
    private static MapData mapData_ike_37_2;
    private static MapData mapData_ike_37_3;
    private static MapData mapData_ike_1;
    private static MapData mapData_siiiemae;
    private static MapData mapData_siiiemae_37_22;
    private static MapData mapData_siiiemae_38_39;
    private static MapData mapData_denwa;
    private static MapData mapData_denwa_38;
    private static MapData mapData_kominkan;
    private static MapData mapData_kominkan_31;
    private static MapData mapData_kominkan_37;
    private static MapData mapData_jishitu;
    private static MapData mapData_jishitu_8;
    private static MapData mapData_jishitu_30;
    private static MapData mapData_siiie;
    private static MapData mapData_siiheya;
    private static MapData mapData_cafenaka;
    private static MapData mapData_ike_depth;
    private static MapData mapData_forest_depth;
    private static MapData mapData_forest_depth_13_21_4;
    private static MapData mapData_akiti;
    private static MapData mapData_akiti_31_;
    private static MapData mapData_koya;
    private static MapData mapData_koya_19;
    private static MapData mapData_koya_23_3;
    private static MapData mapData_koya_32_33;
    private static MapData mapData_kaisou;

    public GameMap(Bitmap bgbmp, Bitmap save_bmp, int width, int height){
        this.bgbmp = bgbmp;
        this.width = width;
        this.height = height;
        this.save_bmp = save_bmp;

        bgW_unit = bgbmp.getWidth()/6;
        bgH_unit = bgbmp.getHeight()/2;

        left = 0;
        top = 0;
        right = width;
        bottom = getH_unit() * 12;

        savebmp_left = 0;
        savebmp_top = getH_unit() * 12;
        savebmp_right = (float)width;
        savebmp_bottom = (float)height;
        save_rect = new RectF(savebmp_left,savebmp_top,savebmp_right,savebmp_bottom);
        src_save_rect = new Rect((int)left,(int)top,save_bmp.getWidth(),save_bmp.getHeight());

        rect = new RectF(left,top,width,bottom);

        srcRect = new Rect((int)(bgW_unit*3),(int)(bgH_unit*1),(int)(bgW_unit*4),(int)(bgH_unit*2));

        scene_A = new Rect((int)(bgW_unit*0)+1,(int)(bgH_unit*0),(int)(bgW_unit*1),(int)(bgH_unit*1));
        scene_B = new Rect((int)(bgW_unit*1)+1,(int)(bgH_unit*0),(int)(bgW_unit*2),(int)(bgH_unit*1));
        scene_C = new Rect((int)(bgW_unit*2)+1,(int)(bgH_unit*0),(int)(bgW_unit*3),(int)(bgH_unit*1));
        scene_D = new Rect((int)(bgW_unit*3)+1,(int)(bgH_unit*0),(int)(bgW_unit*4),(int)(bgH_unit*1));
        scene_E = new Rect((int)(bgW_unit*4)+1,(int)(bgH_unit*0),(int)(bgW_unit*5),(int)(bgH_unit*1));
        scene_F = new Rect((int)(bgW_unit*5)+1,(int)(bgH_unit*0),(int)(bgW_unit*6),(int)(bgH_unit*1));

        scene_G = new Rect((int)(bgW_unit*0)+1,(int)(bgH_unit*1),(int)(bgW_unit*1),(int)(bgH_unit*2));
        scene_H = new Rect((int)(bgW_unit*1)+1,(int)(bgH_unit*1),(int)(bgW_unit*2),(int)(bgH_unit*2));
        scene_I = new Rect((int)(bgW_unit*2)+1,(int)(bgH_unit*1),(int)(bgW_unit*3),(int)(bgH_unit*2));
        scene_J = new Rect((int)(bgW_unit*3)+1,(int)(bgH_unit*1),(int)(bgW_unit*4),(int)(bgH_unit*2));
        scene_K = new Rect((int)(bgW_unit*4)+1,(int)(bgH_unit*1),(int)(bgW_unit*5),(int)(bgH_unit*2));
        scene_L = new Rect((int)(bgW_unit*5)+1,(int)(bgH_unit*1),(int)(bgW_unit*6),(int)(bgH_unit*2));

        //MapDataの初期化
        mapData_cafe = new MapData(1,scene_C,obstacle1);
        mapData_cafe_6_12 = new MapData(1,scene_C, obstacle1_6_12);
        mapData_cafe_37_22 = new MapData(1,scene_C, obstacle1_37_22);
        mapData_cafe_38_39 = new MapData(1,scene_C, obstacle1_38_39);
        mapData_jitaku_ = new MapData(2,scene_J, obstacle2);
        mapData_jitaku_1 = new MapData(2,scene_J, obstacle2_1);
        mapData_jitaku_28 = new MapData(2,scene_J, obstacle2_28);
        mapData_jitaku_37_22_38_39 = new MapData(2,scene_J, obstacle2_37_22_38_39);
        mapData_forest = new MapData(3,scene_F,obstacle3);
        mapData_forest_16_1 = new MapData(3,scene_F,obstacle3_16_1);
        mapData_forest_16_5 = new MapData(3,scene_F,obstacle3_16_5);
        mapData_forest_28 = new MapData(3,scene_F,obstacle3_28);
        mapData_forest_43 = new MapData(3,scene_F,obstacle3_43);
        mapData_ike = new MapData(4,scene_H,obstacle4);
        mapData_ike_1 = new MapData(4,scene_H, obstacle4_1);
        mapData_ike_31_ = new MapData(4,scene_H, obstacle4_31_);
        mapData_ike_32 = new MapData(4,scene_H, obstacle4_32);
        mapData_ike_32_33 = new MapData(4,scene_H, obstacle4_32_33);
        mapData_ike_37_44_1 = new MapData(4,scene_H, obstacle4_37_44_1);
        mapData_ike_37_2 = new MapData(4,scene_H, obstacle4_37_2);
        mapData_ike_37_3 = new MapData(4,scene_H, obstacle4_37_3);
        mapData_siiiemae = new MapData(5,scene_D,obstacle5);
        mapData_siiiemae_37_22 = new MapData(5,scene_D,obstacle5_37_22);
        mapData_siiiemae_38_39 = new MapData(5,scene_D, obstacle5_38_39);
        mapData_denwa = new MapData(6,scene_E,obstacle6);
        mapData_denwa_38 = new MapData(6,scene_E, obstacle6_38_39);
        mapData_kominkan = new MapData(7,scene_K,obstacle7);
        mapData_kominkan_31 = new MapData(7,scene_K,obstacle7_31);
        mapData_kominkan_37 = new MapData(7,scene_K,obstacle7_37);
        mapData_jishitu = new MapData(8,scene_G,obstacle8);
        mapData_jishitu_8 = new MapData(8,scene_G,obstacle8_8);
        mapData_jishitu_30 = new MapData(8,scene_G,obstacle8_30);
        mapData_siiie = new MapData(9,scene_G,obstacle9);
        mapData_siiheya = new MapData(10,scene_G,obstacle10);
        mapData_cafenaka = new MapData(11,scene_G,obstacle11);
        mapData_ike_depth = new MapData(12,scene_B,obstacle12);
        mapData_forest_depth = new MapData(13,scene_L,obstacle13);
        mapData_forest_depth_13_21_4 = new MapData(13,scene_L,obstacle13_21_4);
        mapData_akiti = new MapData(14,scene_I,obstacle14);
        mapData_akiti_31_ = new MapData(14,scene_I,obstacle14_31_);
        mapData_koya = new MapData(15,scene_G,obstacle15);
        mapData_koya_19 = new MapData(15,scene_G,obstacle15_19);
        mapData_koya_23_3 = new MapData(15,scene_G, obstacle15_23_3);
        mapData_koya_32_33 = new MapData(15,scene_G, obstacle15_32_33);
        mapData_kaisou = new MapData(16,scene_G,obstacle_kaisou);
    }
    public static MapData getMapData(int changeBG){
        MapData mapData;
        //test用
        mapData= mapData_forest;
        //test用ここまで
        switch (changeBG){
            case 1:
                if(Integer.valueOf(getChapter()).equals(6) || Integer.valueOf(getChapter()).equals(12)){
                    mapData = mapData_cafe_6_12;
                }else if(Integer.valueOf(getChapter()).equals(37)&& getCounter() < 22){
                    mapData = mapData_cafe_37_22;
                }else if(Integer.valueOf(getChapter()).equals(38)||Integer.valueOf(getChapter()).equals(39)){
                    mapData = mapData_cafe_38_39;
                }else{
                    mapData = mapData_cafe;
                }
                break;
            case 2:
                if(Integer.valueOf(getChapter()).equals(1)){
                    mapData = mapData_jitaku_1;
                }else if(Integer.valueOf(getChapter()).equals(28)){
                    mapData = mapData_jitaku_28;
                }else if((Integer.valueOf(getChapter()).equals(37)&& getCounter() < 22)||Integer.valueOf(getChapter()).equals(38)||Integer.valueOf(getChapter()).equals(39)){
                    mapData = mapData_jitaku_37_22_38_39;
                }else{
                    mapData = mapData_jitaku_;
                }
                break;
            case 3:
                if(Integer.valueOf(getChapter()).equals(16)&&(Integer.valueOf(getCounter()).equals(1)||Integer.valueOf(getCounter()).equals(5))){
                    if(Integer.valueOf(getCounter()).equals(1)){
                        mapData = mapData_forest_16_1;
                    }else{
                        mapData = mapData_forest_16_5;
                    }
                }else if(Integer.valueOf(getChapter()).equals(28)){
                    mapData = mapData_forest_28;
                }else if(Integer.valueOf(getChapter()).equals(43)){
                    mapData = mapData_forest_43;
                }else{
                    mapData = mapData_forest;
                }
                break;
            case 4:
                if(Integer.valueOf(getChapter()).equals(32) || Integer.valueOf(getChapter()).equals(33)){
//                    mapData = mapData_ike_32_33;
                    mapData = mapData_ike_32;
                }else if((Integer.valueOf(getChapter()).equals(37) &&  getCounter() <= 17)||(Integer.valueOf(getChapter()).equals(44))){
                    mapData = mapData_ike_37_44_1;
                }else if(Integer.valueOf(getChapter()).equals(37) && 18 <= getCounter() && getCounter() <= 21 && getSub23CharacterIsShow()){
                    mapData = mapData_ike_37_2;
                }else if(Integer.valueOf(getChapter()).equals(37) && getCounter() == 21 && !getSub23CharacterIsShow()){
                    mapData = mapData_ike_37_3;
                }else if(Integer.valueOf(getChapter()).equals(1)){
                    mapData = mapData_ike_1;
                }else if(getChapter() >= 31){
                    mapData = mapData_ike_31_;
                }else{
                    mapData = mapData_ike;
                }
                break;
            case 5:
                if(Integer.valueOf(getChapter()).equals(37)&& getCounter() < 22){
                    mapData = mapData_siiiemae_37_22;
                }else if(Integer.valueOf(getChapter()).equals(38)||Integer.valueOf(getChapter()).equals(39)){
                    mapData = mapData_siiiemae_38_39;
                }else{
                    mapData = mapData_siiiemae;
                }
                break;
            case 6:
                if(Integer.valueOf(getChapter()).equals(38)||Integer.valueOf(getChapter()).equals(39)){
                    mapData = mapData_denwa_38;
                }else{
                    mapData = mapData_denwa;
                }
                break;
            case 7:
                if(Integer.valueOf(getChapter()).equals(31)){
                    mapData = mapData_kominkan_31;
                }else if(Integer.valueOf(getChapter()).equals(37)){
                    mapData = mapData_kominkan_37;
                }else{
                    mapData = mapData_kominkan;
                }
                break;
            case 8:
                if(Integer.valueOf(getChapter()).equals(8)){
                    mapData = mapData_jishitu_8;
                }else if(Integer.valueOf(getChapter()).equals(30)){
                    mapData = mapData_jishitu_30;
                }else{
                    mapData = mapData_jishitu;
                }
                break;
            case 9:
                mapData = mapData_siiie;
                break;
            case 10:
                mapData = mapData_siiheya;
                break;
            case 11:
                mapData = mapData_cafenaka;
                break;
            case 12:
                mapData = mapData_ike_depth;
                break;
            case 13:
                if(Integer.valueOf(getChapter()).equals(21) && Integer.valueOf(getCounter()).equals(4)){
                    mapData = mapData_forest_depth_13_21_4;
                }else{
                    mapData = mapData_forest_depth;
                }
                break;
            case 14:
                if((Integer.valueOf(getChapter()).equals(37)&& getCounter() < 22)||(Integer.valueOf(getChapter()).equals(38) || Integer.valueOf(getChapter()).equals(39))){
                    mapData = mapData_akiti;
                }else if(getChapter() >= 31){
                    mapData = mapData_akiti_31_;
                }else{
                    mapData = mapData_akiti;
                }
                break;
            case 15:
                if(Integer.valueOf(getChapter()).equals(32) || Integer.valueOf(getChapter()).equals(33)){
                    mapData = mapData_koya_32_33;
                }else if(Integer.valueOf(getChapter()).equals(23) && Integer.valueOf(getCounter()).equals(3)){
                    mapData = mapData_koya_23_3;
                }else if(Integer.valueOf(getChapter()).equals(19) && Integer.valueOf(getCounter()).equals(0)){
                    mapData = mapData_koya_19;
                }else{
                    mapData = mapData_koya;
                }
                break;
            case 16:
                mapData = mapData_kaisou;
                break;
        }
        return mapData;
    }
    void changeBG(int ChangeBG){
        switch (ChangeBG){
            case 1://カフェ
                srcRect = scene_C;
                    if(!(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(0)){
                    stopMusic();
                    setMusicID(0);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("カフェ付近");
                    return;
                    }
                });
                break;
            case 2://自宅前
                srcRect = scene_J;
                    if(!(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(0)){
                    stopMusic();
                    setMusicID(0);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("自宅前");
                    return;
                    }
                });
                break;
            case 3://もり
                srcRect = scene_F;
                    if(!(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(0)){
                    stopMusic();
                    setMusicID(0);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("森");
                    return;
                    }
                });
                break;
            case 4://池
                srcRect = scene_H;
                if((Integer.valueOf(getChapter()).equals(34) || Integer.valueOf(getChapter()).equals(35)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(2)){
                    stopMusic();
                    setMusicID(2);
                    playMusic();
                }
                else if(getEnding() && Integer.valueOf(getChapter()).equals(44) && !Integer.valueOf(getMusicID()).equals(6)){
                    setIsStoryMusic(false);
                    stopMusic();
                    setMusicID(6);
                    playMusic();
                }else if(!(Integer.valueOf(getChapter()).equals(34)) && !(Integer.valueOf(getChapter()).equals(35)) /*&& !(Integer.valueOf(getChapter()).equals(37))*/ &&
                        !(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(0)){
                    stopMusic();
                    setMusicID(0);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("池");
                    return;
                    }
                });
                break;
            case 5://しー家まえ
                srcRect = scene_D;
                    if(!(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(0)){
                    stopMusic();
                    setMusicID(0);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        TopBar.setText("しーちゃんの家付近");
                        return;
                    }
                });
                break;
            case 6://公衆電話
                srcRect = scene_E;
                    if(!(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(0)){
                    stopMusic();
                    setMusicID(0);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("森の入口");
                    return;
                    }
                });
                break;
            case 7://公民館
                srcRect = scene_K;
                    if(!(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(0)){
                    stopMusic();
                    setMusicID(0);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("公民館前");
                    return;
                    }
                });
                break;
            case 8://自宅
                srcRect = scene_G;
                    if(!(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(0)){
                    stopMusic();
                    setMusicID(0);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("自宅");
                        return;
                    }
                });
                break;
            case 9://しーちゃんの家
                srcRect = scene_G;
                    if(!(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(0)){
                    stopMusic();
                    setMusicID(0);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("しーちゃんの家");
                        return;
                    }
                });
                break;
            case 11://カフェの中
                srcRect = scene_G;
                    if(!(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(0)){
                    stopMusic();
                    setMusicID(0);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("カフェ");
                        return;
                    }
                });
                break;
            case 12://池の奧
                srcRect = scene_B;
                    if(!(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(1)){
                    stopMusic();
                    setMusicID(1);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("池の奧");
                        return;
                    }
                });
                break;
            case 13://森の奧
                srcRect = scene_L;
                    if(!(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(0)){
                    stopMusic();
                    setMusicID(0);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("森の奧");
                        return;
                    }
                });
                break;
            case 14://空き地
                srcRect = scene_I;
                    if(!(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(0)){
                    stopMusic();
                    setMusicID(0);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("池への道");
                        return;
                    }
                });
                break;
            case 15://小屋の中
                srcRect = scene_G;
                    if(!(Integer.valueOf(getChapter()).equals(44)) && !getIsStoryMusic() && !Integer.valueOf(getMusicID()).equals(0)){
                    stopMusic();
                    setMusicID(0);
                    playMusic();
                }
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("小屋");
                    return;
                    }
                });
                break;
            case 16:srcRect = scene_G;
                TopBar.getTopBarHandler().post(new Runnable() {
                    @Override
                    public void run() {
                    TopBar.setText("_");
                    return;
                    }
                });
                break;
        }
    }
    void draw(Canvas canvas){
        if(pre_changeBG != getChangeBG()){
            pre_changeBG = getChangeBG();
            setMainCharacterIsHide(true);
        }
        else if(getChangeBG() == 16 ||(getChapter() == 29 && getCounter() < 1)||(getChapter() == 28 && getCounter() >= 5 )){
        }else{
            setMainCharacterIsHide(false);
        }
        if(StoryView.getIsStop()){
            changeBG(getChangeBG());
        }
        canvas.drawBitmap(bgbmp,srcRect,rect,paint);
        canvas.drawBitmap(save_bmp,src_save_rect,save_rect,paint);
    }
}

class MapData{
    private Rect mapRect;
    private int number;
    private List<CoordinateData> coordinateDataList = new ArrayList<>();
    public MapData(int number,Rect rect,int [][] array){
        this.number = number;
        mapRect = rect;
        int index = 0;
        boolean arrayJudge = true;
        for(int x = 0;x<8;x++){
            for(int y = 0;y<13;y++){
                if(arrayJudge && array[index][0] == x && array[index][1] == y){
                    coordinateDataList.add(new CoordinateData(x,y,true));
                    if(index<array.length-1){
                        index++;
                    }else{
                        arrayJudge = false;
                        index=0;
                    }
                }else{
                    coordinateDataList.add(new CoordinateData(x,y));
                }
            }
        }
    }

    public int getNumber() {
        return number;
    }

    public List<CoordinateData> getCoordinateDataList() {
        return coordinateDataList;
    }
}
