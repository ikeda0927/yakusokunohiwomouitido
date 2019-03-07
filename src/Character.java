package com.kohei.ikegon.yakusokunohiwomouitido;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getChangeBG;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getH_unit;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getIsStop;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.getW_unit;
import static com.kohei.ikegon.yakusokunohiwomouitido.StoryView.setIsStop;


public class Character {
    public static Canvas canvas;
    Paint paint = new Paint();
    private Bitmap bmp;
    private int characterWidth;
    private int characterHeight;
    public static final int FRONT = 0;
    public static final int BACK = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int LDF = 4;
    public static final int LDB = 5;
    public static final int RDF = 6;
    public static final int RDB =7;
    private RectF dstRect;
    private Rect srcRect;
    private RectF tempDst;//SubCharacterより
    private int direction;//SubCharacterより
    private List<Point> pointList;
    private int bgNum;
    private int characterNum;
    private boolean isShow = false;
    private float passiveX = -1f;
    private float passiveY = -1f;
    private boolean showCharacter;//SubCharacterより
    private boolean isMainCharacter = false;
    private static int switcherNum=0;
    static final int switcherNumStandard = 10;
    static Rect rect_front1;
    static Rect rect_front2;
    static Rect rect_back1;
    static Rect rect_back2;
    static Rect rect_ldf1;
    static Rect rect_ldf2;
    static Rect rect_rdf1;
    static Rect rect_rdf2;
    static Rect rect_left1;
    static Rect rect_left2;
    static Rect rect_right1;
    static Rect rect_right2;
    static Rect rect_ldb1;
    static Rect rect_ldb2;
    static Rect rect_rdb1;
    static Rect rect_rdb2;
    private int srcRectNum = 0;

    private static float nowX = 0;//For debug
    private static float nowY = 0;//For debug

    //mainCharacterとsubCharacterの識別用
    private boolean isPassive = true;

    private List<Node> nodeList = new ArrayList<>();
    private List<Node> openList = new ArrayList<>();
    private List<Point> path = new ArrayList<>();
    private boolean judge = true;
    private boolean isObstacle = false;
    private int drawCounter = 0;
    private boolean isHide;
    static final int DRAW_WEIGHT = 0;
    private int activeX;
    private int activeY;
    private int baseNodeIndex=0;
    private Node goalNode;
    private Point prePoint;
    private Node parent;
    public Character(Canvas canvas, Bitmap bmp){//主人公用
        this.canvas = canvas;
        this.bmp = bmp;
        isMainCharacter = true;
        characterWidth= bmp.getWidth()/4;
        characterHeight= bmp.getHeight()/4;
        isHide = false;
        isPassive = false;

        if(rect_front1 == null){
            rect_front1 = new Rect(0,0,characterWidth,characterHeight);
            rect_front2 = new Rect(characterWidth,0,characterWidth*2,characterHeight);
            rect_back1 = new Rect(characterWidth*2,0,characterWidth*3,characterHeight);
            rect_back2 = new Rect(characterWidth*3,0,characterWidth*4,characterHeight);
            rect_ldf1 = new Rect(0,characterHeight,characterWidth,characterHeight*2);
            rect_ldf2 = new Rect(characterWidth,characterHeight,characterWidth*2,characterHeight*2);
            rect_rdf1 = new Rect(characterWidth*2,characterHeight,characterWidth*3,characterHeight*2);
            rect_rdf2 = new Rect(characterWidth*3,characterHeight,characterWidth*4,characterHeight*2);
            rect_left1 = new Rect(0,characterHeight*2,characterWidth,characterHeight*3);
            rect_left2 = new Rect(characterWidth,characterHeight*2,characterWidth*2,characterHeight*3);
            rect_right1 = new Rect(characterWidth*2,characterHeight*2,characterWidth*3,characterHeight*3);
            rect_right2 = new Rect(characterWidth*3,characterHeight*2,characterWidth*4,characterHeight*3);
            rect_ldb1 = new Rect(0,characterHeight*3,characterWidth,characterHeight*4);
            rect_ldb2 = new Rect(characterWidth,characterHeight*3,characterWidth*2,characterHeight*4);
            rect_rdb1 = new Rect(characterWidth*2,characterHeight*3,characterWidth*3,characterHeight*4);
            rect_rdb2 = new Rect(characterWidth*3,characterHeight*3,characterWidth*4,characterHeight*4);
        }
    }
    public Character(Canvas canvas, Bitmap bmp,int bgNum,int CNum){
        this.bmp = bmp;
        this.bgNum = bgNum;
        this.canvas = canvas;
        this.characterNum = CNum;
    }
    public Character(Canvas canvas, Bitmap bmp,int bgNum,int CNum,float x,float y,int direction){
        this.bmp = bmp;
        this.bgNum = bgNum;
        this.canvas = canvas;
        this.characterNum = CNum;
        dstRect = new RectF(x*getW_unit(),y*getH_unit(),x*getW_unit()+getW_unit(),y*getH_unit()+getH_unit());
        tempDst = new RectF(x*getW_unit(),y*getH_unit(),x*getW_unit()+getW_unit(),y*getH_unit()+getH_unit());
        this.direction = direction;
        passiveX = x;
        passiveY = y;
    }

    public void setBgNum(int bgNum){
        this.bgNum = bgNum;
    }
    public int getBgNum(){
        return bgNum;
    }
    public boolean getIsShow(){
        return showCharacter;
    }
    public int getDirectionNum(){
        return direction;
    }
    public void setDstRectByCoordinate(float x, float y){
        passiveX = x;
        passiveY = y;
        dstRect = new RectF(x*getW_unit(),y*getH_unit(),x*getW_unit()+getW_unit(),y*getH_unit()+getH_unit());
        tempDst = new RectF(x*getW_unit(),y*getH_unit(),x*getW_unit()+getW_unit(),y*getH_unit()+getH_unit());
    }


    public void setPrePoint(Point point){
        prePoint = point;
    }
    public Point getPrePoint(){
        return prePoint;
    }
    public void setIsHide(boolean judge){
        isHide = judge;
    }
    public boolean getIsHide(){
        return isHide;
    }
    public int getSrcRectNum(){
        return srcRectNum;
    }
    public static int getSwitcherNum(){
        return switcherNum;
    }
    public static void setSwitcherNum(int num){
        switcherNum = num;
    }
    public void setIsPassive(boolean bool){
        isPassive = bool;
    }

    public static float getNowX(){
        return nowX;
    }
    public static float getNowY(){
        return nowY;
    }
    public static void setNowX(float nowx){
        nowX = nowx;
    }
    public static void setNowY(float nowy){
        nowY = nowy;
    }

    public boolean getIsPassive(){
        return isPassive;
    }

    public void setActiveCharacterDirection(int direction){
        srcRectNum = direction;
        this.direction = direction;
    }
    public void setDirection(int direction){
        this.direction = direction;
    }

    public void drawCharacter(){
        if(!isPassive){
            if(DRAW_WEIGHT < drawCounter){
                Point nowPoint;
                if(path.isEmpty()){
                    nowPoint = prePoint;
                    goalNode = null;
                    setIsStop(true);
                }else{
                    nowPoint = path.remove(path.size()-1);
                    setIsStop(false);
                }
                dstRect = new RectF(nowPoint.getX()*getW_unit(),nowPoint.getY()*getH_unit(),(nowPoint.getX()+1)*getW_unit(),(nowPoint.getY()+1)*getH_unit());

                if(isMainCharacter){
                    nowX = nowPoint.getX();
                    nowY = nowPoint.getY();
                }

                if (srcRect == null){
                    srcRect =rect_front1;
                }
                if(prePoint != null && !getIsStop()){
                    if((Float.valueOf(prePoint.getX()).equals(Float.valueOf(nowPoint.getX()))  && prePoint.getY() < nowPoint.getY())||(prePoint.getDirection() == FRONT)){
                        //正面
                        srcRectNum = FRONT;
                        if(switcherNum < switcherNumStandard){
                            srcRect = rect_front1;
                        }else{
                            srcRect = rect_front2;
                        }
                    }else if((Float.valueOf(prePoint.getX()).equals(Float.valueOf(nowPoint.getX())) && prePoint.getY() > nowPoint.getY()) || (prePoint.getDirection() == BACK)){
                        //後ろ
                        srcRectNum = BACK;
                        if(switcherNum < switcherNumStandard){
                            srcRect = rect_back1;
                        }else{
                            srcRect = rect_back2;
                        }
                    }else if((prePoint.getX() > nowPoint.getX() && prePoint.getY() < nowPoint.getY()) || (prePoint.getDirection() == LDF)){
                        //左斜め前
                        srcRectNum = LDF;
                        if(switcherNum<switcherNumStandard){
                            srcRect = rect_ldf1;
                        }else{
                            srcRect = rect_ldf2;
                        }
                    }else if((prePoint.getX() < nowPoint.getX() && prePoint.getY() < nowPoint.getY()) || (prePoint.getDirection() == RDF)){
                        //右斜め前
                        srcRectNum = RDF;
                        if(switcherNum < switcherNumStandard){
                            srcRect = rect_rdf1;
                        }else{
                            srcRect = rect_rdf2;
                        }
                    }else if((prePoint.getX() > nowPoint.getX() && Float.valueOf(prePoint.getY()).equals(Float.valueOf(nowPoint.getY()))) || (prePoint.getDirection() == LEFT)){
                        //左
                        srcRectNum = LEFT;
                        if(switcherNum< switcherNumStandard){
                            srcRect =rect_left1;
                        }else{
                            srcRect = rect_left2;
                        }
                    }else if((prePoint.getX() < nowPoint.getX() && Float.valueOf(prePoint.getY()).equals(Float.valueOf(nowPoint.getY()))) || (prePoint.getDirection() == RIGHT)){
                        //右
                        srcRectNum = RIGHT;
                        if(switcherNum< switcherNumStandard){
                            srcRect = rect_right1;
                        }else{
                            srcRect = rect_right2;
                        }
                    }else if((prePoint.getX() < nowPoint.getX() && prePoint.getY() > nowPoint.getY()) || (prePoint.getDirection()) == RDB){
                        //右斜め後ろ
                        srcRectNum = RDB;
                        if(switcherNum< switcherNumStandard){
                            srcRect = rect_rdb1;
                        }else{
                            srcRect = rect_rdb2;
                        }
                    }else if((prePoint.getX() > nowPoint.getX() && prePoint.getY() > nowPoint.getY()) || (prePoint.getDirection() == LDB)){
                        //左斜め後ろ
                        srcRectNum = LDB;
                        if(switcherNum<switcherNumStandard){
                            srcRect = rect_ldb1;
                        }else{
                            srcRect = rect_ldb2;
                        }
                    }
                }else{
                    switch (srcRectNum){
                        case 0:
                            if(getSwitcherNum() < switcherNumStandard){
                                srcRect = rect_front1;
                            }else{
                                srcRect = rect_front2;
                            }
                            break;
                        case 1:
                            if(getSwitcherNum() < switcherNumStandard){
                                srcRect = rect_back1;
                            }else{
                                srcRect = rect_back2;
                            }
                            break;
                        case 2:
                            if(getSwitcherNum() < switcherNumStandard){
                                srcRect = rect_left1;
                            }else{
                                srcRect = rect_left2;
                            }
                            break;
                        case 3:
                            if(getSwitcherNum() < switcherNumStandard){
                                srcRect = rect_right1;
                            }else{
                                srcRect = rect_right2;
                            }
                            break;
                        case 4:
                            if(getSwitcherNum() < switcherNumStandard){
                                srcRect = rect_ldf1;
                            }else{
                                srcRect = rect_ldf2;
                            }
                            break;
                        case 5:
                            if(getSwitcherNum() < switcherNumStandard){
                                srcRect = rect_ldb1;
                            }else{
                                srcRect = rect_ldb2;
                            }
                            break;
                        case 6:
                            if(getSwitcherNum() < switcherNumStandard){
                                srcRect = rect_rdf1;
                            }else{
                                srcRect = rect_rdf2;
                            }
                            break;
                        case 7:
                            if(getSwitcherNum() < switcherNumStandard){
                                srcRect = rect_rdb1;
                            }else{
                                srcRect = rect_rdb2;
                            }
                            break;
                    }
                }
                prePoint = nowPoint;
                drawCounter =0;
            }
            drawCounter++;
            if(!isHide && srcRect != null && dstRect != null && bmp != null){
                canvas.drawBitmap(bmp,srcRect,dstRect,paint);
            }
        }else{
            if(showCharacter){
                if(Integer.valueOf(bgNum).equals(getChangeBG())){
                    if(DRAW_WEIGHT < drawCounter){
                        if(pointList != null && !pointList.isEmpty()){
                            Point point = pointList.remove(0);
                            passiveX = point.getX();
                            passiveY = point.getY();
                            dstRect = new RectF(passiveX*getW_unit(),passiveY*getH_unit(),passiveX*getW_unit()+getW_unit(),passiveY*getH_unit()+getH_unit());
                            tempDst = new RectF(passiveX*getW_unit(),passiveY*getH_unit(),passiveX*getW_unit()+getW_unit(),passiveY*getH_unit()+getH_unit());
                            direction = point.getDirection();
                        }
                        switch (direction){
                            case 0:
                                if(getSwitcherNum() < switcherNumStandard){
                                    srcRect = rect_front1;
                                }else{
                                    srcRect = rect_front2;
                                }
                                break;
                            case 1:
                                if(getSwitcherNum() < switcherNumStandard){
                                    srcRect = rect_back1;
                                }else{
                                    srcRect = rect_back2;
                                }
                                break;
                            case 2:
                                if(getSwitcherNum() < switcherNumStandard){
                                    srcRect = rect_left1;
                                }else{
                                    srcRect = rect_left2;
                                }
                                break;
                            case 3:
                                if(getSwitcherNum() < switcherNumStandard){
                                    srcRect = rect_right1;
                                }else{
                                    srcRect = rect_right2;
                                }
                                break;
                            case 4:
                                if(getSwitcherNum() < switcherNumStandard){
                                    srcRect = rect_ldf1;
                                }else{
                                    srcRect = rect_ldf2;
                                }
                                break;
                            case 5:
                                if(getSwitcherNum() < switcherNumStandard){
                                    srcRect = rect_ldb1;
                                }else{
                                    srcRect = rect_ldb2;
                                }
                                break;
                            case 6:
                                if(getSwitcherNum() < switcherNumStandard){
                                    srcRect = rect_rdf1;
                                }else{
                                    srcRect = rect_rdf2;
                                }
                                break;
                            case 7:
                                if(getSwitcherNum() < switcherNumStandard){
                                    srcRect = rect_rdb1;
                                }else{
                                    srcRect = rect_rdb2;
                                }
                                break;
                        }
                        drawCounter = 0;
                    }
                    if(srcRect != null && dstRect != null && bmp != null){
                        canvas.drawBitmap(bmp,srcRect,dstRect,paint);
                    }
                    drawCounter++;
                    isShow = true;
                }else{
                    isShow = false;
                }
            }else{
                isShow = false;
            }
        }
    }

    public void setMainCharacterDirection(int direction){
        switch(direction){
            case 0:
                srcRectNum = FRONT;
                this.direction = FRONT;
                break;
            case 1:
                srcRectNum = BACK;
                this.direction = BACK;
                break;
            case 2:
                srcRectNum = LEFT;
                this.direction = LEFT;
                break;
            case 3:
                srcRectNum = RIGHT;
                this.direction = RIGHT;
                break;
        }
    }
    public void setMainCharacterDirectionByCharacter(float subX, float subY){
        if(Float.valueOf(nowX).equals(Float.valueOf(subX)) && nowY < subY){
            srcRectNum = FRONT;
        }else if(Float.valueOf(nowX).equals(Float.valueOf(subX)) && subY < nowY){
            srcRectNum = BACK;
        }else if(Float.valueOf(subY).equals(nowY) && nowX < subX){
            srcRectNum = RIGHT;
        }else if(Float.valueOf(subY).equals(nowY) && subX < nowX){
            srcRectNum = LEFT;
        }
    }
    public void setShowCharacter(boolean bool){//SubCharacterより
        showCharacter = bool;
        if(!bool){
            dstRect = new RectF(-2*getW_unit(),-2*getH_unit(),-1*getW_unit(),-1*getH_unit());
        }else if(tempDst != null){
            dstRect = new RectF(tempDst.left,tempDst.top,tempDst.right,tempDst.bottom);
        }
    }
    public void setDirectionForMainCharacter(){
        if(Float.valueOf(passiveX).equals(Float.valueOf(nowX)) && nowY < passiveY){
            direction = BACK;
        }else if(Float.valueOf(passiveX).equals(Float.valueOf(nowX)) && passiveY < nowY){
            direction = FRONT;
        }else if(Float.valueOf(nowY).equals(passiveY) && Float.valueOf(nowX) < passiveX){
            direction = LEFT;
        }else if(Float.valueOf(nowY).equals(passiveY) && passiveX < Float.valueOf(nowX)){
            direction = RIGHT;
        }
    }
    public void setPointList(List<Point> pointList){
        this.pointList = pointList;
    }

    void searchPath(int startX, int startY, int destinationX, int destinationY, List<CoordinateData> coordinateDataList){
        path = new ArrayList<>();
        //まずノードリストにNONE状態のノードを入れておく。
        if(nodeList.isEmpty()){
            for(CoordinateData coordinateData : GameMap.getMapData(getChangeBG()).getCoordinateDataList()){
                nodeList.add(new Node(coordinateData.getX(),coordinateData.getY(),coordinateData.getObstacle()));
            }
        }

        for(CoordinateData cd:coordinateDataList){
            if(Integer.valueOf(cd.getX()).equals(Integer.valueOf(destinationX)) && Integer.valueOf(Integer.valueOf(cd.getY())).equals(Integer.valueOf(destinationY)) && cd.getObstacle()){
                isObstacle = true;
            }
        }
        if(isObstacle){//いけないところに近いところの座標を得られたら得る。
            final int f = 5;
            int i = 1;
            int destinationNum = 0;//0:above,1:below,2:left,3:right
            boolean right = false;
            boolean left = false;
            boolean above = false;
            boolean below = false;
            int xy = 100;
            while(i < f){
                if(checkMovable(destinationX,destinationY-i,coordinateDataList)){//above
                    above = true;
                }
                if(checkMovable(destinationX,destinationY+i,coordinateDataList)){//below
                    below = true;
                }
                if(checkMovable(destinationX-i,destinationY,coordinateDataList)){//left
                    left = true;
                }
                if(checkMovable(destinationX+i,destinationY,coordinateDataList)){//right
                    right = true;
                }
                if(above || below || left || right){
                    break;
                }
                i++;
            }
            if(above || below || left || right){
                if(above){
                    xy = getAbsoluteValue(startX,destinationX)+getAbsoluteValue(startY,destinationY-i);
                }
                if(below){
                    if(getAbsoluteValue(startX,destinationX)+getAbsoluteValue(startY,destinationY+i) < xy){
                        xy = getAbsoluteValue(startX,destinationX)+getAbsoluteValue(startY,destinationY-i);
                        destinationNum = 1;
                    }
                }
                if(left){
                    if(getAbsoluteValue(startX,destinationX-i)+getAbsoluteValue(startY,destinationY) < xy){
                        xy = getAbsoluteValue(startX,destinationX-i)+getAbsoluteValue(startY,destinationY);
                        destinationNum = 2;
                    }
                }
                if(right){
                    if(getAbsoluteValue(startX,destinationX+i)+getAbsoluteValue(startY,destinationY) < xy){
                        destinationNum = 3;
                    }
                }
                switch (destinationNum){
                    case 0:
                        isObstacle = false;
                        //Log.d("obstacleMove","activeX:"+String.valueOf(destinationX)+" activeY:"+String.valueOf(destinationY-i));
                        destinationY = destinationY - i;
                        break;
                    case 1:
                        isObstacle = false;
                        //Log.d("obstacleMove","activeX:"+String.valueOf(destinationX)+" activeY:"+String.valueOf(destinationY+i));
                        destinationY = destinationY + i;
                        break;
                    case 2:
                        isObstacle = false;
                        //Log.d("obstacleMove","activeX:"+String.valueOf(destinationX-i)+" activeY:"+String.valueOf(destinationY));
                        destinationX = destinationX -i;
                        break;
                    case 3:
                        isObstacle = false;
                        //Log.d("obstacleMove","activeX:"+String.valueOf(destinationX+i)+" activeY:"+String.valueOf(destinationY));
                        destinationX = destinationX +i;
                        break;
                }
            }
        }
        if(!isObstacle && !(Integer.valueOf(startX).equals(destinationX)&&Integer.valueOf(startY).equals(destinationY))){
            openList.clear();
            for(Node node : nodeList){
                if(Integer.valueOf(startX).equals(Integer.valueOf(node.getX())) && Integer.valueOf(startY).equals(Integer.valueOf(node.getY()))&& Integer.valueOf(node.getState()).equals(Integer.valueOf(Node.NONE))){
                    node.setState(Node.OPEN);
                    openList.add(node);
                    parent = node;
                    baseNodeIndex = nodeList.indexOf(node);
                    break;
                }
            }
            activeX = startX;
            activeY = startY;
            while (judge){
                if(checkMovable(activeX -1, activeY -1,coordinateDataList)){
                    int index = getIndexOfNodeList(activeX -1, activeY -1);
                    if(Integer.valueOf(nodeList.get(index).getState()).equals(Integer.valueOf(Node.NONE))){
                        nodeList.get(index).setState(Node.OPEN);
                        if(nodeList.get(index).calc(destinationX,destinationY,parent,true)){
                            goalNode = nodeList.get(index);
                            judge = false;
                            nodeList.get(baseNodeIndex).setState(Node.CLOSE);
                            continue;
                        }
                        openList.add(nodeList.get(index));
                    }
                }
                if(checkMovable(activeX, activeY -1,coordinateDataList)){
                    int index = getIndexOfNodeList(activeX, activeY -1);
                    if(Integer.valueOf(nodeList.get(index).getState()).equals(Integer.valueOf(Node.NONE))){
                        nodeList.get(index).setState(Node.OPEN);
                        if(nodeList.get(index).calc(destinationX,destinationY,parent,false)){
                            goalNode = nodeList.get(index);
                            judge = false;
                            nodeList.get(baseNodeIndex).setState(Node.CLOSE);
                            continue;
                        }
                        openList.add(nodeList.get(index));
                    }
                }
                if(checkMovable(activeX +1, activeY -1,coordinateDataList)){
                    int index = getIndexOfNodeList(activeX +1, activeY -1);
                    if(Integer.valueOf(nodeList.get(index).getState()).equals(Integer.valueOf(Node.NONE))){
                        nodeList.get(index).setState(Node.OPEN);
                        if(nodeList.get(index).calc(destinationX,destinationY,parent,true)){
                            goalNode = nodeList.get(index);
                            judge = false;
                            nodeList.get(baseNodeIndex).setState(Node.CLOSE);
                            continue;
                        }
                        openList.add(nodeList.get(index));
                    }
                }
                if(checkMovable(activeX +1, activeY,coordinateDataList)){
                    int index = getIndexOfNodeList(activeX +1, activeY);
                    if(Integer.valueOf(nodeList.get(index).getState()).equals(Integer.valueOf(Node.NONE))){
                        nodeList.get(index).setState(Node.OPEN);
                        if(nodeList.get(index).calc(destinationX,destinationY,parent,false)){
                            goalNode = nodeList.get(index);
                            judge = false;
                            nodeList.get(baseNodeIndex).setState(Node.CLOSE);
                            continue;
                        }
                        openList.add(nodeList.get(index));
                    }
                }
                if(checkMovable(activeX +1, activeY +1,coordinateDataList)){
                    int index = getIndexOfNodeList(activeX +1, activeY +1);
                    if(Integer.valueOf(nodeList.get(index).getState()).equals(Integer.valueOf(Node.NONE)) ){
                        nodeList.get(index).setState(Node.OPEN);
                        if(nodeList.get(index).calc(destinationX,destinationY,parent,true)){
                            goalNode = nodeList.get(index);
                            judge = false;
                            nodeList.get(baseNodeIndex).setState(Node.CLOSE);
                            continue;
                        }
                        openList.add(nodeList.get(index));
                    }
                }
                if(checkMovable(activeX, activeY +1,coordinateDataList)){
                    int index = getIndexOfNodeList(activeX, activeY +1);
                    if(Integer.valueOf(nodeList.get(index).getState()).equals(Integer.valueOf(Node.NONE))){
                        nodeList.get(index).setState(Node.OPEN);
                        if(nodeList.get(index).calc(destinationX,destinationY,parent,false)){
                            goalNode = nodeList.get(index);
                            judge = false;
                            nodeList.get(baseNodeIndex).setState(Node.CLOSE);
                            continue;
                        }
                        openList.add(nodeList.get(index));
                    }
                }
                if(checkMovable(activeX -1, activeY +1,coordinateDataList)){
                    int index = getIndexOfNodeList(activeX -1, activeY +1);
                    if(Integer.valueOf(nodeList.get(index).getState()).equals(Integer.valueOf(Node.NONE))){
                        nodeList.get(index).setState(Node.OPEN);
                        if(nodeList.get(index).calc(destinationX,destinationY,parent,true)){
                            goalNode = nodeList.get(index);
                            judge = false;
                            nodeList.get(baseNodeIndex).setState(Node.CLOSE);
                            continue;
                        }
                        openList.add(nodeList.get(index));
                    }
                }
                if(checkMovable(activeX -1, activeY,coordinateDataList)){
                    int index = getIndexOfNodeList(activeX -1, activeY);
                    if(Integer.valueOf(nodeList.get(index).getState()).equals(Integer.valueOf(Node.NONE))){
                        nodeList.get(index).setState(Node.OPEN);
                        if(nodeList.get(index).calc(destinationX,destinationY,parent,false)){
                            goalNode = nodeList.get(index);
                            judge = false;
                            nodeList.get(baseNodeIndex).setState(Node.CLOSE);
                            continue;
                        }
                        openList.add(nodeList.get(index));
                    }
                }
                nodeList.get(baseNodeIndex).setState(Node.CLOSE);
                Iterator<Node> iterator = openList.iterator();
                while(iterator.hasNext()){
                    int state = iterator.next().getState();
                    if(Integer.valueOf(state).equals(Integer.valueOf( Node.CLOSE))){
                        iterator.remove();
                    }
                }
                if(openList.isEmpty()){
                    judge = false;
                    continue;
                }
                //基準ノードを得る
                float score = openList.get(0).getScore();
                baseNodeIndex = nodeList.indexOf(openList.get(0));
                int esScore2 = openList.get(0).getEsCost2();//最短判定用
                for(Node openNode:openList){
                    if(openNode.getScore()<score){
                        score = openNode.getScore();
                        baseNodeIndex = nodeList.indexOf(openNode);
                    }else if(Float.valueOf(openNode.getScore()).equals(Float.valueOf(score))){
                        if(openNode.getEsCost2()<esScore2){
                            score = openNode.getScore();
                            baseNodeIndex = nodeList.indexOf(openNode);
                        }
                    }
                }
                parent = nodeList.get(baseNodeIndex);
                activeX = nodeList.get(baseNodeIndex).getX();
                activeY = nodeList.get(baseNodeIndex).getY();
            }
        }
        judge = true;
        isObstacle = false;
        try{
            goalNode.getPath(path);
            //Log.d("path", "-------------------------------------");
            try{
                for(Point point : path){
                    System.out.println("path activeX:"+point.getX()+" activeY:"+point.getY());
                    //Log.d("path","path activeX:"+point.getX()+" activeY:"+point.getY());
                }
            }catch (ConcurrentModificationException e){
                //Log.e("path","ConcurrentModificationException Occurred at 471 in Character.java");
            }
            //Log.d("path","*********************************** START("+String.valueOf(startX)+","+String.valueOf(startY)+") dest("+String.valueOf(destinationX)+","+String.valueOf(destinationY)+")");
            String s0 = "*node*\n";
            for(int i = 0;i<13;i++){
                s0 = s0+String.valueOf(i)+"\t";
                String s1 = "\n"+"\t";
                String s2 = "\n "+"\t";
                String s3 = "\n "+"\t";
                for(int k = 0;k<8;k++){
                    for(Node node : nodeList){
                        if(Integer.valueOf(node.getX()).equals(k) && Integer.valueOf(node.getY()).equals(i)){
                            s0 += "  e:"+node.getEstimatedCost()+"\t";
                            s1 += "  a:"+node.getTheActualCost()+"\t";
                            s2 += "  s:"+node.getScore()+"\t";
                            switch (node.getState()){
                                case 0:
                                    s3+="    \t";
                                    break;
                                case 1:
                                    s3+="Open\t";
                                    break;
                                case 2:
                                    s3+="Close\t";
                                    break;
                            }
                        }
                    }
                }
                s0 = s0+s1+s2+s3+"\n";
            }

            //Log.d("path",s0);
        }catch (NullPointerException e){
            //Log.e("goalNode"," goalNode is null");
        }
        for(Node node : nodeList){
            node.clear();
        }
    }
    public int getIndexOfNodeList(int x,int y){
        for(Node node : nodeList){
            if(Integer.valueOf(node.getX()).equals(Integer.valueOf(x)) && Integer.valueOf(node.getY()).equals(Integer.valueOf(y))){
                return nodeList.indexOf(node);
            }
        }
        return -1;
    }
    boolean checkMovable(int x, int y, List<CoordinateData> coordinateDataList){
        if(0 <= x && 0 <= y && x <= 7 &&  y <= 12){
            for(CoordinateData coordinateData : coordinateDataList){
                if(Integer.valueOf(coordinateData.getX()).equals(Integer.valueOf(x)) && Integer.valueOf(coordinateData.getY()).equals(Integer.valueOf(y))){
                    return !coordinateData.getObstacle();
                }
            }
        }
        return false;
    }
    public int getAbsoluteValue(int src,int dest){
        int value = 0;
        if((dest - src)<0){
            value = (dest - src)*-1;
        }else{
            value = dest - src;
        }
        return value;
    }
}

class Node{
    private int x;
    private int y;
    public static final int NONE = 0;
    public static final int OPEN = 1;
    public static final int CLOSE = 2;
    private int state = NONE;
    private int estimatedCost;
    private int esCost2;
    private float theActualCost = 0f;
    private float score;
    private boolean isObstacle = false;
    private Node parent;
    public Node(int x, int y, boolean isObstacle){
        this.x = x;
        this.y = y;
        this.isObstacle = isObstacle;
        parent = null;
        score = theActualCost + estimatedCost;
    }
    public void clear(){
        state = NONE;
        estimatedCost =0;
        esCost2 = 0;
        theActualCost =0;
        score = 0;
        parent = null;
    }
    public float getTheActualCost(){
        return theActualCost;
    }
    public int getAbsoluteValue(int src,int dest){
        int value = 0;
        if((dest - src)<0){
            value = (dest - src)*-1;
        }else{
            value = dest - src;
        }
        return value;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public boolean calc(int destinationX,int destinationY, Node parent,boolean isDiagonal){
        this.parent = parent;
        if(parent != null){
            if(isDiagonal){
                theActualCost = 1.5f+parent.getTheActualCost();
            }else{
                theActualCost = 1f+parent.getTheActualCost();
            }
        }
        if(getAbsoluteValue(x,destinationX) < getAbsoluteValue(y,destinationY)){
            estimatedCost = getAbsoluteValue(y,destinationY);
            esCost2 = getAbsoluteValue(x,destinationX);
        }else{
            estimatedCost = getAbsoluteValue(x,destinationX);
            esCost2 = getAbsoluteValue(y,destinationY);
        }
        score = theActualCost+estimatedCost;
        if(Integer.valueOf(x).equals(Integer.valueOf(destinationX)) && Integer.valueOf(y).equals(Integer.valueOf(destinationY))){
            return true;
        }else {
            return false;
        }
    }
    public int getEsCost2(){
        return esCost2;
    }
    public boolean getIsObstacle(){
        return isObstacle;
    }
    public int getEstimatedCost(){
        return estimatedCost;
    }
    public int getState(){
        return state;
    }
    public float getScore(){
        return score;
    }
    public void setState(int state){
        this.state = state;
    }

    public void getPath(List<Point> pointList){
        pointList.add(new Point(x, y));
        if(parent != null){
            parent.getPath(pointList);
        }
    }
}
