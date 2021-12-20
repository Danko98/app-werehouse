package uz.pdp.appwerehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appwerehouse.entity.Measurement;
import uz.pdp.appwerehouse.projection.CustomMeasurement;

@RepositoryRestResource(path = "measurement",excerptProjection = CustomMeasurement.class)
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
        boolean existsByName(String name);
        Page<Measurement> findAllBy(Pageable pageable);
}
