package sb.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController
{
	private final String indexView = "index";
	
	@GetMapping(
	    { "/", "" }
	)
	public String showIndexPage(
	)
	{
		
		return indexView;
	}
	
}
