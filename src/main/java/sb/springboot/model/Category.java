package sb.springboot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Category
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long catId;
	
	private String description;
	
	@ManyToMany
	@JoinTable(
	        name = "Recipe_Category", joinColumns = @JoinColumn(name = "catId"), inverseJoinColumns = @JoinColumn(name = "recipeId")
	)
	private List<Recipe> recipes;
	
	public Category(
	)
	{
		super();
		this.recipes = new ArrayList<Recipe>();
	}
	
}
