//package org.classwatch.service;
//
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockMultipartFile;
//
//import java.io.ByteArrayOutputStream;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class BaseAddressTest {
//    @Test
//    void testExtract_shouldReturnValidEmails() throws Exception {
//        // Создаем Excel в памяти
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("emails");
//
//        // Заголовок
//        Row header = sheet.createRow(0);
//        header.createCell(0).setCellValue("Email");
//
//        // Валидные email'ы
//        Row row1 = sheet.createRow(1);
//        row1.createCell(0).setCellValue("john@example.com");
//
//        Row row2 = sheet.createRow(2);
//        row2.createCell(0).setCellValue("jane.doe@example.com");
//
//        // Пишем в память
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        workbook.write(out);
//        workbook.close();
//
//        byte[] excelBytes = out.toByteArray();
//
//        // MockMultipartFile из байтов
//        MockMultipartFile file = new MockMultipartFile(
//                "file",
//                "test.xlsx",
//                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
//                excelBytes
//        );
//
//        // Вызываем метод
//        BaseAddress baseAddress = new BaseAddress();
//        List<String> emails = baseAddress.extraсt(file);
//
//        // Проверка
//        assertNotNull(emails);
//
//        assertEquals(2, emails.size());
//        assertTrue(emails.contains("john@example.com"));
//        assertTrue(emails.contains("jane.doe@example.com"));
//    }
//
//}
