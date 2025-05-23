package com.example;

import java.util.ArrayList;
import org.json.JSONObject;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        //setup
        JFrame mainframe = new JFrame("Weather App");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setResizable(false);
        mainframe.setSize(new Dimension(500, 400));
        JPanel gui = new GradientPanel(new Color(75, 0, 250, 100), new Color(75,0,250,200), new FlowLayout());
        mainframe.setContentPane(gui);

        //input setup
        Input inputArea = new Input();
        inputArea.setPreferredSize(new Dimension(400, 100));
        inputArea.getText().setPreferredSize(new Dimension(400, 50));
        inputArea.getText().setHorizontalAlignment(SwingConstants.CENTER);
        Font font = new Font("Arial", Font.BOLD, 25);
        inputArea.getText().setFont(font);
        inputArea.addListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean valid = false;
                //remove all instances of displaypreview
                for (int i = 0; i < gui.getComponentCount(); i++) {
                    if (gui.getComponent(i) instanceof DisplayPreview) {
                        gui.remove(i);
                    }
                }
                DisplayPreview displayPreview = null;
                String city = cityString(inputArea.getInput().getText());
                //weather
                String endpoint = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=imperial&APPID=41b37103b67fe0f874e4a4a93ac37cdf";
                String data = null;
                //forecast
                String endpointF = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&units=imperial&APPID=41b37103b67fe0f874e4a4a93ac37cdf";
                String dataF = null;
                try {
                    data = API.getData(endpoint);
                    dataF = API.getData(endpointF);
                    valid = true;
                } catch (Exception ex) {
                    inputArea.getText().setText("Not a valid city name.");
                }
                if (valid) {
                    inputArea.getText().setText("Enter a city name.");
                    JSONObject obj = new JSONObject(data);
                    System.out.println(obj);
                    System.out.println(obj.getJSONObject("main").getInt("humidity"));
                    inputArea.setObj(obj);
                    //forecast
                    JSONObject forecast = new JSONObject(dataF);
                    System.out.println(forecast);
                    inputArea.setForecast(forecast);
                    //create display and add display
                    displayPreview = inputArea.createDisplay();
                    displayPreview.setPreferredSize(new Dimension(400, 200));
                    gui.add(displayPreview);
                    mainframe.setVisible(false);
                    mainframe.setVisible(true);
                }
            }
        });
        gui.add(inputArea);
        mainframe.setVisible(true);
    }

    public static String cityString(String city) {
        for (int i = 0; i < city.length(); i++) {//make string readable by api
            if (city.substring(i, i+1).equals(" ")) {
                city = city.substring(0, i) + "+" + city.substring(i+1);
            }
        }
        int removalIndex = city.length();
        for (int i = city.length() - 1; i >= 0; i--) {//remove plus at end
            if (!city.substring(i, i+1).equals("+")) {
                removalIndex = i + 1;
                break;
            }
        }

        city = city.substring(0, removalIndex);
        return city;
    }
}
