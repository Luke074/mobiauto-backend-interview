CREATE TABLE usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    cargo VARCHAR(50),
    loja_id INTEGER, 
)

INSERT INTO `usuarios` (`id`,`login` ,`cargo`, `email`, `loja_id`, `nome`, `senha`) VALUES
(1, "lucasmendes", 0, 'lucas@teste.com.br', 1, 'Lucas Mendes', '$2a$10$.yn.B8Li3RHFzvQ11TXhaOohFy.CW7qyOUF1YCtCr8dSZqQ9scYg2');