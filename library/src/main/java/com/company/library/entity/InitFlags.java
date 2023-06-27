package com.company.library.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@JmixEntity
@Table(name = "INIT_FLAGS")
@Entity
public class InitFlags {

    @Column(name = "ID", nullable = false)
    @Id
    private Integer id;

    @Column(name = "REPORTS_INITIALIZED")
    private Boolean reportsInitialized;

    public Boolean getReportsInitialized() {
        return reportsInitialized;
    }

    public void setReportsInitialized(Boolean reportsInitialized) {
        this.reportsInitialized = reportsInitialized;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}