package com.example.signin.service;

import com.example.signin.DTO.AccessRequest;
import com.example.signin.DTO.FindClassroomRequest;
import com.example.signin.entity.Classroom;
import com.example.signin.entity.Student;
import com.example.signin.repository.ClassroomRepository;
import com.example.signin.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FindClassroomService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<Student> findClassroomByName(FindClassroomRequest request){
        List<Student> list = studentRepository.findByName(request.getName());
        return list
                .stream()
                .filter(student -> {
                    String classroom = student.getClassroom();
                    return classroom != null && !classroom.equalsIgnoreCase("none");
                })
                .collect(Collectors.toList());
    }

    public String findTeacherByClassroom(String classroom){
         return classroomRepository.findByClassroom(classroom).get(0).getTeacher().toString();
    }
}
