package io;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import javax.imageio.ImageIO;

/**
 * The Class ParsingUtilities.
 * @author Eden Yefet
 */
public final class ParsingUtilities {

    /**
     * Parse color from name and return the specified color.
     * @param s the s
     * @return the color
     */
    // parse color definition and return the specified color.
    public static Color colorFromString(String s) {
        Color color;
        try {
            Field field = Class.forName("java.awt.Color").getField(s);
            color = (Color) field.get(null);
        } catch (Exception e) {
            System.err.println("Undefined Color, please check your input.");
            return null;
        }
        return color;
    }

    /**
     * Parse color from RGB and return the specified color.
     * @param s the s
     * @return the color
     */
    public static Color colorFromStringOfRGB(String s) {
        String[] rgb = s.split(",");
        int r = Integer.valueOf(rgb[0]);
        int g = Integer.valueOf(rgb[1]);
        int b = Integer.valueOf(rgb[2]);

        return new Color(r, g, b);
    }

    /**
     * Gets an image from the path.
     * @param path the path of the image, relative to the resources folder.
     * @return the image
     */
    public static Image getImage(String path) {
        // load the image data into an java.awt.Image object
        InputStream is = null;
        BufferedImage img = null;
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Instantiates a new parsing utilities.
     */
    public ParsingUtilities() {

    }
}
