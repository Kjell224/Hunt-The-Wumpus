// Kjell Tingelstad
// 2/16/24
// Period 5
// Hunt the Wumpus - Trivia Class

package Trivia;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileWriter;

public class Trivia{
    //////////////////////
    // Properties & Fields
    //////////////////////
    private File file;
    private ArrayList<Question> questions;
    private ArrayList<Question> tempQuestions;
    /////////////////////
    // Constructor(s)
    /////////////////////

    public Trivia(){
        this.file = new File("HuntTheWumpus/Trivia/Questions.csv");
        this.questions = createQuestions(this.file);
        this.tempQuestions = this.questions;
        Collections.shuffle(this.questions);
    }

    ///////////////////////
    // Methods
    //////////////////////

    public ArrayList<Question> createQuestions(File f){
        ArrayList<Question> lines = new ArrayList<Question>();
        try{
            Scanner s = new Scanner(f); //Creates a new scanner to read the questions csv file
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] parts = line.split(",");
                lines.add(new Question(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]));
            }
            s.close();
        } catch(FileNotFoundException e){ //I use the catch in case the file is not found
            System.out.println("File not found");
        }
        return lines;
    }


    
    public Question getQuestion(){
        Question tempQuestion = this.questions.get(0);
        this.questions.remove(0);
        try{
            FileWriter writer = new FileWriter(this.file);
            for(int i = 0; i < this.questions.size(); i++){
                writer.write(this.questions.get(i).toString() + "\n");
            }
            writer.close();
        } catch(Exception e){
            System.out.println("File not found!!!");
        }
        return tempQuestion;
    }

    // method used by game control
    public void reInitilizeQuestions(){
        try{
            FileWriter writer = new FileWriter(this.file);
            for(int i = 0; i < this.tempQuestions.size(); i++){
                writer.write(this.tempQuestions.get(i).toString() + "\n");
            }
            writer.close();
        } catch (Exception e){
            System.out.println("I SUCK! " + e);
        }
    }

}