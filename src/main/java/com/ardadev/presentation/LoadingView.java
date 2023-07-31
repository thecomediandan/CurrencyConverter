package com.ardadev.presentation;

import com.ardadev.config.ImageReader;
import com.ardadev.presentation.component.ProgressBarStartApp;

import javax.swing.*;
import java.awt.*;

public class LoadingView extends JFrame {
    private final ProgressBarStartApp progressBar;
    public LoadingView() {
        JPanel jPanel = new JPanel();
        this.progressBar = new ProgressBarStartApp();
        JLabel info = progressBar.getInfo();

        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        info.setBorder(BorderFactory.createEmptyBorder(10,0,15,0));
        jPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        setResizable(false);
        setLayout(new BorderLayout());
        setTitle("Loading...");
        setIconImage(ImageReader.readLocalImage("assets/favicon.png"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0,0,300, 125);
        setLocationRelativeTo(null);

        jPanel.add(info);
        jPanel.add(progressBar);
        add(jPanel, BorderLayout.CENTER);

        progressBar.executeTasks();

    }

    public void showLoading() {
        setVisible(true);
        observeTask();
    }

    public ProgressBarStartApp getProgressBar() {
        return this.progressBar;
    }

    public void observeTask() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(300);
                    if (this.progressBar.getTerminatedTasks()) {
                        this.setVisible(false);
                        break;
                    }
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
