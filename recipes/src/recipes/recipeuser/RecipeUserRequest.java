package recipes.recipeuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeUserRequest {
    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(min = 8)
    String password;
}
