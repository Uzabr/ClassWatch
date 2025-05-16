package org.classwatch.service;

import org.classwatch.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    public Map<String, Long> countByStatus(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getStatus, Collectors.counting()));
    }

    public List<Student> soonDeadline(List<Student> students) {
        return students.stream()
                .filter(s -> s.getDaysToDeadline() <= 10)
                .collect(Collectors.toList());
    }

    public List<Student> blocked(List<Student> students){
        return  students.stream()
                .filter(s -> s.getStatus().equalsIgnoreCase("Заблокирован (дедлайн)"))
                .collect(Collectors.toList());
    }

    public List<Student> frozen(List<Student> students){
       return students.stream()
                .filter(s-> s.getStatus().equalsIgnoreCase("Заморожен"))
                .collect(Collectors.toList());
    }
}
