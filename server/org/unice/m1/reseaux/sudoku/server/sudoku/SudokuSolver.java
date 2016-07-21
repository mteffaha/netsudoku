package org.unice.m1.reseaux.sudoku.server.sudoku;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;

import java.io.*;
import java.util.*;

public class SudokuSolver {

    // ATTENTION:
    // les lignes et colonnes 0 ne sont pas utilis�es
    int[][] T = new int[10][10];
    int[][] TInit = new int[10][10];

    public SudokuSolver() {
    }

    public void zero() {
        int i, j;

        for (i = 1; i <= 9; i++)
            for (j = 1; j <= 9; j++) {
                T[i][j] = 0;
                TInit[i][j] = 0;
            }
    }

    // valeurs non nulles
    void init() {
        int i, j;

        for (i = 1; i <= 9; i++)
            for (j = 1; j <= 9; j++) {
                T[i][j] = TInit[i][j];
            }
    }

    // on teste l'unicite d'une valeur dans son petit carre
    boolean estUniqueDansPetitCarre(int x, int y, int v) {
        int origineXCarre, origineYCarre;

        origineXCarre = ((x - 1) / 3) * 3 + 1;
        origineYCarre = ((y - 1) / 3) * 3 + 1;

        int i, j;

        for (i = origineXCarre; i <= origineXCarre + 2; i++)
            for (j = origineYCarre; j <= origineYCarre + 2; j++) {
                if ((i == x) && (j == y)) continue;
                if (T[i][j] == v) return false;
            }

        return true;
    }

    // on teste la validite d'une valeur � une position donnee
    boolean estValide(int x, int y, int v) {
        if (T[x][y] != 0) return (v == T[x][y]);

        int i, j;

        for (i = 1; i <= 9; i++) {
            if (i == x) continue;
            if (T[i][y] == v) return false;
        }

        for (j = 1; j <= 9; j++) {
            if (j == y) continue;
            if (T[x][j] == v) return false;
        }

        return estUniqueDansPetitCarre(x, y, v);
    }

    boolean estSolution(int x, int y, int v) {
        //	System.out.println (x + " " +  y + " " + v);

        if (!estValide(x, y, v)) return false;

        // on essaye
        T[x][y] = v;

        // on a trouve UNE solution; on s'arrete la!
        if ((x == 9) && (y == 9)) return true;

        // appel recursif
        int i;
        if (y == 9) {
            for (i = 1; i <= 9; i++)
                if (estSolution(x + 1, 1, i)) return true;

            // la solution proposee ne marche pas; backtrack
            if (TInit[x][y] == 0) T[x][y] = 0;
            return false;
        }

        for (i = 1; i <= 9; i++)
            if (estSolution(x, y + 1, i)) return true;

        // la solution proposee ne marche pas; backtrack
        if (TInit[x][y] == 0) T[x][y] = 0;
        return false;
    }

    public void initFromGrid(SudokuGrid grid){
        for(int i=1;i<=9;i++){
            for(int j=0;j<=9;j++){
                TInit[i][j] = grid.getNumber(i-1,j-1);
            }
        }
    }


    public static SudokuGrid solveGrid(SudokuGrid grid){
        SudokuGrid solution = null;
        SudokuSolver solver = new SudokuSolver();
        solver.zero();
        solver.initFromGrid(grid);

        int i;
        solver.init();
        for (i=1; i<=9; i++) {
            if (solver.estSolution (1, 1, i)){
                return solver.getAsSudokuGrid();
            }
        }

        return solver.getAsSudokuGrid();
    }

    public SudokuGrid getAsSudokuGrid(){

        SudokuGrid grid =  new SudokuGrid();
        int i, j;

        for (i=1; i<=9; i++) {
            for (j=1; j<=9; j++)
                grid.setNumber(i-1,j-1,T[i][j]);
        }
        return grid;

    }


    public static void main(String args[]) {

    }
}
 