package recipes.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.recipeuser.RecipeUser;
import recipes.recipeuser.RecipeUserRequest;
import recipes.recipeuser.RecipeUserService;

import java.util.List;


@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegistrationRestController {

    private final RecipeUserService recipeUserService;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody @Validated RecipeUserRequest recipeUser) {
        recipeUserService.addNewUser(recipeUser);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("sample")
//    public RecipeUserRequest requestSample() {
//        return new RecipeUserRequest("siyovusht@gmail.com", "323248sm");
//    }
//
//    @GetMapping("all")
//    public List<RecipeUser> getAll() {
//        return recipeUserService.getAllUsers();
//    }

}
