DROP DATABASE if exists cinetickets;
create database if not exists cinetickets;
use cinetickets;

 -- virou table pessoa
CREATE TABLE pessoa (
cpf VARCHAR(11) PRIMARY KEY,
nome VARCHAR(45) NOT NULL,
dataNascimento DATE NOT NULL,
email VARCHAR(100) NOT NULL,
senha VARCHAR(45) NOT NULL, 
tipo enum('cliente','administrador')
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

constraint pkDataHoraNumeroSalaFilme primary key (dataHora, numeroSala, idFilme),
constraint fkIdFilme foreign key (idFilme) references filme (idFilme),
constraint fkNumeroSala foreign key (numeroSala) references sala (numeroSala)
);

CREATE TABLE ticket (
cpfCliente varchar(11) not null,
idFilme INT,
numeroSala INT,
dataSessao DATETIME NOT NULL,
dataCompra DATETIME NOT NULL,
preco DECIMAL(5,2) NULL DEFAULT 0.00,
assento varchar(4) NOT NULL,
versao3d BOOL NULL DEFAULT 0,
legendado BOOL NULL DEFAULT 0,
meiaEntrada BOOL NULL DEFAULT 0,
cadeirante BOOL NULL DEFAULT 0,

constraint pkDataSessaoNumeroSalaAssento primary key (dataSessao, numeroSala, assento),
constraint fkDataHoraNumeroSalaFilme foreign key (dataSessao, numeroSala, idFilme) references sessao (dataHora, numeroSala, idFilme),
FOREIGN KEY (cpfCliente) REFERENCES pessoa (cpf)
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

insert into pessoa values
('12345678900', 'Matheus Germano','2003-02-27', 'dev.mgermano@gmail.com', 'QE1nMjcwMjAzQA==','administrador'),
('00987654321', 'Matheus Costa','2000-02-04', 'matheus.sico@hotmail.com', 'MTIz','cliente'),
('11111111111', 'Angelo Holandini','2003-02-05', 'angelo.holandini@gmail.com', 'MTIz','cliente'),
('22222222222', 'Joao Silva','2004-02-05', 'joao.silva@gmail.com', 'MTIz','cliente'),
('33333333333', 'Carlos Pereira','2010-02-05', 'carlos.pereira@gmail.com', 'MTIz','cliente'),
('44444444444', 'Kleber Silva','2007-02-05', 'kleber.silva@gmail.com', 'MTIz','cliente'),
('55555555555', 'Eduardo Sousa','1997-02-05', 'eduardo.sousa@gmail.com', 'MTIz','cliente'),
('66666666666', 'Marcela Pereira','2005-02-05', 'marcela.pereira@gmail.com', 'MTIz','cliente'),
('77777777777', 'Taina Damasco','2006-02-05', 'taina.damasco@gmail.com', 'MTIz','cliente'),
('88888888888', 'Estela Costa','2003-02-05', 'estela.costa@gmail.com', 'MTIz','cliente');

insert into sala values
(1, 160),
(2, 160),
(3, 210),
(4, 200),
(5, 140),
(6, 140),
(7, 120),
(8, 120),
(9, 80),
(10, 80);

insert into sessao values
('2022-11-20 12:30:00', 9, 1),
('2022-11-20 14:30:00', 9, 1),
('2022-11-20 16:30:00', 9, 1),
('2022-11-20 12:30:00', 1, 2),
('2022-11-20 14:30:00', 1, 2),
('2022-11-20 16:30:00', 3, 2),
('2022-11-18 12:30:00', 9, 3),
('2022-11-20 14:30:00', 9, 3),
('2022-11-20 16:30:00', 9, 3),
('2022-11-20 12:30:00', 1, 4),
('2022-11-20 14:30:00', 1, 4),
('2022-11-20 16:30:00', 1, 4);

-- insert into ticket values('12345678900', 1, 4,'2022-11-20 14:30:00','2022-11-19 14:30:00', 11.00, 'C08', 0, 0, 1, 0);

-- select * from ticket;

-- insert into ticket values ('12345678900', 1, 4,'2022-11-20 14:30:00','2022-11-19 14:31:00', 11.00, 'C09', 0, 0, 1, 0);

insert into ticket values
(default,'12345678900', 1, 4,'2022-11-20 14:30:00','2022-11-19 14:30:00', 11.00, 'C08', 0, 0, 1, 0),
(default,'12345678900', 1, 4,'2022-11-20 14:30:00','2022-11-19 14:31:00', 11.00, 'C09', 0, 0, 1, 0),
(default,'00987654321', 1, 4,'2022-11-20 14:30:00','2022-11-19 14:32:00', 11.00, 'C10', 0, 0, 1, 0),
(default,'00987654321', 1, 4,'2022-11-20 14:30:00','2022-11-19 14:33:00', 11.00, 'C11', 0, 0, 1, 0),
(default,'00987654321', 1, 4,'2022-11-20 14:30:00','2022-11-19 14:34:00', 11.00, 'C12', 0, 0, 1, 0),
(default,'00987654321', 1, 4,'2022-11-20 14:30:00','2022-11-19 14:35:00', 11.00, 'D08', 0, 0, 1, 0),
(default,'00987654321', 1, 4,'2022-11-20 14:30:00','2022-11-19 14:36:00', 11.00, 'D09', 0, 0, 1, 0),
(default,'00987654321', 1, 4,'2022-11-20 14:30:00','2022-11-19 14:37:00', 11.00, 'D10', 0, 0, 1, 0),
(default,'00987654321', 1, 4,'2022-11-20 14:30:00','2022-11-19 14:38:00', 11.00, 'D11', 0, 0, 1, 0),
(default,'00987654321', 1, 4,'2022-11-20 14:30:00','2022-11-19 14:39:00', 11.00, 'D12', 0, 0, 1, 0),

(default,'12345678900', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:30:00', 22.00, 'B08', 0, 0, 0, 0),
(default,'33333333333', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:31:00', 22.00, 'D09', 0, 0, 0, 0),
(default,'12345678900', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:32:00', 22.00, 'B10', 0, 0, 0, 0),
(default,'22222222222', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:33:00', 22.00, 'C08', 0, 0, 0, 0),
(default,'33333333333', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:34:00', 22.00, 'C09', 0, 0, 0, 0),
(default,'44444444444', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:35:00', 22.00, 'D11', 0, 0, 0, 0),
(default,'22222222222', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:36:00', 22.00, 'D12', 0, 0, 0, 0),
(default,'33333333333', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:37:00', 22.00, 'A05', 0, 0, 0, 0),
(default,'44444444444', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:38:00', 22.00, 'A06', 0, 0, 0, 0),
(default,'44444444444', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:39:00', 22.00, 'A07', 0, 0, 0, 0),
(default,'77777777777', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:40:00', 11.00, 'A08', 0, 0, 1, 0),
(default,'77777777777', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:41:00', 11.00, 'A09', 0, 0, 1, 0),
(default,'77777777777', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:42:00', 11.00, 'A10', 0, 0, 1, 0),
(default,'22222222222', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:43:00', 22.00, 'E08', 0, 0, 0, 0),
(default,'33333333333', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:46:00', 22.00, 'E09', 0, 0, 0, 0),
(default,'44444444444', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:45:00', 22.00, 'E10', 0, 0, 0, 0),
(default,'22222222222', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:47:00', 22.00, 'E11', 0, 0, 0, 0),
(default,'33333333333', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:48:00', 22.00, 'E12', 0, 0, 0, 0),
(default,'44444444444', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:49:00', 22.00, 'E14', 0, 0, 0, 0),
(default,'22222222222', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:50:00', 22.00, 'F08', 0, 0, 0, 0),
(default,'33333333333', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:51:00', 22.00, 'F09', 0, 0, 0, 0),
(default,'44444444444', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:52:00', 22.00, 'F10', 0, 0, 0, 0),
(default,'44444444444', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:53:00', 22.00, 'F11', 0, 0, 0, 0),
(default,'77777777777', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:54:00', 11.00, 'A01', 0, 0, 1, 0),
(default,'77777777777', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:55:00', 11.00, 'A02', 0, 0, 1, 0),
(default,'77777777777', 9, 3,'2022-11-18 12:30:00','2022-11-17 14:56:00', 11.00, 'A03', 0, 0, 1, 0),

(default,'00987654321', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:30:00', 11.00, 'C08', 0, 0, 1, 0),
(default,'00987654321', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:31:00', 11.00, 'C09', 0, 0, 1, 0),
(default,'00987654321', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:32:00', 11.00, 'C10', 0, 0, 1, 0),
(default,'00987654321', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:33:00', 11.00, 'C11', 0, 0, 1, 0),
(default,'00987654321', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:34:00', 11.00, 'C12', 0, 0, 1, 0),
(default,'00987654321', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:35:00', 11.00, 'B08', 0, 0, 1, 0),
(default,'00987654321', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:36:00', 11.00, 'B09', 0, 0, 1, 0),
(default,'00987654321', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:37:00', 11.00, 'B10', 0, 0, 1, 0),
(default,'00987654321', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:38:00', 11.00, 'B11', 0, 0, 1, 0),
(default,'00987654321', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:39:00', 11.00, 'B12', 0, 0, 1, 0),
(default,'22222222222', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:40:00', 22.00, 'A01', 0, 0, 0, 0),
(default,'33333333333', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:41:00', 22.00, 'A02', 0, 0, 0, 0),
(default,'44444444444', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:42:00', 22.00, 'A03', 0, 0, 0, 0),
(default,'22222222222', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:43:00', 22.00, 'A04', 0, 0, 0, 0),
(default,'33333333333', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:44:00', 22.00, 'A05', 0, 0, 0, 0),
(default,'44444444444', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:45:00', 22.00, 'A06', 0, 0, 0, 0),
(default,'22222222222', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:46:00', 22.00, 'A08', 0, 0, 0, 0),
(default,'33333333333', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:47:00', 22.00, 'A09', 0, 0, 0, 0),
(default,'44444444444', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:48:00', 22.00, 'A10', 0, 0, 0, 0),
(default,'44444444444', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:49:00', 22.00, 'B01', 0, 0, 0, 0),
(default,'77777777777', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:49:30', 11.00, 'B02', 0, 0, 1, 0),
(default,'77777777777', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:50:00', 11.00, 'B03', 0, 0, 1, 0),
(default,'77777777777', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:51:00', 11.00, 'B04', 0, 0, 1, 0),
(default,'22222222222', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:52:00', 22.00, 'B08', 0, 0, 0, 0),
(default,'33333333333', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:53:00', 22.00, 'E09', 0, 0, 0, 0),
(default,'44444444444', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:54:00', 22.00, 'E10', 0, 0, 0, 0),
(default,'22222222222', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:55:00', 22.00, 'E11', 0, 0, 0, 0),
(default,'33333333333', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:56:00', 22.00, 'E12', 0, 0, 0, 0),
(default,'44444444444', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:57:00', 22.00, 'F01', 0, 0, 0, 0),
(default,'22222222222', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:58:00', 22.00, 'F02', 0, 0, 0, 0),
(default,'33333333333', 3, 2,'2022-11-20 16:30:00','2022-11-19 14:59:00', 22.00, 'F03', 0, 0, 0, 0),
(default,'44444444444', 3, 2,'2022-11-20 16:30:00','2022-11-19 15:00:00', 22.00, 'F04', 0, 0, 0, 0),
(default,'44444444444', 3, 2,'2022-11-20 16:30:00','2022-11-19 15:20:00', 22.00, 'F05', 0, 0, 0, 0),
(default,'77777777777', 3, 2,'2022-11-20 16:30:00','2022-11-19 15:30:00', 11.00, 'F06', 0, 0, 1, 0),
(default,'77777777777', 3, 2,'2022-11-20 16:30:00','2022-11-19 15:40:00', 11.00, 'F09', 0, 0, 1, 0),
(default,'77777777777', 3, 2,'2022-11-20 16:30:00','2022-11-19 15:50:00', 11.00, 'A11', 0, 0, 1, 0);

-- PROCEDURE -----------------------------------------------------------------------------------------------------------------------------------------------------------

-- Procedure que mostra todas as sessoes de um filme especifico
DROP PROCEDURE IF EXISTS sessoes_filme_especifico;
DELIMITER !
CREATE PROCEDURE sessoes_filme_especifico (nome_filme VARCHAR(45))
BEGIN
	SELECT f.nome Filme, sa.numeroSala Sala, DATE_FORMAT(se.dataHora, "%d/%m/%y %H:%i") Horário
    FROM filme f INNER JOIN sessao se USING(idFilme)
    INNER JOIN sala sa USING(numeroSala)
    WHERE f.nome = nome_filme;
END !
DELIMITER ;

call sessoes_filme_especifico("Sonic 2");

-- Salas que tiveram sessões compradas e seus lucros
DROP PROCEDURE IF EXISTS lucros_sessoes_compradas;
DELIMITER !
CREATE PROCEDURE lucros_sessoes_compradas()
BEGIN
	SELECT sala.numeroSala as 'Número da Sala', sum(ticket.preco) as 'Lucro'
	FROM sala inner join ticket on sala.numeroSala = ticket.numeroSala
    group by sala.numeroSala
    order by lucro asc;
END !
DELIMITER ;

call lucros_sessoes_compradas();

-- Salas vendidas em uma data especifica
drop procedure IF EXISTS sala_assentos_vendidos_data_especifica;
DELIMITER !
CREATE PROCEDURE sala_assentos_vendidos_data_especifica (data_especifica datetime)
BEGIN
	select count(t.idTicket) as 'QuantidadeAssentosVendidos', s.numeroSala as 'SalaReferencia'
	from sala s inner join ticket t 
	where date_format(t.dataSessao, "%Y-%m-%d") = data_especifica and s.numeroSala = t.numeroSala
	group by s.numeroSala
    order by QuantidadeAssentosVendidos asc;
END !
DELIMITER ;

call sala_assentos_vendidos_data_especifica('2022-11-20');

-- Lucro em um intervalo de  tempo

drop procedure IF EXISTS lucro_em_um_intervalo_de_tempo;
DELIMITER !
CREATE PROCEDURE lucro_em_um_intervalo_de_tempo (data_inicio datetime, data_fim datetime)
BEGIN
	select sum(t.preco) as 'Lucro'
	from ticket t 
	where date_format(t.dataSessao, "%Y-%m-%d") between date_format(data_inicio, "%Y-%m-%d") and date_format(data_fim, "%Y-%m-%d");
END !
DELIMITER ;

call lucro_em_um_intervalo_de_tempo('2022-11-18','2022-11-20');
call lucro_em_um_intervalo_de_tempo('2022-11-20','2022-11-20');

-- Top 3 filmes mais vistos

drop procedure IF EXISTS top_3_filmes;
DELIMITER !
CREATE PROCEDURE top_3_filmes ()
BEGIN
	SELECT filme.nome as 'Nome do Filme', count(ticket.idTicket) as 'Total Vendas'
	FROM filme right join ticket on filme.idFilme = ticket.idFilme
    group by filme.nome
    order by ticket.idTicket desc
    limit 3;
END !
DELIMITER ;

call top_3_filmes;

-- FUNCTIONS ------------------------------------------------------------------------------------------------------------------------------------------
-- function lucro total do cinema
DELIMITER !

CREATE FUNCTION soma_lucros_tickets()
RETURNS DECIMAL(10,2) deterministic
BEGIN
DECLARE lucros DECIMAL(10,2);
	SELECT sum(ticket.preco) into lucros
	FROM sala inner join ticket on sala.numeroSala = ticket.numeroSala;
    RETURN lucros;
END !
DELIMITER ;

select soma_lucros_tickets();

-- Media lucro do cinema
DELIMITER !
CREATE procedure media_e_quantidade_tickets()
BEGIN
	SELECT avg(ticket.preco) as "Média", count(ticket.idTicket) as 'Quantidade de vendas'
	FROM sala inner join ticket on sala.numeroSala = ticket.numeroSala;
END !
DELIMITER ;

call media_e_quantidade_tickets();

-- Function para retornar a sala com maior capacidade de cadeiras
drop function IF EXISTS max_capacidade_sala;
DELIMITER !
CREATE function max_capacidade_sala ()
returns int deterministic
BEGIN
declare numeroSalaMaxAseentos int ;
	select numeroSala into numeroSalaMaxAseentos
	from sala
	group by numeroSala
    order by qtdAssentos desc
    limit 1;
return numeroSalaMaxAseentos;
END !
DELIMITER ;

select max_capacidade_sala();

-- VIEWS ----------------------------------------------------------------------------------------------------------------------------------------------
-- Consulta Nome, Preço e Data dos ingressos comprados
drop view IF EXISTS info_cliente;
CREATE VIEW info_cliente (nome, precoTicket, dataSessao) AS 
	SELECT pessoa.nome as 'Nome do Cliente', ticket.preco as 'Preço do Ticket', date_format(ticket.dataSessao,"%d/%m/%Y %h:%i:%s") as 'Data e Hora da Sessão'
	FROM pessoa inner join ticket on pessoa.cpf = ticket.cpfCliente;

select * from info_cliente;

-- Filmes e seus detalhes, junto das sessões em que passaram 
CREATE VIEW info_filme (nome_filme, genero_filme, data_lancamento, idTicket, dataSessao) AS
	SELECT filme.nome as 'Nome do Filme', filme.genero as 'Gênero', date_format(filme.lancamento,"%d/%m/%Y") as 'Data de Lançamento', 
					ticket.idTicket as 'ID da Sessão', date_format(ticket.dataSessao,"%d/%m/%Y %h:%i:%s") as 'Data e Hora da Sessão'
	FROM filme right join ticket on filme.idFilme = ticket.idFilme
    group by ticket.idTicket
    order by ticket.idTicket asc;
    
select * from info_filme;
    
-- TRIGGERS ----------------------------------------------------------------------------------------------------------------------------------------------
-- Trigger que deleta as sessões e os tickets de um filme especifico caso este filme seja deletado
DROP TRIGGER IF EXISTS atualiza_filme_deletado;
DELIMITER !
CREATE TRIGGER atualiza_filme_deletado
BEFORE DELETE ON filme
FOR EACH ROW
BEGIN
	-- DECLARE nomeFilme VARCHAR(45);
    SET FOREIGN_KEY_CHECKS = 0;
    DELETE FROM sessao WHERE idFilme = old.idFilme;
    DELETE FROM ticket WHERE idFilme = old.idFilme;
	SET FOREIGN_KEY_CHECKS = 1;
END
!
DELIMITER ;

SELECT * FROM filme;
SELECT * FROM ticket;
SELECT * FROM sessao;
DELETE FROM filme WHERE idFilme = 1;

-- CONTROLE DE ACESSO ------------------------------------------------------------------------------------------------------------------------------------
select user, host from mysql.user;
select user();

CREATE USER angelo IDENTIFIED BY "1234"
WITH  max_updates_per_hour 8
PASSWORD EXPIRE INTERVAL 30 DAY;

show grants for angelo;

-- Dando permissao para alteraçoes na tabela filme
grant select, update, insert
on cinetickets.filme
to angelo;

DROP USER angelo;

select user, host from mysql.user;
select user();