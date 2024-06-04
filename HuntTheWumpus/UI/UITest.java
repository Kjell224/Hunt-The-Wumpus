package UI;

import javax.swing.*;
import Cave.Cave;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UITest extends JFrame implements ActionListener {

    // Constants for hexagon size and dimensions
    private static final int HEX_SIZE = 50; // Size of the hexagon
    private static final int HEX_WIDTH = (int) (Math.sqrt(3) * HEX_SIZE); // Calculated width of the hexagon
    private static final int HEX_HEIGHT = HEX_SIZE * 2; // Calculated height of the hexagon
    private String number; // To store the selected number
    private Cave cave; // Instance of the Cave class
    private HexagonButton selectedButton; // To track the currently selected button

    // Constructor to initialize the UI
    public UITest() {
        draw(); // Call the draw method to set up the UI
    }

    ///////////////////////
    // Methods
    //////////////////////

    // Method to set up and display the UI
    public void draw() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the frame when the close button is clicked
        setLayout(null); // Use null layout for absolute positioning
        this.getContentPane().setBackground(Color.LIGHT_GRAY); // Set background color

        setSize(8000, 8000); // Set the size of the JFrame

        // Define a pattern for the hexagon buttons
        int[][] pattern = {
            {1, 2, 3, 4, 5, 6},
            {7, 8, 9, 10, 11, 12},
            {13, 14, 15, 16, 17, 18},
            {19, 20, 21, 22, 23, 24},
            {25, 26, 27, 28, 29, 30}
        };

        // Loop through the pattern to create and position hexagon buttons
        for (int y = 0; y < pattern.length; y++) {
            for (int x = 0; x < pattern[y].length; x++) {
                if (pattern[y][x] != 0) { // Create buttons only for non-zero values
                    addHexagonButton(x, y, pattern[y][x]);
                }
            }
        }

        setVisible(true); // Make the JFrame visible
    }

    // Method to add a hexagon button at a specific grid position
    private void addHexagonButton(int x, int y, int number) {
        HexagonButton button = new HexagonButton(String.valueOf(number)); // Create a new button with the number as text
        int posX = x * (HEX_WIDTH * 3 / 4) + 500; // Calculate the x position
        int posY = y * (HEX_HEIGHT * 3 / 4) + (x % 2) * (HEX_HEIGHT / 2) + 200; // Calculate the y position

        if (x % 2 == 0) {
            posY += 6; // Adjust y position for even columns
        }
        
        button.setBounds(posX, posY, HEX_WIDTH, HEX_HEIGHT); // Set the button's bounds
        button.setBackground(Color.WHITE); // Set the button's background color
        button.addActionListener(this); // Add the action listener to handle button clicks
        add(button); // Add the button to the JFrame
    }

    // Method to highlight a button based on its number
    public void highlightButton(int number) {
        Component[] components = getContentPane().getComponents(); // Get all components in the content pane
        for (Component component : components) {
            if (component instanceof HexagonButton) { // Check if the component is a HexagonButton
                HexagonButton button = (HexagonButton) component;
                if (button.getText().equals(String.valueOf(number))) { // Check if the button's text matches the number
                    button.setBackground(Color.GREEN); // Highlight the button by setting its background color to green
                } 
            }
        }
    }

    // Method to handle button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            HexagonButton button = (HexagonButton) e.getSource(); // Get the clicked button
            this.number = button.getText(); // Get the number from the button's text
            int num = Integer.parseInt(number); // Parse the number
            System.out.println(number); // Print the number to the console

            // Update the button colors
            if (selectedButton != null) {
                selectedButton.setBackground(Color.WHITE); // Reset the previous button color
            }
            button.setBackground(Color.RED); // Highlight the new button
            ArrayList<Integer> nums = cave.getNeighbors(num); // Get the neighbors from the Cave instance
            for(int i = 0; i < nums.size(); i++) {
                highlightButton(nums.get(i)); // Highlight the neighboring buttons
            }
            selectedButton = button; // Update the selected button reference
        } catch (Exception ex) {
            // Handle other potential exceptions
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
    }

    // Method to get the currently selected number
    public String getNumber() {
        return this.number;
    }
}
