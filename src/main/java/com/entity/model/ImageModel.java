package com.entity.model;

import com.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageModel extends BaseEntity {
    private Long id;
    private MultipartFile file;

}
