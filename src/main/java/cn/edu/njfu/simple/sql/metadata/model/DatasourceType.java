package cn.edu.njfu.simple.sql.metadata.model;

public enum DatasourceType {
	
	MYSQL(0, "mysql"),
	ORACLE(1, "oracle");

    // 有用吗？没用吧？可以删了吧？
	private int code;
	private String name;
	
	DatasourceType(int i, String name) {
		this.code = i;
		this.name = name;
	}
}
