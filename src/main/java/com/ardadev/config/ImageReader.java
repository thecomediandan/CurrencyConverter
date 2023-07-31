package com.ardadev.config;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class ImageReader {

    public static BufferedImage readLocalImage(String imagePath) {
        try{
            return ImageIO.read(new File(imagePath));
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage readURLImage(String imageURL) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(URI.create(imageURL));
            try (InputStream inputStream = httpClient.execute(httpGet).getEntity().getContent()) {
                BufferedImage readImage = ImageIO.read(inputStream);

                return readImage;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
