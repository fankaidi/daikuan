package co.kensure.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import co.kensure.mem.DateUtils;

/**
 * com.kensure.utils
 *
 * @author Yingjie yao
 */
public class ExcelUtils {

	public static List<String[]> get2CellDataByXls(InputStream inputStream) throws IOException {
		int size = 50;
		List<String[]> valueList = new ArrayList<>();
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow row;
		for (int i = sheet.getTopRow(); i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if (row != null) {
				String[] values = new String[size];
				for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
					HSSFCell hssfCell = row.getCell(j);
					String value;
					if (hssfCell == null) {
						continue;
					} else if (hssfCell.getCellTypeEnum() == CellType.NUMERIC) {
						Double num = new Double(hssfCell.toString());
						value = num.intValue() + "";
					} else {
						value = hssfCell.toString();
					}
					values[j] = value;
				}
				valueList.add(values);
			}
		}
		workbook.close();
		return valueList;
	}

	/**
	 * xlsx的解析
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> get2CellDataByXlsx(InputStream inputStream) throws IOException {
		List<String[]> valueList = new ArrayList<>();

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet xssfSheet = workbook.getSheetAt(0);
		XSSFRow xssfRow;
		// 最多读50列数据
		int size = 50;
		for (int i = 0; i <= xssfSheet.getLastRowNum(); i++) {
			xssfRow = xssfSheet.getRow(i);
			if (xssfRow != null) {
				String[] values = new String[size];
				for (int j = xssfRow.getFirstCellNum(); j <= xssfRow.getLastCellNum(); j++) {
					String value;
					XSSFCell xssfCell = xssfRow.getCell(j);
					if (xssfCell == null) {
						continue;
					} else if (xssfCell.getCellTypeEnum() == CellType.NUMERIC) {
						if (HSSFDateUtil.isCellDateFormatted(xssfCell)) {
							Date date = xssfCell.getDateCellValue();
							value = DateUtils.format(date, DateUtils.DAY_FORMAT);
						} else {
							value =  String.valueOf(xssfCell.getNumericCellValue());
							if(value.indexOf("E")!=-1){
								BigDecimal bd1 = new BigDecimal(xssfCell.getNumericCellValue());
								value = bd1.toPlainString();
							}				
						}
					} else {
						value = xssfCell.toString();
					}
					values[j] = value;
				}
				valueList.add(values);
			}
		}
		workbook.close();
		return valueList;
	}

	/**
	 * 导出xlsx
	 * 
	 * @param outPath
	 *            outPath
	 * @param datas
	 *            datas
	 * @return boolean
	 */
	public static boolean writeXlsx(String outPath, List<String[]> datas) {
		String dir = outPath.substring(0, outPath.lastIndexOf(File.separator));
		FileUtils.createDir(dir);
		// 创建工作文档对象
		Workbook wb = null;
		// 创建文件流
		OutputStream stream = null;
		try {
			wb = new XSSFWorkbook();
			// 创建sheet对象
			Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
			// 循环写入行数据
			for (int i = 0; i < datas.size(); i++) {
				String[] rowdata = datas.get(i);
				Row row = (Row) sheet1.createRow(i);
				// 循环写入列数据
				for (int j = 0; j < rowdata.length; j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(rowdata[j]);
				}
			}

			stream = new FileOutputStream(outPath);
			// 写入数据
			wb.write(stream);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			FileUtils.close(stream);
			FileUtils.close(wb);
		}
		// 关闭文件流

		return true;
	}

}
