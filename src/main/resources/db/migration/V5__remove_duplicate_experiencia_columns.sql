-- Remover colunas duplicadas com nomes em camelCase da tabela experiencia
-- Manter apenas as colunas com nomenclatura snake_case (data_inicio, data_fim)

-- Verificar se as colunas existem antes de removê-las
SET @sql = (SELECT IF(
    (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
     WHERE TABLE_NAME = 'experiencia' 
     AND TABLE_SCHEMA = 'railway' 
     AND COLUMN_NAME = 'dataInicio') > 0,
    'ALTER TABLE experiencia DROP COLUMN dataInicio;',
    'SELECT "Column dataInicio does not exist";'
));
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(
    (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
     WHERE TABLE_NAME = 'experiencia' 
     AND TABLE_SCHEMA = 'railway' 
     AND COLUMN_NAME = 'dataFim') > 0,
    'ALTER TABLE experiencia DROP COLUMN dataFim;',
    'SELECT "Column dataFim does not exist";'
));
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
