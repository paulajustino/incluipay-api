INSERT INTO users (name, email, password, role)
VALUES ('usuario admin', 'admin@incluipay.com', '$2a$10$lBtcaS2jst/gFqijA13cp.Dg9hQ1X0faYeSaqnCp7j7Dwu27A01ri', 'ADMIN');

INSERT INTO projects (title, description, category, organization, goal_amount)
VALUES ('Biblioteca Acessível', 'Digitalização de livros para pessoas cegas.', 'FERRAMENTAS_ASSISTIVAS', 'Org. Visão Futura', 5000.00);

INSERT INTO projects (title, description, category, organization, goal_amount)
VALUES ('Cursos de Código para PCDs', 'Financiamento de bolsas para 50 alunos.', 'CURSOS_TECNOLOGIA', 'Devs Inclusivos', 10000.00);

INSERT INTO projects (title, description, category, organization, goal_amount, status)
VALUES ('Projeto de Teste', 'Este projeto é pra teste.', 'OUTROS', 'Org. Teste', 100.00, 'INACTIVE');