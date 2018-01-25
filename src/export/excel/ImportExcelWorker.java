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
			// �����߼�
			File file = new File(path);
			if (!file.exists()) {
				System.out.println(path + "�ļ�������");
			}
			FileInputStream fis = null;
			try {
				// ʵ����
				ImportData importData = null;
				fis = new FileInputStream(file);
				POIFSFileSystem poiFS = new POIFSFileSystem(fis);
				// �õ�Excel����������
				HSSFWorkbook hssfWb = new HSSFWorkbook(poiFS);
				String fileName = path.substring(path.lastIndexOf(File.separator) + 1);
				// �õ�Excel���������(������������)
				for (int i = 0, len = hssfWb.getNumberOfSheets(); i < len; i++) {
					HSSFSheet sheet = hssfWb.getSheetAt(i);
					// �õ�Excel���������
					HSSFRow sheetTitle = sheet.getRow(0);
					if (sheetTitle == null)
						continue;
					List<String> titleList = new ArrayList<>();
					for (int j = 0; j < sheetTitle.getLastCellNum(); j++) {
						// ����������
						titleList.add(sheetTitle.getCell(j).toString());
					}
					// ���ñ���
					Map<String, Object> rowDatas = null;
					//sheet����
					String sheetName = sheet.getSheetName();
					// 1000���¼���
					List<ImportData> lessThan1000List = new LinkedList<>();
					// 1000���ϼ���
					List<ImportData> greaterThan1000List = new LinkedList<>();
					for (int j = 1, len1 = sheet.getLastRowNum(); j <= len1; j++) {// �ų�������
																					// (����)
						importData = new ImportData();
						// ����excel�ļ���
						importData.setExcelName(fileName);
						// ���õ�ǰsheet����
						importData.setSheetName(sheetName);
						// ���ñ���
						importData.setTitleDatas(titleList);
						HSSFRow perRow = sheet.getRow(j);
						rowDatas = new HashMap<>();
						for (int k = 0, len2 = perRow.getLastCellNum(); k < len2; k++) {// max
																						// ->
																						// 50������
							rowDatas.put(titleList.get(k), perRow.getCell(k));
						}
						// ���������ݼ���
						importData.setRowDatas(rowDatas);
						if (len1 > 100) {// 1000��һ������
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
						// ��ͷ����
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
