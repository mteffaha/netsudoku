package org.unice.m1.reseaux.sudoku.client.gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author TEFFAHA Mortadha
 */
public class Connection extends JPanel{

    private JButton connect;
    private JLabel nameLabel;
    private JTextField nameField;

    public Connection(){
        GroupLayout layout =  new GroupLayout(this);
        connect =  new JButton("Connect");
        nameLabel =  new JLabel("Name :");
        nameField =  new JTextField();


        connect.setPreferredSize(new Dimension(100, 25));
        nameLabel.setPreferredSize(new Dimension(100, 25));
        nameField.setPreferredSize(new Dimension(100, 25));


        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameField)
                                .addComponent(connect))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabel)
                                .addComponent(nameField))
                                .addComponent(connect)
        );
        this.setLayout(layout);
        this.add(connect);
        this.add(nameField);
        this.add(nameLabel);
        this.setPreferredSize(new Dimension(250,150));
        this.setBorder(BorderFactory.createCompoundBorder());

    }


    public static void main(String[] args){
        while(true){
        String str = JOptionPane.showInputDialog(null, "Entrez votre nom pour vous connecté au serveur :  ",
                "", 1);
        if(str != null){
            if(str.compareTo("") == 0){
                JOptionPane.showMessageDialog(null, "Le Nom vide n'est pas accepter",
                        "", 1);
            } else{
                System.out.println("Nom :"+str);
                break;
            }


        }
        else
            break;
        }
    }

}
