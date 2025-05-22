package com.example;

import org.json.JSONObject;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

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
                String city = inputArea.getInput().getText();
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
                    gui.add(displayPreview);
                    mainframe.setVisible(false);
                    mainframe.setVisible(true);
                }
            }
        });
        gui.add(inputArea);
        mainframe.setVisible(true);
    }
}
