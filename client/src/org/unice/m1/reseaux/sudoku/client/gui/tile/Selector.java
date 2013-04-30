package org.unice.m1.reseaux.sudoku.client.gui.tile;

import org.unice.m1.reseaux.sudoku.client.core.ActionPerformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TEFFAHA Mortadha
 *
 * A Class alowing the selection of one number.
 * the selection is done through a grid of buttons containing all the possible numbers
 */
public class Selector extends JPanel implements ActionListener{
    // the size of the grid
    private static final int WIDTH_GRID = 3;
    private static final int HEIGHT_GRID = 3;

    private List<ActionPerformer> performers; // the list of action performer to call once the number is read
    private int number;  // the read number

    private JButton[][] buttons; // a multidimential array holding the possible numbers


    /**
     * the default constructor
     */
    public Selector(){
        // Initialising the numbers
        this.buttons = new JButton[WIDTH_GRID][HEIGHT_GRID];
        this.setLayout(new GridLayout(WIDTH_GRID,HEIGHT_GRID));
        int number = 1;
        for(int i=0;i<WIDTH_GRID;i++){
            for(int j=0;j< HEIGHT_GRID;j++){
                this.buttons[i][j] = new JButton(""+(number++));
                this.add(this.buttons[i][j]);
                this.buttons[i][j].setPreferredSize(new Dimension(300,300));
                this.buttons[i][j].addActionListener(this);
            }
        }

        this.number = -1;
        // Intialising the performers Array
        this.performers =  new ArrayList<ActionPerformer>();
    }

    /**
     * Adds and Action performer to the list of performer to call once the number is read
     * @param performer  the performer to add
     */
    public void regiterActionPerformer(ActionPerformer performer){
        if(performer != null){
            this.performers.add(performer);
        }else{
            // TODO : Throw exception
        }
    }

    /**
     * removes a performer from the list of possible performers
     * @param performer the performer to remove
     */
    public void unRegisterActionPerformer(ActionPerformer performer){
        if(!this.performers.remove(performer)){
            // TODO : Throw exception
        }

    }


    /**
     * Get the number read
     * @return  returns the number read , or -1 if no number was read (this is supposed to be called by the action performer once his services are requested)
     */
    public int getNumber(){
        if(number>0 && number < 10){
            return this.number;
        }else{
            // TODO : throw exception
            return -1;
        }
    }
    public static void main(String[] args){
        JFrame frame =  new JFrame();
        frame.add(new Selector());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

    }

    /**
     * an implementation of ActionListener Interface for handling click event on the buttons
     * @param e this should countain the buttton as source otherwise error
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() != null && e.getSource() instanceof JButton){
            this.number =  Integer.parseInt((((JButton)e.getSource())).getText());
        }else{
            // TODO : throw exception
        }
    }
}

