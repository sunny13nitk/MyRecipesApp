package sb.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sb.springboot.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
	public Optional<Category> findByDescription(
	        String description
	);
}
