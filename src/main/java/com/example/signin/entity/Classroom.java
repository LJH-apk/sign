package com.example.signin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String classroom;

    @Column(nullable = false)
    private String teacher;

    public Classroom() {

    }

    public Classroom(String classroom, String teacher) {
        this.classroom = classroom;
        this.teacher = teacher;
    }

    //Getter and Setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
