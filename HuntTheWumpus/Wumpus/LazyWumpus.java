// Amrit Gill
// March 1, 2024
// Period 5
// Hunt the Wumpus - Lazy Wumpus Class

package HuntTheWumpus.Wumpus;
import HuntTheWumpus.Cave.*;
import HuntTheWumpus.gameControl.*;
import HuntTheWumpus.gameLocations.*;
import HuntTheWumpus.Player.*;
import HuntTheWumpus.Sound.*;
import HuntTheWumpus.Trivia.*;
import HuntTheWumpus.UI.*;
import HuntTheWumpus.Wumpus.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class LazyWumpus {
    ///////////////////////
    // Properties & Fields
    //////////////////////
    public gameLocations gL;
    public Player p;
    public int health = 1;

    ///////////////////////
    // Constructor(s)
    //////////////////////
    public LazyWumpus(){
        getLocation();
    }

    ///////////////////////
    // Methods
    //////////////////////

    public int[] getLocation(){
        return gL.WumpusPos;
    }

    // Precondition: the String state must be "asleep", "awake", or "moving"
    public String state(String state){
        return state;
    }

    public void miss(){
        if ((p.arrows == 2 && health != 0) | (p.arrows == 1 && health != 0) | (p.arrows == 0 && health != 0)){
            gL.WumpusPos[0] += 2 | gL.WumpusPos[1] + 2;
        }
    }

    public void loseTrivia(){
        
    }

    public boolean WumpusWins(){
        if (health != 0 && p.death() == true){
            return true;
        } else {
            return false;
        }
    }

    public boolean WumpusLoses(){
        if (health == 0){
            return true;
        } else{
            return false;
        }
    }
}