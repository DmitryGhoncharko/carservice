package com.example.carservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Test {
    private Long id;
    private String name;
}
