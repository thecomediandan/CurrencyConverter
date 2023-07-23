package com.ardadev;

import com.ardadev.domain.entities.country.Country;
import com.ardadev.domain.uses_cases.country.CountryUseCase;
import com.ardadev.infrastructure.driven_adapter.api.connection.TestConnection;
import com.ardadev.infrastructure.driven_adapter.api.country_api.CountryApi;
import com.ardadev.presentation.LoadingView;

public class Main {
    public static void main(String[] args){
        //LoadingView carga = new LoadingView();
        //carga.setup();
        Country country = new CountryUseCase(new CountryApi()).getCountry("us");
        System.out.println(country);
    }
}