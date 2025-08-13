package tests;

import io.qameta.allure.*;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.RestApiSpec.baseRequestSpecification;
import static specs.RestApiSpec.baseResponseSpecification;

@Owner("trubnikov")
@Tag("rest_api")
public class ReqresAPITests extends TestBase {

    @Feature("Взаимодействие с пользователями")
    @Story("Получение данных пользователя")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @DisplayName("Получение данных зарегистрированного пользователя")
    void getSingleRegisteredUserDataTest() {

        GetSingleUserResponseModel response = step("Отправка GET запроса для пользователя", () ->
                given(baseRequestSpecification)
                .when()
                        .get(userPath + existingUserId)
                .then()
                        .spec(baseResponseSpecification(200))
                        .extract().as(GetSingleUserResponseModel.class));

        step("Проверка корректности данных пользователя", () -> {
            assertNotNull(response.getData(),
                    "Данные пользователя не должны быть null");
            assertEquals(existingUserId, response.getData().getId(),
                    "ID пользователя должен соответствовать запрошенному");
            assertEquals("janet.weaver@reqres.in", response.getData().getEmail(),
                    "Email должен соответствовать ожидаемому");
            assertNotNull(response.getData().getFirst_name(),
                    "Имя пользователя должно быть заполнено");
        });
    }

    @Feature("Взаимодействие с пользователями")
    @Story("Создание нового пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Создание нового пользователя через POST запрос")
    void postCreateNewUserTest() {


        CreateUserModel newUserData = new CreateUserModel("John Doe", "QA Engineer", "2024");

        CreateUserResponseModel response = step("Отправка POST запроса для создания пользователя", () ->
                given(baseRequestSpecification)
                        .body(newUserData)
                .when()
                        .post(userPath)
                .then()
                        .spec(baseResponseSpecification(201))
                        .extract().as(CreateUserResponseModel.class));

        step("Проверка успешного создания пользователя", () -> {
            assertEquals("John Doe", response.getName(),
                    "Имя должно совпадать с отправленным");
            assertEquals("QA Engineer", response.getJob(),
                    "Должность должна совпадать");
            assertEquals("2024", response.getYear(),
                    "Год должен совпадать");
            assertNotNull(response.getId(),
                    "ID созданного пользователя не должен быть null");
            assertNotNull(response.getCreatedAt(),
                    "Дата создания должна быть заполнена");
        });
    }

    @Feature("Взаимодействие с пользователями")
    @Story("Удаление пользователя")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @DisplayName("Удаление пользователя через DELETE запрос")
    void deleteUserTest() {

        String response = step("Отправка DELETE запроса", () ->
                given(baseRequestSpecification)
                .when()
                        .delete(userPath + newUserId)
                .then()
                        .spec(baseResponseSpecification(204))
                        .extract().asString());

        step("Проверка успешного удаления", () -> {
            assertTrue(response.isEmpty(),
                    "Тело ответа должно быть пустым при статусе 204");
        });
    }

    @Feature("Аутентификация")
    @Story("Регистрация пользователя")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @DisplayName("Успешная регистрация пользователя")
    void postRegisterSuccessfulTest() {

        PostRegisterSuccessfulModel registerData =
                new PostRegisterSuccessfulModel(userEmail, userPass);

        PostRegisterSuccessfulResponseModel response = step("Отправка POST запроса регистрации", () ->
                given(baseRequestSpecification)
                        .body(registerData)
                .when()
                        .post(userRegister)
                .then()
                        .spec(baseResponseSpecification(200))
                        .extract().as(PostRegisterSuccessfulResponseModel.class));

        step("Проверка успешной регистрации", () -> {
            assertNotNull(response.getId(),
                    "ID пользователя должен быть возвращён");
            assertNotNull(response.getToken(),
                    "Токен должен быть возвращён");
            assertTrue(response.getToken().length() >= 10,
                    "Токен должен иметь минимальную длину 10 символов");
            assertTrue(response.getToken().matches("^[a-zA-Z0-9]+$"),
                    "Токен должен содержать только буквы и цифры");
            assertEquals("4", response.getId(),
                    "ID должен соответствовать ожидаемому");
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken(),
                    "Токен должен соответствовать документации API");
        });
    }

    @Feature("Аутентификация")
    @Story("Авторизация пользователя")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @DisplayName("Успешная авторизация пользователя")
    void postLoginSuccessfulTest() {

        LoginModel loginData = new LoginModel(userEmail, userPassLogin);

        LoginResponseModel response = step("Отправка POST запроса авторизации", () ->
                given(baseRequestSpecification)
                        .body(loginData)
                .when()
                        .post(userLogin)
                .then()
                        .spec(baseResponseSpecification(200))
                        .extract().as(LoginResponseModel.class));

        step("Проверка успешной авторизации", () -> {
            assertNotNull(response.getToken(),
                    "Токен не должен быть null");
            assertTrue(response.getToken().length() >= 10,
                    "Токен должен иметь минимальную длину 10 символов");
            assertTrue(response.getToken().matches("^[a-zA-Z0-9]+$"),
                    "Токен должен содержать только буквы и цифры");
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken(),
                    "Токен должен соответствовать ожидаемому значению");
        });
    }

    @Feature("Взаимодействие с пользователями")
    @Story("Обновление данных пользователя")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Полное обновление пользователя через PUT запрос")
    void putUpdateUserTest() {

        DataModel updateData =
                new DataModel("Test Name", "1996", "Auto QA");

        DataResponseModel response = step("Отправка PUT запроса для обновления", () ->
                given(baseRequestSpecification)
                        .body(updateData)
                .when()
                        .put(userPath + existingUserId)
                .then()
                        .spec(baseResponseSpecification(200))
                        .extract().as(DataResponseModel.class));

        step("Проверка обновления данных", () -> {
            assertEquals("Test Name", response.getName(),
                    "Имя должно быть обновлено");
            assertEquals("1996", response.getYear(),
                    "Год должен быть обновлён");
            assertEquals("Auto QA", response.getJob(),
                    "Должность должна быть обновлена");
            assertNotNull(response.getUpdatedAt(),
                    "Дата обновления должна быть заполнена");
        });
    }

    @Feature("Взаимодействие с пользователями")
    @Story("Частичное обновление пользователя")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Частичное обновление пользователя через PATCH запрос")
    void patchUpdateUserTest() {


        DataModel patchData = new DataModel("", "1990", "");

        DataResponseModel response = step("Отправка PATCH запроса", () ->
                given(baseRequestSpecification)
                        .body(patchData)
                .when()
                        .patch(userPath + existingUserId)
                .then()
                        .spec(baseResponseSpecification(200))
                        .extract().as(DataResponseModel.class));

        step("Проверка частичного обновления", () -> {
            assertEquals("1990", response.getYear(),
                    "Год должен быть обновлён");
            assertNotNull(response.getUpdatedAt(),
                    "Дата обновления должна быть заполнена");
        });
    }
}

