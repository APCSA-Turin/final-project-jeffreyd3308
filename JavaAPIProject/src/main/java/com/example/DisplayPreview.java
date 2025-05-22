package com.example;

import org.json.JSONArray;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class DisplayPreview extends GradientPanel {
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

    //forecase
    private JSONArray forecast;

    //swing objects
    private JPanel descriptivePanel;
    private JLabel nameText;
    private JLabel descriptionText;
    private JButton openDisplay;

    public DisplayPreview(String name, double currentTemp, double minTemp, double maxTemp, int humidity, String description, String icon, int windDir, double windSpeed, int visibility, JSONArray forecast) throws IOException {
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
        this.forecast = forecast;

        //load PREVIEW, open new window on click More..
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
        openDisplay = new JButton("More..");
        addListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JFrame displayWindow = new Display("Display");
                displayWindow.setSize(new Dimension(300, 800));
                displayWindow.setResizable(false);
                displayWindow.setVisible(true);
            }
        });
        descriptivePanel.add(openDisplay, BorderLayout.SOUTH);
    }

    public void addListener(ActionListener listener) {
        openDisplay.addActionListener(listener);
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

    public JSONArray getForecast() {
        return forecast;
    }
}
