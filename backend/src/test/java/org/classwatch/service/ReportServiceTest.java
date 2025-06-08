package org.classwatch.service;

import org.classwatch.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReportServiceTest {

    private ReportService reportService;

    @BeforeEach
    void setUp() {
        reportService = new ReportService(); // если @Service — просто new
    }

    @Test
    void testFrozen_ReturnsOnlyFrozenStudents() {
        List<Student> students = List.of(
                createStudent("user1", "Заморожен"),
                createStudent("user2", "Активен"),
                createStudent("user3", "Заблокирован (дедлайн)")
        );

        List<Student> frozen = reportService.frozen(students);

        assertEquals(1, frozen.size());
        assertEquals("user1", frozen.get(0).getLogin());
    }

    @Test
    void testBlocked_ReturnsOnlyBlockedStudents() {
        List<Student> students = List.of(
                createStudent("user1", "Заблокирован (дедлайн)"),
                createStudent("user2", "Активен")
        );

        List<Student> blocked = reportService.blocked(students);
        System.out.println(blocked.size());
        assertEquals(1, blocked.size());


        assertEquals("user1", blocked.get(0).getLogin());
    }

    @Test
    void testOverdue_ReturnsStudentsWithPastDeadline() {
        List<Student> students = List.of(
                createStudent("user1", "Активен", LocalDate.now().minusDays(2)),
                createStudent("user2", "Активен", LocalDate.now().plusDays(3))
        );

        List<Student> overdue = reportService.overdue(students);

        assertEquals(1, overdue.size());
        assertEquals("user1", overdue.get(0).getLogin());
    }

    @Test
    void testBelowTarget_ReturnsStudentsBelowTargetLevel() {
        List<Student> students = List.of(
                createStudent("user1", 3, 5),
                createStudent("user2", 5, 5),
                createStudent("user3", 6, 5)
        );

        List<Student> below = reportService.belowTarget(students);

        assertEquals(1, below.size());
        assertEquals("user1", below.get(0).getLogin());
    }

    @Test
    void testSoonDeadline_ReturnsStudentsWithFewDaysLeft() {
        List<Student> students = List.of(
                createStudent("user1", LocalDate.now().plusDays(5)), // < 3 дней
                createStudent("user2", LocalDate.now().plusDays(101))  // > 3 дней
        );

        List<Student> soon = reportService.soonDeadline(students);

        assertEquals(1, soon.size());
        assertEquals("user1", soon.get(0).getLogin());
    }


    private Student createStudent(String login, String status) {
        Student s = new Student();
        s.setLogin(login);
        s.setStatus(status);
        s.setLevel(1);
        s.setTargetLevel(1);
        s.setDeadline(LocalDate.now().plusDays(5));
        s.setDaysToDeadline(5);
        return s;
    }

    private Student createStudent(String login, int level, int target) {
        Student s = new Student();
        s.setLogin(login);
        s.setStatus("Активен");
        s.setLevel(level);
        s.setTargetLevel(target);
        s.setDeadline(LocalDate.now().plusDays(10));
        s.setDaysToDeadline(10);
        return s;
    }

    private Student createStudent(String login, LocalDate deadline) {
        Student s = new Student();
        s.setLogin(login);
        s.setStatus("Активен");
        s.setLevel(5);
        s.setTargetLevel(6);
        s.setDeadline(deadline);
        s.setDaysToDeadline((int) (deadline.toEpochDay() - LocalDate.now().toEpochDay()));
        return s;
    }


    private Student createStudent(String login, String status, LocalDate deadline) {
        Student s = new Student();
        s.setLogin(login);
        s.setStatus(status);
        s.setLevel(3);
        s.setTargetLevel(5);
        s.setDeadline(deadline);
        s.setDaysToDeadline((int) (deadline.toEpochDay() - LocalDate.now().toEpochDay()));
        return s;
    }


}
