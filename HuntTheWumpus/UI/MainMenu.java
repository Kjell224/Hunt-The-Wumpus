package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {

    // Constructor to initialize the main menu UI
    public MainMenu() {
        setTitle("Main Menu"); // Set the title of the JFrame
        setSize(8000, 8000); // Set the size of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
        setLayout(new BorderLayout()); // Use BorderLayout for the JFrame's layout

        // Create and configure the title label
        JLabel titleLabel = new JLabel("Hunt the Wumpus", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50)); // Set font of the title
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0)); // Add padding around the title
        add(titleLabel, BorderLayout.NORTH); // Add the title label to the top of the JFrame

        // Create and configure the play button
        JButton playButton = new JButton("Play");
        playButton.setForeground(new Color(255, 0, 0)); // Set text color of the button
        playButton.setFont(new Font("Arial", Font.PLAIN, 30)); // Set font of the button text
        playButton.setPreferredSize(new Dimension(200, 100)); // Set preferred size of the button
        playButton.addActionListener(this); // Add an action listener to handle button clicks

        // Create a panel to center the play button
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout()); // Use GridBagLayout to center the button
        centerPanel.add(playButton); // Add the play button to the panel
        add(centerPanel, BorderLayout.CENTER); // Add the panel to the center of the JFrame
    }

    // Method to handle button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Play")) { // Check if the "Play" button was clicked
            new UITest(); // Create and show the UITest frame
            dispose(); // Close the main menu window
        }
    }

    
}
