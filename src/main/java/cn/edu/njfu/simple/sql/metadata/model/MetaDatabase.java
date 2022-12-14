package cn.edu.njfu.simple.sql.metadata.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "meta_database")
public class MetaDatabase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long datasourceId;
    private String name;
    private String chineseName;
    private String comment;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Boolean isDeleted;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDatasourceId() {
        return datasourceId;
    }
    public void setDatasourceId(Long dataSourceId) {
        this.datasourceId = dataSourceId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getChineseName() {
        return chineseName;
    }
    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
