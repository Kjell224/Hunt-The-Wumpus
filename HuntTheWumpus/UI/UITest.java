package UI;

import javax.swing.*;

import Cave.Cave;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class UITest extends JFrame implements ActionListener {

    private static final int HEX_SIZE = 50;
    private static final int HEX_WIDTH = (int) (Math.sqrt(3) * HEX_SIZE);
    private static final int HEX_HEIGHT = HEX_SIZE * 2;
    private String number;
    private Cave cave;
    private HexagonButton selectedButton; // Track the currently selected button

    public UITest() {
        draw();
    }

    ///////////////////////
    // Methods
    //////////////////////

    public void draw() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);

        setSize(8000,8000);

        // Pattern as shown in the image
        int[][] pattern = {
            {1, 2, 3, 4, 5, 6},
            {7, 8, 9, 10, 11, 12},
            {13, 14, 15, 16, 17, 18},
            {19, 20, 21, 22, 23, 24},
            {25, 26, 27, 28, 29, 30}
        };

        for (int y = 0; y < pattern.length; y++) {
            for (int x = 0; x < pattern[y].length; x++) {
                if (pattern[y][x] != 0) { // Create buttons only for non-zero values
                    addHexagonButton(x, y, pattern[y][x]);
                }
            }
        }

        setVisible(true);
    }

    private void addHexagonButton(int x, int y, int number) {
        HexagonButton button = new HexagonButton(String.valueOf(number));
        int posX = x * (HEX_WIDTH * 3 / 4) + 500; 
        int posY = y * (HEX_HEIGHT * 3 / 4) + (x % 2) * (HEX_HEIGHT / 2) + 200; 
        if (x % 2 == 0) {
            posY += 6; 
        }
        
        button.setBounds(posX, posY, HEX_WIDTH, HEX_HEIGHT);
        button.setBackground(Color.WHITE);
        button.addActionListener(this);
        add(button);
    }

    public void highlightButton(int number) {
        // Find and highlight the button with the specified number
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof HexagonButton) {
                HexagonButton button = (HexagonButton) component;
                if (button.getText().equals(String.valueOf(number))) {
                    button.setBackground(Color.RED);
                } else {
                    button.setBackground(Color.WHITE);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            HexagonButton button = (HexagonButton) e.getSource();
            this.number = button.getText();
            int num = Integer.parseInt(number);
            // ArrayList<Integer> nums = cave.getNeighbors(num);
            System.out.println(number);

            // Update the button colors
            if (selectedButton != null) {
                selectedButton.setBackground(Color.WHITE); // Reset the previous button color
            }
            button.setBackground(Color.RED); // Highlight the new button
            selectedButton = button; // Update the selected button reference
        } catch (Exception ex) {
            // Handle other potential exceptions
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
    }

    public String getNumber() {
        return this.number;
    }
}
