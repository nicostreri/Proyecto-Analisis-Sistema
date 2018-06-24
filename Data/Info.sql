insert into fixtures (id,name) values 
(1,'RUSIA2018');

insert into schedules (id,date_name,fixture_id) values 
(1,'PRIMERA FECHA',1),
(2,'SEGUNDA FECHA',1),
(3,'TERCERA FECHA',1),
(4,'CUARTA FECHA',1),
(5,'QUINTA FECHA',1);

insert into teams (id,name) values 
(1,'EQUIPO 1'),
(2,'EQUIPO 2'),
(3,'EQUIPO 3'),
(4,'EQUIPO 4'),
(5,'EQUIPO 5'),
(6,'EQUIPO 6'),
(7,'EQUIPO 7'),
(8,'EQUIPO 8'),
(9,'EQUIPO 9'),
(10,'EQUIPO 10');

insert into results (id,result_type,local_goals,visit_goals) values
(1,'gana_local',2,0),
(2,'gana_local',2,1),
(3,'gana_visitante',0,3),
(4,'gana_visitante',1,3),
(5,'empate',0,0),
(6,'gana_local',2,0),
(7,'gana_local',2,1),
(8,'gana_visitante',0,3),
(9,'gana_visitante',1,3),
(10,'empate',0,0),
(11,'gana_local',2,0),
(12,'gana_local',2,1),
(13,'gana_visitante',0,3),
(14,'gana_visitante',1,3),
(15,'empate',0,0),
(16,'gana_local',2,0),
(17,'gana_local',2,1),
(18,'gana_visitante',0,3),
(19,'gana_visitante',1,3),
(20,'empate',0,0),
(21,'no_jugado',2,0),
(22,'no_jugado',2,1),
(23,'no_jugado',0,3),
(24,'no_jugado',1,3),
(25,'no_jugado',0,0);

insert into matches (id,match_date,schedule_id,local_team_id,visitor_team_id,result_id) values
(1,'2018-04-01',1,1,2,1),
(2,'2018-04-02',1,1,3,2),
(3,'2018-04-03',1,1,4,3),
(4,'2018-04-04',1,1,5,4),
(5,'2018-04-05',1,1,6,5),
(6,'2018-04-06',2,2,3,6),
(7,'2018-04-07',2,2,4,7),
(8,'2018-04-08',2,2,5,8),
(9,'2018-04-09',2,2,6,9),
(10,'2018-04-10',2,2,7,10),
(11,'2018-04-11',3,3,4,11),
(12,'2018-04-12',3,3,5,12),
(13,'2018-04-13',3,3,6,13),
(14,'2018-04-14',3,3,7,14),
(15,'2018-04-15',3,3,8,15),
(16,'2018-04-16',4,4,5,16),
(17,'2018-04-17',4,4,6,17),
(18,'2018-04-18',4,4,7,18),
(19,'2018-04-19',4,4,8,19),
(20,'2018-04-20',4,4,9,20),
(21,'2018-07-21',5,5,6,21),
(22,'2018-07-22',5,5,7,22),
(23,'2018-07-23',5,5,8,23),
(24,'2018-07-24',5,5,9,24),
(25,'2018-07-25',5,5,10,25);

insert into users (username,name,lastname,password) values ('user123','UserNombre','UserApellido','123');

insert into players (username) values 
('user123');

-- Controlar que se pueden crear apuestas sin que el usuario este suscrito al fixture

insert into players_fixtures (player_username,fixture_id) values
('user123',1);

insert into bets (bet_date,schedule_id,username_player) values
('2018-04-02',1,'user123');

insert into scores (amount_points,correct_predicted_matches,username_player,bet_id) values
(10,3,'user123',1);

insert into predictions (id,result_type,hit,score_id) values 
(1,'gana_local',1,1);
 
insert into bets_results (bet_id,result_id,prediction_id) values
(1,1,1);

