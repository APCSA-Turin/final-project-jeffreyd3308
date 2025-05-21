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
        JFrame mainframe = new JFrame("mainframe");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setResizable(false);
        mainframe.setSize(new Dimension(500, 400));
        JPanel gui = new GradientPanel(new Color(75, 0, 250, 100), new Color(75,0,250,200), new FlowLayout());
        mainframe.setContentPane(gui);

        //input setup
        Input inputArea = new Input("Enter a city name.", 20);
        inputArea.addListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display display = null;
                String city = inputArea.getInput().getText();
                String endpoint = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=imperial&APPID=41b37103b67fe0f874e4a4a93ac37cdf";
                String data = null;
                try {
                    data = API.getData(endpoint);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);//instead of error display not found
                }
                JSONObject obj = new JSONObject(data);
                System.out.println(obj);
                System.out.println(obj.getJSONObject("main").getInt("humidity"));
                inputArea.setObj(obj);
                //create display and add display
                display = inputArea.createDisplay();
                gui.add(display);
                mainframe.setVisible(false);
                mainframe.setVisible(true);
            }
        });
        gui.add(inputArea);
        mainframe.setVisible(true);
    }
}
