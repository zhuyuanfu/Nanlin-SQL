package cn.edu.njfu.simple.sql.model.metadata;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 告诉jpa将项目中的枚举类型DataType和数据库中的字符串类型进行相互转换
 * @author zhuyuanfu
 *
 */
@Converter(autoApply = true)
public class DataTypeConverter implements AttributeConverter<DataType, String>{
    
    @Override
    public String convertToDatabaseColumn(DataType attribute) {
        return attribute.name();
    }

    @Override
    public DataType convertToEntityAttribute(String dbData) {
        return DataType.valueOf(dbData);
    }
}
