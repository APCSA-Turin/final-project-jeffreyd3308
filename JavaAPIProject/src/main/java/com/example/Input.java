package com.example;
//import java.util.Scanner;
//finished class unless bug/troubleshoot

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.MalformedURLException;

public class Input extends GradientPanel {
    private JTextField input;
    private JButton submitter;
    private JLabel text;
    private JSONObject obj;
    private JSONObject forecast;

    public Input() {
        super(Color.BLUE, Color.CYAN, new FlowLayout());
        setPreferredSize(new Dimension(300, 100));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        text = new JLabel("Enter a city name.");
        input = new JTextField(20);
        submitter = new JButton("Submit");
        add(text);
        add(input);
        add(submitter);
    }

    public DisplayPreview createDisplay() {
        //parse JSON info
        String name = obj.getString("name");
        double currentTemp = obj.getJSONObject("main").getDouble("temp");
        double minTemp = obj.getJSONObject("main").getDouble("temp_min");
        double maxTemp = obj.getJSONObject("main").getDouble("temp_max");
        int humidity = obj.getJSONObject("main").getInt("humidity");
        JSONArray weather = obj.getJSONArray("weather");
        int windDir = obj.getJSONObject("wind").getInt("deg");
        double windSpeed = obj.getJSONObject("wind").getDouble("speed");
        int visibility = obj.getInt("visibility");
        JSONArray forecastList = forecast.getJSONArray("list");
        DisplayPreview displayPreview = null;
        try {
            displayPreview = new DisplayPreview(name, currentTemp, minTemp, maxTemp, humidity, windDir, windSpeed, visibility, forecastList, weather);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return displayPreview;
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

    public void setObj(JSONObject obj) {this.obj = obj;}

    public void setForecast(JSONObject forecast) {this.forecast = forecast;}
}
