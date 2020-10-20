CREATE TABLE usuario(
	id serial PRIMARY KEY,
	usermame varchar(50) not null,
	senha char(32) not null
);

CREATE TABLE localizacao(
	id serial PRIMARY KEY,
	localizacao varchar(50) not null
);

CREATE TABLE pecas(
	id serial PRIMARY KEY,
	nome varchar(50) not null,
	quantidade integer not null,
	id_localizacao integer not null,

	CONSTRAINT fk_localizacao FOREIGN KEY (id_localizacao) REFERENCES localizacao(id)
);

CREATE TABLE setor(
	id serial PRIMARY KEY,
	setor varchar(50) not null
);

CREATE TABLE kit(
	id serial PRIMARY KEY,
	tempo_processamento integer not null,
	nome varchar(50) not null,
	id_setor integer not null,

	CONSTRAINT fk_setor FOREIGN KEY (id_setor) REFERENCES setor(id)
);

CREATE TABLE rotina(
	id serial PRIMARY KEY,
	id_kit integer not null,
	data_producao date not null,
	setor integer not null,

	CONSTRAINT fk_kit FOREIGN KEY (id_setor) REFERENCES kit(id),
	CONSTRAINT fk_setorRotina FOREIGN KEY (id_setor) REFERENCES setor(id)
);

CREATE TABLE pecasKit(
	id_kit integer not null,
	id_peca integer not null,

	CONSTRAINT fk_kitpecas FOREIGN KEY (id_kit) REFERENCES kit(id),
	CONSTRAINT fk_pecaKit FOREIGN KEY (id_peca) REFERENCES pecas(id)
);