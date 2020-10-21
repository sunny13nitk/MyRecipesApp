package sb.springboot.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sb.springboot.model.Ingredient;
import sb.springboot.repository.IngredientRepository;

@Controller
@RequestMapping("/ingredients")
public class IngredientController
{
	@Autowired
	private IngredientRepository ingRepo;
	
	@GetMapping("/{ingId}")
	public String showIngredient(
	        @PathVariable String ingId, Model model
	)
	{
		if (ingId != null)
		{
			Optional<Ingredient> ing = ingRepo.findById(new Long(ingId));
			if (ing != null)
			{
				
				model.addAttribute("ingredient", ing.get());
				
			}
		}
		return "ingredients/show";
	}
	
}
