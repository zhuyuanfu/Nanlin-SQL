package cn.edu.njfu.simple.sql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.njfu.simple.sql.dao.DatabaseRepository;
import cn.edu.njfu.simple.sql.dao.DatasourceRepository;
import cn.edu.njfu.simple.sql.metadata.model.Database;
import cn.edu.njfu.simple.sql.metadata.model.Datasource;
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
    public List<Database> mineDatabases(Datasource datasource) {
        return null;
    }

    @Override
    public List<Table> mineTables(Datasource datasource, Database database) {
        return null;
    }

    @Override
    public List<Field> mineFields(Datasource datasource, Database database, Table table) {
        return null;
    }

    @Override
    public CustomResponse<String> mineCompletely() {
        
        List<Datasource> datasourceList = datasourceRepository.listUndeletedDatasources();
        
        for (Datasource datasource: datasourceList) {
            List<Database> minedDatabases = mineDatabases(datasource);
            List<Database> existingDatabases = databaseRepository.listUndeletedDatabases();
            
            
        }
        
        return null;
    }

}
