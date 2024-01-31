CREATE TABLE veiculos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome VARCHAR(255) NOT NULL,
    valor FLOAT(20) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    ano_modelo DATETIME,
    versao VARCHAR(255) NOT NULL,
)