# Проект по автоматизации API-тестов для проекта Reqres.In

## 🔨 Технологии и инструменты:
<table>
  <tr>
    <th><a href="https://selenide.org/" target="_blank">Selenide</a>
    <th><a href="https://rest-assured.io/" target="_blank">Rest Assured</a></th>
    <th><a href="https://www.oracle.com/java/" target="_blank">Java</a></th>
    <th><a href="https://www.jetbrains.com/idea/" target="_blank">IntelliJ IDEA</a></th>
    <th><a href="https://github.com/" target="_blank">GitHub</a></th>
    <th><a href="https://junit.org/junit5/" target="_blank">JUnit 5</a></th>
    <th><a href="https://gradle.org/" target="_blank">Gradle</a></th>
    <th><a href="https://qameta.io/" target="_blank">Allure</a></th>
  </tr>
</table>

## :clipboard: Тестовые сценарии
- Тест №1: Получение данных зарегистрированного пользователя - GET
- Тест №2: Создание нового пользователя через API - POST
- Тест №3: Удаление пользователя - DELETE
- Тест №4: Регистрация пользователя - POST
- Тест №5: Авторизация пользователя - POST
- Тест №6: Обновление данных пользователя - PUT
- Тест №7: Обновление данных пользователя - PATCH
---
- Тесты реализованы на <code>Java</code> с использованием фреймворка <code>Selenide</code>.
- В качестве сборщика используется <code>Gradle</code>.
- Для модульного тестирования применён фреймворк <code>JUnit 5</code>.
- Для работы с API используется <code>Rest Assured</code>.
---
## :rocket: Команды для запуска

```bash
gradle clean test
```

```bash
./gradlew.bat clean test
```
---
