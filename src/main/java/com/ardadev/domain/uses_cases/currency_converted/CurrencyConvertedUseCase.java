package com.ardadev.domain.uses_cases.currency_converted;

import com.ardadev.domain.entities.currency_converted.CurrencyConverted;
import com.ardadev.domain.entities.currency_converted.gateway.CurrencyConvertedGateway;

public class CurrencyConvertedUseCase {
    private final CurrencyConvertedGateway currencyConvertedGateway;

    public CurrencyConvertedUseCase(CurrencyConvertedGateway currencyConvertedGateway) {
        this.currencyConvertedGateway = currencyConvertedGateway;
    }

    public CurrencyConverted getCurrencyConverted(String currency) {
        return this.currencyConvertedGateway.getCurrencyConverted(currency);
    }
}
