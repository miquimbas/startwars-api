# startwars-api

## O que é este projeto?
Projeto que consome a [API pública do Star Wars](https://swapi.dev/), salva na base de dados sob demanda e fornece rotas com respostas em JSON, para consumo de seus dados.

## Características do projeto?
Criado em Kotlin e baseado no Spring Boot, o projeto também utiliza o Gradle, Retrofit 2, MongoDB e JUnit.

## Como rodar a aplicação?
### Java
Baseada em Java 17, a aplicação pode ser rodada via terminal, sendo necessária a instalação do java na máquina e declarada a variável de ambiente JAVA_HOME, apontando para a pasta da jdk. O processo de instação vai variar, dependendo do sistema operacional, mas uma consulta rápida no Google, já aparecem vários tutoriais. A minha sugestão é [baixar o Intellij Community](https://www.jetbrains.com/idea/download/#section=linux) e fazer todos os processos lá por dentro mesmo.

Após baixada a IDE, ir em File -> Open -> seleciona a pasta raiz do projeto. O projeto vai passar por uma etapa de download das dependências e build, que pode ser acompanhado no rodapé da IDE, no lado direito.

![Captura de tela de 2022-10-16 20-05-39](https://user-images.githubusercontent.com/35075302/196062853-87c18402-2717-43d0-966d-7bd235c32643.png)

### MongoDB
A aplicação roda com o MongoDB, e para funcionar, será necessário subir o mongo na porta 27017. Para isso, temos duas formas de fazer, podemos subir uma instância via docker e rodá-la apenas quando precisarmos ou instalar o Mongo direto na nossa máquina.

Opção 1:
[Subindo o mongo via Docker](https://balta.io/blog/mongodb-docker).

Opção 2:
[Tutorial download MongoDB](https://www.mongodb.com/docs/manual/administration/install-community/).

### Rodando o projeto
Caso você tenha feito o download do Intellij, será necessário acessar a classe StartwarsApiApplication.kt e clicar no ícone de play verde, ao lado do número da linha 9. Conforme imagem:

![Captura de tela de 2022-10-16 20-28-31](https://user-images.githubusercontent.com/35075302/196063971-c09e9721-877b-44bc-ae55-3bcf33fd02c3.png)

Para acompanhar os logs do build, você pode acessar a aba Run, que fica no rodapé da IDE:
![Captura de tela de 2022-10-16 20-32-13](https://user-images.githubusercontent.com/35075302/196064072-9575a5ed-cf94-441a-840a-e1c9d9391ba1.png)

Caso o build dê erro, será necessário selecionar/baixar a JRE 17 (que já vem junto com a jdk). Para isso, siga os seguintes passos:
File -> Settings -> Build, Execution, Deployment -> Build Tools -> Gradle -> Gradle JVM -> Dowload JDK -> selecionar a versão 17 -> Download.
Depois, caso o erro ainda persista, será necessário dizer para a IDE usar a jdk 17. Para isso faça o seguinte: clicar no play verde -> Modify Run Configuration.. -> JRE -> selecionar jdk 17.

Feito esse processo, será necessário clicar no play novamente e selecionar Run StartwarsApiApplication.

## Rotas
O projeto estará disponível na porta 8080, não contêm autenticação e poderá ser acessado conforme especificações abaixo:

| Método  | URL | Descrição |
| ------------- | ------------- | ------------- |
| POST  | localhost:8080/api/planet/{id_api}/load  | Carrega um planeta da [api](https://swapi.dev/documentation#planets) e salva na base da aplicação | 
| GET  | localhost:8080/api/planet/  | Lista todos os planetas salvos na base da aplicação |
| GET | localhost:8080/api/planet/{id} | Busca um planeta pelo ID, que pode ser encontrado na listagem de todos os planetas |
| GET | localhost:8080/api/planet/name/{name} | Busca um planeta pelo seu nome |
| DELETE | localhost:8080/api/planet/{id} | Deleta um planeta pelo seu id, que pode ser encontrado na listagem de todos os planetas |

## Possíveis status de retorno da API
| Status  | Descrição |
| ------------- | ------------- |
| 200 | Quando são retornados registros, mas nenhuma ação de alteração do banco aconteceu |
| 201 | Quando um registro é criado no banco de dados |
| 204 | Quando a ação solicitada ocorreu com sucesso, porém, nenhum registro foi retornado |
| 404 | Quando o parâmetro enviado não existe na base |
| 500 | Ocorreu um erro inesperado |

## Testes
Os testes automatizados escolhidos para esse projeto foram os unitários, utilizando da biblioteca JUnit, mockk e assertj.

### Localização
src -> test -> kotlin

### Como rodar?
Vocẽ pode rodar todos os testes ou rodar classe por classe também. Pode ser que seja necessário alterar a jdk que a IDE vai usar para rodar os testes, para isso, seguir os passos exemplificados na sessão "Rodando o projeto".

#### Rodando todos os testes
Clicar na pasta kotlin com o botão direito -> Run all tests

### Rodando todos os testes de uma classe ou um teste específico
É bem parecido com rodar o servidor. Do lado do nome da classe e do teste, vai ter um play verde, depois de clicar, é só selecionar o Run 'NomeDoTest'.

![Captura de tela de 2022-10-16 21-32-16](https://user-images.githubusercontent.com/35075302/196066827-4a246c31-4b57-4ead-a5c7-527c30b78a21.png)

### Como ver a cobertura de testes?
Clicar na pasta src -> test -> kotlin com o botão direito do mouse, selecionar 'Run all tests with coverage'. Aparecerá uma janela do lado direito com as informações de cobertura de testes por pastas.

Caso não haja a opção de rodar os testes com cobertura, será necessário adicionar um plugin. Vá em File -> Settings -> Plugins -> Marketplace -> Code Coverage for Java.
