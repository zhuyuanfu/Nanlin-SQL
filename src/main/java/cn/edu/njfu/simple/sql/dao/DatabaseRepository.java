package cn.edu.njfu.simple.sql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cn.edu.njfu.simple.sql.metadata.model.MetaDatabase;

public interface DatabaseRepository extends CrudRepository<MetaDatabase, Long>{
    @Query("select t from meta_database t where t.isDeleted = false")
    List<MetaDatabase> listUndeletedDatabases();
    
    @Modifying
    @Query("update meta_database t set t.isDeleted = true where t.id = :id")
    Boolean falseDeleteById(@Param("id") Long id);
    
    @Modifying
    @Query("update meta_database t set t.isDeleted = false where t.id = :id")
    Boolean restoreById(@Param("id") Long id);
    
    @Override
    List<MetaDatabase> findAll();
}
