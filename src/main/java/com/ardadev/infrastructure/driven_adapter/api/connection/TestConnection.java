package com.ardadev.infrastructure.driven_adapter.api.connection;

import java.net.HttpURLConnection;
import java.net.URL;

public class TestConnection {
    public static Boolean TestInternet(String urlConnection) {
        try {
            URL url = new URL(urlConnection);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
