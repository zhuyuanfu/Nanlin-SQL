package cn.edu.njfu.simple.sql.service;

import java.util.List;

import cn.edu.njfu.simple.sql.metadata.model.MetaDatasource;

public interface DatasourceService {

    List<MetaDatasource> listUndeletedDatasources();
    
    Boolean falseDeleteById(Long id);
}
