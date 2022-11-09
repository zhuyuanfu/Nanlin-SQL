package cn.edu.njfu.simple.sql.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.njfu.simple.sql.metadata.model.Datasource;
import cn.edu.njfu.simple.sql.metadata.model.DatasourceType;
import cn.edu.njfu.simple.sql.model.CustomResponse;
import cn.edu.njfu.simple.sql.service.DatasourceService;
import cn.edu.njfu.simple.sql.service.MetadataMiningService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/metadata")
public class MetadataMiningController {
	
	private static Logger logger = LoggerFactory.getLogger(MetadataMiningController.class);
	
	@Autowired
	private MetadataMiningService metadataMiningService;
	
    /**
     * 手动采集一次元数据
     * @return 采集元数据的结果
     */
    @ApiOperation("手动发起一次对元数据的全量采集")
    @RequestMapping(value = "/mining", method = RequestMethod.POST)
    public CustomResponse<String> listSupportedDatasources() {
        return metadataMiningService.mineCompletely();
    }
}
