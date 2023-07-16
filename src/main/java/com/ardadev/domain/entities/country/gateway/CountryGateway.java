package com.ardadev.domain.entities.country.gateway;

import com.ardadev.domain.entities.country.Country;

import java.util.List;

public interface CountryGateway {
    List<Country> getCountry(String code);
}
