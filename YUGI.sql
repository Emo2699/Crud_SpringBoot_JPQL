/*Script para la tabla de carta de yugioh Monstruo*/


CREATE TABLE Monster(
    id_card VARCHAR(20),
    nombre VARCHAR(50) NOT NULL,
    atributo VARCHAR(10) NOT NULL,
    nivel VARCHAR(2) NOT NULL,
    ataque VARCHAR(6) NOT NULL,
    defensa VARCHAR(6) NOT NULL,
    PRIMARY KEY(id_card)
);

SELECT * FROM Monster;

/*Algunas Inserciones*/
INSERT INTO monster VALUES('89943723','elemental hero neos','luz','7','2500','2000');
INSERT INTO monster VALUES('83965310','destiny hero plasma','oscuridad','8','1900','600');
INSERT INTO monster VALUES('23204029','contrast hero chaos','oscuridad','9','3000','2600');
INSERT INTO monster VALUES('37195861','elemental hero ocean','agua','4','1500','1200');
INSERT INTO monster VALUES('21844576','elemental hero avian','viento','4','1000','1000');

COMMIT;