package org.classwatch.service;

import org.classwatch.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExcelReaderServiceTest {

    @Test
    void testReadExcel() throws IOException {
//                // Создаем тестовый Excel-файл
//                Workbook workbook = new XSSFWorkbook();
//                Sheet sheet = workbook.createSheet();
//                for (int i = 0; i < 15; i++) {
//                    Row row = sheet.createRow(i);
//                    if (i >= 12) {
//                        row.createCell(0).setCellValue("login" + i);
//                        row.createCell(1).setCellValue("status" + i);
//                        row.createCell(2).setCellValue("tribe" + i);
//                        row.createCell(3).setCellValue(i);
//                        row.createCell(5).setCellValue(i + 1);
//                        row.createCell(7).setCellValue("2025-05-11");
//                        row.createCell(9).setCellValue(i + 10);
//                    }
//                }
        // Записываем в ByteArrayOutputStream
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                workbook.write(out);
//                ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/test_files/students_info_ 24_12_SKD-3.xlsx"));

                // Создаем MockMultipartFile
                MockMultipartFile multipartFile = new MockMultipartFile("file", "students_info_ 24_12_SKD-3.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", inputStream );

                // Вызываем метод readExcel
                List<Student> students = ExcelReaderService.readExcel(multipartFile);

                // Проверяем результаты
                assertEquals(109, students.size());
                assertEquals("abderusa", students.get(0).getLogin());
                assertEquals("Активен", students.get(0).getStatus());
                assertEquals("Fossa", students.get(0).getTribe());
                assertEquals(5, students.get(0).getLevel());
                assertEquals(7, students.get(0).getTargetLevel());
                assertEquals("2025-07-21", students.get(0).getDeadline().toString());
                assertEquals(85, students.get(0).getDaysToDeadline());
            }
}
