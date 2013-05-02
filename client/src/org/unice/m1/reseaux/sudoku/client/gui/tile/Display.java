package org.unice.m1.reseaux.sudoku.client.gui.tile;

import javax.swing.*;
import java.awt.*;

/**
 * @author TEFFAHA Mortadha
 */
public class Display extends JPanel {
    private JLabel number;



    public Display(int width,int number){
        Font ft =  new Font("sans serif",Font.PLAIN,60);
        if(number > 0 && number <=9){
            this.number =  new JLabel(""+number);
        }else{
            this.number =  new JLabel("");
        }
        this.number.setPreferredSize(new Dimension(width-30,width-30));
        this.number.setFont(ft);
        this.number.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(this.number);


    }

    public Integer getNumber(){
         Integer number;

        try{
            return Integer.parseInt(this.number.getText());
        } catch(NumberFormatException ex){
            return 0;
        }
    }
    public void setNumber(int number){
        if(number > 0 && number <=9){
            this.number.setText(""+number);
        }else{
            this.number.setText("");
        }
    }



    public static void main(String[] args){
        JFrame frame =  new JFrame();
        Display display = new Display(60,0);
        frame.add(display);
        frame.pack();
        frame.setVisible(true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        display.setNumber(7);


    }
}
