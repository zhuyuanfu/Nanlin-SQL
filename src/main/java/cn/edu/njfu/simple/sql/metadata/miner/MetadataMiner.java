package cn.edu.njfu.simple.sql.metadata.miner;

import java.util.List;

import cn.edu.njfu.simple.sql.metadata.model.MetaDatabase;
import cn.edu.njfu.simple.sql.metadata.model.MetaField;
import cn.edu.njfu.simple.sql.metadata.model.MetaTable;
import cn.edu.njfu.simple.sql.model.CustomResponse;

public interface MetadataMiner {
        
    List<MetaDatabase> mineDatabases();
    
    List<MetaTable> mineTables(MetaDatabase database);
    
    List<MetaField> mineFields(MetaDatabase database, MetaTable table);
}
