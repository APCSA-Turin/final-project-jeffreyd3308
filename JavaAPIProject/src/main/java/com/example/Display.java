package com.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Display extends JFrame{
    private static ArrayList<JFrame> frames = new ArrayList<JFrame>(); //all the Display frames

    public Display(String title) {
        super(title);
        setSize(new Dimension(300, 800));
        setResizable(false);
        frames.add(this);
    }

    public void reload() {
        setVisible(false);
        setVisible(true);
    }

    public static ArrayList<JFrame> getDisplayFrames() {
        return frames;
    }
}
