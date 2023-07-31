package com.ardadev.infrastructure.driven_adapter.api.currency_converted_api;

import com.ardadev.domain.entities.currency_converted.CurrencyConverted;
import com.ardadev.domain.entities.currency_converted.gateway.CurrencyConvertedGateway;
import com.ardadev.infrastructure.driven_adapter.api.connection.ConnectionApi;

import javax.swing.*;

public class CurrencyConvertedApi implements CurrencyConvertedGateway {

    @Override
    public CurrencyConverted getCurrencyConverted(String currency) {
        String json = null;
        try {
            json = ConnectionApi.connectionCurrencyConverted(currency);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showConfirmDialog(null,
                    "Error API Currency Converted connection\n" +
                            "Check your internet connection.",
                    "Error",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return new CurrencyConverted().CurrencyConverted_fromJSON(json);
    }
}
