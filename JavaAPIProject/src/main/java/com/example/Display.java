package com.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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

    public Display(String name, double currentTemp, double minTemp, double maxTemp, int humidity, String description, String icon, int windDir, double windSpeed, int visibility) throws IOException {
        super(Color.BLUE, Color.GREEN, new FlowLayout());
        setPreferredSize(new Dimension(300, 100));
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
        //load in different colors depending on weather
        descriptivePanel = new GradientPanel(Color.darkGray, Color.BLACK, new BorderLayout());

        descriptivePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        descriptivePanel.setPreferredSize(new Dimension(250, 90));
        add(descriptivePanel);
        String iconUrl = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
        URL url = new URL(iconUrl);
        BufferedImage image = ImageIO.read(url);
        ImageIcon imageIcon = new ImageIcon(image);
        nameText = new JLabel(name, imageIcon, JLabel.CENTER);
        descriptivePanel.add(nameText, BorderLayout.CENTER);

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
