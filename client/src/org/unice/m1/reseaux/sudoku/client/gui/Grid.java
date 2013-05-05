package org.unice.m1.reseaux.sudoku.client.gui;

import org.unice.m1.reseaux.sudoku.client.gui.tile.Tile;
import org.unice.m1.reseaux.sudoku.client.gui.tile.TileGrid;

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
        if(x > 8 || x < 0 || y > 8 || y < 0){
            // TODO : throw Exception
            return -1;
        }

        // With pass the request to the appropriate grids, and pass the relative positions
        return grids[((y/3)*3)+(x/3)].getNumber(x%3,y%3);
    }

    public void setNumber(int x,int y, int number){

    }


    public static void main(String[] args){
        JFrame frame =  new JFrame();
        Grid grid =  new Grid(800);
        frame.add(grid);



        frame.pack();
        frame.setVisible(true);
        while(true){
        try {
            Thread.sleep(1000);
            for(int i=0;i<9;i++){
                String line = "";
                for(int j=0;j<9;j++){
                    line+="["+grid.getNumber(i,j)+"]";
                }
                System.out.println(""+line);
            }
            System.out.println("--------------");
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        }
    }
}
