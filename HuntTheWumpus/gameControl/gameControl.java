package gameControl;

import java.util.ArrayList;
// Mateen and Eyouel 
// February 12, 2024
// Period 5
// Hunt the Wumpus - Game Control Class
import java.util.Scanner;

import Cave.CellMap;
import Player.Player;
import javafx.scene.control.Cell;

public class gameControl {
    ///////////////////////
    // Properties & Fields
    //////////////////////
    private CellMap cellMap;
    private Player player;
    private boolean isGameOver;
    private boolean isWumpusKilled;
    private int numberOfPlayers;
    private Scanner scanner;

    public enum Direction {
        UP,
        DOWN,
        UP_LEFT,
        UP_RIGHT
    }
    ///////////////////////
    // Constructor(s)
    //////////////////////

    @SuppressWarnings("rawtypes")
    public gameControl() {
        numberOfPlayers = 0;
        cellMap = new CellMap(new Cell()); 
        player = new Player();
        isGameOver = false;
        isWumpusKilled = false;
    }
    private Cave.Cell getNextCell(Cell currentCell, Direction direction) {
        ArrayList<Cave.Cell> adjacents = cellMap.allAjacents(currentCell);
        int targetValue = -1;
        
        switch (direction) {
            case UP:
                targetValue = cellMap.getUp(currentCell.getValue());
                break;
            case DOWN:
                targetValue = cellMap.getDown(currentCell.getValue());
                break;
            case UP_LEFT:
                targetValue = cellMap.getUpLeft(currentCell.getValue());
                break;
            case UP_RIGHT:
                targetValue = cellMap.getUpRight(currentCell.getValue());
                break;
        }

        for (Cave.Cell cell : adjacents) {
            if (cell.getValue() == targetValue) {
                return cell;
            }
        }
        return null;
    }


    ///////////////////////
    // Methods
    //////////////////////

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isWumpusKilled() {
        return isWumpusKilled;
    }
}