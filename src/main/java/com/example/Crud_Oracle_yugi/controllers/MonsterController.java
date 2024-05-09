package com.example.Crud_Oracle_yugi.controllers;


import com.example.Crud_Oracle_yugi.model.Monster;
import com.example.Crud_Oracle_yugi.services.IMonster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/yugi")
public class MonsterController {

    @Autowired
    private IMonster service;

    /*LISTAR CARTAS*/
    @GetMapping("cartas")
    public ResponseEntity<Map<String,Object>> listarCartas(){
        try{
            List<Monster> query = this.service.getCartas();
            Map<String,Object> json = new HashMap<>();

            json.put("data",query);
            json.put("status", HttpStatus.OK);

            return new ResponseEntity<>(json,HttpStatus.OK);
        }catch(Exception e){
            Map<String,Object> json = new HashMap<>();

            json.put("message","Error al obtener las cartas");
            json.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(json,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*ENCONTRAR POR ID*/
    @GetMapping("carta/{id}")
    public ResponseEntity<Map<String,Object>> buscaMonstruo(@PathVariable String id){
        try{
            Monster query = this.service.getMonster(id);
            Map<String,Object> json = new HashMap<>();
            if(query == null){
                json.put("message","No se encontro una carta con ese id");
                json.put("status",HttpStatus.BAD_REQUEST);

                return new ResponseEntity<>(json,HttpStatus.BAD_REQUEST);
            }else{
                json.put("data",query);
                json.put("status",HttpStatus.OK);

                return new ResponseEntity<>(json,HttpStatus.OK);
            }
        }catch(Exception e){
            Map<String,Object> json = new HashMap<>();

            json.put("message","Error al obtener la carta");
            json.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(json,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*AÑADIR CARTA*/
    @PostMapping("agregar")
    public ResponseEntity<Map<String,Object>> agregarCarta(@RequestBody Monster monster){
        try{
            int resultado = this.service.createMonster(monster);
            Map<String,Object> json = new HashMap<>();
            if(resultado == 1){
                json.put("message","Carta agregada correctamente...");
                json.put("status",HttpStatus.OK);
                return new ResponseEntity<>(json,HttpStatus.OK);
            } else if(resultado == 0){
                json.put("message","Ya existe un registro de esa carta....");
                json.put("status",HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(json,HttpStatus.BAD_REQUEST);
            }else{
                json.put("message","Error al agregar la carta....");
                json.put("status",HttpStatus.INTERNAL_SERVER_ERROR);

                return new ResponseEntity<>(json,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(Exception e){
            Map<String,Object> json = new HashMap<>();

            json.put("message","Error al agregar la carta");
            json.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(json,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*ACTUALIZAR CARTA*/
    @PutMapping("actualizar/{id}")
    public ResponseEntity<Map<String,Object>> actualizarInfoCarta(@PathVariable String id, @RequestBody Monster monster) {
        try{
            Map<String,Object> json = new HashMap<>();

            boolean resultado = this.service.actualizarMonster(id,monster);

            if(resultado){
                json.put("message","Carta actualizada correctamente...");
                json.put("status",HttpStatus.OK);
                return new ResponseEntity<>(json,HttpStatus.OK);
            }else{
                json.put("message","Error al actualizar la carta...");
                json.put("status",HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(json,HttpStatus.BAD_REQUEST);
            }

        }catch(Exception e){
            Map<String,Object> json = new HashMap<>();
            json.put("message","Error en la actualizacion");
            json.put("status",HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /*BORRAR CARTA*/
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Map<String,Object>> eliminarMonstruo(@PathVariable String id){
        try{
            boolean result = this.service.deleteMonster(id);
            Map<String,Object> json = new HashMap<>();

            if(result){
                json.put("message","Carta eliminada correctamente...");
                json.put("status",HttpStatus.OK);
                return new ResponseEntity<>(json,HttpStatus.OK);
            }else{
                json.put("message","Error al eliminar la carta");
                json.put("status", HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(json,HttpStatus.BAD_REQUEST);
            }

        }catch(Exception e){
            Map<String,Object> json = new HashMap<>();

            json.put("message","Error al eliminar la carta");
            json.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(json,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
