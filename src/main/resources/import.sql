-- Dados iniciais para facilitar testes em dev

-- Modelos basicos usados pelos tops
insert into modelo (id, nome, descricao, categoria) values (1, 'Energy', 'Modelo base', 'ESPORTIVO');
insert into modelo (id, nome, descricao, categoria) values (2, 'Classic', 'Modelo tradicional', 'BASICO');
insert into modelo (id, nome, descricao, categoria) values (3, 'Premium', 'Modelo premium', 'PREMIUM');
insert into modelo (id, nome, descricao, categoria) values (4, 'Flex', 'Modelo versatil', 'BASICO');

-- Tamanhos disponiveis
insert into tamanho (id, sigla, descricao) values (1, 'M', 'Medio');
insert into tamanho (id, sigla, descricao) values (2, 'G', 'Grande');
insert into tamanho (id, sigla, descricao) values (3, 'P', 'Pequeno');

-- Cores disponiveis
insert into cor (id, nome, hex) values (1, 'Preto', '#000000');
insert into cor (id, nome, hex) values (2, 'Azul', '#0000FF');
insert into cor (id, nome, hex) values (3, 'Vermelho', '#FF0000');

-- Tipos de sustentacao
insert into tiposustentacao (id, descricao, nivel) values (1, 'Suporte alto', 'ALTA');
insert into tiposustentacao (id, descricao, nivel) values (2, 'Suporte medio', 'MEDIA');
insert into tiposustentacao (id, descricao, nivel) values (3, 'Suporte leve', 'LEVE');

-- Materiais para compor os tops
insert into material (id, nome, composicao) values (1, 'Poliamida', '90% Poliamida 10% Elastano');
insert into material (id, nome, composicao) values (2, 'Algodao', '100% Algodao');
insert into material (id, nome, composicao) values (3, 'Elastano', '100% Elastano');

-- Marcas de referencia
insert into marca (id, nome) values (1, 'TopFit');
insert into marca (id, nome) values (2, 'LinhaFit');
insert into marca (id, nome) values (3, 'NeoFit');

-- Fichas tecnicas associadas aos tops
insert into fichatecnica (id, peso, elasticidade, costura) values (1, 0.22, 'Alta', 'Reforcada');
insert into fichatecnica (id, peso, elasticidade, costura) values (2, 0.28, 'Media', 'Dupla');
insert into fichatecnica (id, peso, elasticidade, costura) values (3, 0.20, 'Alta', 'Reforcada');
insert into fichatecnica (id, peso, elasticidade, costura) values (4, 0.32, 'Leve', 'Simples');

-- Produtos base (heranca de Top -> Produto)
insert into produto (id, nome, descricao, preco) values (1, 'Top Energy', 'Top esportivo', 139.90);
insert into produto (id, nome, descricao, preco) values (2, 'Top Classic', 'Top confortavel', 109.90);
insert into produto (id, nome, descricao, preco) values (3, 'Top Premium', 'Top premium', 189.90);
insert into produto (id, nome, descricao, preco) values (4, 'Top Flex', 'Top versatil', 119.90);

-- Tops (com referencias para modelo, tamanho, cor, marca e ficha tecnica)
insert into tops (id, codigo, modelo_id, tamanho_id, cor_id, tipo_sustentacao_id, marca_id, ficha_tecnica_id)
values (1, 'TP-001', 1, 1, 1, 1, 1, 1);

insert into tops (id, codigo, modelo_id, tamanho_id, cor_id, tipo_sustentacao_id, marca_id, ficha_tecnica_id)
values (2, 'TP-002', 2, 2, 2, 2, 2, 2);

insert into tops (id, codigo, modelo_id, tamanho_id, cor_id, tipo_sustentacao_id, marca_id, ficha_tecnica_id)
values (3, 'TP-003', 3, 3, 3, 1, 3, 3);

insert into tops (id, codigo, modelo_id, tamanho_id, cor_id, tipo_sustentacao_id, marca_id, ficha_tecnica_id)
values (4, 'TP-004', 4, 1, 2, 3, 1, 4);

-- Relacao N:N entre top e material
insert into top_material (top_id, material_id) values (1, 1);
insert into top_material (top_id, material_id) values (1, 2);
insert into top_material (top_id, material_id) values (2, 2);
insert into top_material (top_id, material_id) values (3, 1);
insert into top_material (top_id, material_id) values (3, 3);
insert into top_material (top_id, material_id) values (4, 2);