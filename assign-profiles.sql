-- Assign Profiles to All Colaboradores
-- This script assigns a profile (1-6) to each colaborador

-- First, let's see current state
SELECT nome, email, perfil FROM colaborador ORDER BY nome;

-- Update profiles for each colaborador based on their names/roles
-- Profile 1: Backend Developer
UPDATE colaborador SET perfil = 1 WHERE nome = 'Pedro Henrique';

-- Profile 2: Frontend Developer  
UPDATE colaborador SET perfil = 2 WHERE nome LIKE '%Admin%' OR nome = 'Admin Altave';

-- Profile 3: Full Stack Developer
UPDATE colaborador SET perfil = 3 WHERE nome = 'Carlos Eduardo Costa';

-- Profile 4: DevOps Engineer
UPDATE colaborador SET perfil = 4 WHERE nome = 'Cleber Kirch';

-- Profile 5: Data Science
UPDATE colaborador SET perfil = 5 WHERE nome = 'Diego Vitvicki';

-- Profile 6: Mobile Developer
UPDATE colaborador SET perfil = 6 WHERE nome = 'Ed Wilson';

-- For any remaining users without names, assign random profiles
UPDATE colaborador SET perfil = 2 WHERE perfil IS NULL AND (nome = '--' OR nome IS NULL OR nome = '');

-- Verify the updates
SELECT nome, email, perfil, 
       CASE perfil 
           WHEN 1 THEN 'Backend Developer'
           WHEN 2 THEN 'Frontend Developer' 
           WHEN 3 THEN 'Full Stack Developer'
           WHEN 4 THEN 'DevOps Engineer'
           WHEN 5 THEN 'Data Scientist'
           WHEN 6 THEN 'Mobile Developer'
           ELSE 'No Profile'
       END as profile_description
FROM colaborador 
ORDER BY perfil, nome;

-- Now re-assign soft skills for the newly assigned profiles
-- Clear existing associations for users who didn't have profiles before
DELETE cs FROM colaborador_softskill cs 
JOIN colaborador c ON cs.colaborador_id = c.id 
WHERE c.nome IN ('Admin Altave', 'Carlos Eduardo Costa', 'Cleber Kirch', 'Diego Vitvicki', 'Ed Wilson', '--');

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

-- Final verification - show all colaboradores with their profiles and soft skills count
SELECT 
    c.nome, 
    c.email,
    c.perfil,
    CASE c.perfil 
        WHEN 1 THEN 'Backend Developer'
        WHEN 2 THEN 'Frontend Developer' 
        WHEN 3 THEN 'Full Stack Developer'
        WHEN 4 THEN 'DevOps Engineer'
        WHEN 5 THEN 'Data Scientist'
        WHEN 6 THEN 'Mobile Developer'
        ELSE 'No Profile'
    END as profile_description,
    COUNT(cs.softskill_id) as soft_skills_count
FROM colaborador c
LEFT JOIN colaborador_softskill cs ON c.id = cs.colaborador_id
GROUP BY c.id, c.nome, c.email, c.perfil
ORDER BY c.perfil, c.nome;
