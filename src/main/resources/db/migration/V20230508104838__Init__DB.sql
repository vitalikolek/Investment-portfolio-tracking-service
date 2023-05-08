CREATE TABLE IF NOT EXISTS cryptocurrency (
id SERIAL PRIMARY KEY,
symbol VARCHAR(20) UNIQUE NOT NULL,
name VARCHAR(100),
price NUMERIC,
dayHigh NUMERIC,
dayLow NUMERIC,
change NUMERIC,
changeInPercent NUMERIC,
marketcap NUMERIC,
volume BIGINT
);

CREATE TABLE IF NOT EXISTS share (
id SERIAL PRIMARY KEY,
symbol VARCHAR(20) UNIQUE NOT NULL,
name VARCHAR(100),
price NUMERIC,
dayHigh NUMERIC,
dayLow NUMERIC,
change NUMERIC,
changeInPercent NUMERIC,
marketcap NUMERIC,
volume BIGINT
);

CREATE TABLE IF NOT EXISTS currency (
id SERIAL PRIMARY KEY,
symbol VARCHAR(20) UNIQUE NOT NULL,
name VARCHAR(100),
price NUMERIC,
dayHigh NUMERIC,
dayLow NUMERIC,
change NUMERIC,
changeInPercent NUMERIC
);

CREATE TABLE IF NOT EXISTS customer (
id SERIAL PRIMARY KEY,
username VARCHAR(50) UNIQUE NOT NULL,
email VARCHAR(250) UNIQUE NOT NULL,
password VARCHAR(250) NOT NULL,
role VARCHAR(50) NOT NULL,
creation_time DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS customer_cryptocurrency (
customer_id integer NOT NULL,
cryptocurrency_symbol VARCHAR(20) NOT NULL,
amount NUMERIC NOT NULL,
PRIMARY KEY (customer_id, cryptocurrency_symbol),
FOREIGN KEY (customer_id) REFERENCES customer (id),
FOREIGN KEY (cryptocurrency_symbol) REFERENCES cryptocurrency(symbol)
);

CREATE TABLE IF NOT EXISTS customer_share (
customer_id integer NOT NULL,
share_symbol VARCHAR(20) NOT NULL,
amount NUMERIC NOT NULL,
PRIMARY KEY (customer_id, share_symbol),
FOREIGN KEY (customer_id) REFERENCES share (id),
FOREIGN KEY (share_symbol) REFERENCES share (symbol)
);

CREATE TABLE IF NOT EXISTS customer_currency (
customer_id integer NOT NULL,
currency_symbol VARCHAR(20) NOT NULL,
amount NUMERIC NOT NULL,
PRIMARY KEY (customer_id, currency_symbol),
FOREIGN KEY (customer_id) REFERENCES currency (id),
FOREIGN KEY (currency_symbol) REFERENCES currency (symbol)
);