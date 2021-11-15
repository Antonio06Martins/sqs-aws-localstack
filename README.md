<h1>Integração simples de mensagens localstack e Spring Cloud</h1> 

<p align="center">
  <img src="https://img.shields.io/static/v1?label=spring&message=framework&color=green&style=for-the-badge&logo=SPRING"/>
  <img src="http://img.shields.io/static/v1?label=Spring&message=2.5.6&color=red&style=for-the-badge&logo=spring"/>
  <img src="https://img.shields.io/static/v1?label=&message=AWS-SQS&color=gray&style=for-the-badge&logo=AWS-SQS"/>
  <img src="https://img.shields.io/static/v1?label=&message=AWS-SnS&color=gray&style=for-the-badge&logo=AWS-SNS"/>
  <img src="https://img.shields.io/static/v1?label=&message=Docker&color=gray&style=for-the-badge&logo=Docker"/>
  <img src="http://img.shields.io/static/v1?label=TESTES&message=%3E1&color=GREEN&style=for-the-badge"/>
  <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=RED&style=for-the-badge"/>
</p>


### Tópicos

[Descrição do projeto](#descrição-do-projeto)

[Funcionalidades](#funcionalidades)

[Pré-requisitos](#pré-requisitos)

[Como rodar a aplicação](#como-rodar-a-aplicação)


## Descrição do projeto

<p align="justify">
  O aplicativo demonstra como a integração do Spring nos permite criar um produtor _SNS_ e um ouvinte _SQS_ com pouco código e configuração.
  A cereja do bolo é _LocalStack_. Em vez de nos conectarmos a uma conta AWS real, nos conectamos a um servidor LocalStack que nos fornece todos os serviços de que precisamos para o aplicativo.
</p>

## Funcionalidades 

* Gerar pedido
```
http://localhost:8080/create-order
```

## Pré-requisitos

* [Docker](https://docs.docker.com/get-docker/)
* [JAVA](https://www.java.com/pt-BR/)
* [MAVEN](https://maven.apache.org/)
* [LOCALSTACK](https://github.com/localstack/localstack)



## Como rodar a aplicação:

* No terminal, clone o projeto:
```
git clone https://github.com
```

* Com o Docker iniciado:
```
docker-compose up -d
```

* Crie recursos no servidor LocalStack. Precisamos de um tópico SNS e duas filas SQS para este aplicativo.

Para criar o tópico:

```
aws --endpoint-url=http://localhost:4566 sns create-topic --name order-created-topic
```


Para criar as filas(queues):

```
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name order-queue

aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name order-queue-2
```

Para inscrever as filas no tópico:

```
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:eu-central-1:000000000000:order-created-topic --protocol sqs --notification-endpoint arn:aws:sqs:eu-central-1:000000000000:order-queue

aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:eu-central-1:000000000000:order-created-topic --protocol sqs --notification-endpoint arn:aws:sqs:eu-central-1:000000000000:order-queue-2
```

Para verificar, você pode listar filas e assinaturas:

```
aws --endpoint-url=http://localhost:4566 sqs list-queues
aws --endpoint-url=http://localhost:4566 sns list-subscriptions
```

* Execute o aplicativo usando `mvn spring-boot:run`

* Iniciar a aplicação REVER ESTE PONTO

* Executar as requisições para os endpoints do tipo **GET**:

```
http://localhost:8080/create-order
```
