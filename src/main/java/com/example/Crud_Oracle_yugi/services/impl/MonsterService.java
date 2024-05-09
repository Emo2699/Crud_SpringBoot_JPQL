package com.example.Crud_Oracle_yugi.services.impl;

import com.example.Crud_Oracle_yugi.model.Monster;
import com.example.Crud_Oracle_yugi.repository.MonsterDAO;
import com.example.Crud_Oracle_yugi.services.IMonster;
import jakarta.transaction.Transactional;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class MonsterService implements IMonster {

    //Inyectamos el Repositorio
    @Autowired
    private MonsterDAO repository;


    @Transactional
    @Override
    public List<Monster> getCartas() {
        return repository.getAllMonsters();
    }

    @Transactional
    @Override
    public Monster getMonster(String id) {
        return repository.getMonsterById(id);
    }

    /*Return
    * 1 insercion correcta
    * 0 hay un duplicado
    * -1 hubo un error
    *
    * */
    @Transactional
    @Override
    public int createMonster(Monster monster) {
        try{
            //Antes de agregar un nuevo mosntruo debo comprobar que no exista ya en la BD
            Monster query = getMonster(monster.getId_card());

            if(query == null){
                //No hay registros, lo agrego a la BD
                //this.repository.save(monster);
                this.repository.createNewMonster(monster.getId_card(),
                        monster.getNombre(),
                        monster.getAtributo(),
                        monster.getNivel(),
                        monster.getAtaque(),
                        monster.getDefensa());
                return 1;
            }else{
                //Ya hay un registro, por lo que seria un duplicado
                return 0;
            }
        }catch(Exception e){
            return -1;
        }
    }

    @Transactional
    @Override
    public boolean actualizarMonster(String id, Monster monster) {
        try{
            //Hay que verificar que primero si exista el registro a actualizar en la BD
            Monster query = getMonster(id);

            if(query == null){
                //No se encontro registro en la BD, por lo que no se puede actualizar
                return false;
            }else{
                //Hacemos el update en la BD
                /*Tenemos que pasar primero el id con el que se encontro el registro
                * y ya despues pasamos uno por uno todos los campos del nuevo objeto
                * esto con la finalidad de que si se cambia el ID de la carta no hay problema al actualizar.
                * */
                System.out.println("Se encontro registro en la base de datos");
                System.out.println(id);
                System.out.println(monster.getId_card());
                System.out.println(monster.getNombre());
                System.out.println(monster.getAtributo());
                System.out.println(monster.getNivel());
                System.out.println(monster.getAtaque());
                System.out.println(monster.getDefensa());
                this.repository.updateMonster(id,monster.getId_card(),monster.getNombre(), monster.getAtributo(),
                        monster.getNivel(), monster.getAtaque(), monster.getDefensa());
                return true;
            }


        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteMonster(String id) {
        //Antes de agregar un nuevo mosntruo debo comprobar que no exista ya en la BD
        Monster query = getMonster(id);
        if(query == null){
            //No existe el registro ern la base de datos, no se puede eliminar
            return false;
        }else{
            this.repository.deleteMonsterById(id);
            return true;
        }
    }
}
