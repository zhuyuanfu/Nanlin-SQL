package cn.edu.njfu.simple.sql.metadata.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 告诉jpa将项目中的枚举类型DatasourceType和数据库中的字符串类型进行相互转换
 * @author zhuyuanfu
 *
 */
@Converter(autoApply = true)
public class DatasourceTypeConverter implements AttributeConverter<DatasourceType, String>{

    @Override
    public String convertToDatabaseColumn(DatasourceType attribute) {
        return attribute.name();
    }

    @Override
    public DatasourceType convertToEntityAttribute(String dbData) {
        return DatasourceType.valueOf(dbData);
    }

}
