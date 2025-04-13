package com.skillspark.course_service.dto;

public class CourseDTO {

    private String title;
    private String description;
    private int instructor_id;

    public CourseDTO() {
    }

    public CourseDTO(String title, String description, int instructor_id) {
        this.title = title;
        this.description = description;
        this.instructor_id = instructor_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }
}
