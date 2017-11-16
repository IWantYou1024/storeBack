package cn.itcast.poi.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class POITest {

	@Test
	public void testPoi() throws IOException {
		// 创建一个工作簿
		Workbook wb = new HSSFWorkbook(); // 作用域excel2003版本
		// 创建工作表
		Sheet sheet = wb.createSheet();
		// 创建行对象
		Row row = sheet.createRow(3);
		// 创建单元格
		Cell cell = row.createCell(3);
		// 设置单元格的内容
		cell.setCellValue("Don't worry");
		// 设置单元格的样式
		CellStyle cellStyle = wb.createCellStyle();
		Font font = wb.createFont();

		font.setFontHeightInPoints((short) 28); // 字体大小
		font.setFontName("华文行楷"); // 字体名称

		cellStyle.setFont(font); // 设置单元格样式的字体
		cell.setCellStyle(cellStyle); // 将单元格样式作用于单元格

		// 保存, 关闭流
		OutputStream os = new FileOutputStream("F:/jiu.xls");
		wb.write(os);

		wb.close();
		os.close();
	}

}
