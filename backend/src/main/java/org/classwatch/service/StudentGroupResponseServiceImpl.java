package org.classwatch.service;

import org.classwatch.dto.StudentGroupResponse;
import org.classwatch.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGroupResponseServiceImpl implements StudentGroupResponseService {

    private final ReportService reportService;

    public StudentGroupResponseServiceImpl(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public StudentGroupResponse groupReport(List<Student> students) {

        StudentGroupResponse response = new StudentGroupResponse();

        List<Student> frozen = reportService.frozen(students);
        List<Student> blocked = reportService.blocked(students);
        List<Student> soonDeadline = reportService.soonDeadline(students);
        List<Student> overdue = reportService.overdue(students);
        List<Student> belowTarget = reportService.belowTarget(students);

        response.setTotal(students.size());
        response.setFrozen(frozen.size());
        response.setBlocked(blocked.size());
        response.setOverdue(overdue.size());
        response.setBelowTarget(belowTarget.size());
        response.setSoonDeadlineCount(soonDeadline.size());

        response.setTotalStudents(students);
        response.setFrozenStudents(frozen);
        response.setBlockedStudents(blocked);
        response.setOverdueStudents(overdue);
        response.setBelowTargetStudents(belowTarget);
        response.setSoonDeadlineStudents(soonDeadline);

        return response;
    }
}
