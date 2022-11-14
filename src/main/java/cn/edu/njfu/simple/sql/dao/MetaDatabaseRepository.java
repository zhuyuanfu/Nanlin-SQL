package cn.edu.njfu.simple.sql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.njfu.simple.sql.metadata.model.MetaDatabase;

public interface MetaDatabaseRepository extends CrudRepository<MetaDatabase, Long>{
    @Query("select t from meta_database t where t.isDeleted = false")
    List<MetaDatabase> listUndeletedDatabases();
    
    @Query("select t from meta_database t where t.isDeleted = false and t.datasourceId = :id")
    List<MetaDatabase> listUndeledDatabasesByDatasourceId(@Param("id") Long id);
    
    @Transactional
    @Modifying
    @Query("update meta_database t set t.isDeleted = true where t.id = :id")
    Integer falseDeleteById(@Param("id") Long id);
    
    @Transactional
    @Modifying
    @Query("update meta_database t set t.isDeleted = false where t.id = :id")
    Integer restoreById(@Param("id") Long id);
    
    @Override
    List<MetaDatabase> findAll();
}
