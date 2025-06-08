package org.classwatch.service;
import org.apache.poi.EmptyFileException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ExcelReaderServiceTest {

    @Autowired
    private  ExcelReaderService excelReaderService;


    @Test
    void testReadExcel() throws IOException {

        InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/test_files/student.xlsx"));

                // Создаем MockMultipartFile
                MockMultipartFile multipartFile = new MockMultipartFile("file", "students.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", inputStream );

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


    @Test
    void testReadExcel_ValidFile_ReturnsCorrectData() throws IOException {
        InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/test_files/student.xlsx"));
        MockMultipartFile multipartFile = new MockMultipartFile("file", "student.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", inputStream);

        List<Student> students = excelReaderService.readExcel(multipartFile);

        assertEquals(101, students.size());
        assertEquals("abderusa", students.get(0).getLogin());
        // и т.д.
    }

    @Test
    void testReadExcel_EmptyFile_ReturnsEmptyList() throws IOException {
        InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/test_files/empty.xlsx"));
        MockMultipartFile multipartFile = new MockMultipartFile("file", "empty.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", inputStream);

        assertThrows(EmptyFileException.class, () -> excelReaderService.readExcel(multipartFile));
    }

    @Test
    void testReadExcel_InvalidFile_ThrowsException() {
        MockMultipartFile file = new MockMultipartFile("file", "invalid.txt", "text/plain", "not excel".getBytes());

        assertThrows(RuntimeException.class, () -> excelReaderService.readExcel(file));
    }

}
