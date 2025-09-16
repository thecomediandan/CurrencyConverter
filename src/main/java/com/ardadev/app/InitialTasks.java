package com.ardadev.app;

import com.ardadev.domain.entities.country.Country;
import com.ardadev.domain.entities.currency_converted.CurrencyConverted;
import com.ardadev.domain.uses_cases.country.CountryUseCase;
import com.ardadev.domain.uses_cases.currency_converted.CurrencyConvertedUseCase;
import com.ardadev.infrastructure.driven_adapter.api.connection.TestConnection;
import com.ardadev.infrastructure.driven_adapter.api.country_api.CountryApi;
import com.ardadev.infrastructure.driven_adapter.api.currency_converted_api.CurrencyConvertedApi;
import com.ardadev.infrastructure.helpers.CountryApiHelper;
import com.ardadev.infrastructure.helpers.CurrencyConvertedApiHelper;

import java.util.*;

public class InitialTasks {
    private final List<Runnable> taskList;
    private final List<Map<String, Object>> results;

    public InitialTasks() {
        this.taskList = new ArrayList<>();
        this.results = new ArrayList<>();

        // Task: Connecting to Internet
        taskList.add(() -> {
            this.results.add(new SimpleTask().TaskConnection(
                    "https://www.google.com",
                    "Connecting to Internet successfully...",
                    "Error connecting to internet..."));
        });

        // Task: Currencies API connecting
        taskList.add(() -> {
            this.results.add(new SimpleTask().TaskConnection(
                    CurrencyConvertedApiHelper.API_URL+ CurrencyConvertedApiHelper.API_KEY +"/latest/USD",
                    "Connecting to currencies API successfully...",
                    "Error connecting to currencies API..."
            ));
        });

        // Task: Flags API connecting
        taskList.add(() -> {
            this.results.add(new SimpleTask().TaskConnection(
                    CountryApiHelper.API_URL_ALL,
                    "Connecting to flags API successfully...",
                    "Error connecting to flags API..."
            ));
        });

        // Task: Getting local country
        taskList.add(() -> {
            this.results.add(new SimpleTask().TaskGetLocalCountry(
                    "Getting countries successfully...",
                    "Countries not found..."
            ));
        });

        // Task: Getting local currency converted
        taskList.add(() -> {
            String currencyLocal = this.results.get(3).get("currency").toString();
            this.results.add(new SimpleTask().TaskGetLocalCurrencyConverted(
                    "Getting local currency successfully...",
                    "Local currency not found...",
                    currencyLocal
            ));
        });

        // Task: Getting all countries
        taskList.add(() -> {
            this.results.add(new SimpleTask().TaskGetAllCountries(
                    "Getting all countries successfully...",
                    "Countries not found..."
            ));
        });
    }
    private static class SimpleTask {
        public Map<String, Object> TaskConnection(String url, String positiveMsg, String negativeMsg) {
            Map<String, Object> response = new HashMap<>();

            Boolean result = TestConnection.TestInternet(url);
            if (result) {
                response.put("msg", positiveMsg);
                response.put("result", true);
            } else {
                response.put("msg", negativeMsg);
                response.put("result", false);
            }
            return response;
        }

        public Map<String, Object> TaskGetLocalCurrencyConverted(String positiveMsg, String negativeMsg, String currencyLocal) {
            Map<String, Object> response = new HashMap<>();

            CurrencyConverted result = new CurrencyConvertedUseCase(
                    new CurrencyConvertedApi())
                    .getCurrencyConverted(currencyLocal);
            if (result != null) {
                response.put("msg", positiveMsg);
            } else {
                response.put("msg", negativeMsg);
            }
            response.put("result", result);

            return response;
        }

        public Map<String, Object> TaskGetLocalCountry(String positiveMsg, String negativeMsg) {
            Map<String, Object> response = new HashMap<>();
            String currencyLocal = "USD";
            Country result = new CountryUseCase(
                    new CountryApi())
                    .getCountry(System.getProperty("user.country").toLowerCase());
            if (result != null) {
                response.put("msg", positiveMsg);
                currencyLocal = result.getCurrencyCode();
            } else {
                response.put("msg",negativeMsg);
            }
            response.put("result", result);
            response.put("currency", currencyLocal);

            return response;
        }

        public Map<String, Object> TaskGetAllCountries(String positiveMsg, String negativeMsg) {
            Map<String, Object> response = new HashMap<>();
            List<Country> result = new ArrayList<>(
                    new CountryUseCase(new CountryApi()).getListAllCountries()
            );
            //System.out.println("Cantidad de paises: " + result.size());
            if (!result.isEmpty()) {
                response.put("msg", positiveMsg);
            } else {
                response.put("msg",negativeMsg);
            }
            response.put("result", result);

            return response;
        }
    }

    // 'getLocalCountry' and 'getLocalCurrencyConverted' will only provide results if 'taskList' is executed for each task.
    // This is made for the constructor.
    public Country getLocalCountry() {
        return (Country)this.results.get(3).get("result");
    }

    public CurrencyConverted getLocalCurrencyConverted() {
        return (CurrencyConverted)this.results.get(4).get("result");
    }

    public List<Runnable> getTaskList() {
        return taskList;
    }

    public List<Map<String, Object>> getResults() {
        return results;
    }
}
