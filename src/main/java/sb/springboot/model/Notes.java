package sb.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notes
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   notesId;
	private String notes;
	
	/*
	 * no need to maintain the relation on this side
	 */
	private Long recipeId;
	
	public Long getNotesId(
	)
	{
		return notesId;
	}
	
	public void setNotesId(
	        Long id
	)
	{
		this.notesId = id;
	}
	
	public String getNotes(
	)
	{
		return notes;
	}
	
	public void setNotes(
	        String notes
	)
	{
		this.notes = notes;
	}
	
	public Notes(
	)
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getRecipeId(
	)
	{
		return recipeId;
	}
	
	public void setRecipeId(
	        Long recipeId
	)
	{
		this.recipeId = recipeId;
	}
	
}
