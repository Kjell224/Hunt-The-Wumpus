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

public class gameControl {
    ///////////////////////
    // Properties & Fields
    //////////////////////
    private Player player;
    private boolean isGameOver;
    private boolean isWumpusKilled;
    private int numberOfPlayers;
    private Scanner scanner;
    private Cave cave;
    private UITest uitest;

 
    ///////////////////////
    // Constructor(s)
    //////////////////////
    
    public gameControl() throws FileNotFoundException {
        this.cave = new Cave();
        this.uitest = new UITest(cave);
        numberOfPlayers = 0;
        player = new Player();
        isGameOver = false;
        isWumpusKilled = false;
    }
    public Cave getCave(){
        return this.cave;
    }


    ///////////////////////
    // Methods
    //////////////////////

     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // Ensure the UI is created on the Event Dispatch Thread
            MainMenu mainMenu = new MainMenu(); // Create the main menu
            mainMenu.setVisible(true); // Make the main menu visible
        });
    }
}
