package simple.sql.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import simple.sql.model.CustomResponse;
import simple.sql.model.metadata.DatasourceType;

@RestController
@RequestMapping("/datasource")
public class DatasourceController {
	
	private static Logger logger = LoggerFactory.getLogger(DatasourceController.class);
	
	/**
	 * 列出支持的数据源，左侧目录树会用到这个接口
	 * @return 支持的数据源，目前支持oracle和mysql
	 */
	@ApiOperation("列出支持的数据源，用于左侧树")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
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
}
