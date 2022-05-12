package com.entity;

import com.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseEntity {
    @Temporal(TemporalType.TIME)
    private Date timeCreate;
    @Temporal(TemporalType.TIME)
    private Date timeUpdate;

    @PrePersist
    public void prePersist() {
        this.timeCreate = Calendar.getInstance().getTime();
        this.timeUpdate = this.timeCreate;
    }

    @PreUpdate
    public void updatePersist() {
        this.timeUpdate = Calendar.getInstance().getTime();
    }
}
