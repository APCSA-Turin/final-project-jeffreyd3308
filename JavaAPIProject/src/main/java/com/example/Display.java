package com.example;
import org.json.JSONArray;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Display extends JFrame{
    private static ArrayList<JFrame> frames = new ArrayList<JFrame>(); //all the Display frames
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
    private Color start;
    private Color end;

    //JSON
    private JSONArray forecast;
    private JSONArray weather;

    //frameObj
    private JPanel displayGUI;

    public Display(String name, double currentTemp, double minTemp, double maxTemp, int humidity, int windDir, double windSpeed, int visibility, JSONArray forecast, JSONArray weather) throws IOException {
        super(name);
        this.name = super.getTitle();
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

        addWindowListener(new WindowAdapter() {//remove this from frames when closed to allow a new frame to be opened
            @Override
            public void windowClosing(WindowEvent e) {
                for (int i = 0; i < frames.size(); i++) {
                    if (frames.get(i).getTitle().equals(name)) {
                        frames.remove(i);
                    }
                }
            }
        });

        String iconUrl = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
        URL url = new URL(iconUrl);
        BufferedImage image = ImageIO.read(url);

        setIconImage(image);

        setSize(new Dimension(300, 800));
        setResizable(false);
        frames.add(this);

        if (icon.substring(icon.length() - 1).equals("d")) {
            start = new Color(236, 152, 0, 255);
            end = new Color(119, 65, 15, 208);
        } else if (icon.substring(icon.length() - 1).equals("n")) {
            start = new Color(100, 100, 100, 255);
            end = new Color(0, 0, 0, 255);
        }

        //setup front end and statistics
        this.displayGUI = new GradientPanel(start, end, new FlowLayout());
        add(displayGUI);
    }

    public void reload() {
        setVisible(false);
        setVisible(true);
    }

    public static ArrayList<JFrame> getDisplayFrames() {
        return frames;
    }

    //statistical methods
}
