package cn.edu.njfu.simple.sql.metadata.miner;

import java.util.List;

import cn.edu.njfu.simple.sql.metadata.model.Database;
import cn.edu.njfu.simple.sql.metadata.model.Datasource;
import cn.edu.njfu.simple.sql.metadata.model.Field;
import cn.edu.njfu.simple.sql.metadata.model.Table;

public interface MetadataMiner {

    List<Database> mineDatabases(Datasource datasource);
    
    List<Table> mineTables(Datasource datasource, Database database);
    
    List<Field> mineFields(Datasource datasource, Database database, Table table);
}
