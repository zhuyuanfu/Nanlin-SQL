package cn.edu.njfu.simple.sql.service;

import java.util.List;

import cn.edu.njfu.simple.sql.metadata.model.Database;
import cn.edu.njfu.simple.sql.metadata.model.Datasource;
import cn.edu.njfu.simple.sql.metadata.model.Field;
import cn.edu.njfu.simple.sql.metadata.model.Table;
import cn.edu.njfu.simple.sql.model.CustomResponse;

public interface MetadataMiningService {

    CustomResponse<String> mineCompletely();
    
    List<Database> mineDatabases(Datasource datasource);
    
    List<Table> mineTables(Datasource datasource, Database database);
    
    List<Field> mineFields(Datasource datasource, Database database, Table table);
}
