package sb.springboot.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import sb.springboot.repository.RecipeRepository;

public class IndexControllerTest
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
	public void testShowIndexPage(
	)
	{
		String viewName = controller.showIndexPage(model);
		assertEquals("index", viewName);
	}
	
}
