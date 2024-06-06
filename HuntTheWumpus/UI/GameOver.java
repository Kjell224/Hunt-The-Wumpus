package UI;

import javax.swing.*;

import Cave.Cave;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GameOver {

    public GameOver() {
        // Create the frame
        JFrame frame = new JFrame("Game Over");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create a label to display "Game Over"
        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(gameOverLabel, BorderLayout.CENTER);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create Restart button
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            private MainMenu mainmenu;
            private Cave cave;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to restart the game
                JOptionPane.showMessageDialog(frame, "Restarting the game...");
                try {
                    this.mainmenu = new MainMenu(new Cave());
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } // Create the main menu
                this.mainmenu.setVisible(true); // Make the main menu visible  
                frame.dispose(); 
                // Close the Game Over window (or reset the game state)
                
            }
        });
        buttonPanel.add(restartButton);

        // Create Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        // Add the button panel to the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }
}
