package com.example.signin.DTO;

import com.example.signin.entity.Student;

import java.time.LocalDateTime;

public class AccessResponse {
    private Long id;
    private String name;
    private LocalDateTime time;
    private String location;
    private String token;
    private boolean newUser;
    private int accessCount;
    private String classroom;
    private boolean success;

    // 构造器
    public AccessResponse(Student student, boolean newUser,boolean success) {
        this.id = student.getId();
        this.name = student.getName();
        this.time = student.getTime();
        this.location = student.getLocation();
        this.token = student.getToken();
        this.newUser = newUser;
        this.accessCount = student.getAccessCount();
        this.classroom = student.getClassroom();
        this.success = success;
    }

    // Getter/Setter
    public Long getId() { return id; }
    public String getName() { return name; }
    public LocalDateTime getTime() { return time; }
    public String getLocation() { return location; }
    public String getToken() { return token; }
    public boolean isNewUser() { return newUser; }
    public int getAccessCount() { return accessCount; }
    public String getClassroom() { return classroom; }
    public boolean getSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}
