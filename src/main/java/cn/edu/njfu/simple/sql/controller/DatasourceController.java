package cn.edu.njfu.simple.sql.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.njfu.simple.sql.metadata.model.MetaDatasource;
import cn.edu.njfu.simple.sql.metadata.model.DatasourceType;
import cn.edu.njfu.simple.sql.model.CustomResponse;
import cn.edu.njfu.simple.sql.service.DatasourceService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/datasource")
public class DatasourceController {
	
	private static Logger logger = LoggerFactory.getLogger(DatasourceController.class);
	
	@Autowired
	private DatasourceService datasourceService;
    /**
     * 列出支持的数据源类型，左侧目录树会用到这个接口
     * @return 支持的数据源，目前支持oracle和mysql
     */
    @ApiOperation("列出支持的数据源类型，用于左侧树")
    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public CustomResponse<? extends Object> listSupportedDatasources() {
        try {
            List<String> result = new ArrayList<String>();
            DatasourceType[] types = DatasourceType.values();
            for (DatasourceType type: types) {
                result.add(type.name());
            }
            return new CustomResponse<List<String>>(0, result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new CustomResponse<String>(-1, e.getMessage());
        }
    }
    
    
    /**
     * 列出支持的数据源，左侧目录树会用到这个接口
     * @return oracle数据库实例或mysql数据库实例
     */
    @ApiOperation("列出已登记的数据源，用于左侧树")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CustomResponse<? extends Object> listDatasources() {
        return new CustomResponse<List<MetaDatasource>>(0, datasourceService.listUndeletedDatasources());
    }
	
	
}
