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
 * ���빤����
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
		fp1.setFieldName("���");
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
	 * ���빤��
	 * 
	 * @param path
	 *            ȫ·��
	 */
	public static void importExcelToTable(String path) {
		// Ĭ���ļ�����Ӧ�ı���
		importExcelToTable(path, path.substring(path.lastIndexOf(File.separator) + 1));
	}

	/**
	 * ����
	 * 
	 * @param path
	 *            ȫ·��
	 * @param tableName
	 *            ����
	 */
	public static void importExcelToTable(String path, String tableName) {
		importExcelToTable(path, tableName, null);
	}

	/**
	 * ����
	 * 
	 * @param path
	 *            ȫ·��
	 * @param excelTabelMapping
	 *            Excel�ֶκͱ���ӳ�伯��
	 */
	public static void importExcelToTable(String path, Map<String, Object> excelTabelMapping) {
		importExcelToTable(path, path.substring(path.lastIndexOf(File.separator) + 1), excelTabelMapping);
	}

	/**
	 * ����
	 * 
	 * @param path
	 *            ȫ·��
	 * @param tableName
	 *            ����
	 * @param excelTabelMapping
	 *            Excel�ֶκͱ���ӳ�伯��
	 */
	public static void importExcelToTable(String path, String tableName, Map<String, Object> excelTabelMapping) {
		// ������
		ExcelData.pool.execute(new ImportExcelWorker(path));
		// ������ ��������db�̣߳����ж���ɨ��
		for (int i = 0; i < 100; i++) {
			ExcelData.pool.execute(new ImportDBWorker(excelTabelMapping));
		}
	}

	/**
	 * ��ȡ����excel�ļ������У���һ�У�
	 * 
	 * @param path
	 *            excelȫ·��
	 * @return ���⼯��
	 */
	public static Map<String, List<String>> getImportExcelTitles(String path) {
		// �����߼�
		File file = new File(path);
		if (!file.exists()) {
			System.out.println(path + "�ļ�������");
		}
		FileInputStream fis = null;
		Map<String, List<String>> sheetTitleMap = new HashMap<>();
		try {
			// ʵ����
			fis = new FileInputStream(file);
			POIFSFileSystem poiFS = new POIFSFileSystem(fis);
			// �õ�Excel����������
			@SuppressWarnings("resource")
			HSSFWorkbook hssfWb = new HSSFWorkbook(poiFS);
			List<String> titleList = null;
			// �õ�Excel���������(������������)
			for (int i = 0, len = hssfWb.getNumberOfSheets(); i < len; i++) {
				HSSFSheet sheet = hssfWb.getSheetAt(i);
				// �õ�Excel���������
				HSSFRow sheetTitle = sheet.getRow(0);
				if (sheetTitle == null)
					continue;
				titleList = new ArrayList<>();
				for (int j = 0; j < sheetTitle.getLastCellNum(); j++) {
					// ����������
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
