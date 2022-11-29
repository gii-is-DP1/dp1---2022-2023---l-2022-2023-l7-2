-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');
-- One owner user, named orllabled with passwor 0rll4bl3d
INSERT INTO users(username,password,enabled) VALUES ('orllabled','0rll4bl3d',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'orllabled','owner');

-- one owner user, named rubcasfer2 with passwor rubcasfer2
INSERT INTO users(username,password,enabled) VALUES ('rubcasfer2','rubcasfer2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'rubcasfer2','owner');

-- One owner user, named manbarjim2 with passwor mbj2
INSERT INTO users(username,password,enabled) VALUES ('manbarjim2','mbj2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'manbarjim2','owner');

-- One owner user, named ignplathi with passwor ipt01
INSERT INTO users(username,password,enabled) VALUES ('ignplathi','ipt01',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'ignplathi','owner');

-- One owner user, named ernrivper with passwor ernrivper
INSERT INTO users(username,password,enabled) VALUES ('ernrivper','ernrivper',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'ernrivper','owner');

-- One owner user, named siadan with passwor s14d4n
INSERT INTO users(username,password,enabled) VALUES ('siadan','s14d4n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'siadan','owner');


-- One usuario user, maned usuario with password usuario

INSERT INTO vets(id, first_name,last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets(id, first_name,last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets(id, first_name,last_name) VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets(id, first_name,last_name) VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets(id, first_name,last_name) VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets(id, first_name,last_name) VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');
INSERT INTO types VALUES (7, 'turtle');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (11, 'Orlando', 'Jose', 'Castillo de Alanis 8 .', 'Sevilla', '697777232', 'orllabled');
INSERT INTO owners VALUES (16, 'Ernesto', 'Rivero', 'Guadalupe', 'Sevilla', '687353446', 'ernrivper');
INSERT INTO owners VALUES (12, 'Ruben', 'Casal', 'Calle Doctor Marañón', 'Sevilla', '628776688', 'rubcasfer2');
INSERT INTO owners VALUES (13, 'Manuel', 'Barcia', '1 Street', 'Sevilla', '2222222222', 'manbarjim2');
INSERT INTO owners VALUES (14, 'Ignacio', 'Planas', 'Calle Constitucion, 12', 'Mairena Del Aljarafe', '644480511', 'ignplathi');
INSERT INTO owners VALUES (15, 'Siamion', 'Danko', 'Calle Betis 18', 'Ecija', '666777888', 'siadan');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Dino', '2008-04-08', 3, 11);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (19, 'Iki', '2009-05-18', 2, 16);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Kora', '2019-08-08', 2, 12);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'Chuli', '2010-05-01', 2, 13);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (17, 'Ciclon', '2015-11-08', 2, 14);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (18, 'Kora', '2008-01-05', 1, 15);


INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

-- One usuario user, maned usuario with password usuario

INSERT INTO users(username,password,enabled) VALUES ('usuario','usuario',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'usuario','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES(1,'nombreUsuario','apellidoUsuario','usuario');

INSERT INTO users(username,password,enabled) VALUES ('usuario1','aaa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'usuario1','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES(2,'nombreUsuario1','apellidoUsuario1','usuario1');

INSERT INTO users(username,password,enabled) VALUES ('Pepe','aaa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'Pepe','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES(3,'Pepe','Almuedos','Pepe');

INSERT INTO users(username,password,enabled) VALUES ('Pablo','aaa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'Pablo','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES(4,'Pablo','Kratzer','Pablo');

INSERT INTO users(username,password,enabled) VALUES ('usuario2','usuario2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'usuario2','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES (5,'Pepe','Gonzalez','usuario2');

INSERT INTO users(username,password,enabled) VALUES ('usuario3','usuario3',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'usuario3','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES (6,'Maria','Gonzalez','usuario3');

INSERT INTO users(username,password,enabled) VALUES ('usuario4','usuario4',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (16,'usuario4','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES(7,'Juan','Mendoza','usuario4');

INSERT INTO users(username,password,enabled) VALUES ('usuario5','usuario5',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (17,'usuario5','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES(8,'Marina','Blanco','usuario5');

INSERT INTO users(username,password,enabled) VALUES ('usuario6','usuario6',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (18,'usuario6','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES(9,'Miguel','Farnes','usuario6');


--INSERT INTO jugador(id,color,usuario_id,partida_parchis_id) values (1,0,1,1);
--INSERT INTO jugador(id,color,usuario_id,partida_parchis_id) values (2,2,2,1);
--INSERT INTO jugador(id,color,usuario_id,partida_parchis_id) values (5,2,2,1);
--INSERT INTO jugador(id,color,usuario_id,partida_parchis_id) values (3,0,1,2);
--INSERT INTO jugador(id,color,usuario_id,partida_parchis_id) values (4,1,2,2);

INSERT INTO logro(id,nombre,descripcion,oca_partidas_jugadas) VALUES (1,'Jugador Oca Junior','Juega 10 partidas de la Oca',10);

INSERT INTO partida_oca(id, codigo_partida, duracion, estado, fecha_creacion, max_jugadores, numero_turnos) VALUES
(1, 100, 3, 0, '2013-01-01', 3, 0);
INSERT INTO partida_oca(id, codigo_partida, duracion, estado, fecha_creacion, max_jugadores, numero_turnos) VALUES
(2, 101, 4, 0, '2013-01-01', 2, 0);
INSERT INTO partida_oca(id, codigo_partida, duracion, estado, fecha_creacion, max_jugadores, numero_turnos,tableroURL) VALUES
(3, 102, 4, 0, '2000-12-12', 4, 0,'/resources/images/tableOca.jpg');

INSERT INTO partida_parchis(id, codigo_partida, duracion, estado, fecha_creacion, max_jugadores, numero_turnos) VALUES
(1, 103, 5, 0, '2013-01-03', 4, 0);
INSERT INTO partida_parchis(id, codigo_partida, duracion, estado, fecha_creacion, max_jugadores, numero_turnos) VALUES
(2, 104, 5, 2, '2013-01-03', 4, 0);

INSERT INTO partida_parchis(id,codigo_partida,fecha_creacion,estado,max_jugadores) VALUES (3,105,'2022-07-22',2,2);
INSERT INTO partida_parchis(id,codigo_partida,fecha_creacion,estado,max_jugadores) VALUES (4,106,'2022-07-23',2,2);



INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (1, 1, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (2, 2, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (3, 3, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (4, 4, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (5, 5, 1);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (6, 6, 2);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (7, 7, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (8, 8, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (9, 9, 1);

INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (10, 10, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (11, 11, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (12, 12, 2);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (13, 13, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (14, 14, 1);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (15, 15, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (16, 16, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (17, 17, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (18, 18, 1);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (19, 19, 3);

INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (20, 20, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (21, 21, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (22, 22, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (23, 23, 1);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (24, 24, 0);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (25, 25, 0);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (26, 26, 6);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (27, 27, 1);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (28, 28, 0);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (29, 29, 0);

INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (30, 30, 0);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (31, 31, 4);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (32, 32, 1);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (33, 33, 0);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (34, 34, 0);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (35, 35, 0);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (36, 36, 1);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (37, 37, 0);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (38, 38, 0);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (39, 39, 0);

INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (40, 40, 0);
INSERT INTO casilla_Oca(id, numero, tipo_casilla_oca) VALUES (41, 41, 1);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (42, 42, 7);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (43, 43, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (44, 44, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (45, 45, 1);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (46, 46, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (47, 47, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (48, 48, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (49, 49, 0);

INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (50, 50, 1);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (51, 51, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (52, 52, 5);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (53, 53, 6);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (54, 54, 1);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (55, 55, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (56, 56, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (57, 57, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (58, 58, 8);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (59, 59, 1);

INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (60, 60, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (61, 61, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (62, 62, 0);
INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES (63, 63, 9);

INSERT INTO ficha_oca(id, color, casilla_actual_id) VALUES (1, 0, 1);
INSERT INTO ficha_oca(id, color, casilla_actual_id) VALUES (2, 1, 1);
INSERT INTO ficha_oca(id, color, casilla_actual_id) VALUES (3, 2, 1);
INSERT INTO ficha_oca(id, color, casilla_actual_id) VALUES (4, 3, 1);


INSERT INTO jugador(id, color, es_ganador, fichas_comidas, veces_caido_en_muerte, ficha_oca_id, partida_oca_id, partida_parchis_id, usuario_id) VALUES
                    (6,     0,      FALSE,       0,              0,                 1,              3,      null,                    3);
INSERT INTO jugador(id, color, es_ganador,fichas_comidas,veces_caido_en_muerte,ficha_oca_id,partida_oca_id,partida_parchis_id,usuario_id) VALUES
(7, 1, FALSE, 0, 0, 2, 3, null, 4);
INSERT INTO jugador(id, color, es_ganador,fichas_comidas,veces_caido_en_muerte,ficha_oca_id,partida_oca_id,partida_parchis_id,usuario_id) VALUES
(12, 2, FALSE, 0, 0, 3, 3, null, 8);
INSERT INTO jugador(id, color, es_ganador,fichas_comidas,veces_caido_en_muerte,ficha_oca_id,partida_oca_id,partida_parchis_id,usuario_id) VALUES
(13, 3, FALSE, 0, 0, 4, 3, null, 9);


INSERT INTO jugador(id, color, es_ganador,fichas_comidas,veces_caido_en_muerte,ficha_oca_id,partida_oca_id,partida_parchis_id,usuario_id) VALUES
(8, 0, FALSE, 0, 0, null, null, 1, 1);
INSERT INTO jugador(id, color, es_ganador,fichas_comidas,veces_caido_en_muerte,ficha_oca_id,partida_oca_id,partida_parchis_id,usuario_id) VALUES
(9, 1, FALSE, 0, 0, null, null, 1, 2);
INSERT INTO jugador(id, color, es_ganador,fichas_comidas,veces_caido_en_muerte,ficha_oca_id,partida_oca_id,partida_parchis_id,usuario_id) VALUES
(10, 2, FALSE, 0, 0, null, null, 1, 3);
INSERT INTO jugador(id, color, es_ganador,fichas_comidas,veces_caido_en_muerte,ficha_oca_id,partida_oca_id,partida_parchis_id,usuario_id) VALUES
(11, 3, FALSE, 0, 0, null, null, 2, 4);


INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,1);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,2);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,3);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,4);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,5);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,6);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,7);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,8);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,9);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,10);

INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,11);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,12);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,13);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,14);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,15);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,16);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,17);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,18);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,19);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,20);

INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,21);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,22);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,23);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,24);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,25);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,26);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,27);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,28);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,29);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3,30);

INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 31);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 32);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 33);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 34);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 35);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 36);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 37);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 38);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 39);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 40);

INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 41);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 42);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 43);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 44);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 45);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 46);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 47);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 48);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 49);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 50);

INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 51);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 52);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 53);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 54);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 55);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 56);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 57);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 58);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 59);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 60);

INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 61);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 62);
INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES(3, 63);
