package sb.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Notes
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notesId;
	
	@Lob
	private String notes;
	
	@OneToOne(mappedBy = "notes")
	private Recipe recipe;
	
}
