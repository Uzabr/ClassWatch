package org.classwatch.controller;

import org.classwatch.dto.StatisticsResponse;
import org.classwatch.dto.StudentGroupResponse;
import org.classwatch.service.ExcelAnalysisService;
import org.classwatch.service.ExcelReaderService;
import org.classwatch.service.StudentGroupResponseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExcelAnalysisService excelAnalysisService;

    @MockBean
    private ExcelReaderService excelReaderService;

    @MockBean
    private StudentGroupResponseService studentGroupResponseService;

    @Test
    void testUploadAnalyze_ReturnsStatistics() throws Exception {
        Path path = Paths.get("src/main/resources/test_files/student.xlsx");
        byte[] content = Files.readAllBytes(path);

        MockMultipartFile file = new MockMultipartFile(
                "file", "student.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", content
        );

        when(excelAnalysisService.analyze(any())).thenReturn(new StatisticsResponse());

        mockMvc.perform(multipart("/api/upload").file(file))
                .andExpect(status().isOk());

        verify(excelAnalysisService).analyze(any());
    }

    @Test
    void testUploadReport_ReturnsGroupedData() throws Exception {
        Path path = Paths.get("src/main/resources/test_files/student.xlsx");
        byte[] content = Files.readAllBytes(path);

        MockMultipartFile file = new MockMultipartFile(
                "file", "student.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", content
        );

        when(excelReaderService.readExcel(any())).thenReturn(List.of());
        when(studentGroupResponseService.groupReport(any())).thenReturn(new StudentGroupResponse());

        mockMvc.perform(multipart("/api/upload/report").file(file))
                .andExpect(status().isOk());

        verify(excelReaderService).readExcel(any());
        verify(studentGroupResponseService).groupReport(any());
    }
}
