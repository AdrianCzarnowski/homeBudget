CREATE TABLE categories (
  category_id SERIAL PRIMARY KEY,
  user_id SERIAL,
  name VARCHAR (50) NOT NULL,
  type VARCHAR (50) NOT NULL,
  description VARCHAR (50) NOT NULL,
  CONSTRAINT fk_user_id
    FOREIGN KEY(user_id)
        REFERENCES users(user_id)
);