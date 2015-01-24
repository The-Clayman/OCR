/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import sun.applet.Main;

/**
 *
 * @author roy
 */
public class picScale {

    public void Scale(String PicSorce, String PicDest, double scale) {
        
        double fWidth = scale;
        double fHeight = scale;
        File Sfile = new File(PicSorce);//"/home/roy/picP/scale/super2.jpg"
        try {
            BufferedImage original = ImageIO.read(Sfile);
            BufferedImage dbi = null;
            dbi = new BufferedImage((int) (original.getWidth() * fWidth), (int) (original.getHeight() * fHeight), original.getType());
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(original, at);
            ImageIO.write(dbi, "jpg", new File(PicDest));
        } catch (IOException ex) {
            Logger.getLogger(picScale.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
