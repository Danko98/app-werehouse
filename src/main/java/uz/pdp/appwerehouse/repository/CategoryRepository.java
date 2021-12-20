package uz.pdp.appwerehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appwerehouse.entity.Category;
import uz.pdp.appwerehouse.entity.Measurement;
import uz.pdp.appwerehouse.projection.CustomCategory;

@RepositoryRestResource(path = "category", excerptProjection = CustomCategory.class)
public interface CategoryRepository extends JpaRepository<Category, Integer> {

        boolean existsByName(String name);
        boolean existsByNameAndParenCategory_Id(String name, Integer parenCategory_id);
        Page<Category> findAllBy(Pageable pageable);
        Page<Category> findAllByParenCategory_Id(Integer parenCategory_id, Pageable pageable);

}
