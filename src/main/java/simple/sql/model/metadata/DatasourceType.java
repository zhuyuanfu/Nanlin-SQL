package simple.sql.model.metadata;

public enum DatasourceType {
	
	MYSQL(0, "mysql"),
	ORACLE(1, "oracle");

	private int code;
	private String name;
	
	DatasourceType(int i, String name) {
		this.code = i;
		this.name = name;
	}
}
