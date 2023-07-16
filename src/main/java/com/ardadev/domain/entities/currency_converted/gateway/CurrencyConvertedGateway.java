package com.ardadev.domain.entities.currency_converted.gateway;

import com.ardadev.domain.entities.currency_converted.CurrencyConverted;

public interface CurrencyConvertedGateway {
    CurrencyConverted getCurrencyConverted(String currency);
}
