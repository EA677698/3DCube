package me.erick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Math;

public class ThreeDRenderer extends JPanel implements KeyListener {

    Vector[] cube;
    OriginVector[] originVectors;


    ThreeDRenderer(){
        originVectors = new OriginVector[3];
        //I hat vector
        originVectors[0] = new OriginVector(1,0,0);
        //J hat vector
        originVectors[1] = new OriginVector(0,1,0);
        //K hat vector
        originVectors[2] = new OriginVector(0,0,1);
        cube = new Vector[8];
        //Cube corner vectors
        cube[0] = new Vector(-1,-1,-1);
        cube[1] = new Vector(1,-1,-1);
        cube[3] = new Vector(-1,-1,1);
        cube[2] = new Vector(1,-1,1);
        cube[4] = new Vector(-1,1,-1);
        cube[5] = new Vector(1,1,-1);
        cube[7] = new Vector(-1,1,1);
        cube[6] = new Vector(1,1,1);

    }

    public void linearTransformation() {
        for(int i = 0; i<8; i++){
            cube[i].vx = (cube[i].x*originVectors[0].x) + (cube[i].y*originVectors[1].x) + (cube[i].z*originVectors[2].x);
            cube[i].vy = (cube[i].x*originVectors[0].y) + (cube[i].y*originVectors[1].y) + (cube[i].z*originVectors[2].y);
            cube[i].vz = (cube[i].x*originVectors[0].z) + (cube[i].y*originVectors[1].z) + (cube[i].z*originVectors[2].z);
        }
    }

    public void changePitch(boolean clockwise){
        // PI/16 -- random incrementation amount
        int divider = 16;
        float angle = (float) (clockwise ? (-1 * (Math.PI / divider)) : (Math.PI / divider));
        //Stored angles so that it can retain previous state if necessary
        originVectors[0].angle += angle;
        originVectors[2].angle += angle;
        //Changes origin vectors (specifically i and k vector)
        originVectors[0].x = ((float) Math.cos(originVectors[0].angle));
        originVectors[2].z = ((float) Math.sin(originVectors[2].angle));
        linearTransformation();
    }


    public void changeRoll(boolean clockwise){
        // PI/16 -- random incrementation amount
        int divider = 16;
        float angle = (float) (clockwise ? (-1 * (Math.PI / divider)) : (Math.PI / divider));
        //Stored angles so that it can retain previous state if necessary
        originVectors[2].angle += angle;
        originVectors[1].angle += angle;
        //Changes origin vectors (specifically i and j vector)
        originVectors[2].z = ((float) Math.cos(originVectors[2].angle));
        originVectors[2].y = ((float) Math.cos(originVectors[2].angle));
//        originVectors[1].z = ((float) Math.cos(originVectors[1].angle));
        originVectors[1].y = ((float) Math.sin(originVectors[1].angle));
        //Applies linear transformation to cube corner vectors
        linearTransformation();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //3D axis lines
        int originScalar = 200;
        g.setColor(Color.BLUE);
        g.drawLine(960,540,960+(int)(originScalar*originVectors[0].x),540+(int)(originScalar*originVectors[0].y));
        g.setColor(Color.GREEN);
        g.drawLine(960,540,960+(int)(originScalar*originVectors[1].x),540+(int)(originScalar*originVectors[1].y));
        g.setColor(Color.RED);
        g.drawLine(960,540,960+(int)(originScalar*originVectors[2].z),540+(int)(originScalar*originVectors[2].y));
        g.setColor(Color.BLACK);
        //Scalar (from float to int)
        int multiplier = 100;
        //Screen offsets
        int yOffSet = 500;
        int xOffSet = 960;
        //Edges
        for(int i = 0; i<3; i++){
            g.drawLine(xOffSet + ((int) (cube[i].vx*multiplier)), yOffSet + ((int) (cube[i].vy*multiplier)), xOffSet + ((int) (cube[i+1].vx*multiplier)), yOffSet + ((int) (cube[i+1].vy*multiplier)));
            g.drawLine(xOffSet + ((int) (cube[i+4].vx*multiplier)), yOffSet + ((int) (cube[i+4].vy*multiplier)), xOffSet + ((int) (cube[i+5].vx*multiplier)), yOffSet + ((int) (cube[i+4].vy*multiplier)));
            g.drawLine(xOffSet + ((int) (cube[i].vz*multiplier)), yOffSet + ((int) (cube[i].vy*multiplier)), xOffSet + ((int) (cube[i+1].vz*multiplier)), yOffSet + ((int) (cube[i+1].vy*multiplier)));
            g.drawLine(xOffSet + ((int) (cube[i+4].vz*multiplier)), yOffSet + ((int) (cube[i+4].vy*multiplier)), xOffSet + ((int) (cube[i+5].vz*multiplier)), yOffSet + ((int) (cube[i+4].vy*multiplier)));
        }
        g.drawLine(xOffSet + ((int) (cube[0].vx*multiplier)), yOffSet + ((int) (cube[0].vy*multiplier)), xOffSet + ((int) (cube[4].vx*multiplier)), yOffSet + ((int) (cube[4].vy*multiplier)));
        g.drawLine(xOffSet + ((int) (cube[1].vx*multiplier)), yOffSet + ((int) (cube[1].vy*multiplier)), xOffSet + ((int) (cube[5].vx*multiplier)), yOffSet + ((int) (cube[5].vy*multiplier)));
        g.drawLine(xOffSet + ((int) (cube[2].vx*multiplier)), yOffSet + ((int) (cube[2].vy*multiplier)), xOffSet + ((int) (cube[6].vx*multiplier)), yOffSet + ((int) (cube[6].vy*multiplier)));
        g.drawLine(xOffSet + ((int) (cube[3].vx*multiplier)), yOffSet + ((int) (cube[3].vy*multiplier)), xOffSet + ((int) (cube[7].vx*multiplier)), yOffSet + ((int) (cube[7].vy*multiplier)));
        g.drawLine(xOffSet + ((int) (cube[3].vx*multiplier)), yOffSet + ((int) (cube[3].vy*multiplier)), xOffSet + ((int) (cube[0].vx*multiplier)), yOffSet + ((int) (cube[0].vy*multiplier)));
        g.drawLine(xOffSet + ((int) (cube[4].vx*multiplier)), yOffSet + ((int) (cube[4].vy*multiplier)), xOffSet + ((int) (cube[7].vx*multiplier)), yOffSet + ((int) (cube[7].vy*multiplier)));
        g.drawLine(xOffSet + ((int) (cube[0].vz*multiplier)), yOffSet + ((int) (cube[0].vy*multiplier)), xOffSet + ((int) (cube[4].vz*multiplier)), yOffSet + ((int) (cube[4].vy*multiplier)));
        g.drawLine(xOffSet + ((int) (cube[1].vz*multiplier)), yOffSet + ((int) (cube[1].vy*multiplier)), xOffSet + ((int) (cube[5].vz*multiplier)), yOffSet + ((int) (cube[5].vy*multiplier)));
        g.drawLine(xOffSet + ((int) (cube[2].vz*multiplier)), yOffSet + ((int) (cube[2].vy*multiplier)), xOffSet + ((int) (cube[6].vz*multiplier)), yOffSet + ((int) (cube[6].vy*multiplier)));
        g.drawLine(xOffSet + ((int) (cube[3].vz*multiplier)), yOffSet + ((int) (cube[3].vy*multiplier)), xOffSet + ((int) (cube[7].vz*multiplier)), yOffSet + ((int) (cube[7].vy*multiplier)));
        g.drawLine(xOffSet + ((int) (cube[3].vz*multiplier)), yOffSet + ((int) (cube[3].vy*multiplier)), xOffSet + ((int) (cube[0].vz*multiplier)), yOffSet + ((int) (cube[0].vy*multiplier)));
        g.drawLine(xOffSet + ((int) (cube[4].vz*multiplier)), yOffSet + ((int) (cube[4].vy*multiplier)), xOffSet + ((int) (cube[7].vz*multiplier)), yOffSet + ((int) (cube[7].vy*multiplier)));
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){
            changePitch(true);
        }
        if(key == KeyEvent.VK_RIGHT){
            changePitch(false);
        }
        if(key == KeyEvent.VK_DOWN){
            changeRoll(true);
        }
        if(key == KeyEvent.VK_UP){
            changeRoll(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
