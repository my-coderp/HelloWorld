package basic.com.repositories;

import basic.com.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Set up JPA repository
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByName(String name);

    Optional<Category> findById(Long id);

}
