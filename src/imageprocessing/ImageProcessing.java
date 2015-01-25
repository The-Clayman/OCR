/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

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
public class ImageProcessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //   picScale a = new picScale();
        //   a.Scale("/home/roy/picP/scale/super2.jpg", "/home/roy/picP/scale/S5.jpg", 0.5);
        File file = new File("/home/roy/picP/newBW/Hello.jpg");
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
            negetive = Bw.BW(original, "/home/roy/picP/newBW/Hello2.jpg", 130, 2, 0.5);
            Bw.NumOfColors(negetive);
            work = Bw.BW(original, "/home/roy/picP/newBW/Hello1.jpg", 130, 0, 0.5);
            Bw.con(original, "/home/roy/picP/newBW/Hello2.jpg");
            GameOfLife gol = new GameOfLife();
            gol.GOL(negetive, "/home/roy/picP/newBW/Hello3.jpg", D, 1 , 0);

            D.growAll(15, 15);
            D.mergeAll();
            Test.Rects("/home/roy/picP/newBW/Hello1.jpg", "/home/roy/picP/newBW/Hello4.jpg", D.Rects);
   //         System.out.println("num of rect "+D.size());
            RecLineCol recline = new RecLineCol();
          recline.LineRec = D.sort();
            MatLineCol matline = new MatLineCol();
            matline.ProRect(work, recline);
     //       System.out.println(D);
            
     //       recline.getLinesNum();
      //      matline.print();
            
         matcol.ProRect(work, D);
           MatCol newLM = MatSaver.loadCol("/home/roy/picP/Saves/save1.ser");
           OCR ocr = new OCR();
           ocr.go(newLM, matline);
    //       Config Config1 = new Config();
    //       Config1.conf(original,matcol , D, "/home/roy/picP/Saves/save1.ser");
//            MatSaver.saveLineCol("/home/roy/picP/Saves/save1.ser", matline); 
     //       
       //     newLM.PrintAll();
            
           // MatSaver.save("/home/roy/picP/Saves/save1.ser", Col);
           // System.out.println("");
           // System.out.println("");
           // System.out.println("");
           // MatCol newLM = MatSaver.load("/home/roy/picP/Saves/save1.ser");
           // newLM.PrintAll();
            //  b = mat.op(sub2, null);
            //  System.out.println(mat.compareMat(a, b));
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
        // b.BW("/home/roy/picP/rect/Big.jpg", "/home/roy/picP/rect/Big1.jpg", 140);
        //PicSize c = new PicSize("/home/roy/picP/size/BlackM.jpg","/home/roy/picP/size/crop1.jpg");
        //b.BW("/home/roy/picP/size/crop1.jpg", "/home/roy/picP/size/crop1BW.jpg", 130);

    }

}
