package sb.springboot.model;

import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class RecipeConstituent
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rcid;
	
	@OneToOne // No Cascading - Delete etc.
	@JoinColumn(name = "ingId") // Will be Fkey in RecipeConstituent
	private Ingredient ingredient;
	
	private double amount;
	
	@OneToOne // No cascading
	@JoinColumn(name = "uomId")
	private UOM uom;
	
	// No Delete effect on REcipe
	@ManyToOne(cascade =
	{ CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "recipeId")
	private Recipe recipe;
	
	public UOM getUom(
	)
	{
		return uom;
	}
	
	public void setUom(
	        UOM uom
	)
	{
		this.uom = uom;
	}
	
	public Long getRcid(
	)
	{
		return rcid;
	}
	
	public void setRcid(
	        Long rcid
	)
	{
		this.rcid = rcid;
	}
	
	public Ingredient getIngredient(
	)
	{
		return ingredient;
	}
	
	public void setIngredient(
	        Ingredient ingredient
	)
	{
		this.ingredient = ingredient;
	}
	
	public double getAmount(
	)
	{
		return amount;
	}
	
	public void setAmount(
	        double amount
	)
	{
		this.amount = amount;
	}
	
	public Recipe getRecipe(
	)
	{
		return recipe;
	}
	
	public void setRecipe(
	        Recipe recipe
	)
	{
		this.recipe = recipe;
	}
	
	public RecipeConstituent(
	        Optional<Ingredient> ingredient, double amount, Optional<UOM> uom
	)
	{
		super();
		this.ingredient = ingredient.get();
		this.amount     = amount;
		this.uom        = uom.get();
		
	}
	
	public RecipeConstituent(
	        Optional<Ingredient> ingredient, double amount, Optional<UOM> uom, Recipe recipe
	)
	{
		super();
		this.ingredient = ingredient.get();
		this.amount     = amount;
		this.uom        = uom.get();
		this.recipe     = recipe;
	}
	
	public RecipeConstituent(
	)
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
}
