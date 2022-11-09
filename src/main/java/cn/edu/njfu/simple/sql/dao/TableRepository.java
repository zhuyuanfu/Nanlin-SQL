package cn.edu.njfu.simple.sql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cn.edu.njfu.simple.sql.metadata.model.Table;

public interface TableRepository extends CrudRepository<Table, Long>{
    @Query("select t from table t where t.isDeleted = false")
    List<Table> listUndeletedTables();
    
    @Modifying
    @Query("update table t set t.isDeleted = true where t.id = :id")
    Boolean falseDeleteById(@Param("id") Long id);
}
