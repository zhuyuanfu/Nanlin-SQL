package simple.sql.dao;

import org.springframework.data.repository.CrudRepository;

import simple.sql.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
