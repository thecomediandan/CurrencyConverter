package com.ardadev.infrastructure.driven_adapter.api.currency_converted_api;

import com.ardadev.domain.entities.currency_converted.CurrencyConverted;
import com.ardadev.domain.entities.currency_converted.gateway.CurrencyConvertedGateway;
import com.ardadev.infrastructure.driven_adapter.api.connection.ConnectionApi;

public class CurrencyConvertedApi implements CurrencyConvertedGateway {

    @Override
    public CurrencyConverted getCurrencyConverted(String currency) {
        String json = null;
        try {
            json = ConnectionApi.connectionCurrencyConverted(currency);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CurrencyConverted().CurrencyConverted_fromJSON(json);
    }
}
