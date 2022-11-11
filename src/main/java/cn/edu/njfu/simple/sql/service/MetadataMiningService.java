package cn.edu.njfu.simple.sql.service;

import java.util.List;

import cn.edu.njfu.simple.sql.metadata.model.MetaDatabase;
import cn.edu.njfu.simple.sql.metadata.model.MetaDatasource;
import cn.edu.njfu.simple.sql.metadata.model.Field;
import cn.edu.njfu.simple.sql.metadata.model.Table;
import cn.edu.njfu.simple.sql.model.CustomResponse;

public interface MetadataMiningService {

    CustomResponse<String> mineCompletely();
    
    void mineDatabases(MetaDatasource datasource);
    
    void mineTables(MetaDatasource datasource, MetaDatabase database);
    
    void mineFields(MetaDatasource datasource, MetaDatabase database, Table table);
}
