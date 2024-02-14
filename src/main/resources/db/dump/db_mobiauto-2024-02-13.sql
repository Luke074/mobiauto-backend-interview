INSERT INTO `usuarios` (`id`,`login` ,`cargo`, `email`, `nome`, `senha`) VALUES
(1, "admin_sistema", 0, 'admin@teste.com.br', 'Admin', '$2a$10$.yn.B8Li3RHFzvQ11TXhaOohFy.CW7qyOUF1YCtCr8dSZqQ9scYg2');
INSERT INTO `usuarios` (`id`,`login` ,`cargo`, `email`, `nome`, `senha`) VALUES
(2, "jhon_proprietario", 0, 'proprietario@teste.com.br', 'Proprietario Jhon', '$2a$10$.yn.B8Li3RHFzvQ11TXhaOohFy.CW7qyOUF1YCtCr8dSZqQ9scYg2');
INSERT INTO `usuarios` (`id`,`login` ,`cargo`, `email`, `nome`, `senha`) VALUES
(3, "jin_gerente", 0, 'gerente@teste.com.br', 'Gerente Jin', '$2a$10$.yn.B8Li3RHFzvQ11TXhaOohFy.CW7qyOUF1YCtCr8dSZqQ9scYg2');
INSERT INTO `usuarios` (`id`,`login` ,`cargo`, `email`, `nome`, `senha`) VALUES
(4, "tomas_assistente", 0, 'tomas@teste.com.br', 'Assistente Tomas', '$2a$10$.yn.B8Li3RHFzvQ11TXhaOohFy.CW7qyOUF1YCtCr8dSZqQ9scYg2');

INSERT INTO `lojas` (`id`, `cnpj`, `nome`) VALUES
(1, '10617507000142', 'Mobiauto Alphavile');

INSERT INTO `veiculos` (`id`, `ano_modelo`, `marca`, `modelo`, `nome`, `valor`, `versao`) VALUES
(1, 2012, 'chevrolet', 'Prata', 'Corsa 2012', 33500.5, '1.4');
INSERT INTO `veiculos` (`id`, `ano_modelo`, `marca`, `modelo`, `nome`, `valor`, `versao`) VALUES
(2, 2012, 'fiat', 'vermelho', 'Uno 2012 sport', 25000.7, '1.4');

INSERT INTO `oportunidades` (`id`, `data_aplicacao`, `data_conclusao`, `email_cliente`, `nome_cliente`, `revenda_id`, `status_oportunidade`, `telefone_cliente`, `usuario_id`, `veiculo_id`) VALUES
(1, '2024-02-12 21:00:00.000000', '2024-02-13 00:00:00.000000', 'lucas@mendes.com.br', 'Lucas Mendes', 3, 2, '1198105102', 2, 1);