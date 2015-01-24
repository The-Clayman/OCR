package imageprocessing;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author roy
 */
public class LetterMat implements Serializable {

    int[][] mat;
    char c;
    public int StartI;
    public int StartJ;
    public int EndI;
    public int EndJ;
    int ImageWidth;
    int ImageHight;
    boolean Space = false;

    public boolean isSpace() {
        return Space;
    }

    public void setSpace(boolean Space) {
        this.Space = Space;
    }
    public LetterMat(){
        
    }

    public LetterMat(int[][] m , int Hight , int Width) {
        this.mat = m;
        this.c = ' ';
        this.ImageHight = Hight;
        this.ImageWidth = Width;
        this.calcLim();
    }


    public void setChar(char c) {
        this.c = c;
    }

    public void setStartI(int i) {
        this.StartI = i;
    }

    public void setStartj(int j) {
        this.StartJ = j;
    }

    public void setEndI(int i) {
        this.EndI = i;
    }

    public void setEndJ(int j) {
        this.EndJ = j;
    }

    public void calcLim() {
        int x = Integer.MAX_VALUE;
        int y = x;
        int maxX = -1;
        int maxY = -1;
        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[j][i] == 1) {
                    if (i < x) {
                        x = i;
                    }
                    if (j < y) {
                        y = j;
                    }
                    if (i > maxX) {
                        maxX = i;
                    }
                    if (j > maxY) {
                        maxY = j;
                    }
                }
            }
        }
        StartJ = x;
        StartI = y;
        EndI = maxX;
        EndJ = maxY;

    }

    public void setMat(int[][] m) {
        this.mat = m;
    }

    public char getChar() {
        return this.c;
    }

    public int[][] getMat() {
        return this.mat;

    }

    public void Print() {
        System.out.println("char = " + this.c);
        System.out.println("StartI = " + this.StartI + " ,StartJ = " + this.StartJ + ", EndI = " + this.EndI + ", EndJ = " + this.EndJ);
        System.out.println("image hight: "+this.ImageHight+", image width: "+this.ImageWidth);
        

        for (int i = 0; i < this.mat[0].length; i++) {
            for (int j = 0; j < this.mat.length; j++) {
                System.out.print(this.mat[j][i] + " ");
            }
            System.out.println();
        }

    }

}
