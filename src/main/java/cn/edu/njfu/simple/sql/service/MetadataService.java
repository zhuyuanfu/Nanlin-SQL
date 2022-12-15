package cn.edu.njfu.simple.sql.service;

import java.util.List;

import cn.edu.njfu.simple.sql.metadata.model.CrumbMetaObject;

public interface MetadataService {
    List<CrumbMetaObject> list(String parentPath);
}
