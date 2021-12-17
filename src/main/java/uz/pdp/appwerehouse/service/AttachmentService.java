package uz.pdp.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Attachment;
import uz.pdp.appwerehouse.entity.AttachmentContent;
import uz.pdp.appwerehouse.repository.AttachmentContentRepository;
import uz.pdp.appwerehouse.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {


    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public Result uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);

        return new Result("Fayl saqlandi", true,savedAttachment.getId());

    }

    public Result getAttachmentById(Integer id){
        if (!attachmentRepository.existsById(id)){
            return new Result("Bunday fayl topilmadi",false);
        }
        return new Result(attachmentRepository.findById(id));
    }

    public Result getAttachmentContentById(Integer id){
        if (!attachmentRepository.existsById(id)){
            return new Result("Bunday fayl topilmadi", false);
        }
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(id);

        return new Result(optionalAttachmentContent.get());
    }

    public Page<Attachment> getAttachmentPage(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return attachmentRepository.findAll(pageable);
    }

    public Result editAttachment(Integer id, MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        Attachment attachment =optionalAttachment.get();
        attachment.setName(file.getName());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment saveAttachment = attachmentRepository.save(attachment);

        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(id);
        AttachmentContent attachmentContent = optionalAttachmentContent.get();

        attachmentContent.setAttachment(saveAttachment);
        attachmentContent.setBytes(file.getBytes());
        attachmentContentRepository.save(attachmentContent);
        return new Result("Fayl tahrirlandi",true ,saveAttachment.getId());
    }

    public Result deleteAttachment(Integer id){
        if (attachmentRepository.existsById(id)){
            return new Result("Bunday fayl topilmadi",false);
        }
        attachmentRepository.deleteById(id);
        return new Result("Fayl o'chirildi",true);
    }
}










