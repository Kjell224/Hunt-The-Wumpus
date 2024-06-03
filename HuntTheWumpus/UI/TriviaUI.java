//package UITest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Trivia.Trivia;

import java.util.Random;

public class TriviaUI {

    public TriviaUI(final String[] args) {
        final JFrame parent = new JFrame();
        JButton button = new JButton();

        button.setText("trivia question");
        parent.add(button);
        parent.pack();
        parent.setVisible(true);

        Trivia trivia = new Trivia();

        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Random random = new Random();
                Trivia trivia = new Trivia();
                Question question = trivia.getQuestion();
                String question = question.getQuestion();
                String correctAnswer = trivia.questions[questionIndex][3];

                String userAnswer = JOptionPane.showInputDialog(parent, question, null);

                if (userAnswer != null && userAnswer.equalsIgnoreCase(correctAnswer)) {
                    System.out.println("Correct");
                } else {
                    System.out.println("Incorrect");
                }
            }
        });
    }

    public static void main(String[] args) {
        new TriviaUI(args);
    }
}