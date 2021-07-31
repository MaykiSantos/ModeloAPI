<div align=center>
  <img src="https://raw.githubusercontent.com/MaykiSantos/ModeloAPI/main/api%20logo.png" width=90px>
  <h1>ModeloAPI</h1>
</div>

<div>
  <h2> :computer: Sobre</h2>
  <p>Api simples com sistema de autenticação, paginação, filtro e ordenação. Projeto desenvolvido Utilizando Spring boot</p>
</div>

<h2 id="requisitos"> :eyes: Requisitos</h2>
<ul>
  <li><a href="https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html">Java 11</a></li>
  <li><a href="https://downloads.mariadb.org/">MYSQL</a></li>
</ul>

<h2 id="tecnologias"> :man_technologist: Tecnologias</h2>
<ul>
  <li><a href="https://www.java.com/pt-BR/download/">Java</a></li>
  <li><a href="https://www.mysql.com/downloads/">Mysql</a></li>
  <li><a href="https://maven.apache.org/">Maven</a></li>
  <li><a href="https://spring.io/">Spring Boot</a></li>
</ul>


## :book: Como Executar

### >Projeto Compilado

1. Baixe o arquivo ModelAPi-V1.jar.
2. Crie um banco de dados, um usurio e uma senha no Mysql.
3. Inicie o terminal(CMD) do windows.
4. Navege até o diretório onde você salvou o arquivo ModelAPi-V1.jar.
5. Execute o seguinte comando. 
>java -jar -Dspring.profiles.active=prod -DBANCO_DE_DADOS_NOME=**valorAqui** -DBANCO_DE_DADOS_URL=**valorAqui** -DBANCO_DE_DADOS_USUARIO="**valorAqui**" -DBANCO_DE_DADOS_SENHA="**valorAqui**" -DSERVER_PORT=**valorAqui** -DCHAVE_SECRETA_APP="**valorAqui**" -DDUACAO_SESSAO=**valorAqui** ModelAPi-V1.jar

Os valores marcados como **valorAqui** devem ser substituidos seguindo a seguinte tabela:

Valor | Desrição
-----|------
DBANCO_DE_DADOS_NOME | nome do banco de dados utilizado pela api
DBANCO_DE_DADOS_URL | url de acesso ao banco de dados
DBANCO_DE_DADOS_USUARIO | usuario de acesso ao banco de dados
DBANCO_DE_DADOS_SENHA | senha do usuario
DSERVER_PORT | define a porta onde a aplicação irá rodar
DCHAVE_SECRETA_APP | chave secreta para criação dos tokens de acesso.
DDUACAO_SESSAO | tempo de duração dos tokens de acesso. Esse valor deve ser passado em milissegundos. Ex: 3600000 = 1hora

**O código final deve ser similar a este:**

>java -jar -Dspring.profiles.active=prod -DBANCO_DE_DADOS_NOME=**modeloapi** -DBANCO_DE_DADOS_URL=**jdbc:mysql://localhost:3306/modeloapi** -DBANCO_DE_DADOS_USUARIO="**root**" -DBANCO_DE_DADOS_SENHA="**1234**" -DSERVER_PORT=**8081** -DCHAVE_SECRETA_APP="**2sds3sda1313dfgd1UTTY3213**" -DDUACAO_SESSAO=**3600000** ModelAPi-V1.jar

OBS: Quando o terminal for fechado a API ternimará sua execução.


### >Execução pela IDE eclipse

1. Copie o repositório para a sua máquina.
2. Crie um banco de dados, um usurio e uma senha no Mysql.
3. Inicie a IDE do ecipse
4. No menu superior clique em File > Import...
5. Procure a opção "Existing Maven Projects" selecione está opção e clique em "Next".
6. No canto soperior direito, clique em "Browse" navegue até a pasta do projeto, selecione a pasta do projeto e clique em "Selecionar pasta"
7. Apos confirmar a pasta clique em "Next>"
8. Com o projeto já importado vá até a pasta src/main/resources e abra  o arquivo aplication.properties
9. Altere os valores dos campos **server.contextPath**, **spring.datasource.url**, **spring.datasource.username** e **spring.datasource.password** para que corresponda ao seu banco de dados
10. Vá até a classe ModeloApiApplication, clique com o botão direito do mouse nela e selecione "Run as" e clique em "Java Application"

<div>
  <h2>:rocket: Consumindo a API</h2>
  <p>
  Documentação feita utilizando o Postman.</br>
  Documantação: https://documenter.getpostman.com/view/12149762/TzsYM8tY
  </br>
  <b>Usuario e senha para autenticação:</br>
  Email: teste@gmail.com,</br>
  Senha: 123456</b></br>
  </p>
</div>
