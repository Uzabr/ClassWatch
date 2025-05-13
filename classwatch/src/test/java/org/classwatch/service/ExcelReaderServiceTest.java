package org.classwatch.service;
import org.classwatch.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExcelReaderServiceTest {

    @Autowired
    private  ExcelReaderService excelReaderService;


    @Test
    void testReadExcel() throws IOException {

        InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/test_files/students_info_ 24_12_SKD-3.xlsx"));

                // Создаем MockMultipartFile
                MockMultipartFile multipartFile = new MockMultipartFile("file", "students_info_ 24_12_SKD-3.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", inputStream );

                // Вызываем метод readExcel
                List<Student> students = excelReaderService.readExcel(multipartFile);

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
