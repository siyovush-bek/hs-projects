package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.time.LocalDateTime;

@JsonPropertyOrder({"id", "completedAt"})
@Entity
public class QuizCompletion {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long completion_id;

    @JsonProperty("completedAt")
    private LocalDateTime completedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "solver_id")
    private QuizUser solver;

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return quiz.getId();
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public QuizUser getSolver() {
        return solver;
    }

    public void setSolver(QuizUser solver) {
        this.solver = solver;
    }

}
