package com.example;
//import java.util.Scanner;

import javax.swing.*;

public class Input {
    private JTextField input;

    public Input(String defaultString, int size) {
        input = new JTextField(defaultString, size);
    }

    public Input(int size) {
        input = new JTextField(size);
    }

    public Input() {
        input = new JTextField();
    }

    public JTextField getInput() {
        return input;
    }
}
