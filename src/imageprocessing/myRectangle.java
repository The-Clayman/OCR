/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.Rectangle;

/**
 *
 * @author roy
 */
public class myRectangle extends Rectangle {

    int lowX;
    int lowY;
    int CenX;
    int CenY;
    int Area;
    boolean space = false;

    public boolean isSpace() {
        return space;
    }

    public void setSpace(boolean space) {
        this.space = space;
    }

    public myRectangle() {
        super();
        this.lowX = 0;
        this.lowY = 0;
    }

    public void SetAll(int x, int y, int lowX, int lowY) {
        this.x = x;
        this.y = y;
        this.lowX = lowX;
        this.lowY = lowY;
        this.width = lowX - this.x;
        this.height = lowY - this.y;
        this.CenX = (x+lowX)/2;
        this.CenY = (y+lowY)/2;
        this.Area = this.height*this.width;
        
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLowX(int x) {
        this.lowX = x;
    }

    public void setLowY(int y) {
        this.lowY = y;
    }

    public int getlowX() {
        return this.lowX;
    }

    public int getlowY() {
        return this.lowY;
    }

    public boolean isInOne(int i, int j , int returnDistThreshold) {
        boolean ans = false;
        if (i >= (this.x-returnDistThreshold) && i <= (this.lowX+returnDistThreshold) && j >= (this.y-returnDistThreshold) && j <= (this.lowY+returnDistThreshold)) {
            ans = true;
        }
        return ans;
    }
    public boolean onX(int i){
        if (i >=this.x && i<=this.lowX)
            return true;
        else return false;
    }
    public boolean onY(int j){
        if (j >=this.y && j<=this.lowY)
            return true;
        else return false;
    }

    @Override
    public String toString() {
        return "X= " + this.x + ", y= " + this.y + ", lowX= " + this.lowX + ",lowY= " + this.lowY + ", W=" + this.width + ", H=" + this.height
                + ",Center X= "+this.CenX+",Center Y= "+this.CenY+", Area= "+this.Area;
    }

    public void grow(int h, int v) {
        super.grow(h, v);
        this.rebuild();

    }

    public void rebuild() {
        this.setLowX(this.x + this.width);
        this.setLowY(this.y + this.height);
        this.CenX = (this.x+this.lowX)/2;
        this.CenY = (this.y+this.lowY)/2;
        this.Area = this.height*this.width;
    }

    public void uni(myRectangle other) {
        int x1 = Math.min(x, other.x);
        int x2 = Math.max(x + width, other.x + other.width);
        int y1 = Math.min(y, other.y);
        int y2 = Math.max(y + height, other.y + other.height);
        this.SetAll(x1, y1, x2, y2);
    }
    public boolean Inter(myRectangle other) {
       return ((this.x<other.lowX) && (this.lowX > other.x) &&
               (this.y < other.lowY) && (this.lowY) > other.y); 
    }

}
