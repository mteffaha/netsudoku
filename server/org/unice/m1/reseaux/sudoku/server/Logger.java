package org.unice.m1.reseaux.sudoku.server;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author TEFFAHA Mortadha
 */
public class Logger {

    public static void Log(String message){
        String[] lines = message.split("\n");
        for(int i=0;i<lines.length;i++){
           printMessage(lines[i]);
        }

    }

    private static void printMessage(String message){
        Date date =  new Date();
        System.out.println("[Server]["+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(date)+"] "+message);
    }
}
