/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import imageprocessing.BlackWhite;
import imageprocessing.Config;
import imageprocessing.GameOfLife;
import imageprocessing.ImageProcessing;
import imageprocessing.MatCol;
import imageprocessing.MatLineCol;
import imageprocessing.RecCol;
import imageprocessing.RecLineCol;
import imageprocessing.Test;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author roy
 */
public class Configure_Letters {

    final private static String imagepath = "/home/roy/picP/newBW/";
    final private static String fileName = "abc";
    final private static String filetype = ".jpg";

    private static String fileString(int num) {
        return imagepath + fileName + num + filetype;
    }

    public static void main(String[] args) {
        // TODO code application logic here
   
        File file = new File(imagepath+fileName+filetype);
        BlackWhite Bw = new BlackWhite();
        RecCol D = new RecCol();
        MatCol matcol = new MatCol();
        int[][] a;
        int[][] b;
        try {
            BufferedImage original = ImageIO.read(file);
            BufferedImage negetive;
            BufferedImage work;

            BufferedImage sub;
            negetive = Bw.BW(original, fileString(2), 130, 2, 0.5);
            Bw.NumOfColors(negetive);
            work = Bw.BW(original, fileString(1), 130, 0, 0.5);
            Bw.con(original, fileString(2));
            GameOfLife gol = new GameOfLife();
            gol.GOL(negetive,fileString(3), D, 1,5);
            D.mergeAllOver();
            Test.Rects(fileString(1), fileString(4), D.Rects);
            RecLineCol recline = new RecLineCol();
            MatLineCol matline = new MatLineCol();
            matcol.ProRect(work, D);
            Config Config1 = new Config();
            Config1.conf(original, matcol, D, imagepath+"Saves/save1.ser");

        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }

}
