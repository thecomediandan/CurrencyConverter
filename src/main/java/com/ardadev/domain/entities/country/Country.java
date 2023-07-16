package com.ardadev.domain.entities.country;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class Country {
    private Map<String, Object> name;
    private Map<String, Map<String, String>> currencies;
    private Map<String, String> flags;
    Gson gson;

    public Country() { this.gson = new Gson(); }

    public Country countryFromJSON(String json) {
        return this.gson.fromJson(json, Country.class);
    }
    public List<Country> listCountryFromJSON(String json) {
        TypeToken<List<Country>> token = new TypeToken<List<Country>>() {};
        return this.gson.fromJson(json, token.getType());
    }

    public String countryToJSON(Country countryDetails) {
        return this.gson.toJson(countryDetails);
    }

    public Map<String, Object> getName() {
        return name;
    }

    public Map<String, Map<String, String>> getCurrencies() {
        return currencies;
    }

    public Map<String, String> getFlags() {
        return flags;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name=" + name +
                ", currencies=" + currencies +
                ", flags=" + flags +
                '}';
    }
}
