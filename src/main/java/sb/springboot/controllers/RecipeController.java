package sb.springboot.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import sb.springboot.model.Recipe;
import sb.springboot.repository.RecipeRepository;

@Controller
@Slf4j
@RequestMapping("/recipes")
public class RecipeController
{
	
	private final String recipeDetails = "recipe/show";
	private final String recipeForm    = "recipe/recipeForm";
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	@GetMapping("/{recipeId}")
	public String showRecipe(
	        @PathVariable String recipeId, Model model
	)
	{
		if (recipeId != null)
		{
			Optional<Recipe> recipe = recipeRepo.findById(new Long(recipeId));
			if (recipe != null)
			{
				model.addAttribute("recipe", recipe.get());
			}
		}
		return recipeDetails;
	}
	
	@GetMapping("/{recipeId}/update")
	public String updateRecipe(
	        @PathVariable String recipeId, Model model
	)
	{
		if (recipeId != null)
		{
			Optional<Recipe> recipe = recipeRepo.findById(new Long(recipeId));
			if (recipe != null)
			{
				model.addAttribute("recipe", recipe.get());
			}
		}
		return recipeForm;
	}
	
	@GetMapping("/new")
	public String showRecipeForm(
	        Model model
	)
	{
		model.addAttribute("recipe", new Recipe());
		return recipeForm;
	}
	
	@GetMapping("/{recipeId}/delete")
	public String deleteRecipeById(
	        String recipeId, Model model
	)
	{
		if (recipeId != null)
		{
			
			recipeRepo.deleteById(new Long(recipeId));
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/{recipeId}/ingredients")
	public String showRecipeIngredients(
	        @PathVariable String recipeId, Model model
	)
	{
		if (recipeId != null)
		{
			Recipe recipe = recipeRepo.findById(new Long(recipeId)).get();
			if (recipe != null)
			{
				log.debug("Fetching Ingredients for Recipe: " + recipeId);
				model.addAttribute("ingredients", recipe.getConstituents());
				model.addAttribute("recipeId", recipeId);
			}
		}
		return "recipe/constituents/list";
	}
	
	@PostMapping("/")
	public String saveRecipe(
	        @ModelAttribute Recipe recipe
	)
	{
		Recipe updRecipe = recipeRepo.save(recipe);
		return "redirect:/recipes/" + updRecipe.getRecipeId();
	}
}
