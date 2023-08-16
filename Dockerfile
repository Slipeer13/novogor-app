
# Указываем базовый образ
FROM openjdk:8-jre-alpine

# Установка PostgreSQL и других зависимостей
RUN apk add --no-cache postgresql

# Копирование и настройка Spring приложения
COPY target/novogor-app-0.0.1-SNAPSHOT.jar /app/your-app.jar
COPY entrypoint.sh /entrypoint.sh

RUN chmod +x /entrypoint.sh

# Указываем порт, через который можно получить доступ к контейнеру
EXPOSE 8080

# Указываем переменные среды для настройки базы данных
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/my_db
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=root

# Указываем команду, которая будет выполняться при запуске контейнера
CMD ["/entrypoint.sh"]
