package UI;

import javax.swing.*;
import Cave.Cave;
import Player.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Trivia.Trivia;
import Trivia.Question;

public class UITest extends JFrame implements ActionListener {

    // Constants for hexagon size and dimensions
    private static final int HEX_SIZE = 50; // Size of the hexagon
    private static final int HEX_WIDTH = (int) (Math.sqrt(3) * HEX_SIZE); // Calculated width of the hexagon
    private static final int HEX_HEIGHT = HEX_SIZE * 2; // Calculated height of the hexagon
    private String number; // To store the selected number
    private Cave cave; // Instance of the Cave class
    private HexagonButton selectedButton; // To track the currently selected button
    private Trivia trivia; // Instance of the Trivia class
    private int goldCount; // To store the player's gold count
    private JLabel goldLabel; // Label to display the gold count
    private int arrowCount; // To store the player's arrow count
    private JLabel arrowLabel; // Label to display the arrow count

    // Constructor to initialize the UI
    public UITest(Cave cave) {
        this.cave = cave;
        this.trivia = new Trivia(); // Initialize the Trivia instance
        this.goldCount = 0; // Initialize the gold count
        this.arrowCount = 3; // Initialize the arrow count
        draw(); // Call the draw method to set up the UI
        initilizePlayerPosition(cave.getPlayerCell()); // given the player position (int cell)
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

        // Add action buttons on the right side in the middle
        addRightSideButtons();

        // Add the gold label to display the current gold count
        goldLabel = new JLabel("Gold: 0");
        goldLabel.setBounds(1050, 280, 150, 50);
        goldLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(goldLabel);

        // Add the arrow label to display the current arrow count
        arrowLabel = new JLabel("Arrows: 3");
        arrowLabel.setBounds(1050, 320, 150, 50);
        arrowLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(arrowLabel);

        setVisible(true); // Make the JFrame visible
    }

    // Method to add a hexagon button at a specific grid position
    private void addHexagonButton(int x, int y, int number) {
        HexagonButton button = new HexagonButton(String.valueOf(number)); // Create a new button with the number as text
        int posX = x * (HEX_WIDTH * 3 / 4) + 500; // Calculate the x position
        int posY = y * (HEX_HEIGHT * 3 / 4) + (x % 2) * (HEX_HEIGHT / 2) + 200; // Calculate the y position

        if (x % 2 == 0) {
            posY += 6; 
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

    public void initilizePlayerPosition(int num){
        try {
            Component[] components = getContentPane().getComponents(); // Get all components in the content pane
            for (Component component : components) {
                if (component instanceof HexagonButton) { // Check if the component is a HexagonButton
                    HexagonButton button = (HexagonButton) component;
                    if (button.getText().equals(String.valueOf(num))) { // Check if the button's text matches the number
                        button.setBackground(Color.RED); // Highlight the button by setting its background color to red
                    } 
                }
            }

            ArrayList<Integer> nums = cave.getNeighbors(num); // Get the neighbors from the Cave instance
            for (int i = 0; i < nums.size(); i++) {
                highlightButton(nums.get(i)); // Highlight the neighboring buttons
            }

        } catch (Exception e){
            System.out.println("I suck " + e);
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
            resetAllButtonsToWhite();
            if (selectedButton != null) {
                selectedButton.setBackground(Color.WHITE); // Reset the previous button color
            }
            button.setBackground(Color.RED); // Highlight the new button
            ArrayList<Integer> nums = cave.getNeighbors(num); // Get the neighbors from the Cave instance
            for (int i = 0; i < nums.size(); i++) {
                highlightButton(nums.get(i)); // Highlight the neighboring buttons
            }
            selectedButton = button; // Update the selected button reference
        } catch (Exception ex) {
            // Handle other potential exceptions
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
    }
    
    private void resetAllButtonsToWhite() {
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof HexagonButton) {
                ((HexagonButton) component).setBackground(Color.WHITE); // Set all buttons to white
            }
        }
    }

    // Method to add "Shoot Arrow" and "Get Arrow" buttons on the right side in the middle
    private void addRightSideButtons() {
        int buttonWidth = 150;
        int buttonHeight = 50;
        int rightSideX = 1050;
        int rightSideY = 350; 

        JButton shootArrowButton = new JButton("Shoot Arrow");
        shootArrowButton.setBounds(rightSideX, rightSideY, buttonWidth, buttonHeight);
        shootArrowButton.addActionListener(e -> shootArrow());
        add(shootArrowButton);

        JButton getArrowButton = new JButton("Get Arrow");
        getArrowButton.setBounds(rightSideX, rightSideY + 70, buttonWidth, buttonHeight);
        getArrowButton.addActionListener(e -> getArrow());
        add(getArrowButton);
    }

    private void shootArrow() {
        // Define arrow buttons with Unicode arrow symbols
        JButton northButton = new JButton("\u2191"); // Up arrow
        JButton northEastButton = new JButton("\u2197"); // Up-right arrow
        JButton southEastButton = new JButton("\u2198"); // Down-right arrow
        JButton southButton = new JButton("\u2193"); // Down arrow
        JButton southWestButton = new JButton("\u2199"); // Down-left arrow
        JButton northWestButton = new JButton("\u2196"); // Up-left arrow

        // Set up the direction panel
        JPanel directionPanel = new JPanel();
        directionPanel.setLayout(new GridLayout(2, 3));
        directionPanel.add(northWestButton);
        directionPanel.add(northButton);
        directionPanel.add(northEastButton);
        directionPanel.add(southWestButton);
        directionPanel.add(southButton);
        directionPanel.add(southEastButton);

        // Create a dialog to display the direction panel
        JDialog dialog = new JDialog(this, "Select Direction", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(directionPanel, BorderLayout.CENTER);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);

        // Add action listeners to the buttons
        ActionListener arrowListener = e -> {
            dialog.dispose(); // Close the dialog
            JButton source = (JButton) e.getSource();
            JOptionPane.showMessageDialog(this, "Arrow shot " + source.getText() + "!");
            arrowCount--;
            arrowLabel.setText("Arrows: " + arrowCount);
            if (arrowCount == 0) {
                new GameOver();
                dispose();
            }
        };

        northButton.addActionListener(arrowListener);
        northEastButton.addActionListener(arrowListener);
        southEastButton.addActionListener(arrowListener);
        southButton.addActionListener(arrowListener);
        southWestButton.addActionListener(arrowListener);
        northWestButton.addActionListener(arrowListener);

        dialog.setVisible(true);
    }

    private void getArrow() {
        // Implement the logic for getting arrow
        int right = 0;
        for(int c = 0; c < 3; c++){
            Question question = trivia.getQuestion(); // Get a trivia question
            String userAnswer = JOptionPane.showInputDialog(this, question.getQuestion()); // Prompt the user for an answer

            // Check the answer
            if (userAnswer != null && userAnswer.equalsIgnoreCase(question.getAnswer())) {
                JOptionPane.showMessageDialog(this, "Correct!");
                right++; // Increment the arrow count
                if(right >= 2){
                    arrowCount += 2;
                    arrowLabel.setText("Arrow: " + arrowCount); // Update the arrow label
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong.");
            }
        }
    }

    // Method to get the currently selected number
    public String getNumber() {
        return this.number;
    }
}
