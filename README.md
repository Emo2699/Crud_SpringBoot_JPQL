# Crud con JPQL y Oracle
En este proyecto se realizó una aplicación back end con Spring Boot la cual realiza todas las operaciones de un Crud básico con temática de cartas de monstruo de Yu-Gi-Oh

La aplicacion se conectó con una base de datos Oracle para la persistencia de datos.

Finalmente, la particularidad de este proyecto recae en el uso de JPQL para realizar las operaciones de un Crud de una tabla en la base de datos.


## JPQL
**Java Persistence Query Languaje** (JPQL) es un lenguaje de consulta orientado a objetos utilizado en Java Persistence API (JPA) para realizar consultas a bases de datos relacionales. 
JPQL es similar a SQL (Structured Query Language), pero está diseñado específicamente para trabajar con entidades y objetos en un entorno de persistencia.

## ¿Por qué usarlo?
En ocasiones, cuando un proyecto crece y escala a algo mucho más grande, ya es una necesidad realizar consultas mas complejas y personalizadas a las tablas dentro de nuestra base de datos y debido 
a esta necesidad, las operaciones por defecto de los repositorios ya no son suficientes. Sin embargo, gracias a JPQL es posible realizar consultas personalizadas a la base de datos sin la necesidad de
escribir realmente sentencias SQL, en su lugar, se busca mantener la idea de que se esta trabajando con objetos, por lo que las consultas JPQL están orientadaa a atributos de objetos.

## ¿Cómo funciona?
Para hacer uso de sentencias JPQL dentro del proyecto se requiere tener la dependencia de Spring JPA instalada.

Una vez teniendo esta dependencia, las consultas personalizadas se declaran dentro de la interfaz del repositorio, la cual extiende de algún repositorio existente dentro del Spring JPA, tal como
*CrudRepository<>* o *JpaRepository<>*

Dentro de este archivo se usa la anotacion de JPA repository *@Query()*. Dentro de esta anotacion se escribe la sentencia JPQL que se asociara al metodo con el que se mandará a ejecutar.

## Ejemplo
Una consulta JPQL básica para recuperar todos los registros de una tabla es la siguiente.
```Java
@Query("select m from Monster m")
List<Monster> getAllMonsters();
```

> [!NOTE]
> Notese que es muy similar a una sentencia SQL, sin embargo, mantiene la naturaleza de POO al tratar a los registros como objetos.
>

Cuando se requiere pasar un valor a nuestra consulta JPQL, existen dos formasde hacerlo, la primera es por medio de la numeracion de los parametros del metodo:
```Java
    @Query("select m from Monster m where m.id_card = ?1")
    Monster getMonsterById(String id);
```
> [!NOTE]
> En caso de usar mas variables en la sentencia JPQL, se seguirá usando la nomeclatura "?n " donde n representa a la posicion del parametro en el metodo.

La segunda forma de pasarle valores a la sentencia JPQL es por medio de la anotacion *@Param()*. Dentro de esta anotacion se especifica el nombre de la variable en la sentencia 
JPQL a la que hace referencia el parametro en el metodo.
```Java
    @Query("select m from Monster m where m.id_card = :id")
    Monster getMonsterById(@Param("id") String id);
```

**Normalmente las consultas JPQL se utilizan para la consulta, modificación y elimnacion de registros en la Base de datos. Sin embargo en este proyecto se uso JPQL para insertar 
un registro en la Base de Datos.**

> [!WARNING]
> Las sentencias JPQL son muy sensibles a mayusculas, minusculas y espacios dentro de la sentencia. Por lo que se recomienda tener mucha atención en como
> se declaran el nombre de la tabla o campos dentro de la Base de Datos a la que se hace referencia.
