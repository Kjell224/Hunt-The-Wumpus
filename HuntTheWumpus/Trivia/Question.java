// Kjell Tingelstad
// 2/16/24
// Period 5
// Hunt the Wumpus - Trivia Class

package Trivia;

public class Question {
    //////////////////////
    // Properties & Fields
    //////////////////////
    private String index;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;


    public Question(String index, String question, String optionA, String optionB, String optionC, String optionD, String answer){
        this.index = index;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }

    public String getIndex() {
        return index;
    }


    public void setIndex(String index) {
        this.index = index;
    }


    public String getQuestion() {
        return this.question;
    }


    public void setQuestion(String question) {
        this.question = question;
    }


    public String getAnswer() {
        return answer;
    }


    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public String printQuestion(){
        return this.question + " (" + this.optionA + "," + this.optionB + "," + this.optionC + "," + this.optionD + ")";
    }

    @Override
    public String toString(){
        String line = this.index + "," + this.question + this.optionA + "," + this.optionB + "," + this.optionC + "," + this.optionD + "," + this.answer;
        return line;
    }
    
}
