CREATE TABLE admin (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
)

INSERT INTO `admin` (`id`, `nome`, `email`, `senha`) 
VALUES (1, 'Administrador', 'admin@gmail.com', 'admin@123');