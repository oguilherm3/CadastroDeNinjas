-- V2: Migration para adicionar a coluna de Ranking na tabela de cadastro

ALTER TABLE tb_cadastro
ADD COLUMN rank VARCHAR(255);