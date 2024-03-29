package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

	private Integer userId;
	
    private String userEmail;

    private String userPwd;

    private String userName;

    private String userBirth;

    private String userSex;

    private String userTel;

    private String userAddrMain;

    private String userAddrDetail;

    private String userAuth;

}