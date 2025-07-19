package com.example.signin.service;

import com.example.signin.entity.Student;
import com.example.signin.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Transactional
public class GenerateStudentService {
    private final StudentRepository studentRepository;

    //创建姓氏列表
    private static final String[] SINGLE_SURNAMES = {
            "李", "王", "张", "刘", "陈", "杨", "赵", "黄", "周", "吴",
            "徐", "孙", "胡", "尤", "高", "林", "何", "郭", "马", "罗",
            "梁", "宋", "郑", "谢", "韩", "唐", "冯", "于", "董", "萧",
            "程", "曹", "袁", "邓", "许", "傅", "沈", "曾", "彭", "吕",
            "苏", "卢", "蒋", "蔡", "贾", "丁", "魏", "杨", "叶", "耿",
            "余", "潘", "杜", "延", "夏", "钟", "汪", "田", "任", "姜",
            "范", "方", "石", "姚", "谭", "廖", "邹", "熊", "金", "陆",
            "郝", "孔", "白", "崔", "康", "毛", "熊", "秦", "江", "史"
    };
    // 名字常用字（100个）
    private static final String[] NAME_CHARACTERS = {
            "伟", "芳", "娜", "秀", "英", "敏", "静", "丽", "强", "磊",
            "军", "洋", "勇", "艳", "杰", "娟", "涛", "明", "超",
            "霞", "平", "刚", "梓", "文", "华", "建",  "健", "雪",
            "倩", "旭", "宁", "婷", "晨", "誉", "灿", "萱", "晨",
            "瑞", "宇", "鑫", "俊", "敏", "慧", "琳", "丹",
            "浩", "婷", "旭", "帆", "欣", "博", "逸", "思", "睿", "嘉",
            "哲", "乐", "泽", "沐", "梓", "艺", "诗", "辰", "奕",
            "然", "轩", "萱", "琪", "皓", "悦", "妍", "宸", "昊", "彤",
            "怡", "婵", "冉", "欣", "添", "泽", "忱", "玥", "雯", "昭"
    };
    //教室列表
    private static final String[] LOCATION = {
            "Y402","Y403","Y404","405","Y406","Y407","Y408","Y409",
            "Z504","Z503","Z502","S402","S401","Z302","Z301","天台",
            "剧院"
    };
    //初始化随机数对象
    private static final Random random = new Random();

    private static final Logger logger = LoggerFactory.getLogger(GenerateStudentService.class);

    public GenerateStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void generateStudent(int count){
        for (int i = 0; i < 140 ; i++){
            int j = 1;
            Student student = new Student();
            String name = generateName();
            String token = student.getToken();
            while (j <= count){
                Student newStudent = new Student();
                newStudent.setName(name);
                newStudent.setToken(token);
                newStudent.setTime(LocalDateTime.now());
                newStudent.setLocation(LOCATION[random.nextInt(LOCATION.length)]);
                newStudent.setAccessCount(j);
                newStudent.setClassroom("none");
                logger.info("创建信息 姓名:"+name+"第"+j+"条");
                studentRepository.save(newStudent);
                j++;
            }
            int index = i + 1;
            logger.info("创建信息，第"+ index + "个人");
        }
    }

    public static String generateName(){
        // 单姓 + 双名
        String surname = SINGLE_SURNAMES[random.nextInt(SINGLE_SURNAMES.length)];
        String firstGiven = NAME_CHARACTERS[random.nextInt(NAME_CHARACTERS.length)];
        String secondGiven;
        do {
            secondGiven = NAME_CHARACTERS[random.nextInt(NAME_CHARACTERS.length)];
        } while (firstGiven.equals(secondGiven)); // 确保名字两个字不相同
        return surname + firstGiven + secondGiven;
    }
}
