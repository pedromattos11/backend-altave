-- Criar tabela de projetos

CREATE TABLE IF NOT EXISTS projeto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_projeto VARCHAR(100),
    descricao VARCHAR(500),
    tecnologias VARCHAR(200),
    data_inicio DATE,
    data_fim DATE,
    link VARCHAR(500),
    colaborador_id INT,
    FOREIGN KEY (colaborador_id) REFERENCES colaborador(id)
);

