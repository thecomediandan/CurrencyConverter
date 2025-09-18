package com.ardadev.domain.entities.country;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Country implements Comparable<Country>{
    private Map<String, Object> name;
    private Map<String, Map<String, String>> currencies;
    private Map<String, String> flags;
    private String cca2;

    public Country() { }

    public Country countryFromJSON(String json) {
        return new Gson().fromJson(json, Country.class);
    }
    public List<Country> listCountryFromJSON(String json) {
        TypeToken<List<Country>> token = new TypeToken<List<Country>>() {};
        return new Gson().fromJson(json, token.getType());
    }

    public String countryToJSON(Country countryDetails) {
        return new Gson().toJson(countryDetails);
    }


    public Map<String, Map<String, String>> getCurrencies() {
        return currencies;
    }

    public String getCca2() {
        return cca2;
    }
    public String getCommonName() {
        return this.name.get("common").toString();
    }
    public String getOfficialName() {
        return this.name.get("official").toString();
    }
    public String getCurrencyCode() {
        if (this.currencies.isEmpty()) return "N/A";
        return new ArrayList<>(this.currencies.keySet()).get(0);
    }
    public String getCurrencyName() {
        return this.currencies.get(getCurrencyCode()).get("name");
    }
    public String getCurrencySymbol() {
        return this.currencies.get(getCurrencyCode()).get("symbol");
    }
    public String getFlagPng() {
        return this.flags.get("png");
    }
    public String getFlagSvg() {
        return this.flags.get("svg");
    }
    public String getFlagInfo() {
        return this.flags.get("alt");
    }

    @Override
    public String toString() {
        return "Country{" +
                "name=" + name +
                ", currencies=" + currencies +
                ", flags=" + flags +
                '}';
    }

    @Override
    public int compareTo(Country o) {
        return Character.compare(getCommonName().charAt(0), o.getCommonName().charAt(0));
    }
}
