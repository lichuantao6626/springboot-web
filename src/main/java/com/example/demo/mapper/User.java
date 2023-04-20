package com.example.demo.mapper;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定义user表结构对象，涉及的字段和表中的字段个数一致
 */
@Data
@NoArgsConstructor
public class User {
    private Long id;

    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
