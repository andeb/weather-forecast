# Previsão do Tempo 

Esta aplicação foi desenvolvida como teste para um processo seletivo. O objetivo é utilizar a API do [OpenWeather](http://openweathermap.org/api) para exibir a previsão do tempo para uma cidade.

## Construção do aplicativo

Antes de construir o projeto, verifique os pré-requisitos:

1. Node 5.8.0
2. Npm 3.7.3
3. Gradle 2.12
4. JDK 8 (necessário estar no PATH para o Gradle utilizar para realização do build)
5. Mongo no ar (é utilizado a base padrão `test` para inserção da `collection`)

Para construir este projeto, execute os passos abaixo:

1. Faça clone deste repositório.
2. Execute o comando:

    `mongod`
	
3. Execute o comando:

    `npm install`

4. Execute o comando: 

    `bower install`

5. Execute o comando:

   `gradlew`

6. Acessar a URL [http://localhost:8080](http://localhost:8080).

PS: caso ocorra o problema `Cannot find module 'npm\bin\node_modules\npm\bin\npm-cli.js'`, execute o comando `npm install npm`.

## Importar projeto no Eclipse

Para importar o projeto no Eclipse basta importar um projeto Gradle e apontar para o repositório. Necessário possuir o plugin Gradle Buildship ou similar.

## Requisitos do sistema

Os requisitos técnicos são:

1. Tela de cadastro de cidade e listagem das cidades cadastradas. Permitir o usuário cadastrar somente cidades validas na API (que retornem dados). Na listagem de cidades deve ter um link para visualizar os detalhes da previsão (Tela de detalhe das previsões).
2. Tela de detalhe das previsões. Exibe um "forecast" de 5 dias para a cidade.

## Arquitetura

Para o projeto, foi feito a distinção clara entre frontend e backend.

Para o frontend, é utilizado a stack `Angular + Bootstrap`. O objetivo é possuir uma aplicação com frontend robusto e de fácil manuntenção. É utilizado `gulp` para executar minificação de arquivos CSS e JS e BrowserSync. O `Angular` se comunica com o backend através de serviços REST.

Para a construção dos serviços REST é utilizado o `Spring Boot`. O objetivo é fornecer uma construção fácil da implementação evitando a dependência de um servidor de aplicação. Apesar de ser utilizado Spring, a troca para um JAX-RS puro é fácil de fazer pois só muda o nome das anotações utilizadas praticamente.

As cidades disponíveis para exibição da previsão são armazenadas no banco MongoDB. O objetivo é ter um banco de fácil uso para uma necessidade simples.

## Implementação

Para implementação, foi adotado de início o uso de *scaffolding* com o `JHipster` para geração do esqueleto do projeto. No final, o *scaffolding* gerado é verboso demais e foi feito a limpa de quase tudo, o legado do JHipster acabou sendo o arquivo `gulpfile.js` e todos as tarefas que nele possui.

As classes eram poucas, mas onde era possível foi feito uso da programação funcional do Java 8 afim de mostrar o seu uso.

Foi necessário uso somente de duas classes de domínio: `City` (representação de uma cidade) e `Forecast` (representação de uma previsão).

Os principais componentes que corresponde os serviços REST são:

1. `Resource`: endpoint dos serviço, onde é mapeado os serviços.
2. `Service`: um resource se comunico com um serviço a fim de lhe oferecer informações sobre como resolver tal requisição. No caso da previsão, este é o ponto onde é feito o contato com a API do OpenWeather.
3. `Repository`: no caso da cidade, esta classe se comunica com o repositório do MongoDB.
4. `DTO`: classes que carregam a comunicação entre o backend e o frontend.

Para contato com a API do OpenWeather foi utilizado o recurso `RestTemplate` do Spring. O retorno do serviço foi mapeado para um classe de domínio que contém os campos necessários para utilização no front. Esta classe de domínio é traduzida para o DTO que é enviado para o front. 

## Testes

Foi feito um teste automatizado simples para exemplificar os testes do serviço de cidades. Pode ser executado através do comando:

    gradlew test

