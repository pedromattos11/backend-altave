-- Criar colaborador Pedro Mattos com o mesmo email do usuário
-- Este colaborador será vinculado ao usuário criado na V15

INSERT INTO colaborador (nome, email, cpf, apresentacao, perfil, cargo_id) VALUES 
('Pedro Mattos', 'pedro@altave.com', 12345678901, 'Desenvolvedor full stack com expertise em tecnologias modernas.', 1, 1)
ON DUPLICATE KEY UPDATE nome='Pedro Mattos';


