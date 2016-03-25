INSERT INTO user_profile (id, type) VALUES
(5, 'ADMIN'),
(6, 'REGISTERED');

INSERT INTO app_user (id, password, first_name, last_name, email, state, joining_date) VALUES
(6, 'Power123', 'Kasia', 'Watcher', 'kasia@xyz.com', 'Active', NULL),
(7, 'Power123', 'Jacek', 'Theys', 'jacek@xyz.com', 'Active', NULL),
(8, 'Power123', 'Guest', 'Guest', 'guest@xyz.com', 'Active', NULL),
(12, 'Power123', 'Krzysztof', 'Ko&#322;cz', 'krzysztof.kolcz@gmail.com', 'Active', NULL),
(13, 'Power123', 'Marian', 'Zaczkowski', 'mzaczkowski@wp.pl', 'Active', NULL),
(14, 'Power123', 'Marian', 'Zenoniusz', 'marian.zenoniusz@gmail.com', 'Active', NULL);



INSERT INTO app_user_user_profile (user_id, user_profile_id) VALUES
(7, 5),
(6, 6),
(7, 6),
(12, 6),
(13, 6),
(14, 6);
