INSERT INTO `usuario` (`id`, `email`, `nome`, `senha`) VALUES 
(NULL, 'teste@gmail.com', 'mayki', '$2a$10$8YKWPIpnP2De/qUi5g41zOl3SFMmaSF1EnvZiREu./Urh0Qcf0veG');

INSERT INTO `categoria` (`id`, `nome`) VALUES 
(NULL, 'Ação'), (NULL, 'Aventura'), 
(NULL, 'Romance'), 
(NULL, 'Tecnico'),
(NULL, 'História');

INSERT INTO `dono` (`id`, `nome`) VALUES 
(NULL, 'Mayki'), 
(NULL, 'Monick'), 
(NULL, 'Andre');

INSERT INTO `livros` (`id`, `autor`, `titulo`, `dono_id`) VALUES 
(NULL, 'Thomas Nield', 'Introdução a linguagem sql', '1'), 
(NULL, 'Paulo Cougo', 'Modelagem Conceitual', '1'), 
(NULL, 'Vitoria sants', 'Vulvas a solta', '3'), 
(NULL, 'Strongnava Tolas', 'O caminhar andado', '2'),
(NULL, 'Alexandre Saudate', 'APIs REST em Kotlin', '1'), 
(NULL, 'Tiago Silva', 'Coleção Frameworks Python', 1), 
(NULL, 'Everton Coimbra de Araújo', 'Aprofundando em Flutter', 1), 
(NULL, 'Paulo Siécola', 'Web Services REST com ASP .NET Web API e Windows Azure', 1), 
(NULL, 'Tatiana Escovedo e Adriano Koshiyama', 'Introdução a Data Science', 1), 
(NULL, 'Leonardo H. Marinho', 'Iniciando com Flutter Framework', 1), 
(NULL, 'Eduardo Felipe Zambom Santana', 'Back-end Java', 1), 
(NULL, 'Rodrigo Turini', 'Java 9', NULL), (NULL, 'Guilherme Silveira', 'Algoritmos em Java', 1), 
(NULL, 'Guilherme Silveira e Mário Amaral', 'Java SE 8 Programmer I', 1);

INSERT INTO `livros_categoria` (`livro_id`, `categoria_id`) VALUES 
('2', '2'), 
('2', '3'), 
('4', '4'), 
('1', '1'), 
('3', '4'), 
('3', '3'),
('6', '5'), 
('7', '5'), 
('8', '5'), 
('9', '5'), 
('10', '5'), 
('11', '5'), 
('12', '5'), 
('13', '5'), 
('14', '5');