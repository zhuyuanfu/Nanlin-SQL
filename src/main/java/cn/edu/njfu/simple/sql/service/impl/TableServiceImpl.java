package cn.edu.njfu.simple.sql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.njfu.simple.sql.dao.MetaTableRepository;
import cn.edu.njfu.simple.sql.metadata.model.MetaTable;
import cn.edu.njfu.simple.sql.service.TableService;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private MetaTableRepository tableRepository;
    
    @Override
    public List<MetaTable> listUndeletedTables() {
        return tableRepository.listUndeletedTables();
    }


    @Override
    public Integer falseDeleteById(Long id) {
        return tableRepository.falseDeleteById(id);
    }
    
}
