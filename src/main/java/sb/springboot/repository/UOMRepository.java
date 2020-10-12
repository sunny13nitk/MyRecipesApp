package sb.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sb.springboot.model.UOM;

public interface UOMRepository extends JpaRepository<UOM, Long>
{
	public Optional<UOM> findByDescription(
	        String description
	);
}
