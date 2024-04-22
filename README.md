# Projeto Teste Contato

Neste projeto é uma pequena API REST que faz CRUD das Entidades Contato e Endereço, sendo a relação entre elas Many-To-Many criando uma terceira tabela que as relaciona, esse projeto foi feito utilizando
Java 11, framework Spring Boot 2.7.18, gerenciador de dependências Maven 3.9.5 e com o banco de dados em memória H2, este projeto encontra-se hospedada no Google Cloud Plataform no endereço: 
https://teste-contato-4545.rj.r.appspot.com/


#Instalação
Para utilizara API, é necessário a instalação de JRE ( Java Runtime Environment) disponível para download e instalação no próprio site da Oracle, e para uma melhor experiência nas requisições de CRUD utilizando os métodos GET, POST, PUT, PATCH e DELETE, recomenda-se a aplicação Postman e para acessar o banco de dados H2, o browser da sua escolha, recomenda-se o Google Chrome.
- JRE: https://www.java.com/pt-BR/download/manual.jsp
- Postman: https://www.postman.com/downloads/

#Acesso ao Bando de Dados
Para utilizar o Banco de Dados H2, no navegador, ao final do link da aplicação, adicionar o /h2-console que irá redirecioná-lo a tela de managment do H2 https://teste-contato-4545.rj.r.appspot.com/h2-console
pode ser que a URL JDBC e o usuário estejam errados, por isso segue o caminho e o usuário corretos:
- URL JDBC = jdbc:h2:mem:testdb
- Username = admin

#Utilização Contato

#GET
O método GET nesta API retorna todos os contatos existentes na base, poderá utilizá-lo com seguinte link:
https://teste-contato-4545.rj.r.appspot.com/contato

#POST
O método POST do Contato é usado para cadastrar um novo contato, segue abaixo o link e o modelo JSON para enviar junto a requisição:
https://teste-contato-4545.rj.r.appspot.com/contato
{
    "nome":"Gustavo",
    "email":"gustavo@mail.com",
    "telefone":"123123",
    "dataNascimento":"01/01/1970",
    "idEnderecos": [
      1, 3
    ]
}

*O campo idEndereco não é obrigatório, é possível excluir na hora do POST

#PATCH
O método PATCH é utilizado para atualizar um contato, devendo passar além do JSON, um id do contato será necessário.
https://teste-contato-4545.rj.r.appspot.com/contato/{id do conatao}
{
    "nome":"Gustavo",
    "email":"gustavo@mail.com",
    "telefone":"123123",
    "dataNascimento":"01/01/1970"
}

*Nenhum campo é obrigatório ser preenchido, apenas os que desejar alterar e não é possível alterar o endereço, apenas no seu respectivo endpoint.

Também neste mesmo método, é possível remover um endereço do contato, passado o ID do Contato e o ID do Endereco:
https://teste-contato-4545.rj.r.appspot.com/contato/removeEndereco
{
    "id":"1",
    "idEndereco":"1"
}

*Caso uma das informações não existir no banco, será lançado uma exceção informando a inexistência do contato ou endereço.

#PUT
Similar ao método acima, neste é possível adicionar um endereço ao contato utilizando o mesmo JSON:
https://teste-contato-4545.rj.r.appspot.com/contato/addEndereco
{
    "id":"1",
    "idEndereco":"1"
}

#DELETE
Por fim, nos métodos do Contato, e exclusão do contato da base passando na URL o ID:
https://teste-contato-4545.rj.r.appspot.com/contato/{ID do contato}

*Caso o ID passado não seja encontrado com algum contato, será lançado uma exceção.


#Utilização Endereço

#GET
Assim como no método do Contato, para retornar todos os contatos, basta apenas chamar o seguinte endpoint:
https://teste-contato-4545.rj.r.appspot.com/endereco

#POST
Similar ao método POST do Contato, porém aqui o JSON é diferente:
https://teste-contato-4545.rj.r.appspot.com/endereco
{
    "rua":"Rua Um",
    "numero":"123",
    "cep":"123456789"
}

*A referência para contato acontece em uma terceira tabela e fica de responsabilidade do contato adicionar o endereço

#PATCH
Parecido com a atualização do Contato, mudando apenas os campos do JSON:
https://teste-contato-4545.rj.r.appspot.com/endereco/{ID do Endereco}
{
    "rua":"Rua Um",
    "numero":"123",
    "cep":"123456789"
}

#DELETE
Para deletar um endereço, é preciso certificar-se que o mesmo não está sendo utilizado em contato, caso esteja, será lançado uma exceção:
https://teste-contato-4545.rj.r.appspot.com/endereco/{ID do Endereco}
