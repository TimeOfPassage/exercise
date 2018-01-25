package export.excel;

public class FieldMapping {
	// 字段名
	private String fieldName;

	// 对应表字段名称
	private String tableFieldName;

	// 对应表字段类型
	private String tableFieldType;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getTableFieldName() {
		return tableFieldName;
	}

	public void setTableFieldName(String tableFieldName) {
		this.tableFieldName = tableFieldName;
	}

	public String getTableFieldType() {
		return tableFieldType;
	}

	public void setTableFieldType(String tableFieldType) {
		this.tableFieldType = tableFieldType;
	}
}
