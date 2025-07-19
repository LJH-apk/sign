package com.example.signin.service;

import com.example.signin.DTO.AccessRequest;
import com.example.signin.DTO.AccessResponse;
import com.example.signin.entity.Student;
import com.example.signin.exception.ResourceNotFoundException;
import com.example.signin.exception.NotName;
import com.example.signin.repository.StudentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class StudentService {
    //初始化日志
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public AccessResponse handleAccess(AccessRequest request) {


        // 检查是否有有效的token
        if (request.getToken() != null && !request.getToken().isBlank()) {
            List<Student> existingStudent = studentRepository.findByToken(request.getToken());
            List<Student> checkName = studentRepository.findByName(request.getName());
            try {
                if (checkName.isEmpty()) {
                    throw new NotName("未查找到名字");
                }
            } catch (NotName e) {
                logger.warn("未查找到名字，新创建访问记录");
                // 首次访问：创建新记录
                Student newStudent = new Student();
                newStudent.setName(request.getName());
                newStudent.setLocation(request.getLocation());
                newStudent.setTime(LocalDateTime.now());
                newStudent.increaseAccessCount();
                newStudent.setClassroom("none");
                newStudent = studentRepository.save(newStudent);
                return new AccessResponse(newStudent, true,true);
            }
            if (existingStudent.isEmpty() == false) {
                //更新访问
                int index = existingStudent.size() - 1;
                int accessCount = existingStudent.get(index).getAccessCount() + 1;
                Student student = new Student();
                if (accessCount >= 5){
                    return ChooseClassroomService.chooseClassroom(request,student,accessCount,studentRepository);
                }
                student.setName(request.getName());
                student.setLocation(request.getLocation());
                student.setTime(LocalDateTime.now());
                student.setAccessCount(accessCount);
                student.setToken(existingStudent.get(index).getToken());
                logger.info(request.getName()+"第"+accessCount+"次打卡");
                student.setClassroom("none");
                student = studentRepository.save(student);

                return new AccessResponse(student, false,true);
            }
        }
        // 首次访问：创建新记录
        logger.info("首次打卡，姓名："+request.getName());
        Student newStudent = new Student();
        newStudent.setName(request.getName());
        newStudent.setLocation(request.getLocation());
        newStudent.setTime(LocalDateTime.now());
        newStudent.increaseAccessCount();
        newStudent.setClassroom("none");
        newStudent = studentRepository.save(newStudent);
        return new AccessResponse(newStudent, true,true);
    }

    //根据姓名查询
    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    //根据名字删除
    public void deleteByName(String name) {
        List<Student> students = studentRepository.findByName(name);
        if (students.isEmpty()) {
            throw new ResourceNotFoundException("Student with name " + name + " not found");
        }
        for (Student student : students) {
            studentRepository.deleteById(student.getId());
        }
        studentRepository.deleteByName(name);
    }

    //查询所有学生
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

}
