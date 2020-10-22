package sb.springboot.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import sb.springboot.model.Recipe;
import sb.springboot.model.RecipeConstituent;

public interface RecipeService
{
	/*
	 * Add a New Recipe Constituent or Update Existing One 
	 * --------------------------------------------------- ------
	 * Persist Children using Parent- to avoid detached entities in JPA
	 */
	public Recipe updateRecipeConstituent(
	        Long recipeId, RecipeConstituent recipeConstituent
	);
	
	public void saveImageFile(
	        Long recipeId, MultipartFile file
	);
}
