CREATE TABLE usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cargo ENUM("proprietario", "gerente", "assistente") DEFAULT "assistente",
    loja_id INTEGER, 
)

INSERT INTO usuarios (`id`, `nome`, `email`, `senha`, `cargo`, `loja_id`) 
VALUES (1, "Lucas Mendes", "lucas@gmail.com", "usuario@123", "proprietario", 1);