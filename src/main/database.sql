--DROP SCHEMA public CASCADE;
--CREATE SCHEMA public;

create table produtos(
	id serial primary key,
	nome varchar(100) not null,
	descricao text,
	preco decimal(6,2) not null,
	estoque int not null
);

create table clientes(
	id serial primary key,
	nome varchar(100) not null,
	email varchar (100) unique not null,
	endereco varchar(200)
);

create table telefones(
	--id serial primary key,
	id_cliente int not null,
	telefone varchar(20) not null,
	foreign key(id_cliente) references clientes(id)
)

create table pedidos(
	id serial primary key,
	produto_id int not null,
	cliente_id int not null,
	quantidade int not null,
	pedido_id int not null,
	data_pedido date default current_date,
	foreign key (produto_id) references produtos(id),
	foreign key (cliente_id) references clientes(id)
);

create sequence id_pedido start 1;

--MOCKS gerei no chatgpt

INSERT INTO clientes (nome, email, endereco) VALUES 
('Ana Souza', 'ana.souza@example.com', 'Rua das Flores, 123, São Paulo, SP'),
('Carlos Silva', 'carlos.silva@example.com', 'Avenida Brasil, 456, Rio de Janeiro, RJ'),
('Juliana Pereira', 'juliana.pereira@example.com', 'Rua Vitória, 789, Belo Horizonte, MG'),
('Ricardo Oliveira', 'ricardo.oliveira@example.com', 'Rua das Palmeiras, 101, Curitiba, PR'),
('Mariana Costa', 'mariana.costa@example.com', 'Rua dos Ipês, 202, Porto Alegre, RS'),
('Gabriel Martins', 'gabriel.martins@example.com', 'Quadra 5, Bloco A, Brasília, DF'),
('Laura Rocha', 'laura.rocha@example.com', 'Rua Nova, 303, Recife, PE'),
('Fernando Alves', 'fernando.alves@example.com', 'Avenida do Contorno, 404, Fortaleza, CE'),
('Roberta Lima', 'roberta.lima@example.com', 'Rua das Acácias, 410, Salvador, BA'),
('Eduardo Santos', 'eduardo.santos@example.com', 'Praça da Sé, 98, São Paulo, SP'),
('Patrícia Almeida', 'patricia.almeida@example.com', 'Rua João Pessoa, 450, Recife, PE'),
('Tiago Pereira', 'tiago.pereira@example.com', 'Avenida Afonso Pena, 234, Belo Horizonte, MG'),
('Marcia Costa', 'marcia.costa@example.com', 'Rua do Comércio, 872, Rio de Janeiro, RJ'),
('João Silva', 'joao.silva@example.com', 'Rua das Margaridas, 56, Fortaleza, CE'),
('Luciana Martins', 'luciana.martins@example.com', 'Avenida Amazonas, 380, Curitiba, PR'),
('Vinícius Souza', 'vinicius.souza@example.com', 'Rua dos Lírios, 321, Porto Alegre, RS'),
('Fabiana Rocha', 'fabiana.rocha@example.com', 'Rua Sete de Setembro, 112, Natal, RN'),
('Marcos Oliveira', 'marcos.oliveira@example.com', 'Rua Almirante Tamandaré, 65, Florianópolis, SC');

INSERT INTO telefones (id_cliente, telefone) VALUES
(1, '111-2345-6789'),
(2, '222-3456-7890'),
(3, '333-4567-8901'),
(4, '444-5678-9012'),
(5, '555-6789-0123'),
(6, '666-7890-1234'),
(7, '777-8901-2345'),
(8, '888-9012-3456'),
(9, '999-0123-4567'),
(10, '101-1234-5678'),
(11, '212-2345-6789'),
(12, '323-3456-7890'),
(13, '434-4567-8901'),
(14, '545-5678-9012'),
(15, '656-6789-0123'),
(16, '767-7890-1234'),
(17, '878-8901-2345'),
(18, '989-9012-3456'),
(1, '121-2345-6789'),
(2, '131-3456-7890'),
(3, '141-4567-8901'),
(4, '151-5678-9012'),
(5, '161-6789-0123'),
(6, '171-7890-1234'),
(7, '181-8901-2345'),
(8, '191-9012-3456');


INSERT INTO produtos (nome, descricao, preco, estoque) VALUES
('Cafeteira Expresso', 'Máquina de café expresso com 15 barras de pressão e função de cappuccino.', 299.90, 60),
('Câmera DSLR', 'Câmera digital com lente 18-55mm, ideal para fotos de alta qualidade.', 2499.90, 25),
('Monitor LED 24"', 'Monitor Full HD de 24 polegadas com entradas HDMI e VGA.', 499.90, 150),
('Cadeira Escritório', 'Cadeira ergonômica com ajuste de altura e encosto reclinável.', 399.90, 80),
('Jogo de Panelas', 'Conjunto de panelas de alumínio antiaderente, com 5 peças.', 179.90, 100),
('Tablet 10" Android', 'Tablet com tela de 10 polegadas, 64GB de armazenamento e 4GB de RAM.', 899.90, 70),
('Kit Maquiagem', 'Kit completo com base, corretivo, blush e batons de diversas cores.', 129.90, 200),
('Máquina de Lavar Roupas', 'Lavadora de roupas com capacidade para 12kg e 10 ciclos de lavagem.', 899.90, 50),
('Chaleira Elétrica', 'Chaleira elétrica com capacidade de 1,7L e desligamento automático.', 79.90, 150),
('Carregador Solar', 'Carregador portátil solar para celulares, com 10000mAh.', 119.90, 120),
('Micro-ondas 20L', 'Micro-ondas com 20L de capacidade, 5 potências e timer digital.', 349.90, 80),
('Console de Videogame', 'Console de videogame com 500GB de armazenamento e 2 controles inclusos.', 1799.90, 40),
('Câmera de Segurança', 'Câmera de segurança Wi-Fi, com visão noturna e sensor de movimento.', 249.90, 60),
('Tênis Casual', 'Tênis confortável, ideal para o uso diário, disponível em várias cores.', 129.90, 150),
('Sofá 3 Lugares', 'Sofá de 3 lugares com tecido macio e estrutura de madeira resistente.', 899.90, 30),
('Ventilador de Mesa', 'Ventilador de mesa com 3 velocidades e oscilação horizontal.', 99.90, 180),
('Roupão de Banho', 'Roupão de banho de microfibra, disponível em várias cores e tamanhos.', 79.90, 250),
('Secador de Cabelos', 'Secador de cabelo com 2 velocidades, 3 temperaturas e tecnologia iônica.', 149.90, 120),
('Xbox One', 'Console Xbox One, com 1TB de armazenamento e 2 jogos inclusos.', 2499.90, 35),
('Aspirador de Pó Vertical', 'Aspirador de pó sem fio, com filtro HEPA e 2 níveis de potência.', 299.90, 60);


INSERT INTO pedidos (produto_id, cliente_id, quantidade, data_pedido, pedido_id) VALUES
(5, 3, 2, '2024-12-12 08:15:23',1),
(10, 7, 3, '2024-12-13 09:45:10',2),
(12, 9, 1, '2024-12-12 14:30:56',3),
(2, 6, 4, '2024-12-14 16:12:05',4),
(15, 1, 2, '2024-12-13 13:30:45',5),
(6, 14, 1, '2024-12-14 10:23:17',6),
(18, 4, 5, '2024-12-12 18:05:32',7),
(8, 11, 3, '2024-12-13 20:12:56',8),
(13, 17, 2, '2024-12-14 07:45:28',9),
(1, 2, 4, '2024-12-12 16:50:21'10),
(20, 5, 1, '2024-12-13 11:22:10',11),
(9, 12, 3, '2024-12-12 19:30:34',12),
(14, 18, 1, '2024-12-14 09:12:45',13),
(3, 16, 2, '2024-12-13 15:43:22',14),
(17, 8, 1, '2024-12-12 21:30:08',15),
(7, 13, 4, '2024-12-13 22:01:44',16),
(11, 10, 2, '2024-12-14 12:00:30',17),
(4, 15, 3, '2024-12-12 17:40:19',18),
(16, 2, 1, '2024-12-13 08:30:02',19),
(19, 14, 2, '2024-12-12 10:10:56',20);



