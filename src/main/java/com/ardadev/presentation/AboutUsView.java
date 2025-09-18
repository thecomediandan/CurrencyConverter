package com.ardadev.presentation;

import com.ardadev.config.ImageReader;
import com.ardadev.presentation.component.LabelWithHyperlink;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class AboutUsView extends JDialog {

    public AboutUsView() {
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        header.setBorder(BorderFactory.createEmptyBorder(0,0,25,0));
        header.setBackground(Color.white);
        JLabel version = new JLabel("Version 1.0");
        header.add(new JLabel(new ImageIcon(Objects.requireNonNull(ImageReader.readLocalImage("assets/CurrencyConverter.png")))), BorderLayout.NORTH);
        header.add(version);

        JPanel body = new JPanel(new BorderLayout());
        body.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));

        JTextArea info = new JTextArea("This program is a currencies converter using the API page https://www.exchangerate-api.com/ and it displays countries' information from the API page https://restcountries.com/. All intended for personal use or informative purposes.\nIf you present connection problems, probably could solve it update the system clock or contact us.");
        info.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setOpaque(false);
        info.setEditable(false);
        info.setFont(new Font(null, Font.PLAIN,11));

        JPanel contactUs = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contactUs.setPreferredSize(new Dimension(200, 110));
        contactUs.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));

        LabelWithHyperlink linkProject = new LabelWithHyperlink("https://github.com/thecomediandan/CurrencyConverter",
                "https://github.com/thecomediandan/CurrencyConverter", 11);
        LabelWithHyperlink linkEmail = new LabelWithHyperlink(new ImageIcon("assets/gmail.png"),"mailto:danqk@outlook.com");
        LabelWithHyperlink linkInstagram = new LabelWithHyperlink(new ImageIcon("assets/instagram.png"),"https://www.instagram.com/dannqk/");
        LabelWithHyperlink linkGitHub = new LabelWithHyperlink(new ImageIcon("assets/github.png"),"https://github.com/thecomediandan");
        linkGitHub.setText("    ");
        LabelWithHyperlink linkApiCurrencyConverter = new LabelWithHyperlink("https://www.exchangerate-api.com/",
                "https://www.exchangerate-api.com/", 11);
        LabelWithHyperlink linkApiCountries = new LabelWithHyperlink("https://restcountries.com/",
                "https://restcountries.com/", 11);

        contactUs.add(new JLabel("Source code: "));
        contactUs.add(linkProject);
        contactUs.add(new JLabel("Contact us:    "));
        contactUs.add(linkEmail);
        contactUs.add(linkInstagram);
        contactUs.add(linkGitHub);
        contactUs.add(new JLabel("APIs: "));
        contactUs.add(linkApiCurrencyConverter);
        contactUs.add(new JLabel(", "));
        contactUs.add(linkApiCountries);


        body.add(info, BorderLayout.CENTER);
        body.add(contactUs, BorderLayout.SOUTH);


        JLabel footer = new JLabel("© 2025 ArdaDev, All rights reserved.");
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));


        setResizable(false);
        setTitle("About Us");
        setModal(true);
        setIconImage(ImageReader.readLocalImage("assets/favicon.png"));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(0,0,280, 480);
        setLocationRelativeTo(null);

        rootPanel.add(header, BorderLayout.NORTH);
        rootPanel.add(body, BorderLayout.CENTER);
        rootPanel.add(footer, BorderLayout.SOUTH);
        setContentPane(rootPanel);
    }

    public void showAboutUsView() {
        setVisible(true);
    }

}
