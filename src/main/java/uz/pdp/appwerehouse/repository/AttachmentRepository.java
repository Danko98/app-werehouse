package uz.pdp.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwerehouse.entity.Attachment;

import javax.persistence.criteria.CriteriaBuilder;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {


}
