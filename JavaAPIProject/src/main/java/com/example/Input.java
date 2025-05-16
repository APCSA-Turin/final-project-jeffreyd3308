package com.example;
//import java.util.Scanner;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.*;

public class Input {
    private JTextField input;
    private JButton submitter;
    private JLabel text;

    public Input(String defaultString, int size) {
        text = new JLabel(defaultString);
        input = new JTextField(size);
        submitter = new JButton("Submit");
        submitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = input.getText();
                String endpoint = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=41b37103b67fe0f874e4a4a93ac37cdf";
                String data = null;
                try {
                    data = API.getData(endpoint);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                JSONObject obj = new JSONObject(data);
                System.out.println(obj);
                System.out.println(obj.getJSONObject("main").getInt("humidity"));
            }
        });
    }

    public JTextField getInput() {
        return input;
    }

    public JButton getSubmitter() {
        return submitter;
    }

    public JLabel getText() {
        return text;
    }
}
