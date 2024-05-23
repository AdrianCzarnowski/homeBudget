CREATE TABLE homebudget.users (
  user_id UUID PRIMARY KEY,
  username VARCHAR (50) UNIQUE NOT NULL,
  password VARCHAR (50) NOT NULL,
  email VARCHAR (255) UNIQUE NOT NULL,
  created_at TIMESTAMP NOT NULL,
  last_login TIMESTAMP
);
CREATE TABLE homebudget.categories (
  category_id UUID PRIMARY KEY,
  user_id UUID,
  name VARCHAR (50) NOT NULL,
  type VARCHAR (50) NOT NULL,
  description VARCHAR (50) NOT NULL,
  CONSTRAINT fk_user_id
    FOREIGN KEY(user_id)
        REFERENCES users(user_id)
);
CREATE TABLE homebudget.transactions (
  transaction_id UUID PRIMARY KEY,
  user_id UUID,
  category_id UUID,
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
CREATE TABLE homebudget.budgets (
  budget_id UUID PRIMARY KEY,
  user_id UUID,
  category_id UUID,
  amount FLOAT,
  month_dt INTEGER NOT NULL,
  year_dt INTEGER NOT NULL,
  CONSTRAINT f_user_id
    FOREIGN KEY(user_id)
        REFERENCES users(user_id),
  CONSTRAINT f_category_id
    FOREIGN KEY(category_id)
        REFERENCES categories(category_id)
);
CREATE TABLE homebudget.reports (
  report_id UUID PRIMARY KEY,
  user_id UUID,
  report_type VARCHAR(50) NOT NULL,
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP NOT NULL,
  generated_date TIMESTAMP NOT NULL,
  CONSTRAINT kf_user_id
    FOREIGN KEY(user_id)
        REFERENCES users(user_id)
);