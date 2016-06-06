 SET DATABASE TEXT TABLE DEFAULTS 'encoding=UTF-8';

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

CREATE TABLE  product_category(
  id bigint NOT NULL IDENTITY,
  name varchar(255) NOT NULL
);

CREATE TABLE  product(
  id bigint NOT NULL IDENTITY,
  name varchar(255) NOT NULL,
  price decimal(10,2),
  active boolean default true,
  sku varchar(255) NOT NULL,
  state varchar(30)
);


CREATE TABLE  product_product_category(
  product_id bigint NOT NULL,
  product_category_id bigint NOT NULL,
  PRIMARY KEY (product_id ,product_category_id )
);

ALTER TABLE product_product_category ADD CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE product_product_category ADD CONSTRAINT fk_product_category FOREIGN KEY (product_category_id) REFERENCES product_category (id);
