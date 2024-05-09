package com.example.Crud_Oracle_yugi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="MONSTER")
public class Monster implements Serializable {

    @Id
    @Column(name="ID_CARD")
    private String id_card;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="ATRIBUTO")
    private String atributo;

    @Column(name="NIVEL")
    private String nivel;

    @Column(name="ATAQUE")
    private String ataque;

    @Column(name="DEFENSA")
    private String defensa;
}
