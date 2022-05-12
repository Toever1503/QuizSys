package com.service.impl;

import com.entity.ImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements IImageService{
    @Override
    public List<ImageEntity> findAll() {
        return null;
    }

    @Override
    public Page<ImageEntity> findAll(Pageable page) {
        return null;
    }

    @Override
    public ImageEntity findById(Long id) {
        return null;
    }

    @Override
    public ImageEntity add(ImageEntity model) {
        return null;
    }

    @Override
    public ImageEntity update(ImageEntity model) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean deleteByIds(List<Long> id) {
        return false;
    }
}
