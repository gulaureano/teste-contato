INSERT INTO contato (nome, email, telefone, data_nascimento) values ('Abrahão Silva', 'abrahão@mail.com', '5548-8963', '1983-04-09');
INSERT INTO contato (nome, email, telefone, data_nascimento) values ('João Cardozo', 'joaocardozo@mail.com', '5478-2260', '2001-07-25');
INSERT INTO contato (nome, email, telefone, data_nascimento) values ('Fernanda Cardozo', 'fernanda@mail.com', '5478-2260', '1997-02-15');
INSERT INTO contato (nome, email, telefone, data_nascimento) values ('Leonardo Gomes', 'leornardo@mail.com', '4493-2198', '1990-12-04');


INSERT INTO endereco (rua, numero, cep) values ('Rua Japão', '43', '47789-871');
INSERT INTO endereco (rua, numero, cep) values ('Rua Holanda', '784A', '02729-007');
INSERT INTO endereco (rua, numero, cep) values ('Rua Nigéria', '1.497', '05963-449');

INSERT INTO contato_enderecos (contatos_id, enderecos_id) values  ((SELECT id FROM contato WHERE nome = 'Abrahão Silva'), (SELECT id FROM endereco WHERE rua = 'Rua Japão')),
  ((SELECT id FROM contato WHERE nome = 'João Cardozo'), (SELECT id FROM endereco WHERE rua = 'Rua Holanda')),
  ((SELECT id FROM contato WHERE nome = 'Fernanda Cardozo'), (SELECT id FROM endereco WHERE rua = 'Rua Holanda')),
  ((SELECT id FROM contato WHERE nome = 'Leonardo Gomes'), (SELECT id FROM endereco WHERE rua = 'Rua Nigéria'));