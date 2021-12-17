package uz.pdp.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Attachment;
import uz.pdp.appwerehouse.service.AttachmentService;

import java.io.IOException;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {


    @Autowired
    AttachmentService attachmentService;


//CREATE
    @PostMapping("/upload")
    public Result addAttachment(MultipartHttpServletRequest request) throws IOException {
       return attachmentService.uploadFile(request);
    }


//READ ALL ATTACHMENT
    @GetMapping
    public Page<Attachment> getAttachmentPage(@RequestParam int page){
        return attachmentService.getAttachmentPage(page);
    }


//READ ATTACHMENT BY ID
    @GetMapping(value = "/{id}")
    public Result getAttachmentById(@PathVariable Integer id){
         return new Result(attachmentService.getAttachmentById(id));
    }


//READ ATTACHMENT CONTENT BY ID
    @GetMapping(value = "/byContentId/{id}")
    public Result getAttachmentContentById(@PathVariable Integer id){
        return attachmentService.getAttachmentContentById(id);
    }


//UPDATE
    @PutMapping(value = "/{attachmentId}")
    public Result editAttachment(MultipartHttpServletRequest request ,@PathVariable Integer id) throws IOException {
        return attachmentService.editAttachment(id,request);
    }


//DELETE
    @DeleteMapping(value = "/{id}")
    public Result deleteAttachment(@PathVariable Integer id){
        return attachmentService.deleteAttachment(id);
    }



}
