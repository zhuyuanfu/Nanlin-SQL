package cn.edu.njfu.simple.sql.service;

import java.util.List;

import cn.edu.njfu.simple.sql.metadata.model.MetaTable;

public interface TableService {

    List<MetaTable> listUndeletedTables();
    
    Integer falseDeleteById(Long id);
}
