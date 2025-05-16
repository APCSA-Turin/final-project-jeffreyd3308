package com.example;

import org.json.JSONObject;

import javax.swing.*;

import java.awt.FlowLayout;
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
        mainframe.setLayout(new FlowLayout());
        mainframe.setSize(400, 300);

        Input inputArea = new Input("Enter a city name.", 20);
        mainframe.add(inputArea.getText());
        mainframe.add(inputArea.getInput());
        mainframe.add(inputArea.getSubmitter());
        mainframe.setVisible(true);

    }
}
