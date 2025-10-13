-- Fix Soft Skills for All Profiles
-- Run this script directly on your Railway MySQL database

-- First, let's see what we have
SELECT COUNT(*) as total_colaboradores FROM colaborador;
SELECT COUNT(*) as total_soft_skills FROM soft_skill;
SELECT COUNT(*) as total_associations FROM colaborador_softskill;

-- Clear existing associations
DELETE FROM colaborador_softskill;

-- Ensure we have soft skills (create if missing)
INSERT IGNORE INTO soft_skill (nome_competencia) VALUES
('Comunicação'),
('Liderança'),
('Trabalho em equipe'),
('Criatividade'),
('Resolução de problemas'),
('Adaptabilidade'),
('Gestão do tempo'),
('Pensamento crítico'),
('Inteligência emocional'),
('Negociação'),
('Empatia'),
('Proatividade'),
('Organização'),
('Flexibilidade'),
('Iniciativa'),
('Colaboração'),
('Resiliência'),
('Inovação'),
('Networking'),
('Orientação para resultados'),
('Aprendizado contínuo'),
('Multitarefa'),
('Persuasão'),
('Coaching'),
('Mentoria');

-- Associate soft skills by profile
-- Profile 1 (Backend Developer): Technical and organizational skills
INSERT INTO colaborador_softskill (colaborador_id, softskill_id)
SELECT c.id, s.id
FROM colaborador c
CROSS JOIN soft_skill s
WHERE c.perfil = 1 
AND s.nome_competencia IN (
    'Resolução de problemas', 
    'Pensamento crítico', 
    'Organização', 
    'Proatividade', 
    'Aprendizado contínuo',
    'Trabalho em equipe'
);

-- Profile 2 (Frontend Developer): Creative and communication skills
INSERT INTO colaborador_softskill (colaborador_id, softskill_id)
SELECT c.id, s.id
FROM colaborador c
CROSS JOIN soft_skill s
WHERE c.perfil = 2 
AND s.nome_competencia IN (
    'Criatividade', 
    'Comunicação', 
    'Inovação', 
    'Flexibilidade', 
    'Colaboração',
    'Adaptabilidade'
);

-- Profile 3 (Full Stack): Balanced skills
INSERT INTO colaborador_softskill (colaborador_id, softskill_id)
SELECT c.id, s.id
FROM colaborador c
CROSS JOIN soft_skill s
WHERE c.perfil = 3 
AND s.nome_competencia IN (
    'Adaptabilidade', 
    'Multitarefa', 
    'Resolução de problemas', 
    'Comunicação', 
    'Organização',
    'Aprendizado contínuo'
);

-- Profile 4 (DevOps): Operational and leadership skills
INSERT INTO colaborador_softskill (colaborador_id, softskill_id)
SELECT c.id, s.id
FROM colaborador c
CROSS JOIN soft_skill s
WHERE c.perfil = 4 
AND s.nome_competencia IN (
    'Liderança', 
    'Gestão do tempo', 
    'Resiliência', 
    'Proatividade', 
    'Orientação para resultados',
    'Resolução de problemas'
);

-- Profile 5 (Data Science): Analytical skills
INSERT INTO colaborador_softskill (colaborador_id, softskill_id)
SELECT c.id, s.id
FROM colaborador c
CROSS JOIN soft_skill s
WHERE c.perfil = 5 
AND s.nome_competencia IN (
    'Pensamento crítico', 
    'Resolução de problemas', 
    'Aprendizado contínuo', 
    'Comunicação', 
    'Organização',
    'Inovação'
);

-- Profile 6 (Mobile): Innovation and adaptation skills
INSERT INTO colaborador_softskill (colaborador_id, softskill_id)
SELECT c.id, s.id
FROM colaborador c
CROSS JOIN soft_skill s
WHERE c.perfil = 6 
AND s.nome_competencia IN (
    'Inovação', 
    'Adaptabilidade', 
    'Criatividade', 
    'Trabalho em equipe', 
    'Flexibilidade',
    'Aprendizado contínuo'
);

-- Special case for Pedro Mattos (add leadership skills)
INSERT IGNORE INTO colaborador_softskill (colaborador_id, softskill_id)
SELECT c.id, s.id
FROM colaborador c
CROSS JOIN soft_skill s
WHERE c.email = 'pedro.mattos@email.com' 
AND s.nome_competencia IN (
    'Liderança', 
    'Comunicação', 
    'Inteligência emocional', 
    'Negociação', 
    'Coaching'
);

-- Verify results
SELECT 
    c.nome, 
    c.perfil, 
    COUNT(cs.softskill_id) as soft_skills_count
FROM colaborador c
LEFT JOIN colaborador_softskill cs ON c.id = cs.colaborador_id
GROUP BY c.id, c.nome, c.perfil
ORDER BY c.perfil, c.nome;
