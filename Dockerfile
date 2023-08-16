FROM openjdk:17.0.2-jdk-slim-buster

# Установка PostgreSQL
RUN apk add --no-cache postgresql

# Копирование Spring приложения
COPY target/novogor-app-0.0.1-SNAPSHOT.jar /app/your-app.jar

# Установка supervisord
RUN apk add --no-cache supervisor
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/my_db
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=root
EXPOSE 8080
CMD ["supervisord", "-n"]

