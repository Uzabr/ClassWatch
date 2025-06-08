package org.classwatch.service;


import org.classwatch.dto.StatisticsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExcelAnalysisServiceImplTest {




    @Autowired
    private ExcelAnalysisServiceImpl excelAnalysisService;

    @Test
    void testAnalyze() throws IOException {

                // Загрузка файла из resources
                File file = new File("src/main/resources/test_files/student.xlsx");
                FileInputStream input = new FileInputStream(file);

                MultipartFile multipartFile = new MockMultipartFile(
                        "file",
                        file.getName(),
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                        input
                );

                // Вызов метода
                StatisticsResponse response = excelAnalysisService.analyze(multipartFile);

                // Проверки
                assertNotNull(response);
                assertEquals(101, response.getTotalStudents());         // Пример — если в файле 5 студентов
                assertEquals(6, response.getFrozenCount());        // Пример — если 2 заморожены
                assertEquals(4, response.getBlockedCount());       // и т.д.
                assertEquals(8, response.getOverdueCount());
                assertEquals(101, response.getBelowTargetCount());

    }
}
