package sb.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sb.springboot.repository.RecipeRepository;

@Controller
public class IndexController
{
	private final String indexView = "index";
	
	private final RecipeRepository recipeRepo;
	
	@Autowired
	public IndexController(
	        RecipeRepository recipeRepo
	)
	{
		super();
		this.recipeRepo = recipeRepo;
	}
	
	@GetMapping(
	    { "/", "" }
	)
	public String showIndexPage(
	        Model model
	)
	{
		model.addAttribute("recipes", recipeRepo.findAll());
		return indexView;
	}
	
}
