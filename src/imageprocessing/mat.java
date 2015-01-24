/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.Color;
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
public class mat {

    public static int StartI;
    public static int StartJ;
    public static int EndI;
    public static int EndJ;
    
    int[][] M;

    //// leter Proportion = 11/75
    public static void main(String[] args) {
        mat a = new mat();

    }

    public static int[][] op(BufferedImage original, String PicDest) {//

        int black = -1;
        int white = -1;
        int red = -1;
        int binar = -1;
        int MatHight = 10;
        int MatWidth = 8;
        int ResidueW = 1;
        int ResidueH = 1;
        if ((original.getWidth() / MatWidth) % 1 != 0) {
            ResidueW = 0;
        }
        if ((original.getHeight() / MatHight) % 1 != 0) {
            ResidueH = 0;
        }
        int[][] M = new int[MatWidth][MatHight];
        int WidthPro = original.getWidth() / MatWidth + ResidueW;
        int HightPro = original.getHeight() / MatHight + ResidueH;

        for (int i = 0; i < MatWidth * WidthPro; i = i + WidthPro) {
            for (int j = 0; j < MatHight * HightPro; j = j + HightPro) {
                black = 0;
                white = 0;
                for (int x = 0; x < WidthPro && i + x < original.getWidth(); x++) {
                    for (int y = 0; y < HightPro && j + y < original.getHeight(); y++) {
                        red = new Color(original.getRGB(i + x, j + y)).getRed();
                        if (red < 127) {
                            black++;
                        } else {
                            white++;
                        }
                    }
                }
                if (black < white) {
                    binar = 1;
                } else {
                    binar = 0;
                }
                M[i / WidthPro][j / HightPro] = binar;
            }

        }
        //PrintM(M);

        return M;

    }

    public static void PrintM(int[][] M) {

        for (int i = 0; i < M[0].length; i++) {
            for (int j = 0; j < M.length; j++) {
                System.out.print(M[j][i] + " ");

            }
            System.out.println("");

        }

    }

    public static void Start(int M[][]) {
        int x = -1;
        int y = -1;
        int maxX = Integer.MAX_VALUE;
        int maxY = maxX; 
        
        boolean go = true;
        for (int i = 0; go && i < M[0].length; i++) {
            for (int j = 0; go && j < M.length; j++) {
                if (M[j][i] == 1) {
                    x = i;
                    y = j;
                    if (i > maxX) maxX = i;
                    if (j > maxY) maxY = j;
                }
            }
        }
        StartJ = x;
        StartI = y;
        EndI = maxX;
        EndJ = maxY;

    }

    public static double compareMat(int[][] a, int[][] b) {
        int right = 0;
        int wrong = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == b[i][j]) {
                    right++;
                } else {
                    wrong++;
                }
            }
        }
        return (double)right/(double)(right+wrong);
    }
    public static void Statch(){
        
    }

}
