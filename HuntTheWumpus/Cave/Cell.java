/////////////////////
//Import
////////////////////

package Cave;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Cell {
  /////////////////////
  //Properties
  ////////////////////
  int cellNum; //the cell number (used to find neighbors)
  boolean[] walls; // true means there is a wall at that location
  String type; // initial hazard / wumpus / player
  /////////////////////
  //Constuctor(s)
  ////////////////////
  public Cell(String info) throws FileNotFoundException {
    String[] infoArray = info.split(":");
    //reading information from walls.csv, turning string into useable information to the cell
    setCellNum(Integer.parseInt(infoArray[0]));
    setWalls(getBoolWalls(infoArray[1]));
    setType(infoArray[2]);
    System.out.println(toString());
  }
  /////////////////////
  //Methods
  ////////////////////
  
  private boolean[] getBoolWalls(String info){
    boolean[] boolWalls = new boolean[6];
    for(int i = 0; i < info.length(); i++){
      if( info.substring(i, i + 1).equals("F") ){
        boolWalls[i] = false;
      } else{
        boolWalls[i] = true;
      }
    }
    return boolWalls;  
  }

  private String getInfoWalls(boolean[] walls){
    String str = "";
    for(int i = 0; i < walls.length; i++){
      if(walls[i] == false){
        str += "F";
      }else{
        str += "T";
      }
    }
    return str;
  }

  private Integer[] getAllNeighbors(){
    Integer[] cellValues = new Integer[6];
    cellValues[0] = getUp(cellNum);
    cellValues[1] = getUpRight(cellNum);
    cellValues[2] = getDownRight(cellNum);
    cellValues[3] = getDown(cellNum);
    cellValues[4] = getDownLeft(cellNum);
    cellValues[5] = getUpLeft(cellNum);
    return cellValues;
  }

  public ArrayList<Integer> getAccesibleNeighbors(){
    Integer[] all = getAllNeighbors();
    ArrayList<Integer> accesible = new ArrayList<Integer>();
    for(int i = 0; i < all.length; i++){
      if(getWalls()[i] == false){
        accesible.add(all[i]);
      }else{
        // do nothing
      }

    }
    return accesible;
  }


  // GETTERS AND SETTERS //

  public int getCellNum() {
    return cellNum;
  }

  public void setCellNum(int value) {
    this.cellNum = value;
  }

  public String getType(){
    return this.type;
  }

  public void setType(String str){
    this.type = str;
  }

  public boolean[] getWalls(){
    return this.walls;
  }

  public void setWalls(boolean[] walls){
    this.walls = walls;
  }

  // Neighbor Methods (type int)

  private int getUp(int num) {
    int up = num - 6;
    return wrap(up);
  }

  private int getUpRight(int num) {
    int colNum = getCol(num);
    int UR = num;
    if (colNum % 2 == 0 && colNum != 0) {
      return wrap(UR + 1);
    } else if (colNum == 0) {
      return wrap(UR - 5);
    } else {
      return wrap(UR - 5);
    }
  }

  private int getDownRight(int num) {
    int colNum = getCol(num);
    int DR = wrap(num + 1);
    if (colNum % 2 == 0 && colNum != 0) {
      return wrap(DR + 6); // + 7
    } else {
      return DR; // + 1
    }
  }

  private int getDown(int num) {
    int down = num + 6;
    return wrap(down);
  }

  private int getDownLeft(int num) {
    int colNum = getCol(num);
    int DL = num;
    if (colNum % 2 == 1 && colNum != 1) {
      return wrap(DL - 1);
    } else if (colNum == 1) {
      return wrap(DL + 5);
    } else {
      return wrap(DL + 5);
    }
  }

  private int getUpLeft(int num) {
    int colNum = getCol(num);
    int UL = wrap(num - 1);
    if (colNum % 2 == 1 && colNum != 1) {
      return wrap(UL - 6); // - 1
    } else {
      return UL; // - 7
    }
  }

  private int getCol(int num) {
    int col = (num % 6);
    if (col == 0)
      col = 6;
    return col;
  }

  private int wrap(int num) {
    if (num > 30) {
      return num - 30;
    }
    if (num < 1) {
      return 30 - Math.abs(num);
    }
    return num;
  }
  
  @Override 
  public String toString(){
    return "" + getCellNum() + ":" + getInfoWalls(getWalls()) + ":" + getType();
  }
  
}