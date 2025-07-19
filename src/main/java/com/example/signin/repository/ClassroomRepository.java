package com.example.signin.repository;

import com.example.signin.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom,Long> {
    List<Classroom> findByClassroom(String classroom);
}
