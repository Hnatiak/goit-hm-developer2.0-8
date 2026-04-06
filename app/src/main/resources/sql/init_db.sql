-- Створення таблиці працівників
CREATE TABLE IF NOT EXISTS worker (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(1000) NOT NULL CHECK (LENGTH(name) >= 2),
    birthday DATE NOT NULL CHECK (YEAR(birthday) > 1900),
    level VARCHAR(20) NOT NULL CHECK (level IN ('Trainee','Junior','Middle','Senior')),
    salary INT NOT NULL CHECK (salary BETWEEN 100 AND 100000)
);

-- Створення таблиці клієнтів
CREATE TABLE IF NOT EXISTS client (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(1000) NOT NULL CHECK (LENGTH(name) >= 2)
);

-- Створення таблиці проєктів
CREATE TABLE IF NOT EXISTS project (
    id INT PRIMARY KEY AUTO_INCREMENT,
    client_id INT NOT NULL,
    start_date DATE NOT NULL,
    finish_date DATE NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id)
);

-- Зв'язкова таблиця працівників і проєктів
CREATE TABLE IF NOT EXISTS project_worker (
    project_id INT NOT NULL,
    worker_id INT NOT NULL,
    PRIMARY KEY (project_id, worker_id),
    FOREIGN KEY (project_id) REFERENCES project(id),
    FOREIGN KEY (worker_id) REFERENCES worker(id)
);