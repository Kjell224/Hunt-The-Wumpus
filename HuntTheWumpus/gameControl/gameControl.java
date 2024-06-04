package gameControl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
// Mateen and Eyouel 
// February 12, 2024
// Period 5
// Hunt the Wumpus - Game Control Class
import java.util.Scanner;

import javax.swing.SwingUtilities;

import Cave.Cave;
import Player.Player;
import UI.MainMenu;
import UI.UITest;
import Trivia.Trivia;

public class gameControl {
    ///////////////////////
    // Properties & Fields
    //////////////////////
    private Player player;
    private Cave cave;
    private Scanner scanner;
    private MainMenu mainmenu;
    private UITest uitest;
    private Trivia trivia;

 
    ///////////////////////
    // Constructor(s)
    //////////////////////
    
    public gameControl() throws FileNotFoundException {
            this.cave = new Cave();
            this.trivia = new Trivia();
            SwingUtilities.invokeLater(() -> { // Ensure the UI is created on the Event Dispatch Thread
            this.mainmenu = new MainMenu(this.cave); // Create the main menu
            this.mainmenu.setVisible(true); // Make the main menu visible
        });
    }

    public Cave getCave(){
        return this.cave;
    }

    public Player getPlayer(){
        return this.player;
    }

    public UITest getUITest(){
        return this.uitest;
    }


    ///////////////////////
    // Methods
    //////////////////////

}