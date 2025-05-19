package com.example;

import org.json.JSONObject;
import javax.swing.*;

import java.awt.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        //setup
        JFrame mainframe = new JFrame("mainframe");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setResizable(false);
        mainframe.setSize(400, 300);
        JPanel gui = new GradientPanel(new Color(75, 0, 250, 100), new Color(75,0,250,200), new FlowLayout());
        mainframe.setContentPane(gui);

        //input setup
        Input inputArea = new Input("Enter a city name.", 20);
        gui.add(inputArea.getPanel());
        mainframe.setVisible(true);

    }
}
