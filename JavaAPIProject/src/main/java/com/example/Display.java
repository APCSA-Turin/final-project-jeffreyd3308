package com.example;

import javax.swing.*;
import java.awt.*;

public class Display extends GradientPanel {
    private double currentTemp;
    private double minTemp;
    private double maxTemp;
    private int humidity;
    private String description;
    private String icon;
    private String name;
    private int windDir;
    private double windSpeed;
    private int visibility;

    //swing objects
    private JPanel descriptivePanel;
    private JLabel nameText;
    private JLabel descriptionText;

    public Display(String name, double currentTemp, double minTemp, double maxTemp, int humidity, String description, String icon, int windDir, double windSpeed, int visibility) {
        super(Color.BLUE, Color.CYAN, new FlowLayout());
        this.name = name;
        this.currentTemp = currentTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.humidity = humidity;
        this.description = description;
        this.icon = icon;
        this.windDir = windDir;
        this.windSpeed = windSpeed;
        this.visibility = visibility;

        //load
        descriptivePanel = new GradientPanel(Color.darkGray, Color.BLACK, new FlowLayout());
        add(descriptivePanel);
        String iconUrl = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
        ImageIcon image = new ImageIcon(iconUrl);
        nameText = new JLabel(name, image, JLabel.CENTER);
        descriptivePanel.add(nameText);


        descriptivePanel.setVisible(true);
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public int getWindDir() {
        return windDir;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getVisibility() {
        return visibility;
    }
}
