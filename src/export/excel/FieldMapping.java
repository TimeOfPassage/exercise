package export.excel;

public class FieldMapping {
	// �ֶ���
	private String fieldName;

	// ��Ӧ���ֶ�����
	private String tableFieldName;

	// ��Ӧ���ֶ�����
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
