package org.unice.m1.reseaux.sudoku.client.gui;


import org.unice.m1.reseaux.sudoku.client.core.Player;
import org.unice.m1.reseaux.sudoku.client.net.Client;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TEFFAHA Mortadha
 */
public class MainWindow extends JFrame {
    private JList<String> list;
    private List<String> listLogs;
    private JLabel listPlayers;
    public Player[] players;
    public int playersCount=0;
    public Grid grid;
    public static Client client;

    public MainWindow(String playerName) {

        // Getting Player Name

        grid =  new Grid(800);
        grid.setNumber(5,5,5);
        JPanel rightPanel =  new JPanel();
        rightPanel.setLayout(new BorderLayout());
        list =  new JList<String>();
        players = new Player[5];



        listLogs =  new ArrayList<String>();
        list.setPreferredSize(new Dimension(300,500));
        listPlayers =  new JLabel("<html><head><style type=\"text/css\">td{padding:5px;background-color:#CCCCCC;}tr#title td{background-color:white;} </style></head><body><table><tr colspan=\"3\">Liste des Joeurs</tr><tr id=\"title\"><td>Nom</td><td>Score</td><td>Penalité</td></tr><tr><td>Nom</td><td>Score</td><td>Penalité</td></tr></table></body>");



        rightPanel.add(list,BorderLayout.SOUTH);
        rightPanel.add(listPlayers,BorderLayout.NORTH);

        setLayout(new BorderLayout());
        add(rightPanel,BorderLayout.WEST);
        add(grid,BorderLayout.EAST);

        String name = "";
        while(true){
            name = JOptionPane.showInputDialog(null, "Entrez votre nom pour vous connecté au serveur :  ",
                    "", 1);
            if(name != null){
                if(name.compareTo("") == 0){
                    JOptionPane.showMessageDialog(null, "Le Nom vide n'est pas accepter",
                            "", 1);
                } else{
                    client = new Client(this,name);
                    client.init();
                    break;
                }


            }
            else
                System.exit(0);
        }




    }

    public void updateListPlayers(){
        String str= "<html><head><style type=\"text/css\">td{padding:5px;background-color:#CCCCCC;}tr#title td{background-color:white;} </style></head><body><table><tr colspan=\"3\">Liste des Joeurs</tr><tr id=\"title\"><td>Nom</td><td>Score</td><td>Penalité</td></tr>";
        for(int i=0;i<5;i++){
            if(players[i] != null){
                str+="<tr><td>"+players[i].getName()+"</td><td style=\"color:green;\">"+players[i].getScore()+"</td><td style=\"color:red;\">"+players[i].getPenality()+"</td></tr>";
            }
        }

        str+="</table></body>";
        listPlayers.setText(str);

    }

    public void addLog(String message){
        listLogs.add(message);

        String[] array =  new String[listLogs.size()];
        for(int i=0;i<array.length;i++){
            array[i] = listLogs.get(i);
        }
        list.setListData(array);
    }


    public static void main(String[] args){
        MainWindow window =  new MainWindow("Player Name");
        window.pack();
        window.setVisible(true);

    }

    public void setNumberIfLocked(int x, int y, int number) {

        grid.setNumber(x,y,number);
    }
}


