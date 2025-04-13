package com.skillspark.enrollment_service.repository;

import com.skillspark.enrollment_service.dto.EnrollmentDTO;
import com.skillspark.enrollment_service.model.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {


    @Query("SELECT new com.skillspark.enrollment_service.dto.EnrollmentDTO(e.userId, e.courseId, e.enrollmentDate) FROM Enrollment e WHERE e.userId = :userId")
    List<EnrollmentDTO> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT new com.skillspark.enrollment_service.dto.EnrollmentDTO(e.userId, e.courseId, e.enrollmentDate) FROM Enrollment e WHERE e.courseId = :courseId")
    List<EnrollmentDTO> findByCourseId(@Param("courseId")int courseId);

    @Modifying
    @Query("DELETE FROM Enrollment e WHERE e.userId = :userId AND e.courseId = :courseId")
    void deleteByUserIdAndCourseId(@Param("userId") int userId, @Param("courseId") int courseId);
}
