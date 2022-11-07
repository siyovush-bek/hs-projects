package engine.controller;

import engine.model.Quiz;
import engine.dto.QuizAnswer;
import engine.dto.QuizResponse;
import engine.model.QuizCompletion;
import engine.model.QuizUser;
import engine.service.QuizCompletionService;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/quizzes")
public class QuizController {
    private QuizService quizService;
    private QuizCompletionService quizCompletionService;

    @Autowired
    public QuizController(QuizService questionService, QuizCompletionService quizCompletionService) {
        this.quizService = questionService;
        this.quizCompletionService = quizCompletionService;
    }

    @GetMapping
    public Page<Quiz> getAllQuizzes(@RequestParam(defaultValue = "0") int page) {
        return quizService.getAllQuizzesByPage(page);
    }

    @GetMapping("completed")
    public Page<QuizCompletion> getAllQuizCompletions(@RequestParam(defaultValue = "0") int page,
                                                      @AuthenticationPrincipal QuizUser user) {
        return quizCompletionService.getAllCompletions(page, user);
    }

    @PostMapping
    public Quiz addNewQuiz(@RequestBody @Validated Quiz newQuiz,
                           @AuthenticationPrincipal QuizUser user) {
        newQuiz.setAuthor(user);
        quizService.addNewQuiz(newQuiz);
        return newQuiz;
    }

    @GetMapping("{quizId}")
    public Quiz getQuizById(@PathVariable long quizId) {
        return quizService.getById(quizId);
    }

    @DeleteMapping("{quizId}")
    public ResponseEntity<Object> deleteById(@PathVariable long quizId,
                                             @AuthenticationPrincipal QuizUser user) {
        quizService.deleteQuiz(quizId, user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{quizId}/solve")
    public QuizResponse solveQuiz(@RequestBody QuizAnswer answer,
                                  @PathVariable long quizId,
                                  @AuthenticationPrincipal QuizUser user){
        return quizService.solveQuizWithId(quizId, answer, user);
    }

}
