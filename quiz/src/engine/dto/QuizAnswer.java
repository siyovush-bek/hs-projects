package engine.dto;

import java.util.ArrayList;
import java.util.List;

public class QuizAnswer {
    private List<String> answer;

    public QuizAnswer(List<String> answer) {
        this.answer = answer;
    }

    public QuizAnswer(){

    }

    public List<String> getAnswer() {
        if(answer == null) {
            answer = new ArrayList<>();
        }
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuizAnswer{" +
            "answer=" + answer +
            '}';
    }
}
