CREATE TABLE app_user (
  id bigint NOT NULL IDENTITY,
  password varchar(100) NOT NULL,
  first_name varchar(30) NOT NULL,
  last_name varchar(30) NOT NULL,
  email varchar(150) NOT NULL,
  state varchar(30) NOT NULL,
  joining_date date DEFAULT NULL,
  UNIQUE (email)
);


CREATE TABLE app_user_user_profile (
  user_id bigint NOT NULL,
  user_profile_id bigint NOT NULL,
  PRIMARY KEY (user_id,user_profile_id),
);

CREATE TABLE user_profile (
  id bigint NOT NULL IDENTITY,
  type varchar(30) NOT NULL,
  UNIQUE (type)
);

ALTER TABLE app_user_user_profile ADD FOREIGN KEY (user_id) REFERENCES app_user (id);
ALTER TABLE app_user_user_profile ADD FOREIGN KEY (user_profile_id) REFERENCES user_profile (id);

