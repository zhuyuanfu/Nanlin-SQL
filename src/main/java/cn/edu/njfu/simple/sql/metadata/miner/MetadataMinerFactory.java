package cn.edu.njfu.simple.sql.metadata.miner;

import cn.edu.njfu.simple.sql.metadata.model.MetaDatasource;
import cn.edu.njfu.simple.sql.metadata.model.DatasourceType;

public class MetadataMinerFactory {

    public static MetadataMiner getMetadatMiner(MetaDatasource datasource) {
        if (datasource == null) throw new NullPointerException("argument datasource is null");
        
        DatasourceType datasourceType = datasource.getDatasourceType();
        
        switch (datasourceType) {
            case MYSQL:
                return new MySQLMetadataMiner(datasource);
            case ORACLE:
                break;
            default:
                break;
        }
        return null;
    }
}
