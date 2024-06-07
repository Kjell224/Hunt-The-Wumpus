// Arunim
// February 16, 2024
// Period 5
// Hunt the Wumpus - High Score Class

package gameControl;

public class HighScore {
    ///////////////////////
    // Properties & Fields
    //////////////////////
    private int totalScore;

    //////////////////////
    // Constructor
    //////////////////////
    public HighScore(){

    }

    //////////////////////
    // Methods
    //////////////////////
    public int getScore(){
        return totalScore;
    }

    public int computeScore(int turns, int gold, int arrowNum, boolean wumpusDeath){
        totalScore = 100-turns+gold+(5*arrowNum);
        if(wumpusDeath) totalScore++;
        return totalScore;
    }
}
