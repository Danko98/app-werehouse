package uz.pdp.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwerehouse.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByNameAndPhoneNumber(String name, String phoneNumber);

}
