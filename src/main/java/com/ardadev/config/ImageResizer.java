package com.ardadev.config;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
//import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import javax.swing.JOptionPane;

public class ImageResizer {

    /**
     * 'resizeByWidthURL' should be into parameters of object ImageIcon for working on a component (Example: JLabel).
     * Note: Will probably be necessary use the method 'Objects.requireNonNull(ImageResizer.resizeByWidthURL())'
     * for sure objects not null.
     * @param imageURL URL for the networking
     * @param newWidth Width based on pixels
     * @return Object type BufferedImage
     */
    public static BufferedImage resizeByWidthURL(String imageURL, int newWidth) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(URI.create(imageURL));
            try (InputStream inputStream = httpClient.execute(httpGet).getEntity().getContent()) {
                BufferedImage originalImage = ImageIO.read(inputStream);

                // Resizing image
                int originalWidth = originalImage.getWidth();
                int originalHeight = originalImage.getHeight();
                int newHeight = (int) ((double) newWidth / originalWidth * originalHeight);

                BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = resizedImage.createGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
                g2d.dispose();

                /* Save image resized
                String outputImagePath = "ruta/de/la/image_resized.jpg";
                ImageIO.write(resizedImage, "jpg", new File(outputImagePath));
                */
                return resizedImage;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 'resizeByWidthAndHeightURL' should be into parameters of object ImageIcon for working on a component (Example: JLabel).
     * Note: Will probably be necessary use the method 'Objects.requireNonNull(ImageResizer.resizeByWidthAndHeightURL())'
     * for sure objects not null.
     * @param imageURL URL for the networking
     * @param newWidth Width based on pixels
     * @param newHeight Height based on pixels
     * @return Object type BufferedImage
     */
    public static BufferedImage resizeByWidthAndHeightURL(String imageURL, int newWidth, int newHeight) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(URI.create(imageURL));
            try (InputStream inputStream = httpClient.execute(httpGet).getEntity().getContent()) {
                BufferedImage originalImage = ImageIO.read(inputStream);

                BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = resizedImage.createGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
                g2d.dispose();

                return resizedImage;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 'resizedByWidthLocal' should be into parameters of object ImageIcon for working on a component (Example: JLabel).
     * Note: Will probably be necessary use the method 'Objects.requireNonNull(ImageResizer.resizedByWidthLocal())'
     * for sure objects not null.
     * @param imagePath URL for the image path
     * @param newWidth Width based on pixels
     * @return Object type BufferedImage
     */
//    public static BufferedImage resizedByWidthLocal(String imagePath, int newWidth) {
//        try {
//            BufferedImage originalImage = ImageIO.read(new File(imagePath));
//
//            int originalWidth = originalImage.getWidth();
//            int originalHeight = originalImage.getHeight();
//            int newHeight = (int) ((double) newWidth / originalWidth * originalHeight);
//
//            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g2d = resizedImage.createGraphics();
//            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//            g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
//            g2d.dispose();
//
//            return resizedImage;
//        }catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    
    public static BufferedImage resizedByWidthLocal(String resourcePath, int newWidth) {
        try (InputStream is = ImageReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("No se encontró el recurso: " + resourcePath);
            }

            BufferedImage originalImage = ImageIO.read(is);
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int newHeight = (int) ((double) newWidth / originalWidth * originalHeight);

            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g2d.dispose();

            return resizedImage;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "[assets] Resource not found: " + resourcePath,
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
            return null;
        }
    }


    /**
     * 'resizedByWidthAndHeightLocal' should be into parameters of object ImageIcon for working on a component (Example: JLabel).
     * Note: Will probably be necessary use the method 'Objects.requireNonNull(ImageResizer.resizedByWidthAndHeightLocal())'
     * for sure objects not null.
     * @param imagePath URL for the image path
     * @param newWidth Width based on pixels
     * @param newHeight Height based on pixels
     * @return Object type BufferedImage
     */
//    public static BufferedImage resizedByWidthAndHeightLocal(String imagePath, int newWidth, int newHeight) {
//        try {
//            BufferedImage originalImage = ImageIO.read(new File(imagePath));
//
//            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g2d = resizedImage.createGraphics();
//            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//            g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
//            g2d.dispose();
//
//            return resizedImage;
//        }catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    public static BufferedImage resizedByWidthAndHeightLocal(String resourcePath, int newWidth, int newHeight) {
        try (InputStream is = ImageReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("No se encontró el recurso: " + resourcePath);
            }

            BufferedImage originalImage = ImageIO.read(is);
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

            Graphics2D g2d = resizedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g2d.dispose();

            return resizedImage;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "[assets] Resource not found: " + resourcePath,
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
            return null;
        }
    }
}
