-- Inserir usuário de teste para login
-- Email: pedro@altave.com
-- Senha: 123456

INSERT INTO usuarios (nome_completo, data_nascimento, cpf, email, senha, role) VALUES 
('Pedro Mattos', '1990-01-01', '12345678901', 'pedro@altave.com', '123456', 'ADMIN')
ON DUPLICATE KEY UPDATE nome_completo='Pedro Mattos';

