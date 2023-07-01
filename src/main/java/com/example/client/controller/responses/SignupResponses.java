package com.example.client.controller.responses;

public interface SignupResponses {
    String USERNAME_INVALID = "Username has invalid character!";
    String USERNAME_EXIST = "This user name already exist!";
    String PASSWORD_SHORT = "Password must contain at least six characters!";
    String PASSWORD_LOWER_CASE = "Password must contain at least one lowercase letter!";
    String PASSWORD_UPPER_CASE = "Password must contain at least one uppercase letter!";
    String PASSWORD_DIGIT = "Password must contain at least one digit!";
    String PASSWORD_START = "Password must contain at least one character not being letter and digit!";
    String EMAIL_VALID = "Email format is not valid!";
    String EMAIL_EXIST = "This email already exist!";

}
