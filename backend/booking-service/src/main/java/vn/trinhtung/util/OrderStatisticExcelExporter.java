package vn.trinhtung.util;

import org.apache.poi.ss.usermodel.Row;
import vn.trinhtung.dto.OrderStatisticResponseDTO;
import vn.trinhtung.dto.TopRevenueByMoveResponseDTO;

import java.util.Arrays;
import java.util.List;

public class OrderStatisticExcelExporter extends ExcelExporter {
    private final List<OrderStatisticResponseDTO> data;

    public OrderStatisticExcelExporter(List<OrderStatisticResponseDTO> data) {
        super("Order", Arrays.asList("ID", "Time", "Count"));
        this.data = data;
    }

    @Override
    public void writeData() {
        Integer rowCount = 1;
        for (OrderStatisticResponseDTO revenue : data) {
            Row row = sheet.createRow(rowCount);
            Integer columnCount = 0;
            createCell(row, columnCount++, rowCount, null);
            createCell(row, columnCount++, revenue.getTime(), null);
            createCell(row, columnCount, revenue.getCount(), null);

            rowCount++;
        }
    }
}
