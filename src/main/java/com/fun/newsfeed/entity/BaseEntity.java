package com.fun.newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    private Date creationTime;
    private Date updationTime;

    @PrePersist
    protected void onCreate() {
        creationTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updationTime = new Date();
    }
}
