package sb.springboot.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import sb.springboot.model.Recipe;
import sb.springboot.model.RecipeConstituent;
import sb.springboot.repository.RecipeRepository;
import sb.springboot.services.interfaces.RecipeService;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService
{
	@Autowired
	private RecipeRepository recipeRepo;
	
	@Override
	public Recipe updateRecipeConstituent(
	        Long recipeId, RecipeConstituent recipeConstituent
	)
	{
		Recipe savedRecipe = null;
		if (recipeId != null)
		{
			// Get the Recipe from Recipe Repo
			Optional<Recipe> recipeOp = recipeRepo.findById(recipeId);
			if (!recipeOp.isPresent())
			{
				log.debug("Recipe not found for Id : " + recipeId + "Triggered At : " + this.getClass());
				throw new RuntimeException("Recipe not found for Id : " + recipeId);
			}
			
			Recipe recipe = recipeOp.get();
			// Scan for Constituents for the one in question
			
			Optional<RecipeConstituent> recipeConsOp = recipe.getConstituents().stream()
			        .filter(x -> x.getRcid().equals(recipeConstituent.getRcid())).findFirst();
			
			if (recipeConsOp.isPresent())
			{
				//Update Scenario
				RecipeConstituent recipeCons = recipeConsOp.get();
				//Update from Passed in PoJO
				recipeCons.setAmount(recipeConstituent.getAmount());
				recipeCons.setIngredient(recipeConstituent.getIngredient());
				recipeCons.setUom(recipeConstituent.getUom());
				
			} else
			{
				//Create a new Constituent Scenario
				recipeConstituent.setRecipe(recipe);
				recipe.getConstituents().add(recipeConstituent);
				
			}
			
			// Save the Recipe
			savedRecipe = recipeRepo.save(recipe);
		}
		
		return savedRecipe;
		
	}
	
}
