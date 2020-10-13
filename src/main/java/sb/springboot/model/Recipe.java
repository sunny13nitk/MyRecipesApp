package sb.springboot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import sb.springboot.enums.Difficulty;

@Entity
public class Recipe
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   recipeId;
	private String description;
	private int    prepTime;
	private int    cookTime;
	private int    servings;
	private String source;
	private String url;
	
	@Lob
	private String directions;
	
	// add enum for Difficulty
	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;
	
	@Lob
	private Byte[] image;
	
	/*
	 * One to One - Uni-directional Relation
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "notesId")
	@Lob
	private Notes notes;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private List<RecipeConstituent> constituents;
	
	@ManyToMany
	@JoinTable(
	        name = "Recipe_Category", joinColumns = @JoinColumn(name = "recipeId"), inverseJoinColumns = @JoinColumn(name = "catId")
	)
	private List<Category> categories;
	
	public List<Category> getCategories(
	)
	{
		return categories;
	}
	
	public void setCategories(
	        List<Category> categories
	)
	{
		this.categories = categories;
	}
	
	public Difficulty getDifficulty(
	)
	{
		return difficulty;
	}
	
	public void setDifficulty(
	        Difficulty difficulty
	)
	{
		this.difficulty = difficulty;
	}
	
	public Long getRecipeId(
	)
	{
		return recipeId;
	}
	
	public void setRecipeId(
	        Long id
	)
	{
		this.recipeId = id;
	}
	
	public String getDescription(
	)
	{
		return description;
	}
	
	public void setDescription(
	        String description
	)
	{
		this.description = description;
	}
	
	public int getPrepTime(
	)
	{
		return prepTime;
	}
	
	public void setPrepTime(
	        int prepTime
	)
	{
		this.prepTime = prepTime;
	}
	
	public int getCookTime(
	)
	{
		return cookTime;
	}
	
	public void setCookTime(
	        int cookTime
	)
	{
		this.cookTime = cookTime;
	}
	
	public int getServings(
	)
	{
		return servings;
	}
	
	public void setServings(
	        int servings
	)
	{
		this.servings = servings;
	}
	
	public String getSource(
	)
	{
		return source;
	}
	
	public void setSource(
	        String source
	)
	{
		this.source = source;
	}
	
	public String getUrl(
	)
	{
		return url;
	}
	
	public void setUrl(
	        String url
	)
	{
		this.url = url;
	}
	
	public String getDirections(
	)
	{
		return directions;
	}
	
	public void setDirections(
	        String directions
	)
	{
		this.directions = directions;
	}
	
	public Byte[] getImage(
	)
	{
		return image;
	}
	
	public void setImage(
	        Byte[] image
	)
	{
		this.image = image;
	}
	
	public Notes getNotes(
	)
	{
		return notes;
	}
	
	public void setNotes(
	        Notes notes
	)
	{
		this.notes = notes;
	}
	
	public Recipe(
	)
	{
		this.constituents = new ArrayList<RecipeConstituent>();
		this.categories   = new ArrayList<Category>();
	}
	
	public List<RecipeConstituent> getConstituents(
	)
	{
		return constituents;
	}
	
	public void setConstituents(
	        List<RecipeConstituent> constituents
	)
	{
		this.constituents = constituents;
	}
	
	/*
	 * Helper Method to Add a Constituent Directly
	 */
	public Recipe addConstituent(
	        RecipeConstituent constituent
	)
	{
		constituent.setRecipe(this);
		this.constituents.add(constituent);
		return this;
	}
	
}
