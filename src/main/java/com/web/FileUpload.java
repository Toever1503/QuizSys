package com.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("api/images")
public class FileUpload {

    @PostMapping
    public Object upload(MultipartFile[] files) {

        return null;
    }
}
