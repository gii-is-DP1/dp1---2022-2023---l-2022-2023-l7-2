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

-- One usuario user, named usuario with password usuario

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

INSERT INTO users(username,password,enabled) VALUES ('Gonzalez','usuario2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'Gonzalez','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES (5,'Pepe','Gonzalez','Gonzalez');

INSERT INTO users(username,password,enabled) VALUES ('Maria','usuario3',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'Maria','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES (6,'Maria','Gonzalez','Maria');

INSERT INTO users(username,password,enabled) VALUES ('Juan','usuario4',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (16,'Juan','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES(7,'Juan','Mendoza','Juan');

INSERT INTO users(username,password,enabled) VALUES ('Marina','usuario5',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (17,'Marina','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES(8,'Marina','Blanco','Marina');

INSERT INTO users(username,password,enabled) VALUES ('Miguel','usuario6',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (18,'Miguel','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES(9,'Miguel','Farnes','Miguel');

INSERT INTO users(username,password,enabled) VALUES ('aaaaa','aaaaa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (19,'aaaaa','jugador');
INSERT INTO usuarios(id,nombre,apellido,username) VALUES(10,'NoJugador','NoJugador','aaaaa');


--Solicitudes 

INSERT INTO solicitud (id,usuario_invitado_id,usuario_solicitud_id,estado) VALUES (1,3,4,0);
INSERT INTO solicitud (id,usuario_invitado_id,usuario_solicitud_id,estado) VALUES (2,3,7,0);
INSERT INTO solicitud (id,usuario_invitado_id,usuario_solicitud_id,estado) VALUES (3,3,2,0);
INSERT INTO solicitud (id,usuario_invitado_id,usuario_solicitud_id,estado) VALUES (4,3,1,1);
INSERT INTO solicitud (id,usuario_invitado_id,usuario_solicitud_id,estado) VALUES (5,3,5,2);
INSERT INTO solicitud (id,usuario_invitado_id,usuario_solicitud_id,estado) VALUES (6,3,6,3);


--INSERT INTO jugador(id,color,usuario_id,partida_parchis_id) values (1,0,1,1);
--INSERT INTO jugador(id,color,usuario_id,partida_parchis_id) values (2,2,2,1);
--INSERT INTO jugador(id,color,usuario_id,partida_parchis_id) values (5,2,2,1);
--INSERT INTO jugador(id,color,usuario_id,partida_parchis_id) values (3,0,1,2);
--INSERT INTO jugador(id,color,usuario_id,partida_parchis_id) values (4,1,2,2);

INSERT INTO logro(id,nombre,descripcion,oca_partidas_jugadas) VALUES (1,'Jugador Oca Junior','Juega 5 partidas de la Oca',5);
INSERT INTO logro(id,nombre,descripcion,oca_partidas_jugadas) VALUES (2,'Jugador Oca Senior','Juega 10 partidas de la Oca',15);
INSERT INTO logro(id,nombre,descripcion, parchis_partidas_jugadas) VALUES (3,'Tu primerita partida parchis','Juega tu primera partida del Parchis',1);
INSERT INTO logro(id,nombre,descripcion, oca_partidas_jugadas) VALUES (4,'Tu primerita partida oca','Juega tu primera partida de la Oca',1);

INSERT INTO partida_oca(id, codigo_partida, duracion, estado, fecha_creacion, max_jugadores, color_jugador_actual) VALUES
(1, 100, 3, 2, '2022-12-05', 3, 0),
(2, 101, 4, 2, '2013-12-02', 2, 0),
(3, 102, 4, 0, '2023-01-10', 4, 0),
(4, 109, 4, 2, '2023-01-10', 2, 0),
(5, 110, 4, 2, '2023-01-10', 2, 0),
(6, 111, 4, 2, '2023-01-10', 2, 0);


INSERT INTO partida_parchis(id, codigo_partida, duracion, estado, fecha_creacion, max_jugadores, color_jugador_actual, dado, tirada, veces_sacado6,	ULTIMO_SACADO6) VALUES
(1, 103, 5, 1, '2022-12-25', 4, 1, null, 0, 0, 0),
(2, 104, 5, 2, '2022-12-24', 4, 0, null, 0, 0, 0),
(3, 105, 10, 2, '2022-12-23', 3, 0, null, 0, 0, 0),
(4, 106, 20, 2, '2022-12-22', 2, 0, null, 0, 0, 0),
(5, 107, 7, 2, '2022-12-22', 2, 0, null, 0, 0, 0),
(6, 108, 8, 2, '2022-12-22', 2, 0, null, 0, 0, 0);




 INSERT INTO casilla_oca(id, numero, tipo_casilla_oca) VALUES 
 (1, 1, 0),(2, 2, 0),(3, 3, 0),(4, 4, 0),(5, 5, 1),(6, 6, 2),(7, 7, 0),(8, 8, 0),(9, 9, 1),(10, 10, 0),
 (11, 11, 0),(12, 12, 2),(13, 13, 0),(14, 14, 1),(15, 15, 0),(16, 16, 0),(17, 17, 0),(18, 18, 1),(19, 19, 3),(20, 20, 0),
 (21, 21, 0),(22, 22, 0),(23, 23, 1),(24, 24, 0),(25, 25, 0),(26, 26, 6),(27, 27, 1),(28, 28, 0),(29, 29, 0),(30, 30, 0),
 (31, 31, 4),(32, 32, 1),(33, 33, 0),(34, 34, 0),(35, 35, 0),(36, 36, 1),(37, 37, 0),(38, 38, 0),(39, 39, 0),(40, 40, 0),
 (41, 41, 1),(42, 42, 7),(43, 43, 0),(44, 44, 0),(45, 45, 1),(46, 46, 0),(47, 47, 0),(48, 48, 0),(49, 49, 0),(50, 50, 1),
 (51, 51, 0),(52, 52, 5),(53, 53, 6),(54, 54, 1),(55, 55, 0),(56, 56, 0),(57, 57, 0),(58, 58, 8),(59, 59, 1),(60, 60, 0),
 (61, 61, 0),(62, 62, 0),(63, 63, 9);

INSERT INTO ficha_oca(id, color, casilla_actual_id) VALUES (1, 0, 1);
INSERT INTO ficha_oca(id, color, casilla_actual_id) VALUES (2, 1, 1);
-- INSERT INTO ficha_oca(id, color, casilla_actual_id) VALUES (3, 2, 1);
-- INSERT INTO ficha_oca(id, color, casilla_actual_id) VALUES (4, 3, 1);


INSERT INTO jugador(id, color, es_ganador, fichas_comidas, veces_caido_en_muerte, ficha_oca_id, partida_oca_id, partida_parchis_id, usuario_id, num_turnos_bloqueado_restantes_oca) VALUES
--JUGADORES OCA PARTIDA 3 (ACTUAL)
( 6, 0, FALSE, 0, 0, 1, 3, null, 3, 0),
( 7, 1, FALSE, 0, 0, 2, 3, null, 4, 0),
--JUGADORES OCA PARTIDA 1
( 24, 0, TRUE, 0, 0, null, 1, null, 3, 0),
( 25, 1, FALSE, 0, 0, null, 1, null, 4, 0),
( 26, 2, FALSE, 0, 0, null, 1, null, 7, 0),
--JUGADORES OCA PARTIDA 2
( 27, 0, TRUE, 0, 0, null, 2, null, 6, 0),
( 28, 1, FALSE, 0, 0, null, 2, null, 9, 0),
--JUGADORES OCA PARTIDA 4
( 29, 0, TRUE, 0, 0, null, 4, null, 5, 0),
( 30, 1, FALSE, 0, 0, null, 4, null, 8, 0),
--JUGADORES OCA PARTIDA 5
( 31, 0, TRUE, 0, 0, null, 5, null, 6, 0),
( 32, 1, FALSE, 0, 0, null, 5, null, 2, 0),
--JUGADORES OCA PARTIDA 6
( 33, 0, TRUE, 0, 0, null, 6, null, 10, 0),
( 34, 1, FALSE, 0, 0, null, 6, null, 7, 0),
--JUGADORES PARCHIS PARTIDA 1 (ACTUAL)
( 8, 0, FALSE, 0, 0, null, null, 1, 1,0),
( 9, 1, FALSE, 0, 0, null, null, 1, 2,0),
--JUGADORES PARCHIS PARTIDA 2
(11, 0, TRUE, 0, 0, null, null, 2, 4,0),
(12, 1, FALSE, 0, 0, null, null, 2, 7,0),
(13, 2, FALSE, 0, 0, null, null, 2, 8,0),
(14, 3, FALSE, 0, 0, null, null, 2, 10,0),
--JUGADORES PARCHIS PARTIDA 3
(15, 0, FALSE, 0, 0, null, null, 3, 8,0),
(16, 1, TRUE, 0, 0, null, null, 3, 7,0),
(17, 2, FALSE, 0, 0, null, null, 3, 1,0),
--JUGADORES PARCHIS PARTIDA 4
(18, 0, FALSE, 0, 0, null, null, 4, 9,0),
(19, 1, TRUE, 0, 0, null, null, 4, 6,0),
--JUGADORES PARCHIS PARTIDA 5
(20, 0, FALSE, 0, 0, null, null, 5, 5,0),
(21, 1, TRUE, 0, 0, null, null, 5, 7,0),
--JUGADORES PARCHIS PARTIDA 6
(22, 0, FALSE, 0, 0, null, null, 6, 8,0),
(23, 1, TRUE, 0, 0, null, null, 6, 4,0);

-- usuario 1 espectea partida 3(oca)
INSERT INTO PARTIDA_OCA_USUARIOS_OBSERVADORES VALUES(3,1);

INSERT INTO partida_oca_casillas(partida_oca_id,casillas_id) VALUES
(3, 1),(3, 2),(3, 3),(3, 4),(3, 5),(3, 6),(3, 7),(3, 8),(3, 9),(3,10),
(3,11),(3,12),(3,13),(3,14),(3,15),(3,16),(3,17),(3,18),(3,19),(3,20),
(3,21),(3,22),(3,23),(3,24),(3,25),(3,26),(3,27),(3,28),(3,29),(3,30),
(3,31),(3,32),(3,33),(3,34),(3,35),(3,36),(3,37),(3,38),(3,39),(3,40),
(3,41),(3,42),(3,43),(3,44),(3,45),(3,46),(3,47),(3,48),(3,49),(3,50),
(3,51),(3,52),(3,53),(3,54),(3,55),(3,56),(3,57),(3,58),(3,59),(3,60),
(3,61),(3,62),(3,63);


-- PARTIDA PARCHIS
INSERT INTO casilla_parchis(id, numero, bloqueada, tipo_casilla_parchis) VALUES
(1, 1, false, 0),
(2, 2, false, 0),
(3, 3, false, 0),
(4, 4, false, 0),
(5, 5, false, 8),
(6, 6, false, 0),
(7, 7, false, 0),
(8, 8, false, 0),
(9, 9, false, 0),
(10, 10, false, 0),
(11, 11, false, 0),
(12, 12, false, 1),
(13, 13, false, 0),
(14, 14, false, 0),
(15, 15, false, 0),
(16, 16, false, 0),
(17, 17, false, 1),
(18, 18, false, 0),
(19, 19, false, 0),
(20, 20, false, 0),
(21, 21, false, 0),
(22, 22, false, 6),
(23, 23, false, 0),
(24, 24, false, 0),
(25, 25, false, 0),
(26, 26, false, 0),
(27, 27, false, 0),
(28, 28, false, 0),
(29, 29, false, 1),
(30, 30, false, 0),
(31, 31, false, 0),
(32, 32, false, 0),
(33, 33, false, 0),
(34, 34, false, 1),
(35, 35, false, 0),
(36, 36, false, 0),
(37, 37, false, 0),
(38, 38, false, 0),
(39, 39, false, 7),
(40, 40, false, 0),
(41, 41, false, 0),
(42, 42, false, 0),
(43, 43, false, 0),
(44, 44, false, 0),
(45, 45, false, 0),
(46, 46, false, 1),
(47, 47, false, 0),
(48, 48, false, 0),
(49, 49, false, 0),
(50, 50, false, 0),
(51, 51, false, 1),
(52, 52, false, 0),
(53, 53, false, 0),
(54, 54, false, 0),
(55, 55, false, 0),
(56, 56, false, 9),
(57, 57, false, 0),
(58, 58, false, 0),
(59, 59, false, 0),
(60, 60, false, 0),
(61, 61, false, 0),
(62, 62, false, 0),
(63, 63, false, 1),
(64, 64, false, 0),
(65, 65, false, 0),
(66, 66, false, 0),
(67, 67, false, 0),
(68, 68, false, 1),
--pasillo amarillo
(69, 69, false, 12),
(70, 70, false, 12),
(71, 71, false, 12),
(72, 72, false, 12),
(73, 73, false, 12),
(74, 74, false, 12),
(75, 75, false, 12),
--meta amarilla
(76, 76, false, 16),
-- pasillo azul
(77, 77, false, 10),
(78, 78, false, 10),
(79, 79, false, 10),
(80, 80, false, 10),
(81, 81, false, 10),
(82, 82, false, 10),
(83, 83, false, 10),
-- meta azul
(84, 84, false, 14),
-- pasillo rojo
(85, 85, false, 12),
(86, 86, false, 12),
(87, 87, false, 12),
(88, 88, false, 12),
(89, 89, false, 12),
(90, 90, false, 12),
(91, 91, false, 12),
-- meta rojo
(92, 92, false, 15),
-- pasillo verde
(93, 93, false, 13),
(94, 94, false, 13),
(95, 95, false, 13),
(96, 96, false, 13),
(97, 96, false, 13),
(98, 96, false, 13),
(99, 96, false, 13),
-- meta verde
(100, 96, false, 17),
-- Casa Amarilla
(101, 101, false, 4),
-- Casa Azul
(102, 102, false, 2),
-- Casa Roja
(103, 103, false, 3),
-- Casa Verde
(104, 104, false, 5);

INSERT INTO partida_parchis_casillas(partida_parchis_id, casillas_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 21),
(1, 22),
(1, 23),
(1, 24),
(1, 25),
(1, 26),
(1, 27),
(1, 28),
(1, 29),
(1, 30),
(1, 31),
(1, 32),
(1, 33),
(1, 34),
(1, 35),
(1, 36),
(1, 37),
(1, 38),
(1, 39),
(1, 40),
(1, 41),
(1, 42),
(1, 43),
(1, 44),
(1, 45),
(1, 46),
(1, 47),
(1, 48),
(1, 49),
(1, 50),
(1, 51),
(1, 52),
(1, 53),
(1, 54),
(1, 55),
(1, 56),
(1, 57),
(1, 58),
(1, 59),
(1, 60),
(1, 61),
(1, 62),
(1, 63),
(1, 64),
(1, 65),
(1, 66),
(1, 67),
(1, 68),
(1, 69),
(1, 70),
(1, 71),
(1, 72),
(1, 73),
(1, 74),
(1, 75),
(1, 76),
(1, 77),
(1, 78),
(1, 79),
(1, 80),
(1, 81),
(1, 82),
(1, 83),
(1, 84),
(1, 85),
(1, 86),
(1, 87),
(1, 88),
(1, 89),
(1, 90),
(1, 91),
(1, 92),
(1, 93),
(1, 94),
(1, 95),
(1, 96),
(1, 97),
(1, 98),
(1, 99),
(1, 100),
(1, 101),
(1, 102),
(1, 103),
(1, 104);
<<<<<<< HEAD
=======


INSERT INTO ficha_parchis(id, color, esta_en_casa, esta_en_la_meta, casilla_actual_id) VALUES 
(1,0, false, false, 39),
(2,0, false, false, 3),
(3,0, false, false, 55),
(4,0, true, false, 103),

(5,1, false, false, 73),
(6,1, false, false, 54),
(7,1, false, false, 75),
(8,1, false, false, 74)
-- ,(9,2, false, false, 40),
-- (10,2,false, false, 41),
-- (11,2,true, false, 104),
-- (12,2,true, false, 104)
;

INSERT INTO jugador_fichas_parchis(jugador_id,fichas_parchis_id) VALUES
(8,1),(8,2),(8,3),(8,4),
(9,5),(9,6),(9,7),(9,8)
--,(10,9),(10,10),(10,11),(10,12)
;

UPDATE usuarios 
SET OCA_DURACION_MAXIMA = 0,
OCA_DURACION_MEDIA = 0,
OCA_DURACION_MINIMA = 0,
OCA_DURACION_TOTAL = 0, 
OCA_PARTIDAS_GANADAS = 0,
OCA_PARTIDAS_JUGADAS = 0,
OCA_VECES_CAIDO_EN_MUERTE = 0,
PARCHIS_DURACION_MAXIMA = 0, 
PARCHIS_DURACION_MEDIA = 0, 
PARCHIS_DURACION_MINIMA = 0,
PARCHIS_DURACION_TOTAL = 0, 
PARCHIS_FICHAS_COMIDAS = 0, 
PARCHIS_PARTIDAS_GANADAS = 0, 
PARCHIS_PARTIDAS_JUGADAS = 0;