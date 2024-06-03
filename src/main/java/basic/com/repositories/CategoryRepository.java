package basic.com.repositories;

import basic.com.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Set up JPA repository
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByName(String name);

    Optional<Category> findById(Long id);

}
