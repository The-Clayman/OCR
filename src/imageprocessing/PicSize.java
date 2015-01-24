/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author roy
 */
public class PicSize {

    public PicSize(String PicSorce, String PicDest) {
        try {
            File file = new File(PicSorce);//"/home/roy/picP/size/BlackM.jpg"
            BufferedImage original = ImageIO.read(file);
            

            int red;
            int corI = -1;
            int corJ = -1;
            int numWhite;
            double ratio = 1.675 ;//inverted -0.597014925 (40/67px) digit 

            int newPixel;
            int threshold = 160;
            boolean white = false;
            int size = 0;
            int maxSize = 0;
            int LineMaxSize = -1;
            int maxI = -1;
            int maxJ = -1;
            System.out.println("Hight: " + original.getHeight());
            System.out.println("width " + original.getWidth());

            for (int i = 0; i < original.getWidth(); i++) {
                if (maxSize < size) {
                    maxSize = size;
                    maxI = i;
                }
                size = 0;
                for (int j = 0; j < original.getHeight(); j++) {

                    // Get pixels
                    red = new Color(original.getRGB(i, j)).getRed();
                    if (red < 127) {
                        white = false;

                    } else {
                        white = true;
                        if (corI == -1) {
                            corI = i;
                            corJ = j;
                        }
                    }
                    if (white) {
                        System.out.println("i:= " + i + " j:= " + j);
                        size++;
                       
                    }

                   
                }

            }
            System.out.println("size= " + maxSize + " i index is: " + maxI);
            System.out.println("Start corI= " + corI + ", corJ= " + corJ);
            Rectangle rec = new Rectangle(corI, 108 - corJ, (int) (maxSize / ratio), maxSize);
            BufferedImage binarized = original.getSubimage(rec.x, rec.y - 9, rec.width + 9, rec.height
                    + 8);
            ImageIO.write(binarized, "jpg", new File(PicDest));//"/home/roy/picP/size/crop1.jpg")

//           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if (sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }
}
