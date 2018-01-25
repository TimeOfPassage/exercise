package export.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ImportExcelWorker implements Runnable {

	String path;
	public ImportExcelWorker(String path) {
		this.path = path;
	}

	@SuppressWarnings("resource")
	@Override
	public void run() {
		synchronized (ImportExcelWorker.class) {
			// 导入逻辑
			File file = new File(path);
			if (!file.exists()) {
				System.out.println(path + "文件不存在");
			}
			FileInputStream fis = null;
			try {
				// 实例化
				ImportData importData = null;
				fis = new FileInputStream(file);
				POIFSFileSystem poiFS = new POIFSFileSystem(fis);
				// 得到Excel工作簿对象
				HSSFWorkbook hssfWb = new HSSFWorkbook(poiFS);
				String fileName = path.substring(path.lastIndexOf(File.separator) + 1);
				// 得到Excel工作表对象(多个工作表对象)
				for (int i = 0, len = hssfWb.getNumberOfSheets(); i < len; i++) {
					HSSFSheet sheet = hssfWb.getSheetAt(i);
					// 得到Excel工作表的行
					HSSFRow sheetTitle = sheet.getRow(0);
					if (sheetTitle == null)
						continue;
					List<String> titleList = new ArrayList<>();
					for (int j = 0; j < sheetTitle.getLastCellNum(); j++) {
						// 标题行数据
						titleList.add(sheetTitle.getCell(j).toString());
					}
					// 设置标题
					Map<String, Object> rowDatas = null;
					//sheet名称
					String sheetName = sheet.getSheetName();
					// 1000以下集合
					List<ImportData> lessThan1000List = new LinkedList<>();
					// 1000以上集合
					List<ImportData> greaterThan1000List = new LinkedList<>();
					for (int j = 1, len1 = sheet.getLastRowNum(); j <= len1; j++) {// 排除标题行
																					// (万行)
						importData = new ImportData();
						// 设置excel文件名
						importData.setExcelName(fileName);
						// 设置当前sheet名称
						importData.setSheetName(sheetName);
						// 设置标题
						importData.setTitleDatas(titleList);
						HSSFRow perRow = sheet.getRow(j);
						rowDatas = new HashMap<>();
						for (int k = 0, len2 = perRow.getLastCellNum(); k < len2; k++) {// max
																						// ->
																						// 50列数据
							rowDatas.put(titleList.get(k), perRow.getCell(k));
						}
						// 设置行数据集合
						importData.setRowDatas(rowDatas);
						if (len1 > 100) {// 1000条一个集合
							greaterThan1000List.add(importData);
							if (greaterThan1000List.size() % 100 == 0) {
								ExcelData.intoDBQueue.add(greaterThan1000List);
								greaterThan1000List = new LinkedList<>();
							}
						} else {
							lessThan1000List.add(importData);
						}
					}
					if (lessThan1000List.size() > 0) {
						ExcelData.intoDBQueue.add(lessThan1000List);
					} else if (greaterThan1000List.size() > 0) {
						// 零头处理
						ExcelData.intoDBQueue.add(greaterThan1000List);
					}
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
		}
	}
}
