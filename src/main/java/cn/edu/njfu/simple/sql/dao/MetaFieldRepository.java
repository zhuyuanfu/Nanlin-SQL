package cn.edu.njfu.simple.sql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.njfu.simple.sql.metadata.model.MetaField;

public interface MetaFieldRepository extends CrudRepository<MetaField, Long>{
    @Query("select t from meta_field t where t.isDeleted = false")
    List<MetaField> listUndeletedFields();
    
    @Query("select t from meta_field t where t.isDeleted = false and t.tableId = :id")
    List<MetaField> listUndeletedFieldsByTableId(@Param("id")Long id);
    
    @Transactional
    @Modifying
    @Query("update meta_field t set t.isDeleted = true where t.id = :id")
    Integer falseDeleteById(@Param("id") Long id);
    
    @Transactional
    @Modifying
    @Query("update meta_field t set t.isDeleted = false where t.id = :id")
    Integer restoreById(@Param("id") Long id);
}
