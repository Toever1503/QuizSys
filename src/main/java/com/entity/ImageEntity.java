package com.entity;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class ImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_guid", unique = true)
    private String imageGuid;

    @Column(name = "image_url")
    private String imageUrl;
}
