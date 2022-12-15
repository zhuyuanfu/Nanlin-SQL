package cn.edu.njfu.simple.sql.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.njfu.simple.sql.dao.MetaDatabaseRepository;
import cn.edu.njfu.simple.sql.dao.MetaDatasourceRepository;
import cn.edu.njfu.simple.sql.dao.MetaFieldRepository;
import cn.edu.njfu.simple.sql.dao.MetaTableRepository;
import cn.edu.njfu.simple.sql.metadata.miner.MetadataMiner;
import cn.edu.njfu.simple.sql.metadata.miner.MetadataMinerFactory;
import cn.edu.njfu.simple.sql.metadata.model.MetaDatabase;
import cn.edu.njfu.simple.sql.metadata.model.MetaDatasource;
import cn.edu.njfu.simple.sql.metadata.model.MetaField;
import cn.edu.njfu.simple.sql.metadata.model.MetaTable;
import cn.edu.njfu.simple.sql.model.CustomResponse;
import cn.edu.njfu.simple.sql.service.MetadataMiningService;

@Service
public class MetadataMiningServiceImpl implements MetadataMiningService {

    @Autowired
    private MetaDatasourceRepository datasourceRepository;
    
    @Autowired
    private MetaDatabaseRepository databaseRepository;
    
    @Autowired
    private MetaTableRepository tableRepository;
    
    @Autowired
    private MetaFieldRepository fieldRepository;
    
    @Override
    public void mineAndSaveDatabases(MetaDatasource datasource) {
        MetadataMiner miner = MetadataMinerFactory.getMetadatMiner(datasource);
        List<MetaDatabase> minedDatabases =  miner.mineDatabases();
        List<MetaDatabase> allDatabases = databaseRepository.findAll();
        
        // 将从数据库里捞出来的metaDB分成“已被逻辑删除的”和“没被删除的”两类
        List<MetaDatabase> existingDatabases = new ArrayList<MetaDatabase>();
        List<MetaDatabase> falseDeletedDatabases = new ArrayList<MetaDatabase>();
        for (MetaDatabase database: allDatabases) {
            if (database.getIsDeleted()) {
                falseDeletedDatabases.add(database);
            } else {
                existingDatabases.add(database);
            }
        }
        
        List<MetaDatabase> toBeInsertedDatabases = new ArrayList<MetaDatabase>();
        List<MetaDatabase> toBeFalseDeletedDatabases = new ArrayList<MetaDatabase>();
        List<MetaDatabase> toBeRestoredDatabases = new ArrayList<MetaDatabase>();
        
        // 检查需要插入的数据库
        // 对于每一个被挖掘出来的db，如果表里不存在它，就应当将它插入表
        for (MetaDatabase minedDatabase: minedDatabases) {
            if (!metaDatabaseListContains(allDatabases, minedDatabase)) {
                toBeInsertedDatabases.add(minedDatabase);
            }
        }
        
        // 检查需要假删除的数据库
        // 对于表中每一个【已存在的且未删除的db】，如果没挖到它，说明它被别人删了，它就应当被逻辑删除
        for (MetaDatabase existingDatabase: existingDatabases) {
            if (!metaDatabaseListContains(minedDatabases, existingDatabase)) {
                toBeFalseDeletedDatabases.add(existingDatabase);
            }
        }
        
        // 检查需要从假删除状态恢复的数据库
        // 对于每一个已经被逻辑删除的db，如果它被挖出来了，它就应当被恢复（删除位置否）
        for (MetaDatabase falseDeletedDatabase: falseDeletedDatabases) {
            if (metaDatabaseListContains(minedDatabases, falseDeletedDatabase)) {
                toBeRestoredDatabases.add(falseDeletedDatabase);
            }
        }
        
        for (MetaDatabase db: toBeInsertedDatabases) {
            databaseRepository.save(db);
        }
        for (MetaDatabase db: toBeFalseDeletedDatabases) {
            databaseRepository.falseDeleteById(db.getId());
        }
        for (MetaDatabase db: toBeRestoredDatabases) {
            databaseRepository.restoreById(db.getId());
        }
    }

    @Override
    public void mineAndSaveTables(MetaDatasource datasource, MetaDatabase database) {
        MetadataMiner miner = MetadataMinerFactory.getMetadatMiner(datasource);
        List<MetaTable> minedTables = miner.mineTables(database);
        List<MetaTable> allTables = tableRepository.listAllTablesByDatabaseId(database.getId());
        
        List<MetaTable> existingTables = new ArrayList<MetaTable>();
        List<MetaTable> falseDeletedTables = new ArrayList<MetaTable>();
        for (MetaTable t: allTables) {
            if (t.getIsDeleted()) {
                falseDeletedTables.add(t);
            } else {
                existingTables.add(t);
            }
        }
        
        List<MetaTable> toBeInsertedTables = new ArrayList<MetaTable>();
        List<MetaTable> toBeFalseDeletedTables = new ArrayList<MetaTable>();
        List<MetaTable> toBeRestoredTables = new ArrayList<MetaTable>();
        
        // 对于每一张被挖掘出来的表，如果数据库里没有记录它，就应该将它插入数据库
        for (MetaTable minedTable: minedTables) {
            if (!metaTableListContains(allTables, minedTable)) {
                toBeInsertedTables.add(minedTable);
            }
        }
        
        // 对于数据库中每一个未被删除的MetaTable，如果没被挖掘出来，那么说明它被别人删除了，应该将它设为已删除；
        for (MetaTable existingTable: existingTables) {
            if (!metaTableListContains(minedTables, existingTable)) {
                toBeFalseDeletedTables.add(existingTable);
            }
        }
        
        // 对于每一个已经被逻辑删除的db，如果它被挖出来了，就应当被恢复
        for (MetaTable falseDeletedTable: falseDeletedTables) {
            if (metaTableListContains(minedTables, falseDeletedTable)) {
                toBeRestoredTables.add(falseDeletedTable);
            }
        }
        
        for (MetaTable t: toBeInsertedTables) {
            tableRepository.save(t);
        }
        for (MetaTable t: toBeFalseDeletedTables) {
            tableRepository.falseDeleteById(t.getId());
        }
        for (MetaTable t: toBeRestoredTables) {
            //tableRepository.restoreById(t.getId());
            t.setIsDeleted(false);
            t.setUpdatedTime(LocalDateTime.now());
            tableRepository.save(t);
        }
    }

    @Override
    public void mineAndSaveFields(MetaDatasource datasource, MetaDatabase database, MetaTable table) {
        MetadataMiner miner = MetadataMinerFactory.getMetadatMiner(datasource);
        List<MetaField> minedFields = miner.mineFields(database, table);
        List<MetaField> allFields = fieldRepository.listAllFieldsByTableId(table.getId());
        
        List<MetaField> existingFields = new ArrayList<MetaField>();
        List<MetaField> falseDeletedFields = new ArrayList<MetaField>();
        for (MetaField f: allFields) {
            if (f.getIsDeleted()) {
                falseDeletedFields.add(f);
            } else {
                existingFields.add(f);
            }
        }
        
        List<MetaField> toBeInsertedFields = new ArrayList<MetaField>();
        List<MetaField> toBeFalseDeletedFields = new ArrayList<MetaField>();
        List<MetaField> toBeRestoredFields = new ArrayList<MetaField>();
        
        // 对于每一个被挖掘出来的Field，如果数据库里没有记录它，就应该将它插入数据库
        for (MetaField minedField: minedFields) {
            if (!metaFieldListContains(allFields, minedField)) {
                toBeInsertedFields.add(minedField);
            }
        }
        
        // 对于数据库中每一个未被删除的MetaField，如果没被挖掘出来，那么说明它被别人删除了，应该将它设为已删除；
        for (MetaField existingField: existingFields) {
            if (!metaFieldListContains(minedFields, existingField)) {
                toBeFalseDeletedFields.add(existingField);
            }
        }
        
        // 对于每一个已经被逻辑删除的MetaField，如果它被挖出来了，就应当被恢复
        for (MetaField falseDeletedField: falseDeletedFields) {
            if (metaFieldListContains(minedFields, falseDeletedField)) {
                toBeRestoredFields.add(falseDeletedField);
            }
        }
        
        for (MetaField f: toBeInsertedFields) {
            fieldRepository.save(f);
        }
        for (MetaField f: toBeFalseDeletedFields) {
            fieldRepository.falseDeleteById(f.getId());
        }
        for (MetaField f: toBeRestoredFields) {
            fieldRepository.restoreById(f.getId());
        }
        
    }

    // TODO 补完
    @Override
    public CustomResponse<String> mineAndSaveCompletely() {
        List<MetaDatasource> datasourceList = datasourceRepository.listUndeletedDatasources();
        for (MetaDatasource datasource: datasourceList) {
            this.mineAndSaveDatabases(datasource);
            
            List<MetaDatabase> databaseList = databaseRepository.listUndeledDatabasesByDatasourceId(datasource.getId());
            for (MetaDatabase database: databaseList) {
                this.mineAndSaveTables(datasource, database);
                
                List<MetaTable> tableList = tableRepository.listUndeletedTablesByDatabaseId(database.getId());
                for (MetaTable table: tableList) {
                    this.mineAndSaveFields(datasource, database, table);
                }
            }
        }
        
        return new CustomResponse<String>(0, "Mining metadata succeeded.");
    }
    
    private boolean metaDatabaseListContains(List<MetaDatabase> databases, MetaDatabase database) {
        long targetDatasourceId = database.getDatasourceId();
        String targetDatabaseName = database.getName();
        for (MetaDatabase dbListElement: databases) {
            if (dbListElement.getDatasourceId() == targetDatasourceId && dbListElement.getName().equals(targetDatabaseName)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean metaTableListContains(List<MetaTable> tableList, MetaTable table) {
        Long targetDatabaseId = table.getDatabaseId();
        String targetTableName = table.getName();
        for (MetaTable t: tableList) {
            if (t.getDatabaseId() == targetDatabaseId && t.getName().equals(targetTableName)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean metaFieldListContains(List<MetaField> fieldList, MetaField field) {
        Long targetTableId = field.getTableId();
        String targetFieldName = field.getName();
        for (MetaField f: fieldList) {
            if (f.getTableId() == targetTableId && f.getName().equals(targetFieldName)) {
                return true;
            }
        }
        return false;
    }
}
