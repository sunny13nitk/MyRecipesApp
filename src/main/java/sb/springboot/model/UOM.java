package sb.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UOM
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uomId;
	
	private String description;
	
	public Long getUomId(
	)
	{
		return uomId;
	}
	
	public void setUomId(
	        Long uomId
	)
	{
		this.uomId = uomId;
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
	
	public UOM(
	)
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
}
