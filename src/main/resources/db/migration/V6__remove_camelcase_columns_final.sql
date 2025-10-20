-- Remover definitivamente as colunas camelCase duplicadas
-- que foram recriadas pelo Hibernate

-- Verificar e remover dataInicio
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

-- Verificar e remover dataFim
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