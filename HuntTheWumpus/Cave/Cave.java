/////////////////////
//Import
////////////////////
package Cave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Cave {
  /////////////
  //Properties
  /////////////
  private File wallMap;
  private Scanner reader;
  private String map;
  public Cell[] cellsArray = new Cell[30];
  /////////////////
  //Constuctor(s)
  ///////////////
  public Cave() throws FileNotFoundException {
    this.wallMap = new File("HuntTheWumpus\\Cave\\Maps.csv"); // file
    this.reader = new Scanner(wallMap); // scanner
    this.map = getMapFromCsv(); // string
    createCells();
  }
  ///////////
  //Methods
  //////////
  private String getMapFromCsv() throws FileNotFoundException {
    String map = reader.nextLine();
    String restOfLines = "";
    while(reader.hasNextLine()){
      restOfLines += reader.nextLine() + "\n";
    }
    try {
      FileWriter fw = new FileWriter(wallMap);
      fw.write(restOfLines + map);
      fw.close();
    } catch(Exception e) {
      System.out.println("writing back into Walls.csv failed " +  e);
    }
    return map;
  }
  
  private void createCells() throws FileNotFoundException {
    String[] cellInfo = getMap().split(",");
    for(int i = 0; i < 30; i++){
      Cell cell = new Cell(cellInfo[i]);
      cellsArray[i] = cell;
    }
  }

  public ArrayList<Cell> getNeighbors(Cell c){
    ArrayList<Cell> neighbors = new ArrayList<Cell>();
    ArrayList<Integer> cellValues = c.getAccesibleNeighbors();
    for(int i = 0; i < cellValues.size(); i++){
      neighbors.add(getCellsArray()[cellValues.get(i) - 1]);
    }
    return neighbors;
  }

  public ArrayList<Cell> getNeighbors(int num){
    ArrayList<Cell> neighbors = new ArrayList<Cell>();
    Cell c = getCell(num);
    ArrayList<Integer> cellValues = c.getAccesibleNeighbors();
    for(int i = 0; i < cellValues.size(); i++){
      neighbors.add(getCellsArray()[cellValues.get(i) - 1]);
    }
    return neighbors;
  }

  public boolean isValid(Cell current, Cell target){
     ArrayList<Cell> neighbors = getNeighbors(current);
    for(int i = 0; i < neighbors.size(); i++){
      if( neighbors.get(i).getCellNum() == target.getCellNum() ){
        return true;
      }
    }
    return false;
  }
  /* 
  public void printCellsNeighbors(Cell c){
    ArrayList<Integer> nums = c.getAccesibleNeighbors();
    for(int i = 0; i < nums.size(); i++){
      System.out.println(getCellsArray()[nums.get(i)].toString());
    }
  }
  */
  
  public Cell[] getCellsArray(){
    return cellsArray;
  }
  //this should be the ACTUAL cell num
  public Cell getCell(int num){
    return cellsArray[num - 1];
  }
  
  public String getMap(){
    return this.map;
  }

}