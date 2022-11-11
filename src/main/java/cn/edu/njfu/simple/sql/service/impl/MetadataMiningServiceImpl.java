package cn.edu.njfu.simple.sql.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.njfu.simple.sql.dao.DatabaseRepository;
import cn.edu.njfu.simple.sql.dao.DatasourceRepository;
import cn.edu.njfu.simple.sql.metadata.miner.MetadataMiner;
import cn.edu.njfu.simple.sql.metadata.miner.MetadataMinerFactory;
import cn.edu.njfu.simple.sql.metadata.model.MetaDatabase;
import cn.edu.njfu.simple.sql.metadata.model.MetaDatasource;
import cn.edu.njfu.simple.sql.metadata.model.Field;
import cn.edu.njfu.simple.sql.metadata.model.Table;
import cn.edu.njfu.simple.sql.model.CustomResponse;
import cn.edu.njfu.simple.sql.service.MetadataMiningService;

@Service
public class MetadataMiningServiceImpl implements MetadataMiningService {

    @Autowired
    private DatasourceRepository datasourceRepository;
    
    @Autowired
    private DatabaseRepository databaseRepository;
    
    @Override
    public void mineDatabases(MetaDatasource datasource) {
        MetadataMiner miner = MetadataMinerFactory.getMetadatMiner(datasource);
        List<MetaDatabase> minedDatabases =  miner.mineDatabases();
        List<MetaDatabase> allDatabases = databaseRepository.findAll();
        
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
            if (!databaseListContains(allDatabases, minedDatabase)) {
                toBeInsertedDatabases.add(minedDatabase);
            }
        }
        
        // 检查需要假删除的数据库
        // 对于表中每一个【已存在的且未删除的db】，如果没挖到它，说明它被别人删了，它就应当被逻辑删除
        for (MetaDatabase existingDatabase: existingDatabases) {
            if (!databaseListContains(minedDatabases, existingDatabase)) {
                toBeFalseDeletedDatabases.add(existingDatabase);
            }
        }
        
        // 检查需要从假删除状态恢复的数据库
        // 对于每一个已经被逻辑删除的db，如果它被挖出来了，它就应当被恢复（删除位置否）
        for (MetaDatabase falseDeletedDatabase: falseDeletedDatabases) {
            if (databaseListContains(minedDatabases, falseDeletedDatabase)) {
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
    public void mineTables(MetaDatasource datasource, MetaDatabase database) {
        
    }

    @Override
    public void mineFields(MetaDatasource datasource, MetaDatabase database, Table table) {
        
    }

    // TODO 补完
    @Override
    public CustomResponse<String> mineCompletely() {
        List<MetaDatasource> datasourceList = datasourceRepository.listUndeletedDatasources();
        for (MetaDatasource datasource: datasourceList) {
            this.mineDatabases(datasource);
        }
        return new CustomResponse<String>(0, "Mining metadata succeeded.");
    }
    
    private boolean databaseListContains(List<MetaDatabase> databases, MetaDatabase database) {
        long targetDatasourceId = database.getDataSourceId();
        String targetDatabaseName = database.getName();
        for (MetaDatabase dbListElement: databases) {
            if (dbListElement.getDataSourceId() == targetDatasourceId && dbListElement.getName().equals(targetDatabaseName)) {
                return true;
            }
        }
        return false;
    }

}
