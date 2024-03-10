# Sobre o projeto 
## ReadVista 📚
ReadVista é um API de gerenciamento de bibliotecas projetada para facilitar o processo de empréstimo e devolução de livros.

<br>

## 🎥  Assista ao vídeo do projeto  
### Vídeo detalhado do projeto onde cada recurso é explicado e demonstrado em ação! Veja abaixo:




https://github.com/devDouglasN/readvista-backend/assets/122110326/f151268e-073a-4f6d-9261-e9d30850bd67



<br>

## 🏁 Inicializando
### Foi realizado o deploy desta aplicação na AWS. Para testá-la, clique no link abaixo:

http://3.141.192.72:8081/swagger-ui/index.html

Abaixo estão as credenciais padrão de login para acesso à aplicação:

  "Usuário": 
  ```
  usuario@test.com
  ````
  "senha": 
  ```
  senha
````

<br>

http://localhost:8080/login  
http://localhost:8080/customers  
http://localhost:8080/books  
http://localhost:8080/loans  


<br>

## ⏳ Getting Started
### Você pode:
+ [Baixar o ZIP do projeto](https://github.com/devDouglasN/readvista-backend/archive/refs/heads/main.zip) e abri-lo em uma IDE de sua preferência.

### OU 
- Clonar o repositório <span style="color: grey;">https://github.com/devDouglasN/readvista-backend.git</span>
- Rodar <span style="color: grey;">mvn clean install</span> para instalar as dependências do projeto.
- Rodar <span style="color: grey;">mvn spring-boot:run</span> para subir a aplicação.
- A aplicação estará disponível na porta <span style="color: grey;">8080</span>.
- (Para alterar a porta, modifique o <span style="color: grey;">server.port</span> no arquivo <span style="color: grey;">application.properties</span>).

Antes de utilizar a API, lembre-se de primeiro fazer a chamada para a API "Efetuar Login" para gerar o token de acesso. Este token possui uma validade de 2 horas.


<br>
<br>

##  ![Trello](https://img.shields.io/badge/Trello-0052CC?style=for-the-badge&logo=trello&logoColor=white)



Você pode acompanhar o progresso do projeto no [Trello](https://trello.com/b/dcguxWGX/readvista).

<br>

## ⚙️ Funcionalidades
+ CRUD de Customers;
+ CRUD de Books;
+ CRUD de Loans;
+ Emprésimos de livros;
+ Documentação Swagger;
+ Segurança JWT;

<br>




## 🛠️ Tecnologias

<table>
  <tr>
    <td>Java</td>
    <td>Spring Boot</td>
    <td>Spring MVC</td>
    <td>Spring Security</td>
    <td>JPA</td>
    <td>Maven</td>
    <td>Auth0 JWT</td>
    <td>MySQL Workbench</td>
    <td>MySQL Connector/J</td>
    <td>H2 Database</td>
    <td>Springdoc OpenAPI</td>
  </tr>
  <tr>
     <td>17</td>
    <td>3.2.1</td>
    <td>3.2.1</td>
    <td>3.2.1</td>
    <td>3.2.1</td>
    <td>3.9.6</td>
    <td>4.4.0</td>
    <td>8.0</td>
    <td>3.2.1</td>
    <td>3.2.1</td>
    <td>2.3.0</td>
  </tr>
</table>

<br>


## 🔗 Endpoints

Você pode utilizar o Insomnia, Postman ou qualquer outra ferramenta de sua preferência para realizar as requisições.


### Empréstimo
POST ( Cadastrando um empréstimo )
+ http://localhost:8080/loans

```
 {
       "idBook": "Digite o ID do livro",
       "idCustomer": "Digite o ID do cliente",
       "data": "Data para empréstimo. Ex: 2024-03-09T12:06:21.132Z"
       "returnDate": "Devolução. Ex: 2024-03-22T12:06:21.132Z"
}
````

GET ( Retornando lista de empréstimos )
+ http://localhost:8080/loans

```
 {
   "id": "ID do empréstimo a ser retornado: 1",
   "loanDate": "Data em que o livro foi emprestado",
   "returnDate": "Data em que o livro deve ser retornado",
   "book": "ID do livro",
   "customer": "ID do cliente",
   "bookName": "Nome do livro emprestado",
   "customerName": "Nome do cliente em que o livro foi emprestado"
}

````

GET (Retornando empréstimo por ID )
+ http://localhost:8080/loans/{id}
Basta adicionar o ID desejado na requisição. 


<br>

### Clientes
POST ( Cadastrando um cliente )
+ http://localhost:8080/customers

```
 {
        "nome": "Nome Completo do cliente",
        "email": "Endereço de Email do cliente",
        "cpf": "Número de CPF do cliente",
        "password": "Senha para cliente"
}

````
GET ( Retornando lista de clientes )
+ http://localhost:8080/customers

```
 {
       "name": "Nome do cliente a ser retornado",
       "cpf": "CPF do a ser retornado",
       "email": "Email do cliente a ser retornado",
       "password": "Senha do cliente a ser retornado"
    }

````

PUT (Atualizando dados do cliente)
http://localhost:8080/customers
```
{
       "nome": "Atualize o Nome Completo",
       "cpf": "Atualize o CPF",
       "email": "Atualize o Endereço de Email",
       "password": "Atualize a senha"   
}
````

DELETE ( Deletando um cliente ) 
+ http://localhost:8080/customers/{id}   
Basta adicionar o ID desejado na requisição.

<br>

GET (Retornando por ID )
+ http://localhost:8080/customers/{id}
Basta adicionar o ID desejado na requisição.   

<br>

### Livros
POST ( Cadastrando um livro )
+ http://localhost:8080/books  

Abaixo estão o status e as condições que serão utilizadas para o cadastro do livro e que também será testado dentro da regra de negócios:  
"Status":
- BORROWED, 
- RETURNED, 
- OVERDUE   

"Condition": 
- GOOD, 
- DAMAGED, 
- LOST

```
{
      "title": "Título do livro",
      "author": "Autor do livro",
      "yearOfPublication": "Ano de publicação do livro. Ex: 2010",
      "status": "Ex: BORROWED",
      "condition": "Ex: GOOD"

}

````
GET ( Retornando lista de livros )
+ http://localhost:8080/books

```
 {
   "id": ID do livro a ser retornado,
   "title": "Título do livro a ser retornado",
   "author": "Nome do do autor a ser retornada",
   "yearOfPublication": "Ano do livro a ser retornado",
   "status": "Status do livro a ser retornada",
   "condition": "Condição do livro a ser retornado"
}
,
````
PUT (Atualizando livro)
http://localhost:8080/books   
Primeiramente digite o ID do livro que deseja atualizar

```
{
   "title": "Atualize o título do livro",
   "author": "Atualize o nome do autor",
   "yearOfPublication": "Atualize o ano de publicação do livro",
   "status": "Atualize o status do livro",
   "condition": "Atualize a condição do livro"
}

````

GET (Retornando por ID )
+ http://localhost:8080/books/{id}
Basta adicionar o ID desejado na requisição. 

<br>

DELETE ( Deletando um livro ) 
+ http://localhost:8080/books/{id}   
Basta adicionar o ID desejado na requisição.



<br>
<br>

Developed by [Douglas do Nascimento](https://github.com/devDouglasN).
