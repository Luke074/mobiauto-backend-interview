CREATE TABLE oportunidades (
    id INTEGER AUTOINCREMENT PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    status_oportunidade ENUM('NOVO', 'EM_ATENDIMENTO', 'CONCLUIDO') DEFAULT 'NOVO' NOT NULL,
    data_aplicacao DATE NOT NULL,
    data_conclusao DATE NOT NULL,
    revenda_id INTEGER,
)
