package org.unice.m1.reseaux.sudoku.client.gui;

import org.unice.m1.reseaux.sudoku.client.gui.tile.Tile;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @author TEFFAHA Mortadha
 */
public class Grid extends JPanel {

    private static final int WIDTH_GRID = 9;

    private int width;
    private Tile[] tiles;
    private JPanel[] groups;

    public Grid(int width){
        this.width = width;

        tiles = new Tile[WIDTH_GRID*WIDTH_GRID];
        groups =  new JPanel[WIDTH_GRID];
        this.setLayout(new GridLayout(3,3));

        for(int i=0;i<WIDTH_GRID;i++){
            groups[i] =  new JPanel();
            groups[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            groups[i].setLayout(new GridLayout(3, 3));
            groups[i].setPreferredSize(new Dimension(this.width/3,this.width/3));
            add(groups[i]);
        }

        for(int i=0;i<WIDTH_GRID*WIDTH_GRID;i++){
            tiles[i] = new Tile(this.width/9,indexToX(i),indexToY(i));
            tiles[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            groups[(indexToY(i)/3)*3+(indexToX(i)/3)].add(tiles[i]);
        }
    }

    private int indexToX(int index){
        return index%WIDTH_GRID;
    }

    private int indexToY(int index){
        return index/WIDTH_GRID;
    }

    public int getNumber(int x,int y){
        if(x > 8 || x < 0 || y > 8 || y < 0){
            // TODO : throw Exception
            return -1;
        }

        // With pass the request to the appropriate grids, and pass the relative positions
        return -1;//return grids[((y/3)*3)+(x/3)].getNumber(x%3,y%3);
    }

    public void setNumber(int x,int y, int number){

        tiles[y*WIDTH_GRID+x].setNumber(number);
    }


    public static void main(String[] args){
        JFrame frame =  new JFrame();
        Grid grid =  new Grid(800);
        frame.add(grid);

         frame.pack();

        frame.setVisible(true);


    }
}
