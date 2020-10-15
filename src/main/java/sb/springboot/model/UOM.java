package sb.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UOM
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uomId;
	
	private String description;
	
}
