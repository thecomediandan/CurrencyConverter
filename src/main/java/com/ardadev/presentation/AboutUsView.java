package com.ardadev.presentation;

import com.ardadev.config.ImageReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class AboutUsView extends JDialog {

    public AboutUsView() {
        JPanel rootPanel = new JPanel(new BorderLayout());

        JLabel footer = new JLabel("© 2023 ArdaDev, All rights reserved.");
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));

        rootPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        setResizable(false);
        setTitle("About Us");
        setModal(true);
        setIconImage(ImageReader.readLocalImage("assets/favicon.png"));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(0,0,300, 350);
        setLocationRelativeTo(null);

        rootPanel.add(new JLabel(new ImageIcon(Objects.requireNonNull(ImageReader.readLocalImage("assets/CurrencyConverter.png")))), BorderLayout.NORTH);
        //rootPanel.add(mainBody, BorderLayout.CENTER);
        rootPanel.add(footer, BorderLayout.SOUTH);
        getContentPane().add(rootPanel);
    }

    public void showAboutUsView() {
        setVisible(true);
    }

}
