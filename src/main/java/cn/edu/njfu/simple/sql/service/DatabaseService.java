package cn.edu.njfu.simple.sql.service;

import java.util.List;

import cn.edu.njfu.simple.sql.metadata.model.Database;

public interface DatabaseService {

    List<Database> listUndeletedDatabases();
    
    Boolean falseDeleteById(Long id);
}
