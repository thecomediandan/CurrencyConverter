package com.ardadev.app;

import com.ardadev.presentation.AppView;
import com.ardadev.presentation.LoadingView;

import javax.swing.*;
public class App {
    public App() {
        SwingUtilities.invokeLater(() -> {
            LoadingView loadingView = new LoadingView();
            loadingView.showLoading();
            invokeHomeView(loadingView);
        });
    }

    private void invokeHomeView(LoadingView permissionView) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(300);
                    if (permissionView.getProgressBar().getTerminatedTasks() &&
                            !permissionView.isVisible()) {
                        AppView homeView = new AppView(permissionView.getProgressBar().getInitialTasks());
                        homeView.showApp();
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
