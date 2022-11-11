package cn.edu.njfu.simple.sql.service;

import java.util.List;

import cn.edu.njfu.simple.sql.metadata.model.MetaDatabase;

public interface DatabaseService {

    List<MetaDatabase> listUndeletedDatabases();
    
    Boolean falseDeleteById(Long id);
}
