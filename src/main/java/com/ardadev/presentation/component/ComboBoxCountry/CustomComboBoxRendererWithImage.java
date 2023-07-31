package com.ardadev.presentation.component.ComboBoxCountry;

import com.ardadev.domain.entities.country.Country;

import javax.swing.*;
import java.awt.*;

public class CustomComboBoxRendererWithImage extends JLabel implements ListCellRenderer<Country> {
    public CustomComboBoxRendererWithImage() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Country> list, Country value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
            String symbol = " [" + value.getCurrencyCode() +"]  ";
            setText(symbol + value.getCommonName());
            setIcon(new ImageIcon("assets/flags/w20/"+value.getCca2().toLowerCase()+".png"));

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
        }

        return this;
    }
}
