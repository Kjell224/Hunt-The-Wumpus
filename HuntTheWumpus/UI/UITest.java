package UI;

import javax.swing.*;
import Cave.Cave;
import Wumpus.Wumpus;
import Player.Player;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Trivia.Trivia;
import Cave.Cell;
import Player.*;
import Trivia.Question;
import gameLocations.*;

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
    private Map<Integer, HexagonButton> buttonMap; // Map to store hexagon buttons
    private gameLocations gL;
    private Wumpus wumpus;
    private Player player;

    // Constructor to initialize the UI
    public UITest(Cave cave, Trivia trivia, Player player, Wumpus wumpus) {
        this.trivia = trivia; // Initialize the Trivia instance
        try {
            this.gL = new gameLocations();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.cave = cave;
        this.wumpus = wumpus;
        this.player = player;

        this.goldCount = 0; // Initialize the gold count
        this.arrowCount = 3; // Initialize the arrow count
        this.buttonMap = new HashMap<>(); // Initialize the button map
        draw(); // Call the draw method to set up the UI
        initializePlayerPosition(cave.getPlayerCell()); // given the player position (int cell)
        gL.initializeTypes(cave);
    }

    ///////////////////////
    // Methods
    //////////////////////

    // Method to set up and display the UI
    public void draw() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the frame when the close button is clicked
        setLayout(null); // Use null layout for absolute positioning
        this.getContentPane().setBackground(Color.BLACK); // Set background color

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
        goldLabel = new JLabel("Gold: " + goldCount);
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
        buttonMap.put(number, button); // Store the button in the map
        add(button); // Add the button to the JFrame
    }

    // Method to highlight a button based on its number
    public void highlightButton(int number) {
        HexagonButton button = buttonMap.get(number);
        if (button != null) {
            button.setBackground(Color.GREEN); // Highlight the button by setting its background color to green
            button.setEnabled(true); // Enable the button
        }
    }

  // Method to enable only the neighboring buttons
  private void OnlyNeighbors(int num) {
    ArrayList<Integer> neighbors = cave.getNeighbors(num); // Get the neighbors from the Cave instance
    for (HexagonButton button : buttonMap.values()) {
        button.setEnabled(false); // Disable all buttons
        button.setBackground(Color.DARK_GRAY); // Reset all buttons to white
    }
    buttonMap.get(num).setEnabled(true); // Enable the current button
    for (int neighbor : neighbors) {
        highlightButton(neighbor); // Highlight and enable the neighboring buttons
    }
}


    public void initializePlayerPosition(int num) {
        try {
            HexagonButton button = buttonMap.get(num);
            if (button != null) {
                OnlyNeighbors(num); // Enable only the neighboring buttons
                //button.setForeground(Color.GREEN);
                //button.setText("웃");
                button.setBackground(Color.RED); // Highlight the button by setting its background color to red
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
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
            num = turn(num);
            System.out.println(num);
            System.out.println(gL.getBatsLocation()[0]);
            System.out.println(gL.getBatsLocation()[1]);

            if (selectedButton != null) {
                selectedButton.setBackground(Color.WHITE); // Reset the previous button color
            }
            selectedButton = button; // Update the selected button reference
            OnlyNeighbors(num); // Enable only the neighboring buttons

            buttonMap.get(num).setBackground(Color.RED); // Highlight the new button
        } catch (Exception ex) {
            // Handle other potential exceptions
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
    }

    public int checkHazard(int cellNum){
        Cell cell = cave.getCell(cellNum);
        String type = cell.getType();
        if(cell.getWumpus()){
            JOptionPane.showMessageDialog(this, "You Have Encountered A Wumpus! Answer 3 out of 5 questions right!");
            if(askMultipleQuestions(5, 3)){
                gL.setRandomWumpusLocation(cave, cellNum);
                return cellNum;
            }
            endGame();
        }
        else if(type.equals("SuperBat")){
            JOptionPane.showMessageDialog(this, "You Have Encountered Bats!");
            return gL.setRandomBatsLocation(cave, cellNum);
        } else if(type.equals("Pit")){
            JOptionPane.showMessageDialog(this, "You Have Encountered A Bottomless Pit! Answer 2 out of 3 questions right!");
            if(askMultipleQuestions(3, 2)){
                return gL.getPlayerLocation();
            }
            endGame();
        }
        return cellNum;
    }

    public int turn(int cellNum){
        cellNum = checkHazard(cellNum);
        player.turn(cellNum);
        gL.updatePlayerLocations(gL.getPlayerLocation(), cellNum, cave);
        wumpus.turn();
        return gL.getPlayerLocation();
    }

    // Method to add "Shoot Arrow" and "Get Arrow" buttons on the right side in the middle
    private void addRightSideButtons() {
        int buttonWidth = 150;
        int buttonHeight = 50;
        int rightSideX = 1050;
        int rightSideY = 400; 

        JButton shootArrowButton = new JButton("Shoot Arrow");
        shootArrowButton.setBounds(rightSideX, rightSideY, buttonWidth, buttonHeight);
        shootArrowButton.addActionListener(e -> shootArrow());
        add(shootArrowButton);

        JButton getArrowButton = new JButton("Get Arrow");
        getArrowButton.setBounds(rightSideX, rightSideY + 70, buttonWidth, buttonHeight);
        getArrowButton.addActionListener(e -> getArrow());
        add(getArrowButton);

        JButton purchaseSecretButton = new JButton("Purchase Secret");
        purchaseSecretButton.setBounds(rightSideX, rightSideY + 140, buttonWidth, buttonHeight);
        purchaseSecretButton.addActionListener(e -> purchaseSecret());
        add(purchaseSecretButton);
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
                endGame();
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

    
    private void purchaseSecret(){
        int right = 0;
        for(int c = 0; c < 3; c++){
            Question question = this.trivia.getQuestion(); // Get a trivia question
            String userAnswer = JOptionPane.showInputDialog(this, question.printQuestion()); // Prompt the user for an answer
            // Check the answer
            if (userAnswer != null && userAnswer.equalsIgnoreCase(question.getAnswer())) {
                JOptionPane.showMessageDialog(this, "Correct!");
                right++; // Increment the amount right
            } else {
                JOptionPane.showMessageDialog(this, "Wrong.");
            }
            if(right >= 2) break;
        }
        if(right < 2) return;
        
        //Not useful to useful 1-6
        int secretType = (int)(Math.random()*6) + 1;
        int randBatOrPitPos = (int)(Math.random()*2);
        if(secretType == 1) JOptionPane.showMessageDialog(this, "Not Useful! You are in cell " + gL.getPlayerLocation());
        else if(secretType == 2) JOptionPane.showMessageDialog(this, "Not Useful! The Answer to an old trivia question is " + trivia.getRandomAnswer()); // FInd a way to give an answer to a trivia question you already got
        else if(secretType == 3){
            Cell pCell = cave.getCell(gL.getPlayerLocation());
            Integer[] neighbors = pCell.getAllNeighbors();
            boolean wumpusFound = false;
            for(Integer i : neighbors){
                Cell curCell = cave.getCell(i);
                if(curCell.getType().equals("Wumpus")){
                    JOptionPane.showMessageDialog(this, "Useful! A Wumpus is within 2 rooms of you.");
                    wumpusFound = true;
                    break;
                } else {
                    Integer[] neighbors2 = curCell.getAllNeighbors();
                    for(Integer j : neighbors2){
                        Cell curCell2 = cave.getCell(j);
                        if(curCell2.getType().equals("Wumpus")){
                            JOptionPane.showMessageDialog(this, "Useful! A Wumpus is within 2 rooms of you.");
                            wumpusFound = true;
                            break;
                        }
                    }
                    if (wumpusFound) break; // Break the outer loop if a Wumpus is found in the inner loop
                }
            }
            if(!wumpusFound) JOptionPane.showMessageDialog(this, "Useful! A Wumpus is NOT within 2 rooms of you."); //Find a way to give if wumpus is within 2 of player
        }
        else if(secretType == 4) JOptionPane.showMessageDialog(this, "Useful! A swarm of SuperBats is in room " + gL.getBatsLocation()[randBatOrPitPos]);
        else if(secretType == 5) JOptionPane.showMessageDialog(this, "Useful! A Pit is in room " + gL.getPitsLocation()[randBatOrPitPos]);
        else if(secretType == 6) JOptionPane.showMessageDialog(this, "Very Useful! The Wumpus is in cell" + gL.getWumpusLocation());
    }

    private void getArrow() {
        // Implement the logic for getting arrow
        int right = 0;
        for(int c = 0; c < 3; c++){
            Question question = trivia.getQuestion(); // Get a trivia question
            String userAnswer = JOptionPane.showInputDialog(this, question.printQuestion()); // Prompt the user for an answer

            // Check the answer
            if (userAnswer != null && userAnswer.equalsIgnoreCase(question.getAnswer())) {
                JOptionPane.showMessageDialog(this, "Correct!");
                right++; // Increment the arrow count
                if(right >= 2){
                    arrowCount += 2;
                    arrowLabel.setText("Arrows: " + arrowCount); // Update the arrow label
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong.");
            }
        }
    }
    
    public boolean askTriviaQuestion(){
        boolean correct = false;
        Question question = this.trivia.getQuestion(); // Get a trivia question
        String userAnswer = JOptionPane.showInputDialog(this, question.printQuestion()); // Prompt the user for an answer
        if (userAnswer != null && userAnswer.equalsIgnoreCase(question.getAnswer())) {
            JOptionPane.showMessageDialog(this, "Correct!");
            correct = true;
        } else {
            JOptionPane.showMessageDialog(this, "Wrong.");
        }
        goldCount--;
        if(goldCount == 0) endGame();
        return correct;
    }

    public boolean askMultipleQuestions(int qNum, int numCorrect){ //Amount of questions you need to ask
        int right = 0; //Amount of questions got right
        for(int i = 0; i < qNum; i++){
            if(askTriviaQuestion()){
                right++;
            }
            if(right >= numCorrect){
                return true;
            }     
        }
        return false;
    }

    // Method to get the currently selected number
    public String getNumber() {
        return this.number;
    }

    public GameOver endGame(){
        dispose();
        return new GameOver();
    }
}
