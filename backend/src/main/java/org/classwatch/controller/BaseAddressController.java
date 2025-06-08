package org.classwatch.controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.classwatch.util.ExcelCellUtils;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class BaseAddressController {

    @PostMapping("/emails/extract")
    public ResponseEntity<Resource> extractEmails(@RequestParam("file") MultipartFile file) {
        try (InputStream in = file.getInputStream(); Workbook wb = new XSSFWorkbook(in)) {
            Sheet sheet = wb.getSheetAt(0);
            Set<String> emails = new HashSet<>();

            for (Row row : sheet) {
                if (row.getRowNum() < 1) continue;
                String email = ExcelCellUtils.getString(row, 5);
                if (email == null || email.isBlank()) continue;
                if (email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    emails.add(email.toLowerCase().trim());
                }
            }

            // Сохраняем в новый Excel-файл
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Workbook result = new XSSFWorkbook();
            Sheet outSheet = result.createSheet("Emails");

            int rowIdx = 0;
            for (String e : emails) {
                Row r = outSheet.createRow(rowIdx++);
                r.createCell(0).setCellValue(e);
            }

            result.write(out);
            ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"emails_extracted.xlsx\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
