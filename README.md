# Projeto Teste Contato

<p>Este projeto é uma API REST que faz CRUD das Entidades: Contato e Endereço, sendo a relação entre elas Many-To-Many criando uma terceira tabela que as relaciona, esse projeto foi feito utilizando
Java 11, framework Spring Boot 2.7.18, gerenciador de dependências Maven 3.9.5 e com o banco de dados em memória H2, este projeto encontra-se hospedado no Google Cloud Plataform no endereço:</p> 
https://teste-contato-4545.rj.r.appspot.com/


<h2>Instalação</h2>
<p>Para utilizara API, é necessário a instalação da JRE ( Java Runtime Environment) disponível para download e instalação no próprio site da Oracle, e para uma melhor experiência nas requisições de CRUD utilizando os métodos GET, POST, PUT, PATCH e DELETE, recomenda-se a aplicação Postman e para acessar o banco de dados H2, o browser da sua escolha, recomenda-se o Google Chrome.</p>
- JRE: https://www.java.com/pt-BR/download/manual.jsp <br>
- Postman: https://www.postman.com/downloads/

<h2>Acesso ao Bando de Dados</h2>
<p>Para utilizar o Banco de Dados H2, no navegador, ao final do link da aplicação, adicionar o /h2-console que irá redirecioná-lo a tela de managment do H2: https://teste-contato-4545.rj.r.appspot.com/h2-console
pode ser que a URL JDBC e o usuário estejam errados, por isso segue o caminho e o usuário corretos:</p>

- URL JDBC = jdbc:h2:mem:testdb <br>
- Username = admin

<h2>Utilização Contato</h2>

<h3>GET</h3>
<p>O método GET nesta API retorna todos os contatos existentes na base, poderá utilizá-lo com seguinte link:</p>
https://teste-contato-4545.rj.r.appspot.com/contato

<h3>POST</h3>
<p>O método POST do Contato é usado para cadastrar um novo contato, segue abaixo o link e o modelo JSON para enviar junto a requisição:</p>
- https://teste-contato-4545.rj.r.appspot.com/contato
- {
    "nome":"Gustavo",
    "email":"gustavo@mail.com",
    "telefone":"123123",
    "dataNascimento":"01/01/1970",
    "idEnderecos": [
      1, 3
    ]
}

*O campo idEndereco não é obrigatório, é possível excluir na hora do POST

<h3>PATCH</h3>
O método PATCH é utilizado para atualizar um contato, devendo passar além do JSON, um id do contato será necessário.
- https://teste-contato-4545.rj.r.appspot.com/contato/{id do conatao}
- {
    "nome":"Gustavo",
    "email":"gustavo@mail.com",
    "telefone":"123123",
    "dataNascimento":"01/01/1970"
}

*Nenhum campo é obrigatório ser preenchido, apenas os que desejar alterar e não é possível alterar o endereço, apenas no seu respectivo endpoint.

Também neste mesmo método, é possível remover um endereço do contato, passado o ID do Contato e o ID do Endereco:
- https://teste-contato-4545.rj.r.appspot.com/contato/removeEndereco
- {
    "id":"1",
    "idEndereco":"1"
}

*Caso uma das informações não existir no banco, será lançado uma exceção informando a inexistência do contato ou endereço.

<h3>PUT</h3>
Similar ao método acima, neste é possível adicionar um endereço ao contato utilizando o mesmo JSON:
- https://teste-contato-4545.rj.r.appspot.com/contato/addEndereco
- {
    "id":"1",
    "idEndereco":"1"
}

<h3>DELETE</h3>
Por fim, nos métodos do Contato, e exclusão do contato da base passando na URL o ID:
- https://teste-contato-4545.rj.r.appspot.com/contato/{ID do contato}

*Caso o ID passado não seja encontrado com algum contato, será lançado uma exceção.


<h2>Utilização Endereço</h2>

<h3>GET</h3>
Assim como no método do Contato, para retornar todos os contatos, basta apenas chamar o seguinte endpoint:
- https://teste-contato-4545.rj.r.appspot.com/endereco

<h3>POST</h3>
Similar ao método POST do Contato, porém aqui o JSON é diferente:
- https://teste-contato-4545.rj.r.appspot.com/endereco
- {
    "rua":"Rua Um",
    "numero":"123",
    "cep":"123456789"
}

*A referência para contato acontece em uma terceira tabela e fica de responsabilidade do contato adicionar o endereço

<h3>PATCH</h3>
Parecido com a atualização do Contato, mudando apenas os campos do JSON:
- https://teste-contato-4545.rj.r.appspot.com/endereco/{ID do Endereco}
- {
    "rua":"Rua Um",
    "numero":"123",
    "cep":"123456789"
}

<h3>DELETE</h3>
Para deletar um endereço, é preciso certificar-se que o mesmo não está sendo utilizado em contato, caso esteja, será lançado uma exceção:
- https://teste-contato-4545.rj.r.appspot.com/endereco/{ID do Endereco}
