package com.ardadev.presentation.component;

import com.ardadev.config.InitialTasks;

import javax.swing.*;

public class ProgressBarStartApp extends JProgressBar {
    private final InitialTasks initialTasks;
    private final JLabel info;
    private Boolean isTerminatedTasks;
    public ProgressBarStartApp() {
        this.isTerminatedTasks = false;
        this.initialTasks = new InitialTasks();
        this.info = new JLabel("Loading tasks...");
        setMinimum(0);
        setMaximum(100);
        setStringPainted(true);
    }

    public void executeTasks() {
        Thread backgroundThread = new Thread(() -> {
            for (int i = 1; i <= initialTasks.getTaskList().size(); i++) {
                double percentage = (((double) i / initialTasks.getTaskList().size()) * 100);
                initialTasks.getTaskList().get(i - 1).run();

                int finalI = i;
                SwingUtilities.invokeLater(() -> {
                    setValue((int)percentage);
                    info.setText(initialTasks.getResults().get(finalI - 1).get("msg").toString());
                    System.out.println(initialTasks.getResults().get(finalI - 1).get("msg").toString());
                });
                if (i <= 3) {
                    if (!((Boolean)initialTasks.getResults().get(i - 1).get("result"))) {
                        this.isTerminatedTasks = true;
                        JOptionPane.showConfirmDialog(null,
                            initialTasks.getResults().get(finalI - 1).get("msg").toString(),
                            "Error",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.ERROR_MESSAGE);
                        System.exit(1);
                        break;
                }}
            };
            this.isTerminatedTasks = true;
        });
        backgroundThread.start();
    }

    public Boolean getTerminatedTasks() {
        return isTerminatedTasks;
    }

    public InitialTasks getInitialTasks() {
        return this.initialTasks;
    }

    public JLabel getInfo() {
        return this.info;
    }
}
