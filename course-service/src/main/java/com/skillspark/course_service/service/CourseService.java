package com.skillspark.course_service.service;

import com.skillspark.course_service.dto.CourseDTO;
import com.skillspark.course_service.model.Course;
import com.skillspark.course_service.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository repository;

    ModelMapper modelMapper = new ModelMapper();

    // create a new course
    public CourseDTO createCourse(Course course) {
        Course savedCourse = repository.save(course);
        return modelMapper.map(course, CourseDTO.class);
    }

    // get course by course id
    public CourseDTO getCourseById(int id) {
        Optional<Course> course = repository.findById(id);
        if(course.isPresent()) {
            return modelMapper.map(course.get(), CourseDTO.class);
        } else {
            throw new RuntimeException("Course not found under id: " + id);
        }
    }

    // get all course details
    public List<CourseDTO> getAllCourses() {
        List<CourseDTO> coursesList = repository.findAllCourses();
        if(!coursesList.isEmpty()) {
            return coursesList;
        } else {
            throw new RuntimeException("No course available");
        }
    }

    // get course details by instructor id
    public List<CourseDTO> getCourseByInstructorId(int instructor_id) {
        List<CourseDTO> course = repository.findByInstructorId(instructor_id);
        if (!course.isEmpty()) {
            return course;
        } else {
            throw new RuntimeException("Course not found under instructor_id: " + instructor_id);
        }
    }
}
