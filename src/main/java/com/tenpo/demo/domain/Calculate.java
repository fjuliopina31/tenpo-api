package com.tenpo.demo.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calculate {

    @Setter
    @Getter
    private Integer number1;

    @Setter
    @Getter
    private Integer number2;
}
