DROP SCHEMA IF EXISTS playerDB;

CREATE TABLE player (
  id int(11) NOT NULL AUTO_INCREMENT,
  login_name varchar(150) UNIQUE NOT NULL,
  login_password varchar(150) NOT NULL,
  balance double(10, 2) NOT NULL,
  session_token varchar(190),
  PRIMARY KEY (id) 
);

CREATE TABLE player_profile(
  id int(10) NOT NULL AUTO_INCREMENT,
  first_name varchar(150) NOT NULL,
  last_name varchar(150) NOT NULL,
  fk_player int(10) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT player_profile_player_id FOREIGN KEY (fk_player) REFERENCES player (id)
);



--create test players:
INSERT INTO player (login_name, login_password, balance) VALUES("Winnie", "Winnie123456", 333.33);
INSERT INTO player (login_name, login_password, balance) VALUES("Alpha", "Alpha123456", 999.33);
INSERT INTO player (login_name, login_password, balance) VALUES("Titan", "Titan123456", 222.33);
INSERT INTO player (login_name, login_password, balance) VALUES("Elfie", "Elfie", 123.33);

INSERT INTO player_profile (first_name, last_name, fk_player) VALUES("Winnie", "The Poof", 1);
INSERT INTO player_profile (first_name, last_name, fk_player) VALUES("Legolas", "GreenLeaf", 4);


--Connection pool infor for persistence
--
--<?xml version="1.0" encoding="UTF-8"?>
--<persistence version="2.1" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
--	<persistence-unit name="GPPets_Players" transaction-type="JTA">
--		<jta-data-source>jdbc/playerDBdsn</jta-data-source>
--		<class>model.Player</class>
--		<class>model.PlayerProfile</class>
--	</persistence-unit>
--</persistence>

    
    
