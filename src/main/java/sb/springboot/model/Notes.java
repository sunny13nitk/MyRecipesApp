package sb.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Notes
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notesId;
	
	@Lob
	private String notes;
	
	@OneToOne(mappedBy = "notes")
	private Recipe recipe;
	
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
	
	public Notes(
	)
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
}
