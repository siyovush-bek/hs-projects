package engine.repository;

import engine.model.Quiz;
import engine.model.QuizCompletion;
import engine.model.QuizUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCompletionRepository extends PagingAndSortingRepository<QuizCompletion, Long> {

    Page<QuizCompletion> findBySolver(Pageable pageable, QuizUser solver);

    void deleteAllByQuiz(Quiz q);
}
