package cn.edu.njfu.simple.sql.service;

import java.util.List;

import cn.edu.njfu.simple.sql.model.metadata.Database;
import cn.edu.njfu.simple.sql.model.metadata.Datasource;
import cn.edu.njfu.simple.sql.model.metadata.Field;
import cn.edu.njfu.simple.sql.model.metadata.Table;

public interface MetadataMiningService {

    List<Database> mineDatabases(Datasource datasource);
    
    List<Table> mineTables(Datasource datasource, Database database);
    
    List<Field> mineFields(Datasource datasource, Database database, Table table);
}
