CREATE TABLE admin (
    id INTEGER AUTOINCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
)

INSERT INTO `admin` (`nome`, `email`, `senha`) 
VALUES ('Administrador', 'admin@gmail.com', 'admin@123') 