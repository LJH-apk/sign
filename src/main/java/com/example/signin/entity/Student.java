package com.example.signin.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, unique = true, length = 36)
    private String token;

    @Column(nullable = false)
    private String classroom;

    //访问计数器
    private int accessCount;

    public Student() {
        //自动生成唯一token
        this.token = UUID.randomUUID().toString();
    }

    public Student(Long id, String name, LocalDateTime time, String location) {
        this();
        this.id = id;
        this.name = name;
        this.time = time;
        this.location = location;
    }

    //Setter和Getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClassroom() {
        return classroom;
    }
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    //访问次数增加
    public void increaseAccessCount(){
        this.accessCount++;
    }

}
