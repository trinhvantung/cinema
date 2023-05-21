package vn.trinhtung.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class ExcelExporter {
    private final XSSFWorkbook workbook;
    protected final XSSFSheet sheet;

    public ExcelExporter(String sheetName, List<String> headerValues) {
        this.workbook = new XSSFWorkbook();
        this.sheet = this.workbook.createSheet(sheetName);
        writeHeader(headerValues);
    }

    private void writeHeader(List<String> values) {
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeight(20);
        headerStyle.setFont(headerFont);


        headerRow.setRowStyle(headerStyle);

//        createCell(headerRow, 0, "STT", null);
//        createCell(headerRow, 1, "Time", null);
//        createCell(headerRow, 2, "Amount", null);

        Integer columnCount = 0;
        for(String value : values) {
            createCell(headerRow, columnCount++, value, null);
        }
    }

    protected void createCell(Row row, Integer column, Object value, CellStyle style) {
        Cell cell = row.createCell(column);

        if (value instanceof String) {
            cell.setCellValue(value.toString());
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Float) {
            cell.setCellValue((Float) value);
        } else {
            cell.setCellValue(value.toString());
        }

        if(Objects.nonNull(style)) {
            cell.setCellStyle(style);
        }
    }

    public abstract void writeData();

    public void export(HttpServletResponse response) throws IOException {
        writeData();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
