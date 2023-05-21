package vn.trinhtung.util;

import org.apache.poi.ss.usermodel.Row;
import vn.trinhtung.dto.TopRevenueByMoveResponseDTO;

import java.util.Arrays;
import java.util.List;

public class TopRevenueByMovieExcelExporter extends ExcelExporter {

    private final List<TopRevenueByMoveResponseDTO> data;

    public TopRevenueByMovieExcelExporter(List<TopRevenueByMoveResponseDTO> data) {
        super("Revenue", Arrays.asList("ID", "Movie", "Amount"));
        this.data = data;
    }

    @Override
    public void writeData() {
        Integer rowCount = 1;
        for (TopRevenueByMoveResponseDTO revenue : data) {
            Row row = sheet.createRow(rowCount);
            Integer columnCount = 0;
            createCell(row, columnCount++, rowCount, null);
            createCell(row, columnCount++, revenue.getMovieName(), null);
            createCell(row, columnCount, revenue.getAmount(), null);

            rowCount++;
        }
    }
}
