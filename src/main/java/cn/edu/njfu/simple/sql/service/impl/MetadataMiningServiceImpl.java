package cn.edu.njfu.simple.sql.service.impl;

import java.util.List;

import cn.edu.njfu.simple.sql.model.metadata.Database;
import cn.edu.njfu.simple.sql.model.metadata.Datasource;
import cn.edu.njfu.simple.sql.model.metadata.Field;
import cn.edu.njfu.simple.sql.model.metadata.Table;
import cn.edu.njfu.simple.sql.service.MetadataMiningService;

public class MetadataMiningServiceImpl implements MetadataMiningService {

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

}
