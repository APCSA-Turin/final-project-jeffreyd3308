package com.example;
import org.json.JSONArray;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
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
    private JPanel basicUI;
    private JPanel temperaturePredUI;

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

        setSize(new Dimension(300, 600));
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

        this.basicUI = new GradientPanel(Color.DARK_GRAY, Color.BLACK, null);
        basicUI.setLayout(new BoxLayout(basicUI, BoxLayout.Y_AXIS));
        basicUI.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        basicUI.setPreferredSize(new Dimension(250, 200));
        Font basicFont = new Font("Arial", Font.BOLD, 24);
        Font basicFont2 = new Font("Arial", Font.PLAIN, 16);
        JLabel cityName = new JLabel(name);
        cityName.setForeground(Color.WHITE);
        cityName.setFont(basicFont);
        cityName.setBorder(new EmptyBorder(15, 0, 15, 0));
        cityName.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel temperature = new JLabel(Double.toString(currentTemp) + "F°");
        temperature.setForeground(Color.WHITE);
        temperature.setFont(basicFont);
        temperature.setBorder(new EmptyBorder(15, 50, 15, 50));
        temperature.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel cityDescription = new JLabel(description);
        cityDescription.setForeground(Color.WHITE);
        cityDescription.setFont(basicFont2);
        cityDescription.setBorder(new EmptyBorder(5, 50, 5, 50));
        cityDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel highAndLow = new GradientPanel(new Color(236, 152, 0, 255), new Color(69, 69, 245, 255), new BorderLayout());
        highAndLow.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        highAndLow.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel highTempText = new JLabel("High: " + Double.toString(maxTemp) + "F°");
        highTempText.setForeground(Color.BLACK);
        highTempText.setBorder(new EmptyBorder(0, 20, 0, 0));
        JLabel lowTempText = new JLabel("Low: " + Double.toString(minTemp) + "F°");
        lowTempText.setForeground(Color.BLACK);
        lowTempText.setBorder(new EmptyBorder(0, 0, 0, 20));
        highAndLow.add(highTempText, BorderLayout.WEST);
        highAndLow.add(lowTempText, BorderLayout.EAST);
        basicUI.add(cityName, BorderLayout.NORTH);
        basicUI.add(temperature, BorderLayout.NORTH);
        basicUI.add(cityDescription, BorderLayout.NORTH);
        basicUI.add(highAndLow, BorderLayout.SOUTH);

        displayGUI.add(basicUI);

        temperaturePredUI = new GradientPanel(Color.BLUE, Color.CYAN, new FlowLayout());
        temperaturePredUI.setPreferredSize(new Dimension(250, 160));
        temperaturePredUI.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        temperaturePredUI.setOpaque(false);
        JPanel hourlyTemp = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        hourlyTemp.setPreferredSize(new Dimension(250, 125));
        hourlyTemp.setOpaque(false);
        ArrayList<JPanel> predictions = createTempPredictions(forecast);
        for (int i = 0; i < predictions.size(); i++) {
            hourlyTemp.add(predictions.get(i));
        }
        temperaturePredUI.add(hourlyTemp);
        JPanel averageTemp = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        averageTemp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ArrayList<Double> temperatures = new ArrayList<Double>();
        for (int i = 0; i < forecast.length(); i++) {
            temperatures.add(forecast.getJSONObject(i).getJSONObject("main").getDouble("temp"));
        }
        JLabel averagePrediction = new JLabel("The average temperature over 6 days is " + String.valueOf(calculateTempAverage(temperatures)) + "F°.");
        Font averageFont = new Font("Arial", Font.PLAIN, 12);
        averagePrediction.setFont(averageFont);
        averagePrediction.setForeground(Color.BLACK);
        averageTemp.add(averagePrediction);
        temperaturePredUI.add(averageTemp);
        displayGUI.add(temperaturePredUI);

        JPanel wind = new GradientPanel(Color.GRAY, Color.DARK_GRAY, new FlowLayout(FlowLayout.CENTER, 0, 0));
        wind.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        wind.setPreferredSize(new Dimension(250, 90));
        Font windFont = new Font("Arial", Font.BOLD, 25);
        JPanel windSection = new JPanel(new BorderLayout());
        windSection.setOpaque(false);
        windSection.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        windSection.setPreferredSize(new Dimension(250, 45));
        JLabel windName = new JLabel("Wind:");
        windName.setFont(windFont);
        windName.setForeground(Color.WHITE);
        windName.setBorder(new EmptyBorder(0, 25, 0, 0));
        JLabel speed = new JLabel(windSpeed + "mph");
        speed.setFont(windFont);
        speed.setForeground(Color.WHITE);
        speed.setBorder(new EmptyBorder(0, 0, 0, 25));
        windSection.add(windName, BorderLayout.WEST);
        windSection.add(speed, BorderLayout.EAST);
        wind.add(windSection);
        JPanel windDirectionSection = new JPanel(new BorderLayout());
        windDirectionSection.setOpaque(false);
        windDirectionSection.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        windDirectionSection.setPreferredSize(new Dimension(250, 45));
        JLabel windDirection = new JLabel("Direction:");
        windDirection.setFont(windFont);
        windDirection.setForeground(Color.WHITE);
        windDirection.setBorder(new EmptyBorder(0, 25, 0, 0));
        JLabel windDegree = new JLabel(windDir + "°");
        windDegree.setFont(windFont);
        windDegree.setForeground(Color.WHITE);
        windDegree.setBorder(new EmptyBorder(0, 0, 0, 25));
        windDirectionSection.add(windDirection, BorderLayout.WEST);
        windDirectionSection.add(windDegree, BorderLayout.EAST);
        wind.add(windDirectionSection);
        displayGUI.add(wind);

        JPanel otherInformation = new GradientPanel(Color.BLUE, Color.CYAN, null);
        otherInformation.setLayout(new BoxLayout(otherInformation, BoxLayout.Y_AXIS));
        otherInformation.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        otherInformation.setPreferredSize(new Dimension(250, 75));
        Font otherFont = new Font("Arial", Font.BOLD, 16);
        JLabel weatherVis = new JLabel("Visibility: " + visibility);
        weatherVis.setFont(otherFont);
        weatherVis.setForeground(Color.BLACK);
        weatherVis.setAlignmentX(Component.CENTER_ALIGNMENT);
        weatherVis.setBorder(new EmptyBorder(10, 0, 0, 0));
        otherInformation.add(weatherVis, BorderLayout.NORTH);
        JLabel weatherHum = new JLabel("Humidity: " + humidity);
        weatherHum.setFont(otherFont);
        weatherHum.setForeground(Color.BLACK);
        weatherHum.setAlignmentX(Component.CENTER_ALIGNMENT);
        weatherHum.setBorder(new EmptyBorder(20, 0, 10, 0));
        otherInformation.add(weatherHum, BorderLayout.WEST);

        displayGUI.add(otherInformation);
    }

    public void reload() {
        setVisible(false);
        setVisible(true);
    }

    public static ArrayList<JFrame> getDisplayFrames() {
        return frames;
    }

    public ArrayList<JPanel> createTempPredictions(JSONArray forecast) throws IOException {
        ArrayList<JPanel> returnPanel = new ArrayList<JPanel>();

        for (int i = 0; i < 5; i++) {
            String temperatureIcon = forecast.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon");
            String iconUrl = "https://openweathermap.org/img/wn/" + temperatureIcon + "@2x.png";
            URL url = new URL(iconUrl);
            BufferedImage image = ImageIO.read(url);
            ImageIcon weatherIcon = new ImageIcon(image);
            String time = convertTime(forecast.getJSONObject(i).getString("dt_txt"));
            String predictedTemperature = Double.toString(forecast.getJSONObject(i).getJSONObject("main").getDouble("temp")) + "F°";

            JPanel tempPrediction = new JPanel(null);
            tempPrediction.setLayout(new BoxLayout(tempPrediction, BoxLayout.Y_AXIS));
            tempPrediction.setPreferredSize(new Dimension(48, 125));
            tempPrediction.setBackground(new Color(100, 100, 100, 100));
            tempPrediction.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel imageLabel = new JLabel(weatherIcon);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel timeLabel = new JLabel(time);
            Font font = new Font("Arial", Font.BOLD, 12);
            timeLabel.setFont(font);
            timeLabel.setForeground(Color.BLACK);
            timeLabel.setBorder(new EmptyBorder(5, 0, 2, 0));
            timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel predictedTemperatureLabel = new JLabel(predictedTemperature);
            predictedTemperatureLabel.setForeground(Color.BLACK);
            predictedTemperatureLabel.setFont(font);
            predictedTemperatureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            tempPrediction.add(timeLabel, BorderLayout.NORTH);
            tempPrediction.add(predictedTemperatureLabel, BorderLayout.NORTH);
            tempPrediction.add(imageLabel, BorderLayout.NORTH);

            returnPanel.add(tempPrediction);
        }

        return returnPanel;
    }

    public String convertTime(String time) {
        String hour = time.substring(time.length() - 8, time.length() - 6);
        if (Integer.parseInt(hour) - 5 < 0) {
            hour = Integer.toString(Integer.parseInt(hour) - 5 + 24);
        } else {
            hour = Integer.toString(Integer.parseInt(hour) - 5);
        }
        String timeOfDay = "";
        if (Integer.parseInt(hour) > 12) {
            timeOfDay = "PM";
            hour = Integer.toString(Integer.parseInt(hour) - 12);
        } else {
            timeOfDay = "AM";
        }
        return hour + timeOfDay;
    }

    public int calculateTempAverage(ArrayList<Double> temperatures) {
        double total = 0;
        for (int i = 0; i < temperatures.size(); i++) {
            total += temperatures.get(i);
        }
        return (int) total / temperatures.size();
    }
}
