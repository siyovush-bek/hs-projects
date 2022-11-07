package recipes.recipe;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipes.recipeuser.RecipeUser;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "recipes")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    @JsonIgnore
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    private LocalDateTime date;

    @NotBlank
    private String description;

    @NotEmpty
    @Size(max = 10)
    private ArrayList<String> ingredients;

    @NotEmpty
    @Size(max = 10)
    private ArrayList<String> directions;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private RecipeUser author;

    public void updateBy(Recipe recipe) {
        this.setName(recipe.getName());
        this.setCategory(recipe.getCategory());
        this.setDescription(recipe.getDescription());
        this.setDirections(recipe.getDirections());
        this.setIngredients(recipe.getIngredients());
        this.setDate(LocalDateTime.now());
    }
}
