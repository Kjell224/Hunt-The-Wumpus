///////////////////// 
// Import 
/////////////////////
package Cave;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Cell {

    ///////////////////// 
    // Properties 
    ////////////////////

    private int cellNum; // The cell number (used to find neighbors)

    private boolean[] walls; // True means there is a wall at that location

    private String type; // Initial hazard

    private boolean wumpus; // Is Wumpus in cell

    private boolean player; // Is player in cell

    ///////////////////// 
    // Constructors 
    ////////////////////

    public Cell(String info) throws FileNotFoundException {
        String[] infoArray = info.split(":"); // Split info string into array
        setCellNum(Integer.parseInt(infoArray[0])); // Set cell number
        setWalls(getBoolWalls(infoArray[1])); // Set walls array
        setType(infoArray[2]); // Set type
        this.wumpus = convertInfo(infoArray[3]); // Set wumpus presence
        this.player = convertInfo(infoArray[4]); // Set player presence
        //System.out.println(toString());
    }

    ///////////////////// 
    // Methods 
    ////////////////////

    private boolean convertInfo(String bool) {
        if (bool.equals("F")) {
            return false; // Convert "F" to false
        } else {
            return true; // Convert "T" to true
        }
    }

    private boolean[] getBoolWalls(String info) {
        boolean[] boolWalls = new boolean[6]; // Initialize walls array
        for (int i = 0; i < info.length(); i++) {
            if (info.substring(i, i + 1).equals("F")) {
                boolWalls[i] = false; // Convert "F" to false
            } else {
                boolWalls[i] = true; // Convert "T" to true
            }
        }
        return boolWalls; // Return walls array
    }

    private String getInfoWalls(boolean[] walls) {
        String str = ""; // Initialize empty string
        for (int i = 0; i < walls.length; i++) {
            if (walls[i] == false) {
                str += "F"; // Convert false to "F"
            } else {
                str += "T"; // Convert true to "T"
            }
        }
        return str; // Return walls string
    }

    public Integer[] getAllNeighbors() {
        Integer[] cellValues = new Integer[6]; // Initialize neighbors array
        cellValues[0] = getUp(cellNum); // Get cell above
        cellValues[1] = getUpRight(cellNum); // Get cell up-right
        cellValues[2] = getDownRight(cellNum); // Get cell down-right
        cellValues[3] = getDown(cellNum); // Get cell below
        cellValues[4] = getDownLeft(cellNum); // Get cell down-left
        cellValues[5] = getUpLeft(cellNum); // Get cell up-left
        return cellValues; // Return neighbors array
    }

    public ArrayList<Integer> getAccesibleNeighbors() {
        Integer[] all = getAllNeighbors(); // Get all neighbors
        ArrayList<Integer> accesible = new ArrayList<Integer>(); // Initialize accessible neighbors list
        for (int i = 0; i < all.length; i++) {
            if (getWalls()[i] == false) {
                accesible.add(all[i]); // Add accessible neighbors
            }
        }
        return accesible; // Return accessible neighbors list
    }

    // GETTERS AND SETTERS

    public int getCellNum() {
        return cellNum; // Return cell number
    }

    public void setCellNum(int value) {
        this.cellNum = value; // Set cell number
    }

    public String getType() {
        return this.type; // Return cell type
    }

    public void setType(String str) {
        this.type = str; // Set cell type
    }

    public boolean[] getWalls() {
        return this.walls; // Return walls array
    }

    public void setWalls(boolean[] walls) {
        this.walls = walls; // Set walls array
    }

    public boolean getPlayer() {
        return this.player; // Return player presence
    }

    public void setPlayer(boolean player) {
        this.player = player; // Set player presence
    }

    public boolean getWumpus() {
        return this.wumpus; // Return Wumpus presence
    }

    public void setWumpus(boolean wumpus) {
        this.wumpus = wumpus; // Set Wumpus presence
    }

    // Neighbor Methods (type int)

    private int getUp(int num) {
        int up = num - 6; // Calculate cell above
        return wrap(up); // Wrap around if necessary
    }

    private int getUpRight(int num) {
        int colNum = getCol(num); // Get column number
        int UR = num; // Initialize up-right cell number
        if (colNum % 2 == 0 && colNum != 0) {
            return wrap(UR + 1); // Adjust for even columns
        } else if (colNum == 0) {
            return wrap(UR - 5); // Special case for column 0
        } else {
            return wrap(UR - 5); // Adjust for odd columns
        }
    }

    private int getDownRight(int num) {
        int colNum = getCol(num); // Get column number
        int DR = wrap(num + 1); // Initialize down-right cell number
        if (colNum % 2 == 0 && colNum != 0) {
            return wrap(DR + 6); // Adjust for even columns
        } else {
            return DR; // Adjust for odd columns
        }
    }

    private int getDown(int num) {
        int down = num + 6; // Calculate cell below
        return wrap(down); // Wrap around if necessary
    }

    private int getDownLeft(int num) {
        int colNum = getCol(num); // Get column number
        int DL = num; // Initialize down-left cell number
        if (colNum % 2 == 1 && colNum != 1) {
            return wrap(DL - 1); // Adjust for odd columns
        } else if (colNum == 1) {
            return wrap(DL + 5); // Special case for column 1
        } else {
            return wrap(DL + 5); // Adjust for even columns
        }
    }

    private int getUpLeft(int num) {
        int colNum = getCol(num); // Get column number
        int UL = wrap(num - 1); // Initialize up-left cell number
        if (colNum % 2 == 1 && colNum != 1) {
            return wrap(UL - 6); // Adjust for odd columns
        } else {
            return UL; // Adjust for even columns
        }
    }

    private int getCol(int num) {
        int col = (num % 6); // Calculate column number
        if (col == 0) col = 6; // Adjust for column 0
        return col; // Return column number
    }

    private int wrap(int num) {
        if (num > 30) {
            return num - 30; // Wrap around if number is greater than 30
        }
        if (num < 1) {
            return 30 - Math.abs(num); // Wrap around if number is less than 1
        }
        return num; // Return wrapped number
    }

    @Override
    public String toString() {
        return "" + getCellNum() + ":" + getInfoWalls(getWalls()) + ":" + getType(); // Return cell information as string
    }
}
