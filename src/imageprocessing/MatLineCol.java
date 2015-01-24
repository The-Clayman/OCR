/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 *
 * @author roy
 */
public class MatLineCol {

    Vector<Vector<LetterMat>> LineRec;

    public MatLineCol() {
        this.LineRec = new Vector<Vector<LetterMat>>();
    }

    public void ProRect(BufferedImage pic, RecLineCol D) {
        BufferedImage sub;
        int[][] a;
        for (int i = 0; i < D.LineRec.size(); i++) {
            Vector<LetterMat> temp = new Vector<LetterMat>();
            for (int j = 0; j < D.LineRec.elementAt(i).size(); j++) {
                if (D.LineRec.elementAt(i).elementAt(j).isSpace()) {
                    LetterMat Mat = new LetterMat();
                    Mat.setSpace(true);
                    temp.add(Mat);
                } else {
                    sub = pic.getSubimage((int) (D.LineRec.elementAt(i).elementAt(j).getX()), (int) (D.LineRec.elementAt(i).elementAt(j)
                            .getY()), (int) (D.LineRec.elementAt(i).elementAt(j).getWidth()), (int) (D.LineRec.elementAt(i).elementAt(j).getHeight()));
                    a = mat.op(sub, null);
                    LetterMat Mat = new LetterMat(a, sub.getHeight(), sub.getWidth());
                    temp.add(Mat);
                }
            }
            this.LineRec.add(temp);

        }

    }

    public void print() {
        for (int i = 0; i < this.LineRec.size(); i++) {
            for (int j = 0; j < this.LineRec.elementAt(i).size(); j++) {
                this.LineRec.elementAt(i).elementAt(j).Print();
                System.err.print('\n' + '\n');
            }
            System.out.println("**********Line ends***************");

        }
    }
}
