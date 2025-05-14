package com.example;
import org.json.JSONObject;
import javax.swing.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        // String endpoint = "http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid={b1aed90608b1028bb3c1cd48cd402913}";
        // String data = API.getData(endpoint);
        JFrame mainframe = new JFrame("mainframe");
        mainframe.setLayout(null);
        mainframe.setSize(300, 400);
        mainframe.setVisible(true);
    }
}
