package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderCreateDTO {
    @NotNull
    private List<Integer> cartIds;
    @NotNull
    private Integer addressId;
}