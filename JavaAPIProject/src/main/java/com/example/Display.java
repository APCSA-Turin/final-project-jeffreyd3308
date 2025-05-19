package com.example;

public class Display {
    private double currentTemp;
    private double minTemp;
    private double maxTemp;
    private double humidity;
    private String description;
    private String icon;
    private String name;
    private int windDir;
    private double windSpeed;
    private int visibility;

    public Display(String name, double currentTemp, double minTemp, double maxTemp, double humidity, String description, String icon, int windDir, double windSpeed, int visibility) {
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

    public double getHumidity() {
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
