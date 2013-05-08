package org.unice.m1.reseaux.sudoku.client.gui.tile;

import org.unice.m1.reseaux.sudoku.client.core.ActionPerformer;
import org.unice.m1.reseaux.sudoku.client.core.ActionPerformerIssuer;
import org.unice.m1.reseaux.sudoku.client.gui.MainWindow;
import org.unice.m1.reseaux.sudoku.client.net.Client;

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
    private static boolean isLocked;
    private int x,y;


    public Tile(int width,int x,int y){
        this.width = width;
        this.x = x;
        this.y = y;
        content =  new Display(this.width,0);
        this.add(this.content);
        this.setPreferredSize(new Dimension(this.width,this.width));
        addMouseListener(this);
        this.setBorder(new EmptyBorder(10, 10, 10, 10) );

    }
    public Tile(int width,int x,int y,int number){
        this.x = x;
        this.y = y;
        this.width = width;
        content =  new Display(width,number);
        //this.setPreferredSize(new Dimension(this.width,this.width));
        this.setPreferredSize(new Dimension(this.width,this.width));
        this.setBorder(new EmptyBorder(10, 10, 10, 10) );
        this.add(this.content);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(!isLocked() && this.content instanceof Display && ( ((Display)this.content).getNumber() <= 0 || ((Display)this.content).getNumber() >9)){
            lock();
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

    public void setNumber(int number){
        unlock();
        if(this.content instanceof Display){
            ((Display) this.content).setNumber(number);
        }else {
            this.remove(this.content);
            this.content =  new Display(this.width,number);
            this.add(this.content);
        }
        this.revalidate();

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

    private static boolean isLocked(){

        return isLocked;
    }

    private static void lock(){
        isLocked = true;
    }

    private static void unlock(){
        isLocked = false;
    }

    @Override
    public void performAction(Object sender) {
        if(sender instanceof Selector){
            Selector snd =(Selector)sender;
            this.remove(this.content);

            this.content = new Waiting(this.width);
            MainWindow.client.sendMove(this.x,this.y,snd.getNumber());
            this.add(this.content);
            this.revalidate();
        }
    }
}
