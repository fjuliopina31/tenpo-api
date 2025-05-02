package com.tenpo.demo.infrastructure.db;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "call_history")
public class ApiCallHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private LocalDateTime timestamp;

    @Setter
    @Getter
    private String endpoint;

    @Column(columnDefinition = "TEXT")
    @Setter
    @Getter
    private String parameters;

    @Column(columnDefinition = "TEXT")
    @Setter
    @Getter
    private String response;

    @Setter
    @Getter
    private boolean error;

    public ApiCallHistoryEntity() {}

    public ApiCallHistoryEntity(LocalDateTime timestamp, String endpoint, String parameters, String response, boolean error) {
        this.timestamp = timestamp;
        this.endpoint = endpoint;
        this.parameters = parameters;
        this.response = response;
        this.error = error;
    }

    @Override
    public String toString() {
        return "ApiCallHistoryEntity{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", endpoint='" + endpoint + '\'' +
                ", parameters='" + parameters + '\'' +
                ", response='" + response + '\'' +
                ", error=" + error +
                '}';
    }
}
