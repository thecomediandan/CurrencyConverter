package com.ardadev.domain.uses_cases.country;

import com.ardadev.domain.entities.country.Country;
import com.ardadev.domain.entities.country.gateway.CountryGateway;

import java.util.List;

public class CountryUseCase {
    private final CountryGateway countryGateway;

    public CountryUseCase(CountryGateway countryGateway) {
        this.countryGateway = countryGateway;
    }

    public List<Country> getListCountries(String currency) {
        return this.countryGateway.getListCountries(currency);
    }
    public Country getCountry(String code) { return this.countryGateway.getCountry(code); }


}
