# Octo Events

Octo Events � uma aplica��o que recebe eventos do Github Events via webhooks e os exp�e via API para uso futuro.

![alt text](imgs/octo_events.png)

 O teste consiste na constru��o de 2 endpoints:

## 1. Endpoint Webhook

O endpoint Webhook recebe eventos do Github e os salva no banco. A fim de implement�-lo, leia os seguintes docs:

* Webhooks Overview: https://developer.github.com/webhooks/ 
* Creating Webhooks : https://developer.github.com/webhooks/creating/

O endpoint deve ser disponibilizado em `/events`

## 2. Endpoint Events 

O endpoint Events ir� expor eventos por uma API que os filtrar� atrav�s do n�mero da githubIssueSnapshot:

**Request:**

> GET /issues/1000/events

**Response:**

> 200 OK
```javascript
[ 
  { "action": "open", created_at: "...",}, 
  { "action": "closed", created_at: "...",} 
]
```

**Instru��es de integra��o com o Github **

* Dica: Voc� pode usar o ngrok (https://ngrok.com/) para instalar / debugar as chamadas do webhook. Ele gera uma URL p�blica que ir� rotear para sua m�quina:

   $ sudo ngrok http 4000 

![alt text](imgs/ngrok.png)

   GitHub

![alt text](imgs/add_webhook.png)
 
**Observa��es finais**

* Use qualquer biblioteca ou framework que quiser, voc� n�o precisa fazer nada "do zero";
* � obrigat�rio escrever testes, use seu framework favorito pra isso;
* Use o Postgres 9.6 como banco;
* Adicione um README.md com instru��es para executar o projeto.
* Executaremos seu c�digo com a �ltima vers�o do Java ou Kotlin (se usar);
* Sucesso! :-)
