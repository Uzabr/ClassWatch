package org.classwatch.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.classwatch.model.Student;
import org.classwatch.service.ExcelReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {


    private final ExcelReaderService excelReaderService;


    @Autowired
    public HomeController(ExcelReaderService excelReaderService) {
        this.excelReaderService = excelReaderService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile file, Model model) throws IOException {

        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".xlsx")) {
            model.addAttribute("error", "Пожалуйста, выберите Excel-файл формата .xlsx");
            return "index";
        }
        try {
            List<Student> students = excelReaderService.readExcel(file);
            model.addAttribute("students", students);
            return "result";
        }
        catch (Exception e) {
            model.addAttribute("Ошибка при обработке файла", e.getMessage());
            return "index";
        }
    }
}
