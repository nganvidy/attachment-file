package com.example.demo.service;

import com.example.demo.model.Attachment;
import com.example.demo.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentServiceImp implements AttachmentService{

    @Autowired
    AttachmentRepository attachmentRepository;
    @Override
    public int postData(Attachment attachment) {
        return attachmentRepository.postImage(attachment);
    }

    @Override
    public List<Attachment> getAllImage() {
        return attachmentRepository.getAllImage();
    }
}
