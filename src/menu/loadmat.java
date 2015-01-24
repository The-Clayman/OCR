/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import imageprocessing.MatCol;
import imageprocessing.MatSaver;

/**
 *
 * @author roy
 */
public class loadmat {

    final private static String imagepath = "/home/roy/picP/newBW/";
    final private static String fileName = "Roman";
    final private static String filetype = ".jpg";

    private static String fileString(int num) {
        return imagepath + fileName + num + filetype;
    }

    public static void main(String[] args) {
        MatCol newLM = MatSaver.loadCol(imagepath + "Saves/save1.ser");
        newLM.PrintAll();
    }
}
