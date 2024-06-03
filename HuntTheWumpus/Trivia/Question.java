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
    private String answer;


    public Question(String index, String question, String answer){
        this.index = index;
        this.question = question;
        this.answer = answer;
    }

    public String getIndex() {
        return index;
    }


    public void setIndex(String index) {
        this.index = index;
    }


    public String getQuestion() {
        return question;
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

    @Override
    public String toString(){
        String line = this.index + "," + this.question + "," + this.answer;
        return line;
    }
    
}
