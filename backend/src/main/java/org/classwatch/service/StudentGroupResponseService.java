package org.classwatch.service;

import org.classwatch.dto.StudentGroupResponse;
import org.classwatch.model.Student;

import java.util.List;

public interface StudentGroupResponseService {

    StudentGroupResponse groupReport(List<Student> students);
}
