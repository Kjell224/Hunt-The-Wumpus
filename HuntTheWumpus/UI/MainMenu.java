package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    public MainMenu(String[] args) {
        JFrame frame = new JFrame("Hunt the Wumpus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Hunt the Wumpus", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Ariel", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.CENTER);

        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Ariel", Font.PLAIN, 20));
        frame.add(playButton, BorderLayout.SOUTH);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UITest.main(new String[]{});
                frame.dispose(); 
            }
        });

        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new MainMenu(args);
    }
}
