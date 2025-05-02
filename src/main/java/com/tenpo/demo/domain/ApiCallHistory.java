package com.tenpo.demo.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiCallHistory {

    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private LocalDateTime timestamp;

    @Setter
    @Getter
    private String endpoint;

    @Setter
    @Getter
    private String parameters;

    @Setter
    @Getter
    private String response;

    @Setter
    @Getter
    private boolean error;

    @Override
    public String toString() {
        return "ApiCallHistory{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", endpoint='" + endpoint + '\'' +
                ", parameters='" + parameters + '\'' +
                ", response='" + response + '\'' +
                ", error=" + error +
                '}';
    }
}
