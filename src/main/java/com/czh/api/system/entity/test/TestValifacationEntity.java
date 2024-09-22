package com.czh.api.system.entity.test;

import com.czh.api.common.validaion.constraint.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestValifacationEntity {

    @NotNull
    private Integer id;
    @NotBlank
    private String testName;

    @Email
    private String email;

    @PhoneNumber
    private String phone;
}
