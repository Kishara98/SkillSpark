package com.skillspark.enrollment_service.service;

import com.skillspark.enrollment_service.dto.EnrollmentDTO;
import com.skillspark.enrollment_service.model.Enrollment;
import com.skillspark.enrollment_service.repository.EnrollmentRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepository repository;

    ModelMapper modelMapper = new ModelMapper();

    // enroll students to course
    public EnrollmentDTO enrollStudentToaCourse(Enrollment enrollment) {
        enrollment.setEnrollmentDate(LocalDateTime.now());
        return modelMapper.map(repository.save(enrollment), EnrollmentDTO.class);
    }

    // get all course enrollments for a single user
    public List<EnrollmentDTO> getAllEnrollmentsForAStudent(Integer userId) {
        return repository.findByUserId(userId);
    }

    // get all student enrollments for a single course
    public List<EnrollmentDTO> getAllStudentEnrollmentsForACourse(int courseId) {
        return repository.findByCourseId(courseId);
    }

    // remove student enrollment from a course
    @Transactional
    public void removeStudentEnrollment(int userId, int courseId) {
        repository.deleteByUserIdAndCourseId(userId, courseId);
    }

}
