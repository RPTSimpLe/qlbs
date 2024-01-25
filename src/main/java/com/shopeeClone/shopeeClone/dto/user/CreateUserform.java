package com.shopeeClone.shopeeClone.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopeeClone.shopeeClone.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class CreateUserform {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String password;
    private String roleId;
}
