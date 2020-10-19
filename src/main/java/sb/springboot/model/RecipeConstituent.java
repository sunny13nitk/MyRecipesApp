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

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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
	
}
