package com.example.signin.controller;

import com.example.signin.exception.StudentOutOfCapacity;
import com.example.signin.service.GenerateStudentService;
import com.example.signin.service.ResetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dev")
public class DeveloperController {
    private static final Logger log = LoggerFactory.getLogger(DeveloperController.class);
    private final ResetService resetService;
    private final GenerateStudentService generateStudentService;

    public DeveloperController(ResetService resetService, GenerateStudentService generateStudentService) {
        this.resetService = resetService;
        this.generateStudentService = generateStudentService;
    }


    @PostMapping("/reset")
    public ResponseEntity<String> reset(){
        log.warn("数据库重置");
        resetService.resetMySQL();
        return ResponseEntity.ok("数据库重置完成");
    }

    @PostMapping("/generate")
    public ResponseEntity<String>  generate(@RequestParam int count){
        log.warn("数据库信息生成");
        generateStudentService.generateStudent(count);
        return ResponseEntity.ok("信息生成完毕");

    }

    @GetMapping("exception/outofcapacity")
    public String outOfCapacity(){
        log.info("Student out of Capacity 异常测试");
        throw new StudentOutOfCapacity("超出上限");
    }
}
