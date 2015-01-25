/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.geom.Area;
import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author roy
 */
public class RecCol {

    public Vector<myRectangle> Rects;
    public double maxWidth;

    public RecCol() {
        this.Rects = new Vector<myRectangle>();
        this.maxWidth = 0;

    }

    public void add(myRectangle a) {
        this.Rects.add(a);
        if (a.getWidth() > maxWidth) {
            this.maxWidth = a.getWidth();
        }
    }

    public int size() {
        return this.Rects.size();
    }

    public void growAll(int h, int v) {
        for (int i = 0; i < this.size(); i++) {
            this.Rects.elementAt(i).grow(h, v);
        }
    }

    public String toString() {
        String ans = "";
        for (int i = 0; i < this.size(); i++) {
            ans = ans + this.Rects.elementAt(i).toString();
            ans = ans + '\n';
        }
        return ans;
    }

    public boolean isInAll(int i, int j , int returnDistThreshold) {
        boolean ans = false;
        for (int z = 0; !ans && z < this.Rects.size(); z++) {
            if (this.Rects.elementAt(z).isInOne(i, j ,returnDistThreshold)) {
                ans = true;
            }

        }
        return ans;
    }

    public void mergeAll() {
        for (int i = 0; i < this.Rects.size(); i++) {
            for (int j = i + 1; j < this.Rects.size(); j++) {
                if (this.Rects.elementAt(i).Inter(this.Rects.elementAt(j))) {
                    this.Rects.elementAt(i).uni(this.Rects.elementAt(j));
                    this.Rects.removeElementAt(j);

                }
            }
        }
    }

    public void mergeAllOver() {
        double dist;
        for (int i = 0; i < this.Rects.size(); i++) {
            for (int j = 0; j < this.Rects.size(); j++) {
                dist = this.Rects.elementAt(i).getY() - this.Rects.elementAt(j).getlowY();

                if ((i != j) && (dist >= 0) && (dist <= (this.Rects.elementAt(i).getHeight() * 0.25))
                        && (this.Rects.elementAt(i).getX() <= (this.Rects.elementAt(j).getX() + (this.Rects.elementAt(i).width * 0.1)))
                        && (this.Rects.elementAt(i).getlowX() >= (this.Rects.elementAt(j).getlowX() - (this.Rects.elementAt(i).width * 0.1)))) {
                    this.Rects.elementAt(i).uni(this.Rects.elementAt(j));
                    this.Rects.removeElementAt(j);
                    j--;
                    if (i == this.Rects.size()) {
                        i--;
                    }
                    if (j == this.Rects.size()) {
                        j--;
                    }

                }
            }
        }
    }

    public Vector<Vector<myRectangle>> sort() {
        Vector<Vector<myRectangle>> ans = new Vector<Vector<myRectangle>>();
        int CenterY;
        while (this.Rects.size() > 0) {
            CenterY = this.Rects.elementAt(0).CenY;
            Vector<myRectangle> temp = new Vector<myRectangle>();
            for (int i = 0; i < this.Rects.size(); i++) {
                if (this.Rects.elementAt(i).onY(CenterY)) {
                    temp.add(this.Rects.elementAt(i));
                    this.Rects.remove(i);
                    i--;

                }
            }
            //  this.LineRec.elementAt(lines) = new Vector<myRectangle>();
            for (int i = 1; i < temp.size(); i++) {
                for (int j = 0; j < temp.size() - i; j++) {
                    if (temp.elementAt(j).CenX > temp.elementAt(j + 1).CenX) {
                        Collections.swap(temp, j, j + 1);
                    }
                }
            }
            for (int i = 1; i < temp.size(); i++) {
                double tt = temp.elementAt(i).getX() - temp.elementAt(i-1).getlowX();
                if (tt >= (this.getmaxwidth() * 0.5)) {
                    myRectangle t = new myRectangle();
                    t.setSpace(true);
                    temp.add(i, t);
                    i++;

                }
            }

            ans.add(temp);

        }
        return ans;

    }

    public int getmaxwidth() {
        return (int) this.maxWidth;
    }

}
