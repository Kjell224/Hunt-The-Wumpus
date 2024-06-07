package Wumpus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import Cave.Cave;
import Cave.Cell;
import Player.Player;

public class Wumpus {
    //properties
    public Cell WumpCell;
    public Cave cave;
    public int cycleTurns;
    public int cappedRND;
    public String state;
    public int health;
    public Player player;
    //constuctor
    public Wumpus() {
        this.WumpCell = cave.getCell(cave.getWumpusCell());
        this.state = "asleep";
        this.health = 1;
        this.cycleTurns = 0;
        this.cappedRND = getLazyRND(); //this is the counter for when it is in its "moving" state (up to 3)
    }
    //methods

    public int getLazyRND(){
        int rnd = (int) Math.random() * 6 + 5;
        return rnd;
    }

    public void turn(){
        //if wumpus asleep do the count time
        cycleTurns++;
        if(getState().equals("asleep")){
            if(getCycleTurns() != getCappedRND()){
                move(1);
                setCycleTurns(0);
            }
        } else if(getState().equals("moving")){
            if(getCycleTurns() != getCappedRND()){
                move(2);
                setCycleTurns(0);
            }
        } else { //wumpus is awake
            teleport();
        }
    }

    //move(int howmany)
    public int move(int cellAmount){
        for(int i = 1; i <= cellAmount; i++){
            setWumpCell(cave.getRandomAccesibleCell(getWumpCell()));
        }
        return getWumpCell().getCellNum();
    }

    //teleport // only when wumpus is active
    public int teleport(){
        int rnd = (int) Math.random() * 20 + 1;
        if (rnd == 20){
            setWumpCell(cave.getRandomCell());
        }
        return getWumpCell().getCellNum();
    }

    //miss // sets the state to awake
    public void miss(){
        setState("awake");
        move(2);
    }
    //triviaRight (int correctnum) // if num is more than 3 wumpus dies (possibly done in game control), if less than 3 player dies (done in game Control)
    public void triviaFight(int correct){
        if(correct >= 3){
            //the player survived
            //wumpus runs away
            if(getState().equals("asleep")){
                move(3);
                setState("moving");
                int angryTurn = 0;
            } else if(getState().equals("awake") || getState().equals("moving")){

            }
        }
    }

    public boolean WumpusWins(){
        if (WumpusLoses() == false && playerDeath() == true){
            return true;
        } else {
            return false;
        }
    }

    public boolean WumpusLoses(){
        if (getHealth() == 0){
            return true;
        } else{
            return false;
        }
    }

    // This method is for when the player dies
    public boolean playerDeath(){
        if (player.getHealth() == 0){
            return true;
        } else {
            return false;
        }
    }

    public Cave getCave() {
        return cave;
    }

    public int getCycleTurns() {
        return cycleTurns;
    }

    public int getCappedRND() {
        return cappedRND;
    }

    public Player getPlayer() {
        return player;
    }

    public void setCave(Cave cave) {
        this.cave = cave;
    }

    public void setCycleTurns(int cycleTurns) {
        this.cycleTurns = cycleTurns;
    }

    public void setCappedRND(int cappedRND) {
        this.cappedRND = cappedRND;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setWumpCell(Cell cell){
        this.WumpCell = cell;
    }

    public void setWumpCell(int cellNum){
        this.WumpCell = cave.getCell(cellNum);
    }

    public Cell getWumpCell(){
        return this.WumpCell;
    }

    public void setState(String str){
        this.state = str;
    }

    public String getState(){
        return this.state;
    }

    public int getHealth(){
        return this.health;
    }


}
