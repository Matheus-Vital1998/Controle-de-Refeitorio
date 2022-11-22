

-- \************************************************************\

CREATE TABLE USUARIO (
	ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	RA CHAR(9) NOT NULL,
	NOME VARCHAR(100) NOT NULL,
	LOGIN VARCHAR(100) NOT NULL,
	SENHA VARCHAR(100) NOT NULL,
	TIPO CHAR(5) NOT NULL
);

ALTER TABLE USUARIO
	ADD CONSTRAINT CHECK_USUARIO_TIPO
		CHECK (TIPO IN ('ALUNO', 'ADMIN'));



-- \************************************************************\

CREATE TABLE REFEICAO (
	ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	NOME VARCHAR(100) NOT NULL,
	HORARIO_INICIO TIME NOT NULL,
	HORARIO_FIM TIME NOT NULL,
	HORARIO_LIMITE_RESERVA TIME NOT NULL
);


-- \************************************************************\

CREATE TABLE CARDAPIO (
	ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	REFEICAO_ID INT NOT NULL,
	DATA DATE NOT NULL,
	DESCRICAO VARCHAR(100) NOT NULL
);

ALTER TABLE CARDAPIO
	ADD CONSTRAINT FOREIGNKEY_CARDAPIO_REFEICAO
		FOREIGN KEY (REFEICAO_ID) REFERENCES REFEICAO(ID)
			ON DELETE CASCADE
			ON UPDATE RESTRICT;



-- \************************************************************\

CREATE TABLE RESERVA (
	ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	USUARIO_ID INT NOT NULL,
	CARDAPIO_ID INT NOT NULL,
	HORARIO_RESERVA TIMESTAMP NOT NULL
);

ALTER TABLE RESERVA
	ADD CONSTRAINT FOREIGNKEY_RESERVA_USUARIO
		FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO(ID)
			ON DELETE CASCADE
			ON UPDATE RESTRICT;

ALTER TABLE RESERVA
	ADD CONSTRAINT FOREIGNKEY_RESERVA_CARDAPIO
		FOREIGN KEY (CARDAPIO_ID) REFERENCES CARDAPIO(ID)
			ON DELETE CASCADE
			ON UPDATE RESTRICT;



-- \************************************************************\

CREATE TABLE HISTORICO_ENTRADA (
	ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	USUARIO_ID INT NOT NULL,
	CARDAPIO_ID INT NOT NULL,
	HORARIO_CHEGADA TIMESTAMP NOT NULL,
	ENTRADA_AUTORIZADA BOOLEAN NOT NULL,
	MOTIVO VARCHAR(50)
);

ALTER TABLE HISTORICO_ENTRADA
	ADD CONSTRAINT FOREIGNKEY_HISTORICOENTRADA_USUARIO
		FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO(ID)
			ON DELETE CASCADE
			ON UPDATE RESTRICT;

ALTER TABLE HISTORICO_ENTRADA
	ADD CONSTRAINT FOREIGNKEY_HISTORICOENTRADA_CARDAPIO
		FOREIGN KEY (CARDAPIO_ID) REFERENCES CARDAPIO(ID)
			ON DELETE CASCADE
			ON UPDATE RESTRICT;


INSERT INTO iMeal.USUARIO (RA,NOME,LOGIN,SENHA,TIPO) VALUES 
    ('081200024',    'ALUNO 1',    'aluno@aluno',    'aluno1234',    'ALUNO'),
    ('7308',    'ADMINISTRADOR 1',    'adm@adm',    'adm1234',    'ADMIN')
    
    
INSERT INTO iMeal.REFEICAO (NOME, HORARIO_INICIO, HORARIO_FIM, HORARIO_LIMITE_RESERVA) VALUES
    ('Janta',    '18:15',    '19:05',    '17:00'),
    ('Lanche Reforçado',    '20:50',    '21:05',    '19:50')
