package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="roles")
@NoArgsConstructor
@Setter
@Getter
public class Role {
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