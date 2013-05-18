package org.unice.m1.reseaux.sudoku.server.net;

import org.unice.m1.reseaux.sudoku.server.Logger;

import java.io.*;
import java.net.Socket;

/**
 * @author TEFFAHA Mortadha
 */
public class Player implements Runnable {
    private static final int MAX_PLAYERS = 5;
    private static Player[] players = new Player[5];
    private static int count = 0;
    private int id;


    private String name;

    public void setScore(int score) {
        this.score = score;
    }

    private int score = 0;
    private int penality = 0;
    private InputStream in;
    private OutputStream out;

    public static void addPlayer(Socket socket) {
        if (count >= MAX_PLAYERS) {
            Logger.Log("ERROR !! Max players reached");
            return;
        }
        // Read player name
        BufferedReader in = null;
        try {

            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            players[count] = new Player(socket);
            char[] chars = new char[512];
            in.read(chars);
            players[count].name = new String(chars).trim();
            players[count].id = count;
            new Thread(players[count]).start();
            Server.getInstance().BroadcastMessage("003"+players[count]+"\n");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        Logger.Log("Connection With client " + players[count].name + " establiched");
        count++;
    }

        Socket clientSocket = null;

    public static int getCountPlayers() {
        return count;
    }
    public String toString(){
        return this.id+":"+name;
    }

    public static Player getPlayer(int index) {
        if (index >= 0 && index < count) {
            return players[index];
        }
        Logger.Log("ERROR !! unvalid player index");
        return null;
    }

    private Socket socket;

    private Player(Socket socket) {
        this.socket = socket;
    }


    public void sendMessage(String Message){
        try {
            out.write(Message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Override
    public void run() {
        Logger.Log("Thread for :" + this.name + " Started");


        try {
            out = socket.getOutputStream();
            in = socket.getInputStream();
            out.write(Server.getInstance().GameInfo(this.id).getBytes());


            while (socket.isConnected()) {
                int[] move =  new int[3];
                move[0] = in.read();
                move[1] = in.read();
                move[2] = in.read();
                if(Server.getInstance().playMove(move[0],move[1],move[2])){
                     // Broadcast that this player won
                    score++;
                    Logger.Log("Player : "+getName()+" Played a Good Move X:"+move[0]+" Y:"+move[1]+" N:"+move[2]);
                    Server.getInstance().BroadcastMessage("002"+move[0]+""+move[1]+""+move[2]+id);
                }else{
                    if(Server.getInstance().isGoodMove(move[0],move[1],move[2])){
                        // broadcast this player failure without penality
                        Server.getInstance().BroadcastMessage("001"+move[0]+""+move[1]+""+move[2]+id);
                        Logger.Log("Player : "+getName()+" Played a Good Move, but too late X:"+move[0]+" Y:"+move[1]+" N:"+move[2]);
                    }else{
                        // broadcast this player failure with penality
                        penality++;
                        Server.getInstance().BroadcastMessage("000"+move[0]+""+move[1]+""+move[2]+id);
                        Logger.Log("Player : "+getName()+" Played a Bad Move X:"+move[0]+" Y:"+move[1]+" N:"+move[2]);


                    }


                }

            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Logger.Log("Connection with " + this.name + " Closed");
    }

    public int getScore() {
        return score;
    }


    public void setPenality(int penality) {
        this.penality = penality;
    }

    public int getPenality() {
        return penality;
    }

    public String getName(){
        return name;
    }
}
