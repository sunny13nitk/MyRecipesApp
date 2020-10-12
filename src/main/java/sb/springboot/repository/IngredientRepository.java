package sb.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sb.springboot.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>
{
	public Optional<Ingredient> findByDescription(
	        String description
	);
}
