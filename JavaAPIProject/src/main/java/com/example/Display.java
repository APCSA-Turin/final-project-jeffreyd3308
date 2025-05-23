package com.example;
import javax.swing.*;
import java.awt.*;

public class Display extends JFrame{
    public Display(String title) {
        super(title);
        setSize(new Dimension(300, 800));
    }

    public void reload() {
        setVisible(false);
        setVisible(true);
    }
}
