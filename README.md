
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
- 
### Diagrama da Estrutura do projeto
![diagrama](https://github.com/Luke074/mobiauto-backend-interview/assets/68557804/17e9bb88-d90b-481a-ae44-fe9654159410)

## Requisições da API

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
