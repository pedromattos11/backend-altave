-- Inserir Cargo
INSERT IGNORE INTO cargo (id, nome_cargo) VALUES (1, 'Desenvolvedor Full Stack Sênior');

-- Inserir Colaborador
INSERT IGNORE INTO colaborador (id, nome, email, cpf, apresentacao, perfil, cargo_id) VALUES (1, 'Pedro Mattos', 'pedro.mattos@altave.com', 12345678901, 'Desenvolvedor apaixonado por tecnologia com mais de 5 anos de experiência em desenvolvimento full stack. Especialista em React, Node.js e arquiteturas modernas.', 1, 1);

-- Inserir Hard Skills (Competencias)
INSERT IGNORE INTO competencia (id, nome_competencia, tipo_habilidade) VALUES
(1, 'JavaScript/TypeScript', 'hard'),
(2, 'React.js', 'hard'),
(3, 'Node.js', 'hard'),
(4, 'Python', 'hard'),
(5, 'Java', 'hard'),
(6, 'Spring Boot', 'hard'),
(7, 'MongoDB', 'hard'),
(8, 'PostgreSQL', 'hard'),
(9, 'Docker', 'hard'),
(10, 'AWS', 'hard'),
(11, 'Git/GitHub', 'hard'),
(12, 'REST APIs', 'hard');

-- Associar Hard Skills ao Colaborador
INSERT IGNORE INTO colaborador_competencia (colaborador_id, competencia_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12);

-- Inserir Hard Skills na tabela hard_skill
INSERT IGNORE INTO hard_skill (id, nome_competencia, competencia_id, colaborador_id) VALUES
(1, 'JavaScript/TypeScript', 1, 1),
(2, 'React.js', 2, 1),
(3, 'Node.js', 3, 1),
(4, 'Python', 4, 1),
(5, 'Java', 5, 1),
(6, 'Spring Boot', 6, 1),
(7, 'MongoDB', 7, 1),
(8, 'PostgreSQL', 8, 1),
(9, 'Docker', 9, 1),
(10, 'AWS', 10, 1),
(11, 'Git/GitHub', 11, 1),
(12, 'REST APIs', 12, 1);


-- Inserir Soft Skills
INSERT IGNORE INTO soft_skill (id, nome_competencia) VALUES
(1, 'Comunicação efetiva'),
(2, 'Trabalho em equipe'),
(3, 'Liderança'),
(4, 'Resolução de problemas'),
(5, 'Pensamento crítico'),
(6, 'Adaptabilidade'),
(7, 'Gestão de tempo'),
(8, 'Mentoria');

-- Associar Soft Skills ao Colaborador
INSERT IGNORE INTO colaborador_softskill (colaborador_id, softskill_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8);

-- Inserir Certificações
INSERT IGNORE INTO certificacao (id, nome_certificacao, instituicao) VALUES
(1, 'AWS Certified Developer', 'Amazon Web Services'),
(2, 'React Professional', 'Meta'),
(3, 'Scrum Master Certified', 'Scrum Alliance');

-- Associar Certificações ao Colaborador
INSERT IGNORE INTO colaborador_certificacao (colaborador_id, certificacao_id) VALUES
(1, 1), (1, 2), (1, 3);

-- Inserir Experiências
INSERT IGNORE INTO experiencia (id, cargo, empresa, data_inicio, data_fim) VALUES
(1, 'Sistema de Gestão de Competências', 'Altave', '2023-01-01', NULL),
(2, 'E-commerce Altave', 'Altave', '2022-01-01', '2022-12-31'),
(3, 'API de Integração', 'Altave', '2021-01-01', '2021-12-31');

-- Associar Experiências ao Colaborador
INSERT IGNORE INTO colaborador_experiencia (colaborador_id, experiencia_id) VALUES
(1, 1), (1, 2), (1, 3);