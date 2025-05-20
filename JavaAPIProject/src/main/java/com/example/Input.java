package com.example;
//import java.util.Scanner;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

public class Input extends GradientPanel {
    private JTextField input;
    private JButton submitter;
    private JLabel text;
    private JSONObject obj;

    public Input(String defaultString, int size) {
        super(Color.BLUE, Color.CYAN, new FlowLayout());
        setPreferredSize(new Dimension(300, 100));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        text = new JLabel(defaultString);
        input = new JTextField(size);
        submitter = new JButton("Submit");
        add(text);
        add(input);
        add(submitter);
    }

    public Display createDisplay() {
        String name = obj.getString("name");
        double currentTemp = obj.getJSONObject("main").getDouble("temp");
        double minTemp = obj.getJSONObject("main").getDouble("temp_min");
        double maxTemp = obj.getJSONObject("main").getDouble("temp_max");
        int humidity = obj.getJSONObject("main").getInt("humidity");
        String description = obj.getJSONArray("weather").getJSONObject(0).getString("description");
        String icon = obj.getJSONArray("weather").getJSONObject(0).getString("icon");
        int windDir = obj.getJSONObject("wind").getInt("deg");
        double windSpeed = obj.getJSONObject("wind").getDouble("speed");
        int visibility = obj.getInt("visibility");
        Display display = new Display(name, currentTemp, minTemp, maxTemp, humidity, description, icon, windDir, windSpeed, visibility);
        return display;
    }

    public JTextField getInput() {
        return input;
    }

    public JButton getSubmitter() {
        return submitter;
    }

    public void addListener(ActionListener e) {
        submitter.addActionListener(e);
    }

    public JLabel getText() {
        return text;
    }
}
