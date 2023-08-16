#!/bin/sh

# Запуск PostgreSQL
pg_ctl start

# Запуск Spring приложения
java -jar /app/your-app.jar

# Остановка PostgreSQL перед выходом
pg_ctl stop

# Дополнительные команды или настройки могут быть добавлены здесь

# Завершение скрипта
exec "$@"
