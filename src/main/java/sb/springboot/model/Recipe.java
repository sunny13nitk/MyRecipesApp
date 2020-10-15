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

import lombok.Data;
import sb.springboot.enums.Difficulty;

@Entity
@Data
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
	private List<RecipeConstituent> constituents = new ArrayList<RecipeConstituent>();;
	
	@ManyToMany
	@JoinTable(
	        name = "Recipe_Category", joinColumns = @JoinColumn(name = "recipeId"), inverseJoinColumns = @JoinColumn(name = "catId")
	)
	private List<Category> categories = new ArrayList<Category>();;
	
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
