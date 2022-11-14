package cn.edu.njfu.simple.sql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cn.edu.njfu.simple.sql.metadata.model.MetaDatasource;

public interface MetaDatasourceRepository extends CrudRepository<MetaDatasource, Long>{
	
    @Query("select t from meta_datasource t where t.isDeleted = false")
    List<MetaDatasource> listUndeletedDatasources();
    
    @Modifying
	@Query("update meta_datasource t set t.isDeleted = true where t.id = :id")
	Boolean falseDeleteById(@Param("id") Long id);
}
