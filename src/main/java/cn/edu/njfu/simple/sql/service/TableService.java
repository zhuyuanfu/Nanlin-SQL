package cn.edu.njfu.simple.sql.service;

import java.util.List;

import cn.edu.njfu.simple.sql.metadata.model.Table;

public interface TableService {

    List<Table> listUndeletedTables();
    
    Boolean falseDeleteById(Long id);
}
