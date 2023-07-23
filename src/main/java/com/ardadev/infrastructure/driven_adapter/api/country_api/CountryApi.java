package com.ardadev.infrastructure.driven_adapter.api.country_api;

import com.ardadev.domain.entities.country.Country;
import com.ardadev.domain.entities.country.gateway.CountryGateway;
import com.ardadev.infrastructure.driven_adapter.api.connection.ConnectionApi;

import java.util.List;

public class CountryApi implements CountryGateway {
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
            return null;
        }
        return new Country().countryFromJSON(json);
    }
}
