FROM eclipse-temurin:21-alpine
LABEL maintainer="DongHyeok <ucceoum@gmail.com>"

EXPOSE 8080

COPY build/libs/app.jar app.jar
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]