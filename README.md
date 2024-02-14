
# Projeto Mobiauto-backend

O objetivo principal deste back-end é aprimorar a integração entre o front-end e o banco de dados, oferecendo maior flexibilidade na manipulação dos dados. Isso permite facilitar a implementação de novos recursos e a manutenção de bugs de forma mais eficiente.

#### Principais Funcionalidades

- Integração com o Front-end: O back-end foi projetado para se integrar perfeitamente com o front-end, proporcionando uma comunicação fluida entre as interfaces do usuário e o banco de dados
- Manipulação Flexível de Dados: A arquitetura do back-end permite uma manipulação mais livre dos dados, possibilitando operações avançadas de consulta, inserção, atualização e exclusão.
- Suporte a Novas Implementações: Com uma estrutura flexível e modular, o back-end está preparado para receber novas implementações e recursos, facilitando a expansão e a evolução do sistema ao longo do tempo.
- Facilidade na Manutenção: A organização e a clareza do código tornam a identificação e a correção de bugs mais acessíveis, simplificando a manutenção e garantindo a estabilidade do sistema.

#### Tecnologias Utilizadas

- Linguagem de Programação: `Java`
- Frameworks: `Spring boot (Maven)`
- Banco de Dados: `SQL`

### Configure o Ambiente e coloque o dump
Utilizei o banco de dados mysql, então configurei o ambiente usando o `sql server` fazer a implementação com o banco de dados
_Já existe um dump com quase todos os dados para utilizar caso nescessite, que está em resources -> db -> dump -> "db_mobiauto-2024-02-13.sql" (a senha para todos os usuarios do sistema é `123456`)_

Aqui está minha config (Configure assim, ou conforme o seu banco de dados):
![config-database](https://github.com/Luke074/mobiauto-backend-interview/assets/68557804/a0d8f80b-c417-440e-97de-12747fb2e343)
![config-local](https://github.com/Luke074/mobiauto-backend-interview/assets/68557804/568d9c47-6267-44b3-8d13-23a142268488)

### Api POSTMAN
Caso precise das requisições pronta, o link com todas as pastas de requisições do sistema:
[API_POSTMAN](https://www.postman.com/aviation-astronomer-43438735/workspace/lucas-mendes-workspace/collection/13543752-860cf3cb-7d30-4174-8421-eda6b9bb01e3)

### Diagrama da Estrutura do projeto
![driagrama drawio](https://github.com/Luke074/mobiauto-backend-interview/assets/68557804/826a44d2-243b-479e-8199-fcb0c731b65e)

## Requisições da API
Para acessar o sistema é nescesario usar um `login` e `senha`
| URL | Type  | Parameter |
|:----- | :-------- | :------- |
| `/usuarios` | `POSt` | É nescessario enviar pelo `body`, `login` e `senha` | 

#### Admin
| URL | Type  | Parameter |
|:----- | :-------- | :------- |
| `/admin` | `GET` | Não precisa de paramentro| 
| `/admin` | `POST` | Envie os dados pelo `body` da requisição| 
| `/admin` | `PUT` | É nescessario o dado `id` enviado pelo `body` da requisição| 
| `/admin/{id}` | `DELETE` | É nescessario colocor o `id` na url| 

#### Usuarios
| URL | Type  | Parameter |
|:----- | :-------- | :------- |
| `/usuarios` | `GET` | Não precisa de paramentro| 
| `/usuarios` | `POST` | Envie os dados pelo `body` da requisição| 
| `/usuarios` | `PUT` | É nescessario o dado `id` enviado pelo `body` da requisição| 
| `/usuarios/{id}` | `DELETE` | É nescessario colocor o `id` na url| 

#### Veiculos
| URL | Type  | Parameter |
|:----- | :-------- | :------- |
| `/veiculos` | `GET` | Não precisa de paramentro| 
| `/veiculos` | `POST` | Envie os dados pelo `body` da requisição| 
| `/veiculos` | `PUT` | É nescessario o dado `id` enviado pelo `body` da requisição| 
| `/veiculos/{id}` | `DELETE` | É nescessario colocor o `id` na url| 

#### Lojas
| URL | Type  | Parameter |
|:----- | :-------- | :------- |
| `/lojas` | `GET` | Não precisa de paramentro| 
| `/lojas` | `POST` | Envie os dados pelo `body` da requisição| 
| `/lojas` | `PUT` | É nescessario o dado `id` enviado pelo `body` da requisição| 
| `/lojas/{id}` | `DELETE` | É nescessario colocor o `id` na url| 

#### Oportunidades
| URL | Type  | Parameter |
|:----- | :-------- | :------- |
| `/oportunidades` | `GET` | Não precisa de paramentro| 
| `/oportunidades` | `POST` | Envie os dados pelo `body` da requisição|

## Fale conosco para suporte

- E-mail: `mendeslucas277@gmail.com`

## Author
- [@luke074](https://github.com/Luke074/)
