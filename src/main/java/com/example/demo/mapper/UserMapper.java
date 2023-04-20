package com.example.demo.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 配置mybatis  通过sql来增删改查表的sql
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    List<User> findByName(@Param("name") String name);

    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Update("UPDATE USER SET age=#{age} WHERE name=#{name}")
    void update(User user);

    @Delete("DELETE FROM USER WHERE id =#{id}")
    void delete(Long id);
}
