package cn.edu.njfu.simple.sql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cn.edu.njfu.simple.sql.model.metadata.Datasource;

public interface DatasourceRepository extends CrudRepository<Datasource, Long>{
	
    @Query("select t from datasource t where t.isDeleted = false")
    List<Datasource> listUndeletedDatasources();
    
    @Modifying
	@Query("update datasource t set t.isDeleted = true where t.id = :id")
	Boolean falseDeleteById(@Param("id") Long id);
}
