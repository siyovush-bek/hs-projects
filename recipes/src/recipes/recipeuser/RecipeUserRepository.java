package recipes.recipeuser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface RecipeUserRepository extends CrudRepository<RecipeUser, Long> {
    Optional<RecipeUser> findByEmail(String email);
}
