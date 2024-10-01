package basic.com.domain;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;


/**
 * Basic Category POJO using JPA autogenerated ID
 */

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "categories")
public class Category {

    public Category() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return id != null && Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
