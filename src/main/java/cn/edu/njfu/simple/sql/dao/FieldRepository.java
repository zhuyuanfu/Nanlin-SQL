package cn.edu.njfu.simple.sql.dao;

import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.njfu.simple.sql.metadata.model.MetaField;

public interface FieldRepository extends CrudRepository<MetaField, Long>{
    @Query("select t from meta_field t where t.isDeleted = false")
    List<MetaField> listUndeletedFields();
    
    @Transactional
    @Modifying
    @Query("update meta_field t set t.isDeleted = true where t.id = :id")
    Integer falseDeleteById(@Param("id") Long id);
}
