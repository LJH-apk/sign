package com.example.signin.controller;

import com.example.signin.DTO.AccessRequest;
import com.example.signin.DTO.AccessResponse;
import com.example.signin.DTO.FindClassroomRequest;
import com.example.signin.DTO.FindClassroomResponse;
import com.example.signin.entity.Student;
import com.example.signin.service.FindClassroomService;
import com.example.signin.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 刘佳航
 * @Name StudentContorller
 * TODO
 */

@RestController
@RequestMapping("api/students")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private FindClassroomService findClassroomService;

    //根据名字获取学生
    @GetMapping("/find")
    public ResponseEntity<?> findByName(@RequestParam String name){
        List<Student> students = studentService.findByName(name);
        logger.info("查询学生:"+name+"包含"+students+"共"+students.size()+"项");
        return students != null ? ResponseEntity.ok(students) : ResponseEntity.notFound().build();
    }

    //根据名字删除已签到学生
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteStudentByName(@PathVariable String name){
        studentService.deleteByName(name);
        logger.info("删除学生:"+name);
        return ResponseEntity.noContent().build();
    }

    //学生签到
    @PostMapping("/access")
    public ResponseEntity<AccessResponse> handleAccess(@RequestBody AccessRequest request){
        AccessResponse response = studentService.handleAccess(request);
        logger.info(response.getName()+"打卡"+"位置为："+response.getLocation());
        return ResponseEntity.ok(response);
    }

    //查询分配到的教室
    @PostMapping("/queryroom")
    public ResponseEntity<FindClassroomResponse> queryRoom(@RequestBody FindClassroomRequest request){
        logger.info(request.getName()+" 查询分班教室");
        String classroom = findClassroomService.findClassroomByName(request).get(0).getClassroom().toString();
        logger.info("查询到：" + request.getName() + " 同学的教室为：" + classroom);

        //根据查询到分配教室查询教室对应的老师
        String teacher = findClassroomService.findTeacherByClassroom(classroom);
        logger.info("查询到："+ classroom + " 教室的老师为：" + teacher);

        //装载响应信息
        FindClassroomResponse findClassroomResponse = new FindClassroomResponse();
        findClassroomResponse.setClassroom(classroom);
        findClassroomResponse.setTeacher(teacher);
        findClassroomResponse.setName(request.getName());
        findClassroomResponse.setToken(request.getToken());

        return ResponseEntity.ok(findClassroomResponse);
    }

}
