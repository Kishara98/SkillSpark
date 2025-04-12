package com.skillspark.user_service.repository;

import com.skillspark.user_service.dto.UserDTO;
import com.skillspark.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);

    @Query("SELECT new com.skillspark.user_service.dto.UserDTO(u.name, u.email, u.role) FROM User u WHERE u.role = :role")
    List<UserDTO> findByRole(@Param("role")String role);



}
