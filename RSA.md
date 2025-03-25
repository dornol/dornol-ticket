#RSA Key Generation

### RSA Key 생성

```shell
openssl genpkey -algorithm RSA -out app.key -outform PEM
openssl rsa -pubout -in app.key -out app.pub
```

### Docker Swarm Secret 생성

```shell
docker secret create dornol-ticket-rsa-key app.key
docker secret create dornol-ticket-rsa-pub app.pub
```