package com.kohei.ikegon.yakusokunohiwomouitido;

public class CoordinateData {
    private int x;
    private int y;
    private boolean obstacle = false;
    public CoordinateData(int x, int y,boolean obstacle){
        this.x = x;
        this.y = y;
        this.obstacle = obstacle;
    }
    public CoordinateData(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public boolean getObstacle(){
        return obstacle;
    }
}