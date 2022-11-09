package cn.edu.njfu.simple.sql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cn.edu.njfu.simple.sql.metadata.model.Field;

public interface FieldRepository extends CrudRepository<Field, Long>{
    @Query("select t from field t where t.isDeleted = false")
    List<Field> listUndeletedFields();
    
    @Modifying
    @Query("update field t set t.isDeleted = true where t.id = :id")
    Boolean falseDeleteById(@Param("id") Long id);
}
