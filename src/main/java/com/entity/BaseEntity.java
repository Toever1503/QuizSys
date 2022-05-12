package com.entity;

import com.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseEntity {
    private String userCreate;
    private String userUpdate;
    private LocalDateTime timeCreate;
    private LocalDateTime timeUpdate;
    @PrePersist
    public void prePersist(){
        String createBy = Util.username;
        this.userCreate =createBy;
        this.timeCreate = LocalDateTime.now();
    }

    @PreUpdate
    public void updatePersist(){
        String createBy = Util.username;
        this.userUpdate =createBy;
        this.timeUpdate = LocalDateTime.now();
    }
}
