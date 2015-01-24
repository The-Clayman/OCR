/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

/**
 *
 * @author roy
 */
public class OCR {

    public void go(MatCol Sample, MatLineCol words) {
        String ans = "\n";
        char c = '_';
        double max = -1;
        double comp = -1;
        for (int i = 0; i < words.LineRec.size(); i++) {
            for (int j = 0; j < words.LineRec.elementAt(i).size(); j++) {
                if (words.LineRec.elementAt(i).elementAt(j).isSpace()) {
                    ans = ans + " ";
                } else {

                    for (int z = 0; z < Sample.size(); z++) {
                        comp = matComp(words.LineRec.elementAt(i).elementAt(j).getMat(), Sample.mats.elementAt(z).getMat());
                        if (comp > max) {
                            max = comp;
                            c = Sample.mats.elementAt(z).getChar();
                        }

                    }
                    ans = ans + c;
                    words.LineRec.elementAt(i).elementAt(j).setChar(c);
                    max = 0;
                }
            }
            ans = ans + '\n';
        }
        System.out.println(ans);
    }

    public double matComp(int[][] a, int[][] b) {
        int t = 1;
        int f = 1;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == b[i][j]) {
                    f++;
                } else {
                    t++;
                }
            }

        }
        return (double) ((double) f / (double) t);
    }
}
