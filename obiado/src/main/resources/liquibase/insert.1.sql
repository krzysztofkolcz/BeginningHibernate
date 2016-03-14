/* Populate user_profile Table */
INSERT INTO user_profile(type) VALUES ('ADMIN'); 
INSERT INTO user_profile(type) VALUES ('REGISTERED');

/* Populate app_user Table */
INSERT INTO app_user(sso_id, password, first_name, last_name, email, state) VALUES ('kasia','Power123', 'Kasia','Watcher','kasia@xyz.com', 'Active'); 
INSERT INTO app_user(sso_id, password, first_name, last_name, email, state) VALUES ('jacek','Power123', 'Jacek','Theys','jacek@xyz.com', 'Active');
INSERT INTO app_user(sso_id, password, first_name, last_name, email, state) VALUES ('guest','Power123', 'Guest','Guest','guest@xyz.com', 'Active');

 /* Populate JOIN Table */
INSERT INTO app_user_user_profile (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile  
  where user.sso_id='kasia' and profile.type='REGISTERED';
 
INSERT INTO app_user_user_profile (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile
  where user.sso_id='jacek' and profile.type='ADMIN';
 
