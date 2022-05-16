package com.service.impl;

import com.config.WebConfiguration;
import com.entity.ImageEntity;
import com.entity.model.ImageModel;
import com.repository.FileRepository;
import com.service.IFileService;
import com.utils.FileUtilsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements IFileService {
    private final FileRepository imageRepository;

    public FileServiceImpl(FileRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<ImageEntity> findAll() {
        return null;
    }

    ImageEntity toEntity(String fileName, MultipartFile file) {
        return ImageEntity.builder()
                .fileGuid(fileName)
                .fileUrl(WebConfiguration.HOST + fileName)
                .fileType(file.getContentType())
                .build();
    }


    @Override
    public Page<ImageEntity> findAll(Pageable page) {
        return this.imageRepository.findAll(page);
    }

    @Override
    public ImageEntity findById(Long id) {
        return this.imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
    }

    @Override
    public ImageEntity add(ImageModel model) {
        try {
            String imageFile = FileUtilsService.uploadFile(model.getFile());
            ImageEntity imageEntity = toEntity(imageFile, model.getFile());
            return this.imageRepository.save(imageEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Image add failed");
    }

    @Override
    public List<ImageEntity> add(List<ImageModel> model) {
        return null;
    }

    @Override
    public ImageEntity update(ImageModel model) {
        try {
            ImageEntity original = findById(model.getId());
            if (!FileUtilsService.deleteFile(original.getFileGuid()))
                throw new RuntimeException("Original File not found");
            else this.imageRepository.delete(original);

            String imageFile = FileUtilsService.uploadFile(model.getFile());
            ImageEntity imageEntity = toEntity(imageFile, model.getFile());
            return this.imageRepository.save(imageEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Image add failed");
    }

    @Override
    public boolean deleteById(Long id) {
        ImageEntity original = findById(id);
        FileUtilsService.deleteFile(original.getFileGuid());
        this.imageRepository.delete(original);
        return true;
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        ids.forEach(id -> deleteById(id));
        return true;
    }
}
