package com.example.signin.service;

import com.example.signin.DTO.AccessRequest;
import com.example.signin.DTO.AccessResponse;
import com.example.signin.entity.Student;
import com.example.signin.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class ChooseClassroomService {
    //初始化待分配教室
    private static final List<String> CLASSROOM = Arrays.asList(
            "Y402", "Y403", "Y404", "Y405",
            "Y406", "Y407", "Y408", "Y409"
    );

    //初始化随机数对象
    private static final Random random = new Random();

    public static AccessResponse chooseClassroom(AccessRequest request, Student inputStudent, int index, StudentRepository studentRepository) {
        Map<String, Integer> classroomCounts = CLASSROOM
                .stream()
                .collect(Collectors.toMap(
                        classroom -> classroom,
                        classroom -> {
                            List<Student> targetClassroomStudents = studentRepository.findByClassroom(classroom);
                            return targetClassroomStudents.size();
                        }
                ));
        boolean sameAllRoom = AllocationToolService.check(classroomCounts);

        List<Student> students = studentRepository.findByToken(request.getToken());
        List<Long> id =  students
                .stream()
                .filter(student -> student.getAccessCount() == 4
                )
                .map(Student::getId)
                .collect(Collectors.toList());
        if (sameAllRoom){
            if (index == 5){
                inputStudent.setName(request.getName());
                inputStudent.setLocation(request.getLocation());
                inputStudent.setTime(LocalDateTime.now());
                inputStudent.setClassroom(CLASSROOM.get(random.nextInt(CLASSROOM.size())));
                inputStudent.setAccessCount(5);
                inputStudent.setToken(request.getToken());
                studentRepository.save(inputStudent);
                return new AccessResponse(inputStudent, false,true);
            }
            else {
                inputStudent.setId(id.get(0));
                inputStudent.setName(request.getName());
                inputStudent.setLocation(request.getLocation());
                inputStudent.setTime(LocalDateTime.now());
                inputStudent.setClassroom(CLASSROOM.get(random.nextInt(CLASSROOM.size())));
                inputStudent.setAccessCount(5);
                inputStudent.setToken(request.getToken());
                studentRepository.save(inputStudent);
                return new AccessResponse(inputStudent, false,true);
            }
        }
        List<String> availableClassroom = AllocationToolService
                .filteredMap(classroomCounts)
                .keySet()
                .stream()
                .collect(Collectors.toList());
        if (index == 5){
            inputStudent.setName(request.getName());
            inputStudent.setLocation(request.getLocation());
            inputStudent.setTime(LocalDateTime.now());
            inputStudent.setClassroom(availableClassroom.get(random.nextInt(availableClassroom.size())));
            inputStudent.setAccessCount(5);
            inputStudent.setToken(request.getToken());
            studentRepository.save(inputStudent);
            return new AccessResponse(inputStudent, false,true);
        }
        else {
            inputStudent.setId(id.get(0));
            inputStudent.setName(request.getName());
            inputStudent.setLocation(request.getLocation());
            inputStudent.setTime(LocalDateTime.now());
            inputStudent.setClassroom(availableClassroom.get(random.nextInt(availableClassroom.size())));
            inputStudent.setAccessCount(5);
            inputStudent.setToken(request.getToken());
            studentRepository.save(inputStudent);
            return new AccessResponse(inputStudent, false,true);
        }
    }
}
