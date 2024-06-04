package gameControl;

import Cave.Cave;
import Cave.Cell;
import UI.HexagonButton;
import UI.UITest;

import java.awt.*;
import java.util.ArrayList;

public class CaveControl {
    private Cave cave;

    public CaveControl() {
        try {
            cave = new Cave();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void highlightCellAndNeighbors(int cellNumber) {
        ArrayList<Cell> neighbors = cave.getCellNeighbors(cellNumber);
        UITest uiTest = new UITest();
        uiTest.highlightButton(cellNumber);

        for (Cell neighbor : neighbors) {
            uiTest.highlightButton(neighbor.getCellNum());
        }
    }
}
