package com.example;
//import java.util.Scanner;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Input {
    private JTextField input;
    private JButton submitter;
    private JLabel text;
    private JPanel panel;

    public Input(String defaultString, int size) {
        panel = new GradientPanel(Color.BLUE, Color.CYAN, new FlowLayout());
        panel.setPreferredSize(new Dimension(300, 100));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        text = new JLabel(defaultString);
        input = new JTextField(size);
        submitter = new JButton("Submit");
        submitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = input.getText();
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
            }
        });
        panel.add(text);
        panel.add(input);
        panel.add(submitter);
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

    public JPanel getPanel() {
        return panel;
    }
}
