/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;

/**
 *
 * @author roy
 */
public class BWnew {

    public static void main(String[] args) throws IOException {
        //  CheckBoard("/home/roy/picP/newBW/checkboard.jpg");
        //BWOld("/home/roy/picP/newBW/checkboard.jpg", "/home/roy/picP/newBW/checkboard2.jpg", 130);

    }

    public static void CheckBoard(String source) throws IOException {
        int width = 400; // Dimensions of the image
        int height = 400;
        // Let's create a BufferedImage for a binary image.
        BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        // We need its raster to set the pixels' values.
        WritableRaster raster = im.getRaster();
        // Put the pixels on the raster. Note that only values 0 and 1 are used for the pixels.
        // You could even use other values: in this type of image, even values are black and odd
        // values are white.
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if (((h / 50) + (w / 50)) % 2 == 0) {
                    raster.setSample(w, h, 0, 0); // checkerboard pattern.
                } else {
                    raster.setSample(w, h, 0, 1);
                }
            }
        }
        // Store the image using the PNG format.
        ImageIO.write(im, "jpg", new File("/home/roy/picP/newBW/checkboard.jpg"));
    }

    public static BufferedImage BWOld(BufferedImage original, String PicDest, int threshold) {
        try {

            BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

            int red;
            Color Black = new Color(0, 0, 0);
            Color White = new Color(255, 255, 255);
            // int threshold = 130;

            for (int i = 0; i < original.getWidth(); i++) {
                for (int j = 0; j < original.getHeight(); j++) {

                    // Get pixels
                    red = new Color(original.getRGB(i, j)).getRed();
                    if (red > threshold) {
                        binarized.setRGB(i, j, White.getRGB());
                    } else {
                        binarized.setRGB(i, j, Black.getRGB());

                    }

                }
            }
            ///////////////////////////////////////////////////////////////
            Vector<Integer> v = new Vector<Integer>();
            int r = -1;
            for (int j = 0; j < binarized.getHeight(); j++) {
                for (int i = 0; i < binarized.getWidth(); i++) {
                    red = new Color(binarized.getRGB(i, j)).getRed();
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
            
            ImageIO.write(binarized, "jpg", new File(PicDest));//"/home/roy/picP/blackwhiteimage2"
            return binarized; 
            
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;
    }
    

}
