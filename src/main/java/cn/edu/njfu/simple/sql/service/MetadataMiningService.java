package cn.edu.njfu.simple.sql.service;

import cn.edu.njfu.simple.sql.metadata.model.MetaDatabase;
import cn.edu.njfu.simple.sql.metadata.model.MetaDatasource;
import cn.edu.njfu.simple.sql.metadata.model.MetaTable;
import cn.edu.njfu.simple.sql.model.CustomResponse;

public interface MetadataMiningService {

    CustomResponse<String> mineAndSaveCompletely();
    
    void mineAndSaveDatabases(MetaDatasource datasource);
    
    void mineAndSaveTables(MetaDatasource datasource, MetaDatabase database);
    
    void mineAndSaveFields(MetaDatasource datasource, MetaDatabase database, MetaTable table);
}
