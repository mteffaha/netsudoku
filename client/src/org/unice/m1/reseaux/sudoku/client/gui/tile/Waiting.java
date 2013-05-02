package org.unice.m1.reseaux.sudoku.client.gui.tile;

import javax.swing.*;
import java.awt.*;

/**
 * @author TEFFAHA Mortadha
 */
public class Waiting extends JPanel {

    private JLabel loader;

    public Waiting(int width){
        loader = new JLabel("");
        /*
        ImageIcon ii = new ImageIcon(this.getClass().getResource(
                    "snoopy_dancing.gif"));
            imageLabel.setIcon(ii);
         */
        ImageIcon loaderIcon = new ImageIcon(this.getClass().getResource("/loader.gif"));
        loader.setIcon(loaderIcon);
        loader.setPreferredSize(new Dimension(width,width));
        this.add(loader);
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.add(new Waiting(60));
        frame.pack();
        frame.setVisible(true);
    }
}
