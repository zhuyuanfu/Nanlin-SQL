package cn.edu.njfu.simple.sql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.njfu.simple.sql.metadata.model.MetaTable;

public interface MetaTableRepository extends CrudRepository<MetaTable, Long>{
    @Query("select t from meta_table t where t.isDeleted = false")
    List<MetaTable> listUndeletedTables();
    
    @Query("select t from meta_table t where t.isDeleted = false and t.databaseId = :id")
    List<MetaTable> listUndeletedTablesByDatabaseId(@Param("id") Long id);

    @Query("select t from meta_table t where t.databaseId = :id")
    List<MetaTable> listAllTablesByDatabaseId(@Param("id") Long id);
    
    @Transactional
    @Modifying
    @Query("update meta_table t set t.isDeleted = true where t.id = :id")
    Integer falseDeleteById(@Param("id") Long id);
    
    @Transactional
    @Modifying
    @Query("update meta_table t set t.isDeleted = false where t.id = :id")
    Integer restoreById(@Param("id") Long id);
}
