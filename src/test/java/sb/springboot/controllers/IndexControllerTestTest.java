package sb.springboot.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import sb.springboot.repository.RecipeRepository;

class IndexControllerTestTest
{
	@Mock
	RecipeRepository recipeRepo;
	
	@Mock
	Model model;
	
	IndexController controller;
	
	@Before
	public void setUp(
	) throws Exception
	{
		
		MockitoAnnotations.initMocks(this);
		// Create instance of Index Controller Injecting Mock Recipe Repository
		controller = new IndexController(recipeRepo);
		
	}
	
	@Test
	public void getIndexPage(
	) throws Exception
	{
		
		String viewName = controller.showIndexPage(model);
		
		assertEquals("index", viewName);
		verify(recipeRepo, times(1)).findAll();
		verify(model, times(1)).addAttribute(eq("recipes"), anySet());
	}
	
}
