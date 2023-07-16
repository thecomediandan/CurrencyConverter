package com.ardadev;

import com.ardadev.domain.entities.country.Country;
import com.ardadev.domain.entities.currency_converted.CurrencyConverted;
import com.ardadev.domain.uses_cases.country.CountryUseCase;
import com.ardadev.domain.uses_cases.currency_converted.CurrencyConvertedUseCase;
import com.ardadev.infrastructure.driven_adapter.api.country_api.CountryApi;
import com.ardadev.infrastructure.driven_adapter.api.currency_converted_api.CurrencyConvertedApi;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args){

        CurrencyConverted cc1 = new CurrencyConvertedUseCase(new CurrencyConvertedApi()).getCurrencyConverted("BOB");
        System.out.println(cc1.getConversion_rates().keySet());
        List<Country> cc = new CountryUseCase(new CountryApi()).getCountry("usd");
        cc.forEach((v) -> System.out.println(v.getCurrencies().get("USD").get("name")));

        JFrame w = new JFrame("Image");
        JPanel p = new JPanel();
        String imageUrl = "https://flagcdn.com/20x15/ss.png";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(imageUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                byte[] imageBytes = EntityUtils.toByteArray(entity);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));

                // Utiliza la imagen (por ejemplo, muestra o procesa)
                w.getContentPane().setBackground(Color.white);
                p.setBackground(Color.white);
                p.add(new JLabel(new ImageIcon(image)));
                w.getContentPane().add(p);
                w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //w.setBounds(0,0,200,200);
                w.pack();
                w.setLocationRelativeTo(null);
                w.setIconImage(image);
                w.setVisible(true);
                System.out.println("Imagen descargada exitosamente.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}