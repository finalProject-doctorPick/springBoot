package com.example.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserSignupDTO {

    @NotEmpty(message = "회원가입 시 이메일은 필수입력 입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식을 맞춰야합니다")
    private String userEmail;

    @NotEmpty(message = "회원가입 시 비밀번호는 필수입력 입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{7,16}$", message = "비밀번호는 영문+숫자+특수문자를 포함한 8~20자여야 합니다")
    private String userPwd;

    @NotEmpty(message = "회원가입 시 이름은 필수입력 입니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣\\\\s]{2,15}", message = "이름은 영문자, 한글, 공백포함 2글자부터 15글자까지 가능합니다.")
    private String userName;

    @NotEmpty(message = "회원가입 시 생년월일은 필수입력 입니다.")
    @Pattern(regexp = "^[0-9]{8}$", message = "생년월일은 8자리의 숫자로 입력해야 합니다.")
    private String userBirth;

    @NotEmpty(message = "회원가입 시 성별은 필수입력 입니다.")

    private String userSex;

    private String userTel;

    private String userAddrMain;

    private String userAddrDetail;

    private String userAuth;

}