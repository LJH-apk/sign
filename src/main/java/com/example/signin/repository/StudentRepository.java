package com.example.signin.repository;

import com.example.signin.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);
    void deleteByName(String nam);
    List<Student> findByToken(String token);
    List<Student> findByClassroom(String classroom);

    //开发者功能
    @Modifying
    @Query(value = "ALTER TABLE students AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrementMySQL();

}
