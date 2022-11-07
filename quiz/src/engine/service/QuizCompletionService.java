package engine.service;

import engine.model.Quiz;
import engine.model.QuizCompletion;
import engine.model.QuizUser;
import engine.repository.QuizCompletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QuizCompletionService {
    private final QuizCompletionRepository repository;

    @Autowired
    public QuizCompletionService(QuizCompletionRepository repository) {
        this.repository = repository;
    }

    public Page<QuizCompletion> getAllCompletions(int pageNo, QuizUser user) {
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.by("completedAt").descending());
        return repository.findBySolver(pageable, user);
    }

    public void saveCompletion(Quiz q, QuizUser solver) {
        QuizCompletion completion = new QuizCompletion();
        completion.setCompletedAt(LocalDateTime.now());
        completion.setQuiz(q);
        completion.setSolver(solver);
        System.out.println(q);
        System.out.println(solver);
        repository.save(completion);
    }

    public void deleteAllByQuiz(Quiz q) {
        repository.deleteAllByQuiz(q);
    }
}
