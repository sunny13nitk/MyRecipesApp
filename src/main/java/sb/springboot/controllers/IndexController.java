package sb.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import sb.springboot.repository.RecipeRepository;

@Controller
@Slf4j
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
		log.debug("Loading Index Page");
		model.addAttribute("recipes", recipeRepo.findAll());
		return indexView;
	}
	
}
