package com.ardadev.infrastructure.driven_adapter.api.country_api;

import com.ardadev.domain.entities.country.Country;
import com.ardadev.domain.entities.country.gateway.CountryGateway;
import com.ardadev.infrastructure.driven_adapter.api.connection.ConnectionApi;

import javax.swing.*;
import java.util.List;

public class CountryApi implements CountryGateway {
    @Override
    public List<Country> getListAllCountries() {
        String json = null;
        try {
            json = ConnectionApi.connectionAllCountries();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showConfirmDialog(null,
                    "Error API Countries connection\n" +
                            "Check your internet connection.",
                    "Error",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return new Country().listCountryFromJSON(json);
    }

    @Override
    public List<Country> getListCountries(String currency) {
        String json = null;
        try {
            json = ConnectionApi.connectionCountryByCurrency(currency);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new Country().listCountryFromJSON(json);
    }

    @Override
    public Country getCountry(String code) {
        String json = null;
        try {
            json = ConnectionApi.connectionCountryByCode(code);
            json = json.substring(1, json.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showConfirmDialog(null,
                    "Error API Countries connection\n" +
                            "Check your internet connection.",
                    "Error",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return new Country().countryFromJSON(json);
    }
}
