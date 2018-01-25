package export.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * 导入工具类
 */
public class ImportUtils {

	public static void main(String[] args) {
		/**
		 * sheet1TableName => sheet1 sheet2TableName => sheet2
		 * 
		 * sheet1Table => List<FieldMapping> sheet2Table => List<FieldMapping>
		 */
		Map<String, Object> map = new HashMap<>();
		map.put("Sheet1TableName", "t_system_user_1");
		List<FieldMapping> list = new ArrayList<>();
		FieldMapping fp1 = new FieldMapping();
		fp1.setFieldName("编号");
		fp1.setTableFieldName("id");
		fp1.setTableFieldType("Int");
		list.add(fp1);
		FieldMapping fp2 = new FieldMapping();
		fp2.setFieldName("name");
		fp2.setTableFieldName("username");
		fp2.setTableFieldType("String");
		list.add(fp2);
		FieldMapping fp3 = new FieldMapping();
		fp3.setFieldName("pwd");
		fp3.setTableFieldName("password");
		fp3.setTableFieldType("String");
		list.add(fp3);
		map.put("Sheet1TableField", list);
		importExcelToTable("C:\\Users\\Administrator\\Desktop\\aaaaa.xls", map);
	}

	/**
	 * 导入工具
	 * 
	 * @param path
	 *            全路径
	 */
	public static void importExcelToTable(String path) {
		// 默认文件名对应的表名
		importExcelToTable(path, path.substring(path.lastIndexOf(File.separator) + 1));
	}

	/**
	 * 导入
	 * 
	 * @param path
	 *            全路径
	 * @param tableName
	 *            表名
	 */
	public static void importExcelToTable(String path, String tableName) {
		importExcelToTable(path, tableName, null);
	}

	/**
	 * 导入
	 * 
	 * @param path
	 *            全路径
	 * @param excelTabelMapping
	 *            Excel字段和表名映射集合
	 */
	public static void importExcelToTable(String path, Map<String, Object> excelTabelMapping) {
		importExcelToTable(path, path.substring(path.lastIndexOf(File.separator) + 1), excelTabelMapping);
	}

	/**
	 * 导入
	 * 
	 * @param path
	 *            全路径
	 * @param tableName
	 *            表名
	 * @param excelTabelMapping
	 *            Excel字段和表名映射集合
	 */
	public static void importExcelToTable(String path, String tableName, Map<String, Object> excelTabelMapping) {
		// 生产者
		ExcelData.pool.execute(new ImportExcelWorker(path));
		// 消费者 启动导入db线程，进行队列扫描
		for (int i = 0; i < 100; i++) {
			ExcelData.pool.execute(new ImportDBWorker(excelTabelMapping));
		}
	}

	/**
	 * 获取导入excel文件标题行（第一行）
	 * 
	 * @param path
	 *            excel全路径
	 * @return 标题集合
	 */
	public static Map<String, List<String>> getImportExcelTitles(String path) {
		// 导入逻辑
		File file = new File(path);
		if (!file.exists()) {
			System.out.println(path + "文件不存在");
		}
		FileInputStream fis = null;
		Map<String, List<String>> sheetTitleMap = new HashMap<>();
		try {
			// 实例化
			fis = new FileInputStream(file);
			POIFSFileSystem poiFS = new POIFSFileSystem(fis);
			// 得到Excel工作簿对象
			@SuppressWarnings("resource")
			HSSFWorkbook hssfWb = new HSSFWorkbook(poiFS);
			List<String> titleList = null;
			// 得到Excel工作表对象(多个工作表对象)
			for (int i = 0, len = hssfWb.getNumberOfSheets(); i < len; i++) {
				HSSFSheet sheet = hssfWb.getSheetAt(i);
				// 得到Excel工作表的行
				HSSFRow sheetTitle = sheet.getRow(0);
				if (sheetTitle == null)
					continue;
				titleList = new ArrayList<>();
				for (int j = 0; j < sheetTitle.getLastCellNum(); j++) {
					// 标题行数据
					titleList.add(sheetTitle.getCell(j).toString());
				}
				sheetTitleMap.put(sheet.getSheetName(), titleList);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sheetTitleMap;
	}
}
