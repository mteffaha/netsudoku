package org.unice.m1.reseaux.sudoku.server;

import org.unice.m1.reseaux.sudoku.server.net.Server;
import org.unice.m1.reseaux.sudoku.server.sudoku.SudokuGrid;
import org.unice.m1.reseaux.sudoku.server.sudoku.SudokuSolver;

import java.net.URL;

/**
 * @author TEFFAHA Mortadha
 */
public class main {


    public static void main(String[] args){
        Server.getInstance().start();
    }
}
