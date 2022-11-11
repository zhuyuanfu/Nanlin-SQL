package cn.edu.njfu.simple.sql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.njfu.simple.sql.dao.DatabaseRepository;
import cn.edu.njfu.simple.sql.metadata.model.MetaDatabase;
import cn.edu.njfu.simple.sql.service.DatabaseService;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    private DatabaseRepository databaseRepository;
    
    @Override
    public List<MetaDatabase> listUndeletedDatabases() {
        return databaseRepository.listUndeletedDatabases();
    }


    @Override
    public Boolean falseDeleteById(Long id) {
        return databaseRepository.falseDeleteById(id);
    }
    
}
