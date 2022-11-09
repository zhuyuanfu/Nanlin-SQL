package cn.edu.njfu.simple.sql.metadata.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Table {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long databaseId;
    private String name;
    private String chineseName;
    private String comment;
    private LocalDateTime created_time;
    private LocalDateTime updated_time;
    private Boolean isDeleted;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDatabaseId() {
        return databaseId;
    }
    public void setDatabaseId(Long databaseId) {
        this.databaseId = databaseId;
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
    public LocalDateTime getCreated_time() {
        return created_time;
    }
    public void setCreated_time(LocalDateTime created_time) {
        this.created_time = created_time;
    }
    public LocalDateTime getUpdated_time() {
        return updated_time;
    }
    public void setUpdated_time(LocalDateTime updated_time) {
        this.updated_time = updated_time;
    }
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
