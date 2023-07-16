package com.ardadev;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;

public class Prueba1 {
    public static void main(String[] args) {
        String json = "{\"atributo1\":\"valor1\",\"atributo2\":{\"subatributo\":\"valor2\"}}";

        Gson gson = new Gson();
        Type mapType = new com.google.gson.reflect.TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> mapa = gson.fromJson(json, mapType);

        System.out.println(mapa.get("atributo1")); // Imprime: valor1
        mapa.forEach((m,k) -> System.out.println(m.toUpperCase()));
    }
}
