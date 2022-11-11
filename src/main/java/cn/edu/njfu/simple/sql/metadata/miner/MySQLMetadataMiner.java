package cn.edu.njfu.simple.sql.metadata.miner;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.njfu.simple.sql.metadata.model.MetaDatabase;
import cn.edu.njfu.simple.sql.metadata.model.MetaDatasource;
import cn.edu.njfu.simple.sql.metadata.model.DatasourceType;
import cn.edu.njfu.simple.sql.metadata.model.Field;
import cn.edu.njfu.simple.sql.metadata.model.Table;

public class MySQLMetadataMiner implements MetadataMiner {

    private static Logger logger = LoggerFactory.getLogger(MySQLMetadataMiner.class);
    
    private MetaDatasource datasource;
    private Connection conn;
    private DatabaseMetaData metadata;
    
    public MySQLMetadataMiner() {}
    
    public MySQLMetadataMiner(MetaDatasource datasource) {
        this.datasource = datasource;
        openConnection();
    }
    
    @Override
    public List<MetaDatabase> mineDatabases() {
        List<MetaDatabase> result = new ArrayList<MetaDatabase>(); 
        try {
            if (conn != null && !conn.isClosed()) {
                openConnection();
            }
            ResultSet rs = metadata.getCatalogs();
            LocalDateTime now = LocalDateTime.now();
            while (rs.next()) {
                String databaseName = rs.getString("TABLE_CAT");
                MetaDatabase database = new MetaDatabase();
                database.setDataSourceId(datasource.getId());
                database.setName(databaseName);
                database.setIsDeleted(false);
                database.setCreatedTime(now);
                database.setUpdatedTime(now);
                result.add(database);
            }
        } catch (SQLException e) {
            logger.error("Opening connection to database failed. ", e);
        }
        return result;
    }

    @Override
    public List<Table> mineTables(MetaDatabase database) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Field> mineFields(MetaDatabase database, Table table) {
        // TODO Auto-generated method stub
        return null;
    }
    
    private void openConnection() {
        String url = generateConnectionBaseUrl();
        try {
            Class.forName(datasource.getDriverClass());
            this.conn = DriverManager.getConnection(url, datasource.getConnectionAccount(), datasource.getConnectionPassword());
            this.metadata = conn.getMetaData();
        } catch (ClassNotFoundException e) {
            logger.error("DID NOT find driver class: " + datasource.getDriverClass(), e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void closeConnection() {
        try {
            if (conn != null && (!conn.isClosed())) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.error("Closing metadata db connection error.", e);
        }
    }
    
    private String generateConnectionBaseUrl() {
        if (datasource.getDatasourceType() == DatasourceType.MYSQL) {
            return "jdbc:mysql://" + datasource.getHost() + ":" + datasource.getPort() + "/";
        } else if (datasource.getDatasourceType() == DatasourceType.ORACLE) {
            return "jdbc:oracle:thin:@" + datasource.getHost() + ":" + datasource.getPort() + ":";
        } else {
            return "";
        }
    }

    @Override
    public void finalize() {
        closeConnection();
    }
}
