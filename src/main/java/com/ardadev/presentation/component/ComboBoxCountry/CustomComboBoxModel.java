package com.ardadev.presentation.component.ComboBoxCountry;

import com.ardadev.domain.entities.country.Country;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;
import java.util.Objects;

public class CustomComboBoxModel implements ComboBoxModel<Country> {
    private final List<Country> items;
    private Country selectedItem;

    public CustomComboBoxModel(List<Country> items) {
        this.items = items;
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public Country getElementAt(int index) {
        return items.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (Country) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }
    @Override
    public void removeListDataListener(ListDataListener l) {

    }
    public int getIndexCountry(Country country) {
        for (int i = 0; i < items.size(); i++) {
            String countryCode = items.get(i).getCca2();
            if (Objects.equals(countryCode, country.getCca2())) return i;
        }
        return -1;
    }
}
