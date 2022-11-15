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
import cn.edu.njfu.simple.sql.metadata.model.DataType;
import cn.edu.njfu.simple.sql.metadata.model.DatasourceType;
import cn.edu.njfu.simple.sql.metadata.model.MetaField;
import cn.edu.njfu.simple.sql.metadata.model.MetaTable;
import cn.edu.njfu.simple.sql.model.CustomResponse;

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
            if (conn == null || conn.isClosed()) {
                openConnection();
            }
            ResultSet rs = metadata.getCatalogs();
            LocalDateTime now = LocalDateTime.now();
            while (rs.next()) {
                MetaDatabase database = new MetaDatabase();
                database.setDatasourceId(datasource.getId());
                database.setName(rs.getString("TABLE_CAT"));
                database.setIsDeleted(false);
                database.setCreatedTime(now);
                database.setUpdatedTime(now);
                result.add(database);
            }
        } catch (SQLException e) {
            logger.error("Opening connection to database failed. ", e);
        } finally {
            closeConnection();
        }
        return result;
    }

    
    // mysql的话，挖不出performance_schema，不知什么原因
    @Override
    public List<MetaTable> mineTables(MetaDatabase database) {
        List<MetaTable> result = new ArrayList<MetaTable>();
        try {
            if (conn == null || conn.isClosed()) {
                openConnection();
            }
            ResultSet rs = metadata.getTables(database.getName(), null, "%", new String[] {"TABLE"});
            LocalDateTime now = LocalDateTime.now();
            while (rs.next()) {
                MetaTable table = new MetaTable();
                table.setDatabaseId(database.getId());
                table.setName(rs.getString("TABLE_NAME"));
                table.setIsDeleted(false);
                table.setCreatedTime(now);
                table.setUpdatedTime(now);
                result.add(table);
            }
        } catch (SQLException e) {
            logger.error("Opening connection to database failed. ", e);
        } finally {
            closeConnection();
        }
        return result;
    }

    @Override
    public List<MetaField> mineFields(MetaDatabase database, MetaTable table) {
        List<MetaField> result = new ArrayList<MetaField>();
        try {
            if (conn == null || conn.isClosed()) {
                openConnection();
            }
            ResultSet rs = metadata.getColumns(database.getName(), null, table.getName(), null);
            LocalDateTime now = LocalDateTime.now();
            while (rs.next()) {
                MetaField field = new MetaField();
                field.setTableId(table.getId());
                field.setName(rs.getString("COLUMN_NAME"));
                DataType type = null;
                try {
                    type = DataType.valueOf(rs.getString("COLUMN_TYPE"));
                } catch (Exception e) {
                    type = DataType.UNKNOWN;
                }
                field.setDataType(type);
                field.setIsDeleted(false);
                field.setCreatedTime(now);
                field.setUpdatedTime(now);
                result.add(field);
            }
        } catch (SQLException e) {
            logger.error("Opening connection to database failed. ", e);
        } finally {
            closeConnection();
        }
        return result;
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
