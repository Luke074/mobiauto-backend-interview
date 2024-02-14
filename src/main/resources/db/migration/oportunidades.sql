CREATE TABLE oportunidades (
    id INTEGER AUTOINCREMENT PRIMARY KEY,
    usuario_id INTEGER NOT NULL,
    status_oportunidade VARCHAR(100) NOT NULL,
    data_aplicacao DATE NOT NULL,
    data_conclusao DATE NOT NULL,
    revenda_id INTEGER,
    veiculo_id INTEGER,
    nome_cliente VARCHAR(100),
    email_cliente VARCHAR(100),
    telefone_cliente INTEGER(10),
)
