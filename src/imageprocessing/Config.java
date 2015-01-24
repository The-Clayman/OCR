/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author roy
 */
public class Config {

    public Config() {
    }

    

    public void conf(BufferedImage original, MatCol m, RecCol D, String dest) {

        BufferedImage sub;
        JFrame frame = new JFrame();

        for (int i = 0; i < D.size(); i++) {

            sub = original.getSubimage((int) (D.Rects.elementAt(i).getX()), (int) (D.Rects.elementAt(i)
                    .getY()), (int) (D.Rects.elementAt(i).getWidth()), (int) (D.Rects.elementAt(i).getHeight()));

            frame = new JFrame();
            frame.getContentPane().setLayout(new FlowLayout());
            frame.getContentPane().add(new JLabel(new ImageIcon(sub)));
            frame.pack();
            frame.setVisible(true);

            System.out.println("letter num: " + i);
            System.out.println("Please enter char for cataloging");

            char c = readin();
            m.mats.elementAt(i).setChar(c);
            frame.setVisible(false);
            frame.dispose();

        }
        frame.dispose();
        //  MatSaver.add(dest, m);
        m.PrintNames();
        MatSaver.saveCol(dest, m);
    }

    private char readin() {
        char ans = '\n';
        while (ans =='\n'){
            try {
                ans = (char) System.in.read();
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }

}
