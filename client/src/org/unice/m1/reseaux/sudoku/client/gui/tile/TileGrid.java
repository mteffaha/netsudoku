package org.unice.m1.reseaux.sudoku.client.gui.tile;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @author TEFFAHA Mortadha
 */
public class TileGrid extends  JPanel{
    private static final int WIDTH_GRID = 3;
    private static final int HEIGHT_GRID = 3;

    private JPanel[] panes;
    private int width;


    public TileGrid(int width){
        panes = new JPanel[WIDTH_GRID*HEIGHT_GRID];
        Border grayline = BorderFactory.createLineBorder(Color.GRAY);
        this.setLayout(new GridLayout(3,3));
        this.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        this.width = width;
        for(int i=0;i<WIDTH_GRID;i++){
            for(int j=0;j<HEIGHT_GRID;j++){
                panes[(i*WIDTH_GRID)+j] = new Tile(this.width/3);
                panes[(i*WIDTH_GRID)+j].setBorder(grayline);
                this.add( panes[(i*WIDTH_GRID)+j] );
            }
        }
    }



    public int getNumber(int x,int y){
        if(x < 0 || x > 3){
            // TODO throw exception
            return -1;
        }

        if(y < 0 || y > 3){
            // TODO throw exception
            return -1;
        }

        return ((Tile)panes[(y*WIDTH_GRID)+x]).getNumber();
    }

    public void setNumber(int x,int y, int number){

    }
    



    public static void main(String[] args){
        Frame frame =  new Frame();
        frame.add(new TileGrid(240));

        frame.pack();
        frame.setBounds(10,10,255,255);
        frame.setVisible(true);
    }
}
