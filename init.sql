CREATE TABLE IF NOT EXISTS pais (
    idpais SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    disponibilidade BOOLEAN DEFAULT true
);

CREATE TABLE IF NOT EXISTS estado (
    idestado SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    pais_idpais VARCHAR(255),
    disponibilidade BOOLEAN DEFAULT true
);

CREATE TABLE IF NOT EXISTS cidade (
    idcidade SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    estado_idestado VARCHAR(255),
    disponibilidade BOOLEAN DEFAULT true
);

CREATE TABLE IF NOT EXISTS bairro (
    idbairro SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    cidade_idcidade VARCHAR(255),
    disponibilidade BOOLEAN DEFAULT true
);

CREATE TABLE IF NOT EXISTS endereco (
    idendereco SERIAL PRIMARY KEY,
    rua VARCHAR(255),
    bairro_idbairro VARCHAR(255),
    disponibilidade BOOLEAN DEFAULT true
);

CREATE TABLE IF NOT EXISTS pontoacesso (
    idpontoacesso SERIAL PRIMARY KEY,
    ssid VARCHAR(255),
    modelo VARCHAR(255),
    largurabanda VARCHAR(255),
    frequencia VARCHAR(255),
    iproteador VARCHAR(255),
    usuario VARCHAR(255),
    pass VARCHAR(255),
    disponibilidade BOOLEAN DEFAULT true
);

CREATE TABLE IF NOT EXISTS frequencia (
    idfrequencia SERIAL PRIMARY KEY,
    ghz VARCHAR(255),
    mhz VARCHAR(255),
    disponibilidade BOOLEAN DEFAULT true
);

CREATE TABLE IF NOT EXISTS cadastroPessoa (
    idcadastroPessoa SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    sobrenome VARCHAR(255),
    documento VARCHAR(255),
    pais VARCHAR(255),
    estado VARCHAR(255),
    cidade VARCHAR(255),
    bairro VARCHAR(255),
    endereco VARCHAR(255),
    numeroendereco VARCHAR(255),
    nomeusuario VARCHAR(255),
    senhaacesso VARCHAR(255),
    pontoacesso VARCHAR(255),
    disponibilidade BOOLEAN DEFAULT true
);

CREATE TABLE IF NOT EXISTS usuarios (
    idusuarios SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    senha VARCHAR(255),
    supervisor BOOLEAN DEFAULT false
);

-- Dados iniciais (Opcional, para testes)
INSERT INTO usuarios (nome, senha, supervisor) VALUES ('admin', 'admin', true) ON CONFLICT DO NOTHING;
