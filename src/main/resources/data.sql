
-- Dados DEFAULT do banco H2

-- Adiciona roles padrões
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_VETERINARIO');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_RECEPCIONISTA');

-- Adiciona Admin Padrão
INSERT INTO user_profiles (role_id, id, cpf, email, name, password)
    VALUES (1, random_uuid(), '702.481.800-02', 'admin@example.com', 'Admin', '$2a$10$zFUNeDm2em5A49wh6Ro5Jeswj2wsPBN5ZzdGJ5n6ny.XxvDiPXK1K');

-- Adiciona Recepcionista
INSERT INTO user_profiles (role_id, id, cpf, email, name, password)
    VALUES (3, random_uuid(), '789.146.490-16', 'maria-recep@example.com', 'Rosa Maria Allana', '$2a$10$zFUNeDm2em5A49wh6Ro5Jeswj2wsPBN5ZzdGJ5n6ny.XxvDiPXK1K');

-- Adiciona Veterinários

-- VET 1

INSERT INTO user_profiles (role_id, id, cpf, email, name, password)
    VALUES (2, random_uuid(), '963.429.730-75', 'benjamin-vet@example.com', 'Benjamin Vicente Fernandes', '$2a$10$zFUNeDm2em5A49wh6Ro5Jeswj2wsPBN5ZzdGJ5n6ny.XxvDiPXK1K');

INSERT INTO veterinarians (id, crmv, uf)
    VALUES ((SELECT id FROM user_profiles WHERE email = 'benjamin-vet@example.com'), 9012, 'MG');

-- VET 2

INSERT INTO user_profiles (role_id, id, cpf, email, name, password)
    VALUES (2, random_uuid(), '200.447.390-87', 'tonia-vet@example.com', 'Antônia Yasmin Marcela Araújo', '$2a$10$zFUNeDm2em5A49wh6Ro5Jeswj2wsPBN5ZzdGJ5n6ny.XxvDiPXK1K');

INSERT INTO veterinarians (id, crmv, specialty, uf)
    VALUES ((SELECT id FROM user_profiles WHERE email = 'tonia-vet@example.com'), 3456, 'MEDICINA_INTENSIVA_VETERINARIA','RS');

-- VET 3

-- Tipos de Consulta

INSERT INTO exam_types (base_price, name, description) VALUES
(80.00, 'Hemograma completo', 'Análise detalhada das células sanguíneas para avaliar saúde geral e detectar infecções, anemias e outras condições.'),
(50.00, 'Urinálise', 'Exame da urina para detectar infecções urinárias, problemas renais e metabólicos.'),
(150.00, 'Radiografia', 'Imagem de raio-X para visualizar ossos, órgãos internos e detectar fraturas, tumores ou objetos estranhos.'),
(40.00, 'Exame parasitológico de fezes', 'Análise das fezes para detectar parasitas internos.'),
(60.00, 'Exame físico geral', 'Avaliação completa do estado de saúde do animal, incluindo verificação de peso, temperatura, frequência cardíaca e respiratória.');

INSERT INTO appointment_types (base_price, name, description) VALUES
(100.00, 'Consulta de rotina', 'Check-up geral para avaliar a saúde do animal, incluindo exame físico completo e atualização de vacinas.'),
(120.00, 'Consulta pré-cirúrgica', 'Avaliação completa antes de procedimentos cirúrgicos, incluindo exames pré-operatórios.'),
(200.00, 'Consulta de emergência', 'Atendimento imediato para casos urgentes como acidentes, intoxicações ou doenças súbitas.'),
(130.00, 'Consulta odontológica', 'Avaliação da saúde bucal, incluindo exame dos dentes e gengivas.'),
(150.00, 'Consulta dermatológica', 'Avaliação especializada para problemas de pele, alergias e condições do pelo.'),
(90.00, 'Consulta de acompanhamento', 'Revisão após tratamento ou cirurgia para avaliar a recuperação e ajustar o plano de tratamento se necessário.');

INSERT INTO user_profiles (role_id, id, cpf, email, name, password)
    VALUES (2, random_uuid(), '984.712.220-27', 'vi-vet@example.com', 'Vitória Luna Isabela', '$2a$10$zFUNeDm2em5A49wh6Ro5Jeswj2wsPBN5ZzdGJ5n6ny.XxvDiPXK1K');

INSERT INTO veterinarians (id, crmv, specialty, uf)
    VALUES ((SELECT id FROM user_profiles WHERE email = 'vi-vet@example.com'), 7890, 'DERMATOLOGIA','PR');

-- Adiciona Endereço

INSERT INTO addresses (number, neighborhood, street) VALUES
(190, 'Centro', 'Rua Boa Vista');

-- Adiciona Tutor

INSERT INTO tutors (id, address_id, name, phone, cpf) VALUES
(random_uuid(), 1, 'Manu Albuquerque', '(83) 97087-0821', '388.203.260-00');

-- Adiciona Pets

INSERT INTO pets (id, birth_date, weight, tutor_id, breed, name, sex, specie) VALUES
(random_uuid(), '2020-09-17', 4.5, (SELECT id FROM tutors WHERE cpf = '388.203.260-00'), 'siamês', 'Dama de Preto', 'FEMEA', 'FELINA'),
(random_uuid(), '2019-09-17', 27.2, (SELECT id FROM tutors WHERE cpf = '388.203.260-00'), 'SRD', 'Duquesa', 'FEMEA', 'CANINA');

-- Adiciona Consulta
INSERT INTO appointments (appointment_type_id, scheduled_date, pet_id, notes) VALUES
(1, '2025-10-10 09:00:00', (SELECT id FROM pets WHERE name = 'Dama de Preto'), 'O paciente foi examinado durante a consulta de rotina e não apresenta sinais de infecção ou outras anormalidades aparentes. Para obter informações mais detalhadas sobre o estado de saúde, foi solicitado o exame físico geral e o hemograma completo.'),
(5, '2025-10-10 10:00:00', (SELECT id FROM pets WHERE name = 'Duquesa'), null);

-- Adiciona Veterinários a Consulta
INSERT INTO appointment_veterinarian (appointment_id, veterinarian_id) VALUES
(1, (SELECT id FROM user_profiles WHERE email = 'tonia-vet@example.com')),
(2, (SELECT id FROM user_profiles WHERE email = 'tonia-vet@example.com'));

-- Adiciona Exames
INSERT INTO exams (id, appointment_id, exam_type_id, scheduled_date, result) VALUES
(random_uuid(), 1, 5, '2025-10-10 09:10:00', 'O paciente apresenta bom estado geral. Boa hidratação. Temperatura corporal dentro da normalidade: 38,5°C. Frequência cardíaca dentro do esperado: 90 bpm. Dentes limpos, sem acúmulo excessivo de tártaro. Pelagem brilhante e sem áreas de alopecia.'),
(random_uuid(), 1, 1, '2025-10-15 09:10:00', null);

-- Adiciona Prescrições
INSERT INTO prescriptions (id, appointment_id, medicine, dosage_description) VALUES
(random_uuid(), 1, 'Suplemento vitamínico', 'Administrar 1 comprimido por dia durante 30 dias'),
(random_uuid(), 1, 'Antiparasitário', 'Aplicar uma dose única, repetir em 3 meses');
