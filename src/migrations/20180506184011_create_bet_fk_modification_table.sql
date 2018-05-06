ALTER TABLE bets DROP FOREIGN KEY control_usuario_bet;
ALTER TABLE bets ADD FOREIGN KEY control_usuario_bet(username_player) REFERENCES players(username);