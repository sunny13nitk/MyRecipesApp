package sb.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sb.springboot.enums.Difficulty;
import sb.springboot.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>
{
	public List<Recipe> findAllByDescriptionContaining(
	        String recipeDescription
	);
	
	public List<Recipe> findAllByDifficulty(
	        Difficulty difficultyLevel
	);
	
	public List<Recipe> findAllByPrepTimeGreaterThanEqual(
	        int prepTime
	);
	
	public List<Recipe> findAllByPrepTimeLessThanEqual(
	        int prepTime
	);
	
	public List<Recipe> findAllByServingsGreaterThanEqual(
	        int serves
	);
	
	public List<Recipe> findAllByNotesNotesContaining(
	        String notesMatchText
	);
	
	public List<Recipe> findAllByCategoriesDescriptionContaining(
	        String categoryText
	);
	
	public List<Recipe> findAllByConstituentsIngredientDescription(
	        String ingredientDescription
	);
}
