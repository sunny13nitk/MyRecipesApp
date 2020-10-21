package sb.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sb.springboot.model.RecipeConstituent;

public interface RecipeConstituentsRepository extends JpaRepository<RecipeConstituent, Long>
{
	
}
