package com.example;

import org.json.JSONArray;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private int id;

    //forecase
    private JSONArray forecast;
    private JSONArray weather;

    //swing objects
    private JPanel descriptivePanel;
    private JLabel nameText;
    private JLabel descriptionText;
    private JButton openDisplay;
    private JLabel tempText;

    public DisplayPreview(String name, double currentTemp, double minTemp, double maxTemp, int humidity, int windDir, double windSpeed, int visibility, JSONArray forecast, JSONArray weather) throws IOException {
        super(Color.DARK_GRAY, Color.BLACK, new FlowLayout());
        setPreferredSize(new Dimension(300, 100));
        this.name = name;
        this.currentTemp = currentTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.humidity = humidity;
        this.description = weather.getJSONObject(0).getString("description");
        this.icon = weather.getJSONObject(0).getString("icon");
        this.windDir = windDir;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
        this.forecast = forecast;
        this.weather = weather;
        this.id = weather.getJSONObject(0).getInt("id");

        //load PREVIEW, open new window on click More..
        String iconUrl = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
        URL url = new URL(iconUrl);
        BufferedImage image = ImageIO.read(url);
        ImageIcon imageIcon = new ImageIcon(image);
        //load in different colors depending on weather and time
        Color start = Color.yellow;
        Color text = Color.black;
        Color end = Color.yellow;
        if (icon.substring(icon.length() - 1).equals("d")) {
            start = new Color(200, 125, 0, 208);
        } else if (icon.substring(icon.length() - 1).equals("n")) {
            start = Color.darkGray;
            text = Color.white;
        }
        if (id > 800) {
            end = Color.lightGray;
        } else if (id == 800) {
            end = Color.white;
        } else if (id > 700) {
            end = Color.gray;
        } else if (id >= 600) {
            end = Color.white;
        } else if (id >= 500) {
            end = Color.darkGray;
            text = Color.white;
        } else if (id >= 300) {
            end = Color.gray;
        } else if (id >= 200) {
            end = Color.black;
            text = Color.white;
        }
        descriptivePanel = new GradientPanel(start, end, new BorderLayout());
        descriptivePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        descriptivePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        descriptivePanel.setPreferredSize(new Dimension(350, 190));
        add(descriptivePanel);

        //setup for displaying information
        String temperature = Double.toString(currentTemp);
        tempText = new JLabel((temperature + " F°"));
        tempText.setBorder(new EmptyBorder(0, 0, 0, 25));
        tempText.setForeground(text);
        Font tempFont = new Font("Arial", Font.BOLD, 25);
        tempText.setFont(tempFont);

        nameText = new JLabel(name, imageIcon, JLabel.CENTER);
        nameText.setBorder(new EmptyBorder(0, 25, 0, 0));
        nameText.setForeground(text);
        Font nameFont = new Font("Arial", Font.BOLD, 15);
        nameText.setFont(nameFont);

        descriptivePanel.add(nameText, BorderLayout.WEST);
        descriptivePanel.add(tempText, BorderLayout.EAST);
        openDisplay = new JButton("More..");
        //if More... is clicked create and open a Display object
        addListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                boolean alreadyIn = false;
                //no duplicates
                for (int i = 0; i < Display.getDisplayFrames().size(); i++) {
                    if (Display.getDisplayFrames().get(i).getTitle().equals(name)) {
                        alreadyIn = true;
                    }
                }
                if (!alreadyIn) {
                    Display displayWindow = null;
                    try {
                        displayWindow = new Display(name, currentTemp, minTemp, maxTemp, humidity, windDir, windSpeed, visibility, forecast, weather);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    displayWindow.reload();
                }
                //save
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
