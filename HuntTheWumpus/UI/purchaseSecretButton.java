package UI;

import javax.swing.*;
import Cave.Cave;
import Wumpus.Wumpus;
import Player.Player;
import java.awt.*;
import java.awt.event.ActionEvent;
import gameLocations.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Trivia.Trivia;
import Cave.Cell;
import Player.*;
import Trivia.Question;
import gameControl.*;

public class purchaseSecretButton extends JButton{
    gameLocations gL;
    int buttonWidth = 150;
    int buttonHeight = 50;
    int rightSideX = 1050;
    int rightSideY = 350;
    public gameControl gc;
    
    public purchaseSecretButton(gameControl gc){
        this.gc = gc;
        this.gL = gc.getGameLocations();
    }

    private void addRightSideButtons() {

        JButton shootArrowButton = new JButton("Shoot Arrow");
        shootArrowButton.setBounds(rightSideX, rightSideY, buttonWidth, buttonHeight);
        shootArrowButton.addActionListener(e -> gc.getMainMenu().getUITest().shootArrow());
        shootArrowButton.setBackground(new java.awt.Color(255, 253, 159));
        add(shootArrowButton);
    
        JButton getArrowButton = new JButton("Get Arrow");
        getArrowButton.setBounds(rightSideX, rightSideY + 70, buttonWidth, buttonHeight);
        getArrowButton.addActionListener(e -> gc.getMainMenu().getUITest().getArrow());
        getArrowButton.setBackground(new java.awt.Color(255, 253, 159));
        add(getArrowButton);
    
        JButton purchaseSecretButton = new JButton("Purchase Secret");
        purchaseSecretButton.setBounds(rightSideX, rightSideY + 140, buttonWidth, buttonHeight);
        purchaseSecretButton.addActionListener(e -> purchaseSecret());
        purchaseSecretButton.setBackground(new java.awt.Color(255, 253, 159));
        add(purchaseSecretButton);
    }
    
    private void purchaseSecret(){
        int right = 0;
        for(int c = 0; c < 3; c++){
            Question question = gc.getTrivia().getQuestion(); // Get a trivia question
            String userAnswer = JOptionPane.showInputDialog(this, question.printQuestion()); // Prompt the user for an answer
            // Check the answer
            if (userAnswer != null && userAnswer.equalsIgnoreCase(question.getAnswer())) {
                JOptionPane.showMessageDialog(this, "Correct!");
                right++; // Increment the amount right
            } else {
                JOptionPane.showMessageDialog(this, "Wrong.");
            }
                if(right >= 2) break;
            }
            if(right < 2) return;
            
            //Not useful to useful 1-6
            int secretType = (int)(Math.random()*6) + 1;
            int randBatOrPitPos = (int)(Math.random()*2);
            if(secretType == 1) JOptionPane.showMessageDialog(this, "Not Useful! You are in cell " + gL.getPlayerLocation());
            else if(secretType == 2) JOptionPane.showMessageDialog(this, "Not Useful! The Answer to an old trivia question is " + gc.getTrivia().getRandomAnswer()); // FInd a way to give an answer to a trivia question you already got
            else if(secretType == 3){
                Cell pCell = gc.getCave().getCell(gL.getPlayerLocation());
                Integer[] neighbors = pCell.getAllNeighbors();
                boolean wumpusFound = false;
                for(Integer i : neighbors){
                    Cell curCell = gc.getCave().getCell(i);
                    if(curCell.getType().equals("Wumpus")){
                        JOptionPane.showMessageDialog(this, "Useful! A Wumpus is within 2 rooms of you.");
                        wumpusFound = true;
                        break;
                    } else {
                        Integer[] neighbors2 = curCell.getAllNeighbors();
                        for(Integer j : neighbors2){
                            Cell curCell2 = gc.getCave().getCell(j);
                            if(curCell2.getType().equals("Wumpus")){
                                JOptionPane.showMessageDialog(this, "Useful! A Wumpus is within 2 rooms of you.");
                                wumpusFound = true;
                                break;
                            }
                        }
                        if (wumpusFound) break; // Break the outer loop if a Wumpus is found in the inner loop
                    }
                if(!wumpusFound) JOptionPane.showMessageDialog(this, "Useful! A Wumpus is NOT within 2 rooms of you."); //Find a way to give if wumpus is within 2 of player
            }
        }
            else if(secretType == 4) JOptionPane.showMessageDialog(this, "Useful! A swarm of SuperBats is in room " + gL.getBatsLocation()[randBatOrPitPos]);
            else if(secretType == 5) JOptionPane.showMessageDialog(this, "Useful! A Pit is in room " + gL.getPitsLocation()[randBatOrPitPos]);
            else if(secretType == 6) JOptionPane.showMessageDialog(this, "Very Useful! The Wumpus is in cell" + gL.getWumpusLocation());
    }
}
    