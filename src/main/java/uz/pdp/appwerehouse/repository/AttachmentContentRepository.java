package uz.pdp.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.pdp.appwerehouse.entity.AttachmentContent;
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
}
