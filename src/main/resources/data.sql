
-- Dados DEFAULT do banco H2

-- Adiciona roles padrões
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_VETERINARIO');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_RECEPCIONISTA');

-- Adiciona admin padrão (senha: 123)
INSERT INTO user_profiles (role_id, id, cpf, email, name, password)
    VALUES (1, 'aa305fcc-64ba-49e1-abe9-d6b9fd45c306', '000.000.000.-00', 'manu@gmail.com', 'Emmanuella Albuquerque', '$2a$10$zFUNeDm2em5A49wh6Ro5Jeswj2wsPBN5ZzdGJ5n6ny.XxvDiPXK1K');

