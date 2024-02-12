CREATE TABLE oportunidades (
    id INTEGER AUTOINCREMENT PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    status_oportunidade VARCHAR(100) NOT NULL,
    data_aplicacao DATE NOT NULL,
    data_conclusao DATE NOT NULL,
    revenda_id INTEGER,
)
