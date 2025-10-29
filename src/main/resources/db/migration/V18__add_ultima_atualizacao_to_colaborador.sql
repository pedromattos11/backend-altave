-- Adicionar coluna ultima_atualizacao na tabela colaborador
ALTER TABLE colaborador ADD COLUMN ultima_atualizacao DATETIME NULL;

-- Atualizar registros existentes com a data atual
UPDATE colaborador SET ultima_atualizacao = NOW() WHERE ultima_atualizacao IS NULL;
