-- Inserir Hard Skills pré-definidas
INSERT IGNORE INTO competencia (nome_competencia, tipo_habilidade) VALUES
-- Linguagens
('Java', 'hard'),
('JavaScript', 'hard'),
('TypeScript', 'hard'),
('Python', 'hard'),
('C++', 'hard'),
('C#', 'hard'),
('Ruby', 'hard'),
('Go', 'hard'),
('Rust', 'hard'),
-- Frameworks Frontend
('React', 'hard'),
('Vue.js', 'hard'),
('Angular', 'hard'),
('Next.js', 'hard'),
-- Frameworks Backend
('Spring Boot', 'hard'),
('Node.js', 'hard'),
('Express', 'hard'),
('Django', 'hard'),
('FastAPI', 'hard'),
-- Banco de Dados
('MySQL', 'hard'),
('PostgreSQL', 'hard'),
('MongoDB', 'hard'),
('Redis', 'hard'),
-- Cloud e DevOps
('AWS', 'hard'),
('Docker', 'hard'),
('Kubernetes', 'hard'),
('Git', 'hard'),
('CI/CD', 'hard'),
('Terraform', 'hard'),
-- Mobile
('React Native', 'hard'),
('Flutter', 'hard'),
('iOS (Swift)', 'hard'),
('Android (Kotlin)', 'hard');

-- Inserir Soft Skills pré-definidas
INSERT IGNORE INTO soft_skill (nome_competencia) VALUES
('Comunicação efetiva'),
('Trabalho em equipe'),
('Liderança'),
('Resolução de problemas'),
('Pensamento crítico'),
('Adaptabilidade'),
('Gestão de tempo'),
('Mentoria'),
('Criatividade'),
('Empatia'),
('Escuta ativa'),
('Negociação'),
('Inteligência emocional'),
('Assertividade'),
('Paciência'),
('Motivação'),
('Organização'),
('Proatividade'),
('Resiliência'),
('Colaboração'),
('Feedback construtivo'),
('Autodisciplina'),
('Visão estratégica');

