package com.example.Crud_Oracle_yugi.repository;

import com.example.Crud_Oracle_yugi.model.Monster;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonsterDAO extends CrudRepository<Monster,String> {

    /*Aqui van las Queries*/


    /*LISTADO DE REGISTROS...*/
    @Query("select m from Monster m")
    List<Monster> getAllMonsters();

    /*OBTENCION DE UN SOLO REGISTRO...*/
    @Query("select m from Monster m where m.id_card = ?1")
    Monster getMonsterById(String id);



    /*INSERCION DE UN REGISTRO....*/

    @Transactional
    @Modifying
    @Query("INSERT INTO Monster (id_card, nombre, atributo, nivel, ataque, defensa) VALUES (:id, :nombre, :atributo, :nivel, :ataque, :defensa)")
    void createNewMonster(@Param("id")String id,@Param("nombre")String nombre,@Param("atributo")String atributo,
                       @Param("nivel")String nivel,@Param("ataque")String ataque,@Param("defensa")String defensa);



    /*ACTUALIZADO DE UN REGISTRO
    * En estas consultas de JPQL el query es muy sensible a mayusculas o minusculas y cualquier error en espacios
    * por lo que hay que cuidar muy bien eso para evitar errores de sintaxis
    * se recomienda usar los nombres de los atributos tal cual aparecen en la BD
    *
    * */
    @Modifying
    @Query("UPDATE Monster m SET m.id_card = :id, m.nombre = :nombre, m.atributo = :atributo, m.nivel = :nivel, m.ataque = :ataque, m.defensa = :defensa WHERE m.id_card = :id_registrado")
    void updateMonster(@Param("id_registrado")String id_registrado, @Param("id") String id,@Param("nombre")String nombre,
                       @Param("atributo")String atributo,@Param("nivel")String nivel,@Param("ataque")String ataque,@Param("defensa")String defensa);


    /*
    @Modifying
    @Query(value = "UPDATE MONSTER SET id_card=?2, nombre=?3,atributo=?4,nivel=?5,ataque=?6,defensa=?7" +
            "WHERE id_card=?1",
            nativeQuery = true)
    void updateMonster(String id_registrado, String id, String nombre, String atributo, String nivel, String ataque, String defensa);
    */

    /*ELIMINACION DE UN REGISTRO...
    *
    * Notas: Aqui el nombre de la tabla debe ser exactamente igual a como se construyo en la BD
    * ya que ... si esta en minusculas o mayusculas, no encuentra la referencia
    *
    * */
    @Transactional
    @Modifying
    @Query("DELETE FROM Monster m WHERE m.id_card = :id")
    void deleteMonsterById(@Param("id") String id);


}
