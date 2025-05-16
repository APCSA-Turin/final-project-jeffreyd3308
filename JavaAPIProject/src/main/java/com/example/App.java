package com.example;

import org.json.JSONObject;

import javax.swing.*;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Scanner scan = new Scanner(System.in);
        System.out.println( "goober put in city NOW!" );
        String city = scan.next();

        String endpoint = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=41b37103b67fe0f874e4a4a93ac37cdf";
        String data = API.getData(endpoint);
        JSONObject obj = new JSONObject(data);
        System.out.println(obj);
        System.out.println(obj.getJSONObject("main").getInt("humidity"));

        //logicals
        JFrame mainframe = new JFrame("mainframe");
        mainframe.setResizable(false);
        mainframe.setLayout(null);
        mainframe.setSize(1920, 1080);
        mainframe.setVisible(true);
        Input inputArea = new Input();
//        inputArea.addActionListener(e -> {
//            String text = inputArea.getInput().getText();
//            System.out.println(text);
//        });
        mainframe.add(inputArea.getInput());

        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
