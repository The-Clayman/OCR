package imageprocessing;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author roy
 */
public class MatSaver {

    public static void saveCol(String dest, MatCol m) {//"myarray.ser"
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(new FileOutputStream(dest));

            out.writeObject(m);
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(MatSaver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static MatCol loadCol(String dest) {
        MatCol m = new MatCol();
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new FileInputStream(dest));
            m = (MatCol) in.readObject();
            in.close();

        } catch (Exception ex) {
            Logger.getLogger(MatSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return m;
    }
    public static void saveLineCol(String dest, MatLineCol m) {//"myarray.ser"
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(new FileOutputStream(dest));

            out.writeObject(m);
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(MatSaver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static MatLineCol loadLineCol(String dest) {
        MatLineCol m = new MatLineCol();
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new FileInputStream(dest));
            m = (MatLineCol) in.readObject();
            in.close();

        } catch (Exception ex) {
            Logger.getLogger(MatSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return m;
    }
    public static void add(String dest, MatCol m) {//"myarray.ser"
        MatCol ori = new MatCol();
        ObjectInputStream in;
        ObjectOutputStream out;
        try {
            in = new ObjectInputStream(new FileInputStream(dest));
            ori = (MatCol) in.readObject();
            in.close();
            ori.addCol(m);
            out = new ObjectOutputStream(new FileOutputStream(dest));
            out.writeObject(ori);
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(MatSaver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MatSaver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
