package com.jd.graduation.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserChangeInfoDTO {
    private String name;
    private String sex;
    private String idCard;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
}