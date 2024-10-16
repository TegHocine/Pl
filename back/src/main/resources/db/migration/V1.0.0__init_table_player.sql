CREATE TABLE player (
id BIGSERIAL PRIMARY KEY,
player_name VARCHAR(255) NOT NULL,
nation VARCHAR(255),
position VARCHAR(50),
age INTEGER,
match_played INTEGER,
starts INTEGER,
minutes_played FLOAT,
goals FLOAT,
assists FLOAT,
penalties_scored FLOAT,
yellow_cards FLOAT,
red_cards FLOAT,
expected_goals FLOAT,
expected_assists FLOAT,
team VARCHAR(255)
);