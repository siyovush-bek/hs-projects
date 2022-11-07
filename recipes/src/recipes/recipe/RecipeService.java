package recipes.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
  private RecipeRepository repository;

  @Autowired
  public RecipeService(RecipeRepository repository) {
    this.repository = repository;
  }

  public Long save(Recipe r) {
    return repository.save(r).getId();
  }

  public Optional<Recipe> findById(long id) {
    return repository.findById(id);
  }

  public boolean deleteRecipe(long id) {
    if(findById(id).isPresent()) {
      repository.deleteById(id);
      return true;
    }
    return false;
  }

//  public List<Recipe> findRecipesByNameOrCategory(Optional<String> nameOpt, Optional<String> categoryOpt) {
//    Sort sort = Sort.by("date").descending();
//    String name = nameOpt.orElse("");
//    String category = categoryOpt.orElse("");
//
//    if(!name.isBlank() && !category.isBlank()) {
//      return repository.findRecipesWithNameAndCategory(
//          name, category, sort);
//    }
//    if(!name.isBlank()) return repository.findRecipesWithName(name, sort);
//    if(!category.isBlank()) return repository.findRecipesWithCategory(category, sort);
//
//    return List.of();
//  }

  public List<Recipe> findRecipesByName(String name) {
    return repository.findByNameContainingIgnoreCase(name, Sort.by("date").descending());
  }

  public List<Recipe> findRecipesByCategory(String category) {
    return repository.findByCategoryEquals(category, Sort.by("date").descending());
  }

  public List<Recipe> findAll() {
    List<Recipe> recipes = new ArrayList<>();
    int i = 0;
    for(var r : repository.findAll()) {
      if(i > 10) break;
      recipes.add(r);
      i++;
    }
    return recipes;
  }
}
