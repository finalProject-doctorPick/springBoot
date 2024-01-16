package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
    private Integer roleId;

    private String roles;

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", name='" + roles + '\'' +
                '}';
    }

}