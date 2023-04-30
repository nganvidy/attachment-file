package com.example.demo.service;

import com.example.demo.model.Attachment;

import java.util.List;

public interface AttachmentService {
    int postData(Attachment attachment);

    List<Attachment> getAllImage();
}
