package sb.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sb.springboot.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{
	public Optional<Category> findByDescription(
	        String description
	);
}
