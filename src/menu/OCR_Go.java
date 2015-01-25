/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;
import imageprocessing.BlackWhite;
import imageprocessing.GameOfLife;
import imageprocessing.ImageProcessing;
import imageprocessing.MatCol;
import imageprocessing.MatLineCol;
import imageprocessing.MatSaver;
import imageprocessing.OCR;
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
public class OCR_Go {
     final private static String imagepath = "/home/roy/picP/newBW/";
     final private static String fileName = "text";
     final private static String filetype = ".jpg";
     private static String fileString(int num){
         return  imagepath+fileName+num+filetype;
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
            negetive = Bw.BW(original, fileString(2), 130, 1, 0.5);// other threshold  negetive = Bw.BW(original, fileString(2), 130, 2, 0.5);
            Bw.NumOfColors(negetive);
            work = Bw.BW(original, fileString(1), 130, 0, 0.5);
            Bw.con(original, fileString(2));
            GameOfLife gol = new GameOfLife();
            gol.GOL(negetive, fileString(3), D, 1 , 1);//can be gol.GOL(negetive,fileString(3), D, 1, 5);
            D.mergeAllOver();
            Test.Rects(fileString(1), fileString(4), D.Rects);
            RecLineCol recline = new RecLineCol();
            recline.setRecLineCol(D.sort());
            MatLineCol matline = new MatLineCol();
            matline.ProRect(work, recline);
            matcol.ProRect(work, D);
            MatCol newLM = MatSaver.loadCol(imagepath+"Saves/save1.ser");
            OCR ocr = new OCR();
            ocr.go(newLM, matline);

        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
