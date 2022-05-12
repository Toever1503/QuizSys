package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_guid", unique = true, nullable = false)
    private String fileGuid;

    @Column(name = " file_type")
    private String fileType;

    @Column(name = "fileUrl", nullable = false)
    private String fileUrl;
}
