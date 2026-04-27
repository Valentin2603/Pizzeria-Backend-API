# 🍕 Pizzeria Backend API

Backend-приложение для сервиса онлайн-заказа пиццы с поддержкой кастомизации и автоматического расчёта стоимости.

## 🚀 Возможности

- Конструктор пиццы (основа + ингредиенты)
- Поддержка бортиков с логикой совместимости
- Система заказов с позициями (`OrderItem`)
- Расчёт стоимости через отдельный `PricingService`
- Поддержка размеров пиццы (коэффициенты)
- Валидация данных и обработка ошибок
- REST API + Swagger документация

## 🧱 Архитектура
Controller → Service → Repository → Entity
↓
DTO / Mapper

- бизнес-логика в сервисах  
- DTO отделены от entity  
- расчёты вынесены в отдельный сервис  

## 🛠️ Технологии

Java 21, Spring Boot, Spring Web, Spring Data JPA (Hibernate), PostgreSQL, Docker Compose, Maven, Lombok, Bean Validation, Swagger/OpenAPI

## 🐳 Запуск

```bash
git clone https://github.com/Valentin2603/Pizzeria-Backend-API.git
cd Pizzeria-Backend-API
docker compose up --build
```

Swagger:
http://localhost:8080/swagger-ui/index.html

📌 Практическая ценность

Проект моделирует backend сервиса доставки еды:
кастомизация продукта, работа с зависимыми сущностями, бизнес-ограничения и расчёт стоимости заказа.
