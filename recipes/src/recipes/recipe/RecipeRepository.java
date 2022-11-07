package recipes.recipe;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

  @Query("select r from Recipe r where lower(r.name) like lower(concat('%', ?1, '%'))")
  List<Recipe> findByNameContainingIgnoreCase(String name, Sort sort);

  @Query("select r from Recipe r where lower(r.category) = lower(?1)")
  List<Recipe> findByCategoryEquals(String category, Sort sort);

}
