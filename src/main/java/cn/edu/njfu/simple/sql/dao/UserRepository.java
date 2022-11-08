package cn.edu.njfu.simple.sql.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cn.edu.njfu.simple.sql.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	@Query(value="select t from user t where t.id = 1", nativeQuery=false)
	User getProjectAuthor();
	
	@Query("select t from user t where t.id = :id")
	User getUserById(@Param("id") Long id);
}
