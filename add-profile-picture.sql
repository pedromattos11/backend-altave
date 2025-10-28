-- Adicionar coluna profile_picture_path na tabela colaborador
ALTER TABLE colaborador ADD COLUMN IF NOT EXISTS profile_picture_path VARCHAR(255);

-- Verificar se a coluna foi adicionada
DESCRIBE colaborador;

