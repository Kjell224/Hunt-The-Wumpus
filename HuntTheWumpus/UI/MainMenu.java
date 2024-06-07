package UI;

import javax.swing.*;
import Cave.Cave;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class MainMenu extends JFrame implements ActionListener {
    private Cave cave;
    private JTextField nameField;

    // Constructor to initialize the main menu UI
    public MainMenu(Cave cave) {
        this.cave = cave;
        setTitle("Main Menu"); // Set the title of the JFrame
        setSize(800, 800); // Set the size of the JFrame (corrected to more reasonable dimensions)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
        setLayout(new BorderLayout()); // Use BorderLayout for the JFrame's layout

        // Create and configure the title label
        JLabel titleLabel = new JLabel("Hunt the Wumpus", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50)); // Set font of the title
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0)); // Add padding around the title
        add(titleLabel, BorderLayout.NORTH); // Add the title label to the top of the JFrame

        // Create a panel for the name input
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout()); // Use FlowLayout for the name panel

        // Create and configure the name label and text field
        JLabel nameLabel = new JLabel("Enter your name: ");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // Set font of the name label

        nameField = new JTextField(20); // Set the text field with a width of 20 characters
        nameField.setFont(new Font("Arial", Font.PLAIN, 30)); // Set font of the text field

        namePanel.add(nameLabel); // Add the name label to the name panel
        namePanel.add(nameField); // Add the text field to the name panel
        add(namePanel, BorderLayout.CENTER); // Add the name panel to the center of the JFrame

        // Create and configure the play button
        JButton playButton = new JButton("Play");
        playButton.setForeground(new Color(255, 0, 0)); // Set text color of the button
        playButton.setFont(new Font("Arial", Font.PLAIN, 30)); // Set font of the button text
        playButton.setPreferredSize(new Dimension(200, 100)); // Set preferred size of the button
        playButton.addActionListener(this); // Add an action listener to handle button clicks

        // Create a panel to hold the play button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout()); // Use GridBagLayout to center the button
        buttonPanel.add(playButton); // Add the play button to the panel
        add(buttonPanel, BorderLayout.SOUTH); // Add the panel to the bottom of the JFrame
    }

    // Method to handle button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Play")) { // Check if the "Play" button was clicked
            String playerName = nameField.getText(); // Get the entered name
            if (playerName.isEmpty()) { // Check if the name field is empty
                JOptionPane.showMessageDialog(this, "Please enter your name.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                new UITest(getCave()); // Pass the cave and player's name to UITest
                dispose(); // Close the main menu window
            }
        }
    }

    public Cave getCave() {
        return this.cave;
    }
}

