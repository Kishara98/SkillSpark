package com.skillspark.enrollment_service.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class EnrollmentDTO {

    private int userId;
    private int courseId;
    private LocalDateTime enrollmentDate;

    public EnrollmentDTO() {
    }

    public EnrollmentDTO(int userId, int courseId, LocalDateTime enrollmentDate) {
        this.userId = userId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
