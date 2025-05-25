package org.classwatch.service;

import org.classwatch.dto.StatisticsResponse;
import org.classwatch.dto.StudentGroupResponse;
import org.classwatch.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelAnalysisService {
    StatisticsResponse analyze(MultipartFile file);
}
