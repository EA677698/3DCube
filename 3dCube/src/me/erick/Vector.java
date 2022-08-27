package me.erick;


public class Vector {

    public float x,y,z,vx,vy,vz;

    public Vector(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
        setVisiblePoints();
    }

    public Vector(){}

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
