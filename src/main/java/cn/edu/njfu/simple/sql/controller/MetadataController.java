package cn.edu.njfu.simple.sql.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.njfu.simple.sql.metadata.model.CrumbMetaObject;
import cn.edu.njfu.simple.sql.metadata.service.MetadataService;
import cn.edu.njfu.simple.sql.model.CustomResponse;
import cn.edu.njfu.simple.sql.service.MetadataMiningService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/metadata")
public class MetadataController {
	
    private static Logger logger = LoggerFactory.getLogger(MetadataController.class);
	
    @Autowired
    private MetadataMiningService metadataMiningService;
    
    @Autowired
    private MetadataService metadataService;
    
	
    /**
     * 手动采集一次元数据
     * @return 采集元数据的结果
     */
    @ApiOperation("手动发起一次对元数据的全量采集")
    @RequestMapping(value = "/mining/all", method = RequestMethod.POST)
    public CustomResponse<String> mineCompletely() {
        return metadataMiningService.mineAndSaveCompletely();
    }
    
    
    /**
     * 列出元数据的下一级
     * @return 采集元数据的结果
     */
    @ApiOperation("列出元数据的下一级")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CustomResponse<List<CrumbMetaObject>> list(String parentPath) {
        List<CrumbMetaObject> crumbMetaObjectList = metadataService.list(parentPath);
        return new CustomResponse(0, crumbMetaObjectList);
    }
}
