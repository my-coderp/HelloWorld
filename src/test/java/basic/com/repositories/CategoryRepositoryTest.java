package basic.com.repositories;

import basic.com.domain.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;






@DataJpaTest  //only testing the JPA functionality but against a live database
class CategoryRepositoryTest {

        @Autowired
        CategoryRepository repository;


        @Test
        void testFindById() {

            List<Category> names = new ArrayList<>();

            names.add(repository.save(new Category("Jack")));
            names.add(repository.save(new Category("Chloe")));
            names.add(repository.save(new Category("Kim")));
            names.add(repository.save(new Category("David")));
            names.add(repository.save(new Category("Michelle")));

            // fetch an individual customer by ID
            Long id = names.get(0).getId();
            Optional<Category> cat = repository.findById(id);

            if (cat.isPresent()){
                Category category = cat.get();
                assertEquals("Jack", category.getName());
            }
            else fail("Category Record not found");


        }

        @Test
        void testFindByName() {
            repository.save(new Category("Jack"));
            repository.save(new Category("Chloe"));
            repository.save(new Category("Jack"));
            repository.save(new Category("David"));
            repository.save(new Category("Michelle"));

            // fetch category (list) by last name
            List<Category> names = repository.findByName("Jack");

            assertEquals("Jack", names.get(0).getName());
            assertEquals("Jack", names.get(1).getName());

        }
    }