package org.unice.m1.reseaux.sudoku.client.gui.tile;

import org.unice.m1.reseaux.sudoku.client.core.ActionPerformer;
import org.unice.m1.reseaux.sudoku.client.core.ActionPerformerIssuer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author TEFFAHA Mortadha
 */
public class Tile extends JPanel implements MouseListener, ActionPerformer{

    private JPanel content;
    private int width = 0;
    public Tile(int width){
        this.width = width;
        content =  new Display(this.width,0);
        this.add(this.content);
        this.setPreferredSize(new Dimension(this.width,this.width));
        addMouseListener(this);
        this.setBorder(new EmptyBorder(10, 10, 10, 10) );

    }
    public Tile(int width,int number){
        content =  new Display(width,number);
        //this.setPreferredSize(new Dimension(this.width,this.width));
        this.add(this.content);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(this.content instanceof Display && ( ((Display)this.content).getNumber() <= 0 || ((Display)this.content).getNumber() >9)){
            System.out.println("Click Event");
            this.remove(this.content);
            this.content = new Selector(this.width);
            ((ActionPerformerIssuer)this.content).regiterActionPerformer(this);
            this.add(this.content);
            this.revalidate();
        }
    }


    public int getNumber(){
        if(this.content instanceof Display){
             return ((Display)this.content).getNumber();
        }else{
            return -1;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void main(String[] args){
        JFrame frame =  new JFrame();
        Tile tile =  new Tile(69);
        frame.add(tile);
        frame.setVisible(true);
        frame.pack();
    }

    @Override
    public void performAction(Object sender) {
        System.out.println("PerformAction");
        if(sender instanceof Selector){
            Selector snd =(Selector)sender;
            this.remove(this.content);
            this.content = new Display(this.width,snd.getNumber());
            this.add(this.content);
            this.revalidate();

        }
    }
}
