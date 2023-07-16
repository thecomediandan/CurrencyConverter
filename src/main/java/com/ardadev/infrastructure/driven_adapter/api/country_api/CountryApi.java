package com.ardadev.infrastructure.driven_adapter.api.country_api;

import com.ardadev.domain.entities.country.Country;
import com.ardadev.domain.entities.country.gateway.CountryGateway;
import com.ardadev.infrastructure.driven_adapter.api.connection.ConnectionApi;

import java.util.List;

public class CountryApi implements CountryGateway {
    @Override
    public List<Country> getCountry(String code) {
        String json = null;
        try {
            json = ConnectionApi.connectionCountry(code);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Country().listCountryFromJSON(json);
    }
}
