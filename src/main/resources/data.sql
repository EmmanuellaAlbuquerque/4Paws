
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
(120.00, 'Consulta pós-cirúrgica', 'Avaliação completa após os procedimentos cirúrgicos, incluindo exames pós-operatórios.'),
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
(805, 'Manaíra', 'R. Manoel Arruda Cavalcante'),
(342, 'Cabo Branco', 'Av. Epitácio Pessoa'),
(127, 'Tambaú', 'R. Padre Murilo Mota'),
(589, 'Bancários', 'R. Desembargador Madruga'),
(256, 'Bessa', 'R. Governador Flávio Ribeiro'),
(714, 'Jardim Cidade Universitária', 'R. Críspim Gomes'),
(461, 'Cristo Redentor', 'R. Major Antônio Florentino'),
(903, 'Mangabeira', 'R. Prefeito Antônio Silvino'),
(612, 'Torre', 'R. Frei Caneca'),
(237, 'José Américo', 'Av. Primeiro de Maio'),
(548, 'Água Fria', 'R. Antônio Gomes de Lima'),
(176, 'Expedicionários', 'R. General Osório'),
(892, 'Jaguaribe', 'R. Barão de Abiaí'),
(415, 'Pedro Gondim', 'Av. João Machado'),
(653, 'Valentina', 'R. Maria das Neves'),
(289, 'Estados', 'Av. Epitácio Pessoa'),
(734, 'João Paulo II', 'R. Francisca Moura'),
(521, 'Ernesto Geisel', 'R. Dep. Antônio Mariz'),
(367, 'Funcionários', 'R. Desembargador Rodrigues Coelho'),
(601, 'São José', 'Av. Presidente Epitácio Pessoa'),
(218, 'Brisamar', 'R. Clodoaldo Costa'),
(456, 'Castelo Branco', 'Av. das Indústrias'),
(783, 'Alto do Mateus', 'R. Projetada'),
(532, 'Cidade dos Colibris', 'R. das Acácias'),
(647, 'Gramame', 'R. Principal'),
(291, 'Trincheiras', 'R. Dr. José Tavares'),
(915, 'Mandacaru', 'Av. Mercosul'),
(374, 'Ilha do Bispo', 'R. São José'),
(629, 'Oitizeiro', 'R. Dom Pedro II'),
(502, 'Jardim Luna', 'R. Projetada'),
(1542, 'Altiplano', 'R. das Acácias'),
(876, 'Jardim Luna', 'Av. Empresário João Rodrigues'),
(312, 'Torre', 'R. Coronel Salviano'),
(689, 'Miramar', 'R. Desembargador Montenegro'),
(234, 'Expedicionários', 'Av. General Osório'),
(567, 'Brisamar', 'R. Francisco Alves'),
(945, 'Cruz das Armas', 'R. Principal'),
(423, 'Funcionários', 'R. Dr. José Américo'),
(789, 'Mangabeira', 'Av. Presidente Epitácio Pessoa'),
(156, 'Centro', 'R. Duque de Caxias');

-- Adiciona Tutor

INSERT INTO tutors (id, address_id, name, phone, cpf) VALUES
(random_uuid(), 1, 'Manu Albuquerque', '(83) 99371-6599', '388.203.260-00'),
(random_uuid(), 2, 'Maria Vitória Silva', '(98) 95465-3891', '567.160.530-00'),
(random_uuid(), 3, 'João Pedro Oliveira', '(81) 98765-4321', '708.494.580-64'),
(random_uuid(), 4, 'Ana Carolina Santos', '(84) 99234-5678', '123.456.789-00'),
(random_uuid(), 5, 'Rafael Mendes', '(85) 97654-3210', '987.654.321-00'),
(random_uuid(), 6, 'Juliana Costa', '(86) 98123-4567', '456.789.123-00'),
(random_uuid(), 7, 'Carlos Eduardo Rocha', '(87) 99876-5432', '321.654.987-00'),
(random_uuid(), 8, 'Mariana Fernandes', '(88) 97654-8765', '654.321.987-00'),
(random_uuid(), 9, 'Pedro Henrique Almeida', '(89) 98765-1234', '147.258.369-00'),
(random_uuid(), 10, 'Isabela Rodrigues', '(91) 99234-5678', '258.369.147-00'),
(random_uuid(), 11, 'Lucas Gabriel', '(92) 97654-3210', '369.147.258-00'),
(random_uuid(), 12, 'Beatriz Oliveira', '(93) 98123-4567', '741.852.963-00'),
(random_uuid(), 13, 'Mateus Souza', '(94) 99876-5432', '852.963.741-00'),
(random_uuid(), 14, 'Laura Martins', '(95) 97654-8765', '963.741.852-00'),
(random_uuid(), 15, 'André Luiz', '(96) 98765-1234', '159.753.246-00'),
(random_uuid(), 16, 'Camila Rodrigues', '(97) 99234-5678', '246.813.579-00'),
(random_uuid(), 17, 'Felipe Santos', '(98) 97654-3210', '135.792.468-00'),
(random_uuid(), 18, 'Amanda Costa', '(99) 98123-4567', '579.246.813-00'),
(random_uuid(), 19, 'Ricardo Mendes', '(21) 99876-5432', '468.135.792-00'),
(random_uuid(), 20, 'Larissa Silva', '(22) 97654-8765', '813.579.246-00'),
(random_uuid(), 21, 'Fernando Oliveira', '(83) 98765-4321', '024.567.890-12'),
(random_uuid(), 22, 'Mariana Souza', '(83) 97654-3210', '135.678.901-23'),
(random_uuid(), 23, 'Roberto Carlos', '(83) 96543-2109', '246.789-012-34'),
(random_uuid(), 24, 'Juliana Pereira', '(83) 95432-1098', '357.890-123-45'),
(random_uuid(), 25, 'Carlos Alberto', '(83) 94321-0987', '468.901-234-56'),
(random_uuid(), 26, 'Alessandra Lima', '(83) 93210-9876', '579.012-345-67'),
(random_uuid(), 27, 'Daniel Santos', '(83) 92109-8765', '680.123-456-78'),
(random_uuid(), 28, 'Renata Ferreira', '(83) 91098-7654', '791.234-567-89'),
(random_uuid(), 29, 'Marcos Paulo', '(83) 90987-6543', '802.345-678-90'),
(random_uuid(), 30, 'Cristina Almeida', '(83) 89876-5432', '913.456-789-01'),
(random_uuid(), 31, 'Sophia Andrade', '(83) 99234-5678', '123.456.789-01'),
(random_uuid(), 32, 'Rafael Mendes', '(83) 98123-4567', '234.567.890-23'),
(random_uuid(), 33, 'Isabela Correa', '(83) 97654-3210', '345.678.901-34'),
(random_uuid(), 34, 'Lucas Oliveira', '(83) 96543-2109', '456.789.012-45'),
(random_uuid(), 35, 'Beatriz Santos', '(83) 95432-1098', '567.890.123-56'),
(random_uuid(), 36, 'André Pereira', '(83) 94321-0987', '678.901.234-67'),
(random_uuid(), 37, 'Laura Castro', '(83) 93210-9876', '789.012.345-78'),
(random_uuid(), 38, 'Gustavo Rocha', '(83) 92109-8765', '890.123.456-89'),
(random_uuid(), 39, 'Camila Nascimento', '(83) 91098-7654', '901.234.567-90'),
(random_uuid(), 40, 'Diego Fonseca', '(83) 90987-6543', '012.345.678-01');

-- Adiciona Pets

INSERT INTO pets (id, birth_date, weight, tutor_id, breed, name, sex, specie) VALUES
(random_uuid(), '2020-09-17', 4.5, (SELECT id FROM tutors WHERE cpf = '388.203.260-00'), 'siamês', 'Dama de Preto', 'FEMEA', 'FELINA'),
(random_uuid(), '2019-09-17', 27.2, (SELECT id FROM tutors WHERE cpf = '388.203.260-00'), 'SRD', 'Duquesa', 'FEMEA', 'CANINA');

-- Adiciona Consulta
INSERT INTO appointments (appointment_type_id, scheduled_date, pet_id, notes) VALUES
(1, '2025-10-10 09:00:00', (SELECT id FROM pets WHERE name = 'Dama de Preto'), 'O paciente foi examinado durante a consulta de rotina e não apresenta sinais de infecção ou outras anormalidades aparentes. Para obter informações mais detalhadas sobre o estado de saúde, foi solicitado o exame físico geral e o hemograma completo.'),
(5, '2025-10-10 10:00:00', (SELECT id FROM pets WHERE name = 'Duquesa'), null),
(2, '2024-10-10 10:00:00', (SELECT id FROM pets WHERE name = 'Duquesa'), null);

-- Adiciona Veterinários a Consulta
INSERT INTO appointment_veterinarian (appointment_id, veterinarian_id) VALUES
(1, (SELECT id FROM user_profiles WHERE email = 'tonia-vet@example.com')),
(2, (SELECT id FROM user_profiles WHERE email = 'tonia-vet@example.com')),
(3, (SELECT id FROM user_profiles WHERE email = 'benjamin-vet@example.com'));

-- Adiciona Exames
INSERT INTO exams (id, appointment_id, exam_type_id, scheduled_date, result) VALUES
(random_uuid(), 1, 5, '2025-10-10 09:10:00', 'O paciente apresenta bom estado geral. Boa hidratação. Temperatura corporal dentro da normalidade: 38,5°C. Frequência cardíaca dentro do esperado: 90 bpm. Dentes limpos, sem acúmulo excessivo de tártaro. Pelagem brilhante e sem áreas de alopecia.'),
(random_uuid(), 1, 1, '2025-10-15 09:10:00', null),
(random_uuid(), 3, 5, '2024-10-15 09:10:00', 'O paciente está OK');

-- Adiciona Prescrições
INSERT INTO prescriptions (id, appointment_id, medicine, dosage_description) VALUES
(random_uuid(), 1, 'Suplemento vitamínico', 'Administrar 1 comprimido por dia durante 30 dias'),
(random_uuid(), 1, 'Antiparasitário', 'Aplicar uma dose única, repetir em 3 meses'),
(random_uuid(), 3, 'Alantol (Pomada Cicatrizante)', 'Aplicar 1 a 3 vezes ao dia');
