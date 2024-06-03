package basic.com.bootstrap;

import basic.com.repositories.CategoryRepository;
import basic.com.domain.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Basic data load for testing
 */


@Component
public class Bootstrap implements CommandLineRunner{

    private final CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category("Fruits");

        Category dried = new Category("Dried");

        Category fresh = new Category("Fresh");

        Category exotic = new Category("Exotic");

        Category nuts = new Category("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        System.out.println("Data Loaded = " + categoryRepository.count() );

    }
}
