package cn.edu.njfu.simple.sql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cn.edu.njfu.simple.sql.metadata.model.Database;

public interface DatabaseRepository extends CrudRepository<Database, Long>{
    @Query("select t from `database` t where t.isDeleted = false")
    List<Database> listUndeletedDatabases();
    
    @Modifying
    @Query("update `database` t set t.isDeleted = true where t.id = :id")
    Boolean falseDeleteById(@Param("id") Long id);
}
