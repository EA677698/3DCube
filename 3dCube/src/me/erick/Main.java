package me.erick;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame window = new JFrame();
        window.setSize(1920,1080);
        ThreeDRenderer renderer = new ThreeDRenderer();
        window.addKeyListener(renderer);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.add(renderer);
        window.setVisible(true);
    }


}
