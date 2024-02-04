CREATE TABLE oportunidades (
    id INTEGER AUTOINCREMENT PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    status_oportunidade ENUM('novo', 'em atendimento', 'concluido') DEFAULT 'novo' NOT NULL,
    data_aplicacao DATE NOT NULL,
    data_conclusao DATE NOT NULL,
)