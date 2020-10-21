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

import sb.springboot.model.Recipe;
import sb.springboot.model.RecipeConstituent;
import sb.springboot.repository.IngredientRepository;
import sb.springboot.repository.RecipeConstituentsRepository;
import sb.springboot.repository.RecipeRepository;
import sb.springboot.repository.UOMRepository;
import sb.springboot.services.interfaces.RecipeService;

@Controller
@RequestMapping("/recipeConstituents")
public class RecipeConstituentController
{
	
	@Autowired
	private RecipeConstituentsRepository repConRepo;
	
	@Autowired
	private UOMRepository uomRepo;
	
	@Autowired
	private IngredientRepository ingRepo;
	
	@Autowired
	private RecipeService recipeSrv;
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	@GetMapping("/{consId}/update")
	public String updateRecipeConstituents(
	        @PathVariable String consId, Model model
	)
	{
		if (consId != null)
		{
			Optional<RecipeConstituent> recCons = repConRepo.findById(new Long(consId));
			if (recCons != null)
			{
				model.addAttribute("recipeConstituent", recCons.get());
				model.addAttribute("uomList", uomRepo.findAll());
			}
		}
		
		return "recipe/constituents/constituentsform";
	}
	
	@GetMapping("/{consId}/delete")
	public String deleteRecipeConstituents(
	        @PathVariable String consId
	)
	{
		Long recipeId = 0L;
		if (consId != null)
		{
			
			Optional<RecipeConstituent> recipeCons = repConRepo.findById(new Long(consId));
			if (recipeCons.isPresent())
			{
				recipeId = recipeCons.get().getRecipe().getRecipeId();
				repConRepo.deleteById(new Long(consId));
			}
			
		}
		
		return "redirect:/recipes/" + recipeId + "/ingredients";
	}
	
	@GetMapping("/{recipeId}/constituents/new")
	public String showAddConstituent(
	        @PathVariable String recipeId, Model model
	)
	{
		//Create a new Constituent and pass it over to Form
		//Set the Recipe In Properly
		Recipe recipe = null;
		if (recipeId != null)
		{
			Optional<Recipe> recipeO = recipeRepo.findById(new Long(recipeId));
			if (recipeO.isPresent())
			{
				recipe = recipeO.get();
			}
			
			RecipeConstituent recpCons    = new RecipeConstituent();
			Recipe            dummyrecipe = new Recipe();
			dummyrecipe.setRecipeId(recipe.getRecipeId());
			
			//Just set a lean recipe with just an ID to pass to View so it can be
			//propagated further to POST event as a recipe ID
			recpCons.setRecipe(dummyrecipe);
			model.addAttribute("recipeConstituent", recpCons);
			model.addAttribute("ingredientList", ingRepo.findAll());
			model.addAttribute("uomList", uomRepo.findAll());
			
		}
		
		return "recipe/constituents/constituentsnewform";
	}
	
	@PostMapping("/")
	public String saveConstituent(
	        @ModelAttribute RecipeConstituent recipeCons
	)
	{
		if (recipeCons != null && recipeCons.getRecipe().getRecipeId() > 0)
		{
			/*
			 * Can't be saving the Recipe Constituent Directly As it is a DETACHED ENTITY, HIBERNATE will expect to Save
			 * the Recipe Also as the CASCADE is NOT ALL from Constituent to Recipe Cascading is a handy ORM feature,
			 * but it’s not free of issues.
			 * -------------------------------------------------------------------------------- ------------------- ----
			 * You should only cascade from Parent entities to Children and not the other way around. You should always
			 * use only the casacde operations that are demanded by your business logic requirements, and not turn the
			 * CascadeType.ALL into a default Parent – Child association entity state propagation configuration.
			 */
			/*
			 * RecipeConstituent savedCons = repConRepo.save(recipeCons); if (savedCons != null) { recipeId =
			 * savedCons.getRecipe().getRecipeId(); }
			 */
			
			recipeSrv.updateRecipeConstituent(recipeCons.getRecipe().getRecipeId(), recipeCons);
			
		}
		
		// REdirect to Recipe Constituents(Ingredients)
		
		return "redirect:/recipes/" + recipeCons.getRecipe().getRecipeId() + "/ingredients";
	}
	
}
