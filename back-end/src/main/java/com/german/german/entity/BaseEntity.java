package com.german.german.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {


    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createdDate;


    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date lastModifiedDate;

    public Date getCreatedDate() {
        return createdDate;
    }
    @PrePersist
    public void setCreatedDate() {
        this.createdDate = new Date();
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    @PreUpdate
    public void setLastModifiedDate() {
        this.lastModifiedDate = new Date();
    }
}
