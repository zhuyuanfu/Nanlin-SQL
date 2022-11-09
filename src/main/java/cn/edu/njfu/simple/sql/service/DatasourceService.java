package cn.edu.njfu.simple.sql.service;

import java.util.List;

import cn.edu.njfu.simple.sql.metadata.model.Datasource;

public interface DatasourceService {

    List<Datasource> listUndeletedDatasources();
    
    Boolean falseDeleteById(Long id);
}
