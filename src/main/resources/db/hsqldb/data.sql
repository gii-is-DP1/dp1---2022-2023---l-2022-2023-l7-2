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




INSERT INTO users(username,password,enabled) VALUES ('usuario1','usuario1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'usuario1','usuario');

INSERT INTO users(username,password,enabled) VALUES ('usuario2','usuario2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'usuario2','usuario');


INSERT INTO usuario(id,nombre,apellido,username) VALUES (1,'Pepe','Gonzalez','usuario1');
INSERT INTO usuario(id,nombre,apellido,username) VALUES (2,'Maria','Gonzalez','usuario2');