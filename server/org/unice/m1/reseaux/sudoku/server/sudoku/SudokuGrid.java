package org.unice.m1.reseaux.sudoku.server.sudoku;

import java.io.*;
import java.net.URL;

/**
 * @author TEFFAHA Mortadha
 */
public class SudokuGrid {

    private static final int WIDTH_GRID = 9;
    private int[][] grid;

    public SudokuGrid(URL fileURL) {

        grid = new int[WIDTH_GRID][WIDTH_GRID];

        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(fileURL.openStream()));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        String line = "";
        try {
            int j=0;
            while ((line = br.readLine()) != null) {
                char[] numbers = line.toCharArray();
                for (int i = 0; i < WIDTH_GRID; i++) {
                    grid[j][i] = Integer.parseInt(numbers[i]+"");
                }
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public SudokuGrid(){
        grid =  new int[WIDTH_GRID][WIDTH_GRID];
        initGrid();
    }


    public void setNumber(int x,int y,int number){
        if(number > 9 || number <=0){
            // TODO : throw exception
            return;
        }
        if(x >= 9 || x < 0 || y >= 9 || y < 0){
            //TODO : throw exception
            return;
        }
        grid[x][y] = number;
    }


    public String toString(){
        String str = "";
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                str+=""+grid[i][j];
            }
            str+="\n";
        }
        return str;
    }
    public int getNumber(int x,int y){

        if(x >= 9 || x < 0 || y >= 9 || y < 0){
            //TODO : throw exception
            return -1;
        }
        return grid[x][y];

    }




    public void initGrid() {
        for(int i =0;i<WIDTH_GRID;i++){
            for(int j=0;j<WIDTH_GRID;j++){
                grid[i][j] = 0;
            }
        }
    }
}
