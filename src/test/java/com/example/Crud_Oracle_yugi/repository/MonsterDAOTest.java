package com.example.Crud_Oracle_yugi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MonsterDAOTest {
    @Autowired
    private MonsterDAO repository;




    @Test
    void deleteMonsterById() {
        repository.deleteMonsterById("21844576");
        System.out.println("resultado");
    }
}