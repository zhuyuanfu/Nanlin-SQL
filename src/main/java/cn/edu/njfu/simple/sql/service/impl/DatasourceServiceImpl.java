package cn.edu.njfu.simple.sql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.njfu.simple.sql.dao.MetaDatasourceRepository;
import cn.edu.njfu.simple.sql.metadata.model.MetaDatasource;
import cn.edu.njfu.simple.sql.service.DatasourceService;

@Service
public class DatasourceServiceImpl implements DatasourceService {

    @Autowired
    private MetaDatasourceRepository datasourceRepository;
    
    @Override
    public List<MetaDatasource> listUndeletedDatasources() {
        return datasourceRepository.listUndeletedDatasources();
    }

    @Override
    public Boolean falseDeleteById(Long id) {
        return datasourceRepository.falseDeleteById(id);
    }
    
}
