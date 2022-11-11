package cn.edu.njfu.simple.sql.metadata.miner;

import java.util.List;

import cn.edu.njfu.simple.sql.metadata.model.MetaDatabase;
import cn.edu.njfu.simple.sql.metadata.model.Field;
import cn.edu.njfu.simple.sql.metadata.model.Table;

public interface MetadataMiner {
    
    List<MetaDatabase> mineDatabases();
    
    List<Table> mineTables(MetaDatabase database);
    
    List<Field> mineFields(MetaDatabase database, Table table);
}
