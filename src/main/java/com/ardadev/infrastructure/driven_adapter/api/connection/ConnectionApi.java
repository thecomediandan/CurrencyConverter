package com.ardadev.infrastructure.driven_adapter.api.connection;

import com.ardadev.infrastructure.driven_adapter.api.connection.error.StatusException;
import com.ardadev.infrastructure.helpers.CountryApiHelper;
import com.ardadev.infrastructure.helpers.CurrencyConvertedApiHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionApi {
    public static String connectionCurrencyConverted(String CURRENCY) throws IOException {
        URL url = new URL(CurrencyConvertedApiHelper.API_URL + CurrencyConvertedApiHelper.API_KEY + "/latest/" + CURRENCY);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);
        /* HEADER SETTINGS
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer your_access_token");
         */
        return connect(connection);
    }
    public static String connectionCountryByCurrency(String currency) throws IOException {
        URL url = new URL(CountryApiHelper.API_URL_CURRENCY + currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(9000);
        /* HEADER SETTINGS
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer your_access_token");
         */
        return connect(connection);
    }

    public static String connectionAllCountries() throws IOException {
        URL url = new URL(CountryApiHelper.API_URL_ALL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);
        /* HEADER SETTINGS
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer your_access_token");
         */
        return connect(connection);
    }

    public static String connectionCountryByCode(String code) throws IOException {
        URL url = new URL(CountryApiHelper.API_URL_CODE + code);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);
        /* HEADER SETTINGS
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer your_access_token");
         */
        return connect(connection);
    }

    private static String connect(HttpURLConnection connection) throws IOException, StatusException{

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String apiResponse = response.toString();
            connection.disconnect();
            return apiResponse;
        } else {
            connection.disconnect();
            throw new StatusException(responseCode);
        }
    }
}
