/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class will resize all the images in a given folder
 *
 * @author pankaj
 *
 */
public class JavaImageResizer {

    public static void main(String[] args) throws IOException {

     //   File folder = new File("/home/roy/picP/size");
    //    File[] listOfFiles = folder.listFiles();
        File Roy = new File("/home/roy/picP/size/RoyIm.jpg");
   //     System.out.println("Total No of Files:" + listOfFiles.length);
        Image img = null;
        BufferedImage tempPNG = null;
        BufferedImage tempJPG = null;
        File newFilePNG = null;
        File newFileJPG = null;
       // for (int i = 0; i < listOfFiles.length; i++) {
     //       if (Roy.isFile()) {
                System.out.println("File "+ Roy.getName());
                img = ImageIO.read(Roy);
                tempPNG = resizeImage(img, 100, 100);
                tempJPG = resizeImage(img, 100, 100);
 //               newFilePNG = new File(Roy.getName()+ "_New.png");
                newFileJPG = new File("/home/roy/picP/size/RoyIm_New");
//                ImageIO.write(tempPNG, "png", newFilePNG);
                ImageIO.write(tempJPG, "jpg", newFileJPG);
       //     }
   //     }
        System.out.println("DONE");
    }

    /**
     * This function resize the image file and returns the BufferedImage object
     * that can be saved to file system.
     */
    public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
}
