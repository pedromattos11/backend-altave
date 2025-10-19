-- Limpar todas as experiências de teste criadas
DELETE FROM colaborador_experiencia;
DELETE FROM experiencia WHERE cargo LIKE '%Teste%' OR cargo LIKE '%teste%' OR cargo = '1';

-- Adicionar coluna colaborador_id diretamente na tabela experiencia
ALTER TABLE experiencia ADD COLUMN colaborador_id INT;

-- Criar foreign key para o colaborador
ALTER TABLE experiencia ADD FOREIGN KEY (colaborador_id) REFERENCES colaborador(id);

-- Migrar dados existentes da tabela de relacionamento para a nova coluna
UPDATE experiencia e 
SET colaborador_id = (
    SELECT ce.colaborador_id 
    FROM colaborador_experiencia ce 
    WHERE ce.experiencia_id = e.id 
    LIMIT 1
);

-- Remover a tabela de relacionamento já que não precisamos mais
DROP TABLE colaborador_experiencia;