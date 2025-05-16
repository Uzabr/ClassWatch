package org.classwatch.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.time.LocalDate;

public class ExcelCellUtils {


    public static String getString(Row row, int index) {
        if (row == null) return "";
        Cell cell = row.getCell(index);
        if (cell == null || cell.getCellType() == CellType.BLANK) return "";
        return cell.getCellType() == CellType.STRING ? cell.getStringCellValue().trim() : cell.getCellType() == CellType.NUMERIC ? String.valueOf(cell.getNumericCellValue()) : "";
    }

    public static int getInt(Row row, int index) {
        if (row == null) return 0;

        Cell cell = row.getCell(index);
        if(cell == null || cell.getCellType() == CellType.BLANK) return 0;
        return (int) (cell.getCellType() == CellType.NUMERIC ? cell.getNumericCellValue() : 0);
    }

    public static LocalDate getDate(Row row, int index) {
        if (row == null) return null;
        Cell cell = row.getCell(index);
        if (cell == null || cell.getCellType() == CellType.BLANK) return null;
        return cell.getCellType() == CellType.NUMERIC ? cell.getLocalDateTimeCellValue().toLocalDate() : null;
    }
}
