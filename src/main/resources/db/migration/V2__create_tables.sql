CREATE TABLE IF NOT EXISTS cargo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_cargo VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS competencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_competencia VARCHAR(60),
    tipo_habilidade VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS soft_skill (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_competencia VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS certificacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_certificacao VARCHAR(60),
    instituicao VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS experiencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cargo VARCHAR(60),
    empresa VARCHAR(60),
    data_inicio DATE,
    data_fim DATE
);

CREATE TABLE IF NOT EXISTS colaborador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    cpf BIGINT UNIQUE,
    apresentacao VARCHAR(100),
    perfil INT,
    cargo_id INT,
    FOREIGN KEY (cargo_id) REFERENCES cargo(id)
);

CREATE TABLE IF NOT EXISTS hard_skill (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_competencia VARCHAR(60),
    competencia_id INT,
    colaborador_id INT,
    FOREIGN KEY (competencia_id) REFERENCES competencia(id),
    FOREIGN KEY (colaborador_id) REFERENCES colaborador(id)
);

CREATE TABLE IF NOT EXISTS colaborador_competencia (
    colaborador_id INT,
    competencia_id INT,
    PRIMARY KEY (colaborador_id, competencia_id),
    FOREIGN KEY (colaborador_id) REFERENCES colaborador(id),
    FOREIGN KEY (competencia_id) REFERENCES competencia(id)
);

CREATE TABLE IF NOT EXISTS colaborador_softskill (
    colaborador_id INT,
    softskill_id INT,
    PRIMARY KEY (colaborador_id, softskill_id),
    FOREIGN KEY (colaborador_id) REFERENCES colaborador(id),
    FOREIGN KEY (softskill_id) REFERENCES soft_skill(id)
);

CREATE TABLE IF NOT EXISTS colaborador_experiencia (
    colaborador_id INT,
    experiencia_id INT,
    PRIMARY KEY (colaborador_id, experiencia_id),
    FOREIGN KEY (colaborador_id) REFERENCES colaborador(id),
    FOREIGN KEY (experiencia_id) REFERENCES experiencia(id)
);

CREATE TABLE IF NOT EXISTS colaborador_certificacao (
    colaborador_id INT,
    certificacao_id INT,
    PRIMARY KEY (colaborador_id, certificacao_id),
    FOREIGN KEY (colaborador_id) REFERENCES colaborador(id),
    FOREIGN KEY (certificacao_id) REFERENCES certificacao(id)
);