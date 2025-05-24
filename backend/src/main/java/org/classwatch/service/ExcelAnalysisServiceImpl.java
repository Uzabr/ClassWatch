package org.classwatch.service;

import org.classwatch.dto.StatisticsResponse;
import org.classwatch.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ExcelAnalysisServiceImpl implements ExcelAnalysisService {

    private final ExcelReaderService excelReaderService;

    public ExcelAnalysisServiceImpl(ExcelReaderService excelReaderService) {
        this.excelReaderService = excelReaderService;
    }

    @Override
    public StatisticsResponse analyze(MultipartFile file) {

        StatisticsResponse response = new StatisticsResponse();
        try {
            List<Student> students = excelReaderService.readExcel(file);

            int total = students.size();
            int frozen = (int) students.stream()
                            .filter(s -> s.getStatus().equalsIgnoreCase("Заморожен")).count();
            int blocked = (int) students.stream()
                            .filter(s -> s.getStatus().equalsIgnoreCase("Заблокирован (дедлайн)")).count();
            int overdue = (int) students.stream()
                            .filter(s -> s.getDaysToDeadline() < 0).count();
            int belowTarget = (int) students.stream()
                            .filter(s -> s.getLevel() < s.getTargetLevel()).count();
            response.setTotalStudents(total);
            response.setFrozenCount(frozen);
            response.setBlockedCount(blocked);
            response.setOverdueCount(overdue);
            response.setBelowTargetCount(belowTarget);
        }
        catch (Exception e) {
            throw new RuntimeException("Ошибка при обработке Excel: " + e.getMessage());
        }

        return response;
    }
}

