package com.example.carservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TestData {
    private Long id;
    private Long testId;
    private Long questionId;
    private Long answerId;
}
