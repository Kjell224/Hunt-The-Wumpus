package UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Trivia.Trivia;
import Trivia.Question;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JFrame;

public class TriviaUI extends JFrame implements ActionListener {

    public TriviaUI(final String[] args) {
        final JFrame parent = new JFrame();
        JButton button = new JButton();

        button.setText("Trivia");
        parent.add(button);
        parent.pack();
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parent.setVisible(true);
        //parent.setSize(800,800);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int correctAnswers = 0;
        Trivia trivia = new Trivia();

        try (FileWriter fileWriter = new FileWriter("trivia_results.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter)) {

            for (int i = 1; i <= 5; i++) {
                Question question = trivia.getQuestion();
                String questionText = question.getQuestion();
                String correctAnswer = question.getAnswer();

                String userAnswer = JOptionPane.showInputDialog(getParent(), questionText, null);

                if (userAnswer != null && userAnswer.equalsIgnoreCase(correctAnswer)) {
                    printWriter.println("Question " + i + ": " + questionText);
                    printWriter.println("Your Answer: " + userAnswer + " - Correct");
                    correctAnswers++;
                } else {
                    printWriter.println("Question " + i + ": " + questionText);
                    printWriter.println("Your Answer: " + userAnswer + " - Incorrect");
                }
                System.out.println(correctAnswers + " out of " + i);
            }

            printWriter.println("Final Score: " + correctAnswers + " out of 5");
            JOptionPane.showMessageDialog(getParent(), "Trivia session finished You got " + correctAnswers + " out of 5 correct.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(getParent(), "Error writing to file: " + ex.getMessage());
            }
    }

    public static void main(String[] args) {
        new TriviaUI(args);
    }
}
