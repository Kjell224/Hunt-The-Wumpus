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

    /////////////////////
    // Constructor(s)
    /////////////////////

    public Trivia(){
        this.file = new File("HuntTheWumpus\\Trivia\\Questions.csv");
        this.questions = new ArrayList<Question>();
        createQuestions(this.file);
    }

    ///////////////////////
    // Methods
    //////////////////////

    public void createQuestions(File f){
        try{
            Scanner s = new Scanner(f); //Creates a new scanner to read the questions csv file
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] parts = line.split(",");
                this.questions.add(new Question(parts[0], parts[1], parts[2]));
            }
            s.close();
        } catch(FileNotFoundException e){ //I use the catch in case the file is not found
            System.out.println("File not found");
        }
        Collections.shuffle(this.questions);
    }


/*
 public boolean askQuestions(int numOfQuestions, int needCorrect){
        int numOfCorrect = 0;
        String active = "";
        ArrayList<String> indexes = new ArrayList<String>(); // This arrayList keeps track of which questions were asked so it knows what needs to be removed from the csv
        Scanner s = new Scanner(System.in);
        for(int r = 0; r < numOfQuestions; r++){ // This for loop asks the number of questions specified and it checks if the user got it right
            active = this.questions[r][1]; //This sets the string active to the part of the array that contains the question
            indexes.add(this.questions[r][0]);
            System.out.println(active);
            String userAns = s.nextLine();
            if(userAns.equalsIgnoreCase(this.questions[r][2])){ //Checks if the user got the question right
                numOfCorrect++;
                System.out.println("That is correct!");
            } else {
                System.out.println("Sorry, that is incorrect");
            }
        }
        s.close();

        ArrayList<String[]> tempQuestions = new ArrayList<String[]>(); //Creates an arraylist
        for(int i = 0; i < this.questions.length; i++){ //Adds all of the questions that weren't asked to the arrayList
            if(indexes.contains(this.questions[i][0])){
                continue;
            } else {
                tempQuestions.add(this.questions[i]);
            }
        }
        this.questions = new String[tempQuestions.size()][R];
        for(int i = 0; i < tempQuestions.size(); i++){  //Sets the global questions to the temp array
            this.questions[i] = tempQuestions.get(i);
        }

        
         try{
            FileWriter writer = new FileWriter(this.file); //Instantiates a new filewriter
            String line = "";
            for(int i = 0; i < this.questions.length; i++){ //The for loop adds all of the info from questions into the csv file, this is what was already in questions except it removed questions that were asked
                for(int k = 0; k < R; k++){
                    line += questions[i][k];
                    line += ",";
                }
                writer.write(line + "\n"); // Writers the line and starts a new line
                line = "";
            }
            writer.close();

        } catch(Exception e){
            System.out.println("File not found!!!");
        }
        
        return numOfCorrect >= needCorrect;
    }
 */
    
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
}