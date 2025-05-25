package org.classwatch.dto;

import org.classwatch.model.Student;

import java.util.List;

public class StudentGroupResponse {

        private int total;
        private int blocked;
        private int frozen;
        private int overdue;
        private int belowTarget;
        private int soonDeadlineCount;

        private List<Student> totalStudents;
        private List<Student> blockedStudents;
        private List<Student> frozenStudents;
        private List<Student> overdueStudents;
        private List<Student> belowTargetStudents;
        private List<Student> soonDeadlineStudents;


        public int getSoonDeadlineCount() {
                return soonDeadlineCount;
        }

        public void setSoonDeadlineCount(int soonDeadlineCount) {
                this.soonDeadlineCount = soonDeadlineCount;
        }

        public List<Student> getSoonDeadlineStudents() {
                return soonDeadlineStudents;
        }

        public void setSoonDeadlineStudents(List<Student> soonDeadlineStudents) {
                this.soonDeadlineStudents = soonDeadlineStudents;
        }

        public int getTotal() {
                return total;
        }

        public void setTotal(int total) {
                this.total = total;
        }

        public int getBlocked() {
                return blocked;
        }

        public void setBlocked(int blocked) {
                this.blocked = blocked;
        }

        public int getFrozen() {
                return frozen;
        }

        public void setFrozen(int frozen) {
                this.frozen = frozen;
        }

        public int getOverdue() {
                return overdue;
        }

        public void setOverdue(int overdue) {
                this.overdue = overdue;
        }

        public int getBlowTarget() {
                return belowTarget;
        }

        public void setBlowTarget(int belowTarget) {
                this.belowTarget = belowTarget;
        }

        public List<Student> getTotalStudents() {
                return totalStudents;
        }

        public void setTotalStudents(List<Student> totalStudents) {
                this.totalStudents = totalStudents;
        }

        public List<Student> getBlockedStudents() {
                return blockedStudents;
        }

        public void setBlockedStudents(List<Student> blockedStudents) {
                this.blockedStudents = blockedStudents;
        }

        public List<Student> getFrozenStudents() {
                return frozenStudents;
        }

        public void setFrozenStudents(List<Student> frozenStudents) {
                this.frozenStudents = frozenStudents;
        }

        public List<Student> getOverdueStudents() {
                return overdueStudents;
        }

        public void setOverdueStudents(List<Student> overdueStudents) {
                this.overdueStudents = overdueStudents;
        }

        public List<Student> getBlowTargetStudents() {
                return belowTargetStudents;
        }

        public void setBlowTargetStudents(List<Student> belowTargetStudents) {
                this.belowTargetStudents = belowTargetStudents;
        }
}
