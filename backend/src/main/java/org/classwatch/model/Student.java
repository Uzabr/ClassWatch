package org.classwatch.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student {
    private String login;
    private String status;
    private String tribe;
    private int level;
    private int targetLevel;
    private LocalDate deadline;
    private int daysToDeadline;

    public Student() {
    }

    public Student(String login, String status, String tribe, int level, int targetLevel, LocalDate deadline, int daysToDeadline) {
        this.login = login;
        this.status = status;
        this.tribe = tribe;
        this.level = level;
        this.targetLevel = targetLevel;
        this.deadline = deadline;
        this.daysToDeadline = daysToDeadline;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTribe() {
        return tribe;
    }

    public void setTribe(String tribe) {
        this.tribe = tribe;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTargetLevel() {
        return targetLevel;
    }

    public void setTargetLevel(int targetLevel) {
        this.targetLevel = targetLevel;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getDaysToDeadline() {
        return daysToDeadline;
    }

    public void setDaysToDeadline(int daysToDeadline) {
        this.daysToDeadline = daysToDeadline;
    }
}
