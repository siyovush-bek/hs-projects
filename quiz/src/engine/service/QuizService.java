package engine.service;

import engine.dto.QuizAnswer;
import engine.dto.QuizResponse;
import engine.exception.ForbiddenException;
import engine.exception.QuizNotFoundException;
import engine.model.Quiz;
import engine.model.QuizUser;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    public static final int PAGE_SIZE = 10;
    private QuizRepository quizRepository;
    private QuizCompletionService completionService;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuizCompletionService completionService) {
        this.quizRepository = quizRepository;
        this.completionService = completionService;
    }



    public Quiz getById(long id) {
        return quizRepository.findById(id)
            .orElseThrow(QuizNotFoundException::new);
    }

    public QuizResponse solveQuizWithId(long quizId, QuizAnswer answer, QuizUser user) {
        Quiz q = getById(quizId);
        QuizResponse response = new QuizResponse();
        response.setSuccess(false);

        if(q.checkAnswers(answer.getAnswer())) {
            response.setSuccess(true);
            completionService.saveCompletion(q, user);
        }

        return response;
    }

    public List<Quiz> getAllQuizzes() {
        List<Quiz> allQuizzes = new ArrayList<>();
        for(Quiz q : quizRepository.findAll()) {
            allQuizzes.add(q);
        }
        return allQuizzes;
    }

    public void addNewQuiz(Quiz newQuiz) {
        quizRepository.save(newQuiz);
    }

    @Transactional
    public void deleteQuiz(long quizId, @NotNull QuizUser user) {
        Quiz q = quizRepository.findById(quizId)
            .orElseThrow(QuizNotFoundException::new);

        if(!q.getAuthor().getEmail().equals(user.getEmail())) {
            throw new ForbiddenException();
        }

        completionService.deleteAllByQuiz(q);

        quizRepository.delete(q);
    }

    public Page<Quiz> getAllQuizzesByPage(int pageNo) {
        Pageable paging = PageRequest.of(pageNo, PAGE_SIZE);
        return quizRepository.findAll(paging);
    }

}
