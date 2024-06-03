package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {

    public MainMenu() {

        setTitle("Main Menu");
        setSize(8000, 8000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Hunt the Wumpus", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0)); 
        add(titleLabel, BorderLayout.NORTH);

        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.PLAIN, 30));
        playButton.setPreferredSize(new Dimension(200, 100));
        playButton.addActionListener(this);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.add(playButton);
        add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Play")) {
            new UITest();
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}
