package export.excel;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImportDBWorker implements Runnable {

	/**
	 * sheet1TableName => sheet1 sheet2TableName => sheet2 sheet3TableName =>
	 * sheet3
	 * 
	 * sheet1Table => FieldMapping sheet2Table => FieldMapping sheet3Table =>
	 * FieldMapping
	 * 
	 * 
	 * 
	 */
	Map<String, Object> mapping;

	public ImportDBWorker(Map<String, Object> mapping) {
		this.mapping = mapping;
	}

	@Override
	public void run() {
		while (true) {
			List<ImportData> list = ExcelData.intoDBQueue.poll();
			if (list == null)
				continue;
			System.out.println("开始插入数据:" + list.size());
			Connection conn = null;
			try {
				conn = JdbcUtils.getConn();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Statement st = null;
			try {
				st = conn.createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				for (int i = 0; i < list.size(); i++) {
					st.addBatch(getInsertSql(list.get(i)));
				}
				int[] executeBatch = st.executeBatch();
				System.out.println(executeBatch.length);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("数据插入完毕");
		}
	}

	private String getInsertSql(ImportData exportData) {
		// 真实表名
		String tableName = mapping.get(exportData.getSheetName() + "TableName") == null
				? exportData.getSheetName().toString()
				: mapping.get(exportData.getSheetName() + "TableName").toString();
		Object obj = mapping.get(exportData.getSheetName() + "TableField") == null ? null
				: mapping.get(exportData.getSheetName() + "TableField");
		// <id,1>
		Map<String, Object> rowDatas = exportData.getRowDatas();
		Iterator<Object> iterator = rowDatas.values().iterator();
		List<String> titleDatas = exportData.getTitleDatas();
		int len = titleDatas.size();
		if (obj != null) {
			@SuppressWarnings("unchecked")
			List<FieldMapping> list = (List<FieldMapping>) obj;
			for (int i = 0; i < list.size(); i++) {// 多少字段需要替换
				FieldMapping fieldMapping = list.get(i);
				for (int j = 0; j < len; j++) {// excel中标题字段数
					String title = titleDatas.get(j);
					if (title.equals(fieldMapping.getFieldName())) {
						titleDatas.remove(title);
						titleDatas.add(fieldMapping.getTableFieldName());
					}
				}
				// 表格字段名被用户设置字段名覆盖
				if ("Int".equals(fieldMapping.getTableFieldType())) {
					int valInt = (int) Double.parseDouble(rowDatas.get(fieldMapping.getFieldName()).toString());
					rowDatas.remove(fieldMapping.getFieldName());
					rowDatas.put(fieldMapping.getTableFieldName(), valInt);
				} else if ("String".equals(fieldMapping.getTableFieldType())) {
					String valStr = rowDatas.get(fieldMapping.getFieldName()).toString();
					rowDatas.remove(fieldMapping.getFieldName());
					rowDatas.put(fieldMapping.getTableFieldName(), valStr);
				}
			}
		}
		StringBuffer sql = new StringBuffer("insert into ").append(tableName).append("(");
		for (int i = 0; i < len; i++) {
			String key = titleDatas.get(i);
			if (i == len - 1) {
				sql.append(key).append(") values ('");
			} else {
				sql.append(key).append(",");
			}
		}
		for (int i = 0; i < len; i++) {
			String key = titleDatas.get(i);
			if (i == len - 1) {
				sql.append(rowDatas.get(key)).append("')");
			} else {
				sql.append(rowDatas.get(key)).append("','");
			}
		}
		return sql.toString();
	}
}
