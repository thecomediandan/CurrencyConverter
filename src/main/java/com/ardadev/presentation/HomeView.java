package com.ardadev.presentation;

import com.ardadev.config.InitialTasks;
import com.ardadev.domain.entities.country.Country;
import com.ardadev.domain.entities.currency_converted.CurrencyConverted;
import com.ardadev.domain.uses_cases.country.CountryUseCase;
import com.ardadev.domain.uses_cases.currency_converted.CurrencyConvertedUseCase;
import com.ardadev.infrastructure.driven_adapter.api.country_api.CountryApi;
import com.ardadev.infrastructure.driven_adapter.api.currency_converted_api.CurrencyConvertedApi;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JFrame {

    public HomeView(InitialTasks initialTasks) {
        Country localCountry = (Country) initialTasks.getResults().get(3).get("result");
        CurrencyConverted localCurrencyConverted = (CurrencyConverted) initialTasks.getResults().get(4).get("result");
        if (localCountry == null ||
            localCurrencyConverted == null) {
            localCountry = new CountryUseCase(new CountryApi()).getCountry("us");
            localCurrencyConverted = new CurrencyConvertedUseCase(new CurrencyConvertedApi()).getCurrencyConverted("USD");
        }
        System.out.println(localCountry.getCommonName());
        System.out.println(localCountry.getOfficialName());
        System.out.println(localCountry.getCurrencyCode());
        System.out.println(localCountry.getCurrencyName());
        System.out.println(localCountry.getCurrencySymbol());
        System.out.println(localCountry.getFlagPng());
        System.out.println(localCountry.getFlagSvg());
        System.out.println(localCountry.getFlagInfo());
        System.out.println(localCurrencyConverted);

        JPanel jPanel = new JPanel(new BorderLayout());

        jPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        setResizable(false);
        setTitle("Currency Converted");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0,0,500, 500);
        setLocationRelativeTo(null);

        jPanel.add(new JLabel("Currency Coverted: " + localCountry.getOfficialName()), BorderLayout.NORTH);
        jPanel.add(new JLabel("arda.dev 2023"), BorderLayout.SOUTH);
        add(jPanel);
        jPanel.getComponent(0).setEnabled(false);
    }

    public void showLoading() {
        setVisible(true);
    }


}
