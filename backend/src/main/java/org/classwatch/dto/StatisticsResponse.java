package org.classwatch.dto;

public class StatisticsResponse {

    private int totalStudents;
    private int frozenCount;
    private  int blockedCount;
    private int overdueCount;
    private int belowTargetCount;


    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getFrozenCount() {
        return frozenCount;
    }

    public void setFrozenCount(int frozenCount) {
        this.frozenCount = frozenCount;
    }

    public int getBlockedCount() {
        return blockedCount;
    }

    public void setBlockedCount(int blockedCount) {
        this.blockedCount = blockedCount;
    }

    public int getOverdueCount() {
        return overdueCount;
    }

    public void setOverdueCount(int overdueCount) {
        this.overdueCount = overdueCount;
    }

    public int getBelowTargetCount() {
        return belowTargetCount;
    }

    public void setBelowTargetCount(int belowTargetCount) {
        this.belowTargetCount = belowTargetCount;
    }
}
