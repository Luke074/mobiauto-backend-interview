CREATE TABLE admin (
    id INTEGER AUTOINCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    nome_usuario VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
)

INSERT INTO `admin` (`id`, `nome`, `email`, `nome_usuario`, `senha`) VALUES
(1, 'Lucas Mendes', 'lucas@teste.com.br', "lucas_mendes" , '$2a$10$.yn.B8Li3RHFzvQ11TXhaOohFy.CW7qyOUF1YCtCr8dSZqQ9scYg2');