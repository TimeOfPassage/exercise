package export.excel;

import java.util.List;
import java.util.Map;

public class ImportData {

	private String excelName;// ����Excel����
	private String sheetName;// ��ǰ����sheet����
	private Map<String, Object> rowDatas;// ��������
	private List<String> titleDatas;// ����

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Map<String, Object> getRowDatas() {
		return rowDatas;
	}

	public void setRowDatas(Map<String, Object> rowDatas) {
		this.rowDatas = rowDatas;
	}

	public List<String> getTitleDatas() {
		return titleDatas;
	}

	public void setTitleDatas(List<String> titleDatas) {
		this.titleDatas = titleDatas;
	}

	public boolean isNull() {
		if (this.excelName != null && !"".equals(this.excelName))
			return false;
		if (this.sheetName != null && !"".equals(this.sheetName))
			return false;
		if (this.titleDatas != null && this.titleDatas.size() != 0)
			return false;
		if (this.rowDatas != null && this.rowDatas.size() != 0)
			return false;
		return true;
	}
}
