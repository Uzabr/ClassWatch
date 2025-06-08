package org.classwatch.service;

import org.classwatch.dto.StudentGroupResponse;
import org.classwatch.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentGroupResponseServiceImplTest {

    private ReportService reportService;
    private StudentGroupResponseServiceImpl service;

    @BeforeEach
    void setUp() {
        reportService = mock(ReportService.class);
        service = new StudentGroupResponseServiceImpl(reportService);
    }

    @Test
    void testGroupReport_CallsReportServiceAndMapsCorrectly() {
        // Arrange
        Student s1 = createStudent("user1");
        Student s2 = createStudent("user2");
        List<Student> all = List.of(s1, s2);

        when(reportService.frozen(all)).thenReturn(List.of(s1));
        when(reportService.blocked(all)).thenReturn(List.of(s2));
        when(reportService.soonDeadline(all)).thenReturn(List.of(s2));
        when(reportService.overdue(all)).thenReturn(List.of());
        when(reportService.belowTarget(all)).thenReturn(List.of(s1, s2));

        // Act
        StudentGroupResponse response = service.groupReport(all);

        // Assert
        assertEquals(2, response.getTotal());
        assertEquals(1, response.getFrozen());
        assertEquals(1, response.getBlocked());
        assertEquals(1, response.getSoonDeadlineCount());
        assertEquals(0, response.getOverdue());
        assertEquals(2, response.getBelowTarget());

        assertEquals("user1", response.getFrozenStudents().get(0).getLogin());
        assertEquals("user2", response.getBlockedStudents().get(0).getLogin());

        // Verify calls
        verify(reportService).frozen(all);
        verify(reportService).blocked(all);
        verify(reportService).soonDeadline(all);
        verify(reportService).overdue(all);
        verify(reportService).belowTarget(all);
    }

    private Student createStudent(String login) {
        Student s = new Student();
        s.setLogin(login);
        s.setLevel(3);
        s.setTargetLevel(5);
        s.setStatus("Активен");
        s.setDeadline(LocalDate.now().plusDays(10));
        s.setDaysToDeadline(10);
        s.setTribe("Test");
        return s;
    }
}
