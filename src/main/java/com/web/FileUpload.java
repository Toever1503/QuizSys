package com.web;

import com.entity.ImageEntity;
import com.entity.dto.ResponseDto;
import com.entity.model.ImageModel;
import com.service.IImageService;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/images")
public class FileUpload {

    private final IImageService imageService;

    public FileUpload(IImageService imageService) {
        this.imageService = imageService;
    }
    @Transactional
    @GetMapping
    public Object getImages(Pageable page) {
        return ResponseDto.of(this.imageService.findAll(page), "Get all images");
    }


    @Transactional
    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public Object uploadFile(@RequestPart(name = "file") MultipartFile file) throws IOException {
        if (file.isEmpty())
            throw new RuntimeException("Please select a file to upload");
        return imageService.add(ImageModel.builder().file(file).build());
    }

    @Transactional
    @PostMapping("batch")
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public Object uploadBatch(@RequestPart(name = "files") List<MultipartFile> files) {
        if (files.get(0).isEmpty())
            throw new RuntimeException("Please select a file to upload");
        List<ImageEntity> imageEntities = new ArrayList<ImageEntity>();
        for (MultipartFile file : files) {
            try {
                imageEntities.add(imageService.add(ImageModel.builder().file(file).build()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResponseDto.of(imageEntities, "Upload batch files");
    }
    @Transactional
    @PatchMapping("{id}")
    public Object deleteFile(@PathVariable Long id, @RequestPart(name = "file") MultipartFile file) throws IOException {
        if (file.isEmpty())
            throw new RuntimeException("Please select a file to upload");
        return imageService.update(ImageModel.builder().id(id).file(file).build());
    }
    @Transactional
    @DeleteMapping("{id}")
    public Object deleteFile(@PathVariable Long id) {
        return ResponseDto.of(imageService.deleteById(id), "Delete file");
    }
    @Transactional
    @DeleteMapping("batch/{ids}")
    public Object deleteBatch(@PathVariable List<Long> ids) {
        return ResponseDto.of(imageService.deleteByIds(ids), "Delete batch files");
    }
}
