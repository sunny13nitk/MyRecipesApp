package sb.springboot.services.impl;

import java.io.IOException;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	@Override
	public void saveImageFile(
	        Long recipeId, MultipartFile file
	)
	{
		
		/*
		 * Test Code to check to Excel Workbook Handling feature
		 */
		
		//handlexlsWb(file);
		
		// Get the Recipe from Recipe Repo
		Optional<Recipe> recipeOp = recipeRepo.findById(recipeId);
		if (!recipeOp.isPresent())
		{
			log.debug("Recipe not found for Id : " + recipeId + "Triggered At : " + this.getClass());
			throw new RuntimeException("Recipe not found for Id : " + recipeId);
		}
		
		Recipe recipe = recipeOp.get();
		//Create a byte Array
		Byte[] byteObjects = null;
		
		try
		{
			//Get byte length of file
			byteObjects = new Byte[file.getBytes().length];
			
			int i = 0;
			
			for (byte b : file.getBytes())
			{
				byteObjects[i++] = b;
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		recipe.setImage(byteObjects);
		
		recipeRepo.save(recipe);
		
	}
	
	private void handlexlsWb(
	        MultipartFile file
	)
	{
		try
		{
			XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
			if (wb != null)
			{
				for (XSSFSheet xssfSheet : wb)
				{
					System.out.println(xssfSheet.getSheetName());
				}
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
