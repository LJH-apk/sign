package com.example.signin.service;


import com.example.signin.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ResetService {
    private final StudentRepository studentRepository;
    private static final Logger logger= LoggerFactory.getLogger(ResetService.class);

    public ResetService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void resetMySQL(){

        //删除所有数据
        studentRepository.deleteAll();
        logger.warn("数据库信息删除");

        //重置MySQL自增主键
        studentRepository.resetAutoIncrementMySQL();
        logger.warn("数据库主键重置");
    }
}
