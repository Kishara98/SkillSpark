package com.skillspark.enrollment_service.controller;

import com.skillspark.enrollment_service.dto.EnrollmentDTO;
import com.skillspark.enrollment_service.model.Enrollment;
import com.skillspark.enrollment_service.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollment")
public class EnrollmentController {

    @Autowired
    EnrollmentService service;

    @PostMapping("/")
    public ResponseEntity<EnrollmentDTO> enrollStudentToaCourse(@RequestBody Enrollment enrollment) {
        return new ResponseEntity<>(service.enrollStudentToaCourse(enrollment), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollmentsForAStudent(@PathVariable int userId) {
        return new ResponseEntity<>(service.getAllEnrollmentsForAStudent(userId), HttpStatus.OK);
    }
    @GetMapping("/course")
    public ResponseEntity<List<EnrollmentDTO>> getAllStudentEnrollmentsForACourse(@RequestParam int courseId) {
        return new ResponseEntity(service.getAllStudentEnrollmentsForACourse(courseId), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> removeEnrollment(@RequestParam int userId, @RequestParam int courseId) {
        service.removeStudentEnrollment(userId, courseId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
