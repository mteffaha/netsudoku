package org.unice.m1.reseaux.sudoku.server.net;

import org.unice.m1.reseaux.sudoku.server.Logger;
import org.unice.m1.reseaux.sudoku.server.sudoku.SudokuGrid;
import org.unice.m1.reseaux.sudoku.server.sudoku.SudokuSolver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.concurrent.Semaphore;

/**
 * @author TEFFAHA Mortadha
 */
public class Server {

    private static final int PORT_NUMBER = 15866;

    private ServerSocket serverSocket;

    private int port;
    private SudokuGrid solution;
    private SudokuGrid original;
    private Semaphore mutex;

    private static Server instance;
    public static Server getInstance(){
        if(instance == null){
            instance =   new Server(PORT_NUMBER);
        }
        return instance;
    }


    private Server(int port){
        this.port = port;
        this.mutex =  new Semaphore(1);
        try {
            serverSocket =  new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }



    }


    public String GameInfo(int id){
        String str= "";
        str+=original.toString();
        str+=id+":"+Player.getCountPlayers()+"\n";
        for(int i=0;i<Player.getCountPlayers();i++){

            str+=i+"//"+Player.getPlayer(i).getName()+"//"+Player.getPlayer(i).getScore()+"//"+Player.getPlayer(i).getPenality()+"\n";
        }
        return str;
    }

    public boolean playMove(int x,int y,int number){

        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if(original.getNumber(x,y) == 0 && solution.getNumber(x,y)== number){
            original.setNumber(x,y,number);
            mutex.release();
            return true;
        }
        mutex.release();
        return false;
    }

    public boolean isGoodMove(int x,int y,int number){
        if(solution.getNumber(x,y) == number){
            return true;
        }
        return false;
    }

    public void BroadcastMessage(String message){
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        for(int i =0;i<Player.getCountPlayers();i++){
             Player.getPlayer(i).sendMessage(message);
        }
        mutex.release();
    }


    public void start(){
        // Read Sudoku Map
        URL path = this.getClass().getResource("/levels/pb1.txt");

        original =    new SudokuGrid(path);
        Logger.Log("Sudoku Puzzle Loaded:");
        Logger.Log(original.toString());

        // Calculate Solution
        solution =  SudokuSolver.solveGrid(original);
        Logger.Log("Solution Calculated:");
        Logger.Log(solution.toString());

        Logger.Log("Listening in on Port : "+port);
        // Listen to game until game done
        boolean open = true;
        while(true){
            try {
                Socket socket = serverSocket.accept();

                Player.addPlayer(socket);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
