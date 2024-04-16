CREATE TABLE transactions (
  transaction_id SERIAL PRIMARY KEY,
  user_id SERIAL,
  category_id SERIAL,
  amount FLOAT,
  type VARCHAR (50) NOT NULL,
  transaction_date TIMESTAMP NOT NULL,
  description VARCHAR (50) NOT NULL,
  CONSTRAINT fk_user_id
    FOREIGN KEY(user_id)
        REFERENCES users(user_id),
  CONSTRAINT fk_category_id
    FOREIGN KEY(category_id)
        REFERENCES categories(category_id)
);
CREATE TABLE budgets (
  budget_id SERIAL PRIMARY KEY,
  user_id SERIAL,
  category_id SERIAL,
  amount FLOAT,
  month_dt DATE NOT NULL,
  year_dt DATE NOT NULL,
  CONSTRAINT f_user_id
    FOREIGN KEY(user_id)
        REFERENCES users(user_id),
  CONSTRAINT f_category_id
    FOREIGN KEY(category_id)
        REFERENCES categories(category_id)
);
CREATE TABLE reports (
  report_id SERIAL PRIMARY KEY,
  user_id SERIAL,
  report_type VARCHAR(50) NOT NULL,
  start_time DATE NOT NULL,
  end_time DATE NOT NULL,
  generated_date TIMESTAMP NOT NULL,
  CONSTRAINT kf_user_id
    FOREIGN KEY(user_id)
        REFERENCES users(user_id)
);