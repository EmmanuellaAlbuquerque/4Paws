
-- Dados DEFAULT do banco H2

-- Adiciona roles padrões
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_VETERINARIO');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_RECEPCIONISTA');

-- Adiciona admin padrão (senha: 123)
INSERT INTO user_profiles (role_id, id, cpf, email, name, password)
    VALUES (1, '72252574-f9f2-4569-a3c3-80675bd5fb36', '000.000.000-00', 'manu@gmail.com', 'Emmanuella Albuquerque', '$2a$10$zFUNeDm2em5A49wh6Ro5Jeswj2wsPBN5ZzdGJ5n6ny.XxvDiPXK1K');

INSERT INTO user_profiles (role_id, id, cpf, email, name, password)
    VALUES (3, 'b1b2f126-ab19-4503-b210-f00caede98c0', '000.000.000-00', 'email@example.com', 'Recepcionista 1', '$2a$10$zFUNeDm2em5A49wh6Ro5Jeswj2wsPBN5ZzdGJ5n6ny.XxvDiPXK1K');

INSERT INTO user_profiles (role_id, id, cpf, email, name, password)
    VALUES (2, 'f87bb204-2545-4f41-90f7-48badbb5bae5', '000.000.000-00', 'vet@example.com', 'Veterinário 1', '$2a$10$zFUNeDm2em5A49wh6Ro5Jeswj2wsPBN5ZzdGJ5n6ny.XxvDiPXK1K');

INSERT INTO veterinarians (id, crmv, specialty, uf)
    VALUES ('f87bb204-2545-4f41-90f7-48badbb5bae5', '9878', 'CIRURGIA','PB');

INSERT INTO user_profiles (role_id, id, cpf, email, name, password)
    VALUES (2, '330b358e-0945-4161-a8d5-a4514d73c78f', '000.000.000-00', 'vet2@example.com', 'Veterinário 2', '$2a$10$zFUNeDm2em5A49wh6Ro5Jeswj2wsPBN5ZzdGJ5n6ny.XxvDiPXK1K');

INSERT INTO veterinarians (id, crmv, specialty, uf)
    VALUES ('330b358e-0945-4161-a8d5-a4514d73c78f', '9878', 'CIRURGIA','PB');

-- Inserindo dados para testes manuais

-- Insert data into ADDRESSES table
INSERT INTO ADDRESSES (ID, NUMBER, NEIGHBORHOOD, STREET) VALUES
(1, '123', 'Centro', 'Rua Principal'),
(2, '456', 'Bairro Novo', 'Avenida Secundária'),
(3, '789', 'Jardim', 'Rua das Flores');

-- Insert data into TUTORS table
INSERT INTO TUTORS (ADDRESS_ID, ID, NAME, PHONE) VALUES
(1, '02ed9f8b-e529-4eb5-81c1-9ae78588d283', 'João Silva', '(83) 99999-1111'),
(2, '12971a56-5bcf-4527-8f92-9a12d2b76cb4', 'Maria Oliveira', '(83) 99999-2222'),
(3, 'a049b975-bb29-478f-8e37-abc7629e9e7c', 'Carlos Santos', '(83) 99999-3333');

-- Insert data into PETS table
INSERT INTO PETS (BIRTH_DATE, WEIGHT, ID, TUTOR_ID, BREED, NAME, SEX, SPECIE) VALUES
('2020-01-15', 5.5, 'd9e1a7b5-ad18-41fd-b84e-0665eff4fa91', '12971a56-5bcf-4527-8f92-9a12d2b76cb4', 'Labrador', 'Rex', 'MACHO', 'CANINA'),
('2019-05-20', 4.0, '54c015f4-8309-4af6-9ba5-2f9598310bfe', '12971a56-5bcf-4527-8f92-9a12d2b76cb4', 'Siamese', 'Luna', 'FEMEA', 'FELINA');

-- Insert data into APPOINTMENT_TYPES table
INSERT INTO APPOINTMENT_TYPES (BASE_PRICE, ID, DESCRIPTION, NAME) VALUES
(100.00, 1, 'Consulta de rotina', 'Consulta Padrão'),
(150.00, 2, 'Vacinação', 'Vacinação'),
(200.00, 3, 'Cirurgia simples', 'Cirurgia Menor');

-- Insert data into APPOINTMENTS table
INSERT INTO APPOINTMENTS (APPOINTMENT_TYPE_ID, ID, SCHEDULED_DATE, PET_ID, NOTES) VALUES
(3, 1, '2024-09-12 09:00:00', '54c015f4-8309-4af6-9ba5-2f9598310bfe', 'Remoção de tumor benigno'),
(1, 3, '2024-09-10 16:00:00', 'd9e1a7b5-ad18-41fd-b84e-0665eff4fa91', 'Checkup anual'),
(1, 4, '2024-09-09 11:00:00', 'd9e1a7b5-ad18-41fd-b84e-0665eff4fa91', 'Checkup mensal'),
(2, 2, '2024-09-11 14:30:00', 'd9e1a7b5-ad18-41fd-b84e-0665eff4fa91', 'Vacinação contra raiva');

-- Insert data into APPOINTMENT_VETERINARIAN table
INSERT INTO APPOINTMENT_VETERINARIAN (APPOINTMENT_ID, VETERINARIAN_ID) VALUES
(1, 'f87bb204-2545-4f41-90f7-48badbb5bae5'),
(2, 'f87bb204-2545-4f41-90f7-48badbb5bae5'),
(3, 'f87bb204-2545-4f41-90f7-48badbb5bae5'),
(4, 'f87bb204-2545-4f41-90f7-48badbb5bae5');

-- Insert data into EXAM_TYPES table
INSERT INTO EXAM_TYPES (BASE_PRICE, ID, DESCRIPTION, NAME) VALUES
(80.00, 1, 'Exame de sangue completo', 'Hemograma'),
(120.00, 2, 'Raio-X', 'Radiografia'),
(100.00, 3, 'Teste de urina', 'Urinálise');

-- Insert data into EXAMS table
INSERT INTO EXAMS (APPOINTMENT_ID, EXAM_TYPE_ID, SCHEDULED_DATE, ID, RESULT) VALUES
(1, 1, '2024-09-10 11:00:00', random_uuid(), 'Resultados normais'),
(2, 2, '2024-09-11 15:30:00', random_uuid(), 'Sem alterações significativas'),
(3, 3, '2024-09-12 10:00:00', random_uuid(), 'Pendente');

-- Insert data into PRESCRIPTIONS table
INSERT INTO PRESCRIPTIONS (APPOINTMENT_ID, ID, DOSAGE_DESCRIPTION, MEDICINE) VALUES
(1, random_uuid(), '1 comprimido por dia durante 7 dias', 'Vitamina C'),
(2, random_uuid(), '1 aplicação única', 'Vacina Antirrábica'),
(3, random_uuid(), '1 comprimido a cada 12 horas por 5 dias', 'Antibiótico');

