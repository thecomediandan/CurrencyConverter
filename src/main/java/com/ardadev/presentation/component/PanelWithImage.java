package com.ardadev.presentation.component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelWithImage extends JPanel {

    private final BufferedImage imageBackground;

    public PanelWithImage(BufferedImage imageBackground) {
        this.imageBackground = imageBackground;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuja la imagen de fondo
        if (this.imageBackground != null) {
            int x = (getWidth() - this.imageBackground.getWidth()) / 2;
            int y = (getHeight() - this.imageBackground.getHeight()) / 2;
            g.drawImage(this.imageBackground, x, y, this);
        }
    }
}
