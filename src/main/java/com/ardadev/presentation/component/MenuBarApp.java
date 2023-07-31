package com.ardadev.presentation.component;

import com.ardadev.config.ImageResizer;
import com.ardadev.presentation.AboutUsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class MenuBarApp extends JMenuBar {
    public MenuBarApp(CardLayout mainCardLayout, JPanel mainBody, JProgressBar progressBarLoadConsults) {
        JProgressBar jProgressBar = progressBarLoadConsults;
        //Where the GUI is created:
        JMenu menu, submenu;
        JMenuItem menuItem;
        JMenuItem homeItem, currencyConverterItem, unitsConverterItem, exitItem, internetTestItem, apiTestItem, aboutUsItem;
        /*
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;
        */
        //Build the first menu.
        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);
        menu.getAccessibleContext().setAccessibleDescription(
                "The menu content all the program views.");
        add(menu);

        //a group of JMenuItems
        homeItem = new JMenuItem("Home",
                KeyEvent.VK_H);
        homeItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_H, InputEvent.ALT_MASK));
        homeItem.getAccessibleContext().setAccessibleDescription(
                "This call the home view.");
        homeItem.setIcon(new ImageIcon("assets/view.png"));
        menu.add(homeItem);
        homeItem.addActionListener((e) -> {
            mainCardLayout.show(mainBody, "Welcome");
            jProgressBar.setVisible(false);
        });

        currencyConverterItem = new JMenuItem("Currency Converter",
                KeyEvent.VK_C);
        currencyConverterItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, InputEvent.ALT_MASK));
        currencyConverterItem.getAccessibleContext().setAccessibleDescription(
                "This call the Currency Converter view.");
        currencyConverterItem.setIcon(new ImageIcon("assets/view.png"));
        menu.add(currencyConverterItem);
        currencyConverterItem.addActionListener((e) -> {
            mainCardLayout.show(mainBody, "Currency Converter");
            progressBarLoadConsults.setVisible(true);
        });

        unitsConverterItem = new JMenuItem("Units Converter",
                KeyEvent.VK_U);
        unitsConverterItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_U, InputEvent.ALT_MASK));
        unitsConverterItem.getAccessibleContext().setAccessibleDescription(
                "This call the Units Converter view.");
        unitsConverterItem.setIcon(new ImageIcon("assets/view.png"));
        // The item of Units Converter is disabled for it's in develop progress
        unitsConverterItem.setEnabled(false);
        menu.add(unitsConverterItem);
        unitsConverterItem.addActionListener((e) -> {
            mainCardLayout.show(mainBody, "Units Converter");
            progressBarLoadConsults.setVisible(false);
        });

        menu.addSeparator();
        exitItem = new JMenuItem("Exit",
                KeyEvent.VK_E);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E, InputEvent.ALT_MASK));
        exitItem.getAccessibleContext().setAccessibleDescription(
                "This exit out the program.");
        exitItem.setIcon(new ImageIcon("assets/exit.png"));
        menu.add(exitItem);
        exitItem.addActionListener((e) -> System.exit(1));

        //a group of radio button menu items
        /*
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        */
        //a group of check box menu items
        /*
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        menu.add(cbMenuItem);
        */

        //Build second menu in the menu bar.
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu content help for connection testing and the program information.");
        add(menu);

        //Add a submenu
        submenu = new JMenu("Test");
        submenu.setMnemonic(KeyEvent.VK_T);

        internetTestItem = new JMenuItem("Connection internet test", KeyEvent.VK_O);
        internetTestItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, InputEvent.ALT_MASK));
        internetTestItem.setIcon(new ImageIcon("assets/test.png"));
        submenu.add(internetTestItem);

        apiTestItem = new JMenuItem("Connection API test", KeyEvent.VK_P);
        apiTestItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, InputEvent.ALT_MASK));
        apiTestItem.setIcon(new ImageIcon("assets/test.png"));
        submenu.add(apiTestItem);
        menu.add(submenu);

        //Add another item
        menu.addSeparator();
        aboutUsItem = new JMenuItem("About Us", KeyEvent.VK_B);
        aboutUsItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_B, InputEvent.ALT_MASK));
        aboutUsItem.setIcon(new ImageIcon("assets/us.png"));
        menu.add(aboutUsItem);
        aboutUsItem.addActionListener((event) -> new AboutUsView().showAboutUsView());
    }
}
