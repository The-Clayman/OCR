/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imageprocessing;

import java.util.Vector;

/**
 *
 * @author roy
 */
public class RecLineCol {
    Vector<Vector<myRectangle>> LineRec;
    
     public void getLinesNum(){
        System.out.println("lines: "+this.LineRec.size());
    }
     public void setRecLineCol(Vector<Vector<myRectangle>> other){
        this.LineRec = other;
     }
    
}
