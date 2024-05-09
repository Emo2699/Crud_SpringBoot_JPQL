package com.example.Crud_Oracle_yugi.services;


import com.example.Crud_Oracle_yugi.model.Monster;

import java.util.List;

public interface IMonster {

    List<Monster> getCartas();
    Monster getMonster(String id);

    int createMonster(Monster monster);

    boolean actualizarMonster(String id, Monster monster);

    boolean deleteMonster(String id);
}
