/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author roy
 */
public class BlackWhite {
    /**
     * this object handles filtering a raw image
     * @param original
     * @param PicDest
     * @param threshold
     * @param size
     * @param av
     * @return 
     */

    public BufferedImage BW(BufferedImage original, String PicDest, int threshold, int size, double av) {

        BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        try {

            int red;
            int newPixel;
            // int threshold = 130;

            for (int i = 0; i < original.getWidth(); i++) {
                for (int j = 0; j < original.getHeight(); j++) {

                    // Get pixels
                    red = new Color(original.getRGB(i, j)).getRed();

                    int alpha = new Color(original.getRGB(i, j)).getAlpha();
                    newPixel = isWhite(original, i, j, size, av, threshold);
                    newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
                    binarized.setRGB(i, j, newPixel);

                }
            }
            ImageIO.write(binarized, "jpg", new File(PicDest));//"/home/roy/picP/blackwhiteimage2"
        } catch (IOException e) {
            e.printStackTrace();
        }
        return binarized;
    }
    /**
     * method which receives (BufferedImage original, String PicDest, int threshold, int size, double av)
     * BufferedImage original - image to process.
     * String PicDest - String of path for image result saving.
     * int threshold - binary convert threshold (0-255);
     * int size - blur "game of life" value;
     * double av - stands for the required black&white ratio (black/white).
     * 
     * @param alpha
     * @param red
     * @param green
     * @param blue
     * @return <BufferedImage> an 2 color binary image  
     */

    private static int colorToRGB(int alpha, int red, int green, int blue) {
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;
    }
    /**
     * sub static function for BW method
     * calculate the pixel in binary aspect
     * @param original
     * @param i
     * @param j
     * @param size
     * @param av
     * @param threshold
     * @return int newPixel
     */

    public static int isWhite(BufferedImage original, int i, int j, int size, double av, int threshold) { //av = black/white
        int ans = -1;
        int red = -1;
        int black = 0;
        int white = 0;
        int a, b;
        if (size == 0) {
            red = new Color(original.getRGB(i, j)).getRed();
            if (red > threshold) {
                ans = 0;
            } else {
                ans = 255;
            }
            return ans;
        } else {
            for (int x = -size; x <= size; x++) {
                for (int y = -size; y <= size; y++) {
                    a = i + x;
                    b = j + y;
                    if (a >= 0 && a < original.getWidth() && b >= 0 && b < original.getHeight()) {

                        red = red = new Color(original.getRGB(i + x, j + y)).getRed();
                        if (red > threshold) {
                            black++;
                        } else {
                            white++;
                        }
                    }
                }

            }
//            if ((double)(black/white) > av)
            if (black > white) {
                return 0;
            } else {
                return 255;
            }

        }

    }
    /**
     * static function which receives (BufferedImage original, int i, int j, int size, double av, int threshold)
     * 
     * function which calculate if a given pixel is black or white.
     * Used by: Bw method.
     * 
     * 
     * 
     * receives:
     * BufferedImage original - image to process.
     * int i - i current location in original.
     * int j - j current location in original.
     * int size - blur "game of life" value.
     * double av - stands for the required black&white ratio (black/white).
     * int threshold - binary convert threshold (0-255);
     * 
     * @param pic 
     * return <int>0 for black or 255 for white
     */

    public static void NumOfColors(BufferedImage pic) {
        Vector<Integer> v = new Vector<Integer>();
        int red = -1;

        for (int j = 0; j < pic.getHeight(); j++) {
            for (int i = 0; i < pic.getWidth(); i++) {
                red = new Color(pic.getRGB(i, j)).getRed();
                if (!v.contains(red)) {
                    v.add(red);
                }

            }
        }
        System.out.println("num of colors: " + v.size());

        for (int i = 0; i < v.size(); i++) {
            if (i % 10 == 0) {
                System.out.println("");
            }
            System.out.print(v.elementAt(i) + ", ");
        }
    }
    /**
     * static function which prints the number of colors in the given image
     * 
     * receives:
     * BufferedImage pic - image for analyzation 
     * Prints how many colors it contains.
     * @param original
     * @param PicDest 
     */

    public static void con(BufferedImage original, String PicDest) {
        File file = new File(PicDest);
        BufferedImage dist;
        try {
     //       dist = ImageIO.read(file);

            RescaleOp rescaleOp = new RescaleOp(1.0f, 100,null);
            rescaleOp.filter(original, original);  // Source and destination are the same.
            ImageIO.write(original, "jpg", new File(PicDest));//"/home/roy/picP/blackwhiteimage2"

        } catch (IOException ex) {
            Logger.getLogger(BlackWhite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
