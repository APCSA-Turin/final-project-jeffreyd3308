package com.example;

import java.awt.*;
import javax.swing.*;

public class GradientPanel extends JPanel {
    private Color start;
    private Color end;

    public GradientPanel(Color start, Color end, LayoutManager layout) {
        super(layout);
        this.start = start;
        this.end = end;
    }

    @Override
    protected void paintComponent(Graphics g) { //https://www.tutorialspoint.com/Create-gradient-translucent-windows-in-Java-Swing
        Paint p = new GradientPaint(0.0f, 0.0f, start,
                getWidth(), getHeight(), end, true);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(p);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
