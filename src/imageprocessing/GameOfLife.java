/*
 * To change this license header, choose License Headers in Project Properties.GameOfLife 
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;

/**
 *
 * @author roy
 */
public class GameOfLife {


    
    public GameOfLife(){};
    
    
    public  void GOL(BufferedImage original, String PicDest, RecCol D , int threshold, int returnDistThreshold) {
        try {
            int red;
            boolean white = false;

            // int threshold = 130;
            // red = new Color(original.getRGB(i, j)).getRed(); ****** gets the color. mid value = 127
            for (int j = 0; j < original.getHeight(); j++) {
                for (int i = 0; i < original.getWidth(); i++) {

                    // Get pixels
                    red = new Color(original.getRGB(i, j)).getRed();
                    if (red < 127) {
                        white = false;

                    } else {
                        if (!D.isInAll(i, j , returnDistThreshold )) {
                            white = true;
                            myRectangle temp;
                            temp = BuildRec(i, j, original , threshold,returnDistThreshold);
                            D.add(temp);
                        }

                    }

                }
            }
//            System.out.println("num of rect: " + D.size());
//            System.out.println("rect 1 =  " + D.Rects.elementAt(0));
            
            //Test.Rects(original, PicDest, D.Rects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private myRectangle BuildRec(int i, int j, BufferedImage pic , int threshold , int returnDistThreshold) {
        boolean go = true;
        int minI = i;
        int minJ = j;
        int maxI = i;
        int maxJ = j;
        int iniI = i;
        int iniJ = j;
        int lastAction = -1;

        myRectangle ans = new myRectangle();
        ans.SetAll(i, j, i, j);
        int runningCounter = 0;
        
       //chacking block, printing circles for tracking, need to be disabled. 
      //  Graphics2D g = pic.createGraphics();
      //  g.setColor(Color.red);
        
        
        while (go) {
            runningCounter ++;
            
            
        //    g.fillOval(i, j, 10, 10);// for testing
            
            
            go = false;
            if (lastAction != 8 && lastAction != 7 && lastAction != 9 && isWhite(i, j, 2, pic , threshold)) {//Up
                j--;
                go = true;
                lastAction = 2;

            } else if (lastAction != 7 && lastAction != 4 && lastAction != 8 && isWhite(i, j, 3, pic , threshold)) {//Up Right
                j--;
                i++;
                go = true;
                lastAction = 3;
            } else if (lastAction != 4 && lastAction != 1 && lastAction != 7 && isWhite(i, j, 6, pic , threshold)) {//Right
                i++;
                go = true;
                lastAction = 6;
            } else if (lastAction != 1 && lastAction != 4 && lastAction != 2 && isWhite(i, j, 9, pic , threshold)) {//Right Down
                i++;
                j++;
                go = true;
                lastAction = 9;
            } else if (lastAction != 2 && lastAction != 1 && lastAction != 3 && isWhite(i, j, 8, pic , threshold)) {//Down
                j++;
                go = true;
                lastAction = 8;
            } else if (lastAction != 3 && lastAction != 2 && lastAction != 6 && isWhite(i, j, 7, pic ,threshold)) {//Down Left
                j++;
                i--;
                go = true;
                lastAction = 7;
            } else if (lastAction != 6 && lastAction != 3 && lastAction != 9 && isWhite(i, j, 4, pic ,threshold)) {//Left
                i--;
                go = true;
                lastAction = 4;
            } else if (lastAction != 9 && lastAction != 8 && lastAction != 6 && isWhite(i, j, 1, pic , threshold)) {//Left Up
                i--;
                j--;
                go = true;
                lastAction = 1;
            }

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
            if (runningCounter > returnDistThreshold && Math.abs(i-iniI) <= returnDistThreshold && Math.abs(j-iniJ) <= returnDistThreshold) {
           // if (j>=iniJ){
                go = false;
            }
            if (i == iniI && j == iniJ)
                go = false;
            

        }
        // ans.SetAll(minI, minJ, maxI, maxJ);
        ans.SetAll(minI, minJ, maxI, maxJ);
        

        return ans;
    }

    private boolean isWhite(int i, int j, int dir, BufferedImage pic , int threshold) {
        int red = -1;
        boolean ans = false;
        switch (dir) {
            case 2: {//Up
                if (j - threshold < 0) {
                    return false;
                }
                red = new Color(pic.getRGB(i, j - threshold)).getRed();
                if (red > 127 && isBorder(i, j - threshold, pic)) {
                    ans = true;
                } else {
                    ans = false;
                }
                return ans;
            }
            case 3: {//Up Right
                if (j - threshold < 0 || i + threshold >= pic.getWidth()) {
                    return false;
                }
                red = new Color(pic.getRGB(i + threshold, j - threshold)).getRed();
                if (red > 127 && isBorder(i + threshold, j - threshold, pic)) {
                    ans = true;
                } else {
                    ans = false;
                }
                return ans;
            }
            case 6: {//Right
                if (i + threshold >= pic.getWidth()) {
                    return false;
                }
                red = new Color(pic.getRGB(i + threshold, j)).getRed();
                if (red > 127 && isBorder(i + threshold, j, pic)) {
                    ans = true;
                } else {
                    ans = false;
                }
                return ans;
            }
            case 9: {//Right Down
                if (j + threshold >= pic.getHeight() || i + threshold >= pic.getWidth()) {
                    return false;
                }
                red = new Color(pic.getRGB(i + threshold, j + threshold)).getRed();
                if (red > 127 && isBorder(i + threshold, j + threshold, pic)) {
                    ans = true;
                } else {
                    ans = false;
                }
                return ans;
            }
            case 8: {//Down
                if (j + threshold >= pic.getHeight()) {
                    return false;
                }
                red = new Color(pic.getRGB(i, j + threshold)).getRed();
                if (red > 127 && isBorder(i, j + threshold, pic)) {
                    ans = true;
                } else {
                    ans = false;
                }
                return ans;
            }
            case 7: {//Down Left
                if (j + threshold >= pic.getHeight() || i - threshold < 0) {
                    return false;
                }
                red = new Color(pic.getRGB(i - threshold, j + threshold)).getRed();
                if (red > 127 && isBorder(i - threshold, j + threshold, pic)) {
                    ans = true;
                } else {
                    ans = false;
                }
                return ans;
            }
            case 4: {//left
                if (i - threshold < 0) {
                    return false;
                }
                red = new Color(pic.getRGB(i - threshold, j)).getRed();
                if (red > 127 && isBorder(i - threshold, j, pic)) {
                    ans = true;
                } else {
                    ans = false;
                }
                return ans;
            }
            case 1: {//left Up
                if (j - threshold < 0 || i - threshold < 0) {
                    return false;
                }
                red = new Color(pic.getRGB(i - threshold, j - threshold)).getRed();
                if (red > 127 && isBorder(i - threshold, j - threshold, pic)) {
                    ans = true;
                } else {
                    ans = false;
                }
                return ans;
            }

        }
        return ans;
    }

    private boolean isBorder(int i, int j, BufferedImage pic) {
        boolean ans = false;
        int red = -1;
        int a = -1;
        int b = -1;
        for (int x = -1; !ans & x < 2; x++) {
            for (int y = -1; !ans & y < 2; y++) {
                a = i + x;
                b = j + y;
                if (a >= 0 && a < pic.getWidth() && b >= 0 && b < pic.getHeight()) {
                    red = new Color(pic.getRGB(i + x, j + y)).getRed();
                    if (red < 127) {
                        ans = true;
                    }
                }

            }
        }
        return ans;
    }
}
