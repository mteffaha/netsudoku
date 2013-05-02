package org.unice.m1.reseaux.sudoku.client.gui.tile;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @author TEFFAHA Mortadha
 */
public class Grid extends JPanel {

    private int width;
    private TileGrid[] grids;

    public Grid(int width){
        this.width = width;
        grids =  new TileGrid[9];

        this.setLayout(new GridLayout(3,3));

        Border thickBorder = BorderFactory.createLineBorder(Color.BLACK,2);
        for(int i=0;i<9;i++){
            grids[i] =  new TileGrid(this.width/3);
            grids[i].setBorder(thickBorder);
            this.add(grids[i]);
        }



    }


    public int getNumber(int x,int y){
        return 0;
    }

    public void setNumber(int x,int y, int number){

    }


    public static void main(String[] args){
        JFrame frame =  new JFrame();
        frame.add(new Grid(800));
        frame.pack();
        frame.setVisible(true);
    }
}
