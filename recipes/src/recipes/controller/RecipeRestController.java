package recipes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.exception.BadRequestException;
import recipes.exception.RecipeNotFoundException;
import recipes.recipe.Recipe;
import recipes.recipe.RecipeService;
import recipes.recipeuser.RecipeUser;
import recipes.recipeuser.RecipeUserRequest;
import recipes.recipeuser.RecipeUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipe")
public class RecipeRestController {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeUserService recipeUserService;

    Logger logger = LoggerFactory.getLogger(RecipeRestController.class);

    @PostMapping("new")
    private ResponseEntity<Object> addNewRecipe(@AuthenticationPrincipal RecipeUser recipeUser,
                                                @RequestBody @Validated Recipe recipe){
        recipe.setDate(LocalDateTime.now());
        recipe.setAuthor(recipeUser);
        long id = recipeService.save(recipe);
        return ResponseEntity.status(HttpStatus.OK)
            .body(Map.of("id", id));
    }


    @GetMapping("{id}")
    private Recipe recipeById(@PathVariable long id) {
        return recipeService.findById(id)
            .orElseThrow(RecipeNotFoundException::new);
    }

    @PutMapping("{id}")
    private ResponseEntity updateRecipe(@RequestBody @Validated Recipe recipe,
                                        @PathVariable long id,
                                        @AuthenticationPrincipal RecipeUser recipeUser) {
        Recipe r = recipeService.findById(id)
            .orElseThrow(RecipeNotFoundException::new);
        if(r.getAuthor().getEmail().equals(recipeUser.getEmail())) {
            r.updateBy(recipe);
            recipeService.save(r);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

    @DeleteMapping("{id}")
    private ResponseEntity<Object> deleteRecipe(@PathVariable long id,
                                                @AuthenticationPrincipal RecipeUser recipeUser) {
        Recipe r = recipeService.findById(id)
            .orElseThrow(RecipeNotFoundException::new);
        if(r.getAuthor().getEmail().equals(recipeUser.getEmail())) {
            recipeService.deleteRecipe(r.getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("search")
    private List<Recipe> searchRecipes(@RequestParam Optional<String> name, @RequestParam Optional<String> category) {
        if((name.isPresent() && category.isPresent()) ||
            name.isEmpty() && category.isEmpty()) {
            throw new BadRequestException();
        }
        if(name.isPresent()) {
            if(name.get().isBlank()) return List.of();
            return recipeService.findRecipesByName(name.get());
        }
        if(category.isPresent()) {
            if(category.get().isBlank()) return List.of();
            List<Recipe> rr = recipeService.findRecipesByCategory(category.get());
            return rr;
        }
        return List.of();
    }

    @GetMapping("all")
    private List<Recipe> all() {
        return recipeService.findAll();
    }

    @GetMapping("whoami")
    public String whoAmI(@AuthenticationPrincipal RecipeUser recipeUser) {
        return recipeUser.getEmail();
    }
}
