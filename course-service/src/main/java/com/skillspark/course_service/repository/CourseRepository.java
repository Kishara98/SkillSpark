package com.skillspark.course_service.repository;

import com.skillspark.course_service.dto.CourseDTO;
import com.skillspark.course_service.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT new com.skillspark.course_service.dto.CourseDTO(c.title, c.description, c.instructor_id) FROM Course c WHERE c.instructor_id = :instructor_id")
    List<CourseDTO> findByInstructorId(@Param("instructor_id") int instructor_id);

    @Query("SELECT new com.skillspark.course_service.dto.CourseDTO(c.title, c.description, c.instructor_id) FROM Course c")
    List<CourseDTO> findAllCourses();
}
