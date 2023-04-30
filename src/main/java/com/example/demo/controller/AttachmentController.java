package com.example.demo.controller;

import com.example.demo.model.Attachment;
import com.example.demo.service.AttachmentService;
import com.example.demo.service.UploadFile;
import com.example.demo.utils.Response;
import com.example.demo.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class AttachmentController {

    @Autowired
    UploadFile uploadFile;
    @Autowired
    AttachmentService attachmentService;
@PostMapping("/images")
Response<Attachment> uploadFile(@RequestParam("file") MultipartFile file){
            try {
                String filenames = "http://localhost:8080/images/" + uploadFile.uploadFile(file);
                System.out.println(filenames);
              Attachment attachment= new Attachment().setImageURL(filenames)
                        .setFileType(file.getContentType())
                        .setData(UploadUtil.compressImage(file.getBytes()));
              int result=  attachmentService.postData(attachment);
              attachment.setId(result);
              if (result>0){
                 return Response.<Attachment>createSuccess().setPayload(attachment).setMessage("One Row Add Successfully");
              }else {
               return    Response.<Attachment>badRequest().setSuccess(false).setMessage("Your insert is error");
              }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
               return Response.<Attachment>exception().setSuccess(false).setMessage("Error Exception");
            }
    }

    @GetMapping("/images")
    Response<List<Attachment>> getAllImage(){
    try {
        List<Attachment> attachmentList = attachmentService.getAllImage();
        return Response.<List<Attachment>>ok().setPayload(attachmentList).setMessage("All your data.");
    }catch (Exception e){
        System.out.println("this is your error :"+e);
        return Response.<List<Attachment>>exception().setMessage("Error Exception ");
    }

    }
}
