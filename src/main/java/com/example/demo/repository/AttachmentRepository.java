package com.example.demo.repository;

import com.example.demo.model.Attachment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AttachmentRepository {

    @Select("INSERT  INTO attachment_tb(filename,filetype,data) VALUES (#{attachment.ImageURL},#{attachment.fileType},#{attachment.data}) returning id")
    int postImage(@Param("attachment")Attachment attachment);


    @Result(property = "ImageURL",column = "filename")
    @Select("SELECT * FROM attachment_tb")
    List<Attachment> getAllImage();

}
