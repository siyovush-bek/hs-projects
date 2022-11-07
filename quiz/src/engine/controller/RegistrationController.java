package engine.controller;

import engine.model.QuizUser;
import engine.service.QuizUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/register")
public class RegistrationController {

    private QuizUserService userService;

    public RegistrationController(QuizUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> addNewUser(@RequestBody @Validated QuizUser user) {
        userService.addUser(user);
        return ResponseEntity.ok().build();
    }
}
