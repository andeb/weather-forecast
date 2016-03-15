# Previs�o do Tempo 

Esta aplica��o foi desenvolvida como teste para um processo seletivo. O objetivo � utilizar a API do [OpenWeather](http://openweathermap.org/api) para exibir a previs�o do tempo para uma cidade.

## Constru��o do aplicativo

Antes de construir o projeto, verifique os pr�-requisitos:

1. Node 5.8.0
2. Npm 3.7.3
3. Gradle 2.12
4. JDK 8 (necess�rio estar no PATH para o Gradle utilizar para realiza��o do build)
5. Mongo no ar (� utilizado a base padr�o `test` para inser��o da `collection`)

Para contruir este projeto, execute os passos abaixo:

1. Fa�a clone deste reposit�rio.
2. Execute o comando:

    `mongod`
	
3. Execute o comando:

    `npm install`

4. Execute o comando: 

    `bower install`

5. Execute o comando:

   ` ./gradlew`

6. Acessar a URL [http://localhost:8080](http://localhost:8080).

PS: caso ocorra o problema `Cannot find module 'npm\bin\node_modules\npm\bin\npm-cli.js'`, execute o comando `npm install npm`.

## Importar projeto no Eclipse

Para importar o projeto no Eclipse basta importar um projeto Gradle e apontar para o reposit�rio. Necess�rio possuir o plugin Gradle Buildship ou similar.

## Requisitos do sistema

Os requisitos t�cnicos s�o:

1. Tela de cadastro de cidade e listagem das cidades cadastradas. Permitir o usu�rio cadastrar somente cidades validas na API (que retornem dados). Na listagem de cidades deve ter um link para visualizar os detalhes da previs�o (Tela de detalhe das previs�es).
2. Tela de detalhe das previs�es. Exibe um "forecast" de 5 dias para a cidade.

## Arquitetura

Para o projeto, foi feito a distin��o clara entre frontend e backend.

Para o frontend, � utilizado a stack `Angular + Bootstrap`. O objetivo � possuir uma aplica��o com frontend robusto e de f�cil manunten��o. � utilizado `gulp` para executar minifica��o de arquivos CSS e JS e BrowserSync. O `Angular` se comunica com o backend atrav�s de servi�os REST.

Para a constru��o dos servi�os REST � utilizado o `Spring Boot`. O objetivo � fornecer uma constru��o f�cil da implementa��o evitando a depend�ncia de um servidor de aplica��o. Apesar de ser utilizado Spring, a troca para um JAX-RS puro � f�cil de fazer pois s� muda o nome das anota��es utilizadas praticamente.

As cidades dispon�veis para exibi��o da previs�o s�o armazenadas no banco MongoDB. O objetivo � ter um banco de f�cil uso para uma necessidade simples.

## Implementa��o

Para implementa��o, foi adotado de in�cio o uso de *scaffolding* com o `JHipster` para gera��o do esqueleto do projeto. No final, o *scaffolding* gerado � verboso demais e foi feito a limpa de quase tudo, o legado do JHipster acabou sendo o arquivo `gulpfile.js` e todos as tarefas que nele possui.

As classes eram poucas, mas onde era poss�vel foi feito uso da programa��o funcional do Java 8 afim de mostrar o seu uso.

Foi necess�rio uso somente de duas classes de dom�nio: `City` (representa��o de uma cidade) e `Forecast` (representa��o de uma previs�o).

Os principais componentes que corresponde os servi�os REST s�o:

1. `Resource`: endpoint dos servi�o, onde � mapeado os servi�os.
2. `Service`: um resource se comunico com um servi�o a fim de lhe oferecer informa��es sobre como resolver tal requisi��o. No caso da previs�o, este � o ponto onde � feito o contato com a API do OpenWeather.
3. `Repository`: no caso da cidade, esta classe se comunica com o reposit�rio do MongoDB.
4. `DTO`: classes que carregam a comunica��o entre o backend e o frontend.

Para contato com a API do OpenWeather foi utilizado o recurso `RestTemplate` do Spring. O retorno do servi�o foi mapeado para um classe de dom�nio que cont�m os campos necess�rios para utiliza��o no front. Esta classe de dom�nio � traduzida para o DTO que � enviado para o front. 

## Testes

Foi feito um teste automatizado simples para exemplificar os testes do servi�o de cidades. Pode ser executado atrav�s do comando:

    `./gradlew test`

