package sb.springboot.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import sb.springboot.model.Recipe;
import sb.springboot.repository.RecipeRepository;
import sb.springboot.services.interfaces.RecipeService;

@Controller
@Slf4j
@RequestMapping("/recipes")
public class RecipeController
{
	
	private final String recipeDetails = "recipe/show";
	private final String recipeForm    = "recipe/recipeForm";
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	@Autowired
	private RecipeService recipeSrv;
	
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
	
	@GetMapping("{recipeId}/image")
	public String showUploadForm(
	        @PathVariable String recipeId, Model model
	)
	{
		Optional<Recipe> recipe = recipeRepo.findById(new Long(recipeId));
		if (recipe != null)
		{
			model.addAttribute("recipe", recipe.get());
		}
		
		return "recipe/imageuploadform";
	}
	
	@GetMapping("/{recipeId}/recipeimage")
	public void renderImageFromDB(
	        @PathVariable String recipeId, HttpServletResponse response
	) throws IOException
	{
		Recipe           recipe  = null;
		Optional<Recipe> recipeO = recipeRepo.findById(new Long(recipeId));
		if (recipeO.isPresent())
		{
			recipe = recipeO.get();
			
			if (recipe.getImage() != null)
			{
				byte[] byteArray = new byte[recipe.getImage().length];
				int    i         = 0;
				
				for (Byte wrappedByte : recipe.getImage())
				{
					byteArray[i++] = wrappedByte; //auto unboxing
				}
				
				//Images
				response.setContentType("image/jpeg");
				
				InputStream is = new ByteArrayInputStream(byteArray);
				IOUtils.copy(is, response.getOutputStream());
				
				//PDF
				//response.setContentType("application/pdf");
				
			}
		}
	}
	
	@PostMapping("/")
	public String saveRecipe(
	        @ModelAttribute Recipe recipe
	)
	{
		Recipe updRecipe = recipeRepo.save(recipe);
		return "redirect:/recipes/" + updRecipe.getRecipeId();
	}
	
	@PostMapping("{recipeId}/image")
	public String handleImagePost(
	        @PathVariable String recipeId, @RequestParam("imagefile") MultipartFile file
	)
	{
		
		recipeSrv.saveImageFile(Long.valueOf(recipeId), file);
		
		return "redirect:/recipes/" + recipeId;
	}
}
