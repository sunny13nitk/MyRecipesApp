package sb.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredient
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ingId;
	
	private String description;
	public long getIngId(
	)
	{
		return ingId;
	}
	
	public void setIngId(
	        long ingId
	)
	{
		this.ingId = ingId;
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
	
	public Ingredient(
	)
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	// UOM to DO
	
}
