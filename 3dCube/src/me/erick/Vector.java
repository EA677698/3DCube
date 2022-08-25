package me.erick;


public class Vector {

    public float x,y,z,vx,vy,vz;

    public void setAllThreePoints(int num){
        x = num;
        y = num;
        z = num;
    }

    public void addToY(int num){
        y += num;
    }

    public void scaleVector(int scalar){
        x = scalar*x;
        y = scalar*y;
        z = scalar*z;

    }

    public void setVisiblePoints(){
        vx = x;
        vy = y;
        vz = z;
    }
}
