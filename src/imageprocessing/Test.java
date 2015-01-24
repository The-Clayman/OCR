/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
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
public class Test {

    public static void main(String[] args) {
    //    myRectangle a = new myRectangle();
        //    a.SetAll(15, 10, 68, 93);
        //    Rects("//home/roy/picP/rect/BlackM.jpg", "/home/roy/picP/rect/BlackM2.jpg",Data.Rects);
        //Circle("/home/roy/picP/rect/Big1.jpg", "/home/roy/picP/rect/BigCir.jpg", 699, 155);
       // checkColors("/home/roy/picP/newBW/checkboard2.jpg");
           minmax("/home/roy/picP/newBW/Hello2.jpg");

    }

    public static BufferedImage deepCopy(BufferedImage bi) {

        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static void Rects(String PicSorce, String PicDest, Vector<myRectangle> rect) {    
        try {
            File file = new File(PicSorce);
            BufferedImage pic = ImageIO.read(file);
            Graphics2D g = pic.createGraphics();
            g.setColor(Color.red);
            for (int i = 0; i < rect.size(); i++) {
                g.drawRect((int) rect.get(i).getX(), (int) rect.get(i).getY(), (int) rect.get(i).getWidth(), (int) rect.get(i).getHeight());

            }

            ImageIO.write(pic, "jpg", new File(PicDest));

        } catch (IOException ex) {
            Logger.getLogger(picScale.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void Circle(String PicSorce, String PicDest, int i, int j) {

        File Sfile = new File(PicSorce);//"/home/roy/picP/scale/super2.jpg"
        try {
            BufferedImage original = ImageIO.read(Sfile);
            Graphics2D g = original.createGraphics();
            g.setColor(Color.red);
            g.fillOval(i, j, 10, 10);
            ImageIO.write(original, "jpg", new File(PicDest));

        } catch (IOException ex) {
            Logger.getLogger(picScale.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void minmax(String PicSorce) {
        int minI = Integer.MAX_VALUE;
        int minJ = Integer.MAX_VALUE;
        int maxI = -1;
        int maxJ = -1;
        int red;
        File Sfile = new File(PicSorce);//"/home/roy/picP/scale/super2.jpg"
        try {
            BufferedImage original = ImageIO.read(Sfile);
            for (int j = 0; j < original.getHeight(); j++) {
                for (int i = 0; i < original.getWidth(); i++) {

                    // Get pixels
                    red = new Color(original.getRGB(i, j)).getRed();
                    if (red > 127) {
                        if (i < minI) {
                            minI = i;
                        }
                        if (j < minJ) {
                            minJ = j;
                        }
                        if (i > maxI) {
                            maxI = i;
                        }
                        if (j > maxJ) {
                            maxJ = j;
                        }
                    }
                }
            }
            System.out.println("minI " + minI + ", minj " + minJ);
            System.out.println("maxI " + maxI + ", maxj " + maxJ);

        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void checkColors(String PicSorce) {
        Vector<Integer> v = new Vector<Integer>();
        int red = -1;
        File Sfile = new File(PicSorce);//"/home/roy/picP/scale/super2.jpg"
        
            BufferedImage original;
        try {
            original = ImageIO.read(Sfile);
        
            for (int j = 0; j < original.getHeight(); j++) {
                for (int i = 0; i < original.getWidth(); i++) {
                    red = new Color(original.getRGB(i, j)).getRed();
                    if (!v.contains(red)) v.add(red);

                }
            }
            
        }
         catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("num of colors: "+v.size());
        for (int i = 0 ; i < v.size() ; i++){
            if (i%10 == 0) System.out.println("");
            System.out.print(v.elementAt(i)+", ");
        }
        
    }
}
