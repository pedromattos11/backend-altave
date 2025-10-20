-- Dropar a tabela colaborador_experiencia que não é mais necessária
-- pois agora temos o relacionamento direto via colaborador_id na tabela experiencia

-- Verificar se a tabela existe antes de dropar
SET @sql = (SELECT IF(
    (SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES 
     WHERE TABLE_NAME = 'colaborador_experiencia' 
     AND TABLE_SCHEMA = 'railway') > 0,
    'DROP TABLE colaborador_experiencia;',
    'SELECT "Table colaborador_experiencia does not exist";'
));
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;