package uz.pdp.appwerehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwerehouse.entity.Category;
import uz.pdp.appwerehouse.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

        boolean existsByNameAndPhoneNumber(String name, String phoneNumber);


}
