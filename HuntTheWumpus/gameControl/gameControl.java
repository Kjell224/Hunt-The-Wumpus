package gameControl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
// Mateen and Eyouel 
// February 12, 2024
// Period 5
// Hunt the Wumpus - Game Control Class
import java.util.Scanner;

import javax.swing.SwingUtilities;

import Cave.*;
import gameLocations.*;
import Player.Player;
import Wumpus.Wumpus;
import UI.MainMenu;
import UI.UITest;
import Trivia.*;

public class gameControl {
    ///////////////////////
    // Properties & Fields
    //////////////////////
    private Player player;
    private Wumpus wumpus;
    private Cave cave;
    private gameLocations gL;
    private Scanner scanner;
    private MainMenu mainmenu;
    private UITest uitest;
    private Trivia trivia;

    ///////////////////////
    // Constructor(s)
    //////////////////////
    
    public gameControl() throws FileNotFoundException {
            this.trivia = new Trivia();
            System.out.println("just re initialized");
            this.cave = new Cave();
            this.gL = new gameLocations();
            this.player = new Player(this);
            this.wumpus = new Wumpus(this);
            SwingUtilities.invokeLater(() -> { // Ensure the UI is created on the Event Dispatch Thread
            this.mainmenu = new MainMenu(this); // Create the main menu
            this.uitest = mainmenu.getUITest();
            this.mainmenu.setVisible(true); // Make the main menu visible
        });

    }

    public Cave getCave(){
        return this.cave;
    }

    public Player getPlayer(){
        return this.player;
    }

    public Trivia getTrivia(){
        return this.trivia;
    }

    public Wumpus getWumpus(){
        return this.wumpus;
    }


    ///////////////////////
    // Methods
    //////////////////////

}
