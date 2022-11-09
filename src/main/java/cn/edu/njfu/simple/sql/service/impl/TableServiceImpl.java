package cn.edu.njfu.simple.sql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.njfu.simple.sql.dao.TableRepository;
import cn.edu.njfu.simple.sql.metadata.model.Table;
import cn.edu.njfu.simple.sql.service.TableService;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;
    
    @Override
    public List<Table> listUndeletedTables() {
        return tableRepository.listUndeletedTables();
    }


    @Override
    public Boolean falseDeleteById(Long id) {
        return tableRepository.falseDeleteById(id);
    }
    
}
