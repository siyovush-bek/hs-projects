package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private long id;

    @NotBlank
    private String title;
    @NotBlank
    private String text;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private QuizUser author;

    @NotNull
    @Size(min = 2)
    private  ArrayList<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ArrayList<String> answer;

//    @JsonIgnore
//    @OneToMany(orphanRemoval = true)
//    List<QuizCompletion> completions;

    public Quiz(long id, String title, String text, @NotNull @Size(min = 2) ArrayList<String> options, ArrayList<String> answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public Quiz(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(@NotNull @Size(min = 2) ArrayList<String> options) {
        this.options = options;
    }

    public List<String> getAnswer() {
        if(answer == null) {
            answer = new ArrayList<String>();
        }
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    public QuizUser getAuthor() {
        return author;
    }

    public void setAuthor(QuizUser author) {
        this.author = author;
    }

    public boolean checkAnswers(List<String> answer) {
        SortedSet<String> correctAnswers = new TreeSet<>(this.getAnswer());
        SortedSet<String> givenAnswers = new TreeSet<>(answer);
        if(correctAnswers.equals(givenAnswers)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Quiz{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", text='" + text + '\'' +
            ", author=" + author +
            ", options=" + options +
            ", answer=" + answer +
            '}';
    }
}
