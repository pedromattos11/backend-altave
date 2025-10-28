-- Migration para aumentar o limite de caracteres do campo apresentacao
-- Altera de VARCHAR(100) para VARCHAR(2000)

ALTER TABLE colaborador 
MODIFY COLUMN apresentacao VARCHAR(2000);

