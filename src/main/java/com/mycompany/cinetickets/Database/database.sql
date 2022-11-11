DROP DATABASE if exists cinetickets;
create database if not exists cinetickets;
use cinetickets;

CREATE TABLE cliente (
cpf VARCHAR(11) PRIMARY KEY,
nome VARCHAR(45) NOT NULL,
dataNascimento DATE NOT NULL,
email VARCHAR(100) NOT NULL,
senha VARCHAR(45) NOT NULL 
);

CREATE TABLE filme (
idFilme INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(45) NOT NULL,
genero VARCHAR(45) NOT NULL,
lancamento DATE NOT NULL,
duracao TIME NOT NULL,
classificacao enum ('Livre','10 anos','12 anos','14 anos','16 anos','18 anos'),
poster text
);

CREATE TABLE sala (
numeroSala INT PRIMARY KEY,
qtdAssentos INT NOT NULL
);

create table sessao (
	dataHora datetime not null,
    idFilme int not null,
    numeroSala int not null,
    
    constraint pkDataHoraNumeroSala primary key (dataHora, numeroSala),
    constraint fkIdFilme foreign key (idFilme) references filme (idFilme),
    constraint fkNumeroSala foreign key (numeroSala) references sala (numeroSala)
);

CREATE TABLE ticket (
idTicket INT AUTO_INCREMENT PRIMARY KEY,
cpfCliente varchar(11) not null,
idFilme INT,
numeroSala INT,
dataHora DATETIME NOT NULL,
preco DECIMAL(5,2) NULL DEFAULT 0.00,
assento varchar(4) NOT NULL,
versao3d BOOL NULL DEFAULT 0,
legendado BOOL NULL DEFAULT 0,
meiaEntrada BOOL NULL DEFAULT 0,

constraint fkDataHoraNumeroSala foreign key (dataHora, numeroSala) references sessao (dataHora, numeroSala),
FOREIGN KEY (cpfCliente) REFERENCES cliente (cpf)
ON DELETE CASCADE
ON UPDATE CASCADE
);
insert into filme (nome, genero, lancamento, duracao, classificacao, poster) values ('Sonic 2','Animação','2022-04-07', 20200,'10 anos','https://jovemnerd.com.br/wp-content/uploads/2022/02/Poster-de-Sonic-2-760x950.jpg');
insert into filme (nome, genero, lancamento, duracao, classificacao, poster) values ('Doutor Estranho no Multiverso da Loucura','Fantasia','2022-05-05', 20600,'14 anos', 'https://upload.wikimedia.org/wikipedia/pt/0/08/Doctor_Strange_in_the_Multiverse_of_Madness_poster.jpeg');
insert into filme (nome, genero, lancamento, duracao, classificacao, poster) values ('Jujutsu Kaisen 0','Anime','2022-04-28', 14500,'14 anos', 'https://d1fz32b7q4znht.cloudfront.net/wp-content/uploads/2021/10/Jujutsu-Kaisen-0-GQCA-poster.jpg');
insert into filme (nome, genero, lancamento, duracao, classificacao, poster) values ('Medida Provisória','Drama','2022-04-14', 13400,'14 anos', 'https://cineclick-static.flixmedia.cloud/1280/processed/518f41d8-1a6c-4692-8842-f806d194577d.webp');
insert into filme (nome, genero, lancamento, duracao, classificacao, poster) values ('Jurassic World: Domínio','Ficção Científica','2022-06-02', 22700,'12 anos', 'https://m.media-amazon.com/images/M/MV5BOTBjMjA4NmYtN2RjMi00YWZlLTliYTktOTIwMmNkYjYxYmE1XkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_FMjpg_UX1000_.jpg');
insert into filme (nome, genero, lancamento, duracao, classificacao, poster) values ('Cidade Perdida','Aventura','2022-04-21', 15200,'14 anos', 'https://br.web.img3.acsta.net/pictures/21/12/16/15/18/0828406.jpg');
insert into filme (nome, genero, lancamento, duracao, classificacao, poster) values ('A Medium','Terror','2022-05-19', 21100,'16 anos', 'https://i0.wp.com/cebolaverde.com.br/wp-content/uploads/2022/05/Poster-Alternativo-A-Medium-65x95cm-scaled.jpg?ssl=1');
insert into filme (nome, genero, lancamento, duracao, classificacao, poster) values ('D. P. A. 3 - Uma Aventura no Fim do Mundo','Comédia','2021-01-07', 10200,'Livre', 'https://s2.glbimg.com/pYtnpGa5Gg27TFnkG3Cj-o5PBtE=/e.glbimg.com/og/ed/f/original/2020/10/12/dpa3_final1_g.jpg');
insert into filme (nome, genero, lancamento, duracao, classificacao, poster) values ('Batman','Ação','2022-03-03', 25600, '14 anos', 'https://img.elo7.com.br/product/original/3FBA809/big-poster-filme-batman-2022-90x60-cm-lo002-poster-batman.jpg');
insert into filme (nome, genero, lancamento, duracao, classificacao, poster) values ('The Joker', 'Drama', '2019-10-04', 20200, '16 anos', 'https://http2.mlstatic.com/D_NQ_NP_972010-MLB47709378565_092021-W.jpg');

insert into cliente values
('12345678900', 'Matheus Germano','2003-02-27', 'dev.mgermano@gmail.com', 'QE1nMjcwMjAzQA=='),
('00987654321', 'Matheus Costa','2000-02-04', 'matheus.sico@hotmail.com', 'MTIz'),
('11111111111', 'Angelo Holandini','2003-02-05', 'angelo.holandini@gmail.com', 'MTIz'),
('22222222222', 'Joao Silva','2004-02-05', 'joao.silva@gmail.com', 'MTIz'),
('33333333333', 'Carlos Pereira','2010-02-05', 'carlos.pereira@gmail.com', 'MTIz'),
('44444444444', 'Kleber Silva','2007-02-05', 'kleber.silva@gmail.com', 'MTIz'),
('55555555555', 'Eduardo Sousa','1997-02-05', 'eduardo.sousa@gmail.com', 'MTIz'),
('66666666666', 'Marcela Pereira','2005-02-05', 'marcela.pereira@gmail.com', 'MTIz'),
('77777777777', 'Taina Damasco','2006-02-05', 'taina.damasco@gmail.com', 'MTIz'),
('88888888888', 'Estela Costa','2003-02-05', 'estela.costa@gmail.com', 'MTIz');

insert into sala values
(1, 100),
(2, 100),
(3, 140),
(4, 140),
(5, 80),
(6, 80),
(7, 40),
(8, 40),
(9, 70),
(10, 70);

insert into sessao values
('2022-06-20 12:30:00', 9, 1),
('2022-06-20 14:30:00', 9, 1),
('2022-06-20 16:30:00', 9, 1),
('2022-06-20 12:30:00', 1, 2),
('2022-06-20 14:30:00', 1, 2),
('2022-06-20 16:30:00', 1, 2),
('2022-06-20 12:30:00', 9, 3),
('2022-06-20 14:30:00', 9, 3),
('2022-06-20 16:30:00', 9, 3),
('2022-06-20 12:30:00', 1, 4),
('2022-06-20 14:30:00', 1, 4),
('2022-06-20 16:30:00', 1, 4);

select * from cliente;
select * from filme;
select * from sala;
select distinct numeroSala from sessao;
select * from ticket;

-- Consulta Nome, Preço e Data dos ingressos comprados
SELECT cliente.nome as 'Nome do Cliente', ticket.preco as 'Preço do Ticket', date_format(ticket.datahora,"%d/%m/%Y %h:%i:%s") as 'Data e Hora da Sessão'
	FROM cliente inner join ticket on cliente.cpf = ticket.cpfCliente;

-- Salas que tiveram sessões compradas e seus lucros
SELECT distinct sala.numeroSala as 'Número da Sala', sum(ticket.preco) as 'Lucro'
	FROM sala inner join ticket on sala.numeroSala = ticket.numeroSala
    group by sala.numeroSala
    order by lucro asc;

-- Filmes e seus detalhes, junto das sessões em que passaram 
SELECT filme.nome as 'Nome do Filme', filme.genero as 'Gênero', date_format(filme.lancamento,"%d/%m/%Y") as 'Data de Lançamento', 
					ticket.idTicket as 'ID da Sessão',date_format(ticket.datahora,"%d/%m/%Y %h:%i:%s") as 'Data e Hora da Sessão'
	FROM filme right join ticket on filme.idFilme = ticket.idFilme
    group by ticket.idTicket
    order by ticket.idTicket asc;
    
    select * from cliente where email = 'dev.mgermano@gmail.com' and senha = 'QE1nMjcwMjAzQA=='limit 1;
