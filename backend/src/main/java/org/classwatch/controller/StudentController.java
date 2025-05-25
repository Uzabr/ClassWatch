package org.classwatch.controller;

import org.classwatch.dto.StatisticsResponse;
import org.classwatch.dto.StudentGroupResponse;
import org.classwatch.model.Student;
import org.classwatch.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {


    private final ExcelAnalysisService excelAnalysisService;
    private final StudentGroupResponseService studentGroupResponseService;
    private final ExcelReaderService excelReaderService;

    public StudentController(ExcelAnalysisService excelAnalysisService, StudentGroupResponseService studentGroupResponseService, ExcelReaderService excelReaderService) {
        this.excelAnalysisService = excelAnalysisService;
        this.studentGroupResponseService = studentGroupResponseService;
        this.excelReaderService = excelReaderService;

    }

    @PostMapping("/upload")
    public ResponseEntity<StatisticsResponse> upload(@RequestParam("file") MultipartFile file) {
        StatisticsResponse statics = excelAnalysisService.analyze(file);
        return ResponseEntity.ok(statics);
    }

    @PostMapping("/upload/report")
    public ResponseEntity<StudentGroupResponse> uploadReport(@RequestParam("file") MultipartFile file) {

        List<Student> students = new ArrayList<>();
        try {
             students = excelReaderService.readExcel(file);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        StudentGroupResponse response = studentGroupResponseService.groupReport(students);
        return ResponseEntity.ok(response);
    }
}
