package com.ardadev;

import com.ardadev.presentation.AppView;

import javax.swing.*;

public class EasyTests {
    public static void main(String[] args) {
            //new App();
        SwingUtilities.invokeLater(() -> new AppView().showApp());
        //SwingUtilities.invokeLater(() -> new AboutUsView().showAboutUsView());
    }
}
