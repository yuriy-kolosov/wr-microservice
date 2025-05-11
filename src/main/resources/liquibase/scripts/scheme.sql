-- liquibase formatted sql

-- changeset yuriy_kolosov:1
CREATE TABLE IF NOT EXISTS users (
  id bigserial PRIMARY KEY,
  email VARCHAR(255) UNIQUE NOT NULL,
  "password" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS subscriptions (
  id bigserial PRIMARY KEY,
  subscription VARCHAR(255) NOT NULL,
  subscriber_id bigint NOT NULL,
  CONSTRAINT fk_users_id_subscriber FOREIGN KEY (subscriber_id) REFERENCES users (id) ON DELETE CASCADE
);
