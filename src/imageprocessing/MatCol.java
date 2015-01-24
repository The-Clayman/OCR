package imageprocessing;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author roy
 */
public class MatCol implements Serializable {

    Vector<LetterMat> mats;
    double maxWidth;

    public double getMaxWidth() {
        return maxWidth;
    }

    public MatCol() {
        this.mats = new Vector<LetterMat>();

    }

    public void addCol(MatCol other) {
        for (int i = 0; i < other.size(); i++) {
            this.add(other.mats.elementAt(i));
        }
    }

    public void add(LetterMat m) {
        this.mats.add(m);
    }

    public int[][] getMat(int i) {
        return this.mats.elementAt(i).getMat();
    }

    public char getChar(int i) {
        return this.mats.elementAt(i).getChar();
    }

    public int size() {
        return this.mats.size();
    }

    public void setChar(int i, char c) {
        this.mats.elementAt(i).setChar(c);
    }

    public void setMat(int i, int[][] m) {
        this.mats.elementAt(i).setMat(m);
    }

    public void PrintAll() {
        for (int i = 0; i < this.mats.size(); i++) {
            this.mats.get(i).Print();
        }
        System.out.println("");
    }

    public void PrintNames() {
        String ans = "";
        System.out.println(this.size() + " letters:");

        for (int i = 0; i < this.mats.size(); i++) {
            ans = ans + this.mats.elementAt(i).c + ", ";
        }
        System.out.println("");
        System.out.println(ans);
        System.out.println("");

    }

    public void ProRect(BufferedImage pic, RecCol D) {
        BufferedImage sub;
        int[][] a;
        this.maxWidth = D.getmaxwidth();

        for (int i = 0; i < D.size(); i++) {
            if (D.Rects.elementAt(i).isSpace()) {
                LetterMat Mat = new LetterMat();
                Mat.setSpace(true);
                this.add(Mat);

            } else {
                sub = pic.getSubimage((int) (D.Rects.elementAt(i).getX()), (int) (D.Rects.elementAt(i)
                        .getY()), (int) (D.Rects.elementAt(i).getWidth()), (int) (D.Rects.elementAt(i).getHeight()));
                a = mat.op(sub, null);
                LetterMat Mat = new LetterMat(a, sub.getHeight(), sub.getWidth());
                this.add(Mat);
            }

        }

    }
}
