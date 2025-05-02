package com.tenpo.demo.infrastructure.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CalculateDTO {

    @NotNull
    @Pattern(regexp = "^[0-9]+$")
    private Integer number1;

    @NotNull
    @Pattern(regexp = "^[0-9]+$")
    private Integer number2;



}
