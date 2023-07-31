package com.ardadev.domain.entities.currency_converted;
import com.google.gson.Gson;

import java.util.Map;

public class CurrencyConverted {
    private String result;
    private String base_code;
    private String time_last_update_utc;
    private Map<String, Double> conversion_rates;
    Gson gson;
    public CurrencyConverted() { this.gson = new Gson(); }

    public CurrencyConverted CurrencyConverted_fromJSON(String json) {
        return this.gson.fromJson(json, CurrencyConverted.class);
    }

    public String CurrencyConverted_toJSON(CurrencyConverted currencyConverted) {
        return this.gson.toJson(currencyConverted);
    }

    public String getTime_last_update_utc() {
        return time_last_update_utc.substring(0, 16);
    }
    public String getResult() {
        return result;
    }

    public String getBase_code() {
        return base_code;
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }

    @Override
    public String toString() {
        return "CurrencyConverted{" +
                "result='" + result + '\'' +
                ", base_code='" + base_code + '\'' +
                ", conversion_rates=" + conversion_rates +
                '}';
    }
}
