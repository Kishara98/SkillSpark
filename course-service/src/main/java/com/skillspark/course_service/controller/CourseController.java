package com.skillspark.course_service.controller;

import com.skillspark.course_service.dto.CourseDTO;
import com.skillspark.course_service.model.Course;
import com.skillspark.course_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    CourseService service;

    @PostMapping("/")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody Course course) {
        return new ResponseEntity<>(service.createCourse(course), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseByCourseId(@PathVariable int id) {
        CourseDTO course = service.getCourseById(id);
        if(course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> course = service.getAllCourses();
        if(!course.isEmpty()) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/instructor/{instructor_id}")
    public ResponseEntity<List<CourseDTO>> getCoursesByInstructorId(@PathVariable int instructor_id) {
        List<CourseDTO> course = service.getCourseByInstructorId(instructor_id);
        if(course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
