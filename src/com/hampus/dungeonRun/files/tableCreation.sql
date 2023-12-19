DROP DATABASE IF EXISTS dungeonrun;

CREATE DATABASE IF NOT EXISTS dungeonrun;

USE dungeonrun;

CREATE TABLE player (
    playerID int AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    playerLevel int,
    health int,
    maxHealth int,
    experience int,
    requiredExperience int,
    strength int,
    agility int,
    gold int,
    criticalHitrate int,
    incomingBossbattle bool,
    numberOfKills int,
    equippedItemID int
);

CREATE TABLE item (
    itemID int AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    itemValue int,
    description VARCHAR(255),
    price int,
    quantity int
);

CREATE TABLE inventory (
	inventoryID int AUTO_INCREMENT PRIMARY KEY,
    itemID int,
    playerID int,
    amountBought int
);

CREATE TABLE monster(
	monsterID int AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	monsterLevel int,
	health int,
	maxHealth int,
	experienceGained int,
	strength int,
	agility int,
	goldGained int,
	criticalHitrate int
);

CREATE TABLE battleHistory(
	battleID int AUTO_INCREMENT PRIMARY KEY,
	playerID int,
	monsterID int,
	resultOfBattle VARCHAR(50),
	timeOfBattle DATETIME
);

TRUNCATE TABLE item;
-- Insert statements for potions
INSERT INTO item (name, itemValue, description, price, quantity)
VALUES ('Small health potion: ', 30, 'A potion that instantly heals your HP by: ', 20, 1000);
INSERT INTO item (name, itemValue, description, price, quantity)
VALUES ('Medium Health Potion: ', 100, 'A potion that instantly heals your HP by: ', 40, 1000);
INSERT INTO item (name, itemValue, description, price, quantity)
VALUES ('Large Health Potion: ', 300, 'A potion that instantly heals your HP by: ', 100, 1000);
-- Insert statements for weapons
INSERT INTO item (name, itemValue, description, price, quantity)
VALUES ('Knife: ', 5, 'A small thieves knife, it will increase your strength by: ', 50, 1);
INSERT INTO item (name, itemValue, description, price, quantity)
VALUES ('Greatsword: ', 15, 'A Sword from a fallen knight, it will increase your strength by: ', 100, 1);
INSERT INTO item (name, itemValue, description, price, quantity)
VALUES ('Excalibur: ', 50, 'A Mythical sword with magic powers, it will increase your strength by: ', 1000, 1);

ALTER TABLE inventory
ADD CONSTRAINT fk_item_id FOREIGN KEY (itemID) REFERENCES item(itemID);

ALTER TABLE inventory
ADD CONSTRAINT fk_player_id FOREIGN KEY (playerID) REFERENCES player(playerID);

ALTER TABLE battleHistory
ADD CONSTRAINT fk_player_id_battle FOREIGN KEY (playerID) REFERENCES player(playerID);

ALTER TABLE battleHistory
ADD CONSTRAINT fk_monster_id_battle FOREIGN KEY (monsterID) REFERENCES monster(monsterID);
