package com.kohei.ikegon.yakusokunohiwomouitido;

public class Point {
    private float x;
    private float y;
    private int direction = 8;
    public Point(int x,int y){
        this.x = (float) x;
        this.y = (float) y;
    }
    public Point(float x,float y){
        this.x = x;
        this.y = y;
    }
    public Point(int x,int y,int direction){
        this.x = (float)x;
        this.y = (float)y;
        this.direction = direction;
    }
    public Point(float x,float y,int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getDirection(){
        return direction;
    }

}
