package com.example.DemoProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorObject {
    private OffsetDateTime offsetDateTime;
    private String message;
    private String code;
}
