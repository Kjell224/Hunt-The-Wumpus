///////////////////// 
// Import 
/////////////////////
package Cave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Cave {

    ///////////// 
    // Properties 
    /////////////

    private File wallMap; // CSV file

    private Scanner reader; // Reader that reads CSV map file

    private String map; // The full string map from the CSV file

    public Cell[] cellsArray = new Cell[30]; // All cells in ascending order

    //////////////////
    // Constructors
    /////////////////

    public Cave() throws FileNotFoundException {
        this.wallMap = new File("HuntTheWumpus\\Cave\\Maps.csv"); // Grab pathname
        this.reader = new Scanner(wallMap); // Read the file
        this.map = getMapFromCsv(); // CSV information (rotates the maps)
        createCells(this.map); // From information, generate the cells info
        System.out.println("Player cell : " + getPlayerCell()); // Print player cell
        System.out.println("Wumpus cell: " + getWumpusCell()); // Print Wumpus cell
    }

    //////////
    // Methods 
    //////////

    private String getMapFromCsv() throws FileNotFoundException {
        String map = reader.nextLine(); // Read the first line
        String restOfLines = ""; // Initialize the rest of the lines as empty
        while (reader.hasNextLine()) { // Loop through the rest of the lines
            restOfLines += reader.nextLine() + "\n"; // Append each line to restOfLines
        }
        try {
            FileWriter fw = new FileWriter(wallMap); // Create a FileWriter object
            fw.write(restOfLines + map); // Write the rest of the lines followed by the first line
            fw.close(); // Close the FileWriter
        } catch (Exception e) {
            System.out.println("Writing back into Maps.csv failed " + e); // Print an error message if writing fails
        }
        return map; // Return the map
    }

    private void createCells(String info) throws FileNotFoundException {
        String[] cellInfo = info.split(","); // Split the map string by commas
        for (int i = 0; i < 30; i++) { // Loop through the first 30 elements
            Cell cell = new Cell(cellInfo[i]); // Create a new Cell object
            cellsArray[i] = cell; // Assign the Cell object to cellsArray
        }
    }

    // ⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓ RETURNS CELL ARRAYLIST ⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓ 

    // Returns an ArrayList of cells given a Cell
    public ArrayList<Cell> getCellNeighbors(Cell c) {
        ArrayList<Cell> neighbors = new ArrayList<Cell>(); // Create a new ArrayList for neighbors
        ArrayList<Integer> cellValues = c.getAccesibleNeighbors(); // Get accessible neighbors
        for (int i = 0; i < cellValues.size(); i++) { // Loop through cell values
            neighbors.add(getCellsArray()[cellValues.get(i) - 1]); // Add neighbor cells to the list
        }
        return neighbors; // Return the list of neighbors
    }

    // Returns an ArrayList of cells given an int
    public ArrayList<Cell> getCellNeighbors(int num) {
        ArrayList<Cell> neighbors = new ArrayList<Cell>(); // Create a new ArrayList for neighbors
        Cell c = getCell(num); // Get the cell by number
        ArrayList<Integer> cellValues = c.getAccesibleNeighbors(); // Get accessible neighbors
        for (int i = 0; i < cellValues.size(); i++) { // Loop through cell values
            neighbors.add(getCellsArray()[cellValues.get(i) - 1]); // Add neighbor cells to the list
        }
        return neighbors; // Return the list of neighbors
    }

    // ⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓ RETURNS INTEGER ARRAYLIST ⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓⇓ 

    // Returns an ArrayList of integers given an int
    public ArrayList<Integer> getNeighbors(int num) {
        return getCell(num).getAccesibleNeighbors(); // Return accessible neighbors
    }

    // Returns an ArrayList of integers given a Cell
    public ArrayList<Integer> getNeighbors(Cell cell) {
        return cell.getAccesibleNeighbors(); // Return accessible neighbors
    }

    // Checks if it's possible to move to a cell given the current cell and target cell
    public boolean isValid(Cell current, Cell target) {
        ArrayList<Cell> neighbors = getCellNeighbors(current); // Get neighbors of the current cell
        for (int i = 0; i < neighbors.size(); i++) { // Loop through the neighbors
            if (neighbors.get(i).getCellNum() == target.getCellNum()) { // Check if the target cell is a neighbor
                return true; // Return true if it's a valid move
            }
        }
        return false; // Return false if it's not a valid move
    }

    // Get the cell number where the player is located
    public int getPlayerCell() {
        for (int i = 0; i < cellsArray.length; i++) { // Loop through the cellsArray
            if (getCell(i).getPlayer()) { // Check if the cell has the player
                return getCell(i).getCellNum(); // Return the cell number
            }
        }
        return -1; // Return -1 if the player is not found
    }

    // Get the cell number where the Wumpus is located
    public int getWumpusCell() {
        for (int i = 1; i <= cellsArray.length; i++) { // Loop through the cellsArray
            if (getCell(i).getWumpus()) { // Check if the cell has the Wumpus
                return getCell(i).getCellNum(); // Return the cell number
            }
        }
        return -1; // Return -1 if the Wumpus is not found
    }

    // Returns the array of cells
    public Cell[] getCellsArray() {
        return cellsArray; // Return the cellsArray
    }

    // Returns a specific cell by its number
    // This will return the actual cell number (not the offset index)
    public Cell getCell(int num) {
        return cellsArray[num - 1]; // Return the cell
    }

    // Returns the map string
    public String getMap() {
        return this.map; // Return the map
    }
}