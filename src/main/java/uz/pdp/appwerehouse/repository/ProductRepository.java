package uz.pdp.appwerehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwerehouse.entity.Product;

import javax.persistence.criteria.CriteriaBuilder;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByNameAndCategoryId(String name, Integer category_id);
    Page<Product> findAllBy(Pageable pageable);
    Page<Product> findAllByCategory_Id(Integer category_id, Pageable pageable);
    Page<Product> findAllByName(String name, Pageable pageable);
    Page<Product> findAllByMeasurement_Id(Integer measurement_id, Pageable pageable);


}
