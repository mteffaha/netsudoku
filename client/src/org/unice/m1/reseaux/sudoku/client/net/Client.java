package org.unice.m1.reseaux.sudoku.client.net;

import org.unice.m1.reseaux.sudoku.client.core.Player;
import org.unice.m1.reseaux.sudoku.client.gui.MainWindow;

import java.io.*;
import java.net.Socket;

/**
 * @author TEFFAHA Mortadha
 */
public class Client extends Player  {
    private static final String SERVER_ADRESS = "0.0.0.0";
    private static final int SERVER_PORT = 15866;
    private OutputStream out;
    private BufferedReader in;
    private MainWindow mainWindow;

    private Player[] opposants;
    private int playersCount;
    private Socket clientSocket;
    private String name;


    public Client(MainWindow window, String name) {
        mainWindow = window;
        this.name = name;
        opposants = new Player[5];
        try {
            clientSocket = new Socket(SERVER_ADRESS, SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            out = clientSocket.getOutputStream();
            in  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {
            out.write(name.getBytes());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public void init() {
        try {
            char[] number= new char[1];


            // Fetching the grid
            for (int i = 0; i < 9; i++) {

                for (int j = 0; j < 9; j++) {
                    in.read(number);
                    mainWindow.grid.setNumber(j, i, Integer.parseInt(new String(number)));

                }
                in.read(number);
            }

            // Fetching the players Info


            String str= in.readLine();
            System.out.println(""+str);

            Integer count = Integer.parseInt(str);
            for(int i=0;i<count;i++){
                str = in.readLine();
                System.out.println(""+str);
                String[] info = str.split("//");
                this.mainWindow.players[i] =  new Player();
                this.mainWindow.players[i].setName(info[1]);
                this.mainWindow.players[i].setScore(Integer.parseInt(info[2]));
                this.mainWindow.players[i].setPenality(Integer.parseInt(info[3]));
                this.mainWindow.players[i].setID(Integer.parseInt(info[0]));
                this.mainWindow.updateListPlayers();
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true){
                            int[] msg =  new int[7];
                            for(int i=0;i<7;i++){
                            msg[i] = Integer.parseInt(""+Character.toString((char)in.read()));
                            }
                            switch(msg[2]){
                                case 0:  // Player Lost
                                    mainWindow.players[msg[6]].setPenality(mainWindow.players[msg[6]].getPenality()+1);
                                    mainWindow.setNumberIfLocked(msg[3],msg[4],0);

                                    mainWindow.updateListPlayers();

                                    mainWindow.addLog("Player :"+mainWindow.players[msg[6]].getName()
                                            +" Made a bad move : X:" +(msg[3]+1)
                                            +" Y:"+(msg[4]+1)
                                            +" Number :"+msg[5]);
                                    break;
                                case 1: // Player Lost without penality
                                    mainWindow.addLog("Player :"+mainWindow.players[msg[6]].getName()+" Made a correct move : X:"+(msg[3]+1)+" Y:"+(msg[4]+1)+" Number :"+msg[5]+" but too late to win points ");
                                    mainWindow.setNumberIfLocked(msg[3],msg[4],msg[5]);
                                    break;
                                case 2: // Player Marked
                                    mainWindow.addLog("Player :"+mainWindow.players[msg[6]].getName()+" Made a correct move : X:"+(msg[3]+1)+" Y:"+(msg[4]+1)+" Number :"+msg[5]);
                                    mainWindow.players[msg[6]].setScore(mainWindow.players[msg[6]].getScore()+1);
                                    mainWindow.setNumberIfLocked(msg[3],msg[4],msg[5]);
                                    mainWindow.updateListPlayers();
                                    break;

                            }
                            // TODO : check broadcast information
                        }
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public void sendMove(int x,int y,int number){
        System.out.println("Move made");
       byte[] buffer = new byte[3];
        buffer[0] = (byte)x;
        buffer[1] = (byte)y;
        buffer[2] = (byte)number;
        try {
            out.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
