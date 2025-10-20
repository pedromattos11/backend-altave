-- Migration V8: Fixar associação de experiências aos colaboradores
-- Garantir que todas as experiências tenham um colaborador associado

-- 1. Verificar se a coluna colaborador_id existe, se não, criar
SET @column_exists = (
    SELECT COUNT(*) 
    FROM INFORMATION_SCHEMA.COLUMNS 
    WHERE TABLE_SCHEMA = DATABASE()
    AND TABLE_NAME = 'experiencia' 
    AND COLUMN_NAME = 'colaborador_id'
);

SET @sql_add_column = IF(@column_exists = 0, 
    'ALTER TABLE experiencia ADD COLUMN colaborador_id INT;', 
    'SELECT "Column colaborador_id already exists";'
);
PREPARE stmt FROM @sql_add_column;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- 2. Verificar se a foreign key existe, se não, criar
SET @fk_exists = (
    SELECT COUNT(*) 
    FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE 
    WHERE TABLE_SCHEMA = DATABASE()
    AND TABLE_NAME = 'experiencia' 
    AND COLUMN_NAME = 'colaborador_id'
    AND REFERENCED_TABLE_NAME = 'colaborador'
);

SET @sql_add_fk = IF(@fk_exists = 0, 
    'ALTER TABLE experiencia ADD CONSTRAINT fk_experiencia_colaborador FOREIGN KEY (colaborador_id) REFERENCES colaborador(id);', 
    'SELECT "Foreign key already exists";'
);
PREPARE stmt FROM @sql_add_fk;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- 3. Contar experiências sem colaborador associado
SELECT 
    COUNT(*) as experiencias_sem_colaborador,
    'Total de experiências órfãs' as descricao
FROM experiencia 
WHERE colaborador_id IS NULL;

-- 4. Obter informações dos colaboradores disponíveis
SELECT 
    id, 
    nome, 
    perfil,
    'Colaboradores disponíveis' as descricao
FROM colaborador 
ORDER BY id;

-- 5. Associar experiências órfãs aos colaboradores de forma inteligente

-- Primeiro, experiências relacionadas a Backend (perfil 1)
UPDATE experiencia 
SET colaborador_id = (
    SELECT id FROM colaborador 
    WHERE perfil = 1 
    ORDER BY id 
    LIMIT 1
)
WHERE colaborador_id IS NULL 
AND (
    LOWER(cargo) LIKE '%backend%' OR 
    LOWER(cargo) LIKE '%java%' OR 
    LOWER(cargo) LIKE '%spring%' OR 
    LOWER(cargo) LIKE '%api%' OR
    LOWER(cargo) LIKE '%servidor%'
);

-- Experiências relacionadas a Frontend (perfil 2)  
UPDATE experiencia 
SET colaborador_id = (
    SELECT id FROM colaborador 
    WHERE perfil = 2 
    ORDER BY id 
    LIMIT 1
)
WHERE colaborador_id IS NULL 
AND (
    LOWER(cargo) LIKE '%frontend%' OR 
    LOWER(cargo) LIKE '%react%' OR 
    LOWER(cargo) LIKE '%ui%' OR 
    LOWER(cargo) LIKE '%ux%' OR
    LOWER(cargo) LIKE '%web%'
);

-- Experiências relacionadas a Full Stack (perfil 3)
UPDATE experiencia 
SET colaborador_id = (
    SELECT id FROM colaborador 
    WHERE perfil = 3 
    ORDER BY id 
    LIMIT 1
)
WHERE colaborador_id IS NULL 
AND (
    LOWER(cargo) LIKE '%full%' OR 
    LOWER(cargo) LIKE '%desenvolvedor%' OR 
    LOWER(cargo) LIKE '%developer%' OR
    LOWER(cargo) LIKE '%programador%'
);

-- Experiências relacionadas a DevOps (perfil 4)
UPDATE experiencia 
SET colaborador_id = (
    SELECT id FROM colaborador 
    WHERE perfil = 4 
    ORDER BY id 
    LIMIT 1
)
WHERE colaborador_id IS NULL 
AND (
    LOWER(cargo) LIKE '%devops%' OR 
    LOWER(cargo) LIKE '%infra%' OR 
    LOWER(cargo) LIKE '%cloud%' OR
    LOWER(cargo) LIKE '%sre%'
);

-- Experiências relacionadas a Data Science (perfil 5)
UPDATE experiencia 
SET colaborador_id = (
    SELECT id FROM colaborador 
    WHERE perfil = 5 
    ORDER BY id 
    LIMIT 1
)
WHERE colaborador_id IS NULL 
AND (
    LOWER(cargo) LIKE '%data%' OR 
    LOWER(cargo) LIKE '%analista%' OR 
    LOWER(cargo) LIKE '%scientist%' OR
    LOWER(cargo) LIKE '%machine%'
);

-- Experiências relacionadas a Mobile (perfil 6)
UPDATE experiencia 
SET colaborador_id = (
    SELECT id FROM colaborador 
    WHERE perfil = 6 
    ORDER BY id 
    LIMIT 1
)
WHERE colaborador_id IS NULL 
AND (
    LOWER(cargo) LIKE '%mobile%' OR 
    LOWER(cargo) LIKE '%android%' OR 
    LOWER(cargo) LIKE '%ios%' OR
    LOWER(cargo) LIKE '%app%'
);

-- 6. Para experiências restantes, distribuir de forma round-robin entre todos os colaboradores
-- Usar uma abordagem que distribui as experiências restantes igualmente

-- Criar uma tabela temporária para distribuição
CREATE TEMPORARY TABLE temp_experiencias_distribuir AS
SELECT 
    e.id as experiencia_id,
    ROW_NUMBER() OVER (ORDER BY e.id) as row_num
FROM experiencia e
WHERE e.colaborador_id IS NULL;

-- Atualizar as experiências restantes usando round-robin
UPDATE experiencia e
JOIN temp_experiencias_distribuir ted ON e.id = ted.experiencia_id
SET e.colaborador_id = (
    SELECT c.id 
    FROM colaborador c 
    ORDER BY c.id 
    LIMIT 1 
    OFFSET ((ted.row_num - 1) % (SELECT COUNT(*) FROM colaborador))
)
WHERE e.colaborador_id IS NULL;

-- Limpar tabela temporária
DROP TEMPORARY TABLE temp_experiencias_distribuir;

-- 7. Verificar resultado final
SELECT 
    c.id,
    c.nome as colaborador_nome,
    c.perfil,
    COUNT(e.id) as total_experiencias,
    'Distribuição final por colaborador' as descricao
FROM colaborador c
LEFT JOIN experiencia e ON c.id = e.colaborador_id
GROUP BY c.id, c.nome, c.perfil
ORDER BY c.id;

-- 8. Verificar se ainda existem experiências órfãs
SELECT 
    COUNT(*) as experiencias_ainda_orfas,
    'Experiências que ainda precisam de colaborador' as status
FROM experiencia 
WHERE colaborador_id IS NULL;

-- 9. Mostrar algumas experiências para verificação
SELECT 
    e.id,
    e.cargo,
    e.empresa,
    e.data_inicio,
    e.data_fim,
    e.colaborador_id,
    c.nome as colaborador_nome,
    c.perfil as colaborador_perfil,
    'Amostra de experiências associadas' as descricao
FROM experiencia e
LEFT JOIN colaborador c ON e.colaborador_id = c.id
ORDER BY e.colaborador_id, e.id
LIMIT 20;