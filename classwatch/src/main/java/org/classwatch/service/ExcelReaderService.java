package org.classwatch.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.classwatch.model.Student;
import org.classwatch.util.ExcelCellUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelReaderService {

    public static List<Student> readExcel(MultipartFile file) throws IOException {

        List<Student> students = new ArrayList<>();
        try(InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() < 12) continue;

                String login = ExcelCellUtils.getString(row, 0);
                if (login.isBlank()) continue;

                Student student = new Student();
                student.setLogin(login);
                student.setStatus(ExcelCellUtils.getString(row, 1));
                student.setTribe(ExcelCellUtils.getString(row, 2));
                student.setLevel(ExcelCellUtils.getInt(row, 3));
                student.setTargetLevel(ExcelCellUtils.getInt(row, 5));
                student.setDeadline(ExcelCellUtils.getDate(row, 7));
                student.setDaysToDeadline(ExcelCellUtils.getInt(row, 9));

                students.add(student);
            }
        }

        return students;
    }
}
