package vn.trinhtung.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.trinhtung.dto.RevenueResponseDTO;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RevenueExcelExporter extends ExcelExporter {
    private final List<RevenueResponseDTO> data;
//    private final XSSFWorkbook workbook;
//    private final XSSFSheet sheet;

    public RevenueExcelExporter(List<RevenueResponseDTO> data) {
        super("Revenue", Arrays.asList("ID", "Time", "Amount"));
        this.data = data;
//        this.workbook = new XSSFWorkbook();
//        this.sheet = this.workbook.createSheet("Revenue");
    }

    @Override
    public void writeData() {
        Integer rowCount = 1;
        for(RevenueResponseDTO revenue : data) {
            Row row = sheet.createRow(rowCount);
            Integer columnCount = 0;
            createCell(row, columnCount++, rowCount, null);
            createCell(row, columnCount++, revenue.getTime(), null);
            createCell(row, columnCount, revenue.getAmount(), null);

            rowCount++;
        }
    }

//    private void writeHeader() {
//        Row headerRow = sheet.createRow(0);
//        CellStyle headerStyle = workbook.createCellStyle();
//        XSSFFont headerFont = workbook.createFont();
//        headerFont.setBold(true);
//        headerFont.setFontHeight(20);
//        headerStyle.setFont(headerFont);
//
//
//        headerRow.setRowStyle(headerStyle);
//
//        createCell(headerRow, 0, "STT", null);
//        createCell(headerRow, 1, "Time", null);
//        createCell(headerRow, 2, "Amount", null);
//    }
//
//    private void writeData() {
//        Integer rowCount = 1;
//        for(RevenueResponseDTO revenue : data) {
//            Row row = sheet.createRow(rowCount);
//            Integer columnCount = 0;
//            createCell(row, columnCount++, rowCount, null);
//            createCell(row, columnCount++, revenue.getTime(), null);
//            createCell(row, columnCount, revenue.getAmount(), null);
//        }
//    }
//
//    private void createCell(Row row, Integer column, Object value, CellStyle style) {
//        Cell cell = row.createCell(column);
//
//        if (value instanceof String) {
//            cell.setCellValue(value.toString());
//        } else if (value instanceof Integer) {
//            cell.setCellValue((Integer) value);
//        } else if (value instanceof Long) {
//            cell.setCellValue((Long) value);
//        } else if (value instanceof Double) {
//            cell.setCellValue((Double) value);
//        } else if (value instanceof Float) {
//            cell.setCellValue((Float) value);
//        } else {
//            cell.setCellValue(value.toString());
//        }
//
//        if(Objects.nonNull(style)) {
//            cell.setCellStyle(style);
//        }
//    }
//
//    public void export(HttpServletResponse response) throws IOException {
//        writeHeader();
//        writeData();
//        ServletOutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//        workbook.close();
//        outputStream.flush();
//        outputStream.close();
//    }
}
