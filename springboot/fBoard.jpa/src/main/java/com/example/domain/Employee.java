package com.example.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data		// Lombok
@Entity		// DTO
@Table(name="emp")
public class Employee {
    @Id
    private Integer empno;
    private String ename;

    @Column(nullable = true)
    private Integer mgr;

    @Column(nullable = true)
    private Date hiredate;

    @Column(nullable = true)
    private Integer sal;

    @Column(nullable = true)
    private Integer comm;

    @Column(nullable = true)
    private String job;

    @ManyToOne(optional = false)
    @JoinColumn(name = "deptno")
    private Department dept;
}

