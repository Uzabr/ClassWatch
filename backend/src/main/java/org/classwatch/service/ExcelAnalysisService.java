package org.classwatch.service;

import org.classwatch.dto.StatisticsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelAnalysisService {
    StatisticsResponse analyze(MultipartFile file);
}
